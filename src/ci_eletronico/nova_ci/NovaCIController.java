/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.nova_ci;

import ci_eletronico.FXMLMainController;
import ci_eletronico.entities.TbAnexo;
import ci_eletronico.entities.TbCiDestinatario;
import ci_eletronico.entities.TbComunicacaoInterna;
import ci_eletronico.entities.TbTipoComunicacoInterna;
import ci_eletronico.entities.TbTipoEnvio;
import ci_eletronico.entities.TbUnidadeOrganizacional;
import ci_eletronico.entities.TbUnidadeOrganizacionalGestor;
import ci_eletronico.fxml_utilitarios.Win_Para_ComcopiaController;
import ci_eletronico.utilitarios.Seguranca;
import ci_eletronico_queries.MainWindowQueries;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author victorcmaf
 */
public class NovaCIController implements Initializable {
    
    private Integer nTipoPerfil = 0;
    private Integer nIdUsuario = 0;
    private Integer nIdUO = 0;
    private String strNomeUsuario = "";
    private String strDescricaoPerfil = "";
    private String strNomeUO = "";
    private String strHtmlAssinatura = "";
    private String strHtmlEncaminharConteudo = "";
    private int nTipoCI = 0;
    private int nIdUOGestor = 0;
    private String strgUserLogin = "";
    
    EntityManager em;
    EntityManagerFactory emf;
    
    //Variaveis utilizadas nas CIs encaminhadas para não perder número de CI
    //criada e quem foi o Remitente inicial (serve para saber quais anexos acompanham o despacho também)
    private    int nlIdCoinGenesis = 0;
    private    int nlIdUnorGenesis = 0;
    private    int nlCoinNumeroGenesis = 0;
    private    String strCoinHistoricoAnexos = "";
    private    String strUnorDescricaoGenesis = "";
    
    @FXML
    Button btnPara;
    @FXML
    Button btnAnexarArquivos;
    @FXML
    Button btnEnviarNovaCI;
    @FXML
    Button btnComCopia;
    
    @FXML
    TextField txtAssunto;
    
    @FXML
    HTMLEditor htmlEditor;
    @FXML
    TextFlow txtFAnexado;
    @FXML
    TextFlow txtFPara;
    @FXML
    TextFlow txtFComCopia;
    @FXML
    ComboBox cmbApensamento;
    @FXML
    TextField txtApensamento;
    
    
    // Clases para tratar Anexar Arquivos
    private Desktop desktop = Desktop.getDesktop();
    private Stage stage = new Stage();
    //-----------------------------------
    
    private Hyperlink linkArquivoSelecionado = new Hyperlink() ;
    
    private Scene scene;
    
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        //htmlEditor.setHtmlText(strAssinatura);
//        //htmlEditor.setDisable(true);
        this.cmbApensamento.setVisible(false);
        
    }   
    
    public void setVariaveisAmbienteNovaCI(final FXMLMainController mainController , String strIdUsuario, String strNomeUsuario, 
                                        String strIdUO, String strNomeUO, String strIdPerfil, String strDescricaoPerfil, String strHtmlAssinatura, 
                                        int nTipoCI, int nIdUOGestor, String strHtmlConteudo,
                                        int nlIdCoinGenesis, int nlIdUnorGenesis, int nlCoinNumeroGenesis, String strCoinHistoricoAnexos, String strUnorDescricaoGenesis,
                                        String strlUserLogin) {
        
        this.strNomeUsuario = strNomeUsuario;
        this.strNomeUO = strNomeUO;          
        this.strDescricaoPerfil = strDescricaoPerfil;
        this.strHtmlAssinatura = "<br /><br /><br />" + strHtmlAssinatura;
        this.strHtmlEncaminharConteudo = strHtmlConteudo;
        
        //Variaveis utilizadas nas CIs encaminhadas para não perder número de CI
        //criada e quem foi o Remitente inicial (serve para saber quais anexos acompanham o despacho também)
        this.nlIdCoinGenesis = nlIdCoinGenesis;
        this.nlIdUnorGenesis = nlIdUnorGenesis;
        this.nlCoinNumeroGenesis = nlCoinNumeroGenesis;
        this.strCoinHistoricoAnexos = strCoinHistoricoAnexos;
        this.strUnorDescricaoGenesis = strUnorDescricaoGenesis;
        //-------------------------------------------------------------------
        
        nIdUsuario = Integer.parseInt(strIdUsuario);        
        nTipoPerfil = Integer.parseInt(strIdPerfil);
        nIdUO = Integer.parseInt(strIdUO);
        strgUserLogin = strlUserLogin;
        
        //Preencher o editor Html com assinatura do usuário
        htmlEditor.setHtmlText(this.strHtmlAssinatura);
        this.nTipoCI = nTipoCI;
        this.nIdUOGestor = nIdUOGestor;
        
        switch (nTipoCI){
            case 1:
                btnEnviarNovaCI.setText("Enviar CI");
                break;
            case 2:
                btnEnviarNovaCI.setText("Enviar CI circular");
                break;
            case 3:
                btnEnviarNovaCI.setText("Enviar CI confidencial");
                break;
            case 4:
                btnEnviarNovaCI.setText("Encaminhar CI");
                break;
            default:
                btnEnviarNovaCI.setText("Enviar CI");
                break;
        }
        
        
    }
    @FXML
    private void handleBtnAnexarArquivo(ActionEvent event) throws IOException {
        List<String> listaArquivos = new ArrayList<String>();
        listaArquivos.clear();
        
        Text txtArquivoSelecionado = new Text();
                       
        //text1.setText("Texto 01");
        txtArquivoSelecionado.setFill(Color.BLUE);
        txtArquivoSelecionado.setFont(Font.font("Arial", FontPosture.ITALIC, 12));
        
        //Hyperlink linkArquivoSelecionado = new Hyperlink();
        //linkArquivoSelecionado = new Hyperlink();
        
        stage.setTitle("File Chooser Sample");
        
        final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        int nContador = 0;
        List<File> list = fileChooser.showOpenMultipleDialog(stage);
                    if (list != null) {
                        for (File file : list) {
                            System.out.println();
                            System.out.println("getPath(): " + file.getPath() + " nome arquivo = " + file.getName());
                            System.out.println("getCanonicalPath(): " + file.getCanonicalPath() + " nome arquivo = " + file.getName());
                            System.out.println("getAbsolutePath(): " + file.getAbsolutePath()+ " nome arquivo = " + file.getName());
//                            txtAnexado.appendText("\"");
//                            txtAnexado.appendText(file.getName());
//                            txtAnexado.appendText("\";");
                            
                            listaArquivos.add("\""+file.getName()+"\";");
                            nContador++;
                            
                            txtArquivoSelecionado = new Text();                          
                            //txtArquivoSelecionado.setText("\""+ file.getName() + "\"; ");
                            //txtArquivoSelecionado.setText(file.getName() + " ; ");
                            txtArquivoSelecionado.setText(file.getPath()+ " ; ");
                            txtArquivoSelecionado.setFill(Color.BLACK);
                            txtArquivoSelecionado.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
                           
                            linkArquivoSelecionado = new Hyperlink();
                            linkArquivoSelecionado.setText("\""+ file.getName() + "\"; ");
                            
                            //txtArquivoSelecionado.getStyleClass().add("link");
                            //txtFAnexado.getChildren().add(txtArquivoSelecionado);
                            //txtFAnexado.getChildren().add(new Text(txtArquivoSelecionado));
                            
                            txtFAnexado.getChildren().add(txtArquivoSelecionado);
                            
                            //txtFAnexado.getChildren().add(linkArquivoSelecionado);
                            
//                            linkArquivoSelecionado.setOnAction(ev->{
//                                System.out.println("Click on  link: " + linkArquivoSelecionado.getText());            
//                            });
                            
                            //flowPaneArquivoAnexado.getChildren().add(txtArquivoSelecionado);
                            
                           
                            //openFile(file);
                        }
                    }
                    System.out.println("listaArquivos size = " +  listaArquivos.size());
                    for (int n = 0 ; n < (listaArquivos.size());n++){
                        linkArquivoSelecionado = new Hyperlink();                        
                        linkArquivoSelecionado.setText(listaArquivos.get(n));
                        
                        
                        //txtFAnexado.getChildren().add(linkArquivoSelecionado);  
                        
//                        linkArquivoSelecionado.setOnAction(ev->{
//                                System.out.println("Click on  link: " + linkArquivoSelecionado.getText());            
//                            });
        
                    }
                    txtFAnexado.setOnMouseClicked(ev -> {
                        if (1 == ev.getClickCount()){
                            if(ev.getTarget() instanceof Text) {
                                Text clicked = (Text) ev.getTarget();
                                clicked.setFill(Color.RED);
                                clicked.setFont(Font.font("Arial", FontPosture.ITALIC, 12));
                                clicked.setUnderline(true);
                                System.out.println(clicked.getText());
                                //txtFAnexado.getChildren().remove(ev.getTarget());
                            }                            
                        }
                        if (2 == ev.getClickCount()){
                            if(ev.getTarget() instanceof Text) {
                                Text clicked = (Text) ev.getTarget();
                                System.out.println(clicked.getText());
                                txtFAnexado.getChildren().remove(ev.getTarget());
                            }
                        }
                    });
                 
    }
    public void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                NovaCIController.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }
    private static void configureFileChooser(final FileChooser fileChooser){                           
        fileChooser.setTitle("Selecionar arquivo");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "//Downloads")); 
    }
    @FXML
    private void handleBtnPara(ActionEvent event) throws IOException {
        int nTipoDestinatario = 0;
        nTipoDestinatario = 1; // Destinatário Para:
        
        showListViewDestinatarios(nTipoDestinatario);
//        scene = new Scene(new StackPane());
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ci_eletronico/fxml_utilitarios/Win_Para_Comcopia.fxml"));
//        scene.setRoot((Parent) loader.load());
//
//        Win_Para_ComcopiaController controller = loader.getController();
//        controller.setTipoDestinatario(nTipoDestinatario);
//
//        Stage stage = new Stage();
//        stage.setTitle("Selecionar UOs");
//        //set icon
//        stage.getIcons().add(new Image("/resources/Nova_CI.png"));
//        stage.initModality(Modality.WINDOW_MODAL);
//
//        stage.setScene(scene);
//        stage.showAndWait();
//        
//        ObservableList<String> UOSelected = controller.getUOsPara();
//        int nSize = 0;
//        nSize = UOSelected.size();
//        if (nSize > 0){
//          setTextFlowPara(UOSelected, nSize);
//        } 
//                                
       
    }
    @FXML
    private void handleBtnComCopia(ActionEvent event) throws IOException {
        int nTipoDestinatario = 0;
        nTipoDestinatario = 2; // Destinatário Com cópia:
        
        showListViewDestinatarios(nTipoDestinatario);
    }
    
    @FXML
    private void handleBtnEnviarNovaCI(ActionEvent event) throws IOException {
        
        String strAssunto = "";
        String strConteudoHtmlText = "";
        
        strAssunto = txtAssunto.getText();
        strConteudoHtmlText = htmlEditor.getHtmlText();
        if (txtAssunto.getText().isEmpty()){
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo Assunto sem dados");
            alert.setHeaderText("Favor preencher o campo Assunto");
            alert.setContentText("O campo Assunto deve possuir dados");
            alert.showAndWait();
            
        } else if (0 == txtFPara.getChildren().size()) {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campo Para sem dados");
            alert.setHeaderText("Favor preencher o campo Para");
            alert.setContentText("O campo Para deve possuir ao menos uma UO selecionada");
            alert.showAndWait();
        } else{
            //System.out.println("ID UO Gestora = " + nIdUOGestor);
            //ntipoCI   1 - CI normal
            //          2 - CI circular
            //          3 - CI confidencial
            //          4 - CI encaminhado
            
            salvarCI(nTipoCI);
            //Ocultamos a janela de seleção UOs
            (((Node)event.getSource()).getScene()).getWindow().hide();
            //--------- FIM Ocultar janela de seleção UOs ------------
        }
        
//        //Verificamos se existem anexos a serem salvos
//        Integer nSize;
//        nSize = txtFAnexado.getChildren().size();
//        
//        if (nSize > 0) {
//            //Existem arquivos a serem salvos
//            ObservableList<Node> nodes = txtFAnexado.getChildren();
//            StringBuilder sb = new StringBuilder();
//            for (Node node : nodes) { sb.append((((Text)node).getText()));
//            }
//            String txt = sb.toString();
//            System.out.println("TxtFAnexado  = " + txt );
//        }
    }
    private void showListViewDestinatarios(int nTipoDestinatario) throws IOException{
        scene = new Scene(new StackPane());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ci_eletronico/fxml_utilitarios/Win_Para_Comcopia.fxml"));
        scene.setRoot((Parent) loader.load());

        Win_Para_ComcopiaController controller = loader.getController();
        //controller.setTipoDestinatario(nTipoDestinatario);

        Stage stage = new Stage();
        stage.setTitle("Selecionar UOs");
        //set icon
        stage.getIcons().add(new Image("/resources/Nova_CI.png"));       

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);     //Window Parent fica inativo
        stage.showAndWait();
        
        ObservableList<String> UOSelected = controller.getUOsPara();
        ObservableList<String> UOFiltrado;
        int nSize = 0;
        try {
            nSize = UOSelected.size();  //se for null então dispara o catch
            if (nSize > 0){
                //Filtramos as UOs a serem mostradas no campo TextFlow
                UOFiltrado = setTextFlowUOFiltrado(UOSelected,nSize);

                //Após filtragem mostramos as UOs no campo TextFlow
                switch (nTipoDestinatario){
                    case 1: // 1 - Destinatário Para:
                            //setTextFlowPara(UOSelected, nSize);
                            setTextFlowPara(UOFiltrado, nSize);
                            break;

                    case 2: // 2 - Destinatário Com cópia:
                            //setTextFlowComCopia(UOSelected, nSize);
                            setTextFlowComCopia(UOFiltrado, nSize);
                            break;

                    default:

                }            
            }
        } catch(Exception e){
            e.printStackTrace();            
        }
    }
    private ObservableList<String> setTextFlowUOFiltrado(ObservableList<String> UOSelected, int nSize){
        ObservableList<String> UOsFiltrados = FXCollections.observableArrayList();;
        int nContador;
        String strId;
        String strNome;
        String delimiters = "-|\\;";
        
        for (nContador = 0; nContador < nSize; nContador++){
            String[] parts = UOSelected.get(nContador).split(delimiters);
            strId = parts[0].trim(); // 004
            strNome = parts[1].trim(); // 034556
            UOsFiltrados.add(strId + "-" + strNome);            
        }
//        String[] parts = strcmbUO.split(delimiters);
//        strIdUO = parts[0].trim(); // 004
//        strNomeUO = parts[1].trim(); // 034556
//        strIdUsuarioPerfil = parts[2].trim();
//        strDescricaoPerfil = parts[3].trim();
//        for (String token: parts){
//            System.out.println(token);
//        }
        return UOsFiltrados;        
    }
    
    private void setTextFlowPara(ObservableList<String> UOSelected, int nSize){
        
        //------------------------------------------------------
        Text txtArquivoSelecionado;
        int nContador;
        for (nContador = 0;nContador<nSize;nContador++){
            txtArquivoSelecionado = new Text();                          
            //txtArquivoSelecionado.setText("\""+ UOSelected.get(nContador)+ "\"; ");
            txtArquivoSelecionado.setText(UOSelected.get(nContador)+ " ; ");
            txtArquivoSelecionado.setFill(Color.BLACK);
            txtArquivoSelecionado.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
            txtFPara.getChildren().add(txtArquivoSelecionado);            
        }
        txtFPara.setOnMouseClicked(ev -> {
            if (1 == ev.getClickCount()){
                if(ev.getTarget() instanceof Text) {
                    Text clicked = (Text) ev.getTarget();
                    clicked.setFill(Color.RED);
                    clicked.setFont(Font.font("Arial", FontPosture.ITALIC, 12));
                    clicked.setUnderline(true);
                    System.out.println(clicked.getText());
                    //txtFAnexado.getChildren().remove(ev.getTarget());
                }                            
            }
            if (2 == ev.getClickCount()){
                if(ev.getTarget() instanceof Text) {
                    Text clicked = (Text) ev.getTarget();
                    System.out.println(clicked.getText());
                    txtFPara.getChildren().remove(ev.getTarget());
                }
            }
        });
        
        
    }
    private void setTextFlowComCopia(ObservableList<String> UOSelected, int nSize){
        Text txtArquivoSelecionado;
        int nContador;
        for (nContador = 0;nContador<nSize;nContador++){
            txtArquivoSelecionado = new Text();                          
            //txtArquivoSelecionado.setText("\""+ UOSelected.get(nContador)+ "\"; ");
            txtArquivoSelecionado.setText(UOSelected.get(nContador)+ " ; ");
            txtArquivoSelecionado.setFill(Color.BLACK);
            txtArquivoSelecionado.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
            txtFComCopia.getChildren().add(txtArquivoSelecionado);            
        }
        txtFComCopia.setOnMouseClicked(ev -> {
            if (1 == ev.getClickCount()){
                if(ev.getTarget() instanceof Text) {
                    Text clicked = (Text) ev.getTarget();
                    clicked.setFill(Color.RED);
                    clicked.setFont(Font.font("Arial", FontPosture.ITALIC, 12));
                    clicked.setUnderline(true);
                    //System.out.println(clicked.getText());
                    //txtFAnexado.getChildren().remove(ev.getTarget());
                }                            
            }
            if (2 == ev.getClickCount()){
                if(ev.getTarget() instanceof Text) {
                    Text clicked = (Text) ev.getTarget();
                    //System.out.println(clicked.getText());
                    txtFComCopia.getChildren().remove(ev.getTarget());
                }
            }
        });
    }
    private void salvarCI(int nTipoCI){       
    try{
        emf = Persistence.createEntityManagerFactory("CI_EletronicoPU");
        em = emf.createEntityManager();        
        em.getTransaction().begin();
        
//        TbUsuario nIdUser= new TbUsuario(nIdUsuario);
//        TbUnidadeOrganizacional nIdUnidadeOrganizacional = new TbUnidadeOrganizacional(nIdUO);        
        
        //Verificamos e seteamos as variaveis a serem persistidos dentro da tabela TB_COMUNICACAO_INTERNA
        boolean bCoinAutorizado = false;
        boolean bCoinUOArquivado = false;
        boolean bCoinUOGestorArquivado = false;
        boolean bCoinReadOnly = false;
        boolean bCoinTemAnexos = false;
        boolean bCoinDestinatarioGestorAutorizado = false;
        boolean bCoinRemitenteGestorAutorizado = false;
        
        //Variaveis utilizadas nas CIs encaminhadas para não perder número de CI
        //criada e quem foi o Remitente inicial (serve para saber quais anexos acompanham o despacho também)
        int nlIdCoinGenesis = 0;
        int nlIdUnorGenesis = 0;
        int nlCoinNumeroGenesis = 0;
        String strCoinHistoricoAnexos = "";
        String strUnorDescricaoGenesis = "";
        
        //Variavel para salvar Assinatura em formato MD5
        String strMD5Assinatura = "";
        //----------------------------------------------------------------------
        
        String strlAssunto = "";
        strlAssunto = "<FONT COLOR=\"000000\">Assunto: </FONT><FONT COLOR=\"00CC00\"><b>"+this.txtAssunto.getText()+"</b></FONT>";
        
        TbComunicacaoInterna SequencialUO = new TbComunicacaoInterna();
        TbUnidadeOrganizacionalGestor UOGestorDestinatario = new TbUnidadeOrganizacionalGestor();
        MainWindowQueries consultaUOGestor = new MainWindowQueries();
        int nSequencialUO = 0;
        String strPara="";
        String strComCopia = "";
        String strDelimiters = "-|\\;";
        String strHtmlConteudo = "";
        
        int [] nArrayCCIdUODestinatario = new int[0]; // = new int[strParts.length/2];   //Dividimos x 2 para pegar só a quantidade exata de Ids no array
        int [] nArrayCCIdUOGestor = new int[0];// = new int[strParts.length/2];
        String [] strArrayCCUONomeDestinatario = new String[0];  
        
        
        //Seteamos Datas
        String strToday = "";
        String strTodayCI = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // utilizado para banco de dados
        DateFormat dfCI = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); // utilizado para mostrar na aplicação
        java.util.Date data_criacao; 
        java.util.Date data_autorizado;
        Calendar data = Calendar.getInstance();
        int nYear = data.get(Calendar.YEAR);
        int nSize = 0;
        data_criacao = new Date();
        strToday = df.format(data_criacao);        
        strTodayCI = dfCI.format(data_criacao); 
        
        //Seteamos Assinatura MD5
        strMD5Assinatura = Seguranca.stringToMD5(new java.util.Date().toString() + strgUserLogin);
        //---------------------------------------
        
        //Seteamos entity TB_COMUNICACAO_INTERNA e TB_CI_DESTINATARIO
        TbTipoComunicacoInterna TipoCI = new TbTipoComunicacoInterna(nTipoCI);
         
        
        TbComunicacaoInterna newTbCI = new TbComunicacaoInterna();
       
        
        //Verificamos se CI vai possuir anexos
        nSize = txtFAnexado.getChildren().size();        
        if (nSize > 0) {
            bCoinTemAnexos = true;
        }
        
        //Verificamos se CI precisa ser autorizado   
        if ((nIdUO == nIdUOGestor)){
            switch (nTipoPerfil) {
                case 1: //Perfil de Gestor
                    bCoinAutorizado = true;
                    data_autorizado = data_criacao;
                    newTbCI.setCoinDataAutorizado(data_autorizado);
                    bCoinRemitenteGestorAutorizado = true;
                    break;
                default:
                    bCoinAutorizado = false;
                    bCoinRemitenteGestorAutorizado = false;
                    break;
            }           
        } else {
            bCoinAutorizado = false;
        }
                
        //Seteamos variavel e html Para:
        ObservableList<Node> nodesPara = txtFPara.getChildren();
        StringBuilder sbPara = new StringBuilder();
        for (Node node : nodesPara) { 
            sbPara.append((((Text)node).getText()));                 
        }
        strPara = sbPara.toString();
        strPara = ltrim(strPara);
        strPara = rtrim(strPara);
        
        String[] strParts = strPara.split(strDelimiters);
        int nSavePara = 0;
        int nSaveComCopia = 0;
        nSavePara = strParts.length/2;
        int [] nArrayIdUODestinatario = new int[strParts.length/2];   //Dividimos x 2 para pegar só a quantidade exata de Ids no array
        int [] nArrayIdUOGestor = new int[strParts.length/2];
        
        String [] strArrayUONomeDestinatario = new String[strParts.length/2];
        strPara = "";
        switch (nTipoCI){
            case 1:
                //CI-eletrônico
                strPara = "<br /><hr><br /><b><FONT COLOR=\"0000FF\">CI</FONT></b>";
                strPara = strPara.concat("<br /><hr><br /><FONT COLOR=\"000000\">De: <b>" + strNomeUO + "</b></FONT><br />");
                strPara = strPara.concat("<FONT COLOR=\"000000\">Usuário remitente: " + strNomeUsuario + "</FONT><br /><br />");
                strPara = strPara.concat("<FONT COLOR=\"000000\">Data criação: " + strTodayCI + "</FONT><br /><br />");
                strPara = strPara.concat("<FONT COLOR=\"000000\">Para: </FONT><b>");
                break;
            case 2:
                //CI circular
                strPara = "<br><hr><br><b><FONT COLOR=\"0000FF\">CI CIRCULAR</FONT></b>";
                strPara = strPara.concat("<br><hr><br><FONT COLOR=\"000000\">De: <b>" + strNomeUO + "</b></FONT>><br>");
                strPara = strPara.concat("<FONT COLOR=\"000000\">Usuário remitente: " + strNomeUsuario + "</FONT><br><br>");
                strPara = strPara.concat("<FONT COLOR=\"000000\">Data criação: " + strTodayCI + "</FONT><br><br>");
                strPara = strPara.concat("<FONT COLOR=\"000000\">Para: </FONT><b>");
                break;
            case 3:
                //Despacho
                strPara = "<br><hr><br><b><FONT COLOR=\"0000FF\">CI CONFIDENCIAL</FONT></b>";
                strPara = strPara.concat("<br><hr><br><FONT COLOR=\"000000\">De: <b>" + strNomeUO + "</b></FONT><br>");
                strPara = strPara.concat("<FONT COLOR=\"000000\">Usuário remitente: " + strNomeUsuario + "</FONT><br><br>");
                strPara = strPara.concat("<FONT COLOR=\"000000\">Data criação: " + strTodayCI + "</FONT><br><br>");
                strPara = strPara.concat("<FONT COLOR=\"000000\">Para: </FONT><b>");
                break;
            case 4:
                //Despacho
                strPara = "<br><hr><br><b><FONT COLOR=\"0000FF\">DESPACHO</FONT></b>";
                strPara = strPara.concat("<br><hr><br><FONT COLOR=\"000000\">De: <b>" + strNomeUO + "</b></FONT><br>");
                strPara = strPara.concat("<FONT COLOR=\"000000\">Usuário remitente: " + strNomeUsuario + "</FONT><br><br>");
                strPara = strPara.concat("<FONT COLOR=\"000000\">Data criação: " + strTodayCI + "</FONT><br><br>");
                strPara = strPara.concat("<FONT COLOR=\"000000\">Para: </FONT><b>");
                break;
            default:
                break;
        }
        if (4 == nTipoCI){
            //Despacho
//            strPara = "<br><hr><br><b><FONT COLOR=\"0000FF\">DESPACHO</FONT></b><br>";
//            strPara = strPara.concat("<br><hr><br>De: <b>" + strNomeUO + "</b><br>");
//            strPara = strPara.concat("Usuário remitente: " + strNomeUsuario + "<br><br>");
//            strPara = strPara.concat("Para: <b>");
                    
        } else {
//            strPara = "<br><hr><br>De: <b>" + strNomeUO + "</b><br>";
//            strPara = strPara.concat("Usuário remitente: " + strNomeUsuario + "<br><br>");
//            strPara = strPara.concat("Para: <b>");
        }
        String strUONome = "";
        String strIdUO = "";
        String strIdsUOPara = "";
        int j = 1;  //Variavel utilizada para saber se contador é multiplo de 2
        int y = 0;  //variavel utilizada  para setear o indice de array de String
        int z = 0;  //variavel utilizada  para setear o indice de array de int
        TbUnidadeOrganizacional nIdUOTemp;
        for (int i = 0; i < strParts.length; i++){
            System.out.println(strParts[i]);
            if ((j%2) == 0){
                strArrayUONomeDestinatario[y] = strParts[i].trim();
                strUONome = strParts[i]; 
                strPara=strPara.concat(strUONome);
                strPara=strPara.concat("; ");
                y++;
            } else {
                nArrayIdUODestinatario[z] = Integer.parseInt(strParts[i].trim());
                nIdUOTemp = new TbUnidadeOrganizacional(Integer.parseInt(strParts[i].trim()));
                strIdUO = strParts[i];
                strIdsUOPara = strIdsUOPara.concat(strIdUO);
                strIdsUOPara = ltrim(strIdsUOPara);
                strIdsUOPara = rtrim(strIdsUOPara);
                strIdsUOPara = strIdsUOPara.concat(";");
                //Seteamos UO Gestora do destinatario
                UOGestorDestinatario = consultaUOGestor.getIdUOGestor(nIdUOTemp);
                nArrayIdUOGestor[z] = UOGestorDestinatario.getIdUoGestor();
                //---------------------------------
                              
                z++;
            }
            j++;
            
        }
        strPara = strPara.concat("</b><br />");
        
//        System.out.println(strPara);
//        System.out.println(strIdsUOPara);
//        System.out.println(strArrayUONomeDestinatario);
//        System.out.println(nArrayIdUODestinatario);
//        System.out.println(nArrayIdUOGestor);
        
        // ---- FIM: Seteamos variavel e html Para -------        
        
        //Seteamos variavel e html Com cópia:
        strComCopia = "";
        strComCopia = strComCopia.concat("<FONT COLOR=\"000000\">Com cópia: </FONT><br />");
        if (txtFComCopia.getChildren().size() > 0) {
            
            ObservableList<Node> nodesComCopia = txtFComCopia.getChildren();
            StringBuilder sbComCopia = new StringBuilder();
            for (Node node : nodesComCopia) { 
                sbComCopia.append((((Text)node).getText()));                 
            }
            strComCopia = sbComCopia.toString();
            strComCopia = ltrim(strComCopia);
            strComCopia = rtrim(strComCopia);
            
            strParts = strComCopia.split(strDelimiters);
            //int nSaveComCopia = 0;
            nSaveComCopia = strParts.length/2;
//            int [] nArrayCCIdUODestinatario = new int[strParts.length/2];   //Dividimos x 2 para pegar só a quantidade exata de Ids no array
//            int [] nArrayCCIdUOGestor = new int[strParts.length/2];
            nArrayCCIdUODestinatario = new int[strParts.length/2];   //Dividimos x 2 para pegar só a quantidade exata de Ids no array
            nArrayCCIdUOGestor = new int[strParts.length/2];
            //String [] strArrayCCUONomeDestinatario = new String[strParts.length/2];
            strArrayCCUONomeDestinatario = new String[strParts.length/2];
            strComCopia = "";            
            strComCopia = strComCopia.concat("<FONT COLOR=\"000000\">Com cópia: </FONT><b>");
            //strComCopia = strComCopia.concat("<b>");
            strUONome = "";
            strIdUO = "";
            String strIdsUOComCopia = "";
            j = 1;  //Variavel utilizada para saber se contador é multiplo de 2
            y = 0;  //variavel utilizada  para setear o indice de array de String
            z = 0;  //variavel utilizada  para setear o indice de array de int
        for (int i = 0; i < strParts.length; i++){
            System.out.println(strParts[i]);
            if ((j%2) == 0){
                strArrayCCUONomeDestinatario[y] = strParts[i].trim();
                strUONome = strParts[i]; 
                strComCopia=strComCopia.concat(strUONome);
                strComCopia=strComCopia.concat("; ");
                y++;
            } else {
                nArrayCCIdUODestinatario[z] = Integer.parseInt(strParts[i].trim());
                nIdUOTemp = new TbUnidadeOrganizacional(Integer.parseInt(strParts[i].trim()));
                strIdUO = strParts[i];
                strIdsUOComCopia = strIdsUOComCopia.concat(strIdUO);
                strIdsUOComCopia = ltrim(strIdsUOComCopia);
                strIdsUOComCopia = rtrim(strIdsUOComCopia);
                strIdsUOComCopia = strIdsUOComCopia.concat(";");
                
               //Seteamos UO Gestora do destinatario
                UOGestorDestinatario = consultaUOGestor.getIdUOGestor(nIdUOTemp);
                nArrayCCIdUOGestor[z] = UOGestorDestinatario.getIdUoGestor();
                //---------------------------------
                
                z++;
            }
            j++;
            
        }
        strComCopia = strComCopia.concat("</b><br />");
        
//        System.out.println(strComCopia);
//        System.out.println(strIdsUOComCopia);
//        System.out.println(nArrayCCIdUODestinatario);
//        System.out.println(strArrayCCUONomeDestinatario);         
//        System.out.println(nArrayCCIdUOGestor); 
//        System.out.println();
            
        }
        
        strPara = strPara.concat(strComCopia);
        
        //Adicionamos Assunto        
        strPara = strPara.concat(strlAssunto);
        //--------------------------------------------
        strPara = strPara.concat("<br /><hr><br />");
        
        strHtmlConteudo = htmlEditor.getHtmlText();
        
        //strPara = strPara.concat(strHtmlConteudo);
        
        if (4 == nTipoCI ){
            //Despacho
            strPara = strPara.concat(strHtmlConteudo);
            strPara = strPara.concat(strHtmlEncaminharConteudo);
        }
        else {
            strPara = strPara.concat(strHtmlConteudo);
        }
        
//        System.out.println();
        //Seteamos número sequencial da CI de acordo ao UO Remitente
        try{
            TypedQuery<Integer> query = em.createQuery("SELECT max(c.coinNumero) FROM TbComunicacaoInterna c WHERE c.idUnidadeOrganizacional = :idUnidadeOrganizacional AND FUNCTION('YEAR',c.coinDataCriacao) = :Ano",Integer.class)            
                                            .setParameter("idUnidadeOrganizacional", nIdUO)
                                            .setParameter("Ano", nYear);
            Integer Resultado = query.getSingleResult();            
            
            if (null != Resultado){
                //Se for ntipoCI == 4 Encaminhar CI então Sequencial não muda
                //e por default já está autorizado para ser visualizado pelos 
                //destinatários
                if (4 == nTipoCI){
                    nSequencialUO = Resultado.intValue();
                    
                    bCoinAutorizado = true;
                    data_autorizado = data_criacao;
                    newTbCI.setCoinDataAutorizado(data_autorizado);
                    bCoinRemitenteGestorAutorizado = true;
                    
                    //Valores para variaveis Genesis
                    nlIdCoinGenesis = this.nlIdCoinGenesis;
                    nlIdUnorGenesis = this.nlIdUnorGenesis;
                    nlCoinNumeroGenesis = this.nlCoinNumeroGenesis;
                    strCoinHistoricoAnexos = this.strCoinHistoricoAnexos;
                    strUnorDescricaoGenesis = this.strUnorDescricaoGenesis;
                    
                    // O valor MD5 da Assinatura é feito através da utilização
                    // da variavel globlal strgUserLogin
                    strMD5Assinatura = this.strgUserLogin; 
                    
                }else{
                    nSequencialUO = Resultado.intValue();
                    nSequencialUO++;
                    //Valores para variaveis Genesis
                    nlIdCoinGenesis = 0; 
                    nlIdUnorGenesis = nIdUO;
                    nlCoinNumeroGenesis = nSequencialUO;
                    strCoinHistoricoAnexos = "";
                    strUnorDescricaoGenesis = strNomeUO;
                    
                    
                }
            } else {
                nSequencialUO = 0;
                nSequencialUO++;
                
                //Valores para variaveis Genesis
                nlIdCoinGenesis = 0; 
                nlIdUnorGenesis = nIdUO;
                nlCoinNumeroGenesis = nSequencialUO;
                strCoinHistoricoAnexos = "";
                strUnorDescricaoGenesis = strNomeUO;
                
                
            }
        } catch(Exception ex){
            nSequencialUO = 0;
            nSequencialUO++;
        }            
        
        if (4 == nTipoCI){
            bCoinAutorizado = true;            
            data_autorizado = data_criacao;
            newTbCI.setCoinDataAutorizado(data_autorizado);
            
            //Valores para variaveis Genesis
            nlIdCoinGenesis = this.nlIdCoinGenesis;
            nlIdUnorGenesis = this.nlIdUnorGenesis;
            nlCoinNumeroGenesis = this.nlCoinNumeroGenesis;
            strCoinHistoricoAnexos = this.strCoinHistoricoAnexos;
            strUnorDescricaoGenesis = this.strUnorDescricaoGenesis;
            
            // O valor MD5 da Assinatura é feito através da utilização
            // da variavel globlal strgUserLogin
            strMD5Assinatura = this.strgUserLogin; 
            
        }
        //Seteamos entity TB_COMUNICACAO_INTERNA

        newTbCI.setCoinAssunto(txtAssunto.getText());
        //newTbCI.setCoinConteudo(htmlEditor.getHtmlText());
        newTbCI.setCoinConteudo(strPara);
        newTbCI.setIdUsuario(nIdUsuario);
        newTbCI.setUsuNomeCompleto(strNomeUsuario);
        newTbCI.setIdUnidadeOrganizacional(nIdUO);
        newTbCI.setUnorDescricao(strNomeUO);
        newTbCI.setIdUoGestor(nIdUOGestor);
        newTbCI.setCoinAutorizado(bCoinAutorizado);
        newTbCI.setIdTipoCoin(TipoCI);
        newTbCI.setCoinApensamento(txtApensamento.getText());
        newTbCI.setCoinNumero(nSequencialUO);
        newTbCI.setCoinUoArquivado(bCoinUOArquivado);
        newTbCI.setCoinUoGestorArquivado(bCoinUOGestorArquivado);
        newTbCI.setCoinDataCriacao(data_criacao);
//        newTbCI.setCoinDataAutorizado(data_autorizado);
        newTbCI.setCoinReadOnly(bCoinReadOnly);
        newTbCI.setCoinTemAnexos(bCoinTemAnexos);
        //newTbCI.setCoinEnviadoPara("");
        //newTbCI.setCoinEnviadoComCopia("");
        
        newTbCI.setIdCoinGenesis(nlIdCoinGenesis);
        newTbCI.setIdUnorGenesis(nlIdUnorGenesis);
        newTbCI.setCoinNumeroGenesis(nlCoinNumeroGenesis);
        newTbCI.setCoinHistoricoAnexos(strCoinHistoricoAnexos);
        newTbCI.setUnorDescricaoGenesis(strUnorDescricaoGenesis);
        
        newTbCI.setCoinAssinatura(strMD5Assinatura);
        
        em.persist(newTbCI);
        em.flush();
        
        
        long IdCoin = newTbCI.getIdCoin();
        
        //Verificamos se existem anexos a serem salvos        
        //nSize = txtFAnexado.getChildren().size();        
        TbComunicacaoInterna nCoinId = new TbComunicacaoInterna((int)IdCoin);
        if (bCoinTemAnexos) {
            //TbAnexo newAnexo = new TbAnexo();            
            //TbComunicacaoInterna nCoinId = new TbComunicacaoInterna((int)IdCoin);
            //Existem arquivos a serem salvos            
            String strFilePath = "";
            int nlen = 0;
            
            ObservableList<Node> nodes = txtFAnexado.getChildren();
            StringBuilder sb = new StringBuilder();
            for (Node node : nodes) { 
                sb.append((((Text)node).getText()));                 
            }
            strFilePath = sb.toString();
            strFilePath = ltrim(strFilePath);
            strFilePath = rtrim(strFilePath);
            //System.out.println(strFilePath);
            for (String retval: strFilePath.split(";")){
                TbAnexo newAnexo = new TbAnexo();
                retval = ltrim(retval);
                retval = rtrim(retval);
                
                //System.out.println(retval);
                
                // Procuramos o arquivo a ser anexado
                try {
                    File file = new File(retval);
                    FileInputStream fis = new FileInputStream(file);
                    nlen = (int)file.length();
                    newAnexo.setAnexoTamanho(nlen);
                    newAnexo.setAnexoNome(file.getName());
                    newAnexo.setIdCoin(nCoinId);
                    newAnexo.setAnexoBlob(readImageOldWay(file));
                    em.persist(newAnexo);
                   
                } catch (Exception e){
                    e.printStackTrace();                    
                }                
            }
        }
        //------------ FIM da rotina de anexos a serem salvos -----
        
        //-------Inicio da rotina para salvar a CI na tabela TB_CI_DESTINATARIO
        
        //-------Salvamos de acordo campo Para
        //Seteamos o tipo de Envio
        
        TbTipoEnvio nIdTipoEnvio = new TbTipoEnvio(1); //1 - Tipo = ENVIADO "PARA"
        try{        
            for(int n =0 ; n < nSavePara;n++ ){
                TbCiDestinatario newTbCIDestinatario = new TbCiDestinatario();
                //Seteamos boolean bCoinDestinatarioGestorAutorizado
                if(nArrayIdUODestinatario[n] == nArrayIdUOGestor[n]){
                    bCoinDestinatarioGestorAutorizado = true;  
                    //se o UO remitente for UO remitente Gestor então seteamos data
                    if (bCoinAutorizado){
                        data_autorizado = data_criacao;
                        newTbCIDestinatario.setCoinDestinatarioGestorDataAutorizado(data_autorizado);                    
                    }
                }else{
                    bCoinDestinatarioGestorAutorizado = false;                    
                }                
            if (4 == nTipoCI){
            data_autorizado = data_criacao;
            newTbCIDestinatario.setCoinDestinatarioGestorDataAutorizado(data_autorizado);
            bCoinDestinatarioGestorAutorizado = true;     
            bCoinRemitenteGestorAutorizado = true;
            }
            //Seteamos Para

            newTbCIDestinatario.setIdCoin(nCoinId);
            newTbCIDestinatario.setIdUsuarioRemitente(nIdUsuario);
            newTbCIDestinatario.setUsuNomeCompletoRemitente(strNomeUsuario);
            newTbCIDestinatario.setIdUoRemitente(nIdUO);
            newTbCIDestinatario.setInorDescricaoRemitente(strNomeUO);
            newTbCIDestinatario.setIdUoDestinatario(nArrayIdUODestinatario[n]);
            newTbCIDestinatario.setUnorDescricaoDestinatario(strArrayUONomeDestinatario[n]);
            newTbCIDestinatario.setIdUoGestorDestinatario(nArrayIdUOGestor[n]);
            newTbCIDestinatario.setCoinDestinatarioGestorAutorizado(bCoinDestinatarioGestorAutorizado);
            newTbCIDestinatario.setCoinDestinatarioUoArquivado(false);
            newTbCIDestinatario.setCoinDestinatarioUoGestorArquivado(false);
            newTbCIDestinatario.setCoinDestinatarioAssunto(txtAssunto.getText());
            newTbCIDestinatario.setCoinDestinatarioConteudo(strPara);
            newTbCIDestinatario.setCoinDestinatarioPendente(false);
            newTbCIDestinatario.setCoinDestinatarioLido(false);
            newTbCIDestinatario.setCoinDestinatarioDataCriacao(data_criacao);
            newTbCIDestinatario.setIdTipoEnvio(nIdTipoEnvio);
            newTbCIDestinatario.setCoinDestinatarioNumero(nSequencialUO);
            newTbCIDestinatario.setCoinDestinatarioReadOnly(false);
            newTbCIDestinatario.setCoinDestinatarioTemAnexos(bCoinTemAnexos);
            newTbCIDestinatario.setCoinRemitenteGestorAutorizado(bCoinRemitenteGestorAutorizado);
            
            //Variaveis Genesis
            newTbCIDestinatario.setIdCoinGenesis(nlIdCoinGenesis);
            newTbCIDestinatario.setIdUnorGenesis(nlIdUnorGenesis);
            newTbCIDestinatario.setCoinNumeroGenesis(nlCoinNumeroGenesis);
            newTbCIDestinatario.setCoinHistoricoAnexos(strCoinHistoricoAnexos);
            newTbCIDestinatario.setUnorDescricaoGenesis(strUnorDescricaoGenesis);
            //----------------------------------
            newTbCIDestinatario.setIdTipoCoin(TipoCI);
            
            newTbCIDestinatario.setCoinAssinatura(strMD5Assinatura);
            
            em.persist(newTbCIDestinatario);            
        }
        }catch(javax.persistence.PersistenceException e){
            //e.printStackTrace();
            em.close();
            emf.close();            
        }
        
        //-------Salvamos de acordo campo Com Cópia
        if (txtFComCopia.getChildren().size() > 0) {
            //nArrayCCIdUODestinatario = new int[nSaveComCopia];   //Dividimos x 2 para pegar só a quantidade exata de Ids no array
            //nArrayCCIdUOGestor = new int[nSaveComCopia];
            //Seteamos o tipo de Envio
            nIdTipoEnvio = new TbTipoEnvio(2); //2 - Tipo = ENVIADO "COM COPIA"
            try{        
                for(int n =0 ; n < nSaveComCopia;n++ ){
                    TbCiDestinatario newTbCIDestinatario = new TbCiDestinatario();
                    //Seteamos boolean bCoinDestinatarioGestorAutorizado
                    //if(nArrayIdUODestinatario[n] == nArrayIdUOGestor[n]){
                    if(nArrayCCIdUODestinatario[n] == nArrayCCIdUOGestor[n]){
                        bCoinDestinatarioGestorAutorizado = true;  
                        //se o UO remitente for UO remitente Gestor então seteamos data
                        if (bCoinAutorizado){
                            data_autorizado = data_criacao;
                            newTbCIDestinatario.setCoinDestinatarioGestorDataAutorizado(data_autorizado);                    
                        }
                    }else{
                        bCoinDestinatarioGestorAutorizado = false;                    
                    }  
                if (4 == nTipoCI){
                    data_autorizado = data_criacao;
                    newTbCIDestinatario.setCoinDestinatarioGestorDataAutorizado(data_autorizado);
                    bCoinDestinatarioGestorAutorizado = true;     
                    bCoinRemitenteGestorAutorizado = true;
                }
                
                //Seteamos Com Copia
                newTbCIDestinatario.setIdCoin(nCoinId);
                newTbCIDestinatario.setIdUsuarioRemitente(nIdUsuario);
                newTbCIDestinatario.setUsuNomeCompletoRemitente(strNomeUsuario);
                newTbCIDestinatario.setIdUoRemitente(nIdUO);
                newTbCIDestinatario.setInorDescricaoRemitente(strNomeUO);
                newTbCIDestinatario.setIdUoDestinatario(nArrayCCIdUODestinatario[n]);
                newTbCIDestinatario.setUnorDescricaoDestinatario(strArrayCCUONomeDestinatario[n]);
                newTbCIDestinatario.setIdUoGestorDestinatario(nArrayCCIdUOGestor[n]);
                newTbCIDestinatario.setCoinDestinatarioGestorAutorizado(bCoinDestinatarioGestorAutorizado);
                newTbCIDestinatario.setCoinDestinatarioUoArquivado(false);
                newTbCIDestinatario.setCoinDestinatarioUoGestorArquivado(false);
                newTbCIDestinatario.setCoinDestinatarioAssunto(txtAssunto.getText());
                newTbCIDestinatario.setCoinDestinatarioConteudo(strPara);
                newTbCIDestinatario.setCoinDestinatarioPendente(false);
                newTbCIDestinatario.setCoinDestinatarioLido(false);
                newTbCIDestinatario.setCoinDestinatarioDataCriacao(data_criacao);
                newTbCIDestinatario.setIdTipoEnvio(nIdTipoEnvio);
                newTbCIDestinatario.setCoinDestinatarioNumero(nSequencialUO);
                newTbCIDestinatario.setCoinDestinatarioReadOnly(false);
                newTbCIDestinatario.setCoinDestinatarioTemAnexos(bCoinTemAnexos);
                newTbCIDestinatario.setCoinRemitenteGestorAutorizado(bCoinRemitenteGestorAutorizado);
                
                //Variaveis Genesis
                newTbCIDestinatario.setIdCoinGenesis(nlIdCoinGenesis);
                newTbCIDestinatario.setIdUnorGenesis(nlIdUnorGenesis);
                newTbCIDestinatario.setCoinNumeroGenesis(nlCoinNumeroGenesis);
                newTbCIDestinatario.setCoinHistoricoAnexos(strCoinHistoricoAnexos);
                newTbCIDestinatario.setUnorDescricaoGenesis(strUnorDescricaoGenesis);
                //------------------------------------------
                newTbCIDestinatario.setIdTipoCoin(TipoCI);
                newTbCIDestinatario.setCoinAssinatura(strMD5Assinatura);
                em.persist(newTbCIDestinatario);            
            }
            }catch(javax.persistence.PersistenceException e){
                //e.printStackTrace();
                em.close();
                emf.close();            
            }
        }
        
        
        //----------FIM da rotina para salvar a CI na tabela TB_CI_DESTINATARIO        
        em.getTransaction().commit();            
        em.close();
        emf.close();
        // Show the error message.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText("Envio de CI");
        alert.setContentText("A informação foi salva e enviada ao(s) destinatário(s)");
        alert.showAndWait();
        
        } catch (javax.persistence.PersistenceException e) {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha no envio da CI");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            //e.printStackTrace();
            em.close();
            emf.close();
        }        
    }
    
    public byte[] readImageOldWay(File file) throws IOException {
    //Logger.getLogger(Main.class.getName()).log(Level.INFO, "[Open File] " + file.getAbsolutePath());
    InputStream is = new FileInputStream(file);
    // Get the size of the file
    long length = file.length();
    // You cannot create an array using a long type.
    // It needs to be an int type.
    // Before converting to an int type, check
    // to ensure that file is not larger than Integer.MAX_VALUE.
    if (length > Integer.MAX_VALUE){
        // File is too large
        // Show the error message.
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Falha no envio do Arquivo");
        alert.setContentText("Arquivo é grande demais para ser enviado através da CI-e");
        alert.showAndWait();
    }
    // Create the byte array to hold the data
    byte[] bytes = new byte[(int) length];
    // Read in the bytes
    int offset = 0;
    int numRead = 0;
    while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0)
    {
      offset += numRead;
    }
    // Ensure all the bytes have been read in
    if (offset < bytes.length)
    {
      throw new IOException("Não foi possível concluir a leitura do arquivo: " + file.getName());
    }
    // Close the input stream and return bytes
    is.close();
    return bytes;
}
    public static String rtrim(String s) {
        int i = s.length()-1;
        while (i >= 0 && Character.isWhitespace(s.charAt(i))) {
            i--;
        }
        return s.substring(0,i+1);
    }
    public static String ltrim(String s) {
        int i = 0;
        while (i < s.length() && Character.isWhitespace(s.charAt(i))) {
            System.out.println("s.charAt(i)  "+s.charAt(i));
            i++;
        }
        return s.substring(i);
    }
    
}
