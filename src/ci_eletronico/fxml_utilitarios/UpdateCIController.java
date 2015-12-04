/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.fxml_utilitarios;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.web.HTMLEditor;

/**
 * FXML Controller class
 *
 * @author victorcmaf
 */
public class UpdateCIController implements Initializable {
    @FXML
    private Button btnAtualizarCI;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lblSequecial;
    @FXML
    private HTMLEditor htmlConteudoParaAtualizar;
            

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnCancelar.setCancelButton(true);
        btnAtualizarCI.setTooltip(new Tooltip("Clique no botão para atualizar a CI")); 
    } 
    
    public void handleBtnCancelar(ActionEvent event) {
        //Ocultamos a janela de seleção UOs
            (((Node)event.getSource()).getScene()).getWindow().hide();
            //--------- FIM Ocultar janela de seleção UOs ------------
    }
    public void handleBtnAtualizarCI(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar atualização");
            alert.setHeaderText(null);
            alert.setContentText("Deseja realmente atualizar CI?"); 

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                //...do something
            }
            else {
                //...do nothing
            }
    }
    
}
