/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico;

import ci_eletronico.entities.TbAnexo;
import ci_eletronico.entities.TbCIPorAprovar;
import ci_eletronico.entities.TbCiDestinatario;
import ci_eletronico.entities.TbComunicacaoInterna;
import ci_eletronico.entities.TbTipoEnvio;
import ci_eletronico.entities.TbUsuario;
import ci_eletronico_queries.MainWindowQueries;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementHandler;
import com.itextpdf.tool.xml.Writable;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.pipeline.WritableElement;
import com.itextpdf.text.Document;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.tool.xml.ElementList;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
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
import javafx.geometry.Pos;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



/**
 *
 * @author victorcmaf
 */
public class FXMLMainController implements Initializable {
//public class FXMLMainController {
    
    public static final String HEADER =
    //"<table width=\"100%\" border=\"0\"><tr><td>Header</td><td align=\"right\">Some title</td></tr></table>";
    "<table width=\"100%\" border=\"0\"><tr><td><img src=\"resources\\logo_prefeitura_salvador.png\" alt=\"\"/></td><td align=\"right\">Some title</td></tr></table>";
    public String FOOTER = "";
    //"<table width=\"100%\" border=\"0\"><tr><td>Footer</td><td align=\"right\">Some title</td></tr></table>";

    
    
  
    
    public class MyFooter extends PdfPageEventHelper {
    protected ElementList header;
    protected ElementList footer;
    
    protected Chunk chunk;
    protected Chunk chunk2;
    
//  
    
    public MyFooter() throws IOException {
        header = XMLWorkerHelper.parseToElementList(HEADER, null);
        footer = XMLWorkerHelper.parseToElementList(FOOTER, null);
    }
    public MyFooter(String strCIAssinatura) throws IOException{
        FOOTER = "<table width=\"100%\" border=\"0\"><tr><td>Assinatura digital: <font size=\"3\">"+ strCIAssinatura +"</font></td><td align=\"right\"> </td></tr></table>";
        header = XMLWorkerHelper.parseToElementList(HEADER, null);
        footer = XMLWorkerHelper.parseToElementList(FOOTER, null);
        
        
    }
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        try {
            com.itextpdf.text.Image logo;       
            //logo = com.itextpdf.text.Image.getInstance("http://172.22.8.18//arquivos//ci_instalador/logo_transalvador_preto_50.png");
            logo = com.itextpdf.text.Image.getInstance("http://172.22.8.18//arquivos//ci_instalador/logo.jpg");
            float width = logo.getScaledWidth();
            float height = logo.getScaledHeight();
            
            logo.setAlignment(logo.ALIGN_LEFT);
            logo.scaleAbsoluteHeight(20);
            logo.scaleAbsoluteWidth(20);
            logo.scalePercent(100);
            
            
//            com.itextpdf.text.Image logo2 = com.itextpdf.text.Image.getInstance("src\\resources\\logo_pms_preto.png");
//            
//            float width2 = logo2.getScaledWidth();
//            float height2 = logo2.getScaledHeight();
//            
//            logo2.setAlignment(logo2.ALIGN_RIGHT);
//            logo2.scaleAbsoluteHeight(20);
//            logo2.scaleAbsoluteWidth(20);
//            logo2.scalePercent(50);
            //Chunk chunk = new Chunk(logo, 0, -45);
            chunk = new Chunk(logo, 0, -45);
            //chunk2 = new Chunk(logo2, 100, -45);
        }catch (IOException | DocumentException e){
            System.out.println();            
        }
        
        try{    
            ColumnText ct = new ColumnText(writer.getDirectContent());
            ct.setSimpleColumn(new Rectangle(6, 832, 559, 810));
            
            ct.addElement(chunk);
//            for (Element e : header) {
//                //ct.addElement(e);
//                ct.addElement(chunk);
//                //ct.addElement(chunk2);
//            }
            ct.go();
            ct.setSimpleColumn(new Rectangle(36, 10, 559, 32));
            for (Element e : footer) {
                ct.addElement(e);
            }
            ct.go();
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
}
    
//    class MyFooter extends PdfPageEventHelper {
//        com.itextpdf.text.Font ffont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.UNDEFINED, 5, com.itextpdf.text.Font.ITALIC);
//
//        public void onEndPage(PdfWriter writer, Document document) {
//        PdfContentByte cb = writer.getDirectContent();
//        Phrase header = new Phrase("this is a header", ffont);
//        Phrase footer = new Phrase("this is a footer", ffont);
//        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
//            header,
//            (document.right() - document.left()) / 2 + document.leftMargin(),
//            document.top() + 10, 0);
//        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
//            footer,
//            (document.right() - document.left()) / 2 + document.leftMargin(),
//            document.bottom() - 10, 0);
//        }
//   }
    
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
    private Button btnCaixaEnviadosArquivados;
    @FXML
    private Button btnEditarCI;
    @FXML
    private Button btnMarcarcomoPendencia;
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
    private TableColumn ClUODestinatario;
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
    @FXML
    private Label lblNomeUOGestor;
               
    // Clases para tratar Anexar Arquivos
    private Desktop desktop = Desktop.getDesktop();
    
    //Tabela a ser utilizada para atualizar status das CIs (aprovadas, arquivadas, desarquivadas)
    // 0 ==> Sem tabela definida
    // 1 ==> TB_COMUNICACAO_INTERNA
    // 2 ==> TB_CI_DESTINATARIO
    int ngTabela = 0;
    
    //1-caixa de recebidas (solicitando aprovação)
    //2-caixa de recebidas
    //3-caixa de recebidas (pendencias)
    //4-caixa de recebidas (arquivadas)
    //5-Caixa de enviados (arquivadas)
    //6-Caixa de enviados
    //7-Caixa de enviados (solicitando aprovação)
    int ngBotao = 0;
    
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
    private String strgUserLogin = "";
    
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
        
        //Labels ocultados na versão de Produção
        this.lblIdPerfil.setVisible(false);
        this.lblIdUsuario.setVisible(false);
        this.lblIdUO.setVisible(false);
        this.lblUOGestora.setVisible(false);     
        
        
        
    
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
//            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
        });
    }
    
    public void setVariaveisAmbiente(final LoginController loginController , String strIdUsuario, String strNomeUsuario, 
                                        String strIdUO, String strNomeUO, String strIdPerfil, String strDescricaoPerfil, String strHtmlAssinatura, 
                                        int nIdUOGestor, String strlUserLogin, String strlUOGestorDescricao) {
        lblIdUsuario.setText(strIdUsuario);
        lblNomeUsuario.setText(strNomeUsuario);
        lblIdUO.setText(strIdUO);
        lblNomeUO.setText(strNomeUO);  
        lblIdPerfil.setText(strIdPerfil);
        lblNomePerfil.setText(strDescricaoPerfil);
        lblUOGestora.setText(Integer.toString(nIdUOGestor));
        lblNomeUOGestor.setText(strlUOGestorDescricao);
        
        this.strHtmlAssinatura = strHtmlAssinatura;
        nTipoPerfil = Integer.parseInt(strIdPerfil);
        this.nIdUOGestor = nIdUOGestor;
        //System.out.print("Tipo de Perfil metodo setVariaveisAmbiente = " + nTipoPerfil);
        nIdUnidadeOrganizacional = Integer.parseInt(strIdUO);
        nIdUsuarioLogado = Integer.parseInt(strIdUsuario);
        this.strgUserLogin = strlUserLogin;
        
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
                
                btnEditarCI.setVisible(false);
                btnMarcarcomoPendencia.setDisable(true);
                
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
                btnEditarCI.setDisable(true);
                
                btnEditarCI.setVisible(false);
                btnMarcarcomoPendencia.setDisable(true);
                
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
                
                btnEditarCI.setVisible(false);
                btnMarcarcomoPendencia.setDisable(true);
                
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
        String strHtmlConteudo = "";
        strIdUsuario = lblIdUsuario.getText();
        strNomeUsuario = lblNomeUsuario.getText();
        strIdUO = lblIdUO.getText();
        strNomeUO = lblNomeUO.getText();  
        strIdPerfil = lblIdPerfil.getText();
        strDescricaoPerfil = lblNomePerfil.getText();
        nTipoCI = 1;    //CI Normal
        
        //Variaveis utilizadas nas CIs encaminhadas para não perder número de CI
        //criada e quem foi o Remitente inicial (serve para saber quais anexos acompanham o despacho também)
        int nlIdCoinGenesis = 0;
        int nlIdUnorGenesis = 0;
        int nlCoinNumeroGenesis = 0;
        String strCoinHistoricoAnexos = "";
        String strUnorDescricaoGenesis = "";
        
        ShowNovaCIe(this , strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, strDescricaoPerfil, strHtmlAssinatura, nTipoCI, nIdUOGestor, strHtmlConteudo,
                nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
                strgUserLogin);
                        
    }
    @FXML
    private void handleBtnNovaCICircular(ActionEvent event) throws IOException {
        String strHtmlConteudo = "";
        strIdUsuario = lblIdUsuario.getText();
        strNomeUsuario = lblNomeUsuario.getText();
        strIdUO = lblIdUO.getText();
        strNomeUO = lblNomeUO.getText();  
        strIdPerfil = lblIdPerfil.getText();
        strDescricaoPerfil = lblNomePerfil.getText();
        nTipoCI = 2;    //CI Circular
        
        //Variaveis utilizadas nas CIs encaminhadas para não perder número de CI
        //criada e quem foi o Remitente inicial (serve para saber quais anexos acompanham o despacho também)
        int nlIdCoinGenesis = 0;
        int nlIdUnorGenesis = 0;
        int nlCoinNumeroGenesis = 0;
        String strCoinHistoricoAnexos = "";
        String strUnorDescricaoGenesis = "";
        
        ShowNovaCIe(this , strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, strDescricaoPerfil, strHtmlAssinatura, nTipoCI, nIdUOGestor, strHtmlConteudo,
                nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
                strgUserLogin);
        
    }
    @FXML
    private void handleBtnNovaCIConfidencial(ActionEvent event) throws IOException {
        String strHtmlConteudo = "";
        strIdUsuario = lblIdUsuario.getText();
        strNomeUsuario = lblNomeUsuario.getText();
        strIdUO = lblIdUO.getText();
        strNomeUO = lblNomeUO.getText();  
        strIdPerfil = lblIdPerfil.getText();
        strDescricaoPerfil = lblNomePerfil.getText();
        nTipoCI = 3;    //CI Confidencial
        
        //Variaveis utilizadas nas CIs encaminhadas para não perder número de CI
        //criada e quem foi o Remitente inicial (serve para saber quais anexos acompanham o despacho também)
        int nlIdCoinGenesis = 0;
        int nlIdUnorGenesis = 0;
        int nlCoinNumeroGenesis = 0;
        String strCoinHistoricoAnexos = "";
        String strUnorDescricaoGenesis = "";
        
        ShowNovaCIe(this , strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, strDescricaoPerfil, strHtmlAssinatura, nTipoCI, nIdUOGestor, strHtmlConteudo,
                nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
                strgUserLogin);
        
    }
    @FXML
    private void handleBtnCIEncaminhar(ActionEvent event) throws IOException{
        
        if (null == TbViewGeral.getSelectionModel().getSelectedItem()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("CI não foi selecionado.");
            alert.setContentText("Favor selecionar uma CI da tabela");
            alert.showAndWait();
        }else{
            String strHtmlConteudo = "";
            String strlAssinatura = "";
            strHtmlConteudo = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_Conteudo();
            strlAssinatura = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_CoinAssinatura();
            strIdUsuario = lblIdUsuario.getText();
            strNomeUsuario = lblNomeUsuario.getText();
            strIdUO = lblIdUO.getText();
            strNomeUO = lblNomeUO.getText();  
            strIdPerfil = lblIdPerfil.getText();
            strDescricaoPerfil = lblNomePerfil.getText();
            nTipoCI = 4;    //CI Encaminhada
            
            //Variaveis utilizadas nas CIs encaminhadas para não perder número de CI
            //criada e quem foi o Remitente inicial (serve para saber quais anexos acompanham o despacho também)
            int nlIdCoinGenesis = 0;
            int nlIdUnorGenesis = 0;
            int nlCoinNumeroGenesis = 0;
            String strCoinHistoricoAnexos = "";
            String strUnorDescricaoGenesis = "";
            
            nlIdCoinGenesis = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoinGenesis();
            nlIdUnorGenesis = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idUnorGenesis();
            nlCoinNumeroGenesis = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_CoinNumeroGenesis();
            strUnorDescricaoGenesis = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_UnorDescricaoGenesis();
            //strCoinHistoricoAnexos = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_CoinHistoricoAnexos();
            
            //Verificamos se CI a ser encaminhada possui anexos
            if(txtFAnexos.getChildren().size() > 0){
                ObservableList<Node> nodes = txtFAnexos.getChildren();
                StringBuilder sb = new StringBuilder();
                for (Node node : nodes) { sb.append((((Text)node).getText()));}
                strCoinHistoricoAnexos = strCoinHistoricoAnexos.concat(sb.toString());
            }
            ShowNovaCIe(this , strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, strDescricaoPerfil, strHtmlAssinatura, nTipoCI, nIdUOGestor, strHtmlConteudo,
            nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
            strlAssinatura);
        }
    }
    public void ShowNovaCIe(final FXMLMainController mainController , String strIdUsuario, String strNomeUsuario, String strIdUO, String strNomeUO, String strIdPerfil, 
            String strDescricaoPerfil, String strHtmlAssinatura, int nTipoCI, int nIdUOGestor, String strHtmlConteudo,
            int nlIdCoinGenesis, int nlIdUnorGenesis, int nlCoinNumeroGenesis, String strCoinHistoricoAnexos, String strUnorDescricaoGenesis,
            String strlUserLogin){
        try{
                scene = new Scene(new SplitPane());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ci_eletronico/nova_ci/NovaCI.fxml"));
                scene.setRoot((Parent) loader.load());
                
                ci_eletronico.nova_ci.NovaCIController nova_ci_controller = loader.<ci_eletronico.nova_ci.NovaCIController>getController();     
                nova_ci_controller.setVariaveisAmbienteNovaCI(mainController, strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, strDescricaoPerfil, 
                        strHtmlAssinatura, nTipoCI, nIdUOGestor, strHtmlConteudo,
                        nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
                        strlUserLogin);
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
        lblCaixa.setText("Caixa de recebidas (solicitando aprovação)");       
        
        setBotoesMainWindow(nTipoPerfil);
        
        btnEncaminharCI.setDisable(true);
        btnDesarquivarCI.setDisable(true);
        
        if ((this.nIdUnidadeOrganizacional == this.nIdUOGestor) &&(this.nTipoPerfil == 1)){
            btnAprovarCI.setDisable(false);
        }else{
            btnAprovarCI.setDisable(true);
        }          
        clearTelas();
        PreencherCaixaEntrada(1);
        
    }
    @FXML
    private void handleBtnPendentesAprovacao(ActionEvent event) throws IOException {
        
        lblCaixa.setText("");
        lblCaixa.setText("Caixa de enviados (solicitando aprovação)");       
        
        setBotoesMainWindow(nTipoPerfil);
        
       
        btnEncaminharCI.setDisable(true);
        btnDesarquivarCI.setDisable(true);
        
        btnEditarCI.setVisible(true);
        btnMarcarcomoPendencia.setDisable(true);
        
        if ((this.nIdUnidadeOrganizacional == this.nIdUOGestor) &&(this.nTipoPerfil == 1)){
            btnAprovarCI.setDisable(false);
        }else{
            btnAprovarCI.setDisable(true);
        }
        
        clearTelas();
        
        ngTabela = 1; // TB_COMUNICACAO_INTERNA
        ngBotao = 7;
        
        //Tipo de perfil
        int nlTipoPerfil = 0;
        nlTipoPerfil = this.nTipoPerfil;
        
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
        
        //Variaveis utilizadas nas CIs encaminhadas para não perder número de CI
        //criada e quem foi o Remitente inicial (serve para saber quais anexos acompanham o despacho também)
        int nlIdCoinGenesis = 0;
        int nlIdUnorGenesis = 0;
        int nlCoinNumeroGenesis = 0;
        String strCoinHistoricoAnexos = "";
        String strUnorDescricaoGenesis = "";
        //-------------------------------------------
        
        String strlDescricaoUODestinatario = "";
        
        //Assinatura MD5
        String strlAssinatura = "";
        
        obslistaTbCIPorAprovar = FXCollections.observableArrayList();
        
        consulta  = new MainWindowQueries();
        
        //Deve-se mostrar as Cis para aprovação só para usuário que faz parte da UO Gestora
        if (this.nIdUOGestor == this.nIdUnidadeOrganizacional){
            if (1 == nlTipoPerfil){ //Perfil Gestor
                listaCiSemAprovar = consulta.getlistaTbComunicacaoInternaPorAprovar(this.nIdUOGestor);
            } else {
                listaCiSemAprovar = consulta.getlistaTbComunicacaoInternaPorAprovarPerfil2(this.nIdUOGestor);
            }

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
                    
                    nlIdCoinGenesis = l.getIdCoinGenesis();
                    nlIdUnorGenesis = l.getIdUnorGenesis();
                    nlCoinNumeroGenesis = l.getCoinNumeroGenesis();
                    strCoinHistoricoAnexos = l.getCoinHistoricoAnexos();
                    strUnorDescricaoGenesis = l.getUnorDescricaoGenesis();
                    
                    strlAssinatura = l.getCoinAssinatura();


        //            obslistaTbCIPorAprovar.add(new TbCIPorAprovar(nIdCoin,strAssunto,strConteudo,nIdUsuario,strUsuarioNomeCompleto,
        //                        nIdUO,strUODescricao,nSequencial,dataCriacao,strDataCriacao,bAutorizado,bTemAnexos,1));
                        obslistaTbCIPorAprovar.add(new TbCIPorAprovar(nIdCoin, strAssunto, strConteudo, nIdUsuario, strUsuarioNomeCompleto, nIdUO, strUODescricao, 
                                nIdUOGestor, bAutorizado, nTipoCoin, strApensamento, nSequencial, bArquivadoUO, bArquivadoUOGestor, dataCriacao, strDataCriacao, 
                                dataAutorizado, bCoinReadOnly, bTemAnexos ,nIdTabelaFonte,
                                nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
                                strlDescricaoUODestinatario,
                                strlAssinatura));
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
                ClUODestinatario.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUODestinatario"));
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
                            
                            String strCoinHistoricoAnexos = "";
                            
                            String strDescricaoUO = "";
                            String strYear = "";
                            Date dataCriacao;
                            SimpleDateFormat df = new SimpleDateFormat("yyyy");

                            nlIdCI = tbCiPorAprovar.getIntp_idCoin();
                            //strDescricaoUO = tbCiPorAprovar.getStrp_DescricaoUORemitente();
                            strDescricaoUO = tbCiPorAprovar.getStrp_UnorDescricaoGenesis();
                            nCISequencial = tbCiPorAprovar.getIntp_idCoinNumero();                    
                            dataCriacao = tbCiPorAprovar.getDataCriacao();
                            bTemAnexo = tbCiPorAprovar.getBoolp_CoinTemAnexos();
                            strCoinHistoricoAnexos = tbCiPorAprovar.getStrp_CoinHistoricoAnexos();

                            strYear = df.format(dataCriacao);

                            htmlEditorCI.setHtmlText(tbCiPorAprovar.getStrp_Conteudo());                    
                            lblNumeroSequencialCI.setText(strDescricaoUO + "-" + String.format("%05d",nCISequencial)+"-" + strYear);
                            if ((bTemAnexo) || (strCoinHistoricoAnexos.length()>0) ){
                                PreencherTxtFAnexos(nlIdCI,strCoinHistoricoAnexos);
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
    private void PreencherTxtFAnexos(int nlIdCI, String strCoinHistoricoAnexos){
        txtFAnexos.getChildren().clear();
        Text txtArquivoSelecionado;
        String strDelimiters = ";";
        
        if (nlIdCI > 0) {
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
        }
        if (strCoinHistoricoAnexos.length()>0){
            String[] strParts = strCoinHistoricoAnexos.split(strDelimiters);
            for (int i = 0; i < strParts.length; i++){
                txtArquivoSelecionado = new Text();                                          
                txtArquivoSelecionado.setText(strParts[i]);
                txtArquivoSelecionado.setFill(Color.BLACK);
                txtArquivoSelecionado.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
                txtFAnexos.getChildren().add(txtArquivoSelecionado);                                
            }            
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
        if (null == TbViewGeral.getSelectionModel().getSelectedItem()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("CI não foi selecionado.");
            alert.setContentText("Favor selecionar uma CI da tabela");
            alert.showAndWait();
        }else{
            int nIdCI = 0;            
            int nBotao = 0;
            int nTabela = 0;
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Aprovação");
            alert.setHeaderText(null);
            alert.setContentText("Deseja realmente aprovar CI?"); 

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                //nIdCI = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoin();
                nBotao = this.ngBotao;
                nTabela = this.ngTabela;
                
                // Aprovamos CI
                //Tabela a ser utilizada para atualizar status das CIs (aprovadas, arquivadas, desarquivadas)
                // 0 ==> Sem tabela definida
                // 1 ==> TB_COMUNICACAO_INTERNA
                // 2 ==> TB_CI_DESTINATARIO
                switch (nTabela){
                    case 1:
                        nIdCI = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoin();
                        AprovarCI(nIdCI, 1);
                        //TableViewRefresh(nBotao);
                        break;
                    case 2:
                        nIdCI = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoinDestinatario();
                        AprovarCI(nIdCI, 2);                        
                        //TableViewRefresh(nBotao);
                        break;
                        
                    default:
                        break;
                }
                
            } else {
                // ... user chose CANCEL or closed the dialog 
            }     
        }
    }
    private void AprovarCI(int nlIdCI, int nlTabela){
        boolean bUpdate = false;
        boolean bAprovado = true;
        
        //Valores dos botões 
        //1-caixa de recebidas (solicitando aprovação) - btnCaixaEntradaSolicitandoAprovacao
        //2-caixa de recebidas - btnCaixaEntrada
        //3-caixa de recebidas (pendencias) - btnCaixaPendencias
        //4-caixa de recebidas (arquivadas) - btnCaixaArquivadas
        //5-Caixa de enviados (arquivadas) - btnCaixaEnviadosArquivados
        //6-Caixa de enviados - btnCaixaSaida;
        //7-Caixa de enviados (solicitando aprovação) - btnPendentesAprovacao
        int nlButtonSelected = 0;
        nlButtonSelected = ngBotao;
        
        consulta  = new MainWindowQueries();  
        EntityManager em;
        EntityManagerFactory emf;
        TbComunicacaoInterna nTbComunicacaoInternaIdCoin= new TbComunicacaoInterna(nlIdCI);
        //Tabela a ser utilizada para atualizar status das CIs (aprovadas, arquivadas, desarquivadas)
        // 0 ==> Sem tabela definida
        // 1 ==> TB_COMUNICACAO_INTERNA
        // 2 ==> TB_CI_DESTINATARIO
        switch (nlTabela){
            case 1:
                try{                    
                    bUpdate = consulta.AprovarCIEnviada(nlIdCI);
                } catch (Exception e){
                    e.printStackTrace();
                }    
                
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
                        l.setCoinRemitenteGestorAutorizado(bAprovado);                        
                        em.merge(l);
                    }
                    em.getTransaction().commit();            
                    em.close();
                    emf.close();
                    
                }catch(javax.persistence.PersistenceException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Aprovar CI ");
                    alert.setHeaderText("Tabela TB_CI_DESTINATARIO");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();                                            
                }
                break;
            case 2:
                try{
                    bUpdate = consulta.AprovarCIRecebida(nlIdCI);                    
                }catch(Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Aprovar CI ");
                    alert.setHeaderText("Tabela TB_CI_DESTINATARIO");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();                                            
                }
                break;
            default:
                break;
        }
        if (bUpdate){
//                        System.out.println("IdUsuario = " + nIdUserUO);
//                        System.out.println("Update deve acontecer");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("CI foi aprovada com sucesso.");
            alert.showAndWait();    
            
            //Refresh da TableView
            switch(nlButtonSelected){
                case 1:
                    btnCaixaEntradaSolicitandoAprovacao.fire();
                    break;
                case 2:
                    btnCaixaEntrada.fire();
                    break;
                case 3:
                    btnCaixaPendencias.fire();
                    break;
                case 4:
                    btnCaixaArquivadas.fire();
                    break;
                case 5:
                    btnCaixaEnviadosArquivados.fire();
                    break;
                case 6:
                    btnCaixaSaida.fire();
                    break;
                case 7:
                    btnPendentesAprovacao.fire();
                    break;
                default:
                    break;                
            } 
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Não foi possível aprovar CI");
            alert.setContentText("Favor contatar o Administrador do sistema");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleBtnEditarCI(ActionEvent event) throws IOException{
        if (null == TbViewGeral.getSelectionModel().getSelectedItem()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("CI não foi selecionado.");
            alert.setContentText("Favor selecionar uma CI da tabela");
            alert.showAndWait();
        }else{            
                // do something
            int nlIdCI = 0;
            String strHtmlUpdateCI = "";
            
            nlIdCI = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoin();
            strHtmlUpdateCI = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_Conteudo();
            UpdateCI(this,nlIdCI,strHtmlUpdateCI);
            //Valores dos botões 
            //1-caixa de recebidas (solicitando aprovação) - btnCaixaEntradaSolicitandoAprovacao
            //2-caixa de recebidas - btnCaixaEntrada
            //3-caixa de recebidas (pendencias) - btnCaixaPendencias
            //4-caixa de recebidas (arquivadas) - btnCaixaArquivadas
            //5-Caixa de enviados (arquivadas) - btnCaixaEnviadosArquivados
            //6-Caixa de enviados - btnCaixaSaida;
            //7-Caixa de enviados (solicitando aprovação) - btnPendentesAprovacao
            int nlButtonSelected = 0;
            nlButtonSelected = ngBotao;
            //Refresh da TableView
            
            switch(nlButtonSelected){
                case 1:
                    btnCaixaEntradaSolicitandoAprovacao.fire();
                    break;
                case 2:
                    btnCaixaEntrada.fire();
                    break;
                case 3:
                    btnCaixaPendencias.fire();
                    break;
                case 4:
                    btnCaixaArquivadas.fire();
                    break;
                case 5:
                    btnCaixaEnviadosArquivados.fire();
                    break;
                case 6:
                    btnCaixaSaida.fire();
                    break;
                case 7:
                    btnPendentesAprovacao.fire();
                    break;
                default:
                    break;                
            } 
        }
    }
    public void UpdateCI(FXMLMainController mainController, int nlIdCI, String strHtmlUpdateCI){
        try{
            scene = new Scene(new SplitPane());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ci_eletronico/fxml_utilitarios/UpdateCI.fxml"));
            scene.setRoot((Parent) loader.load());

            ci_eletronico.fxml_utilitarios.UpdateCIController Update_ci_controller = loader.<ci_eletronico.fxml_utilitarios.UpdateCIController>getController();     
            Update_ci_controller.setVariaveisAmbienteUpdateCI(mainController, nlIdCI, strHtmlUpdateCI);                

            Stage stage = new Stage();
            stage.setTitle("Atualizar CI-eletrônico");
            //set icon
            stage.getIcons().add(new Image("/resources/repeat_green.png"));

            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);     //Window Parent fica inativo
            stage.showAndWait();
//                                
        }catch (IOException ex) {
            Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void handleBtnMarcarcomoPendencia(ActionEvent event) throws IOException{
        if (null == TbViewGeral.getSelectionModel().getSelectedItem()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("CI não foi selecionado.");
            alert.setContentText("Favor selecionar uma CI da tabela");
            alert.showAndWait();
        }else{
            int nIdCI = 0;            
            int nBotao = 0;
            int nTabela = 0;
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar");
            alert.setHeaderText(null);
            alert.setContentText("Deseja realmente marcar como pendência?"); 

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // do something
                nBotao = this.ngBotao;
                nTabela = this.ngTabela;
                
                // Aprovamos CI
                //Tabela a ser utilizada para atualizar status das CIs (aprovadas, arquivadas, desarquivadas)
                // 0 ==> Sem tabela definida
                // 1 ==> TB_COMUNICACAO_INTERNA
                // 2 ==> TB_CI_DESTINATARIO
                switch (nTabela){
                    case 1: // 1 ==> TB_COMUNICACAO_INTERNA
                        nIdCI = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoin();
                        MarcarComoPendente(nIdCI, 1);
                        //TableViewRefresh(nBotao);
                        break;
                    case 2: // 2 ==> TB_CI_DESTINATARIO
                        nIdCI = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoinDestinatario();
                        MarcarComoPendente(nIdCI, 2);                        
                        //TableViewRefresh(nBotao);
                        break;
                        
                    default:
                        break;
                }
            }
            else{
                //...do nothing
            } 
        }
    }
        
    @FXML
    private void handleBtnArquivarCI(ActionEvent event) throws IOException {
        if (null == TbViewGeral.getSelectionModel().getSelectedItem()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("CI não foi selecionado.");
            alert.setContentText("Favor selecionar uma CI da tabela");
            alert.showAndWait();
        }else{
            int nIdCI = 0;            
            int nBotao = 0;
            int nTabela = 0;
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Arquivamento");
            alert.setHeaderText(null);
            alert.setContentText("Deseja realmente arquivar CI?"); 

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                //nIdCI = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoin();
                nBotao = this.ngBotao;
                nTabela = this.ngTabela;
                
                // Aprovamos CI
                //Tabela a ser utilizada para atualizar status das CIs (aprovadas, arquivadas, desarquivadas)
                // 0 ==> Sem tabela definida
                // 1 ==> TB_COMUNICACAO_INTERNA
                // 2 ==> TB_CI_DESTINATARIO
                switch (nTabela){
                    case 1: // 1 ==> TB_COMUNICACAO_INTERNA
                        nIdCI = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoin();
                        ArquivarCI(nIdCI, 1);
                        //TableViewRefresh(nBotao);
                        break;
                    case 2: // 2 ==> TB_CI_DESTINATARIO
                        nIdCI = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoinDestinatario();
                        ArquivarCI(nIdCI, 2);                        
                        //TableViewRefresh(nBotao);
                        break;
                        
                    default:
                        break;
                }
            } else {
                // ... user chose CANCEL or closed the dialog 
            }     
        }
    }
    private void DesarquivarCI(int nlIdCI, int nlTabela){
        boolean bUpdate = false;        
        
        //Valores dos botões 
        //1-caixa de recebidas (solicitando aprovação) - btnCaixaEntradaSolicitandoAprovacao
        //2-caixa de recebidas - btnCaixaEntrada
        //3-caixa de recebidas (pendencias) - btnCaixaPendencias
        //4-caixa de recebidas (arquivadas) - btnCaixaArquivadas
        //5-Caixa de enviados (arquivadas) - btnCaixaEnviadosArquivados
        //6-Caixa de enviados - btnCaixaSaida;
        //7-Caixa de enviados (solicitando aprovação) - btnPendentesAprovacao
        int nlButtonSelected = 0;
        nlButtonSelected = ngBotao;
        
        consulta  = new MainWindowQueries();          
        //Tabela a ser utilizada para atualizar status das CIs (aprovadas, arquivadas, desarquivadas)
        // 0 ==> Sem tabela definida
        // 1 ==> TB_COMUNICACAO_INTERNA
        // 2 ==> TB_CI_DESTINATARIO
        switch (nlTabela){
            case 1:
                try{                    
                    bUpdate = consulta.DesarquivarCIEnviada(nlIdCI);
                } catch (Exception e){
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Desarquivar CI ");
                    alert.setHeaderText("Tabela TB_COMUNICACAO_INTERNA");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();                                            
                }    
                break;
            case 2:
                try{
                    bUpdate = consulta.DesarquivarCIRecebida(nlIdCI);                    
                }catch(Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Desarquivar CI ");
                    alert.setHeaderText("Tabela TB_CI_DESTINATARIO");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();                                            
                }
                break;
            default:
                break;
        }
        if (bUpdate){
//                        System.out.println("IdUsuario = " + nIdUserUO);
//                        System.out.println("Update deve acontecer");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("CI foi desarquivada com sucesso.");
            alert.showAndWait();    
            
            //Refresh da TableView
            switch(nlButtonSelected){
                case 1:
                    btnCaixaEntradaSolicitandoAprovacao.fire();
                    break;
                case 2:
                    btnCaixaEntrada.fire();
                    break;
                case 3:
                    btnCaixaPendencias.fire();
                    break;
                case 4:
                    btnCaixaArquivadas.fire();
                    break;
                case 5:
                    btnCaixaEnviadosArquivados.fire();
                    break;
                case 6:
                    btnCaixaSaida.fire();
                    break;
                case 7:
                    btnPendentesAprovacao.fire();
                    break;
                default:
                    break;                
            } 
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Não foi possível desarquivar CI");
            alert.setContentText("Favor contatar o Administrador do sistema");
            alert.showAndWait();
        }
        
    }
    private void ArquivarCI(int nlIdCI, int nlTabela){
        boolean bUpdate = false;        
        
        //Valores dos botões 
        //1-caixa de recebidas (solicitando aprovação) - btnCaixaEntradaSolicitandoAprovacao
        //2-caixa de recebidas - btnCaixaEntrada
        //3-caixa de recebidas (pendencias) - btnCaixaPendencias
        //4-caixa de recebidas (arquivadas) - btnCaixaArquivadas
        //5-Caixa de enviados (arquivadas) - btnCaixaEnviadosArquivados
        //6-Caixa de enviados - btnCaixaSaida;
        //7-Caixa de enviados (solicitando aprovação) - btnPendentesAprovacao
        int nlButtonSelected = 0;
        nlButtonSelected = ngBotao;
        
        consulta  = new MainWindowQueries();          
        //Tabela a ser utilizada para atualizar status das CIs (aprovadas, arquivadas, desarquivadas)
        // 0 ==> Sem tabela definida
        // 1 ==> TB_COMUNICACAO_INTERNA
        // 2 ==> TB_CI_DESTINATARIO
        switch (nlTabela){
            case 1:
                try{                    
                    bUpdate = consulta.ArquivarCIEnviada(nlIdCI, nlButtonSelected);
                } catch (Exception e){
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Arquivar CI ");
                    alert.setHeaderText("Tabela TB_COMUNICACAO_INTERNA");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();                                            
                }    
                break;
            case 2:
                try{
                    bUpdate = consulta.ArquivarCIRecebida(nlIdCI, nlButtonSelected);                    
                }catch(Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Arquivar CI ");
                    alert.setHeaderText("Tabela TB_CI_DESTINATARIO");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();                                            
                }
                break;
            default:
                break;
        }
        if (bUpdate){
//                        System.out.println("IdUsuario = " + nIdUserUO);
//                        System.out.println("Update deve acontecer");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("CI foi arquivada com sucesso.");
            alert.showAndWait();    
            
            //Refresh da TableView
            switch(nlButtonSelected){
                case 1:
                    btnCaixaEntradaSolicitandoAprovacao.fire();
                    break;
                case 2:
                    btnCaixaEntrada.fire();
                    break;
                case 3:
                    btnCaixaPendencias.fire();
                    break;
                case 4:
                    btnCaixaArquivadas.fire();
                    break;
                case 5:
                    btnCaixaEnviadosArquivados.fire();
                    break;
                case 6:
                    btnCaixaSaida.fire();
                    break;
                case 7:
                    btnPendentesAprovacao.fire();
                    break;
                default:
                    break;                
            } 
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Não foi possível arquivar CI");
            alert.setContentText("Favor contatar o Administrador do sistema");
            alert.showAndWait();
        }
        
    }
    
     private void MarcarComoPendente(int nlIdCI, int nlTabela){
        boolean bUpdate = false;        
        
        //Valores dos botões 
        //1-caixa de recebidas (solicitando aprovação) - btnCaixaEntradaSolicitandoAprovacao
        //2-caixa de recebidas - btnCaixaEntrada
        //3-caixa de recebidas (pendencias) - btnCaixaPendencias
        //4-caixa de recebidas (arquivadas) - btnCaixaArquivadas
        //5-Caixa de enviados (arquivadas) - btnCaixaEnviadosArquivados
        //6-Caixa de enviados - btnCaixaSaida;
        //7-Caixa de enviados (solicitando aprovação) - btnPendentesAprovacao
        int nlButtonSelected = 0;
        nlButtonSelected = ngBotao;
        
        consulta  = new MainWindowQueries();          
        //Tabela a ser utilizada para atualizar status das CIs (aprovadas, arquivadas, desarquivadas)
        // 0 ==> Sem tabela definida
        // 1 ==> TB_COMUNICACAO_INTERNA
        // 2 ==> TB_CI_DESTINATARIO
        switch (nlTabela){
            case 1:
                //Tabela TB_COMUNICACAO_INTERNA não possui campo para setear 
                // registro como marcar como pendencia
//                try{                    
//                    bUpdate = consulta.ArquivarCIEnviada(nlIdCI, nlButtonSelected);
//                } catch (Exception e){
//                    e.printStackTrace();
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("CI - Marcar como pendência");
//                    alert.setHeaderText("Tabela TB_COMUNICACAO_INTERNA");
//                    alert.setContentText(e.getMessage());
//                    alert.showAndWait();                                            
//                }    
                break;
            case 2:
                try{
                    bUpdate = consulta.MarcarComoPendenciaCIRecebida(nlIdCI, nlButtonSelected);                    
                }catch(Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("CI - Marcar como pendência");
                    alert.setHeaderText("Tabela TB_CI_DESTINATARIO");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();                                            
                }
                break;
            default:
                break;
        }
        if (bUpdate){
//                        System.out.println("IdUsuario = " + nIdUserUO);
//                        System.out.println("Update deve acontecer");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("CI foi marcada como pendência com sucesso.");
            alert.showAndWait();    
            
            //Refresh da TableView
            switch(nlButtonSelected){
                case 1:
                    btnCaixaEntradaSolicitandoAprovacao.fire();
                    break;
                case 2:
                    btnCaixaEntrada.fire();
                    break;
                case 3:
                    btnCaixaPendencias.fire();
                    break;
                case 4:
                    btnCaixaArquivadas.fire();
                    break;
                case 5:
                    btnCaixaEnviadosArquivados.fire();
                    break;
                case 6:
                    btnCaixaSaida.fire();
                    break;
                case 7:
                    btnPendentesAprovacao.fire();
                    break;
                default:
                    break;                
            } 
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Não foi possível marcar como pendência");
            alert.setContentText("Favor contatar o Administrador do sistema");
            alert.showAndWait();
        }
        
    }
    @FXML
    private void handleBtnDesarquivarCI(ActionEvent event) throws IOException {
        if (null == TbViewGeral.getSelectionModel().getSelectedItem()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("CI não foi selecionado.");
            alert.setContentText("Favor selecionar uma CI da tabela");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Desarquivamento");
            alert.setHeaderText(null);
            alert.setContentText("Deseja realmente desarquivar CI?"); 

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK 
                int nIdCI = 0;            
                int nBotao = 0;
                int nTabela = 0;
                
                nBotao = this.ngBotao;
                nTabela = this.ngTabela;
                
                // Aprovamos CI
                //Tabela a ser utilizada para atualizar status das CIs (aprovadas, arquivadas, desarquivadas)
                // 0 ==> Sem tabela definida
                // 1 ==> TB_COMUNICACAO_INTERNA
                // 2 ==> TB_CI_DESTINATARIO
                switch (nTabela){
                    case 1: // 1 ==> TB_COMUNICACAO_INTERNA
                        nIdCI = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoin();
                        DesarquivarCI(nIdCI, 1);
                        //TableViewRefresh(nBotao);
                        break;
                    case 2: // 2 ==> TB_CI_DESTINATARIO
                        nIdCI = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoinDestinatario();
                        DesarquivarCI(nIdCI, 2);                        
                        //TableViewRefresh(nBotao);
                        break;
                        
                    default:
                        break;
                }
            } else {
                // ... user chose CANCEL or closed the dialog 
            } 
        }
    }
    @FXML
    private void handleBtnImprimirCI(ActionEvent event) throws IOException {
        if (null == TbViewGeral.getSelectionModel().getSelectedItem()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("CI não foi selecionado.");
            alert.setContentText("Favor selecionar uma CI da tabela");
            alert.showAndWait();
        }else{
            
 
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Informação");
//            alert.setHeaderText("Imprimir CI");
//            alert.setContentText("Funcionalidade será disponibilizada em breve");
//            alert.showAndWait();
//            try{
//            com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance("resources\\logo_prefeitura_salvador.png");
//            logo.setAlignment(logo.ALIGN_LEFT);
//            logo.scaleAbsoluteHeight(20);
//            logo.scaleAbsoluteWidth(20);
//            logo.scalePercent(100);
//            Chunk chunk = new Chunk(logo, 0, -45);
//            com.itextpdf.text.Header header = new com.itextpdf.text.Header(new Phrase(chunk), false); 
//            } catch (IOException | DocumentException e){
//                
//            }
            File outfile = null; // variavel para abrir documento pdf após sua criação
            
            String strFileName = this.lblNumeroSequencialCI.getText();
            String strUserHome = System.getProperty("user.home") + "\\Downloads\\" + strFileName + ".pdf";
            final Document document = new Document();
            try {
                document.setPageSize(PageSize.A4);
                document.setMargins(10f, 10f, 70f, 40f);
                String strCIAssinatura = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_CoinAssinatura();
                
                //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("c:/Temp/teste.pdf"));
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(strUserHome));

                MyFooter evento = new MyFooter(strCIAssinatura);
                writer.setPageEvent(evento);
            
            } catch (IOException | DocumentException e) {
                System.out.println(e.toString());
                System.out.println(e.getMessage());
            }
            document.open();

            //String htmlString = htmlEditorCI.getHtmlText();
            String strlCITitulo = "";
            strlCITitulo = "<br /><p align=\"center\"><b>" + strFileName + "</b></p>";
            String htmlString = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_Conteudo();
            //String strCIAssinatura = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_CoinAssinatura();
           
            
//            htmlString = htmlString.replace("<br>", "\n");
//            htmlString = htmlString.replace("<br/>", "\n");
//            htmlString = htmlString.replace("<br />", "\n");
            
            htmlString = htmlString.replace("<br>", "<br />");

//            htmlString = htmlString.replace("<hr>", "<p></p>");
//            htmlString = htmlString.replace("<hr/>", "<p></p>");
//            htmlString = htmlString.replace("<hr />", "<p></p>");
            
            htmlString = htmlString.replace("<hr>", "<hr />");
            
            htmlString = strlCITitulo.concat(htmlString);
            
            StringReader in = new StringReader(htmlString);

            try {
                final Paragraph test = new Paragraph();
                XMLWorkerHelper.getInstance().parseXHtml(new ElementHandler() {
                    @Override
                    public void add(final Writable w) {
                        if (w instanceof WritableElement) {
                            List<Element> elements = ((WritableElement) w).elements();
                            for (Element e : elements) {
                                test.add(e);
                            }
                        }
                    }
                }, in);

                document.add(test);
            } catch (IOException | DocumentException e) {
                System.out.println(e.toString());
                System.out.println(e.getMessage());
            }

            document.close(); 
            
            outfile = new File(strUserHome);
            
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Informação");
//            alert.setHeaderText("Imprimir CI: " + strFileName );
//            alert.setContentText("Arquivo pdf criado com sucesso na pasta Downloads");
//            alert.showAndWait();
            
            openArquivo(outfile); // Abrimos o pdf criado para ser visualizado
        }
    }
    
    @FXML
    private void handleBtnCaixaEntrada(ActionEvent event) throws IOException {
        lblCaixa.setText("");
        lblCaixa.setText("Caixa de recebidas");
                
        setBotoesMainWindow(nTipoPerfil);
        
        btnAprovarCI.setDisable(true);
        btnEncaminharCI.setDisable(false);
        btnArquivarCI.setDisable(false);
        btnDesarquivarCI.setDisable(true); 
        
        btnEditarCI.setVisible(false);
        btnMarcarcomoPendencia.setDisable(false);
        
        clearTelas();
         
        PreencherCaixaEntrada(2);
        
    }
    @FXML
    private void handleBtnCaixaSaida(ActionEvent event) throws IOException {
        lblCaixa.setText("");
        lblCaixa.setText("Caixa de enviados");
        setBotoesMainWindow(nTipoPerfil);       
        
        btnAprovarCI.setDisable(true);
        btnEncaminharCI.setDisable(true);
        btnArquivarCI.setDisable(false);
        btnDesarquivarCI.setDisable(true);  
        
        clearTelas();
        
        ngTabela = 1; // TB_COMUNICACAO_INTERNA
        ngBotao = 6;
        
        int nlTipoPreenchimento = 0;
        nlTipoPreenchimento = this.ngBotao;
        
        PreencherCaixaSaida(nlTipoPreenchimento);
        
//        int nIdCoin = 0;
//        int nSequencial = 0;
//        String strAssunto = "";
//        String strConteudo = "";
//        int nIdUsuario = 0;
//        String strUsuarioNomeCompleto = "";
//        String strApensamento = "";
//        int nIdUO = 0;
//        int nIdUOGestor = 0;
//        int nTipoCoin = 0;
//        int nIdTabelaFonte = 0;
//        String strUODescricao = "";
//        Date dataCriacao;      
//        Date dataAutorizado;
//        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");        
//        String strDataCriacao = "";
//        boolean bTemAnexos = false;
//        boolean bAutorizado = false;
//        boolean bGestorArquivado = false;
//        boolean bArquivadoUO = false;
//        boolean bArquivadoUOGestor = false;
//        boolean bCoinReadOnly = false;
//        
//        //Variaveis utilizadas nas CIs encaminhadas para não perder número de CI
//        //criada e quem foi o Remitente inicial (serve para saber quais anexos acompanham o despacho também)
//        int nlIdCoinGenesis = 0;
//        int nlIdUnorGenesis = 0;
//        int nlCoinNumeroGenesis = 0;
//        String strCoinHistoricoAnexos = "";
//        String strUnorDescricaoGenesis = "";
//        //----------------------------------------------------------
//        
//        obslistaTbCIPorAprovar = FXCollections.observableArrayList();
//        
//        consulta  = new MainWindowQueries();
//        listaCiSemAprovar = consulta.getlistaTbComunicacaoInternaEnviados(this.nIdUnidadeOrganizacional);
//        
//        for(TbComunicacaoInterna l : listaCiSemAprovar){
//            try {
//            nIdCoin = l.getIdCoin();
//            strAssunto = l.getCoinAssunto();
//            strConteudo = l.getCoinConteudo();
//            nIdUsuario = l.getIdUsuario();
//            strUsuarioNomeCompleto = l.getUsuNomeCompleto();
//            nIdUO = l.getIdUnidadeOrganizacional();
//            strUODescricao = l.getUnorDescricao();
//            nIdUOGestor = l.getIdUoGestor();
//            bAutorizado = l.getCoinAutorizado();
//            nTipoCoin = l.getIdTipoCoin().getIdTipoCoin();
//            strApensamento = l.getCoinApensamento();            
//            nSequencial = l.getCoinNumero();
//            bArquivadoUO = l.getCoinUoArquivado();
//            bArquivadoUOGestor = l.getCoinUoGestorArquivado();            
//            dataCriacao = l.getCoinDataCriacao();
//            strDataCriacao = df.format(dataCriacao);            
//            dataAutorizado = l.getCoinDataAutorizado();
//            bCoinReadOnly = l.getCoinReadOnly();
//            bTemAnexos = l.getCoinTemAnexos();
//            nIdTabelaFonte = 1;
//            
//            nlIdCoinGenesis = l.getIdCoinGenesis();
//            nlIdUnorGenesis = l.getIdUnorGenesis();
//            nlCoinNumeroGenesis = l.getCoinNumeroGenesis();
//            strCoinHistoricoAnexos = l.getCoinHistoricoAnexos();
//            strUnorDescricaoGenesis = l.getUnorDescricaoGenesis();           
//            
//            
//            
////            obslistaTbCIPorAprovar.add(new TbCIPorAprovar(nIdCoin,strAssunto,strConteudo,nIdUsuario,strUsuarioNomeCompleto,
////                        nIdUO,strUODescricao,nSequencial,dataCriacao,strDataCriacao,bAutorizado,bTemAnexos,1));
//                obslistaTbCIPorAprovar.add(new TbCIPorAprovar(nIdCoin, strAssunto, strConteudo, nIdUsuario, strUsuarioNomeCompleto, nIdUO, strUODescricao, 
//                        nIdUOGestor, bAutorizado, nTipoCoin, strApensamento, nSequencial, bArquivadoUO, bArquivadoUOGestor, dataCriacao, strDataCriacao, 
//                        dataAutorizado, bCoinReadOnly, bTemAnexos ,nIdTabelaFonte,
//                        nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis));
//            } catch (Exception e){
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error");
//                alert.setHeaderText("ObservableList Error");
//                alert.setContentText(e.toString());
//                alert.showAndWait();
//            }
//            }
//        //	ClDataEnvio; ClUORemitente; ClAutorRemitente; ClAssunto;
//        ClIdCoin.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Integer>("intp_idCoin"));
//        ClDataEnvio.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_dataCriacao"));        
//        ClUORemitente.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUORemitente"));        
//        ClAutorRemitente.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_UsuarioNomeCompleto"));        
//        ClAssunto.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_Assunto"));
//        TbViewGeral.setItems(obslistaTbCIPorAprovar);
//        //TbViewGeral
//        
//        
//        TbViewGeral.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
//        @Override
//        public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
//        //Check whether item is selected and set value of selected item to Label
//            try{
//                //TableView<TbCIPorAprovar> TbViewGeral = new TableView<>();
//                if(TbViewGeral.getSelectionModel().getSelectedItem() != null){
//                    //TbCIPorAprovar tbCiPorAprovar = TbViewGeral.getSelectionModel().getSelectedItem();
//                    //TbCIPorAprovar tbtbCiPorAprovar = new TbCIPorAprovar();
//                    TbCIPorAprovar tbCiPorAprovar = TbViewGeral.getSelectionModel().getSelectedItem();
//                    int nCISequencial = 0;
//                    int nlIdCI = 0;
//                    int nlIdTipoCoin = 0;
//                    boolean bTemAnexo = false;
//                    
//                    String strCoinHistoricoAnexos = "";
//                    
//                    String strDescricaoUO = "";
//                    String strYear = "";
//                    Date dataCriacao;
//                    SimpleDateFormat df = new SimpleDateFormat("yyyy");
//                    
//                    nlIdCI = tbCiPorAprovar.getIntp_idCoin();
//                    nlIdTipoCoin = tbCiPorAprovar.getIntp_idTipoCoin();
//                    //strDescricaoUO = tbCiPorAprovar.getStrp_DescricaoUORemitente();
//                    strDescricaoUO = tbCiPorAprovar.getStrp_UnorDescricaoGenesis();
//                    if (4 == nlIdTipoCoin){
//                        nCISequencial = tbCiPorAprovar.getIntp_CoinNumeroGenesis();
//                    }else {
//                        nCISequencial = tbCiPorAprovar.getIntp_idCoinNumero();
//                    }
//                    
//                    dataCriacao = tbCiPorAprovar.getDataCriacao();
//                    bTemAnexo = tbCiPorAprovar.getBoolp_CoinTemAnexos();
//                    strCoinHistoricoAnexos = tbCiPorAprovar.getStrp_CoinHistoricoAnexos();
//                    
//                    strYear = df.format(dataCriacao);
//                    
//                    htmlEditorCI.setHtmlText(tbCiPorAprovar.getStrp_Conteudo());                    
//                    lblNumeroSequencialCI.setText(strDescricaoUO + " " + String.format("%05d",nCISequencial)+"/" + strYear);
//                    if ((bTemAnexo) || (strCoinHistoricoAnexos.length()>0) ){
//                        PreencherTxtFAnexos(nlIdCI,strCoinHistoricoAnexos);
//                    } 
//                    else {
//                        txtFAnexos.getChildren().clear();
//                    }
//                }
//            }catch (Exception e) {
//                    e.printStackTrace();
//                    //labelMessage.setText("Error on get row Data");
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Erro");
//                    alert.setHeaderText("Erro na carga da TableView");
//                    alert.setContentText(e.getMessage());
//                    alert.showAndWait();
//            }
//        }
//    }); 
    }
    private void PreencherCaixaSaida(int nlTipoPreenchimento){
                
        ngTabela = 1; // TB_COMUNICACAO_INTERNA
        
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
        
        //Variaveis utilizadas nas CIs encaminhadas para não perder número de CI
        //criada e quem foi o Remitente inicial (serve para saber quais anexos acompanham o despacho também)
        int nlIdCoinGenesis = 0;
        int nlIdUnorGenesis = 0;
        int nlCoinNumeroGenesis = 0;
        String strCoinHistoricoAnexos = "";
        String strUnorDescricaoGenesis = "";
        //----------------------------------------------------------
        
        String strlDescricaoUODestinatario = "";
        
        //Assinatura MD5
        String strlAssinatura = "";
        
        
        //Tipo de perfil
        int nlTipoPerfil = 0;
        nlTipoPerfil = this.nTipoPerfil;
        
        obslistaTbCIPorAprovar = FXCollections.observableArrayList();
        
        //Iniciamos a criação da TableView
        List<TbComunicacaoInterna> listaComunicacaoInterna = new ArrayList<TbComunicacaoInterna>();
        ObservableList<TbCIPorAprovar> obslistaTbCaixaSaida = FXCollections.observableArrayList(); 
        
        consulta  = new MainWindowQueries();
        
        //Método utilizado para preencher TableView: private void PreencherCaixaEntrada(int nlTipoPreenchimento)
        //Método utilizado para preencher TableView: private void PreencherCaixaSaida(int nlTipoPreenchimento)
        //Valores dos botões:
        //1-caixa de recebidas (solicitando aprovação) - btnCaixaEntradaPendentesAprovacao
        //2-caixa de recebidas - btnCaixaEntrada
        //3-caixa de recebidas (pendencias) - btnCaixaPendencias
        //4-caixa de recebidas (arquivadas) - btnCaixaArquivadas
        //5-Caixa de enviados (arquivadas) - btnCaixaEnviadosArquivados
        //6-Caixa de enviados - btnCaixaSaida;
        //7-Caixa de enviados (solicitando aprovação) - btnPendentesAprovacao
        switch (nlTipoPreenchimento){
            case 6: //6-Caixa de enviados
                ngBotao = 6;
                ngTabela = 1; // TB_COMUNICACAO_INTERNA
                if (1 == nlTipoPerfil){ //Perfil Gestor
                    listaComunicacaoInterna = consulta.getlistaTbComunicacaoInternaEnviados(this.nIdUnidadeOrganizacional);                    
                } else {
                    listaComunicacaoInterna = consulta.getlistaTbComunicacaoInternaEnviadosPerfil2(this.nIdUnidadeOrganizacional);
                }
                break;
            case 5: //5-Caixa de enviados (arquivadas)
                ngBotao = 5;
                ngTabela = 1; // TB_COMUNICACAO_INTERNA
                if (1 == nlTipoPerfil){ //Perfil Gestor
                    listaComunicacaoInterna = consulta.getlistaCaixaSaidaArquivados(this.nIdUnidadeOrganizacional);
                } else {
                    listaComunicacaoInterna = consulta.getlistaCaixaSaidaArquivadosPerfil2(this.nIdUnidadeOrganizacional);
                }
                break;                
        }        
        
        for(TbComunicacaoInterna l : listaComunicacaoInterna){
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
            
            nlIdCoinGenesis = l.getIdCoinGenesis();
            nlIdUnorGenesis = l.getIdUnorGenesis();
            nlCoinNumeroGenesis = l.getCoinNumeroGenesis();
            strCoinHistoricoAnexos = l.getCoinHistoricoAnexos();
            strUnorDescricaoGenesis = l.getUnorDescricaoGenesis();  
            
            strlAssinatura = l.getCoinAssinatura();
            
            
            
//            obslistaTbCIPorAprovar.add(new TbCIPorAprovar(nIdCoin,strAssunto,strConteudo,nIdUsuario,strUsuarioNomeCompleto,
//                        nIdUO,strUODescricao,nSequencial,dataCriacao,strDataCriacao,bAutorizado,bTemAnexos,1));
                obslistaTbCaixaSaida.add(new TbCIPorAprovar(nIdCoin, strAssunto, strConteudo, nIdUsuario, strUsuarioNomeCompleto, nIdUO, strUODescricao, 
                        nIdUOGestor, bAutorizado, nTipoCoin, strApensamento, nSequencial, bArquivadoUO, bArquivadoUOGestor, dataCriacao, strDataCriacao, 
                        dataAutorizado, bCoinReadOnly, bTemAnexos ,nIdTabelaFonte,
                        nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
                        strlDescricaoUODestinatario,
                        strlAssinatura));
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
        ClUODestinatario.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUODestinatario"));
        ClDataEnvio.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_dataCriacao"));        
        ClUORemitente.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUORemitente"));        
        ClAutorRemitente.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_UsuarioNomeCompleto"));        
        ClAssunto.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_Assunto"));
        TbViewGeral.setItems(obslistaTbCaixaSaida);
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
                    int nlIdTipoCoin = 0;
                    boolean bTemAnexo = false;
                    
                    String strCoinHistoricoAnexos = "";
                    
                    String strDescricaoUO = "";
                    String strYear = "";
                    Date dataCriacao;
                    SimpleDateFormat df = new SimpleDateFormat("yyyy");
                    
                    nlIdCI = tbCiPorAprovar.getIntp_idCoin();
                    nlIdTipoCoin = tbCiPorAprovar.getIntp_idTipoCoin();
                    //strDescricaoUO = tbCiPorAprovar.getStrp_DescricaoUORemitente();
                    strDescricaoUO = tbCiPorAprovar.getStrp_UnorDescricaoGenesis();
                    if (4 == nlIdTipoCoin){
                        nCISequencial = tbCiPorAprovar.getIntp_CoinNumeroGenesis();
                    }else {
                        nCISequencial = tbCiPorAprovar.getIntp_idCoinNumero();
                    }
                    
                    dataCriacao = tbCiPorAprovar.getDataCriacao();
                    bTemAnexo = tbCiPorAprovar.getBoolp_CoinTemAnexos();
                    strCoinHistoricoAnexos = tbCiPorAprovar.getStrp_CoinHistoricoAnexos();
                    
                    strYear = df.format(dataCriacao);
                    
                    htmlEditorCI.setHtmlText(tbCiPorAprovar.getStrp_Conteudo());                    
                    lblNumeroSequencialCI.setText(strDescricaoUO + "-" + String.format("%05d",nCISequencial)+"-" + strYear);
                    if ((bTemAnexo) || (strCoinHistoricoAnexos.length()>0) ){
                        PreencherTxtFAnexos(nlIdCI,strCoinHistoricoAnexos);
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
        lblCaixa.setText("Caixa de recebidas (pendências)");
        setBotoesMainWindow(nTipoPerfil);
        
        btnDesarquivarCI.setDisable(true);
        
        btnEditarCI.setVisible(false);
        btnMarcarcomoPendencia.setDisable(true);
        
        clearTelas();
        PreencherCaixaEntrada(3);
        
    }
    @FXML
    private void handleBtnCaixaArquivadas(ActionEvent event) throws IOException {
        lblCaixa.setText("");
        lblCaixa.setText("Caixa de recebidas (arquivadas)");
        setBotoesMainWindow(nTipoPerfil);
        clearTelas();
        btnArquivarCI.setDisable(true);
        btnAprovarCI.setDisable(true);
        btnEncaminharCI.setDisable(true);
        PreencherCaixaEntrada(4);
        
    }
    @FXML
    private void handleBtnCaixaEnviadasArquivadas(ActionEvent event) throws IOException {
        lblCaixa.setText("");
        lblCaixa.setText("Caixa de enviados (arquivadas)");
        setBotoesMainWindow(nTipoPerfil);
        clearTelas();
        btnArquivarCI.setDisable(true);
        btnAprovarCI.setDisable(true);
        btnEncaminharCI.setDisable(true);
        
        ngTabela = 1; // TB_COMUNICACAO_INTERNA
        ngBotao = 5;
        
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Informação");
//        alert.setHeaderText("Caixa de enviados (arquivar)");
//        alert.setContentText("Funcionalidade será disponibilizada em breve");
//        alert.showAndWait();
        
        
        //PreencherCaixaEntrada(5);        
        int nlTipoPreenchimento = 0;
        nlTipoPreenchimento = this.ngBotao;
        
        PreencherCaixaSaida(nlTipoPreenchimento);
        
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
        //TbTipoComunicacoInterna tbTipoCoin;
        int nlTipoCoin = 0;
        int nlIdCoinNumero = 0; //Número sequencial das CIs para cada UO
        boolean bReadOnlyUODestinatario = false;
        boolean bCoinTemAnexos = false;
        boolean bAutorizadoPeloGestorRemitente = false;

        String strDataCriacao = "";

        //A variavel intp_idTabelaFonte foi criada para saber qual a fonte de dados
        //por tanto, não existe fisicamente mapeado para tabela nenhuma
        int nlIdTabelaFonte = 2;
        
        //Variaveis utilizadas nas CIs encaminhadas para não perder número de CI
        //criada e quem foi o Remitente inicial (serve para saber quais anexos acompanham o despacho também)
        int nlIdCoinGenesis = 0;
        int nlIdUnorGenesis = 0;
        int nlCoinNumeroGenesis = 0;
        String strCoinHistoricoAnexos = "";
        String strUnorDescricaoGenesis = "";
        
        //Variaveis de apoio
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");        
        int nIdCoin = 0;
        int nIdTipoEnvio = 0;
        
        //Tipo de perfil
        int nlTipoPerfil = 0;
        nlTipoPerfil = this.nTipoPerfil;
        
        //Assinatura MD5
        String strlAssinatura = "";
        
        //Iniciamos a criação da TableView
        List<TbCiDestinatario> listaCiDestinatario = new ArrayList<TbCiDestinatario>();
        ObservableList<TbCIPorAprovar> obslistaTbCaixaEntrada = FXCollections.observableArrayList();       
        
        consulta  = new MainWindowQueries();
        
        //Criamos a consulta a depender da variavel nlTipoPreenchimento
        //e também seteamos a visibilidade dos botões
        switch (nlTipoPreenchimento){
            case 1: //caixa de recebidas (solicitando aprovação)
                ngBotao = 1;
                ngTabela = 2; // TB_CI_DESTINATARIO
                btnAprovarCI.setDisable(false);
                if (1 == nlTipoPerfil){ //Perfil Gestor
                    listaCiDestinatario = consulta.getlistaCaixaEntradaSolicitandoAprovacao(this.nIdUnidadeOrganizacional);
                } else {
                    listaCiDestinatario = consulta.getlistaCaixaEntradaSolicitandoAprovacaoPerfil2(this.nIdUnidadeOrganizacional);                    
                }
                
                break;
            case 2: //caixa de recebidas
                ngBotao = 2;
                ngTabela = 2; // TB_CI_DESTINATARIO
                btnAprovarCI.setDisable(true);
                if (1 == nlTipoPerfil){ //Perfil Gestor
                    listaCiDestinatario = consulta.getlistaCaixaEntrada(this.nIdUnidadeOrganizacional);
                } else {
                    listaCiDestinatario = consulta.getlistaCaixaEntradaPerfil2(this.nIdUnidadeOrganizacional);
                }
                break;
            case 3: //caixa de recebidas (pendencias)
                ngBotao = 3;
                ngTabela = 2; // TB_CI_DESTINATARIO
                if (1 == nlTipoPerfil){ //Perfil Gestor
                    listaCiDestinatario = consulta.getlistaCaixaEntradaPendencias(this.nIdUnidadeOrganizacional);
                } else {
                    listaCiDestinatario = consulta.getlistaCaixaEntradaPendenciasPerfil2(this.nIdUnidadeOrganizacional);
                }
                break;
            case 4: //caixa de recebidas (arquivadas)
                ngBotao = 4;
                ngTabela = 2; // TB_CI_DESTINATARIO
                btnAprovarCI.setDisable(true);
//                if ((this.nIdUnidadeOrganizacional == this.nIdUOGestor) &&(this.nTipoPerfil == 1)){
//                    listaCiDestinatario = consulta.getlistaGestorCaixaEntradaArquivados(this.nIdUnidadeOrganizacional);
//                } else {
                if (1 == nlTipoPerfil){ //Perfil Gestor
                    listaCiDestinatario = consulta.getlistaCaixaEntradaArquivados(this.nIdUnidadeOrganizacional);
                } else {
                    listaCiDestinatario = consulta.getlistaCaixaEntradaArquivadosPerfil2(this.nIdUnidadeOrganizacional);
                }
//                }
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
            
            nlIdCoinGenesis = l.getIdCoinGenesis();
            nlIdUnorGenesis = l.getIdUnorGenesis();
            nlCoinNumeroGenesis = l.getCoinNumeroGenesis();
            strCoinHistoricoAnexos = l.getCoinHistoricoAnexos();
            strUnorDescricaoGenesis = l.getUnorDescricaoGenesis();
            
            nlTipoCoin = l.getIdTipoCoin().getIdTipoCoin();
            
            strlAssinatura = l.getCoinAssinatura();
            
            strDataCriacao = df.format(dataCriacao);
            obslistaTbCaixaEntrada.add(new TbCIPorAprovar(nlIdCoinDestinatario, nIdCoin, nlIdUsuarioRemitente, 
                    strUsuarioNomeCompleto, nlIdUORemitente, strDescricaoUORemitente,nlIdUODestinatario, 
                    strDescricaoUODestinatario, nlIdUOGestorDestinatario, strDescricaoUOGestorDestinatario, 
                    bAutorizadoPeloGestor, bArquivadoPeloUODestinatario, bArquivadoPeloUOGestor, strAssunto, 
                    strConteudo, bPendentePeloUODestinatario, dataAutorizadoPeloGestorDestinatario,
                    bLidoPeloUODestinatario, dataCriacao, nIdTipoEnvio, nlIdCoinNumero, bReadOnlyUODestinatario, 
                    bCoinTemAnexos, bAutorizadoPeloGestorRemitente, strDataCriacao, nlIdTabelaFonte,
                    nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
                    nlTipoCoin, strlAssinatura));
            
            System.out.println();
        }
        ClIdCoin.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Integer>("intp_idCoin"));
        ClUODestinatario.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUODestinatario"));
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
                    
                    String strCoinHistoricoAnexos = "";
                    
                    String strDescricaoUO = "";
                    String strYear = "";
                    Date dataCriacao;
                    SimpleDateFormat df = new SimpleDateFormat("yyyy");                    
                  
                    
                    nlIdCI = tbCiPorAprovar.getIntp_idCoin();
                    
                    strDescricaoUO = tbCiPorAprovar.getStrp_UnorDescricaoGenesis();
                    nCISequencial = tbCiPorAprovar.getIntp_CoinNumeroGenesis();                    
                    dataCriacao = tbCiPorAprovar.getDataCriacao();
                    bTemAnexo = tbCiPorAprovar.getBoolp_CoinTemAnexos();
                    
                    strCoinHistoricoAnexos = tbCiPorAprovar.getStrp_CoinHistoricoAnexos();
                    
                    strYear = df.format(dataCriacao);
                    
                    htmlEditorCI.setHtmlText(tbCiPorAprovar.getStrp_Conteudo());                    
                    lblNumeroSequencialCI.setText(strDescricaoUO + "-" + String.format("%05d",nCISequencial)+"-" + strYear);
                    if ((bTemAnexo) || (strCoinHistoricoAnexos.length()>0) ){
                        PreencherTxtFAnexos(nlIdCI,strCoinHistoricoAnexos);
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
