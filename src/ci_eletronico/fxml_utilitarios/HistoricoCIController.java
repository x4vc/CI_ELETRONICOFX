/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.fxml_utilitarios;

import ci_eletronico.FXMLMainController;
import ci_eletronico.entities.TbCIPorAprovar;
import ci_eletronico.entities.TbCiDestinatario;
import ci_eletronico_queries.MainWindowQueries;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn ClCoinAssinatura;
    @FXML
    private TableColumn ClData;
    @FXML
    private TableColumn ClFrom;
    @FXML
    private TableColumn ClTo;
    @FXML
    private TableColumn ClTipoCoinDescricao;

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
            String strSequencialCI, int nBotao, int nTabela, String strAssinaturaCI){
        
        this.txtCodigoCi.setText(strSequencialCI);
        preencherTabelaHistorico(nIdCiEletronica,nBotao,nTabela, strAssinaturaCI);
        
    }
    
    public void preencherTabelaHistorico(int nIdCiEletronica, int nBotao, int nTabela, String strAssinaturaCI){
               
        MainWindowQueries consultaTbCiDestinatario;
        consultaTbCiDestinatario  = new MainWindowQueries(); 
        
        //Iniciamos a criação da TableView
        //TbCiDestinatario AssinaturaCiDestinatario = new TbCiDestinatario();
        List<TbCiDestinatario> listaCiDestinatario = new ArrayList<TbCiDestinatario>();
        ObservableList<TbCIPorAprovar> obslistaHistoricoCI = FXCollections.observableArrayList();
        
        //Variaveis para construção da TableView
        int nIdCoindestinatario = 0;
        int nIdCoin = 0;
        String strAssinatura = "";
        String strRemitente = "";
        String strDestinatario = "";
        String strTipoCoin = "";
        
        Date dataCriacao;
        String strDataCriacao = "";
        //Variaveis de apoio
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
        
        try {
            listaCiDestinatario = consultaTbCiDestinatario.getHistoricoTbCiDestinatario(strAssinaturaCI);
            for(TbCiDestinatario l : listaCiDestinatario){
                nIdCoindestinatario = l.getIdCoinDestinatario();
                nIdCoin = l.getIdCoin().getIdCoin();
                strAssinatura = l.getCoinAssinatura();
                
                dataCriacao = l.getCoinDestinatarioDataCriacao();
                strDataCriacao = df.format(dataCriacao);
                
                strRemitente = l.getInorDescricaoRemitente();
                strDestinatario = l.getUnorDescricaoDestinatario();
                strTipoCoin = l.getIdTipoCoin().getTiciDescricao();
                
                obslistaHistoricoCI.add(new TbCIPorAprovar(nIdCoindestinatario, nIdCoin, strAssinatura, dataCriacao, 
                        strDataCriacao, strRemitente, strDestinatario, strTipoCoin));
            }
            ClData.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_dataCriacao"));
            ClFrom.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUORemitente"));
            ClTo.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUODestinatario"));
            ClTipoCoinDescricao.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_TipoCoinDescricao"));
            tbViewHistoricoCi.setItems(obslistaHistoricoCI);
            
        } catch (Exception ex){
            
        }
        
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
        
        
    }
    
}
