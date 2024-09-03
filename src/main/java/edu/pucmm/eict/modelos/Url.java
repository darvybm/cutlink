package edu.pucmm.eict.modelos;

import edu.pucmm.eict.controladores.Direccionamiento;
import jakarta.persistence.*;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "urls")
public class Url {

    @Id
    @GeneratedValue
    private UUID id;
    private String urlOriginal;
    private String titulo;
    private String hash;
    private Date fechaCreacion;
    private String fechaString;
    private String direccion;
    private boolean activo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario", nullable = false)
    private Usuario usuario;
    @OneToMany(mappedBy = "url", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Acceso> accesos = new ArrayList<>();

    public Url(String originalURL, String titulo, Date fechaCreacion, String hash, Usuario usuario) throws UnknownHostException {
        if(originalURL.contains("http://") || originalURL.contains("https://"))
            this.urlOriginal = originalURL;
        else
            this.urlOriginal = "http://" + originalURL;
        this.hash = hash;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.fechaString = formatter.format(fechaCreacion);
        this.direccion = Direccionamiento.getInstance().getDireccionServidor() + "/" + hash;
        this.activo = true;
    }

    public Url() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Acceso> getAccesos() {
        return accesos;
    }

    public void setAccesos(List<Acceso> accesos) {
        this.accesos = accesos;
    }

    public String getFechaString() {
        return fechaString;
    }

    public void setFechaString(String fechaString) {
        this.fechaString = fechaString;
    }
    public String getDireccion() {return direccion;}

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
