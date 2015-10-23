/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author victorcmaf
 */
public class FXMLMainController implements Initializable {
    
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
    
    String strIdUsuario, strNomeUsuario;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        strIdUsuario = getStrIdUsuario();
        strNomeUsuario = getStrNomeUsuario();
        lblIdUsuario.setText(strIdUsuario);
        lblNomeUsuario.setText(strNomeUsuario);
        
                
    }    
    
    public String getStrIdUsuario() {
        return strIdUsuario;
    }

    public void setStrIdUsuario(String strIdUsuario) {
        this.strIdUsuario = strIdUsuario;
    }

    public String getStrNomeUsuario() {
        return strNomeUsuario;
    }

    public void setStrNomeUsuario(String strNomeUsuario) {
        this.strNomeUsuario = strNomeUsuario;
    }
    
}
