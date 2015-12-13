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
    
    //Para tratar os registros do tipo "Encaminhar criamos 5 atributos "Genesis"
    private IntegerProperty intp_idCoinGenesis;
    private IntegerProperty intp_idUnorGenesis;
    private IntegerProperty intp_CoinNumeroGenesis;
    private StringProperty strp_CoinHistoricoAnexos;
    private StringProperty strp_UnorDescricaoGenesis;
    //-------------------------------------------------------------------------    
    
    //Assinatura
    private StringProperty strp_CoinAssinatura;
    
    
    //A variavel intp_idTabelaFonte foi criada para saber qual a fonte de dados
    //por tanto, não existe fisicamente mapeado para tabela nenhuma
    private IntegerProperty intp_idTabelaFonte;    //1- TB_COMUNICACAO_INTERNA, 2- TB_CI_DESTINATARIO    
    //------------------------------------------
    
    //variables a serem utilizadas na
    //persistencia da tabela TB_CI_DESTINATARIO
    private IntegerProperty intp_idCoinDestinatario;
    //private IntegerProperty intp_idCoin;
    private IntegerProperty intp_idUsuarioRemitente;
    //private StringProperty strp_UsuarioNomeCompleto;
    //private IntegerProperty intp_idUORemitente;
    //private StringProperty strp_DescricaoUORemitente;
    private IntegerProperty intp_idUODestinatario;
    private StringProperty strp_DescricaoUODestinatario;
    private IntegerProperty intp_idUOGestorDestinatario;
    private StringProperty strp_DescricaoUOGestorDestinatario;
    private BooleanProperty boolp_AutorizadoPeloGestor;
    private BooleanProperty boolp_ArquivadoPeloUODestinatario;
    private BooleanProperty boolp_ArquivadoPeloUOGestor;    
    //private StringProperty strp_Assunto;
    //private StringProperty strp_Conteudo;
    private BooleanProperty boolp_PendentePeloUODestinatario;
    private Date dataAutorizadoPeloGestorDestinatario;
    private BooleanProperty boolp_LidoPeloUODestinatario;
    //private Date dataCriacao;
    private IntegerProperty intp_idTipoEnvio;  //1-Enviado "Para; 2-Enviado "Com cópia"; 3-Enviado "Com cópia oculta" 
    //private IntegerProperty intp_idCoinNumero; //Número sequencial das CIs para cada UO
    private BooleanProperty boolp_ReadOnlyUODestinatario;
    //private BooleanProperty boolp_CoinTemAnexos;
    private BooleanProperty boolp_AutorizadoPeloGestorRemitente;
    
    //private StringProperty strp_dataCriacao;
    
    //A variavel intp_idTabelaFonte foi criada para saber qual a fonte de dados
    //por tanto, não existe fisicamente mapeado para tabela nenhuma
    //private IntegerProperty intp_idTabelaFonte;    //1- TB_COMUNICACAO_INTERNA, 2- TB_CI_DESTINATARIO    
    //------------------------------------------
    
    

    public TbCIPorAprovar() {
    }
    
    //Construtor utilizado para TB_COMUNICACAO_INTERNA
    public TbCIPorAprovar(Integer nIdCoin, String strAssunto, String strConteudo, 
            Integer nIdUsuario, String strUsuarioNomeCompleto, Integer nIdUO, 
            String strUO, Integer nIdUOGestor, boolean bAutorizado, 
            Integer nTipoCoin, String strApensamento, Integer nCoinNumero,
            boolean bArquivadoUO, boolean bArquivadoUOGestor, Date dataCriacao,
            String strDataCriacao, Date dataAutorizado, boolean bCoinReadOnly, 
            boolean bCoinTemAnexos, Integer nIdTabelaFonte,
            Integer intp_idCoinGenesis, Integer intp_idUnorGenesis, Integer intp_CoinNumeroGenesis, String strp_CoinHistoricoAnexos, String strp_UnorDescricaoGenesis,
            String strp_DescricaoUODestinatario, /*variavel String sempre vazio*/
            String strp_CoinAssinatura) {
        
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
        
        this.intp_idCoinGenesis = new SimpleIntegerProperty(intp_idCoinGenesis);
        this.intp_idUnorGenesis = new SimpleIntegerProperty(intp_idUnorGenesis);
        this.intp_CoinNumeroGenesis = new SimpleIntegerProperty(intp_CoinNumeroGenesis);
        this.strp_CoinHistoricoAnexos = new SimpleStringProperty(strp_CoinHistoricoAnexos) ;
        this.strp_UnorDescricaoGenesis = new SimpleStringProperty(strp_UnorDescricaoGenesis);
        
        this.strp_DescricaoUODestinatario = new SimpleStringProperty(strp_DescricaoUODestinatario); // sempre string vazio para compatibilidade com o TableView
        
        this.strp_CoinAssinatura = new SimpleStringProperty(strp_CoinAssinatura);
    }

    public TbCIPorAprovar(Integer nIdCoin, String strAssunto, String strConteudo, 
            Integer nIdUsuario, String strUsuarioNomeCompleto, Integer nIdUO, 
            String strUO, Integer nCoinNumero, Date dataCriacao, String strDataCriacao, 
            boolean bCoinTemAnexos, Integer nIdTabelaFonte) {
        intp_idCoin = new SimpleIntegerProperty(nIdCoin);
        strp_Assunto = new SimpleStringProperty(strAssunto);
        strp_Conteudo = new SimpleStringProperty(strConteudo);
        intp_idUsuario = new SimpleIntegerProperty(nIdUsuario);
        strp_UsuarioNomeCompleto = new SimpleStringProperty(strUsuarioNomeCompleto);
        intp_idUORemitente = new SimpleIntegerProperty(nIdUO);
        strp_DescricaoUORemitente = new SimpleStringProperty(strUO);
        intp_idCoinNumero = new SimpleIntegerProperty(nCoinNumero);
        this.dataCriacao = dataCriacao; 
        strp_dataCriacao = new SimpleStringProperty(strDataCriacao);
        boolp_CoinTemAnexos = new SimpleBooleanProperty(bCoinTemAnexos);
        intp_idTabelaFonte = new SimpleIntegerProperty(nIdTabelaFonte);
    }
    
    //Construtor utilizado para TB_CI_DESTINATARIO
    public TbCIPorAprovar(Integer intp_idCoinDestinatario, Integer intp_idCoin, Integer intp_idUsuarioRemitente, 
            String strp_UsuarioNomeCompleto, Integer intp_idUORemitente, String strp_DescricaoUORemitente, 
            Integer intp_idUODestinatario, String strp_DescricaoUODestinatario, Integer intp_idUOGestorDestinatario, 
            String strp_DescricaoUOGestorDestinatario, boolean boolp_AutorizadoPeloGestor, 
            boolean boolp_ArquivadoPeloUODestinatario, boolean boolp_ArquivadoPeloUOGestor, String strp_Assunto, 
            String strp_Conteudo, boolean boolp_PendentePeloUODestinatario, Date dataAutorizadoPeloGestorDestinatario, 
            boolean boolp_LidoPeloUODestinatario, Date dataCriacao, Integer intp_idTipoEnvio, Integer intp_idCoinNumero, 
            boolean boolp_ReadOnlyUODestinatario, boolean boolp_CoinTemAnexos, boolean boolp_AutorizadoPeloGestorRemitente, 
            String strp_dataCriacao, Integer intp_idTabelaFonte, 
            Integer intp_idCoinGenesis, Integer intp_idUnorGenesis, Integer intp_CoinNumeroGenesis, String strp_CoinHistoricoAnexos, String strp_UnorDescricaoGenesis,
            Integer nTipoCoin, String strp_CoinAssinatura ) {
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
        
        this.intp_idCoinGenesis = new SimpleIntegerProperty(intp_idCoinGenesis);
        this.intp_idUnorGenesis = new SimpleIntegerProperty(intp_idUnorGenesis);
        this.intp_CoinNumeroGenesis = new SimpleIntegerProperty(intp_CoinNumeroGenesis);
        this.strp_CoinHistoricoAnexos = new SimpleStringProperty(strp_CoinHistoricoAnexos) ;
        this.strp_UnorDescricaoGenesis = new SimpleStringProperty(strp_UnorDescricaoGenesis);
        
        intp_idTipoCoin = new SimpleIntegerProperty(nTipoCoin);
        
        this.strp_CoinAssinatura = new SimpleStringProperty(strp_CoinAssinatura);
    }
    
//    //Metodos para TableView
//    public StringProperty strp_UsuarioNomeCompleto(){
//        return strp_UsuarioNomeCompleto;
//    }
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

    public Integer getIntp_idUOGestor() {
        return intp_idUOGestor.getValue();
    }

    public void setIntp_idUOGestor(Integer intp_idUOGestor) {
        this.intp_idUOGestor.setValue(intp_idUOGestor);
    }

    public Boolean getBoolp_Autorizado() {
        return boolp_Autorizado.getValue();
    }

    public void setBoolp_Autorizado(Boolean boolp_Autorizado) {
        this.boolp_Autorizado.setValue(boolp_Autorizado);
    }

    public Integer getIntp_idTipoCoin() {
        return intp_idTipoCoin.getValue();
    }

    public void setIntp_idTipoCoin(Integer intp_idTipoCoin) {
        this.intp_idTipoCoin.setValue(intp_idTipoCoin);
    }

    public String getStrp_Apensamento() {
        return strp_Apensamento.getValue();
    }

    public void setStrp_Apensamento(String strp_Apensamento) {
        this.strp_Apensamento.setValue(strp_Apensamento);
    }

    public Integer getIntp_idCoinNumero() {
        return intp_idCoinNumero.getValue();
    }

    public void setIntp_idCoinNumero(Integer intp_idCoinNumero) {
        this.intp_idCoinNumero.setValue(intp_idCoinNumero);
    }

    public Boolean getBoolp_ArquivadoUORemitente() {
        return boolp_ArquivadoUORemitente.getValue();
    }

    public void setBoolp_ArquivadoUORemitente(Boolean boolp_ArquivadoUORemitente) {
        this.boolp_ArquivadoUORemitente.setValue(boolp_ArquivadoUORemitente);
    }

    public Boolean getBoolp_ArquivadoUOGestor() {
        return boolp_ArquivadoUOGestor.getValue();
    }

    public void setBoolp_ArquivadoUOGestor(Boolean boolp_ArquivadoUOGestor) {
        this.boolp_ArquivadoUOGestor.setValue(boolp_ArquivadoUOGestor);
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

    public Boolean getBoolp_CoinReadOnly() {
        return boolp_CoinReadOnly.getValue();
    }

    public void setBoolp_CoinReadOnly(Boolean boolp_CoinReadOnly) {
        this.boolp_CoinReadOnly.setValue(boolp_CoinReadOnly);
    }

    public Boolean getBoolp_CoinTemAnexos() {
        return boolp_CoinTemAnexos.getValue();
    }

    public void setBoolp_CoinTemAnexos(Boolean boolp_CoinTemAnexos) {
        this.boolp_CoinTemAnexos.setValue(boolp_CoinTemAnexos);
    }

    public Integer getIntp_idTabelaFonte() {
        return intp_idTabelaFonte.getValue();
    }

    public void setIntp_idTabelaFonte(Integer intp_idTabelaFonte) {
        this.intp_idTabelaFonte.setValue(intp_idTabelaFonte);
    }
    
    
    //----------Getters e Setters para TB_CI_DESTINATARIO
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
    
    public Integer getIntp_idCoinGenesis() {
        return intp_idCoinGenesis.getValue();
    }

    public void setIntp_idCoinGenesis(Integer intp_idCoinGenesis) {
        this.intp_idCoinGenesis.setValue(intp_idCoinGenesis);
    }

    public Integer getIntp_idUnorGenesis() {
        return intp_idUnorGenesis.getValue();
    }

    public void setIntp_idUnorGenesis(Integer intp_idUnorGenesis) {
        this.intp_idUnorGenesis.setValue(intp_idUnorGenesis);
    }

    public Integer getIntp_CoinNumeroGenesis() {
        return intp_CoinNumeroGenesis.getValue();
    }

    public void setIntp_CoinNumeroGenesis(Integer intp_CoinNumeroGenesis) {
        this.intp_CoinNumeroGenesis.setValue(intp_CoinNumeroGenesis);
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

    public String getStrp_CoinHistoricoAnexos() {
        return strp_CoinHistoricoAnexos.getValue();
    }

    public void setStrp_CoinHistoricoAnexos(String strp_CoinHistoricoAnexos) {
        this.strp_CoinHistoricoAnexos.setValue(strp_CoinHistoricoAnexos);
    }

    public String getStrp_UnorDescricaoGenesis() {
        return strp_UnorDescricaoGenesis.getValue();
    }

    public void setStrp_UnorDescricaoGenesis(String strp_UnorDescricaoGenesis) {
        this.strp_UnorDescricaoGenesis.setValue(strp_UnorDescricaoGenesis);
    }

    public String getStrp_CoinAssinatura() {
        return strp_CoinAssinatura.getValue();
    }

    public void setStrp_CoinAssinatura(String strp_CoinAssinatura) {
        this.strp_CoinAssinatura.setValue(strp_CoinAssinatura);
    }
    
    
}
