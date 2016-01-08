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
@Table(name = "TB_PARAMETRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbParametro.findAll", query = "SELECT t FROM TbParametro t"),
    @NamedQuery(name = "TbParametro.findByIdParametro", query = "SELECT t FROM TbParametro t WHERE t.idParametro = :idParametro"),
    @NamedQuery(name = "TbParametro.findByNomeParametro", query = "SELECT t FROM TbParametro t WHERE t.nomeParametro = :nomeParametro"),
    @NamedQuery(name = "TbParametro.findByTipoParametro", query = "SELECT t FROM TbParametro t WHERE t.tipoParametro = :tipoParametro"),
    @NamedQuery(name = "TbParametro.findByRetornoParametro", query = "SELECT t FROM TbParametro t WHERE t.retornoParametro = :retornoParametro"),
    @NamedQuery(name = "TbParametro.findByDataInclusao", query = "SELECT t FROM TbParametro t WHERE t.dataInclusao = :dataInclusao")})
public class TbParametro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PARAMETRO")
    private Integer idParametro;
    @Basic(optional = false)
    @Column(name = "NOME_PARAMETRO")
    private String nomeParametro;
    @Basic(optional = false)
    @Column(name = "TIPO_PARAMETRO")
    private String tipoParametro;
    @Basic(optional = false)
    @Column(name = "RETORNO_PARAMETRO")
    private String retornoParametro;
    @Basic(optional = false)
    @Column(name = "DATA_INCLUSAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;

    public TbParametro() {
    }

    public TbParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public TbParametro(Integer idParametro, String nomeParametro, String tipoParametro, String retornoParametro, Date dataInclusao) {
        this.idParametro = idParametro;
        this.nomeParametro = nomeParametro;
        this.tipoParametro = tipoParametro;
        this.retornoParametro = retornoParametro;
        this.dataInclusao = dataInclusao;
    }

    public Integer getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public String getNomeParametro() {
        return nomeParametro;
    }

    public void setNomeParametro(String nomeParametro) {
        this.nomeParametro = nomeParametro;
    }

    public String getTipoParametro() {
        return tipoParametro;
    }

    public void setTipoParametro(String tipoParametro) {
        this.tipoParametro = tipoParametro;
    }

    public String getRetornoParametro() {
        return retornoParametro;
    }

    public void setRetornoParametro(String retornoParametro) {
        this.retornoParametro = retornoParametro;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametro != null ? idParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbParametro)) {
            return false;
        }
        TbParametro other = (TbParametro) object;
        if ((this.idParametro == null && other.idParametro != null) || (this.idParametro != null && !this.idParametro.equals(other.idParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ci_eletronico.entities.TbParametro[ idParametro=" + idParametro + " ]";
    }
    
}
