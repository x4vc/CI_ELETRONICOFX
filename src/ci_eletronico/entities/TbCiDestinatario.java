/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victorcmaf
 */
@Entity
@Table(name = "TB_CI_DESTINATARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbCiDestinatario.findAll", query = "SELECT t FROM TbCiDestinatario t"),
    @NamedQuery(name = "TbCiDestinatario.findByIdCoinDestinatario", query = "SELECT t FROM TbCiDestinatario t WHERE t.idCoinDestinatario = :idCoinDestinatario"),
    @NamedQuery(name = "TbCiDestinatario.findByIdUsuarioRemitente", query = "SELECT t FROM TbCiDestinatario t WHERE t.idUsuarioRemitente = :idUsuarioRemitente"),
    @NamedQuery(name = "TbCiDestinatario.findByUsuNomeCompletoRemitente", query = "SELECT t FROM TbCiDestinatario t WHERE t.usuNomeCompletoRemitente = :usuNomeCompletoRemitente"),
    @NamedQuery(name = "TbCiDestinatario.findByIdUoRemitente", query = "SELECT t FROM TbCiDestinatario t WHERE t.idUoRemitente = :idUoRemitente"),
    @NamedQuery(name = "TbCiDestinatario.findByInorDescricaoRemitente", query = "SELECT t FROM TbCiDestinatario t WHERE t.inorDescricaoRemitente = :inorDescricaoRemitente"),
    @NamedQuery(name = "TbCiDestinatario.findByIdUoDestinatario", query = "SELECT t FROM TbCiDestinatario t WHERE t.idUoDestinatario = :idUoDestinatario"),
    @NamedQuery(name = "TbCiDestinatario.findByUnorDescricaoDestinatario", query = "SELECT t FROM TbCiDestinatario t WHERE t.unorDescricaoDestinatario = :unorDescricaoDestinatario"),
    @NamedQuery(name = "TbCiDestinatario.findByIdUoGestorDestinatario", query = "SELECT t FROM TbCiDestinatario t WHERE t.idUoGestorDestinatario = :idUoGestorDestinatario"),
    @NamedQuery(name = "TbCiDestinatario.findByUnorDescricaoGestorDestinatario", query = "SELECT t FROM TbCiDestinatario t WHERE t.unorDescricaoGestorDestinatario = :unorDescricaoGestorDestinatario"),
    @NamedQuery(name = "TbCiDestinatario.findByCoinDestinatarioGestorAutorizado", query = "SELECT t FROM TbCiDestinatario t WHERE t.coinDestinatarioGestorAutorizado = :coinDestinatarioGestorAutorizado"),
    @NamedQuery(name = "TbCiDestinatario.findByCoinDestinatarioUoArquivado", query = "SELECT t FROM TbCiDestinatario t WHERE t.coinDestinatarioUoArquivado = :coinDestinatarioUoArquivado"),
    @NamedQuery(name = "TbCiDestinatario.findByCoinDestinatarioUoGestorArquivado", query = "SELECT t FROM TbCiDestinatario t WHERE t.coinDestinatarioUoGestorArquivado = :coinDestinatarioUoGestorArquivado"),
    @NamedQuery(name = "TbCiDestinatario.findByCoinDestinatarioConteudo", query = "SELECT t FROM TbCiDestinatario t WHERE t.coinDestinatarioConteudo = :coinDestinatarioConteudo"),
    @NamedQuery(name = "TbCiDestinatario.findByCoinDestinatarioPendente", query = "SELECT t FROM TbCiDestinatario t WHERE t.coinDestinatarioPendente = :coinDestinatarioPendente"),
    @NamedQuery(name = "TbCiDestinatario.findByCoinDestinatarioGestorDataAutorizado", query = "SELECT t FROM TbCiDestinatario t WHERE t.coinDestinatarioGestorDataAutorizado = :coinDestinatarioGestorDataAutorizado"),
    @NamedQuery(name = "TbCiDestinatario.findByCoinDestinatarioLido", query = "SELECT t FROM TbCiDestinatario t WHERE t.coinDestinatarioLido = :coinDestinatarioLido"),
    @NamedQuery(name = "TbCiDestinatario.findByCoinDestinatarioDataCriacao", query = "SELECT t FROM TbCiDestinatario t WHERE t.coinDestinatarioDataCriacao = :coinDestinatarioDataCriacao"),
    @NamedQuery(name = "TbCiDestinatario.findByCoinDestinatarioNumero", query = "SELECT t FROM TbCiDestinatario t WHERE t.coinDestinatarioNumero = :coinDestinatarioNumero"),
    @NamedQuery(name = "TbCiDestinatario.findByCoinDestinatarioReadOnly", query = "SELECT t FROM TbCiDestinatario t WHERE t.coinDestinatarioReadOnly = :coinDestinatarioReadOnly")})
public class TbCiDestinatario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_COIN_DESTINATARIO")
    private Integer idCoinDestinatario;
    @Basic(optional = false)
    @Column(name = "ID_USUARIO_REMITENTE")
    private int idUsuarioRemitente;
    @Basic(optional = false)
    @Column(name = "USU_NOME_COMPLETO_REMITENTE")
    private String usuNomeCompletoRemitente;
    @Basic(optional = false)
    @Column(name = "ID_UO_REMITENTE")
    private int idUoRemitente;
    @Basic(optional = false)
    @Column(name = "INOR_DESCRICAO_REMITENTE")
    private String inorDescricaoRemitente;
    @Basic(optional = false)
    @Column(name = "ID_UO_DESTINATARIO")
    private int idUoDestinatario;
    @Basic(optional = false)
    @Column(name = "UNOR_DESCRICAO_DESTINATARIO")
    private String unorDescricaoDestinatario;
    @Basic(optional = false)
    @Column(name = "ID_UO_GESTOR_DESTINATARIO")
    private int idUoGestorDestinatario;
    @Basic(optional = false)
    @Column(name = "UNOR_DESCRICAO_GESTOR_DESTINATARIO")
    private String unorDescricaoGestorDestinatario;
    @Basic(optional = false)
    @Column(name = "COIN_DESTINATARIO_GESTOR_AUTORIZADO")
    private boolean coinDestinatarioGestorAutorizado;
    @Basic(optional = false)
    @Column(name = "COIN_DESTINATARIO_UO_ARQUIVADO")
    private boolean coinDestinatarioUoArquivado;
    @Basic(optional = false)
    @Column(name = "COIN_DESTINATARIO_UO_GESTOR_ARQUIVADO")
    private boolean coinDestinatarioUoGestorArquivado;
    @Basic(optional = false)
    @Lob
    @Column(name = "COIN_DESTINATARIO_ASSUNTO")
    private byte[] coinDestinatarioAssunto;
    @Basic(optional = false)
    @Column(name = "COIN_DESTINATARIO_CONTEUDO")
    private String coinDestinatarioConteudo;
    @Basic(optional = false)
    @Column(name = "COIN_DESTINATARIO_PENDENTE")
    private boolean coinDestinatarioPendente;
    @Column(name = "COIN_DESTINATARIO_GESTOR_DATA_AUTORIZADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date coinDestinatarioGestorDataAutorizado;
    @Column(name = "COIN_DESTINATARIO_LIDO")
    private Boolean coinDestinatarioLido;
    @Basic(optional = false)
    @Column(name = "COIN_DESTINATARIO_DATA_CRIACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date coinDestinatarioDataCriacao;
    @Column(name = "COIN_DESTINATARIO_NUMERO")
    private Integer coinDestinatarioNumero;
    @Basic(optional = false)
    @Column(name = "COIN_DESTINATARIO_READ_ONLY")
    private boolean coinDestinatarioReadOnly;
    @JoinColumn(name = "ID_COIN", referencedColumnName = "ID_COIN")
    @ManyToOne(optional = false)
    private TbComunicacaoInterna idCoin;
    @JoinColumn(name = "ID_TIPO_ENVIO", referencedColumnName = "ID_TIPO_ENVIO")
    @ManyToOne(optional = false)
    private TbTipoEnvio idTipoEnvio;

    public TbCiDestinatario() {
    }

    public TbCiDestinatario(Integer idCoinDestinatario) {
        this.idCoinDestinatario = idCoinDestinatario;
    }

    public TbCiDestinatario(Integer idCoinDestinatario, int idUsuarioRemitente, String usuNomeCompletoRemitente, int idUoRemitente, String inorDescricaoRemitente, int idUoDestinatario, String unorDescricaoDestinatario, int idUoGestorDestinatario, String unorDescricaoGestorDestinatario, boolean coinDestinatarioGestorAutorizado, boolean coinDestinatarioUoArquivado, boolean coinDestinatarioUoGestorArquivado, byte[] coinDestinatarioAssunto, String coinDestinatarioConteudo, boolean coinDestinatarioPendente, Date coinDestinatarioDataCriacao, boolean coinDestinatarioReadOnly) {
        this.idCoinDestinatario = idCoinDestinatario;
        this.idUsuarioRemitente = idUsuarioRemitente;
        this.usuNomeCompletoRemitente = usuNomeCompletoRemitente;
        this.idUoRemitente = idUoRemitente;
        this.inorDescricaoRemitente = inorDescricaoRemitente;
        this.idUoDestinatario = idUoDestinatario;
        this.unorDescricaoDestinatario = unorDescricaoDestinatario;
        this.idUoGestorDestinatario = idUoGestorDestinatario;
        this.unorDescricaoGestorDestinatario = unorDescricaoGestorDestinatario;
        this.coinDestinatarioGestorAutorizado = coinDestinatarioGestorAutorizado;
        this.coinDestinatarioUoArquivado = coinDestinatarioUoArquivado;
        this.coinDestinatarioUoGestorArquivado = coinDestinatarioUoGestorArquivado;
        this.coinDestinatarioAssunto = coinDestinatarioAssunto;
        this.coinDestinatarioConteudo = coinDestinatarioConteudo;
        this.coinDestinatarioPendente = coinDestinatarioPendente;
        this.coinDestinatarioDataCriacao = coinDestinatarioDataCriacao;
        this.coinDestinatarioReadOnly = coinDestinatarioReadOnly;
    }

    public Integer getIdCoinDestinatario() {
        return idCoinDestinatario;
    }

    public void setIdCoinDestinatario(Integer idCoinDestinatario) {
        this.idCoinDestinatario = idCoinDestinatario;
    }

    public int getIdUsuarioRemitente() {
        return idUsuarioRemitente;
    }

    public void setIdUsuarioRemitente(int idUsuarioRemitente) {
        this.idUsuarioRemitente = idUsuarioRemitente;
    }

    public String getUsuNomeCompletoRemitente() {
        return usuNomeCompletoRemitente;
    }

    public void setUsuNomeCompletoRemitente(String usuNomeCompletoRemitente) {
        this.usuNomeCompletoRemitente = usuNomeCompletoRemitente;
    }

    public int getIdUoRemitente() {
        return idUoRemitente;
    }

    public void setIdUoRemitente(int idUoRemitente) {
        this.idUoRemitente = idUoRemitente;
    }

    public String getInorDescricaoRemitente() {
        return inorDescricaoRemitente;
    }

    public void setInorDescricaoRemitente(String inorDescricaoRemitente) {
        this.inorDescricaoRemitente = inorDescricaoRemitente;
    }

    public int getIdUoDestinatario() {
        return idUoDestinatario;
    }

    public void setIdUoDestinatario(int idUoDestinatario) {
        this.idUoDestinatario = idUoDestinatario;
    }

    public String getUnorDescricaoDestinatario() {
        return unorDescricaoDestinatario;
    }

    public void setUnorDescricaoDestinatario(String unorDescricaoDestinatario) {
        this.unorDescricaoDestinatario = unorDescricaoDestinatario;
    }

    public int getIdUoGestorDestinatario() {
        return idUoGestorDestinatario;
    }

    public void setIdUoGestorDestinatario(int idUoGestorDestinatario) {
        this.idUoGestorDestinatario = idUoGestorDestinatario;
    }

    public String getUnorDescricaoGestorDestinatario() {
        return unorDescricaoGestorDestinatario;
    }

    public void setUnorDescricaoGestorDestinatario(String unorDescricaoGestorDestinatario) {
        this.unorDescricaoGestorDestinatario = unorDescricaoGestorDestinatario;
    }

    public boolean getCoinDestinatarioGestorAutorizado() {
        return coinDestinatarioGestorAutorizado;
    }

    public void setCoinDestinatarioGestorAutorizado(boolean coinDestinatarioGestorAutorizado) {
        this.coinDestinatarioGestorAutorizado = coinDestinatarioGestorAutorizado;
    }

    public boolean getCoinDestinatarioUoArquivado() {
        return coinDestinatarioUoArquivado;
    }

    public void setCoinDestinatarioUoArquivado(boolean coinDestinatarioUoArquivado) {
        this.coinDestinatarioUoArquivado = coinDestinatarioUoArquivado;
    }

    public boolean getCoinDestinatarioUoGestorArquivado() {
        return coinDestinatarioUoGestorArquivado;
    }

    public void setCoinDestinatarioUoGestorArquivado(boolean coinDestinatarioUoGestorArquivado) {
        this.coinDestinatarioUoGestorArquivado = coinDestinatarioUoGestorArquivado;
    }

    public byte[] getCoinDestinatarioAssunto() {
        return coinDestinatarioAssunto;
    }

    public void setCoinDestinatarioAssunto(byte[] coinDestinatarioAssunto) {
        this.coinDestinatarioAssunto = coinDestinatarioAssunto;
    }

    public String getCoinDestinatarioConteudo() {
        return coinDestinatarioConteudo;
    }

    public void setCoinDestinatarioConteudo(String coinDestinatarioConteudo) {
        this.coinDestinatarioConteudo = coinDestinatarioConteudo;
    }

    public boolean getCoinDestinatarioPendente() {
        return coinDestinatarioPendente;
    }

    public void setCoinDestinatarioPendente(boolean coinDestinatarioPendente) {
        this.coinDestinatarioPendente = coinDestinatarioPendente;
    }

    public Date getCoinDestinatarioGestorDataAutorizado() {
        return coinDestinatarioGestorDataAutorizado;
    }

    public void setCoinDestinatarioGestorDataAutorizado(Date coinDestinatarioGestorDataAutorizado) {
        this.coinDestinatarioGestorDataAutorizado = coinDestinatarioGestorDataAutorizado;
    }

    public Boolean getCoinDestinatarioLido() {
        return coinDestinatarioLido;
    }

    public void setCoinDestinatarioLido(Boolean coinDestinatarioLido) {
        this.coinDestinatarioLido = coinDestinatarioLido;
    }

    public Date getCoinDestinatarioDataCriacao() {
        return coinDestinatarioDataCriacao;
    }

    public void setCoinDestinatarioDataCriacao(Date coinDestinatarioDataCriacao) {
        this.coinDestinatarioDataCriacao = coinDestinatarioDataCriacao;
    }

    public Integer getCoinDestinatarioNumero() {
        return coinDestinatarioNumero;
    }

    public void setCoinDestinatarioNumero(Integer coinDestinatarioNumero) {
        this.coinDestinatarioNumero = coinDestinatarioNumero;
    }

    public boolean getCoinDestinatarioReadOnly() {
        return coinDestinatarioReadOnly;
    }

    public void setCoinDestinatarioReadOnly(boolean coinDestinatarioReadOnly) {
        this.coinDestinatarioReadOnly = coinDestinatarioReadOnly;
    }

    public TbComunicacaoInterna getIdCoin() {
        return idCoin;
    }

    public void setIdCoin(TbComunicacaoInterna idCoin) {
        this.idCoin = idCoin;
    }

    public TbTipoEnvio getIdTipoEnvio() {
        return idTipoEnvio;
    }

    public void setIdTipoEnvio(TbTipoEnvio idTipoEnvio) {
        this.idTipoEnvio = idTipoEnvio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCoinDestinatario != null ? idCoinDestinatario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbCiDestinatario)) {
            return false;
        }
        TbCiDestinatario other = (TbCiDestinatario) object;
        if ((this.idCoinDestinatario == null && other.idCoinDestinatario != null) || (this.idCoinDestinatario != null && !this.idCoinDestinatario.equals(other.idCoinDestinatario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ci_eletronico.entities.TbCiDestinatario[ idCoinDestinatario=" + idCoinDestinatario + " ]";
    }
    
}
