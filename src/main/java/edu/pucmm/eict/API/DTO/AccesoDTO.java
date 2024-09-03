package edu.pucmm.eict.API.DTO;

import edu.pucmm.eict.modelos.Acceso;

import java.util.UUID;

public class AccesoDTO {
    private UUID id;
    private String ipClient;
    private String pais;
    private String userAgent;
    private String navegador;
    private String plataforma;
    private String fechaAcceso;
    private String Url;

    public AccesoDTO(Acceso acceso) {
        this.id = acceso.getId();
        this.ipClient = acceso.getIpClient();
        this.pais = acceso.getPais();
        this.userAgent = acceso.getUserAgent();
        this.navegador = acceso.getNavegador();
        this.plataforma = acceso.getPlataforma();
        this.fechaAcceso = acceso.getFechaString();
        this.Url = acceso.getUrl().getDireccion();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIpClient() {
        return ipClient;
    }

    public void setIpClient(String ipClient) {
        this.ipClient = ipClient;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getNavegador() {
        return navegador;
    }

    public void setNavegador(String navegador) {
        this.navegador = navegador;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getFechaString() {
        return fechaAcceso;
    }

    public void setFechaString(String fechaString) {
        this.fechaAcceso = fechaString;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
