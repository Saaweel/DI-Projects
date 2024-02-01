package org.example;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.fxml.FXML;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

public class PrimaryController {

    @FXML
    private void showReport() throws SQLException, JRException {
        InputStream reportFile = getClass().getResourceAsStream("/org/example/techmart.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFile);

        String url = "jdbc:mariadb://localhost:3306/techmart";
        String username = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(url, username, password);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);

        JasperViewer.viewReport(jasperPrint, false);

        JasperExportManager.exportReportToPdfFile(jasperPrint, "/org/example/techmart.jrxml");
    }
}
