/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.fxml_utilitarios;

import ci_eletronico.entities.TbUnidadeOrganizacional;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    
    ArrayList<String> ArraySelecionado= new ArrayList();
    private String strUOSelecionado;
    
    private List<ci_eletronico.entities.TbUnidadeOrganizacional> listListaUO = new ArrayList<>();
    
    private ListView_UO_Query consulta;
    
    //private ObservableList<TbUnidadeOrganizacional> obsList_listaUO = FXCollections.observableArrayList();
    private ObservableList<String> obsList_listaUO = FXCollections.observableArrayList();
    
    private ObservableList<String> UOsAvailable = FXCollections.observableArrayList("ASSTI","DESENV","GTRAN","SEAPE","REDE");

    private ObservableList<String> UOSelecionado = FXCollections.observableArrayList(ArraySelecionado);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    }    
    @FXML
    private void handleBtnAdd(ActionEvent action){
        String selectedItem = boxUOs.getSelectionModel().getSelectedItem();
        obsList_listaUO.remove(selectedItem);
        UOSelecionado.add(selectedItem);
       
    }
    @FXML
    private void handleBtnRemove(ActionEvent action){
        String selectedItem = boxUOsPara.getSelectionModel().getSelectedItem();
        UOSelecionado.remove(selectedItem);
        obsList_listaUO.add(selectedItem);
    }
    
}

