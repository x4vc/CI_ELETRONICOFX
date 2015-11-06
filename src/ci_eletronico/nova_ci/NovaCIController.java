/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.nova_ci;

import ci_eletronico.FXMLMainController;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    
    @FXML
    Button btnPara;
    @FXML
    Button btnAnexarArquivos;
    @FXML
    Button btnEnviarNovaCI;
    @FXML
    Button btnComCopia;
    @FXML
    TextField txtPara;
    @FXML
    TextField txtComCopia;
    @FXML
    TextField txtAssunto;
    @FXML
    TextField txtAnexado;
    @FXML
    HTMLEditor htmlEditor;
    @FXML
    TextFlow txtFAnexado;
    
    
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
//        txtFAnexado.setOnMouseClicked(ev->{
//           
//            if(ev.getTarget() instanceof Hyperlink) {
//                Hyperlink clicked = (Hyperlink) ev.getTarget();
//                System.out.println("Click on  mouse: " + clicked.getText());
//            }
//        });
//        linkArquivoSelecionado.setOnMouseClicked(ev->{
//            System.out.println("Click on  link: ");            
//        });
    }   
    
    public void setVariaveisAmbienteNovaCI(final FXMLMainController mainController , String strIdUsuario, String strNomeUsuario, 
                                        String strIdUO, String strNomeUO, String strIdPerfil, String strDescricaoPerfil) {
        
        this.strNomeUsuario = strNomeUsuario;
        this.strNomeUO = strNomeUO;          
        this.strDescricaoPerfil = strDescricaoPerfil;
        
        nIdUsuario = Integer.parseInt(strIdUsuario);        
        nTipoPerfil = Integer.parseInt(strIdPerfil);
        nIdUO = Integer.parseInt(strIdUO);
        
        
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
                            txtAnexado.appendText("\"");
                            txtAnexado.appendText(file.getName());
                            txtAnexado.appendText("\";");
                            
                            listaArquivos.add("\""+file.getName()+"\";");
                            nContador++;
                            
                            txtArquivoSelecionado = new Text();                          
                            txtArquivoSelecionado.setText("\""+ file.getName() + "\";");
                            txtArquivoSelecionado.setFill(Color.BLUE);
                            txtArquivoSelecionado.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
                           
                            linkArquivoSelecionado = new Hyperlink();
                            linkArquivoSelecionado.setText("\""+ file.getName() + "\";");
                            
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
       
                scene = new Scene(new StackPane());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ci_eletronico/fxml_utilitarios/Win_Para_Comcopia.fxml"));
                scene.setRoot((Parent) loader.load());
               
//                ci_eletronico.nova_ci.NovaCIController nova_ci_controller = loader.<ci_eletronico.nova_ci.NovaCIController>getController();     
//                nova_ci_controller.setVariaveisAmbienteNovaCI(mainController, strIdUsuario, strNomeUsuario, strIdUO, strNomeUO, strIdPerfil, strDescricaoPerfil);
               

                Stage stage = new Stage();
                stage.setTitle("Selecionar UOs");
                //set icon
                stage.getIcons().add(new Image("/resources/Nova_CI.png"));
                stage.initModality(Modality.WINDOW_MODAL);

                stage.setScene(scene);
                stage.showAndWait();
//                                
       
    }
    
    @FXML
    private void handleBtnEnviarNovaCI(ActionEvent event) throws IOException {
        
        String htmlText = "";
        htmlText = htmlEditor.getHtmlText();
        
        System.out.println("htmEditor  = " + htmlText );
        
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
}
