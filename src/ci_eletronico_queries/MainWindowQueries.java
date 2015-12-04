/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci_eletronico_queries;

//import ci_eletronico.entities.TbComunicacaoInterna;
import ci_eletronico.entities.TbAnexo;
import ci_eletronico.entities.TbCiDestinatario;
import ci_eletronico.entities.TbComunicacaoInterna;
import ci_eletronico.entities.TbUnidadeOrganizacional;
import ci_eletronico.entities.TbUnidadeOrganizacionalGestor;
import ci_eletronico.entities.TbUsuario;
import ci_eletronico.utilitarios.Seguranca;
import java.util.Date;
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
                //.setParameter("idUoGestor", nidUoGestor)
                .getResultList();   
        
        }
     public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaEnviados(int nidUoRemitente) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        
        return em.createNamedQuery("TbComunicacaoInterna.findByCIsEnviadas",TbComunicacaoInterna.class) 
                .setParameter("idUnidadeOrganizacional", nidUoRemitente)
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
    
    public boolean DesarquivarCIEnviada(int nlIdCI) throws Exception{
        boolean bArquivado = false;        
        try {
            //Atualizamos primeiro a tabela TB_COMUNICACAO_INTERNA
            TbComunicacaoInterna DesarquivarCI = em.find(TbComunicacaoInterna.class, nlIdCI);
            
            //DesarquivarCI.setCoinUoGestorArquivado(bArquivado);            
            DesarquivarCI.setCoinUoArquivado(bArquivado);                    
                        
            em.merge(DesarquivarCI);
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
    public boolean DesarquivarCIRecebida(int nlIdCI) throws Exception{
        boolean bArquivado = false;
        
        try {
            TbCiDestinatario DesarquivarCI = em.find(TbCiDestinatario.class, nlIdCI);
            
            //DesarquivarCI.setCoinDestinatarioUoGestorArquivado(bArquivado);
            DesarquivarCI.setCoinDestinatarioUoArquivado(bArquivado);

            em.merge(DesarquivarCI);
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
    
    public boolean ArquivarCIEnviada(int nlIdCI, int nlButtonSelected) throws Exception{
        boolean bArquivado = true;
        Date data = new Date();
        try {
            //Atualizamos primeiro a tabela TB_COMUNICACAO_INTERNA
            TbComunicacaoInterna ArquivarCI = em.find(TbComunicacaoInterna.class, nlIdCI);
            
            //Valores dos botões 
            //1-caixa de recebidas (solicitando aprovação) - btnCaixaEntradaSolicitandoAprovacao
            //2-caixa de recebidas - btnCaixaEntrada
            //3-caixa de recebidas (pendencias) - btnCaixaPendencias
            //4-caixa de recebidas (arquivadas) - btnCaixaArquivadas
            //5-Caixa de enviados (arquivadas) - btnCaixaEnviadosArquivados
            //6-Caixa de enviados - btnCaixaSaida;
            //7-Caixa de enviados (solicitando aprovação) - btnPendentesAprovacao
            switch (nlButtonSelected){
                case 7:
                    //Codigo para atualizar registro
                    ArquivarCI.setCoinUoGestorArquivado(bArquivado);
                    break;
                case 6:
                    //Codigo para atualizar registro
                    ArquivarCI.setCoinUoArquivado(bArquivado);
                    break;
            }            
            em.merge(ArquivarCI);
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
    
    public boolean ArquivarCIRecebida(int nlIdCI, int nlButtonSelected) throws Exception{
        boolean bArquivado = true;
        Date data = new Date();
        try {
            TbCiDestinatario ArquivarCI = em.find(TbCiDestinatario.class, nlIdCI);
            
            //Valores dos botões 
            //1-caixa de recebidas (solicitando aprovação) - btnCaixaEntradaSolicitandoAprovacao
            //2-caixa de recebidas - btnCaixaEntrada
            //3-caixa de recebidas (pendencias) - btnCaixaPendencias
            //4-caixa de recebidas (arquivadas) - btnCaixaArquivadas
            //5-Caixa de enviados (arquivadas) - btnCaixaEnviadosArquivados
            //6-Caixa de enviados - btnCaixaSaida;
            //7-Caixa de enviados (solicitando aprovação) - btnPendentesAprovacao
            switch (nlButtonSelected){
                case 1:
                    //Codigo para atualizar registro
                    ArquivarCI.setCoinDestinatarioUoGestorArquivado(bArquivado);
                    break;
                case 2:
                    ArquivarCI.setCoinDestinatarioUoArquivado(bArquivado);
                    break;
                case 3:
                    ArquivarCI.setCoinDestinatarioUoArquivado(bArquivado);
                    break;
            }            
            em.merge(ArquivarCI);
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
    public boolean AprovarCIEnviada(int nlIdCI) throws Exception{
        boolean bAprovado = true;
        Date data = new Date();
        try {
            //Atualizamos primeiro a tabela TB_COMUNICACAO_INTERNA
            TbComunicacaoInterna AprovarCI = em.find(TbComunicacaoInterna.class, nlIdCI);
            
            //Codigo para atualizar registro
            AprovarCI.setCoinAutorizado(bAprovado);
            AprovarCI.setCoinDataAutorizado(data);
            em.merge(AprovarCI);
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
    
    public boolean AprovarCIRecebida(int nlIdCI) throws Exception{
        boolean bAprovado = true;
        Date data = new Date();
        try {
            TbCiDestinatario AprovarCI = em.find(TbCiDestinatario.class, nlIdCI);
            
            //Codigo para atualizar registro
            AprovarCI.setCoinDestinatarioGestorAutorizado(bAprovado);
            AprovarCI.setCoinDestinatarioGestorDataAutorizado(data);
            em.merge(AprovarCI);
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
    public List<TbCiDestinatario> getlistaCaixaEntradaSolicitandoAprovacao(int nIdUoDestinatario) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        
        return em.createNamedQuery("TbCiDestinatario.findByIdUoGestorDestinatario",TbCiDestinatario.class) 
                .setParameter("idUoGestorDestinatario", nIdUoDestinatario)
                .getResultList();   
        
        }
    public List<TbCiDestinatario> getlistaCaixaEntrada(int nIdUoDestinatario) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        
        return em.createNamedQuery("TbCiDestinatario.findByIdUoDestinatario",TbCiDestinatario.class) 
                .setParameter("idUoDestinatario", nIdUoDestinatario)
                .getResultList();   
        
        }
    public List<TbCiDestinatario> getlistaCaixaEntradaPendencias(int nIdUoDestinatario) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        
        return em.createNamedQuery("TbCiDestinatario.findByCoinDestinatarioPendente",TbCiDestinatario.class) 
                .setParameter("idUoDestinatario", nIdUoDestinatario)
                .getResultList();   
        
        }
    public List<TbCiDestinatario> getlistaCaixaEntradaArquivados(int nIdUoDestinatario) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        
        return em.createNamedQuery("TbCiDestinatario.findByCoinDestinatarioUoArquivado",TbCiDestinatario.class) 
                .setParameter("idUoDestinatario", nIdUoDestinatario)
                .getResultList();   
        
        }
    public List<TbComunicacaoInterna> getlistaCaixaSaidaArquivados(int nIdUo) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        
        return em.createNamedQuery("TbComunicacaoInterna.findByUOArquivado",TbComunicacaoInterna.class) 
                .setParameter("idUnidadeOrganizacional", nIdUo)
                .getResultList();   
        
        }
    public List<TbCiDestinatario> getlistaGestorCaixaEntradaArquivados(int nIdUoDestinatario) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        
        return em.createNamedQuery("TbCiDestinatario.findByGestorUoArquivado",TbCiDestinatario.class) 
                .setParameter("idUoDestinatario", nIdUoDestinatario)
                .getResultList();   
        
        }
     public List<TbCiDestinatario> getlistaCiParaAprovar(TbComunicacaoInterna nIdUoDestinatario) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        
        return em.createNamedQuery("TbCiDestinatario.findByIdCoin",TbCiDestinatario.class) 
                .setParameter("idCoin", nIdUoDestinatario)
                .getResultList();   
        
        }
    
}
