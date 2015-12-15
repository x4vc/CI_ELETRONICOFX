/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.fxml_utilitarios;

import ci_eletronico_queries.ListView_UO_Query;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

/**
 * FXML Controller class
 *
 * @author Victor
 */
public class Win_Para_ComcopiaController implements Initializable {
    @FXML
    private TextField txtPesquisarUO;
    @FXML
    private Button btnPesquisarUO;
    @FXML
    //private ListView<TbUnidadeOrganizacional> boxUOs; //boxPlayers
    private ListView<String> boxUOs; //boxPlayers
    @FXML
    private ListView<String> boxUOsPara; // boxTeam
    @FXML
    private Button btnAddAll;    
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnRemoveAll;
    @FXML
    private Button btnOK;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lblPesquisar;
    
    ArrayList<String> ArraySelecionado= new ArrayList();
    private String strUOSelecionado;
    
    private List<ci_eletronico.entities.TbUnidadeOrganizacional> listListaUO = new ArrayList<>();
    
    private ListView_UO_Query consulta;
    
    //private ObservableList<TbUnidadeOrganizacional> obsList_listaUO = FXCollections.observableArrayList();
    private ObservableList<String> obsList_listaUO = FXCollections.observableArrayList();
    
    //private ObservableList<String> UOsAvailable = FXCollections.observableArrayList("ASSTI","DESENV","GTRAN","SEAPE","REDE");

    private ObservableList<String> UOSelecionado = FXCollections.observableArrayList(ArraySelecionado);
    private ObservableList<String> selectedItens;
//    private int nTipoDestinatario = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //Na primeira versão não estará visivel a caixa de Buscar UO
        this.btnPesquisarUO.setVisible(false);
        this.txtPesquisarUO.setVisible(false);
        this.lblPesquisar.setVisible(false);        
        //-------------------------------------------------------------
        
        
        consulta  = new ListView_UO_Query();   
        listListaUO = consulta.listaUO();
        
       // obsList_listaUO = FXCollections.observableArrayList();
        for(ci_eletronico.entities.TbUnidadeOrganizacional l : listListaUO){
            Integer IdUO = l.getIdUnidadeOrganizacional();
            String strIdUO = IdUO.toString();
            String strNomeUO = l.getUnorNome();
            String strDescricaoUO = l.getUnorDescricao();
            boolean bAtivo = l.getUnorAtivo();
            //obsList_listaUO.add(new TbUnidadeOrganizacional(IdUO,strNomeUO,bAtivo));
            obsList_listaUO.add(strIdUO + "-" + strNomeUO + " - " + strDescricaoUO);
            }
        boxUOs.setItems(obsList_listaUO);
        //playersAvailable = FXCollections.observableArrayList("Kardi","Gilmore","Clark");
        //teamOutput = FXCollections.observableArrayList(team);

        //boxUOs.setItems(UOsAvailable);
        boxUOsPara.setItems(UOSelecionado);
        btnCancelar.setCancelButton(true);
        btnAddAll.setTooltip(new Tooltip("Adicionar todas as UOs na caixa \"Selecionado(s)\""));     
        btnAdd.setTooltip(new Tooltip("Adicionar UO na caixa \"Selecionado(s)\""));     
        btnRemove.setTooltip(new Tooltip("Tirar UO da caixa \"Selecionado(s)\""));
        btnRemoveAll.setTooltip(new Tooltip("Tirar todas as UOs da caixa \"Selecionado(s)\""));
       
    }
    @FXML
    private void handleBtnAddAll(ActionEvent action){
        String strUOSelected = "";
        int nContador, nSize;
        nContador = 0; nSize = 0;        
        
        //ObservableList<String> selectedItens = boxUOs.getItems();
        selectedItens = boxUOs.getItems();
        nSize = selectedItens.size();
        
//        for (String strUO: selectedItens){
//            UOSelecionado.add(strUO);            
//        }
        for (nContador = 0;nContador<nSize;nContador++){
            strUOSelected = selectedItens.get(nContador);
            UOSelecionado.add(nContador, strUOSelected);            
        }
        obsList_listaUO.clear();
    }   
    @FXML
    private void handleBtnAdd(ActionEvent action){
        String selectedItem = boxUOs.getSelectionModel().getSelectedItem();
        if (null != selectedItem){
            obsList_listaUO.remove(selectedItem);
            UOSelecionado.add(selectedItem);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro - UO não foi selecionado");
            alert.setHeaderText("Nenhuma UO foi selecionada");
            alert.setContentText("Por favor, selecione UO");
            alert.showAndWait();
            
        }
       
    }
    @FXML
    private void handleBtnRemoveAll(ActionEvent action){
        String strUOSelected = "";
        int nContador, nSize;
        nContador = 0; nSize = 0;        
        
        //ObservableList<String> selectedItens = boxUOsPara.getItems();
        selectedItens = boxUOsPara.getItems();
        nSize = selectedItens.size();
        
//        for (String strUO: selectedItens){
//            UOSelecionado.add(strUO);            
//        }
        for (nContador = 0;nContador<nSize;nContador++){
            strUOSelected = selectedItens.get(nContador);
            obsList_listaUO.add(nContador, strUOSelected);            
        }
        UOSelecionado.clear();
        
    }
    @FXML
    private void handleBtnRemove(ActionEvent action){
        String selectedItem = boxUOsPara.getSelectionModel().getSelectedItem();
        if(null != selectedItem){
            UOSelecionado.remove(selectedItem);
            obsList_listaUO.add(selectedItem);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro - UO não foi selecionado");
            alert.setHeaderText("Nenhuma UO foi selecionada");
            alert.setContentText("Por favor, selecione UO");
            alert.showAndWait();
        }
    }
    @FXML
    private void handleBtnCancel(ActionEvent action){
        //Ocultamos a janela de seleção UOs
        (((Node)action.getSource()).getScene()).getWindow().hide();
        //--------- FIM Ocultar janela de seleção UOs ------------
    }
    @FXML
    private void handleBtnOK(ActionEvent event){
        selectedItens = boxUOsPara.getItems();
        //Ocultamos a janela de login
        (((Node)event.getSource()).getScene()).getWindow().hide();
        //--------- FIM Ocultar janela de seleção UOs ------------
    }
    public ObservableList<String> getUOsPara(){
        return selectedItens;
    }
//    public void setTipoDestinatario(int nTipoDestinatario){
//        // 1 - Para
//        // 2 - Com copia
//        this.nTipoDestinatario = nTipoDestinatario;
//    }
}

