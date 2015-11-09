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
        String strTwoNewLines;
        strTwoNewLines = "<br><br><br>";        
        String strAssinatura;
        strAssinatura = strTwoNewLines
                + "<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><b><span style=\"font-size:11.0pt;line-height:\n" +
"115%;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;\">Senhor Gerente,<o:p></o:p></span></b></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><b><span style=\"font-size:11.0pt;line-height:\n" +
"115%;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;\">&nbsp;</span></b></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:11.0pt;line-height:115%;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;\">Considerando\n" +
"relevância das atividades exercidas por este Setor, e que entre elas\n" +
"encontram-se o controle e arquivamento dos seguintes:<o:p></o:p></span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:11.0pt;line-height:115%;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;\">&nbsp;<o:p></o:p></span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:10.0pt;mso-bidi-font-size:11.0pt;line-height:115%;font-family:\n" +
"&quot;Arial&quot;,&quot;sans-serif&quot;\">a) Processo de Defesa julgado pela CDA, JARI e CETRAN<o:p></o:p></span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:10.0pt;mso-bidi-font-size:11.0pt;line-height:115%;font-family:\n" +
"&quot;Arial&quot;,&quot;sans-serif&quot;\">b) Apresentação de condutor<o:p></o:p></span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:10.0pt;mso-bidi-font-size:11.0pt;line-height:115%;font-family:\n" +
"&quot;Arial&quot;,&quot;sans-serif&quot;\">c) Auto de Infração de Trânsito (depois de conferido pelo\n" +
"SEPIT)<o:p></o:p></span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:10.0pt;mso-bidi-font-size:11.0pt;line-height:115%;font-family:\n" +
"&quot;Arial&quot;,&quot;sans-serif&quot;\">d) Efeito Suspensivo após aplicação<o:p></o:p></span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:10.0pt;mso-bidi-font-size:11.0pt;line-height:115%;font-family:\n" +
"&quot;Arial&quot;,&quot;sans-serif&quot;\">e) Laudos de Aferição dos radares, lombadas e Sensores<o:p></o:p></span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:10.0pt;mso-bidi-font-size:11.0pt;line-height:115%;font-family:\n" +
"&quot;Arial&quot;,&quot;sans-serif&quot;\">f) Relatórios de postagem do Correios das NAIs e NIPs\n" +
"expedidas<o:p></o:p></span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:10.0pt;mso-bidi-font-size:11.0pt;line-height:115%;font-family:\n" +
"&quot;Arial&quot;,&quot;sans-serif&quot;\">g) Croqui de localização dos equipamentos<o:p></o:p></span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:10.0pt;mso-bidi-font-size:11.0pt;line-height:115%;font-family:\n" +
"&quot;Arial&quot;,&quot;sans-serif&quot;\">h) Croqui de localização das sinalizações<o:p></o:p></span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:10.0pt;mso-bidi-font-size:11.0pt;line-height:115%;font-family:\n" +
"&quot;Arial&quot;,&quot;sans-serif&quot;\">i) Solicitação de ressarcimento<o:p></o:p></span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:10.0pt;mso-bidi-font-size:11.0pt;line-height:115%;font-family:\n" +
"&quot;Arial&quot;,&quot;sans-serif&quot;\">j) E outros procedimentos que classificamos como diversos<o:p></o:p></span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:11.0pt;line-height:115%;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;\">&nbsp;</span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:11.0pt;line-height:115%;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;\">Vale\n" +
"destacar que, a média atual de novos processos de defesa/recurso que era de 970/mês\n" +
"em 2013, passou a ser de 4.190/mês, e aqui cumpre informar, que acabamos de\n" +
"receber (26/10) mais de 2.000 mil processos oriundos da JARI e do Setor de\n" +
"Atendimento (SETAP).<o:p></o:p></span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:11.0pt;line-height:115%;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;\">&nbsp;</span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:11.0pt;line-height:115%;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;\">Vale\n" +
"dizer que desde o dia 21/10 que estamos instruindo as defesas, uma vez que os\n" +
"processos de recurso em sua maioria requer acesso aos processos de defesa indeferidos\n" +
"ou de JARI não provido, e estes em sua maioria ainda se encontra no Comércio,\n" +
"bem como as apresentações de condutor infrator dos anos de 2015 (abril), 2014 e\n" +
"2013.<o:p></o:p></span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:11.0pt;line-height:115%;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;\">&nbsp;</span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:11.0pt;line-height:115%;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;\">Considere\n" +
"ainda que este Setor tem se esforçado, alcançando mais de 800 processos de\n" +
"defesa instruídos em 03 dias, alimentando o sistema com NAI/NIP reintegrados,\n" +
"recebendo e enviando para covalidação dos agentes de trânsito, e ainda\n" +
"arrumando (da forma que se pode) o arquivo de autos lavrados dos anos de 2014/2015.<o:p></o:p></span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:11.0pt;line-height:115%;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;\">&nbsp;</span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:11.0pt;line-height:115%;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;\">Portanto,\n" +
"a necessidade de arquivar os mais de 16 mil processos (relatório de agosto/15),\n" +
"e AITs de responsabilidade desta gerência através deste setor, foi amplamente\n" +
"reduzida, motivo pelo qual não podemos garantir a integridade e guarda dos\n" +
"mesmos. E como solução, recomendamos opinativamente que sejam os processos de\n" +
"defesa e recurso julgados armazenados no Arquivo Geral desta Autarquia.<o:p></o:p></span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:11.0pt;line-height:115%;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;\">&nbsp;</span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:11.0pt;line-height:115%;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;\">&nbsp;</span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:11.0pt;line-height:115%;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;\">Certo\n" +
"de vosso apreço renovo nosso compromisso com as atribuições deste setor.<o:p></o:p></span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:11.0pt;line-height:115%;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;\">&nbsp;</span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:11.0pt;line-height:115%;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;\">Atenciosamente,<o:p></o:p></span></p>\n" +
"\n" +
"<p class=\"MsoNormal\" style=\"text-align:justify;line-height:115%\"><span style=\"font-size:11.0pt;line-height:115%;font-family:&quot;Arial&quot;,&quot;sans-serif&quot;\">&nbsp;</span></p>\n" +
"\n" +
"<table class=\"MsoNormalTable\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" width=\"171\" style=\"width:128.0pt;border-collapse:collapse;mso-yfti-tbllook:480;\n" +
" mso-table-lspace:7.05pt;margin-left:4.8pt;mso-table-rspace:7.05pt;margin-right:\n" +
" 4.8pt;mso-table-anchor-vertical:paragraph;mso-table-anchor-horizontal:margin;\n" +
" mso-table-left:left;mso-table-top:11.7pt;mso-padding-alt:0cm 5.4pt 0cm 5.4pt\">\n" +
" <tbody><tr>\n" +
"  <td width=\"171\" valign=\"top\" style=\"width:128.0pt;padding:0cm 5.4pt 0cm 5.4pt\">\n" +
"  <p class=\"MsoTitle\"><span style=\"font-size:10.0pt;font-variant:small-caps\">Rubens Araújo<o:p></o:p></span></p>\n" +
"  <p class=\"MsoTitle\"><span style=\"font-size:8.0pt\">Mat. </span><span style=\"font-size: 8pt;\">2226227<o:p></o:p></span></p>\n" +
"  <p class=\"MsoTitle\"><span style=\"font-size: 8pt;\">Chefe SEINP</span><span style=\"font-size: 10pt;\"><o:p></o:p></span></p>\n" +
"  </td>\n" +
" </tr>\n" +
"</tbody></table><br/>null"
                +"<p class=\"MsoNormal\" align=\"left\" style=\"margin: 0px 0px 0.0001pt; font-family: arial, helvetica, sans-serif; font-size: 10pt;\"><span style=\"font-size: 10pt; font-family: Arial, sans-serif;\">Atenciosamente,</span></p><p class=\"MsoNormal\" align=\"left\" style=\"margin: 0px 0px 0.0001pt; font-family: arial, helvetica, sans-serif; font-size: 13.3333330154419px;\"><br></p><div id=\"marca_assinatura\" style=\"font-family: 'Times New Roman'; font-size: medium; background-color: rgb(255, 255, 255);\"></div><div style=\"font-family: 'Times New Roman'; font-size: medium; margin-left: 150pt; background-color: rgb(255, 255, 255);\"><p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0.0001pt; font-size: 12pt; font-family: 'Times New Roman', serif;\"><br></p><p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0.0001pt; font-size: 12pt; font-family: 'Times New Roman', serif;\"><br></p><p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0.0001pt; font-size: 12pt; font-family: 'Times New Roman', serif;\"><b><span style=\"font-size: 13.5pt; font-family: Arial, sans-serif;\">Itanara Serapião de Souza</span></b></p><p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0.0001pt; font-size: 12pt; font-family: 'Times New Roman', serif;\"><b><span style=\"font-size: 13.5pt; font-family: Arial, sans-serif;\">Ouvidoria Setorial TRANSALVADOR</span></b></p></div><div style=\"font-size: 10pt; font-family: 'Times New Roman'; margin-left: 150pt; background-color: rgb(255, 255, 255);\"><font size=\"2\"><span style=\"font-family: Lato, sans-serif;\">A</span><span style=\"font-family: Lato, sans-serif;\">. Vale dos Barris, nº 501 - Barris - Salvador - Bahia</span></font></div><div style=\"font-size: 10pt; font-family: 'Times New Roman'; margin-left: 150pt; background-color: rgb(255, 255, 255);\"><p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0.0001pt; font-family: 'Times New Roman', serif;\"><span style=\"font-family: Arial, sans-serif;\"><font size=\"2\">+55(71)3202-9085 (informar&nbsp;</font></span><span style=\"font-family: Arial, sans-serif; font-size: small; text-align: center;\">número do registro de protocolo do 118)</span></p></div><div style=\"font-family: 'Times New Roman'; font-size: medium; margin-left: 150pt; background-color: rgb(255, 255, 255);\"><p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0.0001pt; font-size: 12pt; font-family: 'Times New Roman', serif;\"></p></div><div style=\"font-family: 'Times New Roman'; font-size: medium; margin-left: 150pt; background-color: rgb(255, 255, 255);\"><p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0.0001pt; font-size: 12pt; font-family: 'Times New Roman', serif;\"></p></div><div style=\"font-family: arial, helvetica, sans-serif; font-size: 13.3333330154419px; margin-left: 150pt; background-color: rgb(255, 255, 255);\"><p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0.0001pt; font-size: 10pt;\"><font face=\"Arial, sans-serif\"><span style=\"font-size: 14px;\">http://www.transalvador.salvador.ba.gov.br</span></font></p><p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0.0001pt; font-size: 10pt;\"><font face=\"Arial, sans-serif\"><span style=\"font-size: 14px;\">ouvidoria.transalvador@salvador.ba.gov.br</span></font></p><p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0.0001pt;\"><font face=\"Arial, sans-serif\"><span style=\"font-size: 14px;\">http://www.salvador.ba.gov.br/</span></font></p><div><font face=\"Arial, sans-serif\"><span style=\"font-size: 14px;\"><br></span></font></div></div><div style=\"font-family: 'Times New Roman'; font-size: medium; margin-top: 7.5pt; background-color: rgb(255, 255, 255);\"><p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0.0001pt; font-size: 12pt; font-family: 'Times New Roman', serif;\"></p></div><p class=\"MsoNormal\" align=\"center\" style=\"margin: 0px 0px 0.0001pt; font-family: arial, helvetica, sans-serif; font-size: 10pt;\"><b><i><span style=\"font-family: Arial, sans-serif;\"><font size=\"3\"></font></span></i></b></p><p class=\"MsoNormal\" align=\"center\" style=\"margin: 0px 0px 0.0001pt; font-family: arial, helvetica, sans-serif; font-size: 10pt; text-align: center;\"><i><span style=\"font-family: Arial, sans-serif;\"></span></i><i><span style=\"font-size: 10pt; font-family: Arial, sans-serif;\"></span></i><i><span style=\"font-size: 10pt; font-family: Arial, sans-serif;\"></span></i></p>";
        htmlEditor.setHtmlText(strAssinatura);
        //htmlEditor.setDisable(true);
        
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
        
        String htmlText = "";
        htmlText = htmlEditor.getHtmlText();
        if ((htmlText.isEmpty()) || (txtAssunto.getText().isEmpty())){
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Assunto/Conteúdo sem dados");
            alert.setHeaderText("Favor preencher os campos Assunto/Conteúdo da CI-e");
            alert.setContentText("O campo Assunto e o campo Conteúdo da CI-e devem possuir dados");
            alert.showAndWait();
            
        } else {
            System.out.println("htmlEditor  = " + htmlText );            
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
}
