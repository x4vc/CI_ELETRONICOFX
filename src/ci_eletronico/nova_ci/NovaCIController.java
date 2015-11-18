/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.nova_ci;

import ci_eletronico.FXMLMainController;
import ci_eletronico.fxml_utilitarios.Win_Para_ComcopiaController;
import java.awt.Desktop;
import java.io.File;
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
    private int nTipoCI = 0;
    private int nIdUOGestor = 0;
    
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
        
    }   
    
    public void setVariaveisAmbienteNovaCI(final FXMLMainController mainController , String strIdUsuario, String strNomeUsuario, 
                                        String strIdUO, String strNomeUO, String strIdPerfil, String strDescricaoPerfil, String strHtmlAssinatura, int nTipoCI, int nIdUOGestor) {
        
        this.strNomeUsuario = strNomeUsuario;
        this.strNomeUO = strNomeUO;          
        this.strDescricaoPerfil = strDescricaoPerfil;
        this.strHtmlAssinatura = "<br><br><br>" + strHtmlAssinatura;
        
        nIdUsuario = Integer.parseInt(strIdUsuario);        
        nTipoPerfil = Integer.parseInt(strIdPerfil);
        nIdUO = Integer.parseInt(strIdUO);
        
        //Preencher o editor Html com assinatura do usuário
        htmlEditor.setHtmlText(this.strHtmlAssinatura);
        this.nTipoCI = nTipoCI;
        this.nIdUOGestor = nIdUOGestor;
        
        
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

                  
                    
//                    linkArquivoSelecionado.setOnAction(ev->{
//                        System.out.println("Click on  link: ");            
//                    });
//                    Text text1 = new Text();
//                    text1.setText("Texto 01");
//                    text1.setFill(Color.BLUE);
//                    text1.setFont(Font.font("Arial", FontPosture.ITALIC, 12));                    
//                    txtFAnexado.getChildren().add(text1);
                    //TextFlow textFlow = new TextFlow(text1);
    }
    private void openFile(File file) {
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
            System.out.println("ID UO Gestora = " + nIdUOGestor);
            //ntipoCI   1 - CI normal
            //          2 - CI circular
            //          3 - CI confidencial
            //          4 - CI encaminhado
            
            salvarCI(nTipoCI);
        }
        
        //Verificamos se existem anexos a serem salvos
        Integer nSize;
        nSize = txtFAnexado.getChildren().size();
        
        if (nSize > 0) {
            //Existem arquivos a serem salvos
            ObservableList<Node> nodes = txtFAnexado.getChildren();
            StringBuilder sb = new StringBuilder();
            for (Node node : nodes) { sb.append((((Text)node).getText()));
            }
            String txt = sb.toString();
            System.out.println("TxtFAnexado  = " + txt );
        }
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
        stage.initModality(Modality.WINDOW_MODAL);

        stage.setScene(scene);
        stage.showAndWait();
        
        ObservableList<String> UOSelected = controller.getUOsPara();
        ObservableList<String> UOFiltrado;
        int nSize = 0;
        nSize = UOSelected.size();
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
                    System.out.println(clicked.getText());
                    //txtFAnexado.getChildren().remove(ev.getTarget());
                }                            
            }
            if (2 == ev.getClickCount()){
                if(ev.getTarget() instanceof Text) {
                    Text clicked = (Text) ev.getTarget();
                    System.out.println(clicked.getText());
                    txtFComCopia.getChildren().remove(ev.getTarget());
                }
            }
        });
    }
    private void salvarCI(int nTipoCI){
        
        
    }
}
