package com.saaweel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.*;

public class PrimaryController {
    public ListView<Book> booksListView;
    public VBox content;
    public TextField searchInput;
    public TextField bookId;
    public TextField bookTitle;
    public TextField bookAuthor;
    public TextField bookCategory;
    public CheckBox loanedBook;
    private ObservableList<Book> booksData;
    private Connection connection;

    public void initialize() {
        booksData = FXCollections.observableArrayList();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");
        } catch (SQLException e) {
            e.fillInStackTrace();
        }

        loadFromDB();

        booksListView.setItems(booksData);

        booksListView.setCellFactory(param -> new BookCellFactory());

        booksListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                bookId.setText(String.valueOf(newValue.getId()));
                bookTitle.setText(newValue.getTitle());
                bookAuthor.setText(newValue.getAuthor());
                bookCategory.setText(newValue.getCategory());
                loanedBook.setSelected(newValue.isLoaned());
                content.setVisible(true);
            }
        });

        searchInput.textProperty().addListener((observable, oldValue, newValue) -> booksListView.setItems(booksData.filtered(book -> {
            String searchTerm = newValue.toLowerCase();

            return String.valueOf(book.getId()).toLowerCase().contains(searchTerm)
                    || book.getTitle().toLowerCase().contains(searchTerm)
                    || book.getAuthor().toLowerCase().contains(searchTerm);
        })));
    }

    public void newBook() {
        bookId.setText("0");
        bookTitle.setText("");
        bookAuthor.setText("");
        bookCategory.setText("");
        loanedBook.setSelected(false);
        content.setVisible(true);
    }

    public void saveBook() {
        int id = Integer.parseInt(bookId.getText());
        String title = bookTitle.getText();
        String author = bookAuthor.getText();
        String category = bookCategory.getText();
        int loaned = loanedBook.isSelected() ? 1 : 0;

        String query;
        if (id == 0) {
            query = "INSERT INTO libros (titulo, autor, genero, prestado) VALUES (?, ?, ?, ?)";
        } else {
            query = "UPDATE libros SET titulo=?, autor=?, genero=?, prestado=? WHERE id=?";
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, category);
            preparedStatement.setInt(4, loaned);

            if (id != 0) {
                preparedStatement.setInt(5, id);
            }

            preparedStatement.executeUpdate();

            if (id != 0) {
                for (Book book : booksData) {
                    if (book.getId() == id) {
                        book.setTitle(title);
                        book.setAuthor(author);
                        book.setCategory(category);
                        book.setLoaned(loaned == 1);
                        booksData.set(booksData.indexOf(book), book);
                        break;
                    }
                }
            } else {
                loadFromDB();

                content.setVisible(false);
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        }
    }

    public void deleteBook() {
        int id = Integer.parseInt(bookId.getText());

        if (id != 0) {
            String query = "DELETE FROM libros WHERE id=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();

                for (Book book : booksData) {
                    if (book.getId() == id) {
                        booksData.remove(book);
                        break;
                    }
                }
            } catch (SQLException e) {
                e.fillInStackTrace();
            }
        }
    }

    private void loadFromDB() {
        booksData.clear();

        String query = "SELECT * FROM libros";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("titulo");
                String author = resultSet.getString("autor");
                String category = resultSet.getString("genero");
                int loaned = resultSet.getInt("prestado");

                Book book = new Book(id, title, author, category, loaned);
                booksData.add(book);
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        }
    }
}