package edu.pucmm.eict.servicios;

import edu.pucmm.eict.modelos.TipoUsuario;
import edu.pucmm.eict.modelos.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.jasypt.util.text.StrongTextEncryptor;

import java.util.ArrayList;

public class UsuarioServicios extends GestionDB<Usuario>{
    private static UsuarioServicios instancia;

    private UsuarioServicios(){
        super(Usuario.class);
    }

    public static UsuarioServicios getInstance(){
        if(instancia==null){
            instancia = new UsuarioServicios();
        }
        return instancia;
    }


    public Usuario addUsuario(Usuario usuario){
        EntityManager em = getEntityManager();
        String plainPassword = usuario.getPassword();

        StrongTextEncryptor encriptador = new StrongTextEncryptor();
        encriptador.setPassword("CutLink");
        String encryptedPassword = encriptador.encrypt(plainPassword);
        usuario.setPassword(encryptedPassword);
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        return usuario;
    }

    public Usuario getUsuarioByUsuario(String usuario){
        EntityManager em = getEntityManager();
        Usuario user = null;
        try {
            user = em.find(Usuario.class, usuario);
        }
        catch (Exception e){
            user = null;
        }
        return user;
    }

    public ArrayList<Usuario> getUsuarios(){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select u from Usuario u");
        return (ArrayList<Usuario>) query.getResultList();
    }

    public ArrayList<Usuario> getUsuariosByTipo(TipoUsuario tipo){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select u from Usuario u where u.tipoUsuario = :tipo");
        query.setParameter("tipo", tipo);
        return (ArrayList<Usuario>) query.getResultList();
    }

    public ArrayList<Usuario> getUsuariosByTipoAndUsuario(TipoUsuario tipo, String usuario){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select u from Usuario u where u.tipoUsuario = :tipo and LOWER(u.usuario) LIKE LOWER(:usuario)");
        query.setParameter("tipo", tipo);
        query.setParameter("usuario", usuario + "%"); // % es para que busque cualquier cosa que empiece con el usuario
        return (ArrayList<Usuario>) query.getResultList();
    }

    public void modificarUsuario(Usuario usuario) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
    }

    public void eliminarUsuario(String usuario) {
        if(usuario.equals("admin")){
            return;
        }
        EntityManager em = getEntityManager();
        Usuario user = em.find(Usuario.class, usuario);
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }

    public Usuario getInvitado(){
        Usuario invitado = new Usuario("Invitado", "Invitado", "Invitado", "Invitado", TipoUsuario.INVITADO);
        return invitado;
    }

    public Boolean existeUsuario(String usuario){
        EntityManager em = getEntityManager();
        Usuario user = null;
        try {
            user = em.find(Usuario.class, usuario);
            if(user != null){
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }

    public TipoUsuario habilitarUsuario(String usuario , Boolean habilitado) {
        EntityManager em = getEntityManager();
        Usuario user = em.find(Usuario.class, usuario);
        user.setHabilitado(habilitado);
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        return user.getTipoUsuario();
    }
}