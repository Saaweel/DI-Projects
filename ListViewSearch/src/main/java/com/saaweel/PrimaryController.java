package com.saaweel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.util.ArrayList;

public class PrimaryController {
    public TextField search;

    public TextField priceLimit;

    public ListView<Product> listView;

    private ObservableList<Product> products_showing;

    private ArrayList<Product> products;

    public void initialize() {
        products_showing = FXCollections.observableArrayList();

        products = new ArrayList<>();

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
                            setText(null);
                            setGraphic(null);
                        }
                    }
                };
            }
        });

        listView.setItems(products_showing);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                updateList(newValue, Double.parseDouble(priceLimit.getText()));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Error en los filtros").show();
                e.printStackTrace();
            }
        });

        priceLimit.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                updateList(search.getText(), Double.parseDouble(newValue));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Error en los filtros").show();
                e.printStackTrace();
            }
        });

        updateList(null, null);
    }

    private void updateList(String filter1, Double filter2) {
        products_showing.clear();

        for (Product p : products)
            if ((filter1 == null || p.getName().contains(filter1) && (filter2 == 0.0 || p.getPrice() <= filter2)))
                products_showing.add(p);
    }
}
