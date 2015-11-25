/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico_queries;

//import ci_eletronico.entities.TbComunicacaoInterna;
import ci_eletronico.entities.TbUnidadeOrganizacional;
import ci_eletronico.entities.TbUnidadeOrganizacionalGestor;
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
    
    
}
