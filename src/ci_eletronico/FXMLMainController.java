/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico;

import ci_eletronico.entities.TbCIPorAprovar;
import ci_eletronico.entities.TbComunicacaoInterna;
import ci_eletronico_queries.MainWindowQueries;
import java.io.IOException;
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
import javafx.scene.layout.GridPane;
import javafx.scene.web.HTMLEditor;
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
        setBotoesMainWindow(nTipoPerfil);
    }
    
    @FXML
    private void handleBtnTrocarSenha(ActionEvent event) throws IOException{        
        setBotoesMainWindow(nTipoPerfil);
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
                System.out.println("OK button pressed");
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
        nTipoCI = 1;
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
                stage.showAndWait();
//                                
            }catch (IOException ex) {
                Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    @FXML
    private void handleBtnPendentesAprovacao(ActionEvent event) throws IOException {
        lblCaixa.setText("");
        lblCaixa.setText("CIs pendentes de aprovação");       
        
        setBotoesMainWindow(nTipoPerfil);
        
        btnEncaminharCI.setDisable(true);
        btnDesarquivarCI.setDisable(true);
        
        if ((this.nIdUnidadeOrganizacional == this.nIdUOGestor) &&(this.nTipoPerfil == 1)){
            btnAprovarCI.setDisable(false);
        }else{
            btnAprovarCI.setDisable(true);
        }
            
        
        
        
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
                    String strDescricaoUO = "";
                    String strYear = "";
                    Date dataCriacao;
                    SimpleDateFormat df = new SimpleDateFormat("yyyy");
                    
                    strDescricaoUO = tbCiPorAprovar.getStrp_DescricaoUORemitente();
                    nCISequencial = tbCiPorAprovar.getIntp_idCoinNumero();
                    
                    dataCriacao = tbCiPorAprovar.getDataCriacao();
                    
                    strYear = df.format(dataCriacao);
                    
                    htmlEditorCI.setHtmlText(tbCiPorAprovar.getStrp_Conteudo());
                    
                    lblNumeroSequencialCI.setText(strDescricaoUO + " " + String.format("%05d",nCISequencial)+"/" + strYear);
                }
            }catch (Exception e) {
                    e.printStackTrace();
                    //labelMessage.setText("Error on get row Data");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Tipo de Exame");
                    alert.setHeaderText("TableView");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
            }
        }
    });
        
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
    private void handleBtnCaixaEntrada(ActionEvent event) throws IOException {
        lblCaixa.setText("");
        lblCaixa.setText("Caixa de entrada");
        setBotoesMainWindow(nTipoPerfil);
        
    }
    @FXML
    private void handleBtnCaixaSaida(ActionEvent event) throws IOException {
        lblCaixa.setText("");
        lblCaixa.setText("Caixa de saida");
        setBotoesMainWindow(nTipoPerfil);
        
    }
    @FXML
    private void handleBtnCaixaPendencias(ActionEvent event) throws IOException {
        lblCaixa.setText("");
        lblCaixa.setText("Caixa de pendências");
        setBotoesMainWindow(nTipoPerfil);
        
    }
    @FXML
    private void handleBtnCaixaArquivadas(ActionEvent event) throws IOException {
        lblCaixa.setText("");
        lblCaixa.setText("Caixa de CIs arquivadas");
        setBotoesMainWindow(nTipoPerfil);
        
    }
}
