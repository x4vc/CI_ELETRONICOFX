/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico_queries;

import ci_eletronico.entities.TbAtualizarSistema;
import ci_eletronico.entities.TbUnidadeOrganizacional;
import ci_eletronico.entities.TbUnidadeOrganizacionalGestor;
import ci_eletronico.entities.TbUsuario;
import ci_eletronico.entities.TbUsuarioPerfilUo;
import ci_eletronico.entities.TbVersoesSistema;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author victorcmaf
 */
public class LoginQuery {
    EntityManager em;
    EntityManagerFactory emf;

    public LoginQuery() {
        emf = Persistence.createEntityManagerFactory("CI_EletronicoPU");
        em = emf.createEntityManager();
        
        em.getTransaction().begin();
    }
    
    public List<TbUsuario> listaTbUsuario() {
        
        return em.createNamedQuery("TbUsuario.findAll",TbUsuario.class).getResultList();        
        
        }
    public List<TbAtualizarSistema> listaTbAtualizarSistema() {
        
        return em.createNamedQuery("TbAtualizarSistema.findAll",TbAtualizarSistema.class).getResultList();        
        
        }
    public List<TbUsuario> listaUserLogin(String strLogin) {
        
         //return em.createNamedQuery("TbUsuario.findAll",TbUsuario.class).getResultList();
        return em.createNamedQuery("TbUsuario.findByUsuLogin",TbUsuario.class)
                .setParameter("usuLogin", strLogin )
                .getResultList();
        
        }
    
    public List<TbUsuarioPerfilUo> listaUO(TbUsuario nIdUsuario) {       
        
         //return em.createNamedQuery("TbUsuario.findAll",TbUsuario.class).getResultList();
        return em.createNamedQuery("TbUsuarioPerfilUo.findByIdUsuario",TbUsuarioPerfilUo.class)
                .setParameter("idUsuario", nIdUsuario )
                .getResultList();
        
        }
    
    public List<Object[]> listaJoinUO(TbUsuario nIdUsuario) {
        return em.createNamedQuery("TbUsuarioPerfilUo.findByJoinIdUsuario")
                .setParameter("idUsuario", nIdUsuario)
                .getResultList();
        
    }
    public List<TbUsuarioPerfilUo> listaJoinUO2(TbUsuario nIdUsuario) {       
        
         //return em.createNamedQuery("TbUsuario.findAll",TbUsuario.class).getResultList();
        return em.createNamedQuery("TbUsuarioPerfilUo.findByJoinIdUsuario2",TbUsuarioPerfilUo.class)
                .setParameter("idUsuario", nIdUsuario )
                .getResultList();
        
        }
    public List<TbUnidadeOrganizacionalGestor> getIdUOGestor(TbUnidadeOrganizacional nIdUO) {       
        
        //return em.createNamedQuery("TbUsuario.findAll",TbUsuario.class).getResultList();
        return em.createNamedQuery("TbUnidadeOrganizacionalGestor.findByIdUo",TbUnidadeOrganizacionalGestor.class)
                .setParameter("idUnidadeOrganizacional", nIdUO )
                .getResultList();
        
        }
    public List<TbUnidadeOrganizacional> getDescricaoUOGestor(int nIdUOGestor) {       
        
        //return em.createNamedQuery("TbUsuario.findAll",TbUsuario.class).getResultList();
        return em.createNamedQuery("TbUnidadeOrganizacional.findByIdUnidadeOrganizacional",TbUnidadeOrganizacional.class)
                .setParameter("idUnidadeOrganizacional", nIdUOGestor )
                .getResultList();
        
        }
    public List<TbVersoesSistema> downloadAnexo(String strVersaoCodigo,int nArquitetura, String strFileName) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        
        return em.createNamedQuery("TbVersoesSistema.findByVesi_VersaoCi_NomeJar_ArquiteturaWindows",TbVersoesSistema.class) 
                .setParameter("vesiVersaoJar", strVersaoCodigo)
                .setParameter("vesiNomeJar", strFileName)
                .setParameter("vesiArquiteturaWindows", nArquitetura)
                .getResultList();   
        
        }
}
