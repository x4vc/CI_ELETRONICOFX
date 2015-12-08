/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.fxml_utilitarios;

import ci_eletronico.FXMLMainController;
import ci_eletronico.entities.TbCiDestinatario;
import ci_eletronico.entities.TbComunicacaoInterna;
import ci_eletronico_queries.MainWindowQueries;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
    
    private int ngIdCoin = 0;
            

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnCancelar.setCancelButton(true);
        btnAtualizarCI.setTooltip(new Tooltip("Clique no botão para atualizar a CI")); 
    } 
    public void setVariaveisAmbienteUpdateCI(final FXMLMainController mainController, int nlIdCI, String strHtmlUpdateCI){
        this.htmlConteudoParaAtualizar.setHtmlText(strHtmlUpdateCI);
        this.ngIdCoin = nlIdCI;
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
                atualizarCI(ngIdCoin, this.htmlConteudoParaAtualizar.getHtmlText());
                //Ocultamos a janela de Atualizar CI
                (((Node)event.getSource()).getScene()).getWindow().hide();
                //--------- FIM Ocultar janela ------------
            }
            else {
                //...do nothing
            }
    }
    
    private void atualizarCI(int nlIdCoin, String strCIAtualizada){
        boolean bUpdateTbComunicacaoInterna = false;
        boolean bUpdateTbCiDestinatario = false;
        EntityManager em;
        EntityManagerFactory emf;
        TbComunicacaoInterna nTbComunicacaoInternaIdCoin= new TbComunicacaoInterna(nlIdCoin);
        try{
            MainWindowQueries consulta;
            consulta  = new MainWindowQueries();
            bUpdateTbComunicacaoInterna = consulta.UpdateCITbComunicacaoInterna(nlIdCoin, strCIAtualizada);
            if (bUpdateTbComunicacaoInterna){//Atualização na CI Original foi com sucesso                
                try{
                    consulta  = new MainWindowQueries();
                    emf = Persistence.createEntityManagerFactory("CI_EletronicoPU");
                    em = emf.createEntityManager();        
                    em.getTransaction().begin();
                    //Atualizamos registros na tabela TB_CI_DESTINATARIO
                    //Iniciamos a criação da TableView
                    List<TbCiDestinatario> listaCiDestinatario = new ArrayList<TbCiDestinatario>();
                    listaCiDestinatario = consulta.getlistaCiParaAprovar(nTbComunicacaoInternaIdCoin);
                    for(TbCiDestinatario l : listaCiDestinatario){
                        l.setCoinDestinatarioConteudo(strCIAtualizada);                        
                        em.merge(l);
                    }
                    em.getTransaction().commit();            
                    em.close();
                    emf.close();
                    
                }catch(javax.persistence.PersistenceException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Atualizar CI ");
                    alert.setHeaderText("Tabela TB_CI_DESTINATARIO");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();                                            
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informação");
                alert.setHeaderText(null);
                alert.setContentText("CI foi atualizada com sucesso.");
                alert.showAndWait();   
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Atualizar CI");
                alert.setHeaderText("CI não foi atualizada");
                alert.setContentText("Erro: consulta.UpdateCITbComunicacaoInterna");
                alert.showAndWait();
            }
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atualizar CI");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();                        
        }
        
    }
    
}
