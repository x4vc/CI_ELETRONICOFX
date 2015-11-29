/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.fxml_utilitarios;

import ci_eletronico.FXMLMainController;
import ci_eletronico_queries.MainWindowQueries;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.web.HTMLEditor;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class AssinaturaController implements Initializable {
    
    @FXML
    private HTMLEditor htmlEditorAssinatura;
    @FXML
    private Button btnAtualizarAssinatura;
    @FXML
    private Button btnCancelar;
    
    private MainWindowQueries consulta;
    
    private String strNovaAssinatura = "";
    private String strAssinatura = "";
    int ngIdUsuario = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnCancelar.setCancelButton(true);
        btnAtualizarAssinatura.setTooltip(new Tooltip("Clique no botão para atualizar sua assinatura")); 
    }   
    public void setVariaveisAmbienteTrocarAssinatura(final FXMLMainController mainController , int nlIdUsuario, String strAssinatura){
        this.strAssinatura = strAssinatura;
        ngIdUsuario = nlIdUsuario; 
        
        htmlEditorAssinatura.setHtmlText(this.strAssinatura);
        
    }
    @FXML
    private void handleBtnCancel(ActionEvent action){
        //Ocultamos a janela de trocar Assinatura
        (((Node)action.getSource()).getScene()).getWindow().hide();
        //--------- FIM Ocultar janela ------------
    }
    @FXML
    private void handleBtnAtualizarAssinatura(ActionEvent action){
        //String strNovaAssinatura = "";
        strNovaAssinatura = htmlEditorAssinatura.getHtmlText();
        atualizarAssinatura(ngIdUsuario, strNovaAssinatura);
                
    }
    private void atualizarAssinatura(int nlIdUsuario, String strNovaAssinatura){
        boolean bUpdate = false;
        try{
            consulta  = new MainWindowQueries();
            bUpdate = consulta.UpdateAssinatura(nlIdUsuario, strNovaAssinatura);
            if (bUpdate){//                        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informação");
                alert.setHeaderText(null);
                alert.setContentText("Sua assinatura foi atualizada com sucesso.");
                alert.showAndWait();                        
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Trocar assinatura");
                alert.setHeaderText("Assinatura não foi atualizada");
                alert.setContentText("Favor contatar Administrador do sistema");
                alert.showAndWait();
            }
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atualizar assinatura");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();                        
        }
        
    }
    public String getNovaAssinatura(){
        return strNovaAssinatura;
    }
    
}
