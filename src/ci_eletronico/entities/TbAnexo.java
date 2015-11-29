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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "TB_ANEXO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbAnexo.findAll", query = "SELECT t FROM TbAnexo t"),
    @NamedQuery(name = "TbAnexo.findByIdAnexo", query = "SELECT t FROM TbAnexo t WHERE t.idAnexo = :idAnexo"),
    @NamedQuery(name = "TbAnexo.findByIdCoin", query = "SELECT t FROM TbAnexo t WHERE t.idCoin = :idCoin"),
    @NamedQuery(name = "TbAnexo.findByAnexoNome", query = "SELECT t FROM TbAnexo t WHERE t.anexoNome = :anexoNome"),
    @NamedQuery(name = "TbAnexo.findByAnexoTamanho", query = "SELECT t FROM TbAnexo t WHERE t.anexoTamanho = :anexoTamanho")})
public class TbAnexo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ANEXO")
    private Integer idAnexo;
    @Basic(optional = false)
    @Column(name = "ANEXO_NOME")
    private String anexoNome;
    @Basic(optional = false)
    @Column(name = "ANEXO_TAMANHO")
    private int anexoTamanho;
    @Basic(optional = false)
    @Lob
    @Column(name = "ANEXO_BLOB")
    private byte[] anexoBlob;
    @JoinColumn(name = "ID_COIN", referencedColumnName = "ID_COIN")
    @ManyToOne(optional = false)
    private TbComunicacaoInterna idCoin;

    public TbAnexo() {
    }

    public TbAnexo(Integer idAnexo) {
        this.idAnexo = idAnexo;
    }

    public TbAnexo(Integer idAnexo, String anexoNome, int anexoTamanho, byte[] anexoBlob) {
        this.idAnexo = idAnexo;
        this.anexoNome = anexoNome;
        this.anexoTamanho = anexoTamanho;
        this.anexoBlob = anexoBlob;
    }

    public Integer getIdAnexo() {
        return idAnexo;
    }

    public void setIdAnexo(Integer idAnexo) {
        this.idAnexo = idAnexo;
    }

    public String getAnexoNome() {
        return anexoNome;
    }

    public void setAnexoNome(String anexoNome) {
        this.anexoNome = anexoNome;
    }

    public int getAnexoTamanho() {
        return anexoTamanho;
    }

    public void setAnexoTamanho(int anexoTamanho) {
        this.anexoTamanho = anexoTamanho;
    }

    public byte[] getAnexoBlob() {
        return anexoBlob;
    }

    public void setAnexoBlob(byte[] anexoBlob) {
        this.anexoBlob = anexoBlob;
    }

    public TbComunicacaoInterna getIdCoin() {
        return idCoin;
    }

    public void setIdCoin(TbComunicacaoInterna idCoin) {
        this.idCoin = idCoin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnexo != null ? idAnexo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbAnexo)) {
            return false;
        }
        TbAnexo other = (TbAnexo) object;
        if ((this.idAnexo == null && other.idAnexo != null) || (this.idAnexo != null && !this.idAnexo.equals(other.idAnexo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ci_eletronico.entities.TbAnexo[ idAnexo=" + idAnexo + " ]";
    }
    
}
