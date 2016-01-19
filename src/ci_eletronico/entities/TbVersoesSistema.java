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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victorcmaf
 */
@Entity
@Table(name = "TB_VERSOES_SISTEMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbVersoesSistema.findByVesi_VersaoCi_NomeJar_ArquiteturaWindows", query = "SELECT t FROM TbVersoesSistema t WHERE t.vesiVersaoJar = :vesiVersaoJar AND t.vesiNomeJar = :vesiNomeJar AND t.vesiArquiteturaWindows = :vesiArquiteturaWindows"),
    @NamedQuery(name = "TbVersoesSistema.findAll", query = "SELECT t FROM TbVersoesSistema t"),
    @NamedQuery(name = "TbVersoesSistema.findByIdVesi", query = "SELECT t FROM TbVersoesSistema t WHERE t.idVesi = :idVesi"),
    @NamedQuery(name = "TbVersoesSistema.findByVesiVersaoJar", query = "SELECT t FROM TbVersoesSistema t WHERE t.vesiVersaoJar = :vesiVersaoJar"),
    @NamedQuery(name = "TbVersoesSistema.findByVesiNomeJar", query = "SELECT t FROM TbVersoesSistema t WHERE t.vesiNomeJar = :vesiNomeJar"),
    @NamedQuery(name = "TbVersoesSistema.findByVesiArquiteturaWindows", query = "SELECT t FROM TbVersoesSistema t WHERE t.vesiArquiteturaWindows = :vesiArquiteturaWindows"),
    @NamedQuery(name = "TbVersoesSistema.findByVesiTamanhoJar", query = "SELECT t FROM TbVersoesSistema t WHERE t.vesiTamanhoJar = :vesiTamanhoJar")})
public class TbVersoesSistema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_VESI")
    private Integer idVesi;
    @Basic(optional = false)
    @Column(name = "VESI_VERSAO_JAR")
    private String vesiVersaoJar;
    @Basic(optional = false)
    @Column(name = "VESI_NOME_JAR")
    private String vesiNomeJar;
    @Basic(optional = false)
    @Column(name = "VESI_ARQUITETURA_WINDOWS")
    private int vesiArquiteturaWindows;
    @Basic(optional = false)
    @Column(name = "VESI_TAMANHO_JAR")
    private int vesiTamanhoJar;
    @Basic(optional = false)
    @Lob
    @Column(name = "VESI_BLOB")
    private byte[] vesiBlob;

    public TbVersoesSistema() {
    }

    public TbVersoesSistema(Integer idVesi) {
        this.idVesi = idVesi;
    }

    public TbVersoesSistema(Integer idVesi, String vesiVersaoJar, String vesiNomeJar, int vesiArquiteturaWindows, int vesiTamanhoJar, byte[] vesiBlob) {
        this.idVesi = idVesi;
        this.vesiVersaoJar = vesiVersaoJar;
        this.vesiNomeJar = vesiNomeJar;
        this.vesiArquiteturaWindows = vesiArquiteturaWindows;
        this.vesiTamanhoJar = vesiTamanhoJar;
        this.vesiBlob = vesiBlob;
    }

    public Integer getIdVesi() {
        return idVesi;
    }

    public void setIdVesi(Integer idVesi) {
        this.idVesi = idVesi;
    }

    public String getVesiVersaoJar() {
        return vesiVersaoJar;
    }

    public void setVesiVersaoJar(String vesiVersaoJar) {
        this.vesiVersaoJar = vesiVersaoJar;
    }

    public String getVesiNomeJar() {
        return vesiNomeJar;
    }

    public void setVesiNomeJar(String vesiNomeJar) {
        this.vesiNomeJar = vesiNomeJar;
    }

    public int getVesiArquiteturaWindows() {
        return vesiArquiteturaWindows;
    }

    public void setVesiArquiteturaWindows(int vesiArquiteturaWindows) {
        this.vesiArquiteturaWindows = vesiArquiteturaWindows;
    }

    public int getVesiTamanhoJar() {
        return vesiTamanhoJar;
    }

    public void setVesiTamanhoJar(int vesiTamanhoJar) {
        this.vesiTamanhoJar = vesiTamanhoJar;
    }

    public byte[] getVesiBlob() {
        return vesiBlob;
    }

    public void setVesiBlob(byte[] vesiBlob) {
        this.vesiBlob = vesiBlob;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVesi != null ? idVesi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbVersoesSistema)) {
            return false;
        }
        TbVersoesSistema other = (TbVersoesSistema) object;
        if ((this.idVesi == null && other.idVesi != null) || (this.idVesi != null && !this.idVesi.equals(other.idVesi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ci_eletronico.entities.TbVersoesSistema[ idVesi=" + idVesi + " ]";
    }
    
}
