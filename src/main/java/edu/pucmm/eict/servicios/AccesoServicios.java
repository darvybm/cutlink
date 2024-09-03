package edu.pucmm.eict.servicios;

import edu.pucmm.eict.modelos.Acceso;
import edu.pucmm.eict.modelos.TipoUsuario;
import edu.pucmm.eict.modelos.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccesoServicios extends GestionDB<Acceso>{

    private static AccesoServicios instance;
    private AccesoServicios() {
        super(Acceso.class);
    }

    public static AccesoServicios getInstance() {
        if (instance == null) {
            instance = new AccesoServicios();
        }
        return instance;
    }

    public void crearAcceso(Acceso acceso) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(acceso);
        em.getTransaction().commit();
        em.close();
    }

    public List<Acceso> countAccesosFromHash(String hash) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select count(a) FROM Acceso a WHERE url.hash = :hash", Acceso.class);
        query.setParameter("hash", hash);
        List<Acceso> accesos = query.getResultList();
        em.close();
        return accesos;
    }

    //check top country with most accesses
    public String getTopCountryWithMostAccesses(String hash) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT a.pais FROM Acceso a WHERE a.url.hash = :hash GROUP BY a.pais ORDER BY COUNT(a.pais) DESC");
        query.setParameter("hash", hash);
        query.setMaxResults(1);
        List<String> countries = query.getResultList();
        em.close();
        if (!countries.isEmpty()) {
            return countries.get(0);
        }
        return null;
    }

    public HashMap<String, Integer> getCountriesTotalAccess(Usuario usuario){
        Query query;
        EntityManager em = getEntityManager();
        if(!usuario.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)){
            query = em.createQuery("SELECT a.pais, COUNT(a.pais) FROM Acceso a WHERE a.url.usuario = :usuario GROUP BY a.pais");
            query.setParameter("usuario", usuario);
        }
        else
            query = em.createQuery("SELECT a.pais, COUNT(a.pais) FROM Acceso a GROUP BY a.pais");
        List<Object[]> countries = query.getResultList();
        em.close();
        HashMap<String, Integer> countriesMap = new HashMap<>();
        for (Object[] country : countries) {
            countriesMap.put((String) country[0], ((Long) country[1]).intValue());
        }
        return countriesMap;
    }


    public int countTotalAccesses(Usuario usuario) {
        Query query;
        EntityManager em = getEntityManager();
        if(!usuario.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)){
            query = em.createQuery("SELECT COUNT(a) FROM Acceso a WHERE a.url.usuario = :usuario");
            query.setParameter("usuario", usuario);
        }
        else
            query = em.createQuery("SELECT COUNT(a) FROM Acceso a");
        Long result = (Long) query.getSingleResult();
        int total = result.intValue();
        em.close();
        return total;
    }

    public String getTopBrowserWithMostAccesses(String hash) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select a.navegador FROM Acceso a WHERE url.hash = :hash GROUP BY a.navegador ORDER BY COUNT(a.navegador) DESC");
        query.setParameter("hash", hash);
        query.setMaxResults(1);
        List <String> navegador = query.getResultList();
        em.close();
        if (!navegador.isEmpty()) {
            return navegador.get(0);
        }
        return null;
    }

    public String getTopBrowserWithMostAccesses(String hash, int MaxBrowser) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select a.navegador FROM Acceso a WHERE url.hash = :hash GROUP BY a.navegador ORDER BY COUNT(a.navegador) DESC");
        query.setParameter("hash", hash);
        query.setMaxResults(MaxBrowser);
        List <String> navegador = query.getResultList();
        em.close();
        if (!navegador.isEmpty()) {
            return navegador.get(0);
        }
        return null;
    }

    public List<Acceso> getAccesosFromHash(String hash) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select a FROM Acceso a WHERE url.hash = :hash", Acceso.class);
        query.setParameter("hash", hash);
        List<Acceso> accesos = query.getResultList();
        em.close();
        return accesos;
    }

    public List<Acceso> countPaisesFromHash(String hash) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select count(a.pais) FROM Acceso a WHERE url.hash = :hash", Acceso.class);
        query.setParameter("hash", hash);
        List<Acceso> accesos = query.getResultList();
        em.close();
        return accesos;
    }

    public List<Acceso> getPaisesFromHash(String hash) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select a.pais FROM Acceso a WHERE url.hash = :hash", Acceso.class);
        query.setParameter("hash", hash);
        List<Acceso> accesos = query.getResultList();
        em.close();
        return accesos;
    }





}
