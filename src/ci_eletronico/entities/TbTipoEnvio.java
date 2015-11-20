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
@Table(name = "TB_TIPO_ENVIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbTipoEnvio.findAll", query = "SELECT t FROM TbTipoEnvio t"),
    @NamedQuery(name = "TbTipoEnvio.findByIdTipoEnvio", query = "SELECT t FROM TbTipoEnvio t WHERE t.idTipoEnvio = :idTipoEnvio"),
    @NamedQuery(name = "TbTipoEnvio.findByTienDescricao", query = "SELECT t FROM TbTipoEnvio t WHERE t.tienDescricao = :tienDescricao"),
    @NamedQuery(name = "TbTipoEnvio.findByTienAtivo", query = "SELECT t FROM TbTipoEnvio t WHERE t.tienAtivo = :tienAtivo")})
public class TbTipoEnvio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TIPO_ENVIO")
    private Integer idTipoEnvio;
    @Basic(optional = false)
    @Column(name = "TIEN_DESCRICAO")
    private String tienDescricao;
    @Basic(optional = false)
    @Column(name = "TIEN_ATIVO")
    private boolean tienAtivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoEnvio")
    private Collection<TbCiDestinatario> tbCiDestinatarioCollection;

    public TbTipoEnvio() {
    }

    public TbTipoEnvio(Integer idTipoEnvio) {
        this.idTipoEnvio = idTipoEnvio;
    }

    public TbTipoEnvio(Integer idTipoEnvio, String tienDescricao, boolean tienAtivo) {
        this.idTipoEnvio = idTipoEnvio;
        this.tienDescricao = tienDescricao;
        this.tienAtivo = tienAtivo;
    }

    public Integer getIdTipoEnvio() {
        return idTipoEnvio;
    }

    public void setIdTipoEnvio(Integer idTipoEnvio) {
        this.idTipoEnvio = idTipoEnvio;
    }

    public String getTienDescricao() {
        return tienDescricao;
    }

    public void setTienDescricao(String tienDescricao) {
        this.tienDescricao = tienDescricao;
    }

    public boolean getTienAtivo() {
        return tienAtivo;
    }

    public void setTienAtivo(boolean tienAtivo) {
        this.tienAtivo = tienAtivo;
    }

    @XmlTransient
    public Collection<TbCiDestinatario> getTbCiDestinatarioCollection() {
        return tbCiDestinatarioCollection;
    }

    public void setTbCiDestinatarioCollection(Collection<TbCiDestinatario> tbCiDestinatarioCollection) {
        this.tbCiDestinatarioCollection = tbCiDestinatarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEnvio != null ? idTipoEnvio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbTipoEnvio)) {
            return false;
        }
        TbTipoEnvio other = (TbTipoEnvio) object;
        if ((this.idTipoEnvio == null && other.idTipoEnvio != null) || (this.idTipoEnvio != null && !this.idTipoEnvio.equals(other.idTipoEnvio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ci_eletronico.entities.TbTipoEnvio[ idTipoEnvio=" + idTipoEnvio + " ]";
    }
    
}
