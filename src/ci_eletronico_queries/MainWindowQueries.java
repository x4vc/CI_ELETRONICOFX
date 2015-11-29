/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico_queries;

//import ci_eletronico.entities.TbComunicacaoInterna;
import ci_eletronico.entities.TbAnexo;
import ci_eletronico.entities.TbComunicacaoInterna;
import ci_eletronico.entities.TbUnidadeOrganizacional;
import ci_eletronico.entities.TbUnidadeOrganizacionalGestor;
import ci_eletronico.entities.TbUsuario;
import ci_eletronico.utilitarios.Seguranca;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author victorcmaf
 */
public class MainWindowQueries {
    EntityManager em;
    EntityManagerFactory emf;

    public MainWindowQueries() {
        emf = Persistence.createEntityManagerFactory("CI_EletronicoPU");
        em = emf.createEntityManager();        
        em.getTransaction().begin();
    }
    
    public TbUnidadeOrganizacionalGestor getIdUOGestor(TbUnidadeOrganizacional nIdUO) {
        
        return em.createNamedQuery("TbUnidadeOrganizacionalGestor.findByIdUo",TbUnidadeOrganizacionalGestor.class)
                .setParameter("idUnidadeOrganizacional", nIdUO )
                .getSingleResult();
        
        }
    public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar(int nidUoGestor) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        
        return em.createNamedQuery("TbComunicacaoInterna.findPorAprovarByIdUoGestor",TbComunicacaoInterna.class) 
                .setParameter("idUoGestor", nidUoGestor)
                .getResultList();   
        
        }
    public boolean UpdateTrocarSenha(int nIdUsuario, String strNovaSenha)throws Exception{
        try {
            String strEnc = Seguranca.encriptar(strNovaSenha);
            TbUsuario novaSenha = em.find(TbUsuario.class, nIdUsuario);
            
            //Codigo para Create new record
            novaSenha.setUsuSenha(strEnc);
            
            em.merge(novaSenha);
            em.getTransaction().commit();            
            em.close();
            emf.close();
            return true;           
            
        } catch (javax.persistence.PersistenceException e) {
            e.printStackTrace();
            em.close();
            emf.close();
            return false;            
        }        
    }
    
    public boolean UpdateAssinatura(int nlIdUsuario, String strNovaAssinatura)throws Exception{
        try {
            TbUsuario novaAssinatura = em.find(TbUsuario.class, nlIdUsuario);
            
            //Codigo para atualizar registro
            novaAssinatura.setUsuAssinatura(strNovaAssinatura);            
            em.merge(novaAssinatura);
            em.getTransaction().commit();            
            em.close();
            emf.close();
            return true;            
        } catch (javax.persistence.PersistenceException e) {
            e.printStackTrace();
            em.close();
            emf.close();
            return false;            
        }
    }
    public List<TbAnexo> getlistaAnexo(TbComunicacaoInterna nlIdCoin) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        
        return em.createNamedQuery("TbAnexo.findByIdCoin",TbAnexo.class) 
                .setParameter("idCoin", nlIdCoin)
                .getResultList();   
        
        }
    public List<TbAnexo> downloadAnexo(int nlIdAnexo) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        
        return em.createNamedQuery("TbAnexo.findByIdAnexo",TbAnexo.class) 
                .setParameter("idAnexo", nlIdAnexo)
                .getResultList();   
        
        }
    public List<TbUsuario> listaUserAssinatura(int nlIdUser) {
        
         //return em.createNamedQuery("TbUsuario.findAll",TbUsuario.class).getResultList();
        return em.createNamedQuery("TbUsuario.findByIdUsuario",TbUsuario.class)
                .setParameter("idUsuario", nlIdUser )
                .getResultList();
        
        }
    
}
