/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico_queries;

//import ci_eletronico.entities.TbComunicacaoInterna;
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
        
//        return em.createNamedQuery("TbComunicacaoInterna.findPorAprovarByIdUoGestor",TbComunicacaoInterna.class)                
//                .setParameter("idUoGestor",nidUoGestor)
//                .getResultList();
        
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
    
}
