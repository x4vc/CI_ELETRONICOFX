/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico;

import ci_eletronico.entities.TbUnidadeOrganizacional;
import ci_eletronico.entities.TbUnidadeOrganizacionalGestor;
import ci_eletronico.entities.TbUsuario;
import ci_eletronico.utilitarios.Seguranca;
import ci_eletronico_queries.LoginQuery;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author victorcmaf
 */
public class LoginController implements Initializable {
//public class LoginController {
    @FXML
    private Button btnAcessar;
    @FXML
    private Button btnOK;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField pwdSenha;
    @FXML
    private ComboBox cmbUO;   
    @FXML
    private Label lblUO;
    
    private List<ci_eletronico.entities.TbUsuario> listaUsuarios = new ArrayList<>();
    private List<ci_eletronico.entities.TbUsuarioPerfilUo> listaUO = new ArrayList<>();
    private List<ci_eletronico.entities.TbUnidadeOrganizacionalGestor> listaUOGestor = new ArrayList<>();
    private List<Object[]> listaJoin; 
    private LoginQuery consulta_TB_USUARIO  = new LoginQuery();  
    private LoginQuery consulta_TB_USUARIO_PERFIL_UO  = new LoginQuery();  
    private LoginQuery consulta_TB_UO_GESTOR  = new LoginQuery();  
    
    ObservableList<String> ol_listUO = FXCollections.observableArrayList();
    
    private Scene scene;
    
    private String strIdUsuario = "";
    private String strUsername = "";
    private String strIdUO = "";
    private String strNomeUO = "";
    private String strIdUsuarioPerfil = "";
    private String strDescricaoPerfil = "";
    private String strHtmlAssinatura = "";
    private int nIdUOGestor = 0;
    //private LoginQuery consulta  = new LoginQuery();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //public void initialize() {
        // TODO
        //listaUsuarios = consulta.listaTbUsuario();
        ComponentesVisible(false);         
        
        btnAcessar.setDefaultButton(true);
    } 
    
    @FXML
    private void handleBtnAcessarAction(ActionEvent event) throws IOException, Exception {
        
        
        //FXMLLoader loader = new FXMLLoader();
        
        //FXMLMainController mainController = 
        
        String strUserLogin ="";
        strUserLogin = txtUsername.getText();
        listaUsuarios = consulta_TB_USUARIO.listaUserLogin(strUserLogin);
        
        if (listaUsuarios.size()>0){
            for(ci_eletronico.entities.TbUsuario l : listaUsuarios){
                if(txtUsername.getText().equals(l.getUsuLogin())){

                    String strPassword = pwdSenha.getText();
                    String strEnc = Seguranca.encriptar(strPassword);
                    //String strDec = Seguranca.desencriptar(strEnc);

                    //if(pwdSenha.getText().equals(l.getUsuSenha())){
                    if(strEnc.equals(l.getUsuSenha())){
                        ComponentesVisible(true);
                        ComponentesDisable(true);
                        btnAcessar.setDefaultButton(false);
                        btnOK.setDefaultButton(true);
                        //Verificamos qual UO faz parte
                        TbUsuario nIdUsuario = new TbUsuario(l.getIdUsuario()); //= 0;
                                                
                        //Getting assinatura do Usuario
                        strHtmlAssinatura = l.getUsuAssinatura();
                        if (null == strHtmlAssinatura){
                            strHtmlAssinatura = "";
                        }
                        //-------------------------------------
//                        String strIdUsuario = "";
//                        String strUsername = "";
//                        String strIdUO = "";
//                        String strNomeUO = "";
//                        String strIdUsuarioPerfil = "";
//                        String strDescricaoPerfil = "";
                        
                        String strUO = "";
                        //nIdUsuario.setIdUsuario(l.getIdUsuario());
                        strIdUsuario = l.getIdUsuario().toString();
                        strUsername = l.getUsuNomeCompleto();
                        
                        
                        listaUO = consulta_TB_USUARIO_PERFIL_UO.listaUO(nIdUsuario);   
                        //listaUO = consulta_TB_USUARIO_PERFIL_UO.listaJoinUO2(nIdUsuario);  
                         for(ci_eletronico.entities.TbUsuarioPerfilUo lUO : listaUO){
                             System.out.println("TbUsuarioPerfilUo campo 1 - " + lUO.getIdUnidadeOrganizacional().getIdUnidadeOrganizacional());
                             System.out.println("TbUsuarioPerfilUo campo 2 - " + lUO.getIdUnidadeOrganizacional().getUnorNome());
                             System.out.println("TbUsuarioPerfilUo campo 3 - " + lUO.getIdUsuarioPerfil().getIdUsuarioPerfil());
                             System.out.println("TbUsuarioPerfilUo campo 1 - " + lUO.getIdUsuarioPerfil().getPeusDescricao());
                             cmbUO.getItems().add(lUO.getIdUnidadeOrganizacional().getIdUnidadeOrganizacional() + " - " + lUO.getIdUnidadeOrganizacional().getUnorNome() + " ; " + lUO.getIdUsuarioPerfil().getIdUsuarioPerfil()+" - "+lUO.getIdUsuarioPerfil().getPeusDescricao());
                         }
                        //listaJoin = consulta_TB_USUARIO_PERFIL_UO.listaJoinUO(nIdUsuario);
                                                
//                        for(Object[] lista: listaJoin){
//                                System.out.println("campo 1 - " + lista[0]);
//                                System.out.println("campo 2 - " + lista[1]);
//                                System.out.println("campo 3 - " + lista[2]);
//                                System.out.println("campo 4 - " + lista[3]);
//                                
//                                //cmbUO.getItems().add(lista[1]+ " - "+ lista[3]);
//                                
//                            }
                        
                        cmbUO.getSelectionModel().selectFirst(); 
                        //TbUnidadeOrganizacional nIdUO;
                        if (listaUO.size()>0 && listaUO.size() < 2) {
                        //if (listaUO.size()>0 && listaUO.size() < 2){
                            strUO = cmbUO.getSelectionModel().getSelectedItem().toString();
                            System.out.println("Valor do combobox selecionado: " + strUO);
                            TbUnidadeOrganizacional nIdUO;
                            
                            for(ci_eletronico.entities.TbUsuarioPerfilUo lUO : listaUO){   
                                nIdUO = new TbUnidadeOrganizacional(lUO.getIdUnidadeOrganizacional().getIdUnidadeOrganizacional());
                                strIdUO = lUO.getIdUnidadeOrganizacional().getIdUnidadeOrganizacional().toString();
                                strNomeUO = lUO.getIdUnidadeOrganizacional().getUnorNome();
                                strIdUsuarioPerfil = lUO.getIdUsuarioPerfil().getIdUsuarioPerfil().toString();
                                strDescricaoPerfil = lUO.getIdUsuarioPerfil().getPeusDescricao();                             
                                listaUOGestor = consulta_TB_UO_GESTOR.getIdUOGestor(nIdUO);
                            } 
                            for (ci_eletronico.entities.TbUnidadeOrganizacionalGestor lUOGestor: listaUOGestor){
                                nIdUOGestor = lUOGestor.getIdUoGestor();
                            }
                            
                            
                            //loader.setLocation(FXMLMainController.class.getResource("/ci_eletronico/FXMLMain.fxml"));
                                                      
                            //Ocultamos a janela de login
                            (((Node)event.getSource()).getScene()).getWindow().hide();
                            //--------- FIM Ocultar janela de Login ------------
                            
//                            FXMLLoader loader = new FXMLLoader();
//                            loader.setLocation(FXMLMainController.class.getResource("/ci_eletronico/FXMLMain.fxml"));
//                            
//                            FXMLMainController controller = loader.getController();
//                            
//                            controller.setStrIdUsuario(l.getIdUsuario().toString());
//                            controller.setStrNomeUsuario(strUsername);
                            
                            //Mostramos MainWindow com dados do usuário logado
                            ShowMainWindowCIe(this, strIdUsuario, strUsername, strIdUO, strNomeUO, strIdUsuarioPerfil, strDescricaoPerfil, strHtmlAssinatura);
//                            try{
//                                scene = new Scene(new BorderPane());
//                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ci_eletronico/FXMLMain.fxml"));
//                                scene.setRoot((Parent) loader.load());
//                                FXMLMainController controller = loader.<FXMLMainController>getController();
//                                controller.setVariaveisAmbiente(this,strIdUsuario,strUsername,strIdUO,strNomeUO,strIdUsuarioPerfil,strDescricaoPerfil);
//                                
//                                Stage stage = new Stage();
//                                stage.setTitle("CI-eletrônico");
//                                //set icon
//                                stage.getIcons().add(new Image("/resources/CI_FX02.png"));
//
//                                stage.setScene(scene);
//                                stage.show();
////                                
//                            }catch (IOException ex) {
//                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//                            }


                            //Mostramos uma nova janela chamada MainWindow
                           /* 
                            Parent parent;
                            parent = FXMLLoader.load(getClass().getResource("/ci_eletronico/FXMLMain.fxml"));                           
                            
                            //Scene scene = new Scene(parent);
                            scene = new Scene(parent);
                          
                            Stage stage = new Stage();
                            stage.setTitle("CI-eletrônico");
                            //set icon
                            stage.getIcons().add(new Image("/resources/CI_FX02.png"));

                            stage.setScene(scene);
                            stage.show();
                                   */
                        } else {
                            // Show the error message.
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Informação de UOs");
                            alert.setHeaderText("Usuário possui mais de uma UO");
                            alert.setContentText("Por favor selecione só uma UO do combobox");
                            alert.showAndWait();
                                                                                   
                            String str;
                            int n = 0;
                            
                            for(ci_eletronico.entities.TbUsuarioPerfilUo lUO : listaUO){
//                               ol_listUO.add(lUO.getIdUsuario()+ " - " + lUO.getIdUnidadeOrganizacional() + " - " + lUO.getIdUsuarioPerfil());                               
//                               str = ol_listUO.get(n);
//                               str = ol_listUO.toString();
//                               n++;
                               //cmbUO.getItems().add(lUO.getIdUnidadeOrganizacional().getIdUnidadeOrganizacional() + "-" + lUO.getUnorNome().getUnorNome() + "-" + lUO.getIdUsuarioPerfil().getIdUsuarioPerfil()+"-"+lUO.getPeusDescricao().getPeusDescricao());
                            }
//                            cmbUO.getItems().addAll(listaUO.g)
//                            cmbUO.setItems(ol_listUO);                            
                            
                        }
                    } else {
                        // Show the error message.
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Senha errada");
                        alert.setContentText("Por favor preencha o campo com a senha correta");
                        alert.showAndWait();  
                        
                    }

                } else {
                    // Show the error message.
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Login errado");
                    alert.setContentText("Por favor preencha o campo com o Login correto");
                    alert.showAndWait();
                }
            }
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login não encontrado");
            alert.setContentText("Por favor preencha o campo com o Login correto");
            alert.showAndWait();
        }
            
        
    }
    
    @FXML
    private void handleBtnOKAction(ActionEvent event) throws IOException {
        
        String strcmbUO;    
        TbUnidadeOrganizacional nIdUO;        
        
        strcmbUO = cmbUO.getSelectionModel().getSelectedItem().toString();
        
        System.out.println("Valor do combobox selecionado: " + strcmbUO);
        //String delimiters = "\\s+|;\\s*|\\-\\s*";
        String delimiters = "-|\\;";
        String[] parts = strcmbUO.split(delimiters);
        strIdUO = parts[0].trim(); // 004
        strNomeUO = parts[1].trim(); // 034556
        strIdUsuarioPerfil = parts[2].trim();
        strDescricaoPerfil = parts[3].trim();        
        for (String token: parts){
            System.out.println(token);
        }
        
        nIdUO = new TbUnidadeOrganizacional(Integer.parseInt(strIdUO));
        listaUOGestor = consulta_TB_UO_GESTOR.getIdUOGestor(nIdUO);
        
        for (ci_eletronico.entities.TbUnidadeOrganizacionalGestor lUOGestor: listaUOGestor){
            nIdUOGestor = lUOGestor.getIdUoGestor();
        }
        
         //Ocultamos a janela de login
        (((Node)event.getSource()).getScene()).getWindow().hide();
        //--------- FIM Ocultar janela de Login ------------
        
        ShowMainWindowCIe(this, strIdUsuario, strUsername, strIdUO, strNomeUO, strIdUsuarioPerfil, strDescricaoPerfil, strHtmlAssinatura);
      
        
    }
    private void ComponentesVisible(boolean bCondicao){
        lblUO.setVisible(bCondicao);
        cmbUO.setVisible(bCondicao);
        btnOK.setVisible(bCondicao);  
        
    } 
    private void ComponentesDisable(boolean bCondicao){
        txtUsername.setDisable(bCondicao);
        pwdSenha.setDisable(bCondicao);
        btnAcessar.setDisable(bCondicao);
    }
    
    private void ShowMainWindowCIe(final LoginController loginController , String strIdUsuario, String strNomeUsuario, 
                                        String strIdUO, String strNomeUO, String strIdPerfil, String strDescricaoPerfil, String strHtmlAssinatura){
        try{
            
                scene = new Scene(new BorderPane());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ci_eletronico/FXMLMain.fxml"));
                scene.setRoot((Parent) loader.load());
                FXMLMainController controller = loader.<FXMLMainController>getController();
                controller.setVariaveisAmbiente(loginController,strIdUsuario,strNomeUsuario,strIdUO,strNomeUO,strIdPerfil,strDescricaoPerfil, strHtmlAssinatura);

                Stage stage = new Stage();
                stage.setTitle("CI-eletrônico");
                //set icon
                stage.getIcons().add(new Image("/resources/CI_FX02.png"));

                stage.setScene(scene);
                stage.show();
//                                
            }catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    
}
