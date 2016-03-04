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
import ci_eletronico.entities.TbParametro;
import ci_eletronico.entities.TbUnidadeOrganizacional;
import ci_eletronico.entities.TbUnidadeOrganizacionalGestor;
import ci_eletronico.entities.TbUsuario;
import ci_eletronico.utilitarios.Seguranca;
import java.util.ArrayList;
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
    
    public List<TbUnidadeOrganizacionalGestor> getIdsUOs(TbUnidadeOrganizacional nIdUO) {
        
        return em.createNamedQuery("TbUnidadeOrganizacionalGestor.findIdsUos",TbUnidadeOrganizacionalGestor.class)
                .setParameter("idUnidadeOrganizacional", nIdUO )
                .getResultList();
        
        }
    
    public TbUnidadeOrganizacional getUODescricao(int nIdUO) {
        
        return em.createNamedQuery("TbUnidadeOrganizacional.findByIdUnidadeOrganizacional",TbUnidadeOrganizacional.class)
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
    public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovarPerfil2(int nidUoGestor) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        List<Integer> nValues = new ArrayList<>();
        nValues.add(1);
        nValues.add(2);
        nValues.add(4);
        nValues.add(7);
        return em.createNamedQuery("TbComunicacaoInterna.findPorAprovarByIdUoGestorPerfil2",TbComunicacaoInterna.class) 
                .setParameter("idUoGestor", nidUoGestor)                
                .setParameter("idTipoCoin",nValues)
                .getResultList();   
        
        }
     public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaEnviados(int nidUoRemitente) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        
        return em.createNamedQuery("TbComunicacaoInterna.findByCIsEnviadas",TbComunicacaoInterna.class) 
                .setParameter("idUnidadeOrganizacional", nidUoRemitente)
                .getResultList();   
        
        }
     public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaEnviadosPerfil2(int nidUoRemitente) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        List<Integer> nValues = new ArrayList<>();
        nValues.add(1);
        nValues.add(2);
        nValues.add(4);
        nValues.add(7);
        return em.createNamedQuery("TbComunicacaoInterna.findByCIsEnviadasPerfil2",TbComunicacaoInterna.class) 
                .setParameter("idUnidadeOrganizacional", nidUoRemitente)
                .setParameter("idTipoCoin",nValues)
                .getResultList();   
        
        }
    public boolean UpdateTrocarSenha(int nIdUsuario, String strNovaSenha)throws Exception{
        try {
            String strEnc = "";
            //strEnc = Seguranca.encriptar(strNovaSenha);
            strEnc = Seguranca.stringToMD5(strNovaSenha);
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
    public boolean UpdateCITbComunicacaoInterna(int nlIdCoin, String strUpdateCI)throws Exception{
        try {
            TbComunicacaoInterna CIAtualizada = em.find(TbComunicacaoInterna.class, nlIdCoin);
            
            //Codigo para atualizar registro
            CIAtualizada.setCoinConteudo(strUpdateCI);
            em.merge(CIAtualizada);
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
    
    
    
    public boolean ArquivarCIRespondidaOuEncaminhada(TbComunicacaoInterna nlIdCI, int nIdUO, int nButtonSelected) throws Exception{
        boolean bArquivado = true;
        boolean bCiPendente = false;
        Date data = new Date();
        try {
            //TbCiDestinatario ArquivarCI = em.find(TbCiDestinatario.class, nlIdCI);
            TbCiDestinatario ArquivarCI = em.createNamedQuery("TbCiDestinatario.findByIdCoinIdUoDestinatario",TbCiDestinatario.class)
                    .setParameter("idCoin", nlIdCI)
                    .setParameter("idUoDestinatario", nIdUO )
                    .getSingleResult();  
            //Valores dos botões 
            //1-caixa de recebidas (solicitando aprovação) - btnCaixaEntradaSolicitandoAprovacao
            //2-caixa de recebidas - btnCaixaEntrada
            //3-caixa de recebidas (pendencias) - btnCaixaPendencias
            //4-caixa de recebidas (arquivadas) - btnCaixaArquivadas
            //5-Caixa de enviados (arquivadas) - btnCaixaEnviadosArquivados
            //6-Caixa de enviados - btnCaixaSaida;
            //7-Caixa de enviados (solicitando aprovação) - btnPendentesAprovacao
            switch(nButtonSelected){
                case 2:
                    ArquivarCI.setCoinDestinatarioUoArquivado(bArquivado);
                    break;
                case 3:
                    ArquivarCI.setCoinDestinatarioUoArquivado(bArquivado);
                    ArquivarCI.setCoinDestinatarioPendente(bCiPendente);
                    break;
                default:
                    break;
            }
            //ArquivarCI.setCoinDestinatarioUoArquivado(bArquivado);            
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
    public boolean MarcarComoPendenciaCIRecebida(int nlIdCI, int nlButtonSelected) throws Exception{
        boolean bMarcado = true;
        Date data = new Date();
        try {
            TbCiDestinatario MarcarComoPendente = em.find(TbCiDestinatario.class, nlIdCI);
            
            //Valores dos botões 
            //1-caixa de recebidas (solicitando aprovação) - btnCaixaEntradaSolicitandoAprovacao
            //2-caixa de recebidas - btnCaixaEntrada
            //3-caixa de recebidas (pendencias) - btnCaixaPendencias
            //4-caixa de recebidas (arquivadas) - btnCaixaArquivadas
            //5-Caixa de enviados (arquivadas) - btnCaixaEnviadosArquivados
            //6-Caixa de enviados - btnCaixaSaida;
            //7-Caixa de enviados (solicitando aprovação) - btnPendentesAprovacao
            switch (nlButtonSelected){
                case 1: //caixa de recebidas (solicitando aprovação)
                    //Codigo para atualizar registro
                    MarcarComoPendente.setCoinDestinatarioPendente(bMarcado);
                    MarcarComoPendente.setCoinDestinatarioDataMarcadoPendente(data);
                    break;
                case 2: //caixa de recebidas
                    MarcarComoPendente.setCoinDestinatarioPendente(bMarcado);
                    MarcarComoPendente.setCoinDestinatarioDataMarcadoPendente(data);
                    break;
                case 3://caixa de recebidas (pendencias)
                    MarcarComoPendente.setCoinDestinatarioPendente(bMarcado);
                    MarcarComoPendente.setCoinDestinatarioDataMarcadoPendente(data);
                    break;
            }            
            em.merge(MarcarComoPendente);
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
    public boolean MarcarComoLido(int nlIdCI, int nlButtonSelected, boolean blCILido) throws Exception{
        //boolean bMarcado = true;
//        Date data = new Date();
        try {
            TbCiDestinatario MarcarComoLido = em.find(TbCiDestinatario.class, nlIdCI);
            
            //Valores dos botões 
            //1-caixa de recebidas (solicitando aprovação) - btnCaixaEntradaSolicitandoAprovacao
            //2-caixa de recebidas - btnCaixaEntrada
            //3-caixa de recebidas (pendencias) - btnCaixaPendencias
            //4-caixa de recebidas (arquivadas) - btnCaixaArquivadas
            //5-Caixa de enviados (arquivadas) - btnCaixaEnviadosArquivados
            //6-Caixa de enviados - btnCaixaSaida;
            //7-Caixa de enviados (solicitando aprovação) - btnPendentesAprovacao
            switch (nlButtonSelected){
                case 1: //caixa de recebidas (solicitando aprovação)
                    //Codigo para atualizar registro
                    MarcarComoLido.setCoinDestinatarioLido(blCILido);
                    break;
                case 2: //caixa de recebidas
                    MarcarComoLido.setCoinDestinatarioLido(blCILido);
                    break;
                case 3://caixa de recebidas (pendencias)
                    MarcarComoLido.setCoinDestinatarioLido(blCILido);
                    break;
            }            
            em.merge(MarcarComoLido);
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
    
    public TbComunicacaoInterna DesaprovarCIEnviada(int nlIdCI) throws Exception{
         
        boolean bCancelado = true;
        Date data = new Date();
        try {
            //Procuramos os dados da CI na tabela TB_COMUNICACAO_INTERNA
            TbComunicacaoInterna DesaprovarCI = em.find(TbComunicacaoInterna.class, nlIdCI); 
            
            //Codigo para preencher dados da entidade
            
            //--------------------------------------------
            
            //Codigo para atualizar registro
            DesaprovarCI.setCoinCancelado(bCancelado);
            //AprovarCI.setCoinDataAutorizado(data);
            
            //Se for desaprovado então é automaticamente arquivado
            DesaprovarCI.setCoinUoGestorArquivado(bCancelado);
            em.merge(DesaprovarCI);
            em.getTransaction().commit();            
            em.close();
            emf.close();
            return DesaprovarCI;            
        } catch (javax.persistence.PersistenceException e) {
            e.printStackTrace();
            em.close();
            emf.close();
            return null;            
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
            //Se for aprovado então é automaticamente arquivado
            AprovarCI.setCoinUoGestorArquivado(bAprovado);
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
            AprovarCI.setCoinDestinatarioUoGestorArquivado(bAprovado);
            //AprovarCI.setcoinde
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
    public TbCiDestinatario DesaprovarCIRecebida(int nlIdCI) throws Exception{
        boolean bDesaprovado = true;
        Date data = new Date();
        try {
            TbCiDestinatario DesaprovarCI = em.find(TbCiDestinatario.class, nlIdCI);
            
            //Codigo para atualizar registro
            DesaprovarCI.setCoinCancelado(bDesaprovado);
            DesaprovarCI.setCoinDestinatarioUoGestorArquivado(bDesaprovado);
            
//            AprovarCI.setCoinDestinatarioGestorAutorizado(bAprovado);
//            AprovarCI.setCoinDestinatarioGestorDataAutorizado(data);
//            AprovarCI.setCoinDestinatarioUoGestorArquivado(bAprovado);
            
            em.merge(DesaprovarCI);
            em.getTransaction().commit();            
            em.close();
            emf.close();
            return DesaprovarCI;            
        } catch (javax.persistence.PersistenceException e) {
            e.printStackTrace();
            em.close();
            emf.close();
            return null;            
        }
    }
    public List<TbAnexo> getlistaAnexo(TbComunicacaoInterna nlIdCoin) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        
        return em.createNamedQuery("TbAnexo.findByIdCoin",TbAnexo.class) 
                .setParameter("idCoin", nlIdCoin)
                .getResultList();   
        
        }
    public String getMessagemCiDesaprovada(String strNomeParametro){
        String strMessagem = "";
        List<TbParametro> listaParametro = new ArrayList<TbParametro>();
        listaParametro = em.createNamedQuery("TbParametro.findByNomeParametro",TbParametro.class) 
                .setParameter("nomeParametro", strNomeParametro)
                .getResultList();  
        for(TbParametro l : listaParametro){
            strMessagem = l.getRetornoParametro();
        }
        
        return strMessagem;
        
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
    public List<TbCiDestinatario> getlistaCaixaEntradaSolicitandoAprovacaoPerfil2(int nIdUoDestinatario) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        List<Integer> nValues = new ArrayList<>();
        nValues.add(1);
        nValues.add(2);
        nValues.add(4);
        nValues.add(7);
        return em.createNamedQuery("TbCiDestinatario.findByIdUoGestorDestinatarioPerfil2",TbCiDestinatario.class) 
                .setParameter("idUoGestorDestinatario", nIdUoDestinatario)
                .setParameter("idTipoCoin",nValues)
                .getResultList();   
        
        }
    public List<TbCiDestinatario> getlistaCaixaEntrada(int nIdUoDestinatario) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        
        return em.createNamedQuery("TbCiDestinatario.findByIdUoDestinatario",TbCiDestinatario.class) 
                .setParameter("idUoDestinatario", nIdUoDestinatario)
                .getResultList();   
        
        }
    public List<TbCiDestinatario> getlistaCaixaEntradaPerfil2(int nIdUoDestinatario) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        List<Integer> nValues = new ArrayList<>();
        nValues.add(1);
        nValues.add(2);
        nValues.add(4);
        nValues.add(7);
        return em.createNamedQuery("TbCiDestinatario.findByIdUoDestinatarioPerfil2",TbCiDestinatario.class) 
                .setParameter("idUoDestinatario", nIdUoDestinatario)
                .setParameter("idTipoCoin",nValues)
                .getResultList();   
        
        }
    public List<TbCiDestinatario> getlistaCaixaEntradaPendencias(int nIdUoDestinatario) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        
        return em.createNamedQuery("TbCiDestinatario.findByCoinDestinatarioPendente",TbCiDestinatario.class) 
                .setParameter("idUoDestinatario", nIdUoDestinatario)
                .getResultList();   
        
        }
    public List<TbCiDestinatario> getlistaCaixaEntradaPendenciasPerfil2(int nIdUoDestinatario) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        List<Integer> nValues = new ArrayList<>();
        nValues.add(1);
        nValues.add(2);
        nValues.add(4);
        nValues.add(7);
        return em.createNamedQuery("TbCiDestinatario.findByCoinDestinatarioPendentePerfil2",TbCiDestinatario.class) 
                .setParameter("idUoDestinatario", nIdUoDestinatario)
                .setParameter("idTipoCoin",nValues)
                .getResultList();   
        
        }
    public List<TbCiDestinatario> getlistaCaixaEntradaArquivados(int nIdUoDestinatario) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        
        return em.createNamedQuery("TbCiDestinatario.findByCoinDestinatarioUoArquivado",TbCiDestinatario.class) 
                .setParameter("idUoDestinatario", nIdUoDestinatario)
                .getResultList();   
        
        }
    public List<TbCiDestinatario> getlistaCaixaEntradaArquivadosPerfil2(int nIdUoDestinatario) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        List<Integer> nValues = new ArrayList<>();
        nValues.add(1);
        nValues.add(2);
        nValues.add(4);
        nValues.add(7);
        return em.createNamedQuery("TbCiDestinatario.findByCoinDestinatarioUoArquivadoPerfil2",TbCiDestinatario.class) 
                .setParameter("idUoDestinatario", nIdUoDestinatario)
                .setParameter("idTipoCoin",nValues)
                .getResultList();   
        
        }
    public List<TbComunicacaoInterna> getlistaCaixaSaidaArquivados(int nIdUo) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        
        return em.createNamedQuery("TbComunicacaoInterna.findByUOArquivado",TbComunicacaoInterna.class) 
                .setParameter("idUnidadeOrganizacional", nIdUo)
                .getResultList();   
        
        }
    public List<TbComunicacaoInterna> getlistaCaixaSaidaArquivadosPerfil2(int nIdUo) {
    //public List<TbComunicacaoInterna> getlistaTbComunicacaoInternaPorAprovar() {
        List<Integer> nValues = new ArrayList<>();
        nValues.add(1);
        nValues.add(2);
        nValues.add(4);
        nValues.add(7);
        return em.createNamedQuery("TbComunicacaoInterna.findByUOArquivadoPerfil2",TbComunicacaoInterna.class) 
                .setParameter("idUnidadeOrganizacional", nIdUo)
                .setParameter("idTipoCoin",nValues)
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
