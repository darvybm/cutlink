package edu.pucmm.eict.servicios;


import edu.pucmm.eict.modelos.TipoUsuario;
import edu.pucmm.eict.modelos.Url;
import edu.pucmm.eict.modelos.Usuario;
import edu.pucmm.eict.utils.GeneradorHash;
import jakarta.persistence.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class UrlServicios extends GestionDB<Url>{

    private static UrlServicios instance;

    private UrlServicios() {
        super(Url.class);
    }

    public static UrlServicios getInstance() {
        return instance == null ? instance = new UrlServicios() : instance;}

    public String crearUrl(String urlOriginal, String titulo, Usuario usuario) throws IOException {
        if(urlOriginal == null || urlOriginal.isEmpty()) {
            throw new RuntimeException("La URL no puede estar vacía");
        }
        else if(urlOriginal.length() > 255) {
            throw new RuntimeException("La URL no puede tener más de 255 caracteres");
        }
        EntityManager em = getEntityManager();
        String hash = GeneradorHash.crearHash(urlOriginal);
        while (comprobarExistenciaHash(hash)) {
            hash = GeneradorHash.crearHash(urlOriginal + System.currentTimeMillis());
        }
        Url url;
        if(urlOriginal.contains("http://") || urlOriginal.contains("https://")) {
            url = new Url(urlOriginal, titulo, new Date(), hash, usuario);
        } else {
            url = new Url("http://" + urlOriginal, titulo, new Date(), hash, usuario);
        }
        em.getTransaction().begin();
        em.persist(url);
        em.getTransaction().commit();
        return hash;
    }



    private boolean comprobarExistenciaHash(String hash) {
        EntityManager em = getEntityManager();
        List<Url> urls = em.createQuery("FROM Url WHERE hash = :hash", Url.class)
                .setParameter("hash", hash)
                .getResultList();
        em.close();
        return urls.size() > 0;
    }

    public Url buscarUrlPorUUID(UUID uuid) {
        EntityManager em = getEntityManager();
        return em.find(Url.class, uuid);
    }

    //TODO: Seguir este modelo de búsqueda y actualizacion para el resto de los métodos
    public void habilitarUrl(UUID uuid, Boolean habilitar) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Url url = em.find(Url.class, uuid);
            url.setActivo(habilitar);
            System.out.println("URL: " + url.getUrlOriginal());
            em.merge(url);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public Url buscarUrlPorHash(String hash) {
        EntityManager em = getEntityManager();
        TypedQuery<Url> query = em.createQuery("select u from Url u where u.hash = :hash", Url.class);
        query.setParameter("hash", hash);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public ArrayList<Url> topUrlsList(Usuario miUsuario) {
        Query query;
        EntityManager em = getEntityManager();
        if (!miUsuario.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR))
        {
            query = em.createQuery("select u from Url u where u.usuario = :miUsuario order by SIZE(u.accesos) desc");
            query.setParameter("miUsuario", miUsuario);
        }
        else
            query = em.createQuery("select u from Url u order by SIZE(u.accesos) desc");
        query.setMaxResults(5);
        ArrayList<Url> urls = (ArrayList<Url>) query.getResultList();
        System.out.println("URLS: " + urls.size());
        return urls;
    }

    public HashMap<String, Integer> topUrls() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select u from Url u order by SIZE(u.accesos) desc");
        query.setMaxResults(5);
        ArrayList<Url> urls = (ArrayList<Url>) query.getResultList();
        HashMap<String, Integer> topUrls = new HashMap<>();
        for (Url url : urls) {
            topUrls.put(url.getUrlOriginal(), url.getAccesos().size());
        }
        return topUrls;
    }

    public ArrayList<Url> buscarUrlPorTitulo(String titulo) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select u from Url u where LOWER(u.titulo) LIKE LOWER(:titulo)");
        query.setParameter("titulo", titulo.toLowerCase() + "%");
        ArrayList<Url> urls = (ArrayList<Url>) query.getResultList();
        System.out.println("URLS Buscado: " + urls.size());
        return urls;
    }

    public void modificarUrl(Url url) {
    }

    public ArrayList<Url> getUrls() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select u from Url u");
        ArrayList<Url> urls = (ArrayList<Url>) query.getResultList();
        System.out.println("URLS: " + urls.size());
        return urls;
    }




    public ArrayList<Url> getActiveUrls() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select u from Url u where u.activo = true");
        ArrayList<Url> urls = (ArrayList<Url>) query.getResultList();
        System.out.println("URLS: " + urls.size());
        return urls;
    }

    public Integer getCountUrls() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select count(u) from Url u where u.activo = true");
        return ((Long) query.getSingleResult()).intValue();
    }

    public Integer getCountUrlsActive(Usuario usuario) {
        Query query;
        EntityManager em = getEntityManager();
        if(!usuario.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR))
        {
            query = em.createQuery("select count(u) from Url u where u.usuario = :usuario");
            query.setParameter("usuario", usuario);
        }

        else
            query = em.createQuery("select count(u) from Url u");

        return ((Long) query.getSingleResult()).intValue();
    }

    public ArrayList<Url> getUrlsUsuario(String usuario) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select u from Url u where u.usuario.usuario = :usuario");
        query.setParameter("usuario", usuario);
        ArrayList<Url> urls = (ArrayList<Url>) query.getResultList();
        System.out.println("URLS: " + urls.size());
        return urls;
    }


    public ArrayList<Url> getPaginatedProductos(int pageNumber, int pageSize, ArrayList<Url> urls) {
        int offset = (pageNumber - 1) * pageSize;
        return (ArrayList<Url>) urls
                .stream()
                .filter(url -> url.isActivo())
                .skip(offset)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
