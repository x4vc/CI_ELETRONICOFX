/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.entities;

import java.util.Calendar;
import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author victorcmaf
 */
public class TbCIPorAprovar {
    //variables a serem utilizadas na
    //persistencia da tabela TB_COMUNICACAO_INTERNA
    private IntegerProperty intp_idCoin;
    private StringProperty strp_Assunto;
    private StringProperty strp_Conteudo;
    private IntegerProperty intp_idUsuario;
    private StringProperty strp_UsuarioNomeCompleto;
    private IntegerProperty intp_idUORemitente;
    private StringProperty strp_DescricaoUORemitente;
    private IntegerProperty intp_idUOGestor;
    private BooleanProperty boolp_Autorizado;
    private IntegerProperty intp_idTipoCoin;    //1-CI NORMAL, 2-CI CIRCULAR, 3-CI CONFIDENCIAL, 4-CI ENCAMINHADO
    private StringProperty strp_Apensamento;
    private IntegerProperty intp_idCoinNumero; //Número sequencial das CIs para cada UO
    private BooleanProperty boolp_ArquivadoUORemitente;
    private BooleanProperty boolp_ArquivadoUOGestor;
    private Date dataCriacao;
    private StringProperty strp_dataCriacao;
    private Date dataAutorizado;
    
    private BooleanProperty boolp_CoinReadOnly;
    private BooleanProperty boolp_CoinTemAnexos;    
    
    //A variavel intp_idTabelaFonte foi criada para saber qual a fonte de dados
    //por tanto, não existe fisicamente mapeado para tabela nenhuma
    private IntegerProperty intp_idTabelaFonte;    //1- TB_COMUNICACAO_INTERNA, 2- TB_CI_DESTINATARIO    
    //------------------------------------------

    public TbCIPorAprovar() {
    }
    
    

    public TbCIPorAprovar(Integer nIdCoin, String strAssunto, String strConteudo, 
            Integer nIdUsuario, String strUsuarioNomeCompleto, Integer nIdUO, 
            String strUO, Integer nIdUOGestor, boolean bAutorizado, 
            Integer nTipoCoin, String strApensamento, Integer nCoinNumero,
            boolean bArquivadoUO, boolean bArquivadoUOGestor, Date dataCriacao,
            String strDataCriacao, Date dataAutorizado, boolean bCoinReadOnly, 
            boolean bCoinTemAnexos, Integer nIdTabelaFonte) {
        
        intp_idCoin = new SimpleIntegerProperty(nIdCoin);
        strp_Assunto = new SimpleStringProperty(strAssunto);
        strp_Conteudo = new SimpleStringProperty(strConteudo);
        intp_idUsuario = new SimpleIntegerProperty(nIdUsuario);
        strp_UsuarioNomeCompleto = new SimpleStringProperty(strUsuarioNomeCompleto);
        intp_idUORemitente = new SimpleIntegerProperty(nIdUO);
        strp_DescricaoUORemitente = new SimpleStringProperty(strUO);
        intp_idUOGestor = new SimpleIntegerProperty(nIdUOGestor);
        boolp_Autorizado = new SimpleBooleanProperty(bAutorizado);
        intp_idTipoCoin = new SimpleIntegerProperty(nTipoCoin);
        strp_Apensamento = new SimpleStringProperty(strApensamento);
        intp_idCoinNumero = new SimpleIntegerProperty(nCoinNumero);
        boolp_ArquivadoUORemitente = new SimpleBooleanProperty(bArquivadoUO);
        boolp_ArquivadoUOGestor = new SimpleBooleanProperty(bArquivadoUOGestor);
        this.dataCriacao = dataCriacao;
        strp_dataCriacao = new SimpleStringProperty(strDataCriacao);
        this.dataAutorizado = dataAutorizado;
        boolp_CoinReadOnly = new SimpleBooleanProperty(bCoinReadOnly);
        boolp_CoinTemAnexos = new SimpleBooleanProperty(bCoinTemAnexos);
        intp_idTabelaFonte = new SimpleIntegerProperty(nIdTabelaFonte);
    }

    public TbCIPorAprovar(Integer nIdCoin, String strAssunto, String strConteudo, 
            Integer nIdUsuario, String strUsuarioNomeCompleto, Integer nIdUO, 
            String strUO, Date dataCriacao, String strDataCriacao,
            boolean bCoinTemAnexos, Integer nIdTabelaFonte) {
        intp_idCoin = new SimpleIntegerProperty(nIdCoin);
        strp_Assunto = new SimpleStringProperty(strAssunto);
        strp_Conteudo = new SimpleStringProperty(strConteudo);
        intp_idUsuario = new SimpleIntegerProperty(nIdUsuario);
        strp_UsuarioNomeCompleto = new SimpleStringProperty(strUsuarioNomeCompleto);
        intp_idUORemitente = new SimpleIntegerProperty(nIdUO);
        strp_DescricaoUORemitente = new SimpleStringProperty(strUO);
        this.dataCriacao = dataCriacao; 
        strp_dataCriacao = new SimpleStringProperty(strDataCriacao);
        boolp_CoinTemAnexos = new SimpleBooleanProperty(bCoinTemAnexos);
        intp_idTabelaFonte = new SimpleIntegerProperty(nIdTabelaFonte);
    }
    
    //Metodos para TableView
    public StringProperty strp_UsuarioNomeCompleto(){
        return strp_UsuarioNomeCompleto;
    }
    //----------FIM MEtodos para TableView ------
    
    
    //Setters and getters
    public String getStrp_dataCriacao() {
        return strp_dataCriacao.getValue();
    }

    //getters and setters
    public void setStrp_dataCriacao(String strp_dataCriacao) {
        this.strp_dataCriacao.set(strp_dataCriacao);
    }

    public Integer getIntp_idCoin() {
        return intp_idCoin.getValue();
    }

    public void setIntp_idCoin(Integer intp_idCoin) {
        this.intp_idCoin.setValue(intp_idCoin);
    }

    public String getStrp_Assunto() {
        return strp_Assunto.getValue();
    }

    public void setStrp_Assunto(String strp_Assunto) {
        this.strp_Assunto.set(strp_Assunto);
    }

    public String getStrp_Conteudo() {
        return strp_Conteudo.getValue();
    }

    public void setStrp_Conteudo(String strp_Conteudo) {
        this.strp_Conteudo.set(strp_Conteudo);
    }

    public Integer getIntp_idUsuario() {
        return intp_idUsuario.getValue();
    }

    public void setIntp_idUsuario(Integer intp_idUsuario) {
        this.intp_idUsuario.setValue(intp_idUsuario);
    }

    public String getStrp_UsuarioNomeCompleto() {
        return this.strp_UsuarioNomeCompleto.getValue();
    }
    public void setStrp_UsuarioNomeCompleto(String strp_UsuarioNomeCompleto) {
        this.strp_UsuarioNomeCompleto.set(strp_UsuarioNomeCompleto);
    }
//    public StringProperty getStrp_UsuarioNomeCompleto() {
//        return strp_UsuarioNomeCompleto;
//    }
//    public void setStrp_UsuarioNomeCompleto(StringProperty strp_UsuarioNomeCompleto) {
//        this.strp_UsuarioNomeCompleto = strp_UsuarioNomeCompleto;
//    }

    public Integer getIntp_idUORemitente() {
        return intp_idUORemitente.getValue();
    }

    public void setIntp_idUORemitente(Integer intp_idUORemitente) {
        this.intp_idUORemitente.setValue(intp_idUORemitente);
    }

    public String getStrp_DescricaoUORemitente() {
        return strp_DescricaoUORemitente.getValue();
    }

    public void setStrp_DescricaoUORemitente(String strp_DescricaoUORemitente) {
        this.strp_DescricaoUORemitente.set(strp_DescricaoUORemitente);
    }

    public IntegerProperty getIntp_idUOGestor() {
        return intp_idUOGestor;
    }

    public void setIntp_idUOGestor(IntegerProperty intp_idUOGestor) {
        this.intp_idUOGestor = intp_idUOGestor;
    }

    public BooleanProperty getBoolp_Autorizado() {
        return boolp_Autorizado;
    }

    public void setBoolp_Autorizado(BooleanProperty boolp_Autorizado) {
        this.boolp_Autorizado = boolp_Autorizado;
    }

    public IntegerProperty getIntp_idTipoCoin() {
        return intp_idTipoCoin;
    }

    public void setIntp_idTipoCoin(IntegerProperty intp_idTipoCoin) {
        this.intp_idTipoCoin = intp_idTipoCoin;
    }

    public StringProperty getStrp_Apensamento() {
        return strp_Apensamento;
    }

    public void setStrp_Apensamento(StringProperty strp_Apensamento) {
        this.strp_Apensamento = strp_Apensamento;
    }

    public IntegerProperty getIntp_idCoinNumero() {
        return intp_idCoinNumero;
    }

    public void setIntp_idCoinNumero(IntegerProperty intp_idCoinNumero) {
        this.intp_idCoinNumero = intp_idCoinNumero;
    }

    public BooleanProperty getBoolp_ArquivadoUORemitente() {
        return boolp_ArquivadoUORemitente;
    }

    public void setBoolp_ArquivadoUORemitente(BooleanProperty boolp_ArquivadoUORemitente) {
        this.boolp_ArquivadoUORemitente = boolp_ArquivadoUORemitente;
    }

    public BooleanProperty getBoolp_ArquivadoUOGestor() {
        return boolp_ArquivadoUOGestor;
    }

    public void setBoolp_ArquivadoUOGestor(BooleanProperty boolp_ArquivadoUOGestor) {
        this.boolp_ArquivadoUOGestor = boolp_ArquivadoUOGestor;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAutorizado() {
        return dataAutorizado;
    }

    public void setDataAutorizado(Date dataAutorizado) {
        this.dataAutorizado = dataAutorizado;
    }

    public BooleanProperty getBoolp_CoinReadOnly() {
        return boolp_CoinReadOnly;
    }

    public void setBoolp_CoinReadOnly(BooleanProperty boolp_CoinReadOnly) {
        this.boolp_CoinReadOnly = boolp_CoinReadOnly;
    }

    public BooleanProperty getBoolp_CoinTemAnexos() {
        return boolp_CoinTemAnexos;
    }

    public void setBoolp_CoinTemAnexos(BooleanProperty boolp_CoinTemAnexos) {
        this.boolp_CoinTemAnexos = boolp_CoinTemAnexos;
    }

    public IntegerProperty getIntp_idTabelaFonte() {
        return intp_idTabelaFonte;
    }

    public void setIntp_idTabelaFonte(IntegerProperty intp_idTabelaFonte) {
        this.intp_idTabelaFonte = intp_idTabelaFonte;
    }
    
    
}
