/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico;

import ci_eletronico.entities.TbUnidadeOrganizacional;
import ci_eletronico.entities.TbUsuario;
import ci_eletronico.entities.TbVersoesSistema;
import ci_eletronico.utilitarios.Seguranca;
import ci_eletronico_queries.LoginQuery;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author victorcmaf
 */
//public class LoginController implements Initializable {
public class LoginController {
    @FXML
    Label lblMessage;
    @FXML
    private Button btnFechar;
    @FXML
    private Hyperlink hlink;
    @FXML
    private Label lblLogin;
    @FXML
    private TextField txtUsername;
    @FXML
    private Label lblSenha;
    @FXML
    private PasswordField pwdSenha;
    @FXML
    private Button btnAcessar;
    @FXML
    private Label lblUO;
    @FXML
    private ComboBox cmbUO; 
    @FXML
    private Button btnOK;
    @FXML
    private Button btnAtualizar;
   

      
    
    
    
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
    private String strgUserLogin = "";
    private String strVersaoCodigo = "";
    private String strRelease = "11032016";
    //private LoginQuery consulta  = new LoginQuery();
    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
  public void initialize() {
        // TODO
        //listaUsuarios = consulta.listaTbUsuario();
//        hlink.setOnAction(new EventHandler() {
//
//            @Override
//            public void handle(Event event) {
//                app.getHostServices().showDocument("http://www.jumpingbean.biz");
//            }
//        });
        boolean blPrecisaUpdate = false;
        
                 
        
        btnAcessar.setDefaultButton(true);        
        
        blPrecisaUpdate = VerificarVersaoSistema();
        if (blPrecisaUpdate){
            //Disabled os labels e botões
            btnAcessar.setVisible(false);
            lblUO.setVisible(false);
            cmbUO.setVisible(false);
            btnOK.setVisible(false); 
            hlink.setVisible(false);
            btnFechar.setVisible(true);
            btnAtualizar.setVisible(false);
            txtUsername.setVisible(false);
            pwdSenha.setVisible(false);
            lblLogin.setVisible(false);
            lblSenha.setVisible(false);
            lblMessage.setText("");
            lblMessage.setText("Sistema CI-eletrônica precisa ser atualizado.\nFavor entrar em contato com suporte ASSTI. ");
            lblMessage.setVisible(true);
            
        } else {
            ComponentesVisible(false);
            btnFechar.setVisible(false);
            lblMessage.setVisible(false);
            lblMessage.setText(""); 
            hlink.setVisible(false);
            btnAtualizar.setVisible(false);
            
        }
        
    } 
    
    private boolean VerificarVersaoSistema(){
        boolean blPrecisaUpdate = false;
        String strVersaoBancoDados = "";
        //String strVersaoCodigo = "";
        
        this.strVersaoCodigo = "1.5";
        
        LoginQuery consulta_TB_ATUALIZAR_SISTEMA  = new LoginQuery(); 
        List<ci_eletronico.entities.TbAtualizarSistema> listaVersoes = new ArrayList<>();
        listaVersoes = consulta_TB_ATUALIZAR_SISTEMA.listaTbAtualizarSistema();
        for(ci_eletronico.entities.TbAtualizarSistema l : listaVersoes){
            blPrecisaUpdate = l.getAtsiPrecisaAtualizar();
            strVersaoBancoDados = l.getAtsiVersao();
        }
        if (blPrecisaUpdate){
            if (0==this.strVersaoCodigo.compareTo(strVersaoBancoDados)){
                blPrecisaUpdate = false;
            } else {
                blPrecisaUpdate = true;
            }
        }
        return blPrecisaUpdate;
    }
    
    @FXML
    private void handleBtnFecharTelaLogin(ActionEvent event){
        exit(0);        
    }
    
    @FXML
    private void handleBtnAtualizarAplicacao(ActionEvent event){
        //Ocultamos a janela de login
        
        //(((Node)event.getSource()).getScene()).getWindow().hide();
        
        //--------- FIM Ocultar janela de Login ------------
        
        //Primeiro verificamos arquitetura do Windows
        //Verificar arquitetura do SO
        String arch = System.getenv("PROCESSOR_ARCHITECTURE");
        String wow64Arch = System.getenv("PROCESSOR_ARCHITEW6432");
        int nArquitetura = 0;

        String realArch = arch.endsWith("64") || wow64Arch != null && wow64Arch.endsWith("64") ? "64" : "32";
        System.out.println("PROCESSOR_ARCHITECTURE = " + realArch);
        if ("32".equals(realArch)){
            // SO Windows de 32 bits
            System.out.println("SO Windows 32 bits");
            nArquitetura = 32;

       } else {
            // SO Windows de 64 bits            
            System.out.println("SO Windows 64 bits");
            nArquitetura = 64;
       }
        
        //Procuramos o arquivo jar que realizará o Update do sistema 
        String strFileNameUpdate = "UpdateCiEletronica.jar";
        String strFileJar = "CI_Eletronico.jar";
        String strSqlJar = "sqljdbc42.jar";
        strFileNameUpdate = strFileNameUpdate.trim();
        String strUserHome = System.getProperty("user.home") + "\\Downloads\\";
        String strDirLibreriaBancoDados = strUserHome + "\\lib\\";
        boolean bMakeLibDir = false;
        boolean bDirLibExists = false;
        
        File theLibDir = new File(strDirLibreriaBancoDados);
        bDirLibExists = theLibDir.exists();
        
        if (!bDirLibExists){
            try{
                bMakeLibDir = theLibDir.mkdir();
            }catch(SecurityException se){
                //handle it
                bMakeLibDir = false;            
            }
        } else {
            bMakeLibDir = true;
        }
        if (bMakeLibDir){
            File outfile = null;

            LoginQuery consulta = new LoginQuery();
            List<TbVersoesSistema> listaAnexosUpdate = new ArrayList<TbVersoesSistema>();
            List<TbVersoesSistema> listaAnexosCi = new ArrayList<TbVersoesSistema>();
            List<TbVersoesSistema> listaAnexosSql = new ArrayList<TbVersoesSistema>();
            
            //Realizar o download do arquivo sqljdbc42.jar
             listaAnexosSql = consulta.downloadAnexo("4.2", 64, strSqlJar);
             for(TbVersoesSistema l : listaAnexosSql){
                 strSqlJar = l.getVesiNomeJar();
                 outfile = new File(strDirLibreriaBancoDados + l.getVesiNomeJar());
                 try {
                     writeArquivo(outfile, l.getVesiBlob());
                 }catch (IOException e){
                     e.printStackTrace();
                 }
             }

            //Realizar o download do arquivo CI_Eletronico.jar


             listaAnexosCi = consulta.downloadAnexo(this.strVersaoCodigo/*"1.3"*/, nArquitetura, strFileJar );
             for(TbVersoesSistema l : listaAnexosCi){
                 strFileJar = l.getVesiNomeJar();
                 outfile = new File(strUserHome + l.getVesiNomeJar());
                 try {
                     writeArquivo(outfile, l.getVesiBlob());
                 }catch (IOException e){
                     e.printStackTrace();
                 }
             }

            //Realizar o download do arquivo UpdateCiEletronica.jar        

             listaAnexosUpdate = consulta.downloadAnexo(this.strVersaoCodigo/*"1.3"*/, nArquitetura, strFileNameUpdate );
             for(TbVersoesSistema l : listaAnexosUpdate){
                 strFileNameUpdate = l.getVesiNomeJar();
                 outfile = new File(strUserHome + l.getVesiNomeJar());
                 try {
                     writeArquivo(outfile, l.getVesiBlob());
                 }catch (IOException e){
                     e.printStackTrace();
                 }
             }

             strFileNameUpdate = strFileNameUpdate.trim();        

            String strFilePath = strUserHome + strFileNameUpdate;

            //Runtime.exec(" java -jar " + strFilePath);

            String strJavaHome = System.getProperty("java.home");
            String strJavaBin = strJavaHome + File.separator + "bin" + File.separator + "java";



            try{            
                //ProcessBuilder pb = new ProcessBuilder("java", "-jar", strFilePath);
                ProcessBuilder pb = new ProcessBuilder(strJavaBin, "-jar", strFilePath);
                //pb.directory(new File(strUserHome));
                Process p = pb.start();   
                exit(0);
            }catch(IOException ex){
                System.out.println("Erro ao tentar executar arquivo jar: " + ex);
            }
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Não foi possível criar diretório: lib");
            alert.setContentText("Por favor contatar suporte ASSTI");
            alert.showAndWait();  
            
        }
        
        //exit(0);
    }
    
     public void writeArquivo(File file, byte[] data) throws IOException
    {
        OutputStream fo = new FileOutputStream(file);
        // Write the data
        fo.write(data);
        // flush the file (down the toilet)
        fo.flush();
        // Close the door to keep the smell in.
        fo.close();
    }
    
    @FXML
    private void handleBtnAcessarAction(ActionEvent event) throws IOException, Exception {
        
        
        //FXMLLoader loader = new FXMLLoader();
        
        //FXMLMainController mainController = 
        
        String strUserLogin ="";
        strUserLogin = txtUsername.getText();
        String strlUOGestorDescricao = "";
        
        listaUsuarios = consulta_TB_USUARIO.listaUserLogin(strUserLogin);
        
        if (listaUsuarios.size()>0){
            for(ci_eletronico.entities.TbUsuario l : listaUsuarios){
                if(txtUsername.getText().equals(l.getUsuLogin())){

                    String strPassword = pwdSenha.getText();
                    String strEnc = Seguranca.encriptar(strPassword);
                    String strMD5 = Seguranca.stringToMD5(strPassword);
                    
                    //if(strEnc.equals(l.getUsuSenha())){ // Utilizamos Encriptação
                    if(strMD5.equals(l.getUsuSenha().toUpperCase())){ //Utilizamos MD5
                        this.strgUserLogin = strUserLogin;
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
                                                
                        String strUO = "";
                        //nIdUsuario.setIdUsuario(l.getIdUsuario());
                        strIdUsuario = l.getIdUsuario().toString();
                        strUsername = l.getUsuNomeCompleto();
                        
                        
                        listaUO = consulta_TB_USUARIO_PERFIL_UO.listaUO(nIdUsuario);   
                        //listaUO = consulta_TB_USUARIO_PERFIL_UO.listaJoinUO2(nIdUsuario);  
                         for(ci_eletronico.entities.TbUsuarioPerfilUo lUO : listaUO){
//                             System.out.println("TbUsuarioPerfilUo campo 1 - " + lUO.getIdUnidadeOrganizacional().getIdUnidadeOrganizacional());
//                             System.out.println("TbUsuarioPerfilUo campo 2 - " + lUO.getIdUnidadeOrganizacional().getUnorNome());
//                             System.out.println("TbUsuarioPerfilUo campo 3 - " + lUO.getIdUsuarioPerfil().getIdUsuarioPerfil());
//                             System.out.println("TbUsuarioPerfilUo campo 1 - " + lUO.getIdUsuarioPerfil().getPeusDescricao());
                             cmbUO.getItems().add(lUO.getIdUnidadeOrganizacional().getIdUnidadeOrganizacional() + " = " + lUO.getIdUnidadeOrganizacional().getUnorNome() + " ; " + lUO.getIdUsuarioPerfil().getIdUsuarioPerfil()+" = "+lUO.getIdUsuarioPerfil().getPeusDescricao());
                         }
                       
                        
                        cmbUO.getSelectionModel().selectFirst(); 
                        //TbUnidadeOrganizacional nIdUO;
                        
                        //Variaveis para sber o nome da UO Gestora
                        List<ci_eletronico.entities.TbUnidadeOrganizacional> listaUODescricao = new ArrayList<>();
                        LoginQuery consulta_Nome_UO  = new LoginQuery(); 
                        //----------------------------------------------------------
                        
                        if (listaUO.size()>0 && listaUO.size() < 2) {
                        //if (listaUO.size()>0 && listaUO.size() < 2){
                            strUO = cmbUO.getSelectionModel().getSelectedItem().toString();
//                            System.out.println("Valor do combobox selecionado: " + strUO);
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
                            
                            listaUODescricao = consulta_Nome_UO.getDescricaoUOGestor(nIdUOGestor); 
                            for (ci_eletronico.entities.TbUnidadeOrganizacional lista: listaUODescricao){
                               strlUOGestorDescricao = lista.getUnorNome();
                            }
                            
                            //loader.setLocation(FXMLMainController.class.getResource("/ci_eletronico/FXMLMain.fxml"));
                                                      
                            //Ocultamos a janela de login
                            (((Node)event.getSource()).getScene()).getWindow().hide();
                            //--------- FIM Ocultar janela de Login ------------
                            
//                                                       
                            //Mostramos MainWindow com dados do usuário logado
                            ShowMainWindowCIe(this, strIdUsuario, strUsername, strIdUO, strNomeUO, strIdUsuarioPerfil, strDescricaoPerfil, strHtmlAssinatura, nIdUOGestor, strgUserLogin,
                                    strlUOGestorDescricao);

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
        //String delimiters = "-|\\;";
        String delimiters = "=|\\;";
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
        
        List<ci_eletronico.entities.TbUnidadeOrganizacional> listaUODescricao = new ArrayList<>();
        LoginQuery consulta_Nome_UO  = new LoginQuery();         
        listaUODescricao = consulta_Nome_UO.getDescricaoUOGestor(nIdUOGestor); 
        String strlUOGestorDescricao = "";
        
        for (ci_eletronico.entities.TbUnidadeOrganizacional lista: listaUODescricao){
           strlUOGestorDescricao = lista.getUnorNome();
        }
        
         //Ocultamos a janela de login
        (((Node)event.getSource()).getScene()).getWindow().hide();
        //--------- FIM Ocultar janela de Login ------------
        
        ShowMainWindowCIe(this, strIdUsuario, strUsername, strIdUO, strNomeUO, strIdUsuarioPerfil, strDescricaoPerfil, strHtmlAssinatura, nIdUOGestor, strgUserLogin, 
                strlUOGestorDescricao );
      
        
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
                                        String strIdUO, String strNomeUO, String strIdPerfil, String strDescricaoPerfil, String strHtmlAssinatura, int nIdUOGestor, 
                                        String strlUserLogin, String strlUOGestorDescricao){
        try{
            
                scene = new Scene(new BorderPane());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ci_eletronico/FXMLMain.fxml"));
                scene.setRoot((Parent) loader.load());
                FXMLMainController controller = loader.<FXMLMainController>getController();
                controller.setVariaveisAmbiente(loginController,strIdUsuario,strNomeUsuario,strIdUO,strNomeUO,strIdPerfil,strDescricaoPerfil, strHtmlAssinatura, 
                        nIdUOGestor, strlUserLogin, strlUOGestorDescricao);

                Stage stage = new Stage();
                stage.setTitle("CI-eletrônica (versão " + this.strVersaoCodigo + ") release-" +  this.strRelease);
                //set icon
                stage.getIcons().add(new Image("/resources/CI_FX02.png"));
                
                scene.getStylesheets().add(getClass().getResource("/resources/highlightingTable.css").toExternalForm());

                stage.setScene(scene);
                stage.show();
//                                
            }catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    
}
