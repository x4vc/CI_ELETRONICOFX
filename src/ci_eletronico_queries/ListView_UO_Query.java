/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico_queries;

import ci_eletronico.entities.TbUnidadeOrganizacional;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author victorcmaf
 */
public class ListView_UO_Query {
    EntityManager em;
    EntityManagerFactory emf;

    public ListView_UO_Query() {
        emf = Persistence.createEntityManagerFactory("CI_EletronicoPU");
        em = emf.createEntityManager();
        
        em.getTransaction().begin();
    }
     public List<TbUnidadeOrganizacional> listaUO() {       
        
         //return em.createNamedQuery("TbUsuario.findAll",TbUsuario.class).getResultList();
        return em.createNamedQuery("TbUnidadeOrganizacional.findAll",TbUnidadeOrganizacional.class).getResultList();
        
        }
    
    
}
