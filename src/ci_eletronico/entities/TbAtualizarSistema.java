/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Victor
 */
@Entity
@Table(name = "TB_ATUALIZAR_SISTEMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbAtualizarSistema.findAll", query = "SELECT t FROM TbAtualizarSistema t"),
    @NamedQuery(name = "TbAtualizarSistema.findByIdAtsi", query = "SELECT t FROM TbAtualizarSistema t WHERE t.idAtsi = :idAtsi"),
    @NamedQuery(name = "TbAtualizarSistema.findByAtsiVersao", query = "SELECT t FROM TbAtualizarSistema t WHERE t.atsiVersao = :atsiVersao"),
    @NamedQuery(name = "TbAtualizarSistema.findByAtsiPrecisaAtualizar", query = "SELECT t FROM TbAtualizarSistema t WHERE t.atsiPrecisaAtualizar = :atsiPrecisaAtualizar")})
public class TbAtualizarSistema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ATSI")
    private Integer idAtsi;
    @Basic(optional = false)
    @Column(name = "ATSI_VERSAO")
    private String atsiVersao;
    @Basic(optional = false)
    @Column(name = "ATSI_PRECISA_ATUALIZAR")
    private boolean atsiPrecisaAtualizar;

    public TbAtualizarSistema() {
    }

    public TbAtualizarSistema(Integer idAtsi) {
        this.idAtsi = idAtsi;
    }

    public TbAtualizarSistema(Integer idAtsi, String atsiVersao, boolean atsiPrecisaAtualizar) {
        this.idAtsi = idAtsi;
        this.atsiVersao = atsiVersao;
        this.atsiPrecisaAtualizar = atsiPrecisaAtualizar;
    }

    public Integer getIdAtsi() {
        return idAtsi;
    }

    public void setIdAtsi(Integer idAtsi) {
        this.idAtsi = idAtsi;
    }

    public String getAtsiVersao() {
        return atsiVersao;
    }

    public void setAtsiVersao(String atsiVersao) {
        this.atsiVersao = atsiVersao;
    }

    public boolean getAtsiPrecisaAtualizar() {
        return atsiPrecisaAtualizar;
    }

    public void setAtsiPrecisaAtualizar(boolean atsiPrecisaAtualizar) {
        this.atsiPrecisaAtualizar = atsiPrecisaAtualizar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAtsi != null ? idAtsi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbAtualizarSistema)) {
            return false;
        }
        TbAtualizarSistema other = (TbAtualizarSistema) object;
        if ((this.idAtsi == null && other.idAtsi != null) || (this.idAtsi != null && !this.idAtsi.equals(other.idAtsi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ci_eletronico.entities.TbAtualizarSistema[ idAtsi=" + idAtsi + " ]";
    }
    
}
