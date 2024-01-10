package com.saaweel;

import javafx.scene.control.Label;

public class BookController {
    public Label id;
    public Label title;
    public Label author;

    public void setData(Book book) {
        id.setText("ID: " + book.getId());
        title.setText("Titulo: " + book.getTitle());
        author.setText("Autor: " + book.getAuthor());
    }
}
