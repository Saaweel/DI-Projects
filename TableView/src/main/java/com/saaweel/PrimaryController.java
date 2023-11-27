package com.saaweel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PrimaryController {
    public TextField search;

    public TableView<Product> tableView;

    private ObservableList<Product> productsShowing;

    private ArrayList<Product> products;

    public void initialize() {
        products = new ArrayList<>();

        productsShowing = FXCollections.observableArrayList();

        products.add(new Product("Laptop", 999.99, 20, 2.5, new Date(), "Gris", "Plástico", "ABC123"));
        products.add(new Product("Smartphone", 599.99, 50, 0.3, new Date(), "Negro", "Metal", "XYZ789"));
        products.add(new Product("Televisor", 799.99, 15, 12.0, new Date(), "Negro", "Plástico", "DEF456"));
        products.add(new Product("Zapatillas", 49.99, 100, 0.5, new Date(), "Blanco", "Tela", "123GHI"));
        products.add(new Product("Refrigerador", 799.99, 10, 50.0, new Date(), "Plateado", "Metal", "456JKL"));
        products.add(new Product("Cámara Digital", 299.99, 30, 0.2, new Date(), "Rojo", "Plástico", "MNO789"));
        products.add(new Product("Reloj", 199.99, 25, 0.1, new Date(), "Oro", "Metal", "PQR012"));
        products.add(new Product("Bicicleta", 349.99, 5, 12.0, new Date(), "Azul", "Metal", "STU345"));
        products.add(new Product("Libro", 19.99, 200, 0.5, new Date(), "N/A", "Papel", "VWX678"));
        products.add(new Product("Impresora", 149.99, 12, 10.0, new Date(), "Blanco", "Plástico", "YZA901"));
        products.add(new Product("Auriculares", 39.99, 30, 0.2, new Date(), "Negro", "Plástico", "BCD234"));
        products.add(new Product("Teclado inalámbrico", 29.99, 25, 0.5, new Date(), "Blanco", "Plástico", "EFG567"));
        products.add(new Product("Silla de oficina", 79.99, 15, 10.0, new Date(), "Negro", "Cuero", "HIJ890"));
        products.add(new Product("Cafetera", 49.99, 20, 3.0, new Date(), "Plateado", "Metal", "KLM123"));
        products.add(new Product("Horno de microondas", 69.99, 10, 15.0, new Date(), "Blanco", "Metal", "NOP456"));
        products.add(new Product("Pelota de fútbol", 19.99, 50, 0.7, new Date(), "Blanco y negro", "Piel sintética", "QRS789"));
        products.add(new Product("Gafas de sol", 59.99, 40, 0.1, new Date(), "Negro", "Plástico", "TUV012"));
        products.add(new Product("Mesa de comedor", 149.99, 8, 20.0, new Date(), "Marrón", "Madera", "WXY345"));
        products.add(new Product("Batería recargable", 29.99, 60, 0.3, new Date(), "Verde", "Metal", "YZB678"));
        products.add(new Product("Cepillo de dientes", 4.99, 100, 0.05, new Date(), "Azul", "Plástico", "CDE901"));

        updateList(null);

        setTableColumns();

        tableView.setItems(productsShowing);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                updateList(newValue);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Error en los filtros").show();
                e.printStackTrace();
            }
        });
    }

    private void updateList(String filter1) {
        productsShowing.clear();

        for (Product p : products)
            if (filter1 == null || p.getName().contains(filter1))
                productsShowing.add(p);
    }

    private void setTableColumns() {
        HashMap<String, String> columns = new HashMap<>();
        columns.put("Nombre", "name");
        columns.put("Precio", "price");
        columns.put("Stock", "stock");
        columns.put("Peso", "weight");
        columns.put("Fecha de vencimiento", "expire");
        columns.put("Color", "color");
        columns.put("Material", "material");
        columns.put("Modelo", "model");

        columns.forEach((k,v) -> {
            TableColumn<Product, String> col = new TableColumn<>(k);
            col.setCellValueFactory(new PropertyValueFactory<>(v));
            tableView.getColumns().add(col);
        });
    }
}
