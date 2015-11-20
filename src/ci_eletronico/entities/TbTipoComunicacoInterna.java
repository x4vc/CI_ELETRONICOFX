/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author victorcmaf
 */
@Entity
@Table(name = "TB_TIPO_COMUNICACO_INTERNA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbTipoComunicacoInterna.findAll", query = "SELECT t FROM TbTipoComunicacoInterna t"),
    @NamedQuery(name = "TbTipoComunicacoInterna.findByIdTipoCoin", query = "SELECT t FROM TbTipoComunicacoInterna t WHERE t.idTipoCoin = :idTipoCoin"),
    @NamedQuery(name = "TbTipoComunicacoInterna.findByTiciDescricao", query = "SELECT t FROM TbTipoComunicacoInterna t WHERE t.ticiDescricao = :ticiDescricao"),
    @NamedQuery(name = "TbTipoComunicacoInterna.findByTiciAtivo", query = "SELECT t FROM TbTipoComunicacoInterna t WHERE t.ticiAtivo = :ticiAtivo")})
public class TbTipoComunicacoInterna implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TIPO_COIN")
    private Integer idTipoCoin;
    @Basic(optional = false)
    @Column(name = "TICI_DESCRICAO")
    private String ticiDescricao;
    @Basic(optional = false)
    @Column(name = "TICI_ATIVO")
    private boolean ticiAtivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCoin")
    private Collection<TbComunicacaoInterna> tbComunicacaoInternaCollection;

    public TbTipoComunicacoInterna() {
    }

    public TbTipoComunicacoInterna(Integer idTipoCoin) {
        this.idTipoCoin = idTipoCoin;
    }

    public TbTipoComunicacoInterna(Integer idTipoCoin, String ticiDescricao, boolean ticiAtivo) {
        this.idTipoCoin = idTipoCoin;
        this.ticiDescricao = ticiDescricao;
        this.ticiAtivo = ticiAtivo;
    }

    public Integer getIdTipoCoin() {
        return idTipoCoin;
    }

    public void setIdTipoCoin(Integer idTipoCoin) {
        this.idTipoCoin = idTipoCoin;
    }

    public String getTiciDescricao() {
        return ticiDescricao;
    }

    public void setTiciDescricao(String ticiDescricao) {
        this.ticiDescricao = ticiDescricao;
    }

    public boolean getTiciAtivo() {
        return ticiAtivo;
    }

    public void setTiciAtivo(boolean ticiAtivo) {
        this.ticiAtivo = ticiAtivo;
    }

    @XmlTransient
    public Collection<TbComunicacaoInterna> getTbComunicacaoInternaCollection() {
        return tbComunicacaoInternaCollection;
    }

    public void setTbComunicacaoInternaCollection(Collection<TbComunicacaoInterna> tbComunicacaoInternaCollection) {
        this.tbComunicacaoInternaCollection = tbComunicacaoInternaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoCoin != null ? idTipoCoin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbTipoComunicacoInterna)) {
            return false;
        }
        TbTipoComunicacoInterna other = (TbTipoComunicacoInterna) object;
        if ((this.idTipoCoin == null && other.idTipoCoin != null) || (this.idTipoCoin != null && !this.idTipoCoin.equals(other.idTipoCoin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ci_eletronico.entities.TbTipoComunicacoInterna[ idTipoCoin=" + idTipoCoin + " ]";
    }
    
}
