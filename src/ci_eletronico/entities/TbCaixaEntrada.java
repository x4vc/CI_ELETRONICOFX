/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.entities;

import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Victor
 */
public class TbCaixaEntrada {
    //variables a serem utilizadas na
    //persistencia da tabela TB_CI_DESTINATARIO
    private IntegerProperty intp_idCoinDestinatario;
    private IntegerProperty intp_idCoin;
    private IntegerProperty intp_idUsuarioRemitente;
    private StringProperty strp_UsuarioNomeCompleto;
    private IntegerProperty intp_idUORemitente;
    private StringProperty strp_DescricaoUORemitente;
    private IntegerProperty intp_idUODestinatario;
    private StringProperty strp_DescricaoUODestinatario;
    private IntegerProperty intp_idUOGestorDestinatario;
    private StringProperty strp_DescricaoUOGestorDestinatario;
    private BooleanProperty boolp_AutorizadoPeloGestor;
    private BooleanProperty boolp_ArquivadoPeloUODestinatario;
    private BooleanProperty boolp_ArquivadoPeloUOGestor;    
    private StringProperty strp_Assunto;
    private StringProperty strp_Conteudo;
    private BooleanProperty boolp_PendentePeloUODestinatario;
    private Date dataAutorizadoPeloGestorDestinatario;
    private BooleanProperty boolp_LidoPeloUODestinatario;
    private Date dataCriacao;
    private IntegerProperty intp_idTipoEnvio;  //1-Enviado "Para; 2-Enviado "Com cópia"; 3-Enviado "Com cópia oculta" 
    private IntegerProperty intp_idCoinNumero; //Número sequencial das CIs para cada UO
    private BooleanProperty boolp_ReadOnlyUODestinatario;
    private BooleanProperty boolp_CoinTemAnexos;
    private BooleanProperty boolp_AutorizadoPeloGestorRemitente;
    
    private StringProperty strp_dataCriacao;
    
    //A variavel intp_idTabelaFonte foi criada para saber qual a fonte de dados
    //por tanto, não existe fisicamente mapeado para tabela nenhuma
    private IntegerProperty intp_idTabelaFonte;    //1- TB_COMUNICACAO_INTERNA, 2- TB_CI_DESTINATARIO    
    //------------------------------------------
    
    //----Atributos que estão na tabela TB_COMUNICACAO_INTERNA
    // mas que não existem na tabela TB_CI_DESTINATARIO
//    private IntegerProperty intp_idTipoCoin;    //1-CI NORMAL, 2-CI CIRCULAR, 3-CI CONFIDENCIAL, 4-CI ENCAMINHADO
//    private StringProperty strp_Apensamento;    
//    private BooleanProperty boolp_ArquivadoUORemitente;
//    private BooleanProperty boolp_ArquivadoUOGestor;

    public TbCaixaEntrada() {
    }

    public TbCaixaEntrada(Integer intp_idCoinDestinatario, Integer intp_idCoin, Integer intp_idUsuarioRemitente, 
            String strp_UsuarioNomeCompleto, Integer intp_idUORemitente, String strp_DescricaoUORemitente, 
            Integer intp_idUODestinatario, String strp_DescricaoUODestinatario, Integer intp_idUOGestorDestinatario, 
            String strp_DescricaoUOGestorDestinatario, boolean boolp_AutorizadoPeloGestor, 
            boolean boolp_ArquivadoPeloUODestinatario, boolean boolp_ArquivadoPeloUOGestor, String strp_Assunto, 
            String strp_Conteudo, boolean boolp_PendentePeloUODestinatario, Date dataAutorizadoPeloGestorDestinatario, 
            boolean boolp_LidoPeloUODestinatario, Date dataCriacao, Integer intp_idTipoEnvio, Integer intp_idCoinNumero, 
            boolean boolp_ReadOnlyUODestinatario, boolean boolp_CoinTemAnexos, boolean boolp_AutorizadoPeloGestorRemitente, 
            String strp_dataCriacao, Integer intp_idTabelaFonte) {
        this.intp_idCoinDestinatario = new SimpleIntegerProperty(intp_idCoinDestinatario);
        this.intp_idCoin = new SimpleIntegerProperty(intp_idCoin);
        this.intp_idUsuarioRemitente = new SimpleIntegerProperty(intp_idUsuarioRemitente);
        this.strp_UsuarioNomeCompleto = new SimpleStringProperty(strp_UsuarioNomeCompleto);
        this.intp_idUORemitente = new SimpleIntegerProperty(intp_idUORemitente);
        this.strp_DescricaoUORemitente = new SimpleStringProperty(strp_DescricaoUORemitente);
        this.intp_idUODestinatario = new SimpleIntegerProperty(intp_idUODestinatario);
        this.strp_DescricaoUODestinatario = new SimpleStringProperty(strp_DescricaoUODestinatario);
        this.intp_idUOGestorDestinatario = new SimpleIntegerProperty(intp_idUOGestorDestinatario);
        this.strp_DescricaoUOGestorDestinatario = new SimpleStringProperty(strp_DescricaoUOGestorDestinatario);
        this.boolp_AutorizadoPeloGestor = new SimpleBooleanProperty(boolp_AutorizadoPeloGestor);
        this.boolp_ArquivadoPeloUODestinatario = new SimpleBooleanProperty(boolp_ArquivadoPeloUODestinatario);
        this.boolp_ArquivadoPeloUOGestor = new SimpleBooleanProperty(boolp_ArquivadoPeloUOGestor);
        this.strp_Assunto = new SimpleStringProperty(strp_Assunto);
        this.strp_Conteudo = new SimpleStringProperty(strp_Conteudo);
        this.boolp_PendentePeloUODestinatario = new SimpleBooleanProperty(boolp_PendentePeloUODestinatario);
        this.dataAutorizadoPeloGestorDestinatario = dataAutorizadoPeloGestorDestinatario;
        this.boolp_LidoPeloUODestinatario = new SimpleBooleanProperty(boolp_LidoPeloUODestinatario);
        this.dataCriacao = dataCriacao;
        this.intp_idTipoEnvio = new SimpleIntegerProperty(intp_idTipoEnvio);
        this.intp_idCoinNumero = new SimpleIntegerProperty(intp_idCoinNumero);
        this.boolp_ReadOnlyUODestinatario = new SimpleBooleanProperty(boolp_ReadOnlyUODestinatario);
        this.boolp_CoinTemAnexos = new SimpleBooleanProperty(boolp_CoinTemAnexos);
        this.boolp_AutorizadoPeloGestorRemitente = new SimpleBooleanProperty(boolp_AutorizadoPeloGestorRemitente);
        this.strp_dataCriacao = new SimpleStringProperty(strp_dataCriacao);
        this.intp_idTabelaFonte = new SimpleIntegerProperty(intp_idTabelaFonte);
    }
    

    public Integer getIntp_idCoinDestinatario() {
        return intp_idCoinDestinatario.getValue();
    }

    public void setIntp_idCoinDestinatario(Integer intp_idCoinDestinatario) {
        this.intp_idCoinDestinatario.setValue(intp_idCoinDestinatario);
    }

//    public Integer getIntp_idCoin() {
//        return intp_idCoin.getValue();
//    }
//
//    public void setIntp_idCoin(Integer intp_idCoin) {
//        this.intp_idCoin.setValue(intp_idCoin);
//    }

    public Integer getIntp_idUsuarioRemitente() {
        return intp_idUsuarioRemitente.getValue();
    }

    public void setIntp_idUsuarioRemitente(Integer intp_idUsuarioRemitente) {
        this.intp_idUsuarioRemitente.setValue(intp_idUsuarioRemitente);
    }

//    public String getStrp_UsuarioNomeCompleto() {
//        return strp_UsuarioNomeCompleto.getValue();
//    }
//
//    public void setStrp_UsuarioNomeCompleto(String strp_UsuarioNomeCompleto) {
//        this.strp_UsuarioNomeCompleto.setValue(strp_UsuarioNomeCompleto);
//    }

//    public Integer getIntp_idUORemitente() {
//        return intp_idUORemitente.getValue();
//    }
//
//    public void setIntp_idUORemitente(Integer intp_idUORemitente) {
//        this.intp_idUORemitente.setValue(intp_idUORemitente);
//    }

//    public String getStrp_DescricaoUORemitente() {
//        return strp_DescricaoUORemitente.getValue();
//    }
//
//    public void setStrp_DescricaoUORemitente(String strp_DescricaoUORemitente) {
//        this.strp_DescricaoUORemitente.setValue(strp_DescricaoUORemitente);
//    }

    public Integer getIntp_idUODestinatario() {
        return intp_idUODestinatario.getValue();
    }

    public void setIntp_idUODestinatario(Integer intp_idUODestinatario) {
        this.intp_idUODestinatario.setValue(intp_idUODestinatario);
    }

    public String getStrp_DescricaoUODestinatario() {
        return strp_DescricaoUODestinatario.getValue();
    }

    public void setStrp_DescricaoUODestinatario(String strp_DescricaoUODestinatario) {
        this.strp_DescricaoUODestinatario.setValue(strp_DescricaoUODestinatario);
    }

    public Integer getIntp_idUOGestorDestinatario() {
        return intp_idUOGestorDestinatario.getValue();
    }

    public void setIntp_idUOGestorDestinatario(Integer intp_idUOGestorDestinatario) {
        this.intp_idUOGestorDestinatario.setValue(intp_idUOGestorDestinatario);
    }

    public String getStrp_DescricaoUOGestorDestinatario() {
        return strp_DescricaoUOGestorDestinatario.getValue();
    }

    public void setStrp_DescricaoUOGestorDestinatario(String strp_DescricaoUOGestorDestinatario) {
        this.strp_DescricaoUOGestorDestinatario.setValue(strp_DescricaoUOGestorDestinatario);
    }

    public Boolean getBoolp_AutorizadoPeloGestor() {
        return boolp_AutorizadoPeloGestor.getValue();
    }

    public void setBoolp_AutorizadoPeloGestor(Boolean boolp_AutorizadoPeloGestor) {
        this.boolp_AutorizadoPeloGestor.setValue(boolp_AutorizadoPeloGestor); 
    }

    public Boolean getBoolp_ArquivadoPeloUODestinatario() {
        return boolp_ArquivadoPeloUODestinatario.getValue();
    }

    public void setBoolp_ArquivadoPeloUODestinatario(Boolean boolp_ArquivadoPeloUODestinatario) {
        this.boolp_ArquivadoPeloUODestinatario.setValue(boolp_ArquivadoPeloUODestinatario);
    }

    public Boolean getBoolp_ArquivadoPeloUOGestor() {
        return boolp_ArquivadoPeloUOGestor.getValue();
    }

    public void setBoolp_ArquivadoPeloUOGestor(Boolean boolp_ArquivadoPeloUOGestor) {
        this.boolp_ArquivadoPeloUOGestor.setValue(boolp_ArquivadoPeloUOGestor); 
    }

//    public String getStrp_Assunto() {
//        return strp_Assunto.getValue();
//    }
//
//    public void setStrp_Assunto(String strp_Assunto) {
//        this.strp_Assunto.setValue(strp_Assunto);
//    }
//
//    public String getStrp_Conteudo() {
//        return strp_Conteudo.getValue();
//    }
//
//    public void setStrp_Conteudo(String strp_Conteudo) {
//        this.strp_Conteudo.setValue(strp_Conteudo);
//    }

    public Boolean getBoolp_PendentePeloUODestinatario() {
        return boolp_PendentePeloUODestinatario.getValue();
    }

    public void setBoolp_PendentePeloUODestinatario(Boolean boolp_PendentePeloUODestinatario) {
        this.boolp_PendentePeloUODestinatario.setValue(boolp_PendentePeloUODestinatario);
    }

    public Date getDataAutorizadoPeloGestorDestinatario() {
        return dataAutorizadoPeloGestorDestinatario;
    }

    public void setDataAutorizadoPeloGestorDestinatario(Date dataAutorizadoPeloGestorDestinatario) {
        this.dataAutorizadoPeloGestorDestinatario = dataAutorizadoPeloGestorDestinatario;
    }

    public Boolean getBoolp_LidoPeloUODestinatario() {
        return boolp_LidoPeloUODestinatario.getValue();
    }

    public void setBoolp_LidoPeloUODestinatario(Boolean boolp_LidoPeloUODestinatario) {
        this.boolp_LidoPeloUODestinatario.setValue(boolp_LidoPeloUODestinatario);
    }

//    public Date getDataCriacao() {
//        return dataCriacao;
//    }
//
//    public void setDataCriacao(Date dataCriacao) {
//        this.dataCriacao = dataCriacao;
//    }

    public Integer getIntp_idTipoEnvio() {
        return intp_idTipoEnvio.getValue();
    }

    public void setIntp_idTipoEnvio(Integer intp_idTipoEnvio) {
        this.intp_idTipoEnvio.setValue(intp_idTipoEnvio);
    }

//    public Integer getIntp_idCoinNumero() {
//        return intp_idCoinNumero.getValue();
//    }
//
//    public void setIntp_idCoinNumero(Integer intp_idCoinNumero) {
//        this.intp_idCoinNumero.setValue(intp_idCoinNumero); 
//    }

    public Boolean getBoolp_ReadOnlyUODestinatario() {
        return boolp_ReadOnlyUODestinatario.getValue();
    }

    public void setBoolp_ReadOnlyUODestinatario(Boolean boolp_ReadOnlyUODestinatario) {
        this.boolp_ReadOnlyUODestinatario.setValue(boolp_ReadOnlyUODestinatario);
    }

//    public Boolean getBoolp_CoinTemAnexos() {
//        return boolp_CoinTemAnexos.getValue();
//    }
//
//    public void setBoolp_CoinTemAnexos(Boolean boolp_CoinTemAnexos) {
//        this.boolp_CoinTemAnexos.setValue(boolp_CoinTemAnexos);
//    }

    public Boolean getBoolp_AutorizadoPeloGestorRemitente() {
        return boolp_AutorizadoPeloGestorRemitente.getValue();
    }

    public void setBoolp_AutorizadoPeloGestorRemitente(Boolean boolp_AutorizadoPeloGestorRemitente) {
        this.boolp_AutorizadoPeloGestorRemitente.setValue(boolp_AutorizadoPeloGestorRemitente);
    }

//    public String getStrp_dataCriacao() {
//        return strp_dataCriacao.getValue();
//    }
//
//    public void setStrp_dataCriacao(String strp_dataCriacao) {
//        this.strp_dataCriacao.setValue(strp_dataCriacao);
//    }

//    public Integer getIntp_idTabelaFonte() {
//        return intp_idTabelaFonte.getValue();
//    }
//
//    public void setIntp_idTabelaFonte(Integer intp_idTabelaFonte) {
//        this.intp_idTabelaFonte.setValue(intp_idTabelaFonte);
//    }
    
    
    
}
