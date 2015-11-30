/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico;

import ci_eletronico.entities.TbAnexo;
import ci_eletronico.entities.TbCIPorAprovar;
import ci_eletronico.entities.TbCaixaEntrada;
import ci_eletronico.entities.TbCiDestinatario;
import ci_eletronico.entities.TbComunicacaoInterna;
import ci_eletronico.entities.TbTipoEnvio;
import ci_eletronico.entities.TbUsuario;
import ci_eletronico_queries.MainWindowQueries;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Modality;
import javafx.util.Pair;

/**
 *
 * @author victorcmaf
 */
public class FXMLMainController implements Initializable {
//public class FXMLMainController {
    
    @FXML
    private Label lblIdUsuario;    
    @FXML
    private Label lblNomeUsuario;    
    @FXML
    private Label lblIdUO;    
    @FXML
    private Label lblNomeUO;    
    @FXML
    private Label lblIdPerfil;    
    @FXML
    private Label lblNomePerfil;    
    @FXML
    private Label lblUOGestora;
    @FXML
    private Label lblCaixa;
    @FXML
    private Button btnTrocarSenha; 
    @FXML
    private Button btnTrocarAssinatura;
    @FXML
    private Button btnSair;
    @FXML
    private Button btnPendentesAprovacao;
    @FXML
    private Button btnCaixaEntrada;
    @FXML
    private Button btnCaixaEntradaSolicitandoAprovacao;
    @FXML
    private Button btnCaixaSaida;
    @FXML
    private Button btnCaixaPendencias;
    @FXML
    private Button btnCaixaArquivadas;
    @FXML
    private Button btnNovaCI;
    @FXML
    private Button btnNovaCICircular;
    @FXML
    private Button btnNovaCIConfidencial;
    @FXML
    //private TableView TbViewGeral;
    private TableView<TbCIPorAprovar> TbViewGeral;
    @FXML
    private TableColumn ClIdCoin;
    @FXML
    private TableColumn ClDataEnvio; 
    @FXML
    private TableColumn ClUORemitente; 
    @FXML
    private TableColumn ClAutorRemitente; 
    @FXML
    private TableColumn ClAssunto; 
    @FXML
    private Button btnImprimirCI;
    @FXML 
    private Button btnAprovarCI;
    @FXML
    private Button btnEncaminharCI;
    @FXML
    private Button btnArquivarCI;
    @FXML
    private Button btnDesarquivarCI;
    @FXML
    private HTMLEditor htmlEditorCI;
    @FXML
    private Label lblNumeroSequencialCI;
    @FXML
    private TextFlow txtFAnexos;
               
    // Clases para tratar Anexar Arquivos
    private Desktop desktop = Desktop.getDesktop();
    
    private Integer nTipoPerfil = 0;
    
    private Scene scene;
    
    private String strIdUsuario = "";
    private int nIdUsuarioLogado = 0;
    private String strNomeUsuario = "";
    private String strIdUO = ""; 
    private String strNomeUO = "";
    private String strIdPerfil = "";
    private String strDescricaoPerfil = "";
    private String strHtmlAssinatura="";
    private int nTipoCI = 0;
    private int nIdUOGestor = 0;
    private int nIdUnidadeOrganizacional = 0;
    
    //private MainWindowQueries consulta = new MainWindowQueries();
    private MainWindowQueries consulta;
    private List<TbComunicacaoInterna> listaCiSemAprovar = new ArrayList<TbComunicacaoInterna>();
    //private ObservableList<TbComunicacaoInterna> obslistaTbComunicacaoInterna;
    private ObservableList<TbCIPorAprovar> obslistaTbCIPorAprovar;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//    public void initialize() {
        // TODO 
    //System.out.print("Tipo de Perfil metodo initialize = " + nTipoPerfil);
    
    
    
    }
    
    @FXML
    private void handleBtnSairAction(ActionEvent event) throws IOException {
        
        //Ocultamos a janela de login
        (((Node)event.getSource()).getScene()).getWindow().hide();
        //--------- FIM Ocultar janela de Main ------------
        
    }
    @FXML
    private void handleBtnTrocarAssinatura(ActionEvent event){
        //setBotoesMainWindow(nTipoPerfil);
        String strAssinatura= "";
        int nlIdUsuario = 0;        
        
        nlIdUsuario = Integer.parseInt(lblIdUsuario.getText());
        consulta = new MainWindowQueries();
        List<TbUsuario> listaUsuarios = new ArrayList<TbUsuario>();
        
        //Extrair o Id para realizar o download do arquivo
        
        listaUsuarios = consulta.listaUserAssinatura(nlIdUsuario);
        for(TbUsuario l : listaUsuarios){
            try {
                if (l.getUsuAssinatura().isEmpty()){
                    strAssinatura = "";
                } else {
                    strAssinatura = l.getUsuAssinatura();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }       
        
        ShowTrocarAssinatura(this , nlIdUsuario, strAssinatura);
    }
    private void ShowTrocarAssinatura(FXMLMainController mainController, int nlIdUsuario, String strAssinatura){
        try{
                scene = new Scene(new SplitPane());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ci_eletronico/fxml_utilitarios/Assinatura.fxml"));
                scene.setRoot((Parent) loader.load());
                
                ci_eletronico.fxml_utilitarios.AssinaturaController Assinatura_controller = loader.<ci_eletronico.fxml_utilitarios.AssinaturaController>getController();     
                Assinatura_controller.setVariaveisAmbienteTrocarAssinatura(mainController, nlIdUsuario, strAssinatura);
                //controller.setVariaveisAmbienteNovaCI(mainController, strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, strDescricaoPerfil);                
                
                Stage stage = new Stage();
                stage.setTitle("Trocar Assinatura");
                //set icon
                stage.getIcons().add(new Image("/resources/signature_32.png"));

                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);     //Window Parent fica inativo
                stage.showAndWait();
                
                strHtmlAssinatura = Assinatura_controller.getNovaAssinatura();
//                                
            }catch (IOException ex) {
                Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    @FXML
    private void handleBtnTrocarSenha(ActionEvent event) throws IOException{        
        //setBotoesMainWindow(nTipoPerfil);
        trocarSenha(nIdUsuarioLogado);               
    }
    
    public void trocarSenha(int nIdUserUO) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Trocar senha");
        dialog.setHeaderText(null);

        // Set the icon (must be included in the project).
        dialog.setGraphic(new ImageView(this.getClass().getResource("/resources/User_password.png").toString()));

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        PasswordField username = new PasswordField();
        username.setPromptText("Nova senha");
        PasswordField password = new PasswordField();
        password.setPromptText("Confirmar nova senha");

        grid.add(new Label("Nova senha:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Confirmar nova senha:"), 0, 1);
        grid.add(password, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            String strSenha1 = "";
            String strSenha2 = "";
            boolean bUpdate = false;
            if (dialogButton == loginButtonType) {
                //System.out.println("OK button pressed");
                strSenha1 = username.getText();
                strSenha2 = password.getText();
                if(username.getText().equals(password.getText())){
                    // Senhas batem. Update senha no banco de dados
                    try{
                    consulta  = new MainWindowQueries();
                    bUpdate = consulta.UpdateTrocarSenha(nIdUserUO, username.getText());
                    if (bUpdate){
//                        System.out.println("IdUsuario = " + nIdUserUO);
//                        System.out.println("Update deve acontecer");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Informação");
                        alert.setHeaderText(null);
                        alert.setContentText("Sua senha foi trocada com sucesso.");
                        alert.showAndWait();
                        return new Pair<>(username.getText(), password.getText());
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Trocar senha");
                        alert.setHeaderText("Senha não foi atualizada");
                        alert.setContentText("Favor contatar Administrador do sistema");
                        alert.showAndWait();
                    }
                    }catch(Exception e){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Trocar senha");
                        alert.setHeaderText(null);
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();                        
                    }                        
                }
                else{
                    // Erro. A nova senha não bate
                    //System.out.println("As senhas não batem");  
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText("Os valores inseridos não batem");
                    alert.setContentText("Favor verificar os valores inseridos.\nInfelizmente sua senha não foi trocada");
                    alert.showAndWait();
                }                
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
        });
    }
    
    public void setVariaveisAmbiente(final LoginController loginController , String strIdUsuario, String strNomeUsuario, 
                                        String strIdUO, String strNomeUO, String strIdPerfil, String strDescricaoPerfil, String strHtmlAssinatura, int nIdUOGestor) {
        lblIdUsuario.setText(strIdUsuario);
        lblNomeUsuario.setText(strNomeUsuario);
        lblIdUO.setText(strIdUO);
        lblNomeUO.setText(strNomeUO);  
        lblIdPerfil.setText(strIdPerfil);
        lblNomePerfil.setText(strDescricaoPerfil);
        lblUOGestora.setText(Integer.toString(nIdUOGestor));
        
        this.strHtmlAssinatura = strHtmlAssinatura;
        nTipoPerfil = Integer.parseInt(strIdPerfil);
        this.nIdUOGestor = nIdUOGestor;
        //System.out.print("Tipo de Perfil metodo setVariaveisAmbiente = " + nTipoPerfil);
        nIdUnidadeOrganizacional = Integer.parseInt(strIdUO);
        nIdUsuarioLogado = Integer.parseInt(strIdUsuario);
        
        setBotoesMainWindow(nTipoPerfil);
        
    }
    public void setBotoesMainWindow(Integer IntTipoPerfil){
        switch(IntTipoPerfil){
            case 1: //ROLE_ADM - Gestor da UO
                //Botões principais
                btnNovaCI.setDisable(false);
                btnNovaCICircular.setDisable(false);
                btnNovaCIConfidencial.setDisable(false);
                btnPendentesAprovacao.setDisable(false);
                
                //Botões secundarios
                btnImprimirCI.setDisable(false);
                btnAprovarCI.setDisable(false);
                btnEncaminharCI.setDisable(false);
                btnArquivarCI.setDisable(false);
                btnDesarquivarCI.setDisable(false);
                
                //Labels
                lblNumeroSequencialCI.setText("");
                
                break;
                    
            case 2: //ROLE_MNG - Usuario da UO
                //Botões principais
                btnNovaCI.setDisable(false);
                btnNovaCICircular.setDisable(false);
                btnNovaCIConfidencial.setDisable(true);
                btnPendentesAprovacao.setDisable(false);
                
                //Botões secundarios
                btnImprimirCI.setDisable(false);
                btnAprovarCI.setDisable(true);
                btnEncaminharCI.setDisable(false);
                btnArquivarCI.setDisable(false);
                btnDesarquivarCI.setDisable(false);
                
                //Labels
                lblNumeroSequencialCI.setText("");
                
                break;
                
            case 3: // ROLE_USER - Usuario Comum
                //Botões principais
                btnNovaCI.setDisable(true);
                btnNovaCICircular.setDisable(true);
                btnNovaCIConfidencial.setDisable(true);
                btnPendentesAprovacao.setDisable(true);
                
                //Botões secundarios
                btnImprimirCI.setDisable(true);
                btnAprovarCI.setDisable(true);
                btnEncaminharCI.setDisable(true);
                btnArquivarCI.setDisable(true);
                btnDesarquivarCI.setDisable(true);
                
                //Labels
                lblNumeroSequencialCI.setText("");
                
                break;
            default:
                break;
            
        }
    }
    
    @FXML
    private void handleBtnNovaCI(ActionEvent event) throws IOException {
//        String strIdUsuario = "";
//        String strNomeUsuario = "";
//        String strIdUO = ""; 
//        String strNomeUO = "";
//        String strIdPerfil = "";
//        String strDescricaoPerfil = "";
        strIdUsuario = lblIdUsuario.getText();
        strNomeUsuario = lblNomeUsuario.getText();
        strIdUO = lblIdUO.getText();
        strNomeUO = lblNomeUO.getText();  
        strIdPerfil = lblIdPerfil.getText();
        strDescricaoPerfil = lblNomePerfil.getText();
        nTipoCI = 1;    //CI Normal
        ShowNovaCIe(this , strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, strDescricaoPerfil, strHtmlAssinatura, nTipoCI, nIdUOGestor);
                        
    }
    @FXML
    private void handleBtnNovaCICircular(ActionEvent event) throws IOException {
        strIdUsuario = lblIdUsuario.getText();
        strNomeUsuario = lblNomeUsuario.getText();
        strIdUO = lblIdUO.getText();
        strNomeUO = lblNomeUO.getText();  
        strIdPerfil = lblIdPerfil.getText();
        strDescricaoPerfil = lblNomePerfil.getText();
        nTipoCI = 2;    //CI Circular
        ShowNovaCIe(this , strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, strDescricaoPerfil, strHtmlAssinatura, nTipoCI, nIdUOGestor);
        
    }
    @FXML
    private void handleBtnNovaCIConfidencial(ActionEvent event) throws IOException {
        strIdUsuario = lblIdUsuario.getText();
        strNomeUsuario = lblNomeUsuario.getText();
        strIdUO = lblIdUO.getText();
        strNomeUO = lblNomeUO.getText();  
        strIdPerfil = lblIdPerfil.getText();
        strDescricaoPerfil = lblNomePerfil.getText();
        nTipoCI = 3;    //CI Confidencial
        ShowNovaCIe(this , strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, strDescricaoPerfil, strHtmlAssinatura, nTipoCI, nIdUOGestor);
        
    }
    @FXML
    private void handleBtnCIEncaminhar(ActionEvent event) throws IOException{
        strIdUsuario = lblIdUsuario.getText();
        strNomeUsuario = lblNomeUsuario.getText();
        strIdUO = lblIdUO.getText();
        strNomeUO = lblNomeUO.getText();  
        strIdPerfil = lblIdPerfil.getText();
        strDescricaoPerfil = lblNomePerfil.getText();
        nTipoCI = 4;    //CI Encaminhada
        ShowNovaCIe(this , strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, strDescricaoPerfil, strHtmlAssinatura, nTipoCI, nIdUOGestor);
    }
    public void ShowNovaCIe(final FXMLMainController mainController , String strIdUsuario, String strNomeUsuario, 
                                        String strIdUO, String strNomeUO, String strIdPerfil, String strDescricaoPerfil, String strHtmlAssinatura, int nTipoCI, int nIdUOGestor){
        try{
                scene = new Scene(new SplitPane());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ci_eletronico/nova_ci/NovaCI.fxml"));
                scene.setRoot((Parent) loader.load());
                
                ci_eletronico.nova_ci.NovaCIController nova_ci_controller = loader.<ci_eletronico.nova_ci.NovaCIController>getController();     
                nova_ci_controller.setVariaveisAmbienteNovaCI(mainController, strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, strDescricaoPerfil, strHtmlAssinatura, nTipoCI, nIdUOGestor);
                //controller.setVariaveisAmbienteNovaCI(mainController, strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, strDescricaoPerfil);                
                
                Stage stage = new Stage();
                stage.setTitle("Nova CI-eletrônico");
                //set icon
                stage.getIcons().add(new Image("/resources/Nova_CI.png"));

                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);     //Window Parent fica inativo
                stage.showAndWait();
//                                
            }catch (IOException ex) {
                Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    @FXML
    private void handleBtnCaixaEntradaPendentesAprovacao(ActionEvent event) throws IOException {
        
        lblCaixa.setText("");
        lblCaixa.setText("Caixa de entrada (solicitando aprovação)");       
        
        setBotoesMainWindow(nTipoPerfil);
        
        btnEncaminharCI.setDisable(true);
        btnDesarquivarCI.setDisable(true);
        
        if ((this.nIdUnidadeOrganizacional == this.nIdUOGestor) &&(this.nTipoPerfil == 1)){
            btnAprovarCI.setDisable(false);
        }else{
            btnAprovarCI.setDisable(true);
        }          
        clearTelas();
        
    }
    @FXML
    private void handleBtnPendentesAprovacao(ActionEvent event) throws IOException {
        
        lblCaixa.setText("");
        lblCaixa.setText("Caixa de saída (solicitando aprovação)");       
        
        setBotoesMainWindow(nTipoPerfil);
        
       
        btnEncaminharCI.setDisable(true);
        btnDesarquivarCI.setDisable(true);
        
        if ((this.nIdUnidadeOrganizacional == this.nIdUOGestor) &&(this.nTipoPerfil == 1)){
            btnAprovarCI.setDisable(false);
        }else{
            btnAprovarCI.setDisable(true);
        }
        
        clearTelas();
        
        //List<TbComunicacaoInterna> listaCiSemAprovar = new ArrayList<>();
        int nIdCoin = 0;
        int nSequencial = 0;
        String strAssunto = "";
        String strConteudo = "";
        int nIdUsuario = 0;
        String strUsuarioNomeCompleto = "";
        String strApensamento = "";
        int nIdUO = 0;
        int nIdUOGestor = 0;
        int nTipoCoin = 0;
        int nIdTabelaFonte = 0;
        String strUODescricao = "";
        Date dataCriacao;      
        Date dataAutorizado;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");        
        String strDataCriacao = "";
        boolean bTemAnexos = false;
        boolean bAutorizado = false;
        boolean bGestorArquivado = false;
        boolean bArquivadoUO = false;
        boolean bArquivadoUOGestor = false;
        boolean bCoinReadOnly = false;
        
        obslistaTbCIPorAprovar = FXCollections.observableArrayList();
        
        consulta  = new MainWindowQueries();
        
        //Deve-se mostrar as Cis para aprovação só para usuário que faz parte da UO Gestora
        if (this.nIdUOGestor == this.nIdUnidadeOrganizacional){
                listaCiSemAprovar = consulta.getlistaTbComunicacaoInternaPorAprovar(this.nIdUOGestor);

                for(TbComunicacaoInterna l : listaCiSemAprovar){
                    try {
                    nIdCoin = l.getIdCoin();
                    strAssunto = l.getCoinAssunto();
                    strConteudo = l.getCoinConteudo();
                    nIdUsuario = l.getIdUsuario();
                    strUsuarioNomeCompleto = l.getUsuNomeCompleto();
                    nIdUO = l.getIdUnidadeOrganizacional();
                    strUODescricao = l.getUnorDescricao();
                    nIdUOGestor = l.getIdUoGestor();
                    bAutorizado = l.getCoinAutorizado();
                    nTipoCoin = l.getIdTipoCoin().getIdTipoCoin();
                    strApensamento = l.getCoinApensamento();            
                    nSequencial = l.getCoinNumero();
                    bArquivadoUO = l.getCoinUoArquivado();
                    bArquivadoUOGestor = l.getCoinUoGestorArquivado();            
                    dataCriacao = l.getCoinDataCriacao();
                    strDataCriacao = df.format(dataCriacao);            
                    dataAutorizado = l.getCoinDataAutorizado();
                    bCoinReadOnly = l.getCoinReadOnly();
                    bTemAnexos = l.getCoinTemAnexos();
                    nIdTabelaFonte = 1;


        //            obslistaTbCIPorAprovar.add(new TbCIPorAprovar(nIdCoin,strAssunto,strConteudo,nIdUsuario,strUsuarioNomeCompleto,
        //                        nIdUO,strUODescricao,nSequencial,dataCriacao,strDataCriacao,bAutorizado,bTemAnexos,1));
                        obslistaTbCIPorAprovar.add(new TbCIPorAprovar(nIdCoin, strAssunto, strConteudo, nIdUsuario, strUsuarioNomeCompleto, nIdUO, strUODescricao, 
                                nIdUOGestor, bAutorizado, nTipoCoin, strApensamento, nSequencial, bArquivadoUO, bArquivadoUOGestor, dataCriacao, strDataCriacao, 
                                dataAutorizado, bCoinReadOnly, bTemAnexos ,nIdTabelaFonte));
                    } catch (Exception e){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("ObservableList Error");
                        alert.setContentText(e.toString());
                        alert.showAndWait();
                    }
                    }
                //	ClDataEnvio; ClUORemitente; ClAutorRemitente; ClAssunto;
                ClIdCoin.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Integer>("intp_idCoin"));
                ClDataEnvio.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_dataCriacao"));        
                ClUORemitente.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUORemitente"));        
                ClAutorRemitente.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_UsuarioNomeCompleto"));        
                ClAssunto.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_Assunto"));
                TbViewGeral.setItems(obslistaTbCIPorAprovar);
                //TbViewGeral


                TbViewGeral.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                    try{
                        //TableView<TbCIPorAprovar> TbViewGeral = new TableView<>();
                        if(TbViewGeral.getSelectionModel().getSelectedItem() != null){
                            //TbCIPorAprovar tbCiPorAprovar = TbViewGeral.getSelectionModel().getSelectedItem();
                            //TbCIPorAprovar tbtbCiPorAprovar = new TbCIPorAprovar();
                            TbCIPorAprovar tbCiPorAprovar = TbViewGeral.getSelectionModel().getSelectedItem();
                            int nCISequencial = 0;
                            int nlIdCI = 0;
                            boolean bTemAnexo = false;
                            String strDescricaoUO = "";
                            String strYear = "";
                            Date dataCriacao;
                            SimpleDateFormat df = new SimpleDateFormat("yyyy");

                            nlIdCI = tbCiPorAprovar.getIntp_idCoin();
                            strDescricaoUO = tbCiPorAprovar.getStrp_DescricaoUORemitente();
                            nCISequencial = tbCiPorAprovar.getIntp_idCoinNumero();                    
                            dataCriacao = tbCiPorAprovar.getDataCriacao();
                            bTemAnexo = tbCiPorAprovar.getBoolp_CoinTemAnexos();

                            strYear = df.format(dataCriacao);

                            htmlEditorCI.setHtmlText(tbCiPorAprovar.getStrp_Conteudo());                    
                            lblNumeroSequencialCI.setText(strDescricaoUO + " " + String.format("%05d",nCISequencial)+"/" + strYear);
                            if (bTemAnexo){
                                PreencherTxtFAnexos(nlIdCI);
                            }
                            else {
                                txtFAnexos.getChildren().clear();
                            }
                        }
                    }catch (Exception e) {
                            e.printStackTrace();
                            //labelMessage.setText("Error on get row Data");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erro");
                            alert.setHeaderText("Erro na carga da TableView");
                            alert.setContentText(e.getMessage());
                            alert.showAndWait();
                    }
                }
            }); 
        }
    }
    private void PreencherTxtFAnexos(int nlIdCI){
        txtFAnexos.getChildren().clear();
        Text txtArquivoSelecionado;
        consulta  = new MainWindowQueries();
        List<TbAnexo> listaAnexos = new ArrayList<TbAnexo>();
        TbComunicacaoInterna nlIdCoin = new TbComunicacaoInterna(nlIdCI);
        listaAnexos = consulta.getlistaAnexo(nlIdCoin);
        for(TbAnexo l : listaAnexos){
            txtArquivoSelecionado = new Text();                          
            //txtArquivoSelecionado.setText("\""+ UOSelected.get(nContador)+ "\"; ");
            txtArquivoSelecionado.setText(l.getIdAnexo() + "=" + l.getAnexoNome() + " ; ");
            txtArquivoSelecionado.setFill(Color.BLACK);
            txtArquivoSelecionado.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
            txtFAnexos.getChildren().add(txtArquivoSelecionado);
        }
        
        txtFAnexos.setOnMousePressed(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent ev) {
                boolean bPrimary = false;
                boolean bMiddle = false;
                boolean bSecondary = false;
                bPrimary = ev.isPrimaryButtonDown();
                bMiddle = ev.isMiddleButtonDown();
                bSecondary = ev.isSecondaryButtonDown();
                
                final ContextMenu contextMenu = new ContextMenu();
                MenuItem menuItemAbrir = new MenuItem("Abrir arquivo");
                MenuItem menuItemSalvar = new MenuItem("Salvar arquivo");        
                contextMenu.getItems().addAll(menuItemAbrir, menuItemSalvar);
                contextMenu.hide();
                menuItemAbrir.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
//                        int nlIdCoinAnexo = 0;
//                        nlIdCoinAnexo = nlIdCoin.getIdCoin();
                        Text clicked = (Text) ev.getTarget();
//                        System.out.println("Abrir arquivo clicked");
//                        System.out.println("IdCoin = " + nlIdCoinAnexo + " Nome arquivo clicked = " + clicked.getText());
                        AbrirArquivo(clicked.getText(),1);
                    }
                });
                menuItemSalvar.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
//                        System.out.println("Salvar arquivo clicked");
                        Text clicked = (Text) ev.getTarget();
//                        System.out.println("Abrir arquivo clicked");
//                        System.out.println("IdCoin = " + nlIdCoinAnexo + " Nome arquivo clicked = " + clicked.getText());
                        AbrirArquivo(clicked.getText(),2);
                    }
                });
                
                if (ev.isSecondaryButtonDown()){
                    if (1 == ev.getClickCount()){
                        if(ev.getTarget() instanceof Text) {                            
                            contextMenu.show(txtFAnexos, ev.getScreenX(), ev.getScreenY());
                        }
                    }
                }    
            }
        });        
    }  
    private void AbrirArquivo(String strArquivo, int nlMenuItem){
        int nlIdAnexo = 0;
        File outfile = null;
        String strFileName = "";
        String strUserHome = System.getProperty("user.home") + "//Downloads//";
        String strDelimiters = "=";
        String[] strParts = strArquivo.split(strDelimiters);
        for (int i = 0; i < strParts.length; i++){
            if (0==i){
                nlIdAnexo = Integer.parseInt(strParts[i].trim());
            }
        }
        
        consulta  = new MainWindowQueries();
        List<TbAnexo> listaAnexos = new ArrayList<TbAnexo>();
        
        //Extrair o Id para realizar o download do arquivo
        
        listaAnexos = consulta.downloadAnexo(nlIdAnexo);
        for(TbAnexo l : listaAnexos){
            strFileName = l.getAnexoNome();
            outfile = new File(strUserHome + l.getAnexoNome());
            try {
                writeArquivo(outfile, l.getAnexoBlob());
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        switch(nlMenuItem){
            case 1:
                    openArquivo(outfile);
                    break;
            case 2:
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Salvar arquivo");
                    alert.setHeaderText("O arquivo " + strFileName + " foi salvo com sucesso");
                    alert.setContentText("O arquivo foi salvo na pasta Downloads");
                    alert.showAndWait();
                    break;
            default:
                    break;
        }
        
    }
    public void openArquivo(File file){
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                FXMLMainController.class.getName()).log(
                    Level.SEVERE, null, ex
                );
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
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
    private void handleBtnAprovarCI(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Aprovação");
        alert.setHeaderText(null);
        alert.setContentText("Deseja realmente aprovar CI?"); 
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK 
        } else {
            // ... user chose CANCEL or closed the dialog 
        }        
    }
    @FXML
    private void handleBtnArquivarCI(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Arquivamento");
        alert.setHeaderText(null);
        alert.setContentText("Deseja realmente arquivar CI?"); 
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK 
        } else {
            // ... user chose CANCEL or closed the dialog 
        }        
    }
    @FXML
    private void handleBtnDesarquivarCI(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Desarquivamento");
        alert.setHeaderText(null);
        alert.setContentText("Deseja realmente desarquivar CI?"); 
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK 
        } else {
            // ... user chose CANCEL or closed the dialog 
        }        
    }
    
    @FXML
    private void handleBtnCaixaEntrada(ActionEvent event) throws IOException {
        lblCaixa.setText("");
        lblCaixa.setText("Caixa de entrada");
        setBotoesMainWindow(nTipoPerfil);
        clearTelas();
        PreencherCaixaEntrada(2);
        
    }
    @FXML
    private void handleBtnCaixaSaida(ActionEvent event) throws IOException {
        lblCaixa.setText("");
        lblCaixa.setText("Caixa de saida");
        setBotoesMainWindow(nTipoPerfil);       
        
        btnAprovarCI.setDisable(true);
        btnEncaminharCI.setDisable(true);
        btnArquivarCI.setDisable(true);
        btnDesarquivarCI.setDisable(true);  
        
        clearTelas();
        
//        if ((this.nIdUnidadeOrganizacional == this.nIdUOGestor) &&(this.nTipoPerfil == 1)){
//            btnAprovarCI.setDisable(false);
//        }else{
//            btnAprovarCI.setDisable(true);
//        }          
        
        //List<TbComunicacaoInterna> listaCiSemAprovar = new ArrayList<>();
        int nIdCoin = 0;
        int nSequencial = 0;
        String strAssunto = "";
        String strConteudo = "";
        int nIdUsuario = 0;
        String strUsuarioNomeCompleto = "";
        String strApensamento = "";
        int nIdUO = 0;
        int nIdUOGestor = 0;
        int nTipoCoin = 0;
        int nIdTabelaFonte = 0;
        String strUODescricao = "";
        Date dataCriacao;      
        Date dataAutorizado;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");        
        String strDataCriacao = "";
        boolean bTemAnexos = false;
        boolean bAutorizado = false;
        boolean bGestorArquivado = false;
        boolean bArquivadoUO = false;
        boolean bArquivadoUOGestor = false;
        boolean bCoinReadOnly = false;
        
        obslistaTbCIPorAprovar = FXCollections.observableArrayList();
        
        consulta  = new MainWindowQueries();
        listaCiSemAprovar = consulta.getlistaTbComunicacaoInternaEnviados(this.nIdUnidadeOrganizacional);
        
        for(TbComunicacaoInterna l : listaCiSemAprovar){
            try {
            nIdCoin = l.getIdCoin();
            strAssunto = l.getCoinAssunto();
            strConteudo = l.getCoinConteudo();
            nIdUsuario = l.getIdUsuario();
            strUsuarioNomeCompleto = l.getUsuNomeCompleto();
            nIdUO = l.getIdUnidadeOrganizacional();
            strUODescricao = l.getUnorDescricao();
            nIdUOGestor = l.getIdUoGestor();
            bAutorizado = l.getCoinAutorizado();
            nTipoCoin = l.getIdTipoCoin().getIdTipoCoin();
            strApensamento = l.getCoinApensamento();            
            nSequencial = l.getCoinNumero();
            bArquivadoUO = l.getCoinUoArquivado();
            bArquivadoUOGestor = l.getCoinUoGestorArquivado();            
            dataCriacao = l.getCoinDataCriacao();
            strDataCriacao = df.format(dataCriacao);            
            dataAutorizado = l.getCoinDataAutorizado();
            bCoinReadOnly = l.getCoinReadOnly();
            bTemAnexos = l.getCoinTemAnexos();
            nIdTabelaFonte = 1;
            
            
//            obslistaTbCIPorAprovar.add(new TbCIPorAprovar(nIdCoin,strAssunto,strConteudo,nIdUsuario,strUsuarioNomeCompleto,
//                        nIdUO,strUODescricao,nSequencial,dataCriacao,strDataCriacao,bAutorizado,bTemAnexos,1));
                obslistaTbCIPorAprovar.add(new TbCIPorAprovar(nIdCoin, strAssunto, strConteudo, nIdUsuario, strUsuarioNomeCompleto, nIdUO, strUODescricao, 
                        nIdUOGestor, bAutorizado, nTipoCoin, strApensamento, nSequencial, bArquivadoUO, bArquivadoUOGestor, dataCriacao, strDataCriacao, 
                        dataAutorizado, bCoinReadOnly, bTemAnexos ,nIdTabelaFonte));
            } catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("ObservableList Error");
                alert.setContentText(e.toString());
                alert.showAndWait();
            }
            }
        //	ClDataEnvio; ClUORemitente; ClAutorRemitente; ClAssunto;
        ClIdCoin.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Integer>("intp_idCoin"));
        ClDataEnvio.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_dataCriacao"));        
        ClUORemitente.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUORemitente"));        
        ClAutorRemitente.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_UsuarioNomeCompleto"));        
        ClAssunto.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_Assunto"));
        TbViewGeral.setItems(obslistaTbCIPorAprovar);
        //TbViewGeral
        
        
        TbViewGeral.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
        @Override
        public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        //Check whether item is selected and set value of selected item to Label
            try{
                //TableView<TbCIPorAprovar> TbViewGeral = new TableView<>();
                if(TbViewGeral.getSelectionModel().getSelectedItem() != null){
                    //TbCIPorAprovar tbCiPorAprovar = TbViewGeral.getSelectionModel().getSelectedItem();
                    //TbCIPorAprovar tbtbCiPorAprovar = new TbCIPorAprovar();
                    TbCIPorAprovar tbCiPorAprovar = TbViewGeral.getSelectionModel().getSelectedItem();
                    int nCISequencial = 0;
                    int nlIdCI = 0;
                    boolean bTemAnexo = false;
                    String strDescricaoUO = "";
                    String strYear = "";
                    Date dataCriacao;
                    SimpleDateFormat df = new SimpleDateFormat("yyyy");
                    
                    nlIdCI = tbCiPorAprovar.getIntp_idCoin();
                    strDescricaoUO = tbCiPorAprovar.getStrp_DescricaoUORemitente();
                    nCISequencial = tbCiPorAprovar.getIntp_idCoinNumero();                    
                    dataCriacao = tbCiPorAprovar.getDataCriacao();
                    bTemAnexo = tbCiPorAprovar.getBoolp_CoinTemAnexos();
                    
                    strYear = df.format(dataCriacao);
                    
                    htmlEditorCI.setHtmlText(tbCiPorAprovar.getStrp_Conteudo());                    
                    lblNumeroSequencialCI.setText(strDescricaoUO + " " + String.format("%05d",nCISequencial)+"/" + strYear);
                    if (bTemAnexo){
                        PreencherTxtFAnexos(nlIdCI);
                    }
                    else {
                        txtFAnexos.getChildren().clear();
                    }
                }
            }catch (Exception e) {
                    e.printStackTrace();
                    //labelMessage.setText("Error on get row Data");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText("Erro na carga da TableView");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
            }
        }
    });
        
        
            
        
    }
    @FXML
    private void handleBtnCaixaPendencias(ActionEvent event) throws IOException {
        lblCaixa.setText("");
        lblCaixa.setText("Caixa de entrada (pendências)");
        setBotoesMainWindow(nTipoPerfil);
        clearTelas();
        PreencherCaixaEntrada(3);
        
    }
    @FXML
    private void handleBtnCaixaArquivadas(ActionEvent event) throws IOException {
        lblCaixa.setText("");
        lblCaixa.setText("Caixa de entrada (arquivadas)");
        setBotoesMainWindow(nTipoPerfil);
        clearTelas();
        PreencherCaixaEntrada(4);
        
    }
    private void clearTelas(){
        int nlTblViewGeralSize = 0;
        //Devemos fazer refresh da tableView
        nlTblViewGeralSize = TbViewGeral.getItems().size();
        if (nlTblViewGeralSize > 0){
            TbViewGeral.getItems().clear();
        }
        htmlEditorCI.setHtmlText("");
        txtFAnexos.getChildren().clear();
    }
    private void PreencherCaixaEntrada(int nlTipoPreenchimento){
        int nlIdCoinDestinatario = 0;
        TbComunicacaoInterna nlIdCoin;
        int nlIdUsuarioRemitente = 0;
        String strUsuarioNomeCompleto = "";
        int nlIdUORemitente = 0;
        String strDescricaoUORemitente = "";
        int nlIdUODestinatario = 0;
        String strDescricaoUODestinatario = "";
        int nlIdUOGestorDestinatario = 0;
        String strDescricaoUOGestorDestinatario = "";
        boolean bAutorizadoPeloGestor = false;
        boolean bArquivadoPeloUODestinatario = false;
        boolean bArquivadoPeloUOGestor = false;    
        String strAssunto = "";
        String strConteudo = "";
        boolean bPendentePeloUODestinatario = false;
        Date dataAutorizadoPeloGestorDestinatario;
        boolean bLidoPeloUODestinatario = false;
        Date dataCriacao;
        TbTipoEnvio nlIdTipoEnvio;  //1-Enviado "Para; 2-Enviado "Com cópia"; 3-Enviado "Com cópia oculta" 
        int nlIdCoinNumero = 0; //Número sequencial das CIs para cada UO
        boolean bReadOnlyUODestinatario = false;
        boolean bCoinTemAnexos = false;
        boolean bAutorizadoPeloGestorRemitente = false;

        String strDataCriacao = "";

        //A variavel intp_idTabelaFonte foi criada para saber qual a fonte de dados
        //por tanto, não existe fisicamente mapeado para tabela nenhuma
        int nlIdTabelaFonte = 2;
        
        //Variaveis de apoio
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");        
        int nIdCoin = 0;
        int nIdTipoEnvio = 0;
        
        //Iniciamos a criação da TableView
        List<TbCiDestinatario> listaCiDestinatario = new ArrayList<TbCiDestinatario>();
        ObservableList<TbCIPorAprovar> obslistaTbCaixaEntrada = FXCollections.observableArrayList();       
        
        consulta  = new MainWindowQueries();
        
        //Criamos a consulta a depender da variavel nlTipoPreenchimento
        //e também seteamos a visibilidade dos botões
        switch (nlTipoPreenchimento){
            case 1: //caixa de entrada (solicitando aprovação)
                btnAprovarCI.setDisable(false);
                //listaCiDestinatario = consulta.getlistaTbComunicacaoInternaEnviados(this.nIdUnidadeOrganizacional);
                break;
            case 2: //caixa de entrada
                btnAprovarCI.setDisable(true);
                listaCiDestinatario = consulta.getlistaCaixaEntrada(this.nIdUnidadeOrganizacional);
                break;
            case 3: //caixa de entrada (pendencias)
                listaCiDestinatario = consulta.getlistaCaixaEntradaPendencias(this.nIdUnidadeOrganizacional);
                break;
            case 4: //caixa de entrada (arquivadas)
                btnAprovarCI.setDisable(true);
                listaCiDestinatario = consulta.getlistaCaixaEntradaArquivados(this.nIdUnidadeOrganizacional);
                break;
            default:
                break;
        }
        
        for(TbCiDestinatario l : listaCiDestinatario){
            nlIdCoinDestinatario = l.getIdCoinDestinatario();
            nlIdCoin = l.getIdCoin();
            nIdCoin = nlIdCoin.getIdCoin();
            nlIdUsuarioRemitente = l.getIdUsuarioRemitente();
            strUsuarioNomeCompleto = l.getUsuNomeCompletoRemitente();
            nlIdUORemitente = l.getIdUoRemitente();
            strDescricaoUORemitente = l.getInorDescricaoRemitente();
            nlIdUODestinatario = l.getIdUoDestinatario();
            strDescricaoUODestinatario = l.getUnorDescricaoDestinatario();
            nlIdUOGestorDestinatario = l.getIdUoGestorDestinatario();
            strDescricaoUOGestorDestinatario = l.getUnorDescricaoGestorDestinatario();
            bAutorizadoPeloGestor = l.getCoinDestinatarioGestorAutorizado();
            bArquivadoPeloUODestinatario = l.getCoinDestinatarioUoArquivado();
            bArquivadoPeloUOGestor = l.getCoinDestinatarioUoGestorArquivado();    
            strAssunto = l.getCoinDestinatarioAssunto();
            strConteudo = l.getCoinDestinatarioConteudo();
            bPendentePeloUODestinatario = l.getCoinDestinatarioPendente();
            dataAutorizadoPeloGestorDestinatario = l.getCoinDestinatarioGestorDataAutorizado();
            bLidoPeloUODestinatario = l.getCoinDestinatarioLido();
            dataCriacao = l.getCoinDestinatarioDataCriacao();
            nlIdTipoEnvio = l.getIdTipoEnvio();  //1-Enviado "Para; 2-Enviado "Com cópia"; 3-Enviado "Com cópia oculta" 
            nIdTipoEnvio = nlIdTipoEnvio.getIdTipoEnvio();
            nlIdCoinNumero = l.getCoinDestinatarioNumero(); //Número sequencial das CIs para cada UO
            bReadOnlyUODestinatario = l.getCoinDestinatarioReadOnly();
            bCoinTemAnexos = l.getCoinDestinatarioTemAnexos();
            bAutorizadoPeloGestorRemitente = l.getCoinRemitenteGestorAutorizado();
            
            strDataCriacao = df.format(dataCriacao);
            obslistaTbCaixaEntrada.add(new TbCIPorAprovar(nlIdCoinDestinatario, nIdCoin, nlIdUsuarioRemitente, 
                    strUsuarioNomeCompleto, nlIdUORemitente, strDescricaoUORemitente,nlIdUODestinatario, 
                    strDescricaoUODestinatario, nlIdUOGestorDestinatario, strDescricaoUOGestorDestinatario, 
                    bAutorizadoPeloGestor, bArquivadoPeloUODestinatario, bArquivadoPeloUOGestor, strAssunto, 
                    strConteudo, bPendentePeloUODestinatario, dataAutorizadoPeloGestorDestinatario,
                    bLidoPeloUODestinatario, dataCriacao, nIdTipoEnvio, nlIdCoinNumero, bReadOnlyUODestinatario, 
                    bCoinTemAnexos, bAutorizadoPeloGestorRemitente, strDataCriacao, nlIdTabelaFonte));
            
            System.out.println();
        }
        ClIdCoin.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Integer>("intp_idCoin"));
        ClDataEnvio.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_dataCriacao"));        
        ClUORemitente.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUORemitente"));        
        ClAutorRemitente.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_UsuarioNomeCompleto"));        
        ClAssunto.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_Assunto"));
        TbViewGeral.setItems(obslistaTbCaixaEntrada);
        
        TbViewGeral.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
        @Override
        public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        //Check whether item is selected and set value of selected item to Label
            try{
                //TableView<TbCIPorAprovar> TbViewGeral = new TableView<>();
                if(TbViewGeral.getSelectionModel().getSelectedItem() != null){
                    //TbCIPorAprovar tbCiPorAprovar = TbViewGeral.getSelectionModel().getSelectedItem();
                    //TbCIPorAprovar tbtbCiPorAprovar = new TbCIPorAprovar();
                    TbCIPorAprovar tbCiPorAprovar = TbViewGeral.getSelectionModel().getSelectedItem();
                    int nCISequencial = 0;
                    int nlIdCI = 0;
                    boolean bTemAnexo = false;
                    String strDescricaoUO = "";
                    String strYear = "";
                    Date dataCriacao;
                    SimpleDateFormat df = new SimpleDateFormat("yyyy");
                    
                    nlIdCI = tbCiPorAprovar.getIntp_idCoin();
                    strDescricaoUO = tbCiPorAprovar.getStrp_DescricaoUORemitente();
                    nCISequencial = tbCiPorAprovar.getIntp_idCoinNumero();                    
                    dataCriacao = tbCiPorAprovar.getDataCriacao();
                    bTemAnexo = tbCiPorAprovar.getBoolp_CoinTemAnexos();
                    
                    strYear = df.format(dataCriacao);
                    
                    htmlEditorCI.setHtmlText(tbCiPorAprovar.getStrp_Conteudo());                    
                    lblNumeroSequencialCI.setText(strDescricaoUO + " " + String.format("%05d",nCISequencial)+"/" + strYear);
                    if (bTemAnexo){
                        PreencherTxtFAnexos(nlIdCI);
                    }
                    else {
                        txtFAnexos.getChildren().clear();
                    }
                }
            }catch (Exception e) {
                    e.printStackTrace();
                    //labelMessage.setText("Error on get row Data");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText("Erro na carga da TableView");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
            }
        }
    });
        
    }
}
