package com.saaweel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class PrimaryController {
    @FXML
    public ListView<Product> listView;

    public void initialize() {
        ObservableList<Product> products = FXCollections.observableArrayList();

        try {
            products.add(new Product("Laptop", 999.99, 5));
            products.add(new Product("Teléfono", 299.99, 10));
            products.add(new Product("Tablet", 199.99, 8));
            products.add(new Product("Cámara", 149.99, 15));
            products.add(new Product("Auriculares", 49.99, 20));
            products.add(new Product("Teclado", 29.99, 30));
            products.add(new Product("Mouse", 19.99, 25));
            products.add(new Product("Monitor", 199.99, 12));
            products.add(new Product("Impresora", 149.99, 8));
            products.add(new Product("Altavoces", 79.99, 18));
            products.add(new Product("Micrófono", 39.99, 15));
            products.add(new Product("Disco Duro", 79.99, 10));
            products.add(new Product("Memoria RAM", 49.99, 22));
            products.add(new Product("Router", 39.99, 17));
            products.add(new Product("Tarjeta Gráfica", 129.99, 7));
            products.add(new Product("Batería Externa", 29.99, 25));
            products.add(new Product("Mochila para Laptop", 34.99, 20));
            products.add(new Product("Funda para Teléfono", 14.99, 30));
            products.add(new Product("Cable USB", 9.99, 40));
            products.add(new Product("Adaptador de Corriente", 19.99, 15));
        } catch (Exception e) {
            e.printStackTrace();
        }

        listView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Product> call(ListView<Product> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Product item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getName() + " - $" + item.getPrice() + " (" + item.getStock() + ")");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });

        listView.setItems(products);
    }
}
