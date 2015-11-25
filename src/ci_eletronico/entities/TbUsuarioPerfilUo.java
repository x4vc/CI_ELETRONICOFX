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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victorcmaf
 */
@Entity
@Table(name = "TB_USUARIO_PERFIL_UO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbUsuarioPerfilUo.findAll", query = "SELECT t FROM TbUsuarioPerfilUo t"),
    @NamedQuery(name = "TbUsuarioPerfilUo.findByIdUsuarioPerfilUo", query = "SELECT t FROM TbUsuarioPerfilUo t WHERE t.idUsuarioPerfilUo = :idUsuarioPerfilUo"),
    @NamedQuery(name = "TbUsuarioPerfilUo.findByIdUsuario", query = "SELECT t FROM TbUsuarioPerfilUo t WHERE t.idUsuario = :idUsuario AND t.uspuAtivo = 1"),
    @NamedQuery(name = "TbUsuarioPerfilUo.findByUspuAtivo", query = "SELECT t FROM TbUsuarioPerfilUo t WHERE t.uspuAtivo = :uspuAtivo")})
public class TbUsuarioPerfilUo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_USUARIO_PERFIL_UO")
    private Integer idUsuarioPerfilUo;
    @Basic(optional = false)
    @Column(name = "USPU_ATIVO")
    private boolean uspuAtivo;
    @JoinColumn(name = "ID_UNIDADE_ORGANIZACIONAL", referencedColumnName = "ID_UNIDADE_ORGANIZACIONAL")
    @ManyToOne(optional = false)
    private TbUnidadeOrganizacional idUnidadeOrganizacional;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private TbUsuario idUsuario;
    @JoinColumn(name = "ID_USUARIO_PERFIL", referencedColumnName = "ID_USUARIO_PERFIL")
    @ManyToOne(optional = false)
    private TbUsuarioPerfil idUsuarioPerfil;

    public TbUsuarioPerfilUo() {
    }

    public TbUsuarioPerfilUo(Integer idUsuarioPerfilUo) {
        this.idUsuarioPerfilUo = idUsuarioPerfilUo;
    }

    public TbUsuarioPerfilUo(Integer idUsuarioPerfilUo, boolean uspuAtivo) {
        this.idUsuarioPerfilUo = idUsuarioPerfilUo;
        this.uspuAtivo = uspuAtivo;
    }

    public Integer getIdUsuarioPerfilUo() {
        return idUsuarioPerfilUo;
    }

    public void setIdUsuarioPerfilUo(Integer idUsuarioPerfilUo) {
        this.idUsuarioPerfilUo = idUsuarioPerfilUo;
    }

    public boolean getUspuAtivo() {
        return uspuAtivo;
    }

    public void setUspuAtivo(boolean uspuAtivo) {
        this.uspuAtivo = uspuAtivo;
    }

    public TbUnidadeOrganizacional getIdUnidadeOrganizacional() {
        return idUnidadeOrganizacional;
    }

    public void setIdUnidadeOrganizacional(TbUnidadeOrganizacional idUnidadeOrganizacional) {
        this.idUnidadeOrganizacional = idUnidadeOrganizacional;
    }

    public TbUsuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(TbUsuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TbUsuarioPerfil getIdUsuarioPerfil() {
        return idUsuarioPerfil;
    }

    public void setIdUsuarioPerfil(TbUsuarioPerfil idUsuarioPerfil) {
        this.idUsuarioPerfil = idUsuarioPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioPerfilUo != null ? idUsuarioPerfilUo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbUsuarioPerfilUo)) {
            return false;
        }
        TbUsuarioPerfilUo other = (TbUsuarioPerfilUo) object;
        if ((this.idUsuarioPerfilUo == null && other.idUsuarioPerfilUo != null) || (this.idUsuarioPerfilUo != null && !this.idUsuarioPerfilUo.equals(other.idUsuarioPerfilUo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ci_eletronico.entities.TbUsuarioPerfilUo[ idUsuarioPerfilUo=" + idUsuarioPerfilUo + " ]";
    }
    
}
