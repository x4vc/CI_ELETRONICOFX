/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.nova_ci;

import ci_eletronico.FXMLMainController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

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
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
}
