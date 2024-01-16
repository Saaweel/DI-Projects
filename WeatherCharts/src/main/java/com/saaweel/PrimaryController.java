package com.saaweel;

import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;

import java.text.SimpleDateFormat;
import java.util.List;

public class PrimaryController {
    public ComboBox<String> selector;
    public LineChart<String, Integer> chart1;
    public BarChart<String, Integer> chart2;
    public AreaChart<String, Integer> chart3;
    public ScatterChart<String, Integer> chart4;
    public void initialize() {
        List<WeatherData> data = WeatherData.getData();

        chart1.getXAxis().setLabel("Date");
        chart1.getYAxis().setLabel("Temperature (ºC)");
        chart2.getXAxis().setLabel("Month");
        chart2.getYAxis().setLabel("Precipitation (mm)");
        chart3.getXAxis().setLabel("Date");
        chart3.getYAxis().setLabel("Humidity (%)");
        chart4.getXAxis().setLabel("Date");
        chart4.getYAxis().setLabel("Wind Speed (km/h)");

        XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
        series1.setName("Temperature");
        XYChart.Series<String, Integer> series2 = new XYChart.Series<>();
        series2.setName("Precipitation");
        XYChart.Series<String, Integer> series3 = new XYChart.Series<>();
        series3.setName("Humidity");
        XYChart.Series<String, Integer> series4 = new XYChart.Series<>();
        series4.setName("Wind Speed");

        for (WeatherData weather: data) {
            series1.getData().add(new XYChart.Data<>(weather.getDate().toString(), (weather.getMaxDegrees() + weather.getMinDegrees()) / 2));
            series2.getData().add(new XYChart.Data<>(new SimpleDateFormat("MMM").format(weather.getDate()), weather.getPrecipitation()));
            series3.getData().add(new XYChart.Data<>(weather.getDate().toString(), weather.getHumidityPercentage()));
            series4.getData().add(new XYChart.Data<>(weather.getDate().toString(), weather.getWindSpeed()));
        }

        chart1.getData().add(series1);
        chart2.getData().add(series2);
        chart3.getData().add(series3);
        chart4.getData().add(series4);

        selector.getItems().addAll("All", "Line Chart", "Bar Chart", "Area Chart", "Scatter Chart");

        selector.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            chart1.setVisible(false);
            chart2.setVisible(false);
            chart3.setVisible(false);
            chart4.setVisible(false);

            switch (newValue) {
                case "All":
                    chart1.setVisible(true);
                    chart2.setVisible(true);
                    chart3.setVisible(true);
                    chart4.setVisible(true);
                    break;
                case "Line Chart":
                    chart1.setVisible(true);
                    break;
                case "Bar Chart":
                    chart2.setVisible(true);
                    break;
                case "Area Chart":
                    chart3.setVisible(true);
                    break;
                case "Scatter Chart":
                    chart4.setVisible(true);
                    break;
            }
        });
    }
}
