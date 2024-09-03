package edu.pucmm.eict.servicios;

import jakarta.persistence.*;

public class GestionDB<T> {

    private static EntityManagerFactory emf;
    private Class<T> claseEntidad;


    public GestionDB(Class<T> claseEntidad) {
        if(emf == null) {
            emf = Persistence.createEntityManagerFactory("MiUnidadPersistencia");
        }
        this.claseEntidad = claseEntidad;
    }

    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}