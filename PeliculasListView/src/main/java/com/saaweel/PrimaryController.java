package com.saaweel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class PrimaryController {
    @FXML
    public ListView<Film> listView;

    public void initialize() {
        ObservableList<Film> films = FXCollections.observableArrayList();

        films.add(new Film("Forrest Gump", "Robert Zemeckis", 1994, ""));
        films.add(new Film("Pulp Fiction", "Quentin Tarantino", 1994, ""));
        films.add(new Film("El Señor de los Anillos: La Comunidad del Anillo", "Peter Jackson", 2001, "https://play-lh.googleusercontent.com/imeAs3_Nb9fyoj56LgLzSRBs3UXTZTH_TLg2xMkg6J90ZPzxscAXPvtsR9Q9azxe-WCy5A"));
        films.add(new Film("Matrix", "Lana Wachowski, Lilly Wachowski", 1999, ""));
        films.add(new Film("Star Wars: Episodio IV - Una Nueva Esperanza", "George Lucas", 1977, "https://ladarsenacm.com/wp-content/uploads/2018/08/10agosto-cine-star-wars-4-manzanares-el-real.png"));
        films.add(new Film("Jurassic Park", "Steven Spielberg", 1993, ""));
        films.add(new Film("Gladiador", "Ridley Scott", 2000, "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/p24674_p_v8_ae.jpg"));


        listView.setCellFactory(param -> new FilmFXMLListCell());

        listView.setItems(films);
    }
}
