/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author 1152358 - Israel Bulla Rey
 *         1152384 - Jose Luis Jiménez Bayona
 */
public class MainIniciar extends Application{

    public static void main(String[] args) {
	Application.launch(MainIniciar.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("unidadesdatos.fxml"));

        stage.setScene(new Scene(loader.load()));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }
	
}