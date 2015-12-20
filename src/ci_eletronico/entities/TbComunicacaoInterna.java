/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author victorcmaf
 */
@Entity
@Table(name = "TB_COMUNICACAO_INTERNA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbComunicacaoInterna.findAll", query = "SELECT t FROM TbComunicacaoInterna t"),
    @NamedQuery(name = "TbComunicacaoInterna.findByIdCoin", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.idCoin = :idCoin"),
    @NamedQuery(name = "TbComunicacaoInterna.findByCoinAssunto", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.coinAssunto = :coinAssunto"),
    @NamedQuery(name = "TbComunicacaoInterna.findByCoinConteudo", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.coinConteudo = :coinConteudo"),
    @NamedQuery(name = "TbComunicacaoInterna.findByIdUsuario", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.idUsuario = :idUsuario"),
    @NamedQuery(name = "TbComunicacaoInterna.findByIdUnidadeOrganizacional", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.idUnidadeOrganizacional = :idUnidadeOrganizacional"),
    @NamedQuery(name = "TbComunicacaoInterna.findByIdUoGestor", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.idUoGestor = :idUoGestor"),
    //@NamedQuery(name = "TbComunicacaoInterna.findPorAprovarByIdUoGestor", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.idUoGestor = :idUoGestor AND t.coinAutorizado = 0 ORDER BY t.coinDataCriacao DESC"),    
    @NamedQuery(name = "TbComunicacaoInterna.findPorAprovarByIdUoGestor", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.idUoGestor = :idUoGestor AND t.coinAutorizado = 0 AND t.coinUoGestorArquivado = 0 ORDER BY t.coinDataCriacao DESC"),    
    @NamedQuery(name = "TbComunicacaoInterna.findPorAprovarByIdUoGestorPerfil2", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.idUoGestor = :idUoGestor AND t.coinAutorizado = 0 AND t.coinUoGestorArquivado = 0 AND t.idTipoCoin IN :idTipoCoin ORDER BY t.coinDataCriacao DESC"),    
    @NamedQuery(name = "TbComunicacaoInterna.findByCIsEnviadas", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.idUnidadeOrganizacional = :idUnidadeOrganizacional AND t.coinUoArquivado = 0 ORDER BY t.coinDataCriacao DESC"),
    @NamedQuery(name = "TbComunicacaoInterna.findByCIsEnviadasPerfil2", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.idUnidadeOrganizacional = :idUnidadeOrganizacional AND t.coinUoArquivado = 0 AND t.idTipoCoin IN :idTipoCoin ORDER BY t.coinDataCriacao DESC"),
    @NamedQuery(name = "TbComunicacaoInterna.findByCoinAutorizado", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.coinAutorizado = :coinAutorizado"),
    @NamedQuery(name = "TbComunicacaoInterna.findByCoinApensamento", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.coinApensamento = :coinApensamento"),
    @NamedQuery(name = "TbComunicacaoInterna.findByCoinNumero", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.coinNumero = :coinNumero"),
    @NamedQuery(name = "TbComunicacaoInterna.findByCoinUoArquivado", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.coinUoArquivado = :coinUoArquivado"),
    @NamedQuery(name = "TbComunicacaoInterna.findByCoinUoGestorArquivado", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.coinUoGestorArquivado = :coinUoGestorArquivado"),
    @NamedQuery(name = "TbComunicacaoInterna.findByCoinDataCriacao", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.coinDataCriacao = :coinDataCriacao"),
    @NamedQuery(name = "TbComunicacaoInterna.findByCoinDataAutorizado", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.coinDataAutorizado = :coinDataAutorizado"),
    @NamedQuery(name = "TbComunicacaoInterna.findByCoinReadOnly", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.coinReadOnly = :coinReadOnly"),
    @NamedQuery(name = "TbComunicacaoInterna.findByUOArquivado", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.idUnidadeOrganizacional = :idUnidadeOrganizacional AND t.coinUoArquivado = 1 OR t.coinUoGestorArquivado = 1 ORDER BY t.coinDataCriacao DESC "),
    @NamedQuery(name = "TbComunicacaoInterna.findByUOArquivadoPerfil2", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.idUnidadeOrganizacional = :idUnidadeOrganizacional AND t.coinUoArquivado = 1 OR t.coinUoGestorArquivado = 1 AND t.idTipoCoin IN :idTipoCoin ORDER BY t.coinDataCriacao DESC "),
    @NamedQuery(name = "TbComunicacaoInterna.findByCoinTemAnexos", query = "SELECT t FROM TbComunicacaoInterna t WHERE t.coinTemAnexos = :coinTemAnexos")})
public class TbComunicacaoInterna implements Serializable {
    @Basic(optional = false)
    @Column(name = "COIN_ASSINATURA")
    private String coinAssinatura;
    @Basic(optional = false)
    @Column(name = "UNOR_DESCRICAO_GENESIS")
    private String unorDescricaoGenesis;
    @Column(name = "COIN_HISTORICO_ANEXOS")
    private String coinHistoricoAnexos;
    @Basic(optional = false)
    @Column(name = "COIN_NUMERO_GENESIS")
    private int coinNumeroGenesis;
    @Basic(optional = false)
    @Column(name = "ID_COIN_GENESIS")
    private int idCoinGenesis;
    @Basic(optional = false)
    @Column(name = "ID_UNOR_GENESIS")
    private int idUnorGenesis;
    @Basic(optional = false)
    @Column(name = "USU_NOME_COMPLETO")
    private String usuNomeCompleto;
    @Basic(optional = false)
    @Column(name = "UNOR_DESCRICAO")
    private String unorDescricao;    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_COIN")
    private Integer idCoin;
    @Basic(optional = false)
    @Column(name = "COIN_ASSUNTO")
    private String coinAssunto;
    @Basic(optional = false)
    @Column(name = "COIN_CONTEUDO")
    private String coinConteudo;
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private int idUsuario;
    @Basic(optional = false)
    @Column(name = "ID_UNIDADE_ORGANIZACIONAL")
    private int idUnidadeOrganizacional;
    @Basic(optional = false)
    @Column(name = "ID_UO_GESTOR")
    private int idUoGestor;
    @Basic(optional = false)
    @Column(name = "COIN_AUTORIZADO")
    private boolean coinAutorizado;
    @Column(name = "COIN_APENSAMENTO")
    private String coinApensamento;
    @Column(name = "COIN_NUMERO")
    private Integer coinNumero;
    @Basic(optional = false)
    @Column(name = "COIN_UO_ARQUIVADO")
    private boolean coinUoArquivado;
    @Basic(optional = false)
    @Column(name = "COIN_UO_GESTOR_ARQUIVADO")
    private boolean coinUoGestorArquivado;
    @Basic(optional = false)
    @Column(name = "COIN_DATA_CRIACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date coinDataCriacao;
    @Column(name = "COIN_DATA_AUTORIZADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date coinDataAutorizado;
    @Basic(optional = false)
    @Column(name = "COIN_READ_ONLY")
    private boolean coinReadOnly;
    @Basic(optional = false)
    @Column(name = "COIN_TEM_ANEXOS")
    private boolean coinTemAnexos;
    @JoinColumn(name = "ID_TIPO_COIN", referencedColumnName = "ID_TIPO_COIN")
    @ManyToOne(optional = false)
    private TbTipoComunicacoInterna idTipoCoin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCoin")
    private Collection<TbCiDestinatario> tbCiDestinatarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCoin")
    private Collection<TbAnexo> tbAnexoCollection;

    public TbComunicacaoInterna() {
    }

    public TbComunicacaoInterna(Integer idCoin) {
        this.idCoin = idCoin;
    }

    public TbComunicacaoInterna(Integer idCoin, String coinAssunto, String coinConteudo, int idUsuario, int idUnidadeOrganizacional, int idUoGestor, boolean coinAutorizado, boolean coinUoArquivado, boolean coinUoGestorArquivado, Date coinDataCriacao, boolean coinReadOnly, boolean coinTemAnexos) {
        this.idCoin = idCoin;
        this.coinAssunto = coinAssunto;
        this.coinConteudo = coinConteudo;
        this.idUsuario = idUsuario;
        this.idUnidadeOrganizacional = idUnidadeOrganizacional;
        this.idUoGestor = idUoGestor;
        this.coinAutorizado = coinAutorizado;
        this.coinUoArquivado = coinUoArquivado;
        this.coinUoGestorArquivado = coinUoGestorArquivado;
        this.coinDataCriacao = coinDataCriacao;
        this.coinReadOnly = coinReadOnly;
        this.coinTemAnexos = coinTemAnexos;
    }

    public Integer getIdCoin() {
        return idCoin;
    }

    public void setIdCoin(Integer idCoin) {
        this.idCoin = idCoin;
    }

    public String getCoinAssunto() {
        return coinAssunto;
    }

    public void setCoinAssunto(String coinAssunto) {
        this.coinAssunto = coinAssunto;
    }

    public String getCoinConteudo() {
        return coinConteudo;
    }

    public void setCoinConteudo(String coinConteudo) {
        this.coinConteudo = coinConteudo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdUnidadeOrganizacional() {
        return idUnidadeOrganizacional;
    }

    public void setIdUnidadeOrganizacional(int idUnidadeOrganizacional) {
        this.idUnidadeOrganizacional = idUnidadeOrganizacional;
    }

    public int getIdUoGestor() {
        return idUoGestor;
    }

    public void setIdUoGestor(int idUoGestor) {
        this.idUoGestor = idUoGestor;
    }

    public boolean getCoinAutorizado() {
        return coinAutorizado;
    }

    public void setCoinAutorizado(boolean coinAutorizado) {
        this.coinAutorizado = coinAutorizado;
    }

    public String getCoinApensamento() {
        return coinApensamento;
    }

    public void setCoinApensamento(String coinApensamento) {
        this.coinApensamento = coinApensamento;
    }

    public Integer getCoinNumero() {
        return coinNumero;
    }

    public void setCoinNumero(Integer coinNumero) {
        this.coinNumero = coinNumero;
    }

    public boolean getCoinUoArquivado() {
        return coinUoArquivado;
    }

    public void setCoinUoArquivado(boolean coinUoArquivado) {
        this.coinUoArquivado = coinUoArquivado;
    }

    public boolean getCoinUoGestorArquivado() {
        return coinUoGestorArquivado;
    }

    public void setCoinUoGestorArquivado(boolean coinUoGestorArquivado) {
        this.coinUoGestorArquivado = coinUoGestorArquivado;
    }

    public Date getCoinDataCriacao() {
        return coinDataCriacao;
    }

    public void setCoinDataCriacao(Date coinDataCriacao) {
        this.coinDataCriacao = coinDataCriacao;
    }

    public Date getCoinDataAutorizado() {
        return coinDataAutorizado;
    }

    public void setCoinDataAutorizado(Date coinDataAutorizado) {
        this.coinDataAutorizado = coinDataAutorizado;
    }

    public boolean getCoinReadOnly() {
        return coinReadOnly;
    }

    public void setCoinReadOnly(boolean coinReadOnly) {
        this.coinReadOnly = coinReadOnly;
    }

    public boolean getCoinTemAnexos() {
        return coinTemAnexos;
    }

    public void setCoinTemAnexos(boolean coinTemAnexos) {
        this.coinTemAnexos = coinTemAnexos;
    }

    public TbTipoComunicacoInterna getIdTipoCoin() {
        return idTipoCoin;
    }

    public void setIdTipoCoin(TbTipoComunicacoInterna idTipoCoin) {
        this.idTipoCoin = idTipoCoin;
    }

    @XmlTransient
    public Collection<TbCiDestinatario> getTbCiDestinatarioCollection() {
        return tbCiDestinatarioCollection;
    }

    public void setTbCiDestinatarioCollection(Collection<TbCiDestinatario> tbCiDestinatarioCollection) {
        this.tbCiDestinatarioCollection = tbCiDestinatarioCollection;
    }

    @XmlTransient
    public Collection<TbAnexo> getTbAnexoCollection() {
        return tbAnexoCollection;
    }

    public void setTbAnexoCollection(Collection<TbAnexo> tbAnexoCollection) {
        this.tbAnexoCollection = tbAnexoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCoin != null ? idCoin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbComunicacaoInterna)) {
            return false;
        }
        TbComunicacaoInterna other = (TbComunicacaoInterna) object;
        if ((this.idCoin == null && other.idCoin != null) || (this.idCoin != null && !this.idCoin.equals(other.idCoin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ci_eletronico.entities.TbComunicacaoInterna[ idCoin=" + idCoin + " ]";
    }

    public String getUsuNomeCompleto() {
        return usuNomeCompleto;
    }

    public void setUsuNomeCompleto(String usuNomeCompleto) {
        this.usuNomeCompleto = usuNomeCompleto;
    }

    public String getUnorDescricao() {
        return unorDescricao;
    }

    public void setUnorDescricao(String unorDescricao) {
        this.unorDescricao = unorDescricao;
    }

    public int getIdCoinGenesis() {
        return idCoinGenesis;
    }

    public void setIdCoinGenesis(int idCoinGenesis) {
        this.idCoinGenesis = idCoinGenesis;
    }

    public int getIdUnorGenesis() {
        return idUnorGenesis;
    }

    public void setIdUnorGenesis(int idUnorGenesis) {
        this.idUnorGenesis = idUnorGenesis;
    }

    public int getCoinNumeroGenesis() {
        return coinNumeroGenesis;
    }

    public void setCoinNumeroGenesis(int coinNumeroGenesis) {
        this.coinNumeroGenesis = coinNumeroGenesis;
    }

    public String getCoinHistoricoAnexos() {
        return coinHistoricoAnexos;
    }

    public void setCoinHistoricoAnexos(String coinHistoricoAnexos) {
        this.coinHistoricoAnexos = coinHistoricoAnexos;
    }

    public String getUnorDescricaoGenesis() {
        return unorDescricaoGenesis;
    }

    public void setUnorDescricaoGenesis(String unorDescricaoGenesis) {
        this.unorDescricaoGenesis = unorDescricaoGenesis;
    }

    public String getCoinAssinatura() {
        return coinAssinatura;
    }

    public void setCoinAssinatura(String coinAssinatura) {
        this.coinAssinatura = coinAssinatura;
    }

    
    
}
