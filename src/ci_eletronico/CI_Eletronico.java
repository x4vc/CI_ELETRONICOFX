/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author victorcmaf
 */
public class CI_Eletronico extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLMain.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        //root.setStyle("-fx-background-image: url('/resources/logo_prefeitura_salvador.png')");
        //root.setStyle("-fx-background-image: url('/resources/logo_prefeitura_salvador.png')");
        //root.setStyle("-fx-background-image: url('/resources/transalvador.png')");
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("resources/PersonalStyleSheet.css");
        stage.setTitle("CI-eletrônico - Página Login");
        stage.getIcons().add(new Image("/resources/CI_FX02.png"));
        stage.setScene(scene);
        
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
