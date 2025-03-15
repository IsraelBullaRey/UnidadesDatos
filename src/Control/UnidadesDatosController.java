/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Modelo.RepresentacionDatos;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 *
 *  @author 1152358 - Israel Bulla Rey
 *          1152384 - Jose Luis Jiménez Bayona
 * 
/*
 * Sample Skeleton for 'unidadesdatos.fxml' Controller Class
 */

public class UnidadesDatosController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="cmdConBits"
    private Button cmdConBits; // Value injected by FXMLLoader
    
    @FXML // fx:id="cmdConBytesBinario"
    private Button cmdConBytesBinario; // Value injected by FXMLLoader

    @FXML // fx:id="cmdConBytesDecimal"
    private Button cmdConBytesDecimal; // Value injected by FXMLLoader

    @FXML // fx:id="cmdConBinarioBits"
    private Button cmdConBinarioBits; // Value injected by FXMLLoader

    @FXML // fx:id="cmdConBinarioByes"
    private Button cmdConBinarioByes; // Value injected by FXMLLoader

    @FXML // fx:id="cmdConBinarioDecimal"
    private Button cmdConBinarioDecimal; // Value injected by FXMLLoader

    @FXML // fx:id="cmdConBitsBinario"
    private Button cmdConBitsBinario; // Value injected by FXMLLoader

    @FXML // fx:id="cmdConBitsDecimal"
    private Button cmdConBitsDecimal; // Value injected by FXMLLoader

    @FXML // fx:id="cmdConBinario"
    private Button cmdConBinario; // Value injected by FXMLLoader

    @FXML // fx:id="cmdConDecimal"
    private Button cmdConDecimal; // Value injected by FXMLLoader

    @FXML // fx:id="cmdConDecimalBinario"
    private Button cmdConDecimalBinario; // Value injected by FXMLLoader

    @FXML // fx:id="cmdConDecimalBits"
    private Button cmdConDecimalBits; // Value injected by FXMLLoader

    @FXML // fx:id="cmdConDecimalBytes"
    private Button cmdConDecimalBytes; // Value injected by FXMLLoader
    
    RepresentacionDatos r = new RepresentacionDatos();

    private void alertError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("ERROR");
        alert.setHeaderText("Algo va mal...");
        alert.showAndWait();
    }
    
    private void alertInformation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle("MENSAJE");
        alert.setHeaderText("Correcto...");
        alert.showAndWait();
    }
    
    @FXML
    void conBinarioBits(ActionEvent event) {
        String [] choices1 = {"B","KiB","MiB","GiB","TiB","PiB","EiB","ZiB","YiB"};
        String [] choices2 = {"b","Kb","Mb","Gb","Tb","Pb","Eb","Zb","Yb"};
        
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Conversión sistema binario a sistema de bits");
        dialog.setHeaderText("Ingresa un número y selecciona su unidad para luego seleccionar la unidad a la que deseas convertir el valor");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Convertir", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));   

        TextField valor = new TextField();
        valor.setPromptText("Ingresa el valor numérico");
        ChoiceBox<String> actual = new ChoiceBox<>();
        actual.getItems().addAll(choices1);
        ChoiceBox<String> nuevo = new ChoiceBox<>();
        nuevo.getItems().addAll(choices2);

        grid.add(new Label("Valor numérico: "), 0, 0);
        grid.add(valor, 1, 0);   
        grid.add(new Label("Unidad actual: "), 0, 1);
        grid.add(actual, 1, 1);
        grid.add(new Label("Unidad nueva: "), 0, 2);
        grid.add(nuevo, 1, 2);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        valor.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> valor.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(valor.getText(), actual.getSelectionModel().getSelectedItem());
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        try{
            if (result.isPresent()) {
                double num = Double.parseDouble(valor.getText());
                int u1 = actual.getSelectionModel().getSelectedIndex();
                int u2 = nuevo.getSelectionModel().getSelectedIndex();
                double valorNuevo = r.conBinarioBits(num, u1, u2);
                alertInformation("El valor nuevo es: " + valorNuevo + " " + nuevo.getSelectionModel().getSelectedItem());
            }
        } catch (Exception e) {
            alertError(e.getMessage());
        }
    }

    @FXML
    void conBinarioBytes(ActionEvent event) {
        String [] choices1 = {"B","KiB","MiB","GiB","TiB","PiB","EiB","ZiB","YiB"};
        
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Conversión sistema binario a bytes");
        dialog.setHeaderText("Ingresa un número y selecciona su unidad para convertirlo a bytes");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Convertir", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));   

        TextField valor = new TextField();
        valor.setPromptText("Ingresa el valor numérico");
        ChoiceBox<String> actual = new ChoiceBox<>();
        actual.getItems().addAll(choices1);

        grid.add(new Label("Valor numérico: "), 0, 0);
        grid.add(valor, 1, 0);   
        grid.add(new Label("Unidad actual: "), 0, 1);
        grid.add(actual, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        valor.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> valor.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(valor.getText(), actual.getSelectionModel().getSelectedItem());
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        try{
            if (result.isPresent()) {
                double num = Double.parseDouble(valor.getText());
                int u1 = actual.getSelectionModel().getSelectedIndex();
                double valorNuevo = r.conDatoBinarioByte(num, u1);
                alertInformation("El valor nuevo es: " + valorNuevo + " B");
            }
        } catch (Exception e) {
            alertError(e.getMessage());
        }
    }

    @FXML
    void conBinarioDecimal(ActionEvent event) {
        String [] choices1 = {"B","KiB","MiB","GiB","TiB","PiB","EiB","ZiB","YiB"};
        String [] choices2 = {"B","KB","MB","GB","TB","PB","EB","ZB","YB"};
        
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Conversión sistema binario a sistema decimal");
        dialog.setHeaderText("Ingresa un número y selecciona su unidad para luego seleccionar la unidad a la que deseas convertir el valor");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Convertir", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));   

        TextField valor = new TextField();
        valor.setPromptText("Ingresa el valor numérico");
        ChoiceBox<String> actual = new ChoiceBox<>();
        actual.getItems().addAll(choices1);
        ChoiceBox<String> nuevo = new ChoiceBox<>();
        nuevo.getItems().addAll(choices2);

        grid.add(new Label("Valor numérico: "), 0, 0);
        grid.add(valor, 1, 0);   
        grid.add(new Label("Unidad actual: "), 0, 1);
        grid.add(actual, 1, 1);
        grid.add(new Label("Unidad nueva: "), 0, 2);
        grid.add(nuevo, 1, 2);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        valor.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> valor.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(valor.getText(), actual.getSelectionModel().getSelectedItem());
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        try{
            if (result.isPresent()) {
                double num = Double.parseDouble(valor.getText());
                int u1 = actual.getSelectionModel().getSelectedIndex();
                int u2 = nuevo.getSelectionModel().getSelectedIndex();
                double valorNuevo = r.conBinaDeci(num, u1, u2);
                alertInformation("El valor nuevo es: " + valorNuevo + " " + nuevo.getSelectionModel().getSelectedItem());
            }
        } catch (Exception e) {
            alertError(e.getMessage());
        }
    }

    @FXML
    void conBitsBinario(ActionEvent event) {
        String [] choices1 = {"b","Kb","Mb","Gb","Tb","Pb","Eb","Zb","Yb"};
        String [] choices2 = {"B","KiB","MiB","GiB","TiB","PiB","EiB","ZiB","YiB"};
        
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Conversión sistema de bits a sistema binario");
        dialog.setHeaderText("Ingresa un número y selecciona su unidad para luego seleccionar la unidad a la que deseas convertir el valor");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Convertir", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));   

        TextField valor = new TextField();
        valor.setPromptText("Ingresa el valor numérico");
        ChoiceBox<String> actual = new ChoiceBox<>();
        actual.getItems().addAll(choices1);
        ChoiceBox<String> nuevo = new ChoiceBox<>();
        nuevo.getItems().addAll(choices2);

        grid.add(new Label("Valor numérico: "), 0, 0);
        grid.add(valor, 1, 0);   
        grid.add(new Label("Unidad actual: "), 0, 1);
        grid.add(actual, 1, 1);
        grid.add(new Label("Unidad nueva: "), 0, 2);
        grid.add(nuevo, 1, 2);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        valor.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> valor.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(valor.getText(), actual.getSelectionModel().getSelectedItem());
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        try{
            if (result.isPresent()) {
                double num = Double.parseDouble(valor.getText());
                int u1 = actual.getSelectionModel().getSelectedIndex();
                int u2 = nuevo.getSelectionModel().getSelectedIndex();
                double valorNuevo = r.conBitsBinario(num, u1, u2);
                alertInformation("El valor nuevo es: " + valorNuevo + " " + nuevo.getSelectionModel().getSelectedItem());
            }
        } catch (Exception e) {
            alertError(e.getMessage());
        }
    }

    @FXML
    void conBitsDecimal(ActionEvent event) {
        String [] choices1 = {"b","Kb","Mb","Gb","Tb","Pb","Eb","Zb","Yb"};
        String [] choices2 = {"B","KB","MB","GB","TB","PB","EB","ZB","YB"};
        
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Conversión sistema de bits a sistema decimal");
        dialog.setHeaderText("Ingresa un número y selecciona su unidad para luego seleccionar la unidad a la que deseas convertir el valor");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Convertir", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));   

        TextField valor = new TextField();
        valor.setPromptText("Ingresa el valor numérico");
        ChoiceBox<String> actual = new ChoiceBox<>();
        actual.getItems().addAll(choices1);
        ChoiceBox<String> nuevo = new ChoiceBox<>();
        nuevo.getItems().addAll(choices2);

        grid.add(new Label("Valor numérico: "), 0, 0);
        grid.add(valor, 1, 0);   
        grid.add(new Label("Unidad actual: "), 0, 1);
        grid.add(actual, 1, 1);
        grid.add(new Label("Unidad nueva: "), 0, 2);
        grid.add(nuevo, 1, 2);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        valor.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> valor.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(valor.getText(), actual.getSelectionModel().getSelectedItem());
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        try{
            if (result.isPresent()) {
                double num = Double.parseDouble(valor.getText());
                int u1 = actual.getSelectionModel().getSelectedIndex();
                int u2 = nuevo.getSelectionModel().getSelectedIndex();
                double valorNuevo = r.conBitsDecimal(num, u1, u2);
                alertInformation("El valor nuevo es: " + valorNuevo + " " + nuevo.getSelectionModel().getSelectedItem());
            }
        } catch (Exception e) {
            alertError(e.getMessage());
        }
    }

    @FXML
    void conBinario(ActionEvent event) {
        String [] choices1 = {"B","KiB","MiB","GiB","TiB","PiB","EiB","ZiB","YiB"};
        String [] choices2 = {"B","KiB","MiB","GiB","TiB","PiB","EiB","ZiB","YiB"};
        
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Conversión dentro del sistema binario");
        dialog.setHeaderText("Ingresa un número y selecciona su unidad para luego seleccionar la unidad a la que deseas convertir el valor");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Convertir", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));   

        TextField valor = new TextField();
        valor.setPromptText("Ingresa el valor numérico");
        ChoiceBox<String> actual = new ChoiceBox<>();
        actual.getItems().addAll(choices1);
        ChoiceBox<String> nuevo = new ChoiceBox<>();
        nuevo.getItems().addAll(choices2);

        grid.add(new Label("Valor numérico: "), 0, 0);
        grid.add(valor, 1, 0);   
        grid.add(new Label("Unidad actual: "), 0, 1);
        grid.add(actual, 1, 1);
        grid.add(new Label("Unidad nueva: "), 0, 2);
        grid.add(nuevo, 1, 2);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        valor.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> valor.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(valor.getText(), actual.getSelectionModel().getSelectedItem());
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        try{
            if (result.isPresent()) {
                double num = Double.parseDouble(valor.getText());
                int u1 = actual.getSelectionModel().getSelectedIndex();
                int u2 = nuevo.getSelectionModel().getSelectedIndex();
                double valorNuevo = r.conversionBinaria(num, u1, u2);
                alertInformation("El valor nuevo es: " + valorNuevo + " " + nuevo.getSelectionModel().getSelectedItem());
            }
        } catch (Exception e) {
            alertError(e.getMessage());
        }
    }

    @FXML
    void conDecimal(ActionEvent event) {
        String [] choices1 = {"B","KB","MB","GB","TB","PB","EB","ZB","YB"};
        String [] choices2 = {"B","KB","MB","GB","TB","PB","EB","ZB","YB"};
        
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Conversión dentro del sistema decimal");
        dialog.setHeaderText("Ingresa un número y selecciona su unidad para luego seleccionar la unidad a la que deseas convertir el valor");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Convertir", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));   

        TextField valor = new TextField();
        valor.setPromptText("Ingresa el valor numérico");
        ChoiceBox<String> actual = new ChoiceBox<>();
        actual.getItems().addAll(choices1);
        ChoiceBox<String> nuevo = new ChoiceBox<>();
        nuevo.getItems().addAll(choices2);

        grid.add(new Label("Valor numérico: "), 0, 0);
        grid.add(valor, 1, 0);   
        grid.add(new Label("Unidad actual: "), 0, 1);
        grid.add(actual, 1, 1);
        grid.add(new Label("Unidad nueva: "), 0, 2);
        grid.add(nuevo, 1, 2);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        valor.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> valor.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(valor.getText(), actual.getSelectionModel().getSelectedItem());
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        try{
            if (result.isPresent()) {
                double num = Double.parseDouble(valor.getText());
                int u1 = actual.getSelectionModel().getSelectedIndex();
                int u2 = nuevo.getSelectionModel().getSelectedIndex();
                double valorNuevo = r.conversionDecimal(num, u1, u2);
                alertInformation("El valor nuevo es: " + valorNuevo + " " + nuevo.getSelectionModel().getSelectedItem());
            }
        } catch (Exception e) {
            alertError(e.getMessage());
        }
    }

    @FXML
    void conDecimalBinario(ActionEvent event) {
        String [] choices1 = {"B","KB","MB","GB","TB","PB","EB","ZB","YB"};
        String [] choices2 = {"B","KiB","MiB","GiB","TiB","PiB","EiB","ZiB","YiB"};
        
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Conversión sistema decimal a sistema de bits");
        dialog.setHeaderText("Ingresa un número y selecciona su unidad para luego seleccionar la unidad a la que deseas convertir el valor");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Convertir", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));   

        TextField valor = new TextField();
        valor.setPromptText("Ingresa el valor numérico");
        ChoiceBox<String> actual = new ChoiceBox<>();
        actual.getItems().addAll(choices1);
        ChoiceBox<String> nuevo = new ChoiceBox<>();
        nuevo.getItems().addAll(choices2);

        grid.add(new Label("Valor numérico: "), 0, 0);
        grid.add(valor, 1, 0);   
        grid.add(new Label("Unidad actual: "), 0, 1);
        grid.add(actual, 1, 1);
        grid.add(new Label("Unidad nueva: "), 0, 2);
        grid.add(nuevo, 1, 2);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        valor.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> valor.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(valor.getText(), actual.getSelectionModel().getSelectedItem());
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        try{
            if (result.isPresent()) {
                double num = Double.parseDouble(valor.getText());
                int u1 = actual.getSelectionModel().getSelectedIndex();
                int u2 = nuevo.getSelectionModel().getSelectedIndex();
                double valorNuevo = r.conDeciBina(num, u1, u2);
                alertInformation("El valor nuevo es: " + valorNuevo + " " + nuevo.getSelectionModel().getSelectedItem());
            }
        } catch (Exception e) {
            alertError(e.getMessage());
        }
    }

    @FXML
    void conDecimalBits(ActionEvent event) {
        String [] choices1 = {"B","KB","MB","GB","TB","PB","EB","ZB","YB"};
        String [] choices2 = {"b","Kb","Mb","Gb","Tb","Pb","Eb","Zb","Yb"};
        
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Conversión sistema decimal a sistema de bits");
        dialog.setHeaderText("Ingresa un número y selecciona su unidad para luego seleccionar la unidad a la que deseas convertir el valor");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Convertir", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));   

        TextField valor = new TextField();
        valor.setPromptText("Ingresa el valor numérico");
        ChoiceBox<String> actual = new ChoiceBox<>();
        actual.getItems().addAll(choices1);
        ChoiceBox<String> nuevo = new ChoiceBox<>();
        nuevo.getItems().addAll(choices2);

        grid.add(new Label("Valor numérico: "), 0, 0);
        grid.add(valor, 1, 0);   
        grid.add(new Label("Unidad actual: "), 0, 1);
        grid.add(actual, 1, 1);
        grid.add(new Label("Unidad nueva: "), 0, 2);
        grid.add(nuevo, 1, 2);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        valor.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> valor.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(valor.getText(), actual.getSelectionModel().getSelectedItem());
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        try{
            if (result.isPresent()) {
                double num = Double.parseDouble(valor.getText());
                int u1 = actual.getSelectionModel().getSelectedIndex();
                int u2 = nuevo.getSelectionModel().getSelectedIndex();
                double valorNuevo = r.conDecimalBits(num, u1, u2);
                alertInformation("El valor nuevo es: " + valorNuevo + " " + nuevo.getSelectionModel().getSelectedItem());
            }
        } catch (Exception e) {
            alertError(e.getMessage());
        }
    }

    @FXML
    void conDecimalBytes(ActionEvent event) {
        String [] choices1 = {"B","KB","MB","GB","TB","PB","EB","ZB","YB"};
        
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Conversión sistema decimal a bytes");
        dialog.setHeaderText("Ingresa un número y selecciona su unidad para convertirlo a bytes");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Convertir", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));   

        TextField valor = new TextField();
        valor.setPromptText("Ingresa el valor numérico");
        ChoiceBox<String> actual = new ChoiceBox<>();
        actual.getItems().addAll(choices1);

        grid.add(new Label("Valor numérico: "), 0, 0);
        grid.add(valor, 1, 0);   
        grid.add(new Label("Unidad actual: "), 0, 1);
        grid.add(actual, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        valor.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> valor.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(valor.getText(), actual.getSelectionModel().getSelectedItem());
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        try{
            if (result.isPresent()) {
                double num = Double.parseDouble(valor.getText());
                int u1 = actual.getSelectionModel().getSelectedIndex();
                double valorNuevo = r.conDatoDecimalByte(num, u1);
                alertInformation("El valor nuevo es: " + valorNuevo + " B");
            }
        } catch (Exception e) {
            alertError(e.getMessage());
        }
    }
    
    @FXML
    void conBits(ActionEvent event) {
        String [] choices1 = {"b","Kb","Mb","Gb","Tb","Pb","Eb","Zb","Yb"};
        String [] choices2 = {"b","Kb","Mb","Gb","Tb","Pb","Eb","Zb","Yb"};
        
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Conversión dentro del sistema de bits");
        dialog.setHeaderText("Ingresa un número y selecciona su unidad para luego seleccionar la unidad a la que deseas convertir el valor");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Convertir", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));   

        TextField valor = new TextField();
        valor.setPromptText("Ingresa el valor numérico");
        ChoiceBox<String> actual = new ChoiceBox<>();
        actual.getItems().addAll(choices1);
        ChoiceBox<String> nuevo = new ChoiceBox<>();
        nuevo.getItems().addAll(choices2);

        grid.add(new Label("Valor numérico: "), 0, 0);
        grid.add(valor, 1, 0);   
        grid.add(new Label("Unidad actual: "), 0, 1);
        grid.add(actual, 1, 1);
        grid.add(new Label("Unidad nueva: "), 0, 2);
        grid.add(nuevo, 1, 2);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        valor.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> valor.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(valor.getText(), actual.getSelectionModel().getSelectedItem());
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        try{
            if (result.isPresent()) {
                double num = Double.parseDouble(valor.getText());
                int u1 = actual.getSelectionModel().getSelectedIndex();
                int u2 = nuevo.getSelectionModel().getSelectedIndex();
                double valorNuevo = r.conBits(num, u1, u2);
                alertInformation("El valor nuevo es: " + valorNuevo + " " + nuevo.getSelectionModel().getSelectedItem());
            }
        } catch (Exception e) {
            alertError(e.getMessage());
        }
    }
    
    @FXML
    void conBytesBinario(ActionEvent event) {
        String [] choices1 = {"B","KiB","MiB","GiB","TiB","PiB","EiB","ZiB","YiB"};
        
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Conversión de bytes a sistema binario");
        dialog.setHeaderText("Ingresa un número en bytes y selecciona la unidad para convertirlo");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Convertir", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));   

        TextField valor = new TextField();
        valor.setPromptText("Ingresa el valor numérico");
        ChoiceBox<String> actual = new ChoiceBox<>();
        actual.getItems().addAll(choices1);

        grid.add(new Label("Valor numérico: "), 0, 0);
        grid.add(valor, 1, 0);   
        grid.add(new Label("Unidad nueva: "), 0, 1);
        grid.add(actual, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        valor.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> valor.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(valor.getText(), actual.getSelectionModel().getSelectedItem());
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        try{
            if (result.isPresent()) {
                double num = Double.parseDouble(valor.getText());
                int u1 = actual.getSelectionModel().getSelectedIndex();
                double valorNuevo = r.conByteBinario(num, u1);
                alertInformation("El valor nuevo es: " + valorNuevo + " " + actual.getSelectionModel().getSelectedItem());
            }
        } catch (Exception e) {
            alertError(e.getMessage());
        }
    }

    @FXML
    void conBytesDecimal(ActionEvent event) {
        String [] choices1 = {"B","KB","MB","GB","TB","PB","EB","ZB","YB"};
        
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Conversión de bytes a sistema decimal");
        dialog.setHeaderText("Ingresa un número en bytes y selecciona la unidad para convertirlo");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Convertir", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));   

        TextField valor = new TextField();
        valor.setPromptText("Ingresa el valor numérico");
        ChoiceBox<String> actual = new ChoiceBox<>();
        actual.getItems().addAll(choices1);

        grid.add(new Label("Valor numérico: "), 0, 0);
        grid.add(valor, 1, 0);   
        grid.add(new Label("Unidad nueva: "), 0, 1);
        grid.add(actual, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        valor.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> valor.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(valor.getText(), actual.getSelectionModel().getSelectedItem());
                }
                return null;
            });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        try{
            if (result.isPresent()) {
                double num = Double.parseDouble(valor.getText());
                int u1 = actual.getSelectionModel().getSelectedIndex();
                double valorNuevo = r.conByteDecimal(num, u1);
                alertInformation("El valor nuevo es: " + valorNuevo + " " + actual.getSelectionModel().getSelectedItem());
            }
        } catch (Exception e) {
            alertError(e.getMessage());
        }
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmdConBinario != null : "fx:id=\"cmdConBinario\" was not injected: check your FXML file 'unidadesdatos.fxml'.";
        assert cmdConBinarioBits != null : "fx:id=\"cmdConBinarioBits\" was not injected: check your FXML file 'unidadesdatos.fxml'.";
        assert cmdConBinarioByes != null : "fx:id=\"cmdConBinarioByes\" was not injected: check your FXML file 'unidadesdatos.fxml'.";
        assert cmdConBinarioDecimal != null : "fx:id=\"cmdConBinarioDecimal\" was not injected: check your FXML file 'unidadesdatos.fxml'.";
        assert cmdConBits != null : "fx:id=\"cmdConBits\" was not injected: check your FXML file 'unidadesdatos.fxml'.";
        assert cmdConBitsBinario != null : "fx:id=\"cmdConBitsBinario\" was not injected: check your FXML file 'unidadesdatos.fxml'.";
        assert cmdConBitsDecimal != null : "fx:id=\"cmdConBitsDecimal\" was not injected: check your FXML file 'unidadesdatos.fxml'.";
        assert cmdConBytesBinario != null : "fx:id=\"cmdConBytesBinario\" was not injected: check your FXML file 'unidadesdatos.fxml'.";
        assert cmdConBytesDecimal != null : "fx:id=\"cmdConBytesDecimal\" was not injected: check your FXML file 'unidadesdatos.fxml'.";
        assert cmdConDecimal != null : "fx:id=\"cmdConDecimal\" was not injected: check your FXML file 'unidadesdatos.fxml'.";
        assert cmdConDecimalBinario != null : "fx:id=\"cmdConDecimalBinario\" was not injected: check your FXML file 'unidadesdatos.fxml'.";
        assert cmdConDecimalBits != null : "fx:id=\"cmdConDecimalBits\" was not injected: check your FXML file 'unidadesdatos.fxml'.";
        assert cmdConDecimalBytes != null : "fx:id=\"cmdConDecimalBytes\" was not injected: check your FXML file 'unidadesdatos.fxml'.";

    }

}

