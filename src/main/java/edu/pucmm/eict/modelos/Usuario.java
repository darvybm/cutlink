package edu.pucmm.eict.modelos;

import edu.pucmm.eict.controladores.CountryController;
import edu.pucmm.eict.controladores.Direccionamiento;
import edu.pucmm.eict.servicios.UrlServicios;
import jakarta.persistence.*;

import java.net.InetAddress;
import java.net.UnknownHostException;


@Entity
@Table(name = "usuarios")
public class Usuario {



    @Id
    private String usuario;
    private String nombre;
    private String correo;
    private String password;
    private TipoUsuario tipoUsuario;

    private boolean habilitado;

    public Usuario( String usuario, String nombre, String correo, String password, TipoUsuario tipoUsuario) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.habilitado = true;
    }

    // Constructor vacío para Hibernate
    public Usuario() {

    }

    // Getters y setters

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getPais() throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        String ip = addr.getHostAddress();
        return CountryController.getInstancia().getCountry(ip);
    }

    public int getLinks() {
        Usuario user = new Usuario();
        user.setCorreo(getCorreo());
        user.setHabilitado(isHabilitado());
        user.setUsuario(getUsuario());
        user.setNombre(getNombre());
        user.setPassword(getPassword());
        user.setTipoUsuario(getTipoUsuario());
        return UrlServicios.getInstance().getUrlsUsuario(user.getUsuario()).size();
    }

}

