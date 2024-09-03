package edu.pucmm.eict.modelos;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "codigos_qr")
public class CodigoQR {
    @Id
    @GeneratedValue
    private UUID id;
    private String url;
    private String tipo;
    private boolean activo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario", nullable = false)
    private Usuario usuario;

    public CodigoQR(String url, Usuario usuario, String tipo) {
        this.url = url;
        this.usuario = usuario;
        this.tipo = tipo;
        this.activo = true;
    }

    public CodigoQR() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
