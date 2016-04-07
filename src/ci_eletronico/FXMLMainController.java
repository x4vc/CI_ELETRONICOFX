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
import ci_eletronico.entities.TbTipoComunicacoInterna;
import ci_eletronico.entities.TbTipoEnvio;
import ci_eletronico.entities.TbUnidadeOrganizacional;
import ci_eletronico.entities.TbUnidadeOrganizacionalGestor;
import ci_eletronico.entities.TbUsuario;
import static ci_eletronico.nova_ci.NovaCIController.ltrim;
import static ci_eletronico.nova_ci.NovaCIController.rtrim;
import ci_eletronico_queries.MainWindowQueries;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.util.Callback;
import javafx.util.Pair;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


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
    private Label lblRecordCount;
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
    private Button btnResponderCI;
    @FXML
    private Button btnHistoricoCI;
    
    @FXML    
    private TableView<TbCIPorAprovar> TbViewGeral2;
    @FXML
    private TableColumn ClIdCoin2;
    @FXML
    private TableColumn ClICiSequencial2;
    @FXML
    private TableColumn ClUODestinatario2;
    @FXML
    private TableColumn ClDataEnvio2; 
    @FXML
    private TableColumn ClUORemitente2; 
    @FXML
    private TableColumn ClAutorRemitente2; 
    @FXML
    private TableColumn ClAssunto2; 
    @FXML
    private TableColumn ClAprovado;
    
    @FXML
    //private TableView TbViewGeral;
    private TableView<TbCIPorAprovar> TbViewGeral;
    @FXML
    private TableColumn ClIdCoin;
    @FXML
    private TableColumn ClICiSequencial;
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
    private TableColumn ClLido;
    
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
    @FXML
    private Button btnMarcarComoLido;
    @FXML
    private Label lblAguardandoAprovacao;
    @FXML
    private Button btnDesaprovarCI;
    @FXML
    private TitledPane tPaneRecebidas;
    @FXML
    private TitledPane tPaneEnviadas;
    @FXML
    private TitledPane tPaneArquivadas;
    @FXML
    private Accordion accordionCaixa;
    @FXML
    private ScrollPane scrollPaneTextFlow;
    @FXML
    private Button btnSalvarTodosArquivos;
    
    @FXML
    private ListView<Choice> lviewAnexos;
               
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
    
     private Hyperlink linkArquivoSelecionado = new Hyperlink() ;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//    public void initialize() {
        // TODO 
    //System.out.print("Tipo de Perfil metodo initialize = " + nTipoPerfil);
        
        //Labels e botões ocultados na versão de Produção
        this.lblIdPerfil.setVisible(false);
        this.lblIdUsuario.setVisible(false);
        this.lblIdUO.setVisible(false);
        this.lblUOGestora.setVisible(false);     
        
        this.btnPendentesAprovacao.setVisible(false);
        this.btnCaixaEntradaSolicitandoAprovacao.setVisible(false);
        
        //A partir da versão 1.5.3 debemos esconder o ScrollPane e TextFlow
        this.scrollPaneTextFlow.setVisible(false);
        //this.scrollPaneTextFlow.setTooltip(new Tooltip("Para visualizar, favor selecione o arquivo e clique 2 vezes no botão esquerdo do Mouse"));
        //---------------------------------------------------------------------
        
        tPaneRecebidas.setCollapsible(false);
        tPaneEnviadas.setCollapsible(false);
        tPaneArquivadas.setCollapsible(false);
        
        this.btnSalvarTodosArquivos.setVisible(true);
        this.lviewAnexos.setTooltip(new Tooltip("Para visualizar, favor selecione o arquivo e clique 2 vezes no botão esquerdo do Mouse"));
    }
    public void VerificarMarcadosComoPendencia(){
        Long lQuantidade = 0L;
        int nlQuantidade = 0;
        int nidUoDestinatario = 0;
        int nlTipoPerfil= 0;
        nidUoDestinatario = this.nIdUnidadeOrganizacional;
        nlTipoPerfil = this.nTipoPerfil;
        lQuantidade = RecordCountMarcadosComoPendentesTbCiDestinatario(nidUoDestinatario, nlTipoPerfil);
        nlQuantidade = lQuantidade.intValue();
        if(nlQuantidade > 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta");
            alert.setHeaderText("CIs marcadas como pendência");
            alert.setContentText("Na sua caixa de CIs Recebidas e marcadas como Pendências\n" +"existem CIs que não foram tratadas hà dias");
            alert.showAndWait();
        }
    }
    @FXML
    private void handleBtnSalvarTodosArquivos(ActionEvent event) throws IOException{
        int nSize = 0;
        int j = 0;
        int nIdAnexo = 0;
        String strFilePath = "";
        String strDelimiters = "=|\\;";
        String strFileName = "";
        File outfile = null;
        
        if (0==txtFAnexos.getChildren().size()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("A CI-Eletrônica não possui arquivos para serem salvos");
            alert.showAndWait();            
        } else {
            
            
            //Quantidade de arquvios a serem salvos
            nSize = txtFAnexos.getChildren().size();
            
            //Selecionamos a pasta de destino 
            Stage stage = new Stage();

            DirectoryChooser dirChooser = new DirectoryChooser();
            dirChooser.setTitle("CI-Eletrônica");
            //File defaultDirectory = new File("c:/dev/javafx");
            dirChooser.setInitialDirectory(new File(System.getProperty("user.home") + "//Downloads"));
            File selectedDirectory = dirChooser.showDialog(stage);            
            //-----------------------------------------------
            if (selectedDirectory!= null){
                ObservableList<Node> nodes = txtFAnexos.getChildren();
                StringBuilder sb = new StringBuilder();
                for (Node node : nodes) { 
                    sb.append((((Text)node).getText()));                 
                }
                strFilePath = sb.toString();
                strFilePath = ltrim(strFilePath);
                strFilePath = rtrim(strFilePath);

                String[] strParts = strFilePath.split(strDelimiters);

                for (int i = 0; i < strParts.length; i++){
                    //System.out.println(strParts[i]);

                    if ((j%2) == 0){
                        System.out.println("entro en j%2: " + strParts[i]);
    //                    strIdAnexo = strParts[i];
    //                    strIdAnexo = ltrim(strIdAnexo);
    //                    strIdAnexo = rtrim(strIdAnexo);
                        nIdAnexo = Integer.parseInt(strParts[i].trim());
                        System.out.println("Id Anexo = " + nIdAnexo); 

                        //Salvamos o arquivo na pasta selecionada pelo usuário
                        MainWindowQueries consulta  = new MainWindowQueries();
                        List<TbAnexo> listaAnexos = new ArrayList<TbAnexo>();

                        //Extrair o Id para realizar o download do arquivo

                        listaAnexos = consulta.downloadAnexo(nIdAnexo);
                        for(TbAnexo l : listaAnexos){
                            strFileName = l.getAnexoNome();
                            outfile = new File(selectedDirectory + "\\" + l.getAnexoNome());
                            try {
                                writeArquivo(outfile, l.getAnexoBlob());
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }

                    } else {
                        System.out.println("está fora de j%2: " + strParts[i]);

                    }
                    j++;
                    strFileName = "";
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informação");
                alert.setHeaderText(null);
                alert.setContentText("Os arquivos foram salvos na pasta selecionada pelo usuário.");
                alert.showAndWait();
            } else {
                System.out.println("Button cancelar pressed");
            }
        }
        
        
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
        
        VerificarMarcadosComoPendencia();
        
        //Default mostrar Cis na caixa de recebeidas
        btnCaixaEntrada.fire();
        //PreencherCaixaEntrada(2, true);
        
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
                
                btnEncaminharCI.setDisable(false);
                btnResponderCI.setDisable(false);
                btnArquivarCI.setDisable(false);
                btnDesarquivarCI.setDisable(false);
                
                //btnEditarCI.setVisible(false);
                btnAprovarCI.setDisable(true);
                btnEditarCI.setDisable(true);
                btnDesaprovarCI.setDisable(true);
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
                btnResponderCI.setDisable(false);
                btnArquivarCI.setDisable(false);
                btnDesarquivarCI.setDisable(false);
                btnEditarCI.setDisable(true);
                btnDesaprovarCI.setDisable(true);
                
                //btnEditarCI.setVisible(false);
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
                btnResponderCI.setDisable(true);
                btnArquivarCI.setDisable(true);
                btnDesarquivarCI.setDisable(true);
                
                //btnEditarCI.setVisible(false);
                btnEditarCI.setDisable(true);
                btnDesarquivarCI.setDisable(true);
                btnMarcarcomoPendencia.setDisable(true);
                btnDesaprovarCI.setDisable(true);
                
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
        
        int nIdUORemitente = 0;
        String strUONomeRemitente = "";
        
        //Variaveis para arquivar CI após enviado
        int nBotao = 0;
        int nTabela = 0;
        int nIdCiEletronica = 0;

//        nBotao = this.ngBotao;
//        nTabela = this.ngTabela;
//        nIdCiEletronica = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoin();
        //---------------------------------------------
        
        //Variaveis utilizadas nas CIs encaminhadas para não perder número de CI
        //criada e quem foi o Remitente inicial (serve para saber quais anexos acompanham o despacho também)
        int nlIdCoinGenesis = 0;
        int nlIdUnorGenesis = 0;
        int nlCoinNumeroGenesis = 0;
        String strCoinHistoricoAnexos = "";
        String strUnorDescricaoGenesis = "";
        String strlAssunto = "";
        
        ShowNovaCIe(this , strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, strDescricaoPerfil, strHtmlAssinatura, nTipoCI, nIdUOGestor, strHtmlConteudo,
                nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
                strgUserLogin,
                strlAssunto,
                nIdUORemitente/*nIdUORemitente = 0 quando for diferente de 7 - CI respondida*/,
                strUONomeRemitente,
                nBotao, nTabela, nIdCiEletronica);
                        
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
        
        int nIdUORemitente = 0;
        String strUONomeRemitente = "";
        
        //Variaveis para arquivar CI após enviado
        int nBotao = 0;
        int nTabela = 0;
        int nIdCiEletronica = 0;

//        nBotao = this.ngBotao;
//        nTabela = this.ngTabela;
//        nIdCiEletronica = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoin();
        //---------------------------------------------
        
        //Variaveis utilizadas nas CIs encaminhadas para não perder número de CI
        //criada e quem foi o Remitente inicial (serve para saber quais anexos acompanham o despacho também)
        int nlIdCoinGenesis = 0;
        int nlIdUnorGenesis = 0;
        int nlCoinNumeroGenesis = 0;
        String strCoinHistoricoAnexos = "";
        String strUnorDescricaoGenesis = "";
        String strlAssunto = "";
        
        ShowNovaCIe(this , strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, strDescricaoPerfil, strHtmlAssinatura, nTipoCI, nIdUOGestor, strHtmlConteudo,
                nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
                strgUserLogin,
                strlAssunto,
                nIdUORemitente/*nIdUORemitente = 0 quando for diferente de 7 - CI respondida*/,
                strUONomeRemitente,
                nBotao, nTabela, nIdCiEletronica);
        
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
        
        int nIdUORemitente = 0;
        String strUONomeRemitente = "";
        
        //Variaveis utilizadas nas CIs encaminhadas para não perder número de CI
        //criada e quem foi o Remitente inicial (serve para saber quais anexos acompanham o despacho também)
        int nlIdCoinGenesis = 0;
        int nlIdUnorGenesis = 0;
        int nlCoinNumeroGenesis = 0;
        String strCoinHistoricoAnexos = "";
        String strUnorDescricaoGenesis = "";
        String strlAssunto = "";
        
        //Variaveis para arquivar CI após enviado
        int nBotao = 0;
        int nTabela = 0;
        int nIdCiEletronica = 0;

//        nBotao = this.ngBotao;
//        nTabela = this.ngTabela;
//        nIdCiEletronica = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoin();
        //---------------------------------------------
        
        ShowNovaCIe(this , strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, strDescricaoPerfil, strHtmlAssinatura, nTipoCI, nIdUOGestor, strHtmlConteudo,
                nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
                strgUserLogin,
                strlAssunto,
                nIdUORemitente/*nIdUORemitente = 0 quando for diferente de 7 - CI respondida*/,
                strUONomeRemitente,
                nBotao, nTabela, nIdCiEletronica);
        
    }
     @FXML
    private void handleBtnCIResponder(ActionEvent event) throws IOException{
        
        if (null == TbViewGeral.getSelectionModel().getSelectedItem()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("CI não foi selecionado.");
            alert.setContentText("Favor selecionar uma CI da tabela");
            alert.showAndWait();
        }else{
            String strHtmlConteudo = "";
            String strlAssinatura = "";
            String strlAssunto = "";
            
                       
            strHtmlConteudo = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_Conteudo();
            strlAssinatura = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_CoinAssinatura();
            strlAssunto = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_Assunto();
            
            //Valores para preencher o campo Para:
            int nIdUORemitente = 0;
            String strUONomeRemitente = "";
            
            nIdUORemitente = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idUORemitente();
            strUONomeRemitente = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_DescricaoUORemitente();
            //-----------------------------------------
            
            strIdUsuario = lblIdUsuario.getText();
            strNomeUsuario = lblNomeUsuario.getText();
            strIdUO = lblIdUO.getText();
            strNomeUO = lblNomeUO.getText();  
            strIdPerfil = lblIdPerfil.getText();
            strDescricaoPerfil = lblNomePerfil.getText();
            nTipoCI = 7;    //CI Respondida
            
            //Variaveis para arquivar CI após enviado
            int nBotao = 0;
            int nTabela = 0;
            int nIdCiEletronica = 0;
            
            nBotao = this.ngBotao;
            nTabela = this.ngTabela;
            nIdCiEletronica = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoin();
            //---------------------------------------------
            
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
            ShowNovaCIe(this , strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, 
                    strDescricaoPerfil, strHtmlAssinatura, nTipoCI, nIdUOGestor, strHtmlConteudo,
                    nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
                    strlAssinatura,
                    strlAssunto,
                    nIdUORemitente,
                    strUONomeRemitente,
                    nBotao, nTabela, nIdCiEletronica);
        }
        
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
            String strlAssunto = "";
            strHtmlConteudo = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_Conteudo();
            strlAssinatura = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_CoinAssinatura();
            strlAssunto = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_Assunto();
            strIdUsuario = lblIdUsuario.getText();
            strNomeUsuario = lblNomeUsuario.getText();
            strIdUO = lblIdUO.getText();
            strNomeUO = lblNomeUO.getText();  
            strIdPerfil = lblIdPerfil.getText();
            strDescricaoPerfil = lblNomePerfil.getText();
            nTipoCI = 4;    //CI Encaminhada
            
            //Variaveis para arquivar CI após enviado
            int nBotao = 0;
            int nTabela = 0;
            int nIdCiEletronica = 0;
            
            nBotao = this.ngBotao;
            nTabela = this.ngTabela;
            nIdCiEletronica = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoin();
            //---------------------------------------------
            
            int nIdUORemitente = 0;
            String strUONomeRemitente = "";
            
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
            ShowNovaCIe(this , strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, 
                    strDescricaoPerfil, strHtmlAssinatura, nTipoCI, nIdUOGestor, strHtmlConteudo,
                    nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
                    strlAssinatura,
                    strlAssunto,
                    nIdUORemitente/*nIdUORemitente = 0 quando for diferente de 7 - CI respondida*/,
                    strUONomeRemitente,
                    nBotao, nTabela, nIdCiEletronica);
        }
    }
    public void ShowNovaCIe(final FXMLMainController mainController , String strIdUsuario, String strNomeUsuario, String strIdUO, String strNomeUO, String strIdPerfil, 
            String strDescricaoPerfil, String strHtmlAssinatura, int nTipoCI, int nIdUOGestor, String strHtmlConteudo,
            int nlIdCoinGenesis, int nlIdUnorGenesis, int nlCoinNumeroGenesis, String strCoinHistoricoAnexos, String strUnorDescricaoGenesis,
            String strlUserLogin,
            String strlAssunto,
            int nIdUORemitente,
            String strUONomeRemitente,
            int nBotao, int nTabela, int nIdCiEletronica /*arquivamento após Ci enviado*/ ){
        try{
                scene = new Scene(new SplitPane());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ci_eletronico/nova_ci/NovaCI.fxml"));
                scene.setRoot((Parent) loader.load());
                
                ci_eletronico.nova_ci.NovaCIController nova_ci_controller = loader.<ci_eletronico.nova_ci.NovaCIController>getController();     
                nova_ci_controller.setVariaveisAmbienteNovaCI(mainController, strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, strDescricaoPerfil, 
                        strHtmlAssinatura, nTipoCI, nIdUOGestor, strHtmlConteudo,
                        nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
                        strlUserLogin,
                        strlAssunto,
                        nIdUORemitente, 
                        strUONomeRemitente,
                        nIdCiEletronica, nBotao);
                //controller.setVariaveisAmbienteNovaCI(mainController, strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, strDescricaoPerfil);                
                
                Stage stage = new Stage();
                stage.setTitle("Nova CI-eletrônico");
                //set icon
                stage.getIcons().add(new Image("/resources/Nova_CI.png"));

                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);     //Window Parent fica inativo
                stage.showAndWait();
                
                //Arquivamos as CIs que foram do tipo Ci - Encaminhada ==>4 ou Ci -respondida ==>7
                switch (nTipoCI){
                    case 4: case 7:
                        //ArquivarCiRespondidaOuEncaminhada(Integer.parseInt(strIdUO),nIdCiEletronica, nTabela);
                        
                        //Refresh da TableView
                        switch(nBotao){
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
                        
                        break;
                    default:
                        break;
                }
                
//                                
            }catch (IOException ex) {
                Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    @FXML
    private void handleBtnCaixaEntradaPendentesAprovacao(ActionEvent event) throws IOException {
        
//        lblCaixa.setText("");
//        lblCaixa.setText("Caixa de recebidas (solicitando aprovação)");       
//        
//        setBotoesMainWindow(nTipoPerfil);
//        
//        btnEncaminharCI.setDisable(true);
//        btnDesarquivarCI.setDisable(true);
//        
//        if ((this.nIdUnidadeOrganizacional == this.nIdUOGestor) &&(this.nTipoPerfil == 1)){
//            btnAprovarCI.setDisable(false);
//        }else{
//            btnAprovarCI.setDisable(true);
//        }          
//        clearTelas();
//        
//        boolean blPreencherTableView2 = true;
//        PreencherCaixaEntrada(1,blPreencherTableView2);
        
    }
    @FXML
    private void handleBtnPendentesAprovacao(ActionEvent event) throws IOException {
//        
//        lblCaixa.setText("");
//        lblCaixa.setText("Caixa de enviados (solicitando aprovação)");       
//        
//        setBotoesMainWindow(nTipoPerfil);
//        
//       
//        btnEncaminharCI.setDisable(true);
//        btnDesarquivarCI.setDisable(true);
//        
//        
//        
//        btnEditarCI.setVisible(true);
//        btnMarcarcomoPendencia.setDisable(true);
//        
//        if ((this.nIdUnidadeOrganizacional == this.nIdUOGestor) &&(this.nTipoPerfil == 1)){
//            btnAprovarCI.setDisable(false);
//        }else{
//            btnAprovarCI.setDisable(true);
//        }
//        
//        clearTelas();
//        
//        ngTabela = 1; // TB_COMUNICACAO_INTERNA
//        ngBotao = 7;
//        
//        //Tipo de perfil
//        int nlTipoPerfil = 0;
//        nlTipoPerfil = this.nTipoPerfil;
//        
//        //List<TbComunicacaoInterna> listaCiSemAprovar = new ArrayList<>();
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
//        //-------------------------------------------
//        
//        String strlDescricaoUODestinatario = "";
//        
//        //Assinatura MD5
//        String strlAssinatura = "";
//        
//        boolean bCoinLido = false;
//        
//        obslistaTbCIPorAprovar = FXCollections.observableArrayList();
//        
//        consulta  = new MainWindowQueries();
//        
//        //Deve-se mostrar as Cis para aprovação só para usuário que faz parte da UO Gestora
//        if (this.nIdUOGestor == this.nIdUnidadeOrganizacional){
//            if (1 == nlTipoPerfil){ //Perfil Gestor
//                listaCiSemAprovar = consulta.getlistaTbComunicacaoInternaPorAprovar(this.nIdUOGestor);
//            } else {
//                listaCiSemAprovar = consulta.getlistaTbComunicacaoInternaPorAprovarPerfil2(this.nIdUOGestor);
//            }
//
//                for(TbComunicacaoInterna l : listaCiSemAprovar){
//                    try {
//                    nIdCoin = l.getIdCoin();
//                    strAssunto = l.getCoinAssunto();
//                    strConteudo = l.getCoinConteudo();
//                    nIdUsuario = l.getIdUsuario();
//                    strUsuarioNomeCompleto = l.getUsuNomeCompleto();
//                    nIdUO = l.getIdUnidadeOrganizacional();
//                    strUODescricao = l.getUnorDescricao();
//                    nIdUOGestor = l.getIdUoGestor();
//                    bAutorizado = l.getCoinAutorizado();
//                    nTipoCoin = l.getIdTipoCoin().getIdTipoCoin();
//                    strApensamento = l.getCoinApensamento();            
//                    nSequencial = l.getCoinNumero();
//                    bArquivadoUO = l.getCoinUoArquivado();
//                    bArquivadoUOGestor = l.getCoinUoGestorArquivado();            
//                    dataCriacao = l.getCoinDataCriacao();
//                    strDataCriacao = df.format(dataCriacao);            
//                    dataAutorizado = l.getCoinDataAutorizado();
//                    bCoinReadOnly = l.getCoinReadOnly();
//                    bTemAnexos = l.getCoinTemAnexos();
//                    nIdTabelaFonte = 1;
//                    
//                    nlIdCoinGenesis = l.getIdCoinGenesis();
//                    nlIdUnorGenesis = l.getIdUnorGenesis();
//                    nlCoinNumeroGenesis = l.getCoinNumeroGenesis();
//                    strCoinHistoricoAnexos = l.getCoinHistoricoAnexos();
//                    strUnorDescricaoGenesis = l.getUnorDescricaoGenesis();
//                    
//                    strlAssinatura = l.getCoinAssinatura();
//
//
//        //            obslistaTbCIPorAprovar.add(new TbCIPorAprovar(nIdCoin,strAssunto,strConteudo,nIdUsuario,strUsuarioNomeCompleto,
//        //                        nIdUO,strUODescricao,nSequencial,dataCriacao,strDataCriacao,bAutorizado,bTemAnexos,1));
//                        obslistaTbCIPorAprovar.add(new TbCIPorAprovar(nIdCoin, strAssunto, strConteudo, nIdUsuario, strUsuarioNomeCompleto, nIdUO, strUODescricao, 
//                                nIdUOGestor, bAutorizado, nTipoCoin, strApensamento, nSequencial, bArquivadoUO, bArquivadoUOGestor, dataCriacao, strDataCriacao, 
//                                dataAutorizado, bCoinReadOnly, bTemAnexos ,nIdTabelaFonte,
//                                nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
//                                strlDescricaoUODestinatario,
//                                strlAssinatura, bCoinLido));
//                    } catch (Exception e){
//                        Alert alert = new Alert(Alert.AlertType.ERROR);
//                        alert.setTitle("Error");
//                        alert.setHeaderText("ObservableList Error");
//                        alert.setContentText(e.toString());
//                        alert.showAndWait();
//                    }
//                    }
//                //	ClDataEnvio; ClUORemitente; ClAutorRemitente; ClAssunto;
//                ClAprovado.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Boolean>("boolp_CoinLido"));
//                ClIdCoin.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Integer>("intp_idCoin"));
//                ClUODestinatario.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUODestinatario"));
//                ClDataEnvio.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_dataCriacao"));        
//                ClUORemitente.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUORemitente"));        
//                ClAutorRemitente.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_UsuarioNomeCompleto"));        
//                ClAssunto.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_Assunto"));
//                TbViewGeral.setItems(obslistaTbCIPorAprovar);
//                //TbViewGeral
//                
//                
//               
//                TbViewGeral.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
//                @Override
//                public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
//                //Check whether item is selected and set value of selected item to Label
//                    try{
//                        //TableView<TbCIPorAprovar> TbViewGeral = new TableView<>();
//                        if(TbViewGeral.getSelectionModel().getSelectedItem() != null){
//                            //TbCIPorAprovar tbCiPorAprovar = TbViewGeral.getSelectionModel().getSelectedItem();
//                            //TbCIPorAprovar tbtbCiPorAprovar = new TbCIPorAprovar();
//                            TbCIPorAprovar tbCiPorAprovar = TbViewGeral.getSelectionModel().getSelectedItem();
//                            int nCISequencial = 0;
//                            int nlIdCI = 0;
//                            boolean bTemAnexo = false;
//                            
//                            String strCoinHistoricoAnexos = "";
//                            
//                            String strDescricaoUO = "";
//                            String strYear = "";
//                            Date dataCriacao;
//                            SimpleDateFormat df = new SimpleDateFormat("yyyy");
//
//                            nlIdCI = tbCiPorAprovar.getIntp_idCoin();
//                            //strDescricaoUO = tbCiPorAprovar.getStrp_DescricaoUORemitente();
//                            strDescricaoUO = tbCiPorAprovar.getStrp_UnorDescricaoGenesis();
//                            nCISequencial = tbCiPorAprovar.getIntp_idCoinNumero();                    
//                            dataCriacao = tbCiPorAprovar.getDataCriacao();
//                            bTemAnexo = tbCiPorAprovar.getBoolp_CoinTemAnexos();
//                            strCoinHistoricoAnexos = tbCiPorAprovar.getStrp_CoinHistoricoAnexos();
//
//                            strYear = df.format(dataCriacao);
//
//                            htmlEditorCI.setHtmlText(tbCiPorAprovar.getStrp_Conteudo());                    
//                            lblNumeroSequencialCI.setText(strDescricaoUO + "-" + String.format("%05d",nCISequencial)+"-" + strYear);
//                            if ((bTemAnexo) || (strCoinHistoricoAnexos.length()>0) ){
//                                PreencherTxtFAnexos(nlIdCI,strCoinHistoricoAnexos);
//                            }
//                            else {
//                                txtFAnexos.getChildren().clear();
//                            }
//                        }
//                    }catch (Exception e) {
//                            e.printStackTrace();
//                            //labelMessage.setText("Error on get row Data");
//                            Alert alert = new Alert(Alert.AlertType.ERROR);
//                            alert.setTitle("Erro");
//                            alert.setHeaderText("Erro na carga da TableView");
//                            alert.setContentText(e.getMessage());
//                            alert.showAndWait();
//                    }
//                }
//            }); 
//        }
    }
    private void PreencherTxtFAnexos(int nlIdCI, String strCoinHistoricoAnexos){
        txtFAnexos.getChildren().clear();
        lviewAnexos.getItems().clear();
        
        //Hyperlink linkArquivoSelecionado = new Hyperlink() ;
        Text txtIdAnexo;
        Text txtArquivoSelecionado;
        Text txtNomeArquivo;
        String strDelimiters = ";";
        String strSeparador = "=|\\;";
        
         //Preenchemos Ids e nomes dos arquivos
            ObservableList<Choice> choicesArquivos = FXCollections.observableArrayList();
         //------------------------------------------------
        
        if (nlIdCI > 0) {
            
           
            
           
            
            consulta  = new MainWindowQueries();
            List<TbAnexo> listaAnexos = new ArrayList<TbAnexo>();
            TbComunicacaoInterna nlIdCoin = new TbComunicacaoInterna(nlIdCI);
            listaAnexos = consulta.getlistaAnexo(nlIdCoin);
            for(TbAnexo l : listaAnexos){
                
                choicesArquivos.add(new Choice(l.getIdAnexo(), l.getAnexoNome()));  
                
                linkArquivoSelecionado = new Hyperlink();
                linkArquivoSelecionado.setText(l.getIdAnexo() + "=" + l.getAnexoNome() + " ;\n");
                            
                            //txtArquivoSelecionado.getStyleClass().add("link");
                txtIdAnexo = new Text();
                txtNomeArquivo = new Text();
                txtArquivoSelecionado = new Text();                          
                //txtArquivoSelecionado.setText("\""+ UOSelected.get(nContador)+ "\"; ");
                txtIdAnexo.setFill(Color.SILVER);
                txtIdAnexo.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
                txtIdAnexo.setText(l.getIdAnexo() + "=" /*+ l.getAnexoNome() + " ;\n"*/);
                
                txtNomeArquivo.setFill(Color.BLUE);
                txtNomeArquivo.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
                txtNomeArquivo.setText(l.getAnexoNome()/*+";\n"*/);
                
                TextFlow textFlow = new TextFlow(txtIdAnexo, txtNomeArquivo);
                
                txtArquivoSelecionado.setFill(Color.BLUE);
                //txtArquivoSelecionado.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
                //txtArquivoSelecionado.setText(txtIdAnexo.getText() + txtNomeArquivo.getText() );
                
                txtArquivoSelecionado.setText(l.getIdAnexo() + "=" + l.getAnexoNome() + " ;\n");
                
                
                
                //txtArquivoSelecionado.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
                txtFAnexos.getChildren().add(txtArquivoSelecionado);                
                
                
                //txtFAnexos.getChildren().add(textFlow);                
                //txtFAnexos.getChildren().add(linkArquivoSelecionado);  
                
//                linkArquivoSelecionado.setOnAction(ev->{
//                    System.out.println("Click on  link: " + linkArquivoSelecionado.getText());
////                    AbrirArquivo(linkArquivoSelecionado.getText(),1);
//                });    
                
            }
            lviewAnexos.setItems(choicesArquivos);
        }
        if (strCoinHistoricoAnexos.length()>0){
            
            
            //Utilizamos o controle ListView
            String strHistoricoAnexos = strCoinHistoricoAnexos;
            strHistoricoAnexos = ltrim(strHistoricoAnexos);
            strHistoricoAnexos = rtrim(strHistoricoAnexos);
            //System.out.println("strHistoricoAnexos <==> " + strHistoricoAnexos);
            String[] strParteSeparada = strHistoricoAnexos.split(strSeparador);
            
//            // Initialize Scanner object
//		Scanner scan = new Scanner(strHistoricoAnexos);
//		// initialize the string delimiter
//		scan.useDelimiter("=");
//		// Printing the delimiter used
//		System.out.println("The delimiter use is "+scan.delimiter());
//		// Printing the tokenized Strings
//		while(scan.hasNext()){
//			System.out.println(scan.next());
//		}
//		// closing the scanner stream
//		scan.close();
            
            int j = 1;
            int y = 0;
            int z = 0;
            int nContador = strParteSeparada.length/2;
            int [] nArrayId = new int[strParteSeparada.length/2];   //Dividimos x 2 para pegar só a quantidade exata de Ids no array
            String [] strArrayNomeArquivo = new String[strParteSeparada.length/2];
            
            for (int i = 0; i < strParteSeparada.length; i++){
                if ((j%2) == 0){
                    strArrayNomeArquivo[y] = strParteSeparada[i].trim();
                    //System.out.println("Nome Arquivo <==> " + strArrayNomeArquivo[y]);
                    y++;                    
                    
                } else {
                    nArrayId[z] = Integer.parseInt(strParteSeparada[i].trim());
                    //System.out.println("Id <==> " + nArrayId[z]);
                    z++;
                }
                
                //System.out.println("Anexo = "+strParteSeparada[i]);
                j++;
                
            }
            //System.out.println("Fim");
            for (int i = 0; i < nContador; i++){
                choicesArquivos.add(new Choice(nArrayId[i], strArrayNomeArquivo[i]));
            }
            lviewAnexos.setItems(choicesArquivos);           
            
            
            //----------------------------------------------------------------
            
            String[] strParts = strCoinHistoricoAnexos.split(strDelimiters);
            for (int i = 0; i < strParts.length; i++){
                
                //choicesArquivos.add(new Choice(l.getIdAnexo(), l.getAnexoNome()));
                
                linkArquivoSelecionado = new Hyperlink();
                linkArquivoSelecionado.setText(strParts[i] + " ;\n");
                
                txtArquivoSelecionado = new Text();                                          
                txtArquivoSelecionado.setText(strParts[i] + "\n");
                txtArquivoSelecionado.setFill(Color.BLUE);
                txtArquivoSelecionado.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
                txtFAnexos.getChildren().add(txtArquivoSelecionado); 
                //txtFAnexos.getChildren().add(linkArquivoSelecionado); 
//                linkArquivoSelecionado.setOnAction(ev->{
//                    //System.out.println("Click on  link: " + linkArquivoSelecionado.getText());
//                    AbrirArquivo(linkArquivoSelecionado.getText(),1);
//                });   
            }            
        }        
        
//        linkArquivoSelecionado.setOnAction(ev->{
//                    System.out.println("Click on  link: " + linkArquivoSelecionado.getText());
////                    AbrirArquivo(linkArquivoSelecionado.getText(),1);
//                }); 
        lviewAnexos.setOnMouseClicked(ev -> {
                        if (1 == ev.getClickCount()){
                            Choice choiceAnexo = lviewAnexos.getSelectionModel().getSelectedItem();
                            System.out.println();

                        }
                        if (2 == ev.getClickCount()){
                            Choice choiceAnexo = lviewAnexos.getSelectionModel().getSelectedItem();
                            System.out.println();//                            
                            AbrirAnexo(choiceAnexo.id,1);

                        }
                    });
        
        lviewAnexos.setOnMousePressed(new EventHandler<MouseEvent>() {

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
                        
                        Choice choiceAnexo = lviewAnexos.getSelectionModel().getSelectedItem();
                        System.out.println();                        
                        AbrirAnexo(choiceAnexo.id,1);
                    }
                });
                menuItemSalvar.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
//                        System.out.println("Salvar arquivo clicked");
                        
                        Choice choiceAnexo = lviewAnexos.getSelectionModel().getSelectedItem();
                        System.out.println();
                        
                        AbrirAnexo(choiceAnexo.id,2);
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
        
        
        txtFAnexos.setOnMouseClicked(ev -> {
                        if (1 == ev.getClickCount()){
                            if(ev.getTarget() instanceof Text) {
                                Text clicked = (Text) ev.getTarget();
                                clicked.setFill(Color.RED);
                                clicked.setFont(Font.font("Arial", FontPosture.ITALIC, 12));
                                clicked.setUnderline(true);
                                System.out.println(clicked.getText());
                                //AbrirArquivo(clicked.getText(),1);
                                //txtFAnexado.getChildren().remove(ev.getTarget());
                            }                            
                        }
                        if (2 == ev.getClickCount()){
                            if(ev.getTarget() instanceof Text) {
                                Text clicked = (Text) ev.getTarget();
                                System.out.println(clicked.getText());
                                AbrirArquivo(clicked.getText(),1);
                                //txtFAnexado.getChildren().remove(ev.getTarget());
                            }
                        }
                    });

	
        
        
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
                    alert.setContentText("IMPORTANTE: O arquivo foi salvo na pasta Downloads");
                    alert.showAndWait();
                    break;
            default:
                    break;
        }
        
    }
    
    private void AbrirAnexo(int nlIdAnexo, int nlMenuItem){
        File outfile = null;
        String strFileName = "";
        String strUserHome = System.getProperty("user.home") + "//Downloads//";
        
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
                    alert.setContentText("IMPORTANTE: O arquivo foi salvo na pasta Downloads");
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
        if (null == TbViewGeral2.getSelectionModel().getSelectedItem()){
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
                        nIdCI = TbViewGeral2.getSelectionModel().getSelectedItem().getIntp_idCoin();
                        AprovarCI(nIdCI, 1);
                        //TableViewRefresh(nBotao);
                        break;
                    case 2:
                        nIdCI = TbViewGeral2.getSelectionModel().getSelectedItem().getIntp_idCoinDestinatario();
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
        
        MainWindowQueries consulta  = new MainWindowQueries();  
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
                    alert.setHeaderText("Case 1: Tabela TB_CI_DESTINATARIO");
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
                    alert.setHeaderText("Case 2: Tabela TB_CI_DESTINATARIO");
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
    private void handleBtnDesaprovarCI(ActionEvent event) throws IOException{
        if (null == TbViewGeral2.getSelectionModel().getSelectedItem()){
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
            alert.setTitle("Confirmar Desaprovação");
            alert.setHeaderText(null);
            alert.setContentText("Deseja realmente Desaprovar CI?"); 

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
                        nIdCI = TbViewGeral2.getSelectionModel().getSelectedItem().getIntp_idCoin();
                        DesaprovarCI(nIdCI, 1);
                        //TableViewRefresh(nBotao);
                        break;
                    case 2:
                        nIdCI = TbViewGeral2.getSelectionModel().getSelectedItem().getIntp_idCoinDestinatario();
                        DesaprovarCI(nIdCI, 2);                        
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
    private void DesaprovarCI(int nlIdCI, int nlTabela){
       
        boolean bUpdate = false;
        boolean bDesaprovado = true;
        
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
        
        MainWindowQueries consulta  = new MainWindowQueries();  
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
                    TbComunicacaoInterna EntidadeTbCI = new TbComunicacaoInterna();
                    EntidadeTbCI = consulta.DesaprovarCIEnviada(nlIdCI);
                    bUpdate = EnviarMsgCiDesaprovada(nlIdCI,1, EntidadeTbCI,null);
//                    if (bUpdate){
//                        EntidadeTbCI = consulta.DesaprovarCIEnviada(nlIdCI);
//                    }
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
                        l.setCoinCancelado(bDesaprovado);
                        em.merge(l);
                    }
                    em.getTransaction().commit();            
                    em.close();
                    emf.close();
                    
                }catch(javax.persistence.PersistenceException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Desaprovar CI ");
                    alert.setHeaderText("Case 1: Tabela TB_CI_DESTINATARIO");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();                                            
                }
                break;
            case 2:
                try{
                    TbCiDestinatario EntidadeTbCI = new TbCiDestinatario();
                    EntidadeTbCI = consulta.DesaprovarCIRecebida(nlIdCI);
                    bUpdate = EnviarMsgCiDesaprovada(nlIdCI,2, null, EntidadeTbCI);
//                    if (bUpdate){
//                        EntidadeTbCI = consulta.DesaprovarCIRecebida(nlIdCI);
//                    }
                                        
                }catch(Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Desaprovar CI ");
                    alert.setHeaderText("Case 2: Tabela TB_CI_DESTINATARIO");
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
            alert.setHeaderText("CI foi Desaprovada com sucesso.");
            alert.setContentText("Foi enviada uma menssagem para o emisor da CI desaprovada");
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
            alert.setHeaderText("Não foi possível desaprovar CI");
            alert.setContentText("Favor contatar o Administrador do sistema");
            alert.showAndWait();
        }
        
    }
    
    private void EnviarMsgCiLida(int nlIdCI/*, int nlTabela, TbComunicacaoInterna EntidadeTbCI, TbCiDestinatario EntidadeTbCIDest*/){
        // Iniciamos a Persistencia de dados
        EntityManager em;
        EntityManagerFactory emf;
        
        emf = Persistence.createEntityManagerFactory("CI_EletronicoPU");
        em = emf.createEntityManager();        
        em.getTransaction().begin();
        
        MainWindowQueries consulta  = new MainWindowQueries();         
       
        //---------------------------------------------------
        //Pegamos os dados da entidade fonte
        
        TbCiDestinatario entidadeTbCiDestinatario = new TbCiDestinatario();
        try{
            entidadeTbCiDestinatario = consulta.getEntidadeTbCiDestinatario(nlIdCI);
        } catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("CI - Lida ");
            alert.setHeaderText("Tabela TB_CI_DESTINATARIO");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();        
        }
        //-------------------------------------------------------------
        
        //Instanciar novo objeto a ser salvo
        
        TbTipoComunicacoInterna TipoCI = new TbTipoComunicacoInterna(8); //8 ==> CI Lida
        
        TbComunicacaoInterna newTbCI = new TbComunicacaoInterna(); 
        TbCiDestinatario newTbCIDestinatario = new TbCiDestinatario();
        TbUnidadeOrganizacionalGestor UOGestorDestinatario = new TbUnidadeOrganizacionalGestor();
        //---------------------------------------------------------
        TbUnidadeOrganizacional nIdUOTemp;
        nIdUOTemp = new TbUnidadeOrganizacional(this.nIdUnidadeOrganizacional);
        MainWindowQueries consultaUOGestor = new MainWindowQueries();
        UOGestorDestinatario = consultaUOGestor.getIdUOGestor(nIdUOTemp);
        
        
        int nIdCoin = 0;
        String strlAssunto = "";
        //strlAssunto = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_Assunto();
        nIdCoin = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoin();
        
        
        //Valores para preencher o campo Para:
        int nIdUORemitente = 0;
        String strUONomeRemitente = "";        

        nIdUORemitente = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idUORemitente();
        strUONomeRemitente = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_DescricaoUORemitente();
        
        String strSequencialCI = this.lblNumeroSequencialCI.getText();
        String strlUODescricao = "";
        String strlCITitulo = "";
        String strlConteudo = "";
        String strPara = "";
        //Seteamos Datas
        String strToday = "";
        String strTodayCI = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // utilizado para banco de dados
        DateFormat dfCI = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // utilizado para mostrar na aplicação
        
        java.util.Date data_criacao; 
        data_criacao = new Date();
        strToday = df.format(data_criacao);        
        strTodayCI = dfCI.format(data_criacao); 
        
        Calendar data = Calendar.getInstance();
        int nYear = data.get(Calendar.YEAR);
        
        //
        
        //strlAssunto = strlAssunto.concat(" - CI - " + strSequencialCI + " foi lida pelo destinatário. Ref Id:" + Integer.toString(nIdCoin));
        strlAssunto = "Menssagem de confirmação: " + strSequencialCI + " foi lida pelo destinatário. Ref Id: " + Integer.toString(nIdCoin);
        
        consulta= new MainWindowQueries();
        strlConteudo = consulta.getMessagemCiLida("NOTIFICACAO_CI_LIDA");
        
        strlCITitulo = "<br /><p align=\"center\"><b>" + strSequencialCI + "</b></p>";
        //strlConteudo = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: 'Segoe UI';\">Prezado(a),&nbsp;</span></p><p><font face=\"Segoe UI\">Informamos que a CI foi cancelada pela UO Gestora e por esse motivo não será possível o seu envio para seu(s) destinatário(s).</font></p><p><font face=\"Segoe UI\">Atenciosamente,&nbsp;</font></p><p><font face=\"Segoe UI\">Sistema CI-eletrônica.</font></p></body></html>";
        
        strPara = strPara.concat(strlCITitulo);
        strPara = strPara.concat("<br /><hr><br /><b><FONT COLOR=\"0000FF\">CI Lida</FONT></b>");
        strPara = strPara.concat("<br /><hr><br /><FONT COLOR=\"000000\">De: <b>" + this.lblNomeUO.getText() + "</b></FONT><br />");
        strPara = strPara.concat("<FONT COLOR=\"000000\">Usuário remitente: " + this.lblNomeUsuario.getText() + "</FONT><br /><br />");
        strPara = strPara.concat("<FONT COLOR=\"000000\">Assunto: " + strlAssunto + "</FONT><br /><br />");
        strPara = strPara.concat("<FONT COLOR=\"000000\">Data criação: " + strTodayCI + "</FONT><br /><br />");
        strPara = strPara.concat(strlConteudo);
        
        try {
        //Seteamos número sequencial da CI de acordo ao UO Remitente
        TypedQuery<Integer> query = em.createQuery("SELECT max(c.coinNumero) FROM TbComunicacaoInterna c WHERE c.idUnidadeOrganizacional = :idUnidadeOrganizacional AND FUNCTION('YEAR',c.coinDataCriacao) = :Ano",Integer.class)            
                                            .setParameter("idUnidadeOrganizacional", nIdUnidadeOrganizacional)
                                            .setParameter("Ano", nYear);
        Integer Resultado = query.getSingleResult(); 
        
        //-----------------------------------------------------------
        
        //
        //Seteamos entity TB_COMUNICACAO_INTERNA

        newTbCI.setCoinAssunto(strlAssunto);
        //newTbCI.setCoinConteudo(htmlEditor.getHtmlText());
        newTbCI.setCoinConteudo(strPara);
        newTbCI.setIdUsuario(this.nIdUsuarioLogado);
        newTbCI.setUsuNomeCompleto(this.lblNomeUsuario.getText());
        newTbCI.setIdUnidadeOrganizacional(this.nIdUnidadeOrganizacional);
        newTbCI.setUnorDescricao(this.lblNomeUO.getText());
        newTbCI.setIdUoGestor(this.nIdUOGestor);
        newTbCI.setCoinAutorizado(true);
        newTbCI.setIdTipoCoin(TipoCI);
        newTbCI.setCoinApensamento("");
        newTbCI.setCoinNumero(Resultado.intValue());
        newTbCI.setCoinUoArquivado(false);
        newTbCI.setCoinUoGestorArquivado(false);
        newTbCI.setCoinDataCriacao(data_criacao);
        newTbCI.setCoinDataAutorizado(data_criacao);
        newTbCI.setCoinReadOnly(false);
        newTbCI.setCoinTemAnexos(false);
        //newTbCI.setCoinEnviadoPara("");
        //newTbCI.setCoinEnviadoComCopia("");
        
        newTbCI.setIdCoinGenesis(entidadeTbCiDestinatario.getIdCoinGenesis());
        newTbCI.setIdUnorGenesis(entidadeTbCiDestinatario.getIdUnorGenesis());
        newTbCI.setCoinNumeroGenesis(entidadeTbCiDestinatario.getCoinNumeroGenesis());
        newTbCI.setCoinHistoricoAnexos("");
        newTbCI.setUnorDescricaoGenesis(entidadeTbCiDestinatario.getUnorDescricaoGenesis());
        
        newTbCI.setCoinAssinatura(entidadeTbCiDestinatario.getCoinAssinatura());
        
        em.persist(newTbCI);
        em.flush();
        
        
        long IdCoin = newTbCI.getIdCoin();
        TbComunicacaoInterna nCoinId = new TbComunicacaoInterna((int)IdCoin);
        TbTipoEnvio nIdTipoEnvio = new TbTipoEnvio(1); //1 - Tipo = ENVIADO "PARA"
        
        //Procuramos saber qual UO gestora da UO destinataria
        nIdUOTemp = new TbUnidadeOrganizacional(nIdUORemitente);
        UOGestorDestinatario = consultaUOGestor.getIdUOGestor(nIdUOTemp);
        //-----------------------------------------------------------
        
        
        newTbCIDestinatario.setIdCoin(nCoinId);
        newTbCIDestinatario.setIdUsuarioRemitente(this.nIdUsuarioLogado);
        newTbCIDestinatario.setUsuNomeCompletoRemitente(this.lblNomeUsuario.getText());
        newTbCIDestinatario.setIdUoRemitente(this.nIdUnidadeOrganizacional);
        newTbCIDestinatario.setInorDescricaoRemitente(this.lblNomeUO.getText());
        newTbCIDestinatario.setIdUoDestinatario(nIdUORemitente);
        newTbCIDestinatario.setUnorDescricaoDestinatario(strUONomeRemitente);
        newTbCIDestinatario.setIdUoGestorDestinatario(UOGestorDestinatario.getIdUoGestor());
        newTbCIDestinatario.setCoinDestinatarioGestorAutorizado(true);
        newTbCIDestinatario.setCoinDestinatarioUoArquivado(false);
        newTbCIDestinatario.setCoinDestinatarioUoGestorArquivado(false);
        newTbCIDestinatario.setCoinDestinatarioAssunto(strlAssunto);
        newTbCIDestinatario.setCoinDestinatarioConteudo(strPara);
        newTbCIDestinatario.setCoinDestinatarioPendente(false);
        newTbCIDestinatario.setCoinDestinatarioLido(false);
        newTbCIDestinatario.setCoinDestinatarioDataCriacao(data_criacao);
        newTbCIDestinatario.setIdTipoEnvio(nIdTipoEnvio);
        newTbCIDestinatario.setCoinDestinatarioNumero(Resultado.intValue());
        newTbCIDestinatario.setCoinDestinatarioReadOnly(false);
        newTbCIDestinatario.setCoinDestinatarioTemAnexos(false);
        newTbCIDestinatario.setCoinRemitenteGestorAutorizado(true);

        //Variaveis Genesis
        newTbCIDestinatario.setIdCoinGenesis(entidadeTbCiDestinatario.getIdCoinGenesis());
        newTbCIDestinatario.setIdUnorGenesis(entidadeTbCiDestinatario.getIdUnorGenesis());
        newTbCIDestinatario.setCoinNumeroGenesis(entidadeTbCiDestinatario.getCoinNumeroGenesis());
        newTbCIDestinatario.setCoinHistoricoAnexos("");
        newTbCIDestinatario.setUnorDescricaoGenesis(entidadeTbCiDestinatario.getUnorDescricaoGenesis());
        //----------------------------------
        newTbCIDestinatario.setIdTipoCoin(TipoCI);

        newTbCIDestinatario.setCoinAssinatura(entidadeTbCiDestinatario.getCoinAssinatura());

        em.persist(newTbCIDestinatario);   
        }catch(javax.persistence.PersistenceException e){
            //e.printStackTrace();
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha no envio da CI - Lida");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            em.close();
            emf.close();            
        }
        
        //----------FIM da rotina para salvar a CI na tabela TB_CI_DESTINATARIO        
        em.getTransaction().commit();            
        em.close();
        emf.close();
        
        
    }
    
    private boolean EnviarMsgCiDesaprovada(int nlIdCI, int nlTabela, TbComunicacaoInterna EntidadeTbCI, TbCiDestinatario EntidadeTbCIDest){
        // Iniciamos a Persistencia de dados
        EntityManager em;
        EntityManagerFactory emf;
        
        emf = Persistence.createEntityManagerFactory("CI_EletronicoPU");
        em = emf.createEntityManager();        
        em.getTransaction().begin();
        //----------------------------------------------------------------
        
        boolean bCondicao = false;
        String strSequencialCI = this.lblNumeroSequencialCI.getText();
        String strlUODescricao = "";
        String strlCITitulo = "";
        String strlConteudo = "";
        String strPara = "";
        //Seteamos Datas
        String strToday = "";
        String strTodayCI = "";
        
        
        //Construimos a frase do campo Assunto:
        //int nIdCoin = 0;
        String strlAssunto = "";        
        
        //nIdCoin = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoin();
        //----------------------------------------
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // utilizado para banco de dados
        DateFormat dfCI = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // utilizado para mostrar na aplicação
        
        java.util.Date data_criacao; 
        data_criacao = new Date();
        strToday = df.format(data_criacao);        
        strTodayCI = dfCI.format(data_criacao); 
        
        //
        MainWindowQueries consulta= new MainWindowQueries();
        strlConteudo = consulta.getMessagemCiDesaprovada("NOTIFICACAO_CI_DESAPROVADA");
        
        //strlAssunto = "CI: " + strSequencialCI + " foi desaprovada pelo Gestor da UO. Ref Id: " + Integer.toString(nIdCoin);
        strlAssunto = "CI: " + strSequencialCI + " foi desaprovada pelo Gestor da UO.";
        
        strlCITitulo = "<br /><p align=\"center\"><b>" + strSequencialCI + "</b></p>";
        //strlConteudo = "<html dir=\"ltr\"><head></head><body contenteditable=\"true\"><p><span style=\"font-family: 'Segoe UI';\">Prezado(a),&nbsp;</span></p><p><font face=\"Segoe UI\">Informamos que a CI foi cancelada pela UO Gestora e por esse motivo não será possível o seu envio para seu(s) destinatário(s).</font></p><p><font face=\"Segoe UI\">Atenciosamente,&nbsp;</font></p><p><font face=\"Segoe UI\">Sistema CI-eletrônica.</font></p></body></html>";
        
        strPara = strPara.concat(strlCITitulo);
        strPara = strPara.concat("<br /><hr><br /><b><FONT COLOR=\"0000FF\">CI Desaprovada</FONT></b>");
        strPara = strPara.concat("<br /><hr><br /><FONT COLOR=\"000000\">De: <b>" + this.lblNomeUO.getText() + "</b></FONT><br />");
        strPara = strPara.concat("<FONT COLOR=\"000000\">Usuário remitente: " + this.lblNomeUsuario.getText() + "</FONT><br /><br />");
        strPara = strPara.concat("<FONT COLOR=\"000000\">Assunto: " + strlAssunto + "</FONT><br /><br />");
        strPara = strPara.concat("<FONT COLOR=\"000000\">Data criação: " + strTodayCI + "</FONT><br /><br />");
        strPara = strPara.concat(strlConteudo);
        
        switch (nlTabela){
            case 1: //CI Desaprovado - Caixa Enviados
                //Passo 1 - Guardar na tabela TB_COMUICACAO_INTERNA
                TbComunicacaoInterna newTbCI = new TbComunicacaoInterna();
                TbTipoComunicacoInterna nlTipoCI = new TbTipoComunicacoInterna(6);  //6 ==> CI Desaprovada
                //Enviar messagem informando que CI não foi autorizado
                //Seteamos entity TB_COMUNICACAO_INTERNA
                try {
                    newTbCI.setCoinAssunto("CI Não foi autorizada pela Unidade Gestora");        
                    newTbCI.setCoinConteudo(strPara);
                    newTbCI.setIdUsuario(this.nIdUsuarioLogado);
                    newTbCI.setUsuNomeCompleto(this.lblNomeUsuario.getText());
                    newTbCI.setIdUnidadeOrganizacional(this.nIdUnidadeOrganizacional);
                    newTbCI.setUnorDescricao(this.lblNomeUO.getText());
                    newTbCI.setIdUoGestor(this.nIdUOGestor);
                    newTbCI.setCoinAutorizado(true);
                    newTbCI.setIdTipoCoin(nlTipoCI);
                    newTbCI.setCoinApensamento("");
                    newTbCI.setCoinNumero(EntidadeTbCI.getCoinNumero());    //Número Sequencial não é incrementado.
                    newTbCI.setCoinUoArquivado(false);
                    newTbCI.setCoinUoGestorArquivado(false);
                    newTbCI.setCoinDataCriacao(data_criacao);
                    newTbCI.setCoinReadOnly(false);
                    newTbCI.setCoinTemAnexos(false);

                    newTbCI.setIdCoinGenesis(EntidadeTbCI.getIdCoinGenesis());
                    newTbCI.setIdUnorGenesis(EntidadeTbCI.getIdUnorGenesis());
                    newTbCI.setCoinNumeroGenesis(EntidadeTbCI.getCoinNumeroGenesis());
                    newTbCI.setCoinHistoricoAnexos(EntidadeTbCI.getCoinHistoricoAnexos());
                    newTbCI.setUnorDescricaoGenesis(EntidadeTbCI.getUnorDescricaoGenesis());  

                    newTbCI.setCoinAssinatura(EntidadeTbCI.getCoinAssinatura()); // Assinatura é igual à CI desaprovada

                    em.persist(newTbCI);
                    em.flush();

                    //Passo 2 - Guardamos na Tabela TB_CI_DESTINATARIO
                    long IdCoin = newTbCI.getIdCoin();

                    TbCiDestinatario newTbCIDestinatario = new TbCiDestinatario();       

                    TbComunicacaoInterna nCoinId = new TbComunicacaoInterna((int)IdCoin);

                    TbTipoEnvio nIdTipoEnvio = new TbTipoEnvio(1); //1 - Tipo = ENVIADO "PARA"

                    newTbCIDestinatario.setIdCoin(nCoinId);
                    newTbCIDestinatario.setIdUsuarioRemitente(this.nIdUsuarioLogado);
                    newTbCIDestinatario.setUsuNomeCompletoRemitente(this.lblNomeUsuario.getText());
                    newTbCIDestinatario.setIdUoRemitente(this.nIdUnidadeOrganizacional);
                    newTbCIDestinatario.setInorDescricaoRemitente(this.lblNomeUO.getText());
                    newTbCIDestinatario.setIdUoDestinatario(EntidadeTbCI.getIdUnidadeOrganizacional());
                    newTbCIDestinatario.setUnorDescricaoDestinatario(EntidadeTbCI.getUnorDescricao());
                    newTbCIDestinatario.setIdUoGestorDestinatario(this.nIdUnidadeOrganizacional);
                    newTbCIDestinatario.setCoinDestinatarioGestorAutorizado(true);
                    newTbCIDestinatario.setCoinDestinatarioUoArquivado(false);
                    newTbCIDestinatario.setCoinDestinatarioUoGestorArquivado(false);
                    newTbCIDestinatario.setCoinDestinatarioAssunto("CI Não foi autorizada pela Unidade Gestora");
                    newTbCIDestinatario.setCoinDestinatarioConteudo(strPara);
                    newTbCIDestinatario.setCoinDestinatarioPendente(false);
                    newTbCIDestinatario.setCoinDestinatarioLido(false);
                    newTbCIDestinatario.setCoinDestinatarioDataCriacao(data_criacao);
                    newTbCIDestinatario.setIdTipoEnvio(nIdTipoEnvio); // 1 ==> CI normal
                    newTbCIDestinatario.setCoinDestinatarioNumero(EntidadeTbCI.getCoinNumero());    //Número Sequencial não é incrementado.
                    newTbCIDestinatario.setCoinDestinatarioReadOnly(false);
                    newTbCIDestinatario.setCoinDestinatarioTemAnexos(false);
                    newTbCIDestinatario.setCoinRemitenteGestorAutorizado(true);

                    //Variaveis Genesis
    //                newTbCIDestinatario.setIdCoinGenesis(nlIdCoinGenesis);
    //                newTbCIDestinatario.setIdUnorGenesis(nlIdUnorGenesis);
    //                newTbCIDestinatario.setCoinNumeroGenesis(nlCoinNumeroGenesis);
    //                newTbCIDestinatario.setCoinHistoricoAnexos(strCoinHistoricoAnexos);
    //                newTbCIDestinatario.setUnorDescricaoGenesis(strUnorDescricaoGenesis);

                    newTbCIDestinatario.setIdCoinGenesis(EntidadeTbCI.getIdCoinGenesis());
                    newTbCIDestinatario.setIdUnorGenesis(EntidadeTbCI.getIdUnorGenesis());
                    newTbCIDestinatario.setCoinNumeroGenesis(EntidadeTbCI.getCoinNumeroGenesis());
                    newTbCIDestinatario.setCoinHistoricoAnexos(EntidadeTbCI.getCoinHistoricoAnexos());
                    newTbCIDestinatario.setUnorDescricaoGenesis(EntidadeTbCI.getUnorDescricaoGenesis()); 
                    //----------------------------------
                    newTbCIDestinatario.setIdTipoCoin(nlTipoCI);    ////6 ==> CI Desaprovada

                    newTbCIDestinatario.setCoinAssinatura(EntidadeTbCI.getCoinAssinatura()); // Assinatura é igual à CI desaprovada

                    em.persist(newTbCIDestinatario);
                    
                    em.getTransaction().commit();            
                    em.close();
                    emf.close();

                    bCondicao = true;
                
                }catch(javax.persistence.PersistenceException e){
                    //e.printStackTrace();
                    em.close();
                    emf.close();            
                    bCondicao = false;
                }
                break;
            case 2: //CI Desaprovado - Caixa Recebidos
                
                //Passo 1 - Guardar na tabela TB_COMUICACAO_INTERNA
                TbComunicacaoInterna newTbCI2 = new TbComunicacaoInterna();
                TbTipoComunicacoInterna nlTipoCI2 = new TbTipoComunicacoInterna(6);  //6 ==> CI Desaprovada
                //Enviar messagem informando que CI não foi autorizado
                //Seteamos entity TB_COMUNICACAO_INTERNA
                try {
                    newTbCI2.setCoinAssunto("CI Não foi autorizada pela Unidade Gestora");        
                    newTbCI2.setCoinConteudo(strPara);
                    newTbCI2.setIdUsuario(this.nIdUsuarioLogado);
                    newTbCI2.setUsuNomeCompleto(this.lblNomeUsuario.getText());
                    newTbCI2.setIdUnidadeOrganizacional(this.nIdUnidadeOrganizacional);
                    newTbCI2.setUnorDescricao(this.lblNomeUO.getText());
                    newTbCI2.setIdUoGestor(this.nIdUOGestor);
                    newTbCI2.setCoinAutorizado(true);
                    newTbCI2.setIdTipoCoin(nlTipoCI2);
                    newTbCI2.setCoinApensamento("");
                    newTbCI2.setCoinNumero(EntidadeTbCIDest.getCoinDestinatarioNumero());    //Número Sequencial não é incrementado.
                    newTbCI2.setCoinUoArquivado(false);
                    newTbCI2.setCoinUoGestorArquivado(false);
                    newTbCI2.setCoinDataCriacao(data_criacao);
                    newTbCI2.setCoinReadOnly(false);
                    newTbCI2.setCoinTemAnexos(false);

                    newTbCI2.setIdCoinGenesis(EntidadeTbCIDest.getIdCoinGenesis());
                    newTbCI2.setIdUnorGenesis(EntidadeTbCIDest.getIdUnorGenesis());
                    newTbCI2.setCoinNumeroGenesis(EntidadeTbCIDest.getCoinNumeroGenesis());
                    newTbCI2.setCoinHistoricoAnexos(EntidadeTbCIDest.getCoinHistoricoAnexos());
                    newTbCI2.setUnorDescricaoGenesis(EntidadeTbCIDest.getUnorDescricaoGenesis());  

                    newTbCI2.setCoinAssinatura(EntidadeTbCIDest.getCoinAssinatura()); // Assinatura é igual à CI desaprovada

                    em.persist(newTbCI2);
                    em.flush();

                    //Passo 2 - Guardamos na Tabela TB_CI_DESTINATARIO
                    long IdCoin2 = newTbCI2.getIdCoin();

                    TbCiDestinatario newTbCIDestinatario2 = new TbCiDestinatario();       

                    TbComunicacaoInterna nCoinId2 = new TbComunicacaoInterna((int)IdCoin2);

                    TbTipoEnvio nIdTipoEnvio2 = new TbTipoEnvio(1); //1 - Tipo = ENVIADO "PARA"

                    newTbCIDestinatario2.setIdCoin(nCoinId2);
                    newTbCIDestinatario2.setIdUsuarioRemitente(this.nIdUsuarioLogado);
                    newTbCIDestinatario2.setUsuNomeCompletoRemitente(this.lblNomeUsuario.getText());
                    newTbCIDestinatario2.setIdUoRemitente(this.nIdUnidadeOrganizacional);
                    newTbCIDestinatario2.setInorDescricaoRemitente(this.lblNomeUO.getText());
                    newTbCIDestinatario2.setIdUoDestinatario(EntidadeTbCIDest.getIdUoRemitente());
                    newTbCIDestinatario2.setUnorDescricaoDestinatario(EntidadeTbCIDest.getInorDescricaoRemitente());
                    newTbCIDestinatario2.setIdUoGestorDestinatario(EntidadeTbCIDest.getIdCoin().getIdUoGestor());
                    newTbCIDestinatario2.setCoinDestinatarioGestorAutorizado(true);
                    newTbCIDestinatario2.setCoinDestinatarioUoArquivado(false);
                    newTbCIDestinatario2.setCoinDestinatarioUoGestorArquivado(false);
                    newTbCIDestinatario2.setCoinDestinatarioAssunto("CI Não foi autorizada pela Unidade Gestora");
                    newTbCIDestinatario2.setCoinDestinatarioConteudo(strPara);
                    newTbCIDestinatario2.setCoinDestinatarioPendente(false);
                    newTbCIDestinatario2.setCoinDestinatarioLido(false);
                    newTbCIDestinatario2.setCoinDestinatarioDataCriacao(data_criacao);
                    newTbCIDestinatario2.setIdTipoEnvio(nIdTipoEnvio2); // 1 ==> Enviado Para
                    newTbCIDestinatario2.setCoinDestinatarioNumero(EntidadeTbCIDest.getCoinDestinatarioNumero());    //Número Sequencial não é incrementado.
                    newTbCIDestinatario2.setCoinDestinatarioReadOnly(false);
                    newTbCIDestinatario2.setCoinDestinatarioTemAnexos(false);
                    newTbCIDestinatario2.setCoinRemitenteGestorAutorizado(true);
                   
                    newTbCIDestinatario2.setIdCoinGenesis(EntidadeTbCIDest.getIdCoinGenesis());
                    newTbCIDestinatario2.setIdUnorGenesis(EntidadeTbCIDest.getIdUnorGenesis());
                    newTbCIDestinatario2.setCoinNumeroGenesis(EntidadeTbCIDest.getCoinNumeroGenesis());
                    newTbCIDestinatario2.setCoinHistoricoAnexos(EntidadeTbCIDest.getCoinHistoricoAnexos());
                    newTbCIDestinatario2.setUnorDescricaoGenesis(EntidadeTbCIDest.getUnorDescricaoGenesis()); 
                    //----------------------------------
                    newTbCIDestinatario2.setIdTipoCoin(nlTipoCI2);    ////6 ==> CI Desaprovada

                    newTbCIDestinatario2.setCoinAssinatura(EntidadeTbCIDest.getCoinAssinatura()); // Assinatura é igual à CI desaprovada

                    em.persist(newTbCIDestinatario2);
                    
                    //Passo 3 enviamos Com cópia para o gestor
                    //Verificamos se o gestor da UO remitente é igual ao remitente
                    //para evitar enviar 2 vezes
                    if((EntidadeTbCIDest.getIdUoRemitente())!=(EntidadeTbCIDest.getIdCoin().getIdUoGestor())){
                        //Procuramos descrição da UO gestora
                        int nIdUOGestora = 0;
                        TbComunicacaoInterna nIdCoin = new TbComunicacaoInterna(0);
                        nIdCoin = EntidadeTbCIDest.getIdCoin();
                        nIdUOGestora = EntidadeTbCIDest.getIdCoin().getIdUoGestor();
                        TbUnidadeOrganizacional newTbUnidadeOrganizacional = new TbUnidadeOrganizacional();
                        
                        MainWindowQueries consulta2= new MainWindowQueries();
                        newTbUnidadeOrganizacional = consulta2.getUODescricao(nIdUOGestora);
                        strlUODescricao = newTbUnidadeOrganizacional.getUnorNome();
                        
                        TbCiDestinatario newTbCIDestinatario3 = new TbCiDestinatario();  
                        TbTipoEnvio nIdTipoEnvio3 = new TbTipoEnvio(2); //2 - Tipo = ENVIADO "COM COPIA"
                        try {
                            newTbCIDestinatario3.setIdCoin(nCoinId2);

                            newTbCIDestinatario3.setIdUsuarioRemitente(this.nIdUsuarioLogado);
                            newTbCIDestinatario3.setUsuNomeCompletoRemitente(this.lblNomeUsuario.getText());
                            newTbCIDestinatario3.setIdUoRemitente(this.nIdUnidadeOrganizacional);
                            newTbCIDestinatario3.setInorDescricaoRemitente(this.lblNomeUO.getText());
                            newTbCIDestinatario3.setIdUoDestinatario(EntidadeTbCIDest.getIdCoin().getIdUoGestor());
                            newTbCIDestinatario3.setUnorDescricaoDestinatario(strlUODescricao);
                            newTbCIDestinatario3.setIdUoGestorDestinatario(EntidadeTbCIDest.getIdCoin().getIdUoGestor());
                            newTbCIDestinatario3.setCoinDestinatarioGestorAutorizado(true);
                            newTbCIDestinatario3.setCoinDestinatarioUoArquivado(false);
                            newTbCIDestinatario3.setCoinDestinatarioUoGestorArquivado(false);
                            newTbCIDestinatario3.setCoinDestinatarioAssunto("CI Não foi autorizada pela Unidade Gestora");
                            newTbCIDestinatario3.setCoinDestinatarioConteudo(strPara);
                            newTbCIDestinatario3.setCoinDestinatarioPendente(false);
                            newTbCIDestinatario3.setCoinDestinatarioLido(false);
                            newTbCIDestinatario3.setCoinDestinatarioDataCriacao(data_criacao);
                            newTbCIDestinatario3.setIdTipoEnvio(nIdTipoEnvio3); // 2 ==> Enviado "Com cópia"
                            newTbCIDestinatario3.setCoinDestinatarioNumero(EntidadeTbCIDest.getCoinDestinatarioNumero());    //Número Sequencial não é incrementado.
                            newTbCIDestinatario3.setCoinDestinatarioReadOnly(false);
                            newTbCIDestinatario3.setCoinDestinatarioTemAnexos(false);
                            newTbCIDestinatario3.setCoinRemitenteGestorAutorizado(true);

                            newTbCIDestinatario3.setIdCoinGenesis(EntidadeTbCIDest.getIdCoinGenesis());
                            newTbCIDestinatario3.setIdUnorGenesis(EntidadeTbCIDest.getIdUnorGenesis());
                            newTbCIDestinatario3.setCoinNumeroGenesis(EntidadeTbCIDest.getCoinNumeroGenesis());
                            newTbCIDestinatario3.setCoinHistoricoAnexos(EntidadeTbCIDest.getCoinHistoricoAnexos());
                            newTbCIDestinatario3.setUnorDescricaoGenesis(EntidadeTbCIDest.getUnorDescricaoGenesis()); 
                            //----------------------------------
                            newTbCIDestinatario3.setIdTipoCoin(nlTipoCI2);    ////6 ==> CI Desaprovada

                            newTbCIDestinatario3.setCoinAssinatura(EntidadeTbCIDest.getCoinAssinatura()); // Assinatura é igual à CI desaprovada

                            em.persist(newTbCIDestinatario3);
                        }catch(javax.persistence.PersistenceException e){
                            //e.printStackTrace();
                            em.close();
                            emf.close();            
                            bCondicao = false;
                        } 
                    }
                    
                    em.getTransaction().commit();            
                    em.close();
                    emf.close();

                    bCondicao = true;
                
                }catch(javax.persistence.PersistenceException e){
                    //e.printStackTrace();
                    em.close();
                    emf.close();            
                    bCondicao = false;
                }                
                
                break;
            default:
                bCondicao = false;
                break;
        }
    return bCondicao;    
    }
    
    @FXML
    private void handleBtnEditarCI(ActionEvent event) throws IOException{
        if (null == TbViewGeral2.getSelectionModel().getSelectedItem()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("CI não foi selecionado.");
            alert.setContentText("Favor selecionar uma CI da tabela");
            alert.showAndWait();
        }else{            
                // do something
            int nlIdCI = 0;
            String strHtmlUpdateCI = "";
            
            nlIdCI = TbViewGeral2.getSelectionModel().getSelectedItem().getIntp_idCoin();
            strHtmlUpdateCI = TbViewGeral2.getSelectionModel().getSelectedItem().getStrp_Conteudo();
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
    
    public Long RecordCountTbCiDestinatario(int nidUoDestinatario, int nlTipoPerfil){
        
        EntityManager em;
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("CI_EletronicoPU");
        em = emf.createEntityManager();   
        Long lResultado = 0L;
        
        try{                     
        em.getTransaction().begin();
        if (1 == nlTipoPerfil){ //Perfil Gestor
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(t) FROM TbCiDestinatario t WHERE t.idUoDestinatario = :idUoDestinatario AND t.coinRemitenteGestorAutorizado = 1 AND t.coinDestinatarioGestorAutorizado = 1 AND t.coinDestinatarioPendente = 0 AND t.coinDestinatarioUoArquivado = 0 AND t.coinDestinatarioLido = 0 AND t.coinCancelado = 0",Long.class)            
                                            .setParameter("idUoDestinatario", nidUoDestinatario);
            lResultado = query.getSingleResult();
        }else{
            List<Integer> nValues = new ArrayList<>();
            nValues.add(1);
            nValues.add(2);
            nValues.add(4);
            nValues.add(6);
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(t) FROM TbCiDestinatario t WHERE t.idUoDestinatario = :idUoDestinatario AND t.coinRemitenteGestorAutorizado = 1 AND t.coinDestinatarioGestorAutorizado = 1 AND t.coinDestinatarioPendente = 0 AND t.coinDestinatarioUoArquivado = 0 AND t.coinDestinatarioLido = 0 AND t.coinCancelado = 0 AND t.idTipoCoin IN :idTipoCoin",Long.class)            
                                            .setParameter("idUoDestinatario", nidUoDestinatario)
                                            .setParameter("idTipoCoin",nValues);
            lResultado = query.getSingleResult();
        }        
                                            
        //Long Resultado = query.getSingleResult();
        em.close();
        emf.close();
        return lResultado;
        
        }catch(javax.persistence.PersistenceException e){
            System.out.println("Erro:" + e);
            em.close();
            emf.close();
            return 0L;
        }   
        //return 0L;
    }
    
    public Long RecordCountMarcadosComoPendentesTbCiDestinatario(int nidUoDestinatario, int nlTipoPerfil){
        // Iniciamos código para calular as datas a serem utilizadas
        String strlDias = "";
        int nlDias = 0;
        MainWindowQueries consulta= new MainWindowQueries();
        strlDias = consulta.getMessagemCiDesaprovada("NOTIFICACAO_PENDENCIA");
        strlDias = strlDias.trim();
        nlDias = Integer.parseInt(strlDias);
        
        Date dateInstance = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateInstance);
        cal.add(Calendar.DATE, -nlDias);
        Date dateSubtractDays = cal.getTime();
        System.out.println("Date = " + dateSubtractDays);
        //----------------------------------------------------------
        EntityManager em;
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("CI_EletronicoPU");
        em = emf.createEntityManager();   
        Long lResultado = 0L;
        
        try{                     
        em.getTransaction().begin();
        if (1 == nlTipoPerfil){ //Perfil Gestor
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(t) FROM TbCiDestinatario t WHERE t.idUoDestinatario = :idUoDestinatario AND t.coinRemitenteGestorAutorizado = 1 AND t.coinDestinatarioGestorAutorizado = 1 AND t.coinDestinatarioPendente = 1 AND t.coinDestinatarioUoArquivado = 0 AND t.coinDestinatarioLido = 0 AND t.coinCancelado = 0 AND t.coinDestinatarioDataMarcadoPendente < :data",Long.class)            
                                            .setParameter("idUoDestinatario", nidUoDestinatario)
                                            .setParameter("data", dateSubtractDays);
            lResultado = query.getSingleResult();
        }else{
            List<Integer> nValues = new ArrayList<>();
            nValues.add(1);
            nValues.add(2);
            nValues.add(4);
            nValues.add(6);
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(t) FROM TbCiDestinatario t WHERE t.idUoDestinatario = :idUoDestinatario AND t.coinRemitenteGestorAutorizado = 1 AND t.coinDestinatarioGestorAutorizado = 1 AND t.coinDestinatarioPendente = 1 AND t.coinDestinatarioUoArquivado = 0 AND t.coinDestinatarioLido = 0 AND t.coinCancelado = 0 AND t.coinDestinatarioDataMarcadoPendente < :data AND t.idTipoCoin IN :idTipoCoin",Long.class)            
                                            .setParameter("idUoDestinatario", nidUoDestinatario)
                                            .setParameter("data", dateSubtractDays)
                                            .setParameter("idTipoCoin",nValues);
            lResultado = query.getSingleResult();
        }        
                                            
        //Long Resultado = query.getSingleResult();
        em.close();
        emf.close();
        return lResultado;
        
        }catch(javax.persistence.PersistenceException e){
            System.out.println("Erro:" + e);
            em.close();
            emf.close();
            return 0L;
        }   
        //return 0L;
    }
    
    public Long RecordCountEnviadosTbComunicacaoInterna(int nidUoDestinatario, int nlTipoPerfil){
        EntityManager em;
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("CI_EletronicoPU");
        em = emf.createEntityManager();       
        
        
        Long lResultado = 0L;
        try{                     
        em.getTransaction().begin();
        if (1 == nlTipoPerfil){
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(t) FROM TbComunicacaoInterna t WHERE t.idUoGestor = :idUoGestor AND t.coinAutorizado = 0 AND t.coinUoGestorArquivado = 0 AND t.coinCancelado = 0",Long.class)            
                                            .setParameter("idUoGestor", nidUoDestinatario);
        lResultado = query.getSingleResult();
        } else {
            List<Integer> nValues = new ArrayList<>();
            nValues.add(1);
            nValues.add(2);
            nValues.add(4);
            nValues.add(6);
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(t) FROM TbComunicacaoInterna t WHERE t.idUoGestor = :idUoGestor AND t.coinAutorizado = 0 AND t.coinUoGestorArquivado = 0 AND t.coinCancelado = 0 AND t.idTipoCoin IN :idTipoCoin",Long.class)            
                                            .setParameter("idUoGestor", nidUoDestinatario)
                                            .setParameter("idTipoCoin",nValues);
            lResultado = query.getSingleResult();
        }
                                            
        //Long Resultado = query.getSingleResult();
        em.close();
        emf.close();
        return lResultado;
        
        }catch(javax.persistence.PersistenceException e){
            System.out.println("Erro:" + e);
            em.close();
            emf.close();     
            return 0L;
        }   
        //return 0L;
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
        
        MainWindowQueries consulta  = new MainWindowQueries();          
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
/*    
    private void ArquivarCiRespondidaOuEncaminhada(int nIdUO, int nlIdCI, int nlTabela){
        
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
        
        
        boolean bUpdate = false;  
        TbComunicacaoInterna nIdCoin = new TbComunicacaoInterna(nlIdCI);
        
        try{
            bUpdate = consulta.ArquivarCIRespondidaOuEncaminhada(nIdCoin, nIdUO, nlButtonSelected);                    
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Arquivar CI Respondida ou  CI Encaminhada");
            alert.setHeaderText("Tabela TB_CI_DESTINATARIO");
            alert.setContentText(e.getMessage());
            alert.showAndWait();                                            
        }
        if(bUpdate){
            
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
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Não foi possível arquivar CI");
            alert.setContentText("Favor contatar o Administrador do sistema");
            alert.showAndWait();
            
        }
    }
*/    
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
        
        MainWindowQueries consulta  = new MainWindowQueries();          
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
                boolean blcoinUOGestorArquivado = false;
                
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
                        
                        //Se tentamos desarquivar CI que foi do tipo "Enviados aprovado pelo Gestor" simplesmente não pode ser feito
                        blcoinUOGestorArquivado = TbViewGeral.getSelectionModel().getSelectedItem().getBoolp_ArquivadoUOGestor();
                        if (blcoinUOGestorArquivado){
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erro");
                            alert.setHeaderText("CI não pode ser Desarquivada.");
                            alert.setContentText("Provavelmente a CI foi do tipo: 'Enviada e aguardando aprovação'");
                            alert.showAndWait(); 
                        }else{
                        DesarquivarCI(nIdCI, 1);
                        }
                        //TableViewRefresh(nBotao);
                        break;
                    case 2: // 2 ==> TB_CI_DESTINATARIO
                        nIdCI = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoinDestinatario();
                        //Se tentamos desarquivar CI que foi do tipo "Enviados aprovado pelo Gestor" simplesmente não pode ser feito
                        blcoinUOGestorArquivado = TbViewGeral.getSelectionModel().getSelectedItem().getBoolp_ArquivadoPeloUOGestor();
                        if (blcoinUOGestorArquivado){
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Erro");
                            alert.setHeaderText("CI não pode ser Desarquivada.");
                            alert.setContentText("Provavelmente a CI foi do tipo: 'Recebida e aguardando aprovação'");
                            alert.showAndWait(); 
                        }else{
                            DesarquivarCI(nIdCI, 2);
                        }
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
            alert.setHeaderText("CI não foi selecionada.");
            alert.setContentText("Favor selecionar uma CI da tabela");
            alert.showAndWait();
        }else{
            
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
        lblCaixa.setText("CAIXA DE RECEBIDAS");
        
        //Calculamos a quantidade de registros que ainda não foram lidos
        //Unidade Organizacional utilizado nas queries
        //Tipo de perfil
        int nlTipoPerfil = 0;
        nlTipoPerfil = this.nTipoPerfil;
        
        int nlIdUnidadeOrganizacional = 0;
        nlIdUnidadeOrganizacional = this.nIdUnidadeOrganizacional;
        
        Long nlResultado = 0L;
        
        nlResultado = RecordCountTbCiDestinatario(nlIdUnidadeOrganizacional, nlTipoPerfil);
        
        String strLabel = "";        
        strLabel = "CIs não lidos = ";
        strLabel = strLabel.concat(Long.toString(nlResultado));
        lblRecordCount.setText("");
        lblRecordCount.setText(strLabel);
                
        setBotoesMainWindow(nTipoPerfil);
        
        btnAprovarCI.setDisable(true);
        btnEncaminharCI.setDisable(false);
        btnResponderCI.setDisable(false);
        btnArquivarCI.setDisable(false);
        btnDesarquivarCI.setDisable(true); 
        
        //btnEditarCI.setVisible(false);
        btnEditarCI.setDisable(true);
        btnDesaprovarCI.setDisable(true);
        btnMarcarcomoPendencia.setDisable(false);
        
        clearTelas();
        
        boolean bPreencherTableView2 = true;
        
        PreencherCaixaEntrada(2, bPreencherTableView2);
        
    }
    @FXML
    private void handleBtnCaixaSaida(ActionEvent event) throws IOException {
        lblCaixa.setText("");
        lblCaixa.setText("CAIXA DE ENVIADOS");
        
        lblRecordCount.setText("");
        setBotoesMainWindow(nTipoPerfil);       
        
        btnAprovarCI.setDisable(true);
        btnEncaminharCI.setDisable(true);
        btnResponderCI.setDisable(true);
        btnArquivarCI.setDisable(false);
        btnDesarquivarCI.setDisable(true);  
        
        //Calculamos a quantidade de registros que ainda não foram lidos
        //Unidade Organizacional utilizado nas queries
        //Tipo de perfil
        int nlTipoPerfil = 0;
        nlTipoPerfil = this.nTipoPerfil;
        
        int nlIdUnidadeOrganizacional = 0;
        nlIdUnidadeOrganizacional = this.nIdUnidadeOrganizacional;
        Long nlResultado = 0L;
        nlResultado = RecordCountEnviadosTbComunicacaoInterna(nlIdUnidadeOrganizacional, nlTipoPerfil);
        String strLabel = "";
        
        strLabel = "Caixa de enviados";
        
        btnCaixaSaida.setText("");
        if (0 == nlResultado){        
            btnCaixaSaida.setText(strLabel);
        }else {
            strLabel = strLabel.concat(" Aprovação:" + Long.toString(nlResultado));
            btnCaixaSaida.setText(strLabel);
        }
        
        clearTelas();
        
        ngTabela = 1; // TB_COMUNICACAO_INTERNA
        ngBotao = 6;
        
        int nlTipoPreenchimento = 0;
        nlTipoPreenchimento = this.ngBotao;
        
        boolean blPreencherTableView2 = true;
        
        PreencherCaixaSaida(nlTipoPreenchimento, blPreencherTableView2);
        
    }
    private void PreencherCaixaSaida(int nlTipoPreenchimento, boolean blPreencherTableView2){
                
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
        
        //Variavel para construção da Ci Sequencial
        String strCiSequencial = "";
        String strAno = "";        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy"); 
        //---------------------------------------------------
        
        
        String strlDescricaoUODestinatario = "";
        
        //Assinatura MD5
        String strlAssinatura = "";
        
        //CI Lido ou não
        boolean bCoinLido = false;
        
        boolean bCancelado = false;
        
        //Id Unidade Organizacional logado
        int nlIdUnidadeOrganizacional = 0;
        nlIdUnidadeOrganizacional = this.nIdUnidadeOrganizacional;
        
        //Tipo de perfil
        int nlTipoPerfil = 0;
        nlTipoPerfil = this.nTipoPerfil;
        
        //obslistaTbCIPorAprovar = FXCollections.observableArrayList();
        
        //Iniciamos a criação da TableView
        //List<TbCiDestinatario> listaCiDestinatario = new ArrayList<TbCiDestinatario>();
        List<TbComunicacaoInterna> listaComunicacaoInterna = new ArrayList<TbComunicacaoInterna>();
        ObservableList<TbCIPorAprovar> obslistaTbCaixaSaida = FXCollections.observableArrayList(); 
        
        MainWindowQueries consulta  = new MainWindowQueries();
        
        //Para construir campo destinatario
        MainWindowQueries consultaDestinatario  = new MainWindowQueries();
        List<TbCiDestinatario> listaDestinatario = new ArrayList<TbCiDestinatario>();
        
        String strDestinatarios = "";
        //----------------------------------------------------------------------
        
        
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
                TbViewGeral2.setVisible(true);
                TbViewGeral.setPrefHeight(328);
                lblAguardandoAprovacao.setVisible(true);
                
                if (1 == nlTipoPerfil){ //Perfil Gestor
                    listaComunicacaoInterna = consulta.getlistaTbComunicacaoInternaEnviados(nlIdUnidadeOrganizacional);                    
                    //listaCiDestinatario = consulta.getlistaCaixaEntrada(this.nIdUnidadeOrganizacional);
                } else {
                    listaComunicacaoInterna = consulta.getlistaTbComunicacaoInternaEnviadosPerfil2(nlIdUnidadeOrganizacional);
                }
                break;
            case 5: //5-Caixa de enviados (arquivadas)
                ngBotao = 5;
                ngTabela = 1; // TB_COMUNICACAO_INTERNA
                TbViewGeral2.setVisible(false);
                TbViewGeral.setPrefHeight(656);
                lblAguardandoAprovacao.setVisible(false);
                if (1 == nlTipoPerfil){ //Perfil Gestor
                    listaComunicacaoInterna = consulta.getlistaCaixaSaidaArquivados(nlIdUnidadeOrganizacional);
                } else {
                    listaComunicacaoInterna = consulta.getlistaCaixaSaidaArquivadosPerfil2(nlIdUnidadeOrganizacional);
                }
                break;                
        }        
        
        for(TbComunicacaoInterna l : listaComunicacaoInterna){        

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

            bCoinLido = false;
            
            bCancelado = l.getCoinCancelado();
            
            //Construção da CI sequencial            
            strAno = sdf.format(dataCriacao);
            strCiSequencial = strUnorDescricaoGenesis + "-" + String.format("%05d",nlCoinNumeroGenesis)+ "-" + strAno;
            //----------------------------------------------------------
            
            //Construção dos destinatarios
            strDestinatarios = "";
            TbComunicacaoInterna nTbIdCoin = new TbComunicacaoInterna(nIdCoin);
            listaDestinatario = consultaDestinatario.getlistaDestinatarios(nTbIdCoin);

            for (TbCiDestinatario lista : listaDestinatario){
                strDestinatarios = strDestinatarios.concat(lista.getUnorDescricaoDestinatario() + "; ");                        
            }

            //----------------------------------------------------------
            
            obslistaTbCaixaSaida.add(new TbCIPorAprovar(nIdCoin, strAssunto, strConteudo, nIdUsuario, strUsuarioNomeCompleto, nIdUO, strUODescricao, 
                    nIdUOGestor, bAutorizado, nTipoCoin, strApensamento, nSequencial, bArquivadoUO, bArquivadoUOGestor, dataCriacao, strDataCriacao, 
                    dataAutorizado, bCoinReadOnly, bTemAnexos ,nIdTabelaFonte,
                    nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
                    strlDescricaoUODestinatario,
                    strlAssinatura, bCoinLido, bCancelado,
                    strCiSequencial, strDestinatarios));
        }
        //	ClDataEnvio; ClUORemitente; ClAutorRemitente; ClAssunto;
        ClLido.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Boolean>("boolp_CoinLido"));
        ClIdCoin.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Integer>("intp_idCoin"));
        
        ClICiSequencial.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Integer>("strp_CiSequencial"));
        
        //ClUODestinatario.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUODestinatario"));
        ClUODestinatario.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_Destinatarios"));
        ClDataEnvio.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_dataCriacao"));        
        ClUORemitente.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUORemitente"));        
        ClAutorRemitente.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_UsuarioNomeCompleto"));        
        ClAssunto.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_Assunto"));
        TbViewGeral.setItems(obslistaTbCaixaSaida);
        //TbViewGeral.refresh();
        
        
        // Código para mudar cor do registro

            ClLido.setCellFactory(new Callback<TableColumn<TbCIPorAprovar, Boolean>, TableCell<TbCIPorAprovar, Boolean>>() {
                @Override public TableCell<TbCIPorAprovar, Boolean> call(TableColumn<TbCIPorAprovar, Boolean> soCalledFriendBooleanTableColumn) {
                    return new TableCell<TbCIPorAprovar, Boolean>() {
                        @Override public void updateItem(final Boolean item, final boolean empty) {
                            super.updateItem(item, empty);

                            // clear any custom styles
                            //this.getStyleClass().remove("willPayCell");
                            //this.getStyleClass().remove("wontPayCell");
                            this.getTableRow().getStyleClass().remove("willPayRow");
                            this.getTableRow().getStyleClass().remove("wontPayRow");
                            this.getTableRow().getStyleClass().remove("porAprovarRow");
                            this.getTableRow().getStyleClass().remove("caixaEnviadosRow");

                            // update the item and set a custom style if necessary
                            if (item != null) {
                              setText(item.toString());
                              //this.getStyleClass().add(item ? "willPayCell" : "wontPayCell");
                              this.getTableRow().getStyleClass().add(item ? "willPayRow" : "caixaEnviadosRow");
                            }
                        }
                    };
                }
            });
        
        Platform.runLater(new Runnable(){
            @Override
            public void run(){
                TbViewGeral.requestFocus();
                TbViewGeral.getSelectionModel().select(0);
                TbViewGeral.getFocusModel().focus(0);
            }
        });
        
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
                    
                    boolean blCancelado = false;
                    
                    String strCoinHistoricoAnexos = "";
                    
                    String strAssunto = "";
                    
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
                    blCancelado = tbCiPorAprovar.getBoolp_CoinCancelado();
                    strCoinHistoricoAnexos = tbCiPorAprovar.getStrp_CoinHistoricoAnexos();
                    strAssunto = tbCiPorAprovar.getStrp_Assunto();
                    
                    strYear = df.format(dataCriacao);
                    
                    switch (nlTipoPreenchimento)  {
                                case 6: //6-Caixa de enviados
                                    btnAprovarCI.setDisable(true);
                                    btnEncaminharCI.setDisable(true);
                                    btnResponderCI.setDisable(true);
                                    btnArquivarCI.setDisable(false);
                                    btnDesarquivarCI.setDisable(true);
                                    btnHistoricoCI.setDisable(false);
                                    btnMarcarComoLido.setDisable(true);
                                    btnMarcarcomoPendencia.setDisable(true);
                                    //btnEditarCI.setVisible(false);
                                    btnEditarCI.setDisable(true);
                                    btnDesaprovarCI.setDisable(true);
                                    break;
                                case 5: //5-Caixa de enviados (arquivadas)
                                    btnAprovarCI.setDisable(true);
                                    btnEncaminharCI.setDisable(true);
                                    btnResponderCI.setDisable(true);
                                    btnArquivarCI.setDisable(true);
                                    btnDesarquivarCI.setDisable(false);
                                    btnHistoricoCI.setDisable(false);
                                    btnMarcarComoLido.setDisable(true);
                                    btnMarcarcomoPendencia.setDisable(true);
                                    //btnEditarCI.setVisible(false);
                                    btnEditarCI.setDisable(true);
                                    btnDesaprovarCI.setDisable(true);
                                    break;
                
                    }
                    
                    htmlEditorCI.setHtmlText(tbCiPorAprovar.getStrp_Conteudo());     
                    if (strAssunto.equals("CI Não foi autorizada pela Unidade Gestora")){
                                lblNumeroSequencialCI.setText("CI-DESAPROVADA");
                                
                    }else {
                        lblNumeroSequencialCI.setText(strDescricaoUO + "-" + String.format("%05d",nCISequencial)+"-" + strYear);
                    }
                    //lblNumeroSequencialCI.setText(strDescricaoUO + "-" + String.format("%05d",nCISequencial)+"-" + strYear);
                    if ((bTemAnexo) || (strCoinHistoricoAnexos.length()>0) ){
                        PreencherTxtFAnexos(nlIdCI,strCoinHistoricoAnexos);
                    } 
                    else {
                        lviewAnexos.getItems().clear();
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
    
        if (blPreencherTableView2){
            try {
                EnviadosPendentesAprovacao();
            }catch(IOException ex){
                System.out.println("Erro ao preencher tabela Enviados para aprovação: " + ex.getMessage());
            }
            
        }
        
    }
    
    private void EnviadosPendentesAprovacao() throws IOException {
        
//        lblCaixa.setText("");
//        lblCaixa.setText("Caixa de enviados (solicitando aprovação)");       
        
        setBotoesMainWindow(nTipoPerfil);
        
       
        btnEncaminharCI.setDisable(true);
        btnResponderCI.setDisable(true);
        btnDesarquivarCI.setDisable(true);
        btnHistoricoCI.setDisable(true);
        //btnEditarCI.setVisible(true);
        btnMarcarcomoPendencia.setDisable(true);
        
        if ((this.nIdUnidadeOrganizacional == this.nIdUOGestor) &&(this.nTipoPerfil == 1)){
            btnAprovarCI.setDisable(false);
            btnEditarCI.setDisable(false);
            btnDesaprovarCI.setDisable(false);
        }else{
            btnAprovarCI.setDisable(true);
            btnEditarCI.setDisable(true);
            btnDesaprovarCI.setDisable(true);
        }
        
        //clearTelas();
        
        ngTabela = 1; // TB_COMUNICACAO_INTERNA
        //ngBotao = 7;
        
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
        
        //Variavel para construção da Ci Sequencial
        String strCiSequencial = "";
        String strAno = "";        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy"); 
        //----------------------------------------------------
        
        String strlDescricaoUODestinatario = "";
        
        //Assinatura MD5
        String strlAssinatura = "";
        
        boolean bCoinLido = false;
        
        boolean bCancelado = false;
        
        ObservableList<TbCIPorAprovar> obslistaTbCIPorAprovar2;
        obslistaTbCIPorAprovar2 = FXCollections.observableArrayList();
        
        MainWindowQueries consulta2  = new MainWindowQueries();
        List<TbComunicacaoInterna> listaCiSemAprovar2 = new ArrayList<TbComunicacaoInterna>();
        
        //Para construir campo destinatario
        MainWindowQueries consultaDestinatario  = new MainWindowQueries();
        List<TbCiDestinatario> listaDestinatario = new ArrayList<TbCiDestinatario>();
        
        String strDestinatarios = "";
        //----------------------------------------------------------------------
        
        //Deve-se mostrar as Cis para aprovação só para usuário que faz parte da UO Gestora
        if (this.nIdUOGestor == this.nIdUnidadeOrganizacional){
            if (1 == nlTipoPerfil){ //Perfil Gestor
                listaCiSemAprovar2 = consulta2.getlistaTbComunicacaoInternaPorAprovar(this.nIdUOGestor);
            } else {
                listaCiSemAprovar2 = consulta2.getlistaTbComunicacaoInternaPorAprovarPerfil2(this.nIdUOGestor);
            }

                for(TbComunicacaoInterna l : listaCiSemAprovar2){
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
                    
                    bCancelado = l.getCoinCancelado();
                    
                    //Construção da CI sequencial            
                    strAno = sdf.format(dataCriacao);
                    strCiSequencial = strUnorDescricaoGenesis + "-" + String.format("%05d",nlCoinNumeroGenesis)+ "-" + strAno;
                    //----------------------------------------------------------
                    
                    //Construção dos destinatarios
                    strDestinatarios = "";
                    TbComunicacaoInterna nTbIdCoin = new TbComunicacaoInterna(nIdCoin);
                    listaDestinatario = consultaDestinatario.getlistaDestinatariosNaoAutorizados(nTbIdCoin);
                    
                    for (TbCiDestinatario lista : listaDestinatario){
                        strDestinatarios = strDestinatarios.concat(lista.getUnorDescricaoDestinatario() + "; ");                        
                    }
                    
                    //----------------------------------------------------------


        //            obslistaTbCIPorAprovar.add(new TbCIPorAprovar(nIdCoin,strAssunto,strConteudo,nIdUsuario,strUsuarioNomeCompleto,
        //                        nIdUO,strUODescricao,nSequencial,dataCriacao,strDataCriacao,bAutorizado,bTemAnexos,1));
                        obslistaTbCIPorAprovar2.add(new TbCIPorAprovar(nIdCoin, strAssunto, strConteudo, nIdUsuario, strUsuarioNomeCompleto, nIdUO, strUODescricao, 
                                nIdUOGestor, bAutorizado, nTipoCoin, strApensamento, nSequencial, bArquivadoUO, bArquivadoUOGestor, dataCriacao, strDataCriacao, 
                                dataAutorizado, bCoinReadOnly, bTemAnexos ,nIdTabelaFonte,
                                nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
                                strlDescricaoUODestinatario,
                                strlAssinatura, bCoinLido, bCancelado,
                                strCiSequencial, strDestinatarios));
                    } catch (Exception e){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("ObservableList Error");
                        alert.setContentText(e.toString());
                        alert.showAndWait();
                    }
                    }
                //	ClDataEnvio; ClUORemitente; ClAutorRemitente; ClAssunto;
                ClAprovado.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Boolean>("boolp_Autorizado"));
                ClIdCoin2.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Integer>("intp_idCoin"));
                
                ClICiSequencial2.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Integer>("strp_CiSequencial"));
                
                ClUODestinatario2.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUODestinatario"));
                ClDataEnvio2.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_dataCriacao"));        
                ClUORemitente2.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUORemitente"));        
                ClAutorRemitente2.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_UsuarioNomeCompleto"));        
                ClAssunto2.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_Assunto"));
                TbViewGeral2.setItems(obslistaTbCIPorAprovar2);
                //TbViewGeral
                
                ClAprovado.setCellFactory(new Callback<TableColumn<TbCIPorAprovar, Boolean>, TableCell<TbCIPorAprovar, Boolean>>() {
                    @Override public TableCell<TbCIPorAprovar, Boolean> call(TableColumn<TbCIPorAprovar, Boolean> soCalledFriendBooleanTableColumn) {
                        return new TableCell<TbCIPorAprovar, Boolean>() {
                            @Override public void updateItem(final Boolean item, final boolean empty) {
                                super.updateItem(item, empty);

                                // clear any custom styles
                                //this.getStyleClass().remove("willPayCell");
                                //this.getStyleClass().remove("wontPayCell");
                                this.getTableRow().getStyleClass().remove("willPayRow");
                                this.getTableRow().getStyleClass().remove("wontPayRow");
                                this.getTableRow().getStyleClass().remove("porAprovarRow");

                                // update the item and set a custom style if necessary
                                if (item != null) {
                                  setText(item.toString());
                                  //this.getStyleClass().add(item ? "willPayCell" : "wontPayCell");
                                  this.getTableRow().getStyleClass().add(item ? "wontPayRow" : "porAprovarRow");
                                }
                            }
                        };
                    }
                });
               
                TbViewGeral2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                    try{
                        //TableView<TbCIPorAprovar> TbViewGeral = new TableView<>();
                        if(TbViewGeral2.getSelectionModel().getSelectedItem() != null){
                            //TbCIPorAprovar tbCiPorAprovar = TbViewGeral.getSelectionModel().getSelectedItem();
                            //TbCIPorAprovar tbtbCiPorAprovar = new TbCIPorAprovar();
                            TbCIPorAprovar tbCiPorAprovar = TbViewGeral2.getSelectionModel().getSelectedItem();
                            int nCISequencial = 0;
                            int nlIdCI = 0;
                            boolean bTemAnexo = false;
                            
                            boolean blCancelado = false;
                            
                            String strCoinHistoricoAnexos = "";    
                            
                            String strAssunto = "";
                            
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
                            blCancelado = tbCiPorAprovar.getBoolp_CoinCancelado();
                            
                            strAssunto = tbCiPorAprovar.getStrp_Assunto();
                                    
                            strCoinHistoricoAnexos = tbCiPorAprovar.getStrp_CoinHistoricoAnexos();

                            strYear = df.format(dataCriacao);
                            
                            //Verificamos se é Gestor ou não
                            int nlIdUnidadeOrganizacional = Integer.parseInt(lblIdUO.getText());
                            int nlIdUOGestor = Integer.parseInt(lblUOGestora.getText());
                            int nlTipoPerfil = Integer.parseInt(lblIdPerfil.getText());
                            
                            if ((nlIdUnidadeOrganizacional == nlIdUOGestor) &&(nlTipoPerfil == 1)){
                                btnAprovarCI.setDisable(false);
                                btnEditarCI.setDisable(false);
                                btnDesaprovarCI.setDisable(false);
                            }else{
                                btnAprovarCI.setDisable(true);
                                btnEditarCI.setDisable(true);
                                btnDesaprovarCI.setDisable(true);
                            }
                            
                            //btnAprovarCI.setDisable(false); 
                            btnEncaminharCI.setDisable(true);
                            btnResponderCI.setDisable(true);
                            btnArquivarCI.setDisable(true);
                            btnDesarquivarCI.setDisable(true);
                            btnHistoricoCI.setDisable(true);
                            btnMarcarComoLido.setText("Marcar como Lido");
                            btnMarcarComoLido.setDisable(true);                            
                            btnMarcarcomoPendencia.setDisable(true);
                                                       
                            //btnEditarCI.setVisible(true);
                            
                            
                            

                            htmlEditorCI.setHtmlText(tbCiPorAprovar.getStrp_Conteudo()); 
                            if (strAssunto.equals("CI Não foi autorizada pela Unidade Gestora")){
                                lblNumeroSequencialCI.setText("CI-DESAPROVADA");
                                
                            }else {
                                lblNumeroSequencialCI.setText(strDescricaoUO + "-" + String.format("%05d",nCISequencial)+"-" + strYear);
                            }
                            
                            //lblNumeroSequencialCI.setText(strDescricaoUO + "-" + String.format("%05d",nCISequencial)+"-" + strYear);
                            if ((bTemAnexo) || (strCoinHistoricoAnexos.length()>0) ){
                                PreencherTxtFAnexos(nlIdCI,strCoinHistoricoAnexos);
                            }
                            else {
                                lviewAnexos.getItems().clear();
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
    
    
    @FXML
    private void handleBtnCaixaPendencias(ActionEvent event) throws IOException {
        lblCaixa.setText("");
        lblCaixa.setText("Caixa de recebidas (pendências)");
        
        lblRecordCount.setText("");
        
        setBotoesMainWindow(nTipoPerfil);
        
        btnDesarquivarCI.setDisable(true);
        btnHistoricoCI.setDisable(false);
        
        btnEditarCI.setDisable(true);
        btnDesaprovarCI.setDisable(true);
        btnMarcarcomoPendencia.setDisable(true);
        
        clearTelas();
        boolean blPreencherTableView2 = false;
        PreencherCaixaEntrada(3, blPreencherTableView2); 
        
    }
    @FXML
    private void handleBtnCaixaArquivadas(ActionEvent event) throws IOException {
        lblCaixa.setText("");
        lblCaixa.setText("Caixa de recebidas (arquivadas)");
        
        lblRecordCount.setText("");
        
        setBotoesMainWindow(nTipoPerfil);
        clearTelas();
        btnArquivarCI.setDisable(true);
        btnAprovarCI.setDisable(true);
        btnEncaminharCI.setDisable(true);
        btnResponderCI.setDisable(true);
        boolean blPreencherTableView2 = false;
        PreencherCaixaEntrada(4, blPreencherTableView2);
        
    }
    
    @FXML
    private void handleBtnMarcarcomoLido(ActionEvent event) throws IOException{
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
            boolean blCILido = false;
            String strLabel = "";
            strLabel = btnMarcarComoLido.getText();
            blCILido = ((strLabel == "Marcar como Lido") ? true : false);
            
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
                    MarcarComoLido(nIdCI, 1, blCILido);
                    //TableViewRefresh(nBotao);
                    break;
                case 2: // 2 ==> TB_CI_DESTINATARIO
                    nIdCI = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoinDestinatario();
                    MarcarComoLido(nIdCI, 2, blCILido);                        
                    //TableViewRefresh(nBotao);
                    break;

                default:
                    break;
            }
        } 
    }
    
    private void MarcarComoLido(int nlIdCI, int nlTabela, boolean blCILido){
        Alert alert;
        boolean bUpdate = false;   
        boolean bSolicitaEnvioMsgCiLida = false;
        int nlButtonSelected = 0;
        nlButtonSelected = ngBotao;
        
        MainWindowQueries consulta;
        
        consulta  = new MainWindowQueries();          
        //Tabela a ser utilizada para atualizar status das CIs (aprovadas, arquivadas, desarquivadas)
        // 0 ==> Sem tabela definida
        // 1 ==> TB_COMUNICACAO_INTERNA
        // 2 ==> TB_CI_DESTINATARIO
        switch (nlTabela){
            case 1:
                //Tabela TB_COMUNICACAO_INTERNA não possui campo para setear 
                // registro como marcar como lido ou não
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
            case 2: //Tabela TB_CI_DESTINATARIO
                try{
                    bUpdate = consulta.MarcarComoLido(nlIdCI, nlButtonSelected, blCILido);
                    
                    if (blCILido){
                        consulta  = new MainWindowQueries();                        
                        bSolicitaEnvioMsgCiLida = consulta.SolicitaConfirmacaoLeitura(nlIdCI);
                        
                        if (bSolicitaEnvioMsgCiLida){
                            alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Confirmar envio de menssagem");
                            alert.setHeaderText("O remitente solicitou confirmação de leitura da CI");
                            alert.setContentText("Deseja enviar menssagem confirmando que a CI foi Lida?"); 

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK){
                                    EnviarMsgCiLida(nlIdCI);
                            }
                            else{
                                //Do nothing                            
                            }
                        }
                    }
                }catch(Exception e){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("CI - Marcar como Lido/não Lido");
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
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText(null);
            alert.setContentText("CI foi marcada com sucesso.");
            alert.showAndWait();    
            
            //Valores dos botões 
            //1-caixa de recebidas (solicitando aprovação) - btnCaixaEntradaSolicitandoAprovacao
            //2-caixa de recebidas - btnCaixaEntrada
            //3-caixa de recebidas (pendencias) - btnCaixaPendencias
            //4-caixa de recebidas (arquivadas) - btnCaixaArquivadas
            //5-Caixa de enviados (arquivadas) - btnCaixaEnviadosArquivados
            //6-Caixa de enviados - btnCaixaSaida;
            //7-Caixa de enviados (solicitando aprovação) - btnPendentesAprovacao
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
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Não foi possível marcar como Lido/não Lido");
            alert.setContentText("Favor contatar o Administrador do sistema");
            alert.showAndWait();
        }
        
    }
    
    @FXML
    private void handleBtnCaixaEnviadasArquivadas(ActionEvent event) throws IOException {
        lblCaixa.setText("");
        lblCaixa.setText("Caixa de enviados (arquivadas)");
        
        lblRecordCount.setText("");
        
        setBotoesMainWindow(nTipoPerfil);
        clearTelas();
        btnArquivarCI.setDisable(true);
        btnAprovarCI.setDisable(true);
        btnEncaminharCI.setDisable(true);
        btnResponderCI.setDisable(true);
        
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
        
        boolean blPreencherTableView2 = false;
        
        PreencherCaixaSaida(nlTipoPreenchimento, blPreencherTableView2);
        
    }
    private void clearTelas(){
        int nlTblViewGeralSize = 0;
        //Devemos fazer refresh da tableView
        nlTblViewGeralSize = TbViewGeral.getItems().size();
        if (nlTblViewGeralSize > 0){
            TbViewGeral.getItems().clear();
        }
        htmlEditorCI.setHtmlText("");
        lviewAnexos.getItems().clear();
        txtFAnexos.getChildren().clear();
    }
    private void PreencherCaixaEntrada(int nlTipoPreenchimento, boolean blPreencherTableView2){
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
        
        //Variavel para construção da Ci Sequencial
        String strCiSequencial = "";
        String strAno = "";        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
        
        //Variaveis de apoio
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");        
        int nIdCoin = 0;
        int nIdTipoEnvio = 0;
        
        //Unidade Organizacional utilizado nas queries
        int nlIdUnidadeOrganizacional = 0;
        nlIdUnidadeOrganizacional = this.nIdUnidadeOrganizacional;
        
        //Tipo de perfil
        int nlTipoPerfil = 0;
        nlTipoPerfil = this.nTipoPerfil;
        
        //Assinatura MD5
        String strlAssinatura = "";
        
        //CI Lido ou não
        boolean bCoinLido = false;
        
        boolean bCancelado = false;
        
        //Iniciamos a criação da TableView
        List<TbCiDestinatario> listaCiDestinatario = new ArrayList<TbCiDestinatario>();
        ObservableList<TbCIPorAprovar> obslistaTbCaixaEntrada = FXCollections.observableArrayList();  
        
        List<TbCiDestinatario> listaCiDestinatario2 = new ArrayList<TbCiDestinatario>();
        ObservableList<TbCIPorAprovar> obslistaTbCaixaEntrada2 = FXCollections.observableArrayList();
        
             
        MainWindowQueries consulta;
        consulta  = new MainWindowQueries();        
        
        MainWindowQueries consulta2;
        consulta2  = new MainWindowQueries();        
        
        //Criamos a consulta a depender da variavel nlTipoPreenchimento
        //e também seteamos a visibilidade dos botões
        switch (nlTipoPreenchimento){
            case 1: //caixa de recebidas (solicitando aprovação)
                ngBotao = 1;
                ngTabela = 2; // TB_CI_DESTINATARIO
                btnAprovarCI.setDisable(false);
                if (1 == nlTipoPerfil){ //Perfil Gestor
                    //listaCiDestinatario2 = consulta2.getlistaCaixaEntradaSolicitandoAprovacao(this.nIdUnidadeOrganizacional);
                    listaCiDestinatario2 = consulta2.getlistaCaixaEntradaSolicitandoAprovacao(nlIdUnidadeOrganizacional);
                } else {
                    //listaCiDestinatario2 = consulta2.getlistaCaixaEntradaSolicitandoAprovacaoPerfil2(this.nIdUnidadeOrganizacional);                    
                    listaCiDestinatario2 = consulta2.getlistaCaixaEntradaSolicitandoAprovacaoPerfil2(nlIdUnidadeOrganizacional);
                }
                
                break;
            case 2: //caixa de recebidas
                ngBotao = 2;
                ngTabela = 2; // TB_CI_DESTINATARIO
                btnAprovarCI.setDisable(true);
                TbViewGeral2.setVisible(true);
                lblAguardandoAprovacao.setVisible(true);
                TbViewGeral.setPrefHeight(328);
                if (1 == nlTipoPerfil){ //Perfil Gestor
                    //TableView1
                    listaCiDestinatario = consulta.getlistaCaixaEntrada(nlIdUnidadeOrganizacional);
                    //TableView2
                    listaCiDestinatario2 = consulta2.getlistaCaixaEntradaSolicitandoAprovacao(nlIdUnidadeOrganizacional);
                } else {
                    //TableView1
                    listaCiDestinatario = consulta.getlistaCaixaEntradaPerfil2(nlIdUnidadeOrganizacional);
                    //TableView2
                    listaCiDestinatario2 = consulta2.getlistaCaixaEntradaSolicitandoAprovacaoPerfil2(nlIdUnidadeOrganizacional);
                }
                break;
            case 3: //caixa de recebidas (pendencias)
                ngBotao = 3;
                ngTabela = 2; // TB_CI_DESTINATARIO                
                btnMarcarcomoPendencia.setDisable(true);
                TbViewGeral2.setVisible(false);
                lblAguardandoAprovacao.setVisible(false);
                TbViewGeral.setPrefHeight(656);
                //TbViewGeral.setMaxHeight(656);
                
                if (1 == nlTipoPerfil){ //Perfil Gestor
                    listaCiDestinatario = consulta.getlistaCaixaEntradaPendencias(nlIdUnidadeOrganizacional);
                } else {
                    listaCiDestinatario = consulta.getlistaCaixaEntradaPendenciasPerfil2(nlIdUnidadeOrganizacional);
                }
                break;
            case 4: //caixa de recebidas (arquivadas)
                ngBotao = 4;
                ngTabela = 2; // TB_CI_DESTINATARIO
                TbViewGeral2.setVisible(false);
                lblAguardandoAprovacao.setVisible(false);
                btnAprovarCI.setDisable(true);
                TbViewGeral.setPrefHeight(656);
//                if ((this.nIdUnidadeOrganizacional == this.nIdUOGestor) &&(this.nTipoPerfil == 1)){
//                    listaCiDestinatario = consulta.getlistaGestorCaixaEntradaArquivados(this.nIdUnidadeOrganizacional);
//                } else {
                if (1 == nlTipoPerfil){ //Perfil Gestor
                    listaCiDestinatario = consulta.getlistaCaixaEntradaArquivados(nlIdUnidadeOrganizacional);
                } else {
                    listaCiDestinatario = consulta.getlistaCaixaEntradaArquivadosPerfil2(nlIdUnidadeOrganizacional);
                }
//                }
                break;
            default:
                break;
        }
        
        if ((2 == nlTipoPreenchimento) || (3 == nlTipoPreenchimento) || (4 == nlTipoPreenchimento) ) {
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

                bCoinLido = l.getCoinDestinatarioLido();
                
                bCancelado = l.getCoinCancelado();

                strDataCriacao = df.format(dataCriacao);
                
                //Construção da CI sequencial                
                strAno = sdf.format(dataCriacao);
                strCiSequencial = strUnorDescricaoGenesis + "-" + String.format("%05d",nlCoinNumeroGenesis)+ "-" + strAno;
                //----------------------------------------------------------
                
                obslistaTbCaixaEntrada.add(new TbCIPorAprovar(nlIdCoinDestinatario, nIdCoin, nlIdUsuarioRemitente, 
                        strUsuarioNomeCompleto, nlIdUORemitente, strDescricaoUORemitente,nlIdUODestinatario, 
                        strDescricaoUODestinatario, nlIdUOGestorDestinatario, strDescricaoUOGestorDestinatario, 
                        bAutorizadoPeloGestor, bArquivadoPeloUODestinatario, bArquivadoPeloUOGestor, strAssunto, 
                        strConteudo, bPendentePeloUODestinatario, dataAutorizadoPeloGestorDestinatario,
                        bLidoPeloUODestinatario, dataCriacao, nIdTipoEnvio, nlIdCoinNumero, bReadOnlyUODestinatario, 
                        bCoinTemAnexos, bAutorizadoPeloGestorRemitente, strDataCriacao, nlIdTabelaFonte,
                        nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
                        nlTipoCoin, strlAssinatura, bCoinLido, bCancelado,
                        strCiSequencial));

                System.out.println();
            }
            ClLido.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Boolean>("boolp_CoinLido"));
            ClIdCoin.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Integer>("intp_idCoin"));
            
            ClICiSequencial.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Integer>("strp_CiSequencial"));
            
            ClUODestinatario.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUODestinatario"));
            ClDataEnvio.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_dataCriacao"));        
            ClUORemitente.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUORemitente"));        
            ClAutorRemitente.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_UsuarioNomeCompleto"));        
            ClAssunto.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_Assunto"));
            TbViewGeral.setItems(obslistaTbCaixaEntrada);
            
//            TbViewGeral.setFixedCellSize(25);
//            TbViewGeral.prefHeightProperty().bind(TbViewGeral.fixedCellSizeProperty().multiply(Bindings.size(TbViewGeral.getItems()).add(1.01)));
//            TbViewGeral.minHeightProperty().bind(TbViewGeral.prefHeightProperty());
//            TbViewGeral.maxHeightProperty().bind(TbViewGeral.prefHeightProperty());

            // Código para mudar cor do registro

            ClLido.setCellFactory(new Callback<TableColumn<TbCIPorAprovar, Boolean>, TableCell<TbCIPorAprovar, Boolean>>() {
                @Override public TableCell<TbCIPorAprovar, Boolean> call(TableColumn<TbCIPorAprovar, Boolean> soCalledFriendBooleanTableColumn) {
                    return new TableCell<TbCIPorAprovar, Boolean>() {
                        @Override public void updateItem(final Boolean item, final boolean empty) {
                            super.updateItem(item, empty);

                            // clear any custom styles
                            //this.getStyleClass().remove("willPayCell");
                            //this.getStyleClass().remove("wontPayCell");
                            this.getTableRow().getStyleClass().remove("willPayRow");
                            this.getTableRow().getStyleClass().remove("wontPayRow");
                            this.getTableRow().getStyleClass().remove("porAprovarRow");
                            this.getTableRow().getStyleClass().remove("caixaEnviadosRow");

                            // update the item and set a custom style if necessary
                            if (item != null) {
                              setText(item.toString());
                              //this.getStyleClass().add(item ? "willPayCell" : "wontPayCell");
                              if(4==nlTipoPreenchimento){
                                this.getTableRow().getStyleClass().add(item ? "willPayRow" : "caixaEnviadosRow");
                              }else{
                                this.getTableRow().getStyleClass().add(item ? "willPayRow" : "wontPayRow");
                              }
                            }
                        }
                    };
                }
            });
            Platform.runLater(new Runnable(){
                @Override
                public void run(){
                    TbViewGeral.requestFocus();
                    TbViewGeral.getSelectionModel().select(0);
                    TbViewGeral.getFocusModel().focus(0);
                }
            });
//            TbViewGeral.requestFocus();                       // Get the focus
//            TbViewGeral.getSelectionModel().selectFirst();    // select first item in TableView model
//            TbViewGeral.getFocusModel().focus(0);             // set the focus on the first element

            //Codigo para  
    //        final StyleChangingRowFactory<TbCIPorAprovar> rowFactory = new StyleChangingRowFactory<>("highlighted");
    //        TbViewGeral.setRowFactory(rowFactory);
    //        
    //        btnMarcarComoLido.disableProperty().bind(Bindings.isEmpty(TbViewGeral.getSelectionModel().getSelectedIndices()));
    //        btnMarcarComoLido.setOnAction(event -> rowFactory.getStyledRowIndices().setAll(TbViewGeral.getSelectionModel().getSelectedIndex()));

            //------------------------------------------------

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
                            boolean bLido = false;
                            boolean blCancelado = false;
                            String strlBtnMarcarComoLido = "";

                            String strCoinHistoricoAnexos = "";
                            String strAssunto = "";

                            String strDescricaoUO = "";
                            String strYear = "";
                            Date dataCriacao;
                            SimpleDateFormat df = new SimpleDateFormat("yyyy");        

                            nlIdCI = tbCiPorAprovar.getIntp_idCoin();

                            strDescricaoUO = tbCiPorAprovar.getStrp_UnorDescricaoGenesis();
                            nCISequencial = tbCiPorAprovar.getIntp_CoinNumeroGenesis();                    
                            dataCriacao = tbCiPorAprovar.getDataCriacao();
                            bTemAnexo = tbCiPorAprovar.getBoolp_CoinTemAnexos();
                            bLido = tbCiPorAprovar.getBoolp_CoinLido();
                            blCancelado = tbCiPorAprovar.getBoolp_CoinCancelado();
                            strAssunto = tbCiPorAprovar.getStrp_Assunto();

                            strCoinHistoricoAnexos = tbCiPorAprovar.getStrp_CoinHistoricoAnexos();

                            strYear = df.format(dataCriacao);
                            
                            //TableView1 desabilita botão Aprovar CI e habilita MArcar como Lido
                            switch (nlTipoPreenchimento)  {
                                case 2: //caixa de recebidas e pendentes de aprovação
                                    btnAprovarCI.setDisable(true);
                                    btnEncaminharCI.setDisable(false);
                                    btnResponderCI.setDisable(false);
                                    btnArquivarCI.setDisable(false);
                                    btnDesarquivarCI.setDisable(true);
                                    btnHistoricoCI.setDisable(false);
                                    btnMarcarComoLido.setDisable(false);
                                    btnMarcarcomoPendencia.setDisable(false);
                                    btnEditarCI.setDisable(true);  
                                    btnDesaprovarCI.setDisable(true);
                                    
                                    //Icons para botão Marcar como Lido ou não
                                    // Adding images
                                    Image imgRead = new Image(getClass().getResourceAsStream("/resources/mail_read_24.png"));
                                    Image imgUnread = new Image(getClass().getResourceAsStream("/resources/mail_unread_24.png"));
                                    Image imgView;

                                    strlBtnMarcarComoLido = (bLido ? "Marcar como não Lido" : "Marcar como Lido");
                                    btnMarcarComoLido.setText(strlBtnMarcarComoLido);
                                    imgView = (bLido ? imgUnread : imgRead) ;
                                    btnMarcarComoLido.setGraphic(new ImageView(imgView));
                                    break;
                                case 3://caixa de recebidas (pendencias)
                                    btnAprovarCI.setDisable(true);
                                    btnEncaminharCI.setDisable(false);
                                    btnResponderCI.setDisable(false);
                                    btnArquivarCI.setDisable(false);
                                    btnDesarquivarCI.setDisable(true);
                                    btnHistoricoCI.setDisable(false);
                                    btnMarcarComoLido.setDisable(false);
                                    btnMarcarcomoPendencia.setDisable(true); 
                                    btnEditarCI.setDisable(true);
                                    btnDesaprovarCI.setDisable(true);
                                    break;
                                case 4: //caixa de recebidas (arquivadas)
                                    btnAprovarCI.setDisable(true);
                                    btnEncaminharCI.setDisable(true);
                                    btnResponderCI.setDisable(true);
                                    btnArquivarCI.setDisable(true);
                                    btnDesarquivarCI.setDisable(false);
                                    btnHistoricoCI.setDisable(false);
                                    btnMarcarComoLido.setDisable(true);
                                    btnMarcarcomoPendencia.setDisable(true);
                                    btnEditarCI.setDisable(true);
                                    btnDesaprovarCI.setDisable(true);
                                    break;
                                    
                            }
                            
                                
                            
                            
                            //----------------------------------------------------------------------------------
                            htmlEditorCI.setHtmlText(tbCiPorAprovar.getStrp_Conteudo());  
                           if (strAssunto.equals("CI Não foi autorizada pela Unidade Gestora")){
                                lblNumeroSequencialCI.setText("CI-DESAPROVADA");
                                
                            }else {
                                lblNumeroSequencialCI.setText(strDescricaoUO + "-" + String.format("%05d",nCISequencial)+"-" + strYear);
                            }
                            if ((bTemAnexo) || (strCoinHistoricoAnexos.length()>0) ){
                                PreencherTxtFAnexos(nlIdCI,strCoinHistoricoAnexos);
                            }                    
                            else {
                                lviewAnexos.getItems().clear();
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
        
        // Código para caixa de Aguardando aprovação
        if (blPreencherTableView2) {
            //if ((1 == nlTipoPreenchimento)){
                for(TbCiDestinatario l : listaCiDestinatario2){
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

                    bCoinLido = l.getCoinDestinatarioLido();
                    
                    bCancelado = l.getCoinCancelado();

                    strDataCriacao = df.format(dataCriacao);
                    
                    //Construção da CI sequencial                    
                    strAno = sdf.format(dataCriacao);
                    strCiSequencial = strUnorDescricaoGenesis + "-" + String.format("%05d",nlCoinNumeroGenesis)+ "-" + strAno;
                    //----------------------------------------------------------
                    
                    obslistaTbCaixaEntrada2.add(new TbCIPorAprovar(nlIdCoinDestinatario, nIdCoin, nlIdUsuarioRemitente, 
                            strUsuarioNomeCompleto, nlIdUORemitente, strDescricaoUORemitente,nlIdUODestinatario, 
                            strDescricaoUODestinatario, nlIdUOGestorDestinatario, strDescricaoUOGestorDestinatario, 
                            bAutorizadoPeloGestor, bArquivadoPeloUODestinatario, bArquivadoPeloUOGestor, strAssunto, 
                            strConteudo, bPendentePeloUODestinatario, dataAutorizadoPeloGestorDestinatario,
                            bLidoPeloUODestinatario, dataCriacao, nIdTipoEnvio, nlIdCoinNumero, bReadOnlyUODestinatario, 
                            bCoinTemAnexos, bAutorizadoPeloGestorRemitente, strDataCriacao, nlIdTabelaFonte,
                            nlIdCoinGenesis, nlIdUnorGenesis, nlCoinNumeroGenesis, strCoinHistoricoAnexos, strUnorDescricaoGenesis,
                            nlTipoCoin, strlAssinatura, bCoinLido, bCancelado,
                            strCiSequencial));

                    System.out.println();
                }
                ClAprovado.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Boolean>("boolp_AutorizadoPeloGestor"));
                ClIdCoin2.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Integer>("intp_idCoin"));
                
                ClICiSequencial2.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,Integer>("strp_CiSequencial"));
                
                ClUODestinatario2.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUODestinatario"));
                ClDataEnvio2.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_dataCriacao"));        
                ClUORemitente2.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_DescricaoUORemitente"));        
                ClAutorRemitente2.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_UsuarioNomeCompleto"));        
                ClAssunto2.setCellValueFactory(new PropertyValueFactory<TbCIPorAprovar,String>("strp_Assunto"));
                TbViewGeral2.setItems(obslistaTbCaixaEntrada2);

                // Código para mudar cor do registro

                ClAprovado.setCellFactory(new Callback<TableColumn<TbCIPorAprovar, Boolean>, TableCell<TbCIPorAprovar, Boolean>>() {
                    @Override public TableCell<TbCIPorAprovar, Boolean> call(TableColumn<TbCIPorAprovar, Boolean> soCalledFriendBooleanTableColumn) {
                        return new TableCell<TbCIPorAprovar, Boolean>() {
                            @Override public void updateItem(final Boolean item, final boolean empty) {
                                super.updateItem(item, empty);

                                // clear any custom styles
                                //this.getStyleClass().remove("willPayCell");
                                //this.getStyleClass().remove("wontPayCell");
                                this.getTableRow().getStyleClass().remove("willPayRow");
                                this.getTableRow().getStyleClass().remove("wontPayRow");
                                this.getTableRow().getStyleClass().remove("porAprovarRow");

                                // update the item and set a custom style if necessary
                                if (item != null) {
                                  setText(item.toString());
                                  //this.getStyleClass().add(item ? "willPayCell" : "wontPayCell");
                                  this.getTableRow().getStyleClass().add(item ? "wontPayRow" : "porAprovarRow");
                                }
                            }
                        };
                    }
                });

                //Codigo para  
        //        final StyleChangingRowFactory<TbCIPorAprovar> rowFactory = new StyleChangingRowFactory<>("highlighted");
        //        TbViewGeral.setRowFactory(rowFactory);
        //        
        //        btnMarcarComoLido.disableProperty().bind(Bindings.isEmpty(TbViewGeral.getSelectionModel().getSelectedIndices()));
        //        btnMarcarComoLido.setOnAction(event -> rowFactory.getStyledRowIndices().setAll(TbViewGeral.getSelectionModel().getSelectedIndex()));

                //------------------------------------------------

                TbViewGeral2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                    //Check whether item is selected and set value of selected item to Label
                        try{
                            //TableView<TbCIPorAprovar> TbViewGeral = new TableView<>();
                            if(TbViewGeral2.getSelectionModel().getSelectedItem() != null){
                                //TbCIPorAprovar tbCiPorAprovar = TbViewGeral.getSelectionModel().getSelectedItem();
                                //TbCIPorAprovar tbtbCiPorAprovar = new TbCIPorAprovar();
                                
                                TbCIPorAprovar tbCiPorAprovar = TbViewGeral2.getSelectionModel().getSelectedItem();
                                int nCISequencial = 0;
                                int nlIdCI = 0;
                                boolean bTemAnexo = false;
                                boolean bLido = false;
                                boolean blCancelado = false;
                                String strlBtnMarcarComoLido = "";

                                String strCoinHistoricoAnexos = "";
                                String strAssunto = "";

                                String strDescricaoUO = "";
                                String strYear = "";
                                Date dataCriacao;
                                SimpleDateFormat df = new SimpleDateFormat("yyyy");        

                                nlIdCI = tbCiPorAprovar.getIntp_idCoin();

                                strDescricaoUO = tbCiPorAprovar.getStrp_UnorDescricaoGenesis();
                                nCISequencial = tbCiPorAprovar.getIntp_CoinNumeroGenesis();                    
                                dataCriacao = tbCiPorAprovar.getDataCriacao();
                                bTemAnexo = tbCiPorAprovar.getBoolp_CoinTemAnexos();
                                bLido = tbCiPorAprovar.getBoolp_CoinLido();
                                blCancelado = tbCiPorAprovar.getBoolp_CoinCancelado();
                                strAssunto = tbCiPorAprovar.getStrp_Assunto();

                                strCoinHistoricoAnexos = tbCiPorAprovar.getStrp_CoinHistoricoAnexos();

                                strYear = df.format(dataCriacao);
                                
                                //Verificamos se é Gestor ou não
                                int nlIdUnidadeOrganizacional = Integer.parseInt(lblIdUO.getText());
                                int nlIdUOGestor = Integer.parseInt(lblUOGestora.getText());
                                int nlTipoPerfil = Integer.parseInt(lblIdPerfil.getText());

                                if ((nlIdUnidadeOrganizacional == nlIdUOGestor) &&(nlTipoPerfil == 1)){
                                    btnAprovarCI.setDisable(false);
                                    btnEditarCI.setDisable(false);
                                    btnDesaprovarCI.setDisable(false);
                                }else{
                                    btnAprovarCI.setDisable(true);
                                    btnEditarCI.setDisable(true);
                                    btnDesaprovarCI.setDisable(true);
                                }
                                
                                //-----TableView2  
                                //btnAprovarCI.setDisable(false); 
                                btnEncaminharCI.setDisable(true);
                                btnResponderCI.setDisable(true);
                                btnArquivarCI.setDisable(true);
                                btnDesarquivarCI.setDisable(true);
                                btnHistoricoCI.setDisable(true);
                                btnMarcarComoLido.setText("Marcar como Lido");
                                btnMarcarComoLido.setDisable(true);                            
                                btnMarcarcomoPendencia.setDisable(true);

                                //btnEditarCI.setVisible(true);
                                
//                                //TableView2 Habilita botão Aprovar CI e desabilita Marcar como Lido
//                                btnMarcarComoLido.setText("Marcar como Lido");
//                                btnMarcarComoLido.setDisable(true);
//                                btnEncaminharCI.setDisable(true);
//                                btnMarcarcomoPendencia.setDisable(true);
//                                btnAprovarCI.setDisable(false);
                                
//                                strlBtnMarcarComoLido = (bLido ? "Marcar como não Lido" : "Marcar como Lido");
//                                btnMarcarComoLido.setText(strlBtnMarcarComoLido);
                                htmlEditorCI.setHtmlText(tbCiPorAprovar.getStrp_Conteudo());  
                                
                                if (strAssunto.equals("CI Não foi autorizada pela Unidade Gestora")){
                                    lblNumeroSequencialCI.setText("CI-DESAPROVADA");
                                
                                }else {
                                    lblNumeroSequencialCI.setText(strDescricaoUO + "-" + String.format("%05d",nCISequencial)+"-" + strYear);
                                }
                                
                                //lblNumeroSequencialCI.setText(strDescricaoUO + "-" + String.format("%05d",nCISequencial)+"-" + strYear);
                                if ((bTemAnexo) || (strCoinHistoricoAnexos.length()>0) ){
                                    PreencherTxtFAnexos(nlIdCI,strCoinHistoricoAnexos);
                                }                    
                                else {
                                    lviewAnexos.getItems().clear();
                                    txtFAnexos.getChildren().clear();
                                }

                            }
                        }catch (Exception e) {
                                e.printStackTrace();
                                //labelMessage.setText("Error on get row Data");
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Erro");
                                alert.setHeaderText("Erro na carga da TableView2");
                                alert.setContentText(e.getMessage());
                                alert.showAndWait();
                        }
                    }
                });        
            //}
        }
        
    }
    @FXML
    private void handleBtnHistoricoCI(ActionEvent event) throws IOException{
        int nBotao = 0;
        nBotao = this.ngBotao;
        
        //Variaveis para construir o Histórico da CI
        String strSequencialCI = this.lblNumeroSequencialCI.getText();            
        int nTabela = 0;
        int nIdCoinDestinatario = 0;
        int nIdCoin = 0;
        String strAssinaturaTbCiDestinatario = "";
        String strAssinaturaTbCi = "";
        
        nTabela = this.ngTabela;
        
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
                if (null == TbViewGeral.getSelectionModel().getSelectedItem()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText("CI não foi selecionado.");
                    alert.setContentText("Favor selecionar uma CI da tabela");
                    alert.showAndWait();
                }else{
                    nIdCoinDestinatario = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoinDestinatario();
                    nIdCoin = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoin();
                    strAssinaturaTbCiDestinatario = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_CoinAssinatura();
                    System.out.println();
                    //Mostramos o historico
                    ShowHistoricoCIe(this, nIdCoinDestinatario, strSequencialCI, nBotao, nTabela, strAssinaturaTbCiDestinatario);
            //---------------------------------------------
                    
                }
                break;
            case 5: case 6: case 7: //TB_COMUNICACAO_INTERNA
                if (null == TbViewGeral.getSelectionModel().getSelectedItem()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText("CI não foi selecionado.");
                    alert.setContentText("Favor selecionar uma CI da tabela");
                    alert.showAndWait();
                }else{
                    //nIdCoinDestinatario = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoinDestinatario();
                    nIdCoin = TbViewGeral.getSelectionModel().getSelectedItem().getIntp_idCoin();
                    strAssinaturaTbCi = TbViewGeral.getSelectionModel().getSelectedItem().getStrp_CoinAssinatura();
                    System.out.println();
                    //Mostramos o historico
                    ShowHistoricoCIe(this, nIdCoin, strSequencialCI, nBotao, nTabela, strAssinaturaTbCi);
            //---------------------------------------------
                    
                }
                break;
            default:
                break;
        }
        
        
    }
    public void ShowHistoricoCIe(final FXMLMainController mainController, int nIdCiEletronica, String strSequencialCI,
            int nBotao, int nTabela, String strAssinaturaCI) {
        try {
            scene = new Scene(new SplitPane());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ci_eletronico/fxml_utilitarios/HistoricoCI.fxml"));
            scene.setRoot((Parent) loader.load());
                
            ci_eletronico.fxml_utilitarios.HistoricoCIController HistoricoCiController = loader.<ci_eletronico.fxml_utilitarios.HistoricoCIController>getController();     
            
            HistoricoCiController.setVariaveisAmbienteHistoricoCI(mainController, nIdCiEletronica, strSequencialCI, nBotao, nTabela, strAssinaturaCI);
            
            Stage stage = new Stage();
            stage.setTitle("Histórico da CI-eletrônica");
            //set icon
            stage.getIcons().add(new Image("/resources/Business-Process-icon_16.png"));

            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);     //Window Parent fica inativo
            stage.showAndWait();
            
        }catch (IOException ex) {
                Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}


class Choice {        
      
    Integer id; String displayString; String displayString2;
    Choice(Integer id)                       { this(id, null,null); }
    Choice(String  displayString)            { this(null, displayString); }        
    Choice(Integer id, String displayString) { this.id = id; this.displayString = displayString;}
    Choice(Integer id, String displayString, String displayString2) { this.id = id; this.displayString = displayString; this.displayString2 = displayString2;}

    @Override public String toString() { return displayString; } 
    
    @Override public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Choice choice = (Choice) o;
      return displayString != null && displayString.equals(choice.displayString) || id != null && id.equals(choice.id);      
    }

    @Override public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (displayString != null ? displayString.hashCode() : 0);
        return result;
        }
}