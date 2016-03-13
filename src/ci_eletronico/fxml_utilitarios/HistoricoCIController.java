/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.fxml_utilitarios;

import ci_eletronico.FXMLMainController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class HistoricoCIController implements Initializable {
    @FXML
    private Button btnFechar;
    @FXML
    private TextField txtCodigoCi;
    @FXML
    private TableView tbViewHistoricoCi;
    @FXML
    private TableColumn ClIdCoin;
    @FXML
    private TableColumn ClData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtCodigoCi.setEditable(false);
        btnFechar.setCancelButton(true);
    } 
    
    public void handleBtnFechar(ActionEvent event) {
        //Ocultamos a janela 
        (((Node)event.getSource()).getScene()).getWindow().hide();
        //--------- FIM Ocultar janela ------------
    }
    
    public void setVariaveisAmbienteHistoricoCI(final FXMLMainController mainController, int nIdCiEletronica, 
            String strSequencialCI){
        
        this.txtCodigoCi.setText(strSequencialCI);
        
    }
    
}
