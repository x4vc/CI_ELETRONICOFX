/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.fxml_utilitarios;

import ci_eletronico.FXMLMainController;
import ci_eletronico.entities.TbCIPorAprovar;
import ci_eletronico.entities.TbCiDestinatario;
import ci_eletronico.entities.TbComunicacaoInterna;
import ci_eletronico_queries.MainWindowQueries;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
            String strSequencialCI, int nBotao, int nTabela){
        
        this.txtCodigoCi.setText(strSequencialCI);
        preencherTabelaHistorico(nIdCiEletronica,nBotao,nTabela);
        
    }
    
    public void preencherTabelaHistorico(int nIdCiEletronica, int nBotao, int nTabela){
        MainWindowQueries consultaTbComunicacaoInterna;
        consultaTbComunicacaoInterna  = new MainWindowQueries();
        
        MainWindowQueries consultaTbCiDestinatario;
        consultaTbCiDestinatario  = new MainWindowQueries(); 
        
        //Iniciamos a criação da TableView
        List<TbComunicacaoInterna> listaComunicacaoInterna = new ArrayList<TbComunicacaoInterna>();
        List<TbCiDestinatario> listaCiDestinatario = new ArrayList<TbCiDestinatario>();
        ObservableList<TbCIPorAprovar> obslistaTbCaixaEntrada = FXCollections.observableArrayList();
        
        //Valores nTabela:
        // 1 - TB_COMUNICACAO_INTERNA
        // 2 - TB_CI_DESTINATARIO
        //--------------------------------------------------------
        //Valores dos botões:
        //1-caixa de recebidas (solicitando aprovação) - btnCaixaEntradaPendentesAprovacao
        //2-caixa de recebidas - btnCaixaEntrada
        //3-caixa de recebidas (pendencias) - btnCaixaPendencias
        //4-caixa de recebidas (arquivadas) - btnCaixaArquivadas
        //5-Caixa de enviados (arquivadas) - btnCaixaEnviadosArquivados
        //6-Caixa de enviados - btnCaixaSaida;
        //7-Caixa de enviados (solicitando aprovação) - btnPendentesAprovacao
        switch (nBotao){
            case 1: case 2: case 3: case 4: //TB_CI_DESTINATARIO
                
                break;
                
            case 5: case 6: case 7:         //TB_COMUNICACAO_INTERNA
                break;
                
            default:
                break;
        }
        
    }
    
}
