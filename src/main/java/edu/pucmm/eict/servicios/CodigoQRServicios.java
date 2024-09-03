package edu.pucmm.eict.servicios;

import edu.pucmm.eict.modelos.CodigoQR;
import edu.pucmm.eict.modelos.Url;
import edu.pucmm.eict.modelos.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CodigoQRServicios extends GestionDB<CodigoQR> {
    private static CodigoQRServicios instance;

    private CodigoQRServicios() {
        super(CodigoQR.class);
    }

    public static CodigoQRServicios getInstance() {
        return instance == null ? instance = new CodigoQRServicios() : instance;}

    public ArrayList<CodigoQR> getCodigosQR() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select u from CodigoQR u");
        ArrayList<CodigoQR> codigos = (ArrayList<CodigoQR>) query.getResultList();
        return codigos;
    }

    public ArrayList<CodigoQR> getCodigosQRByUser(Usuario usuario) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select u from CodigoQR u where u.usuario = :usuario");
        query.setParameter("usuario", usuario);
        ArrayList<CodigoQR> codigosQR = (ArrayList<CodigoQR>) query.getResultList();
        return codigosQR;
    }

    public void addCodigoQR(CodigoQR codigoQR){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(codigoQR);
        em.getTransaction().commit();
    }

    public ArrayList<CodigoQR> getPaginatedCodigos(int pageNumber, int pageSize, ArrayList<CodigoQR> codigosQR) {
        int offset = (pageNumber - 1) * pageSize;
        return (ArrayList<CodigoQR>) codigosQR
                .stream()
                .filter(codigo -> codigo.isActivo())
                .skip(offset)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
}
