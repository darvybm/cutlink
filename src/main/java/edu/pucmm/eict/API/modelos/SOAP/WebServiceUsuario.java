package edu.pucmm.eict.API.modelos.SOAP;

import edu.pucmm.eict.modelos.TipoUsuario;
import edu.pucmm.eict.modelos.Usuario;
import edu.pucmm.eict.servicios.UsuarioServicios;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.io.Serializable;

@WebService
public class WebServiceUsuario implements Serializable {

    private UsuarioServicios usuarioServicios = UsuarioServicios.getInstance();

    @WebMethod
    public Usuario crearUsuario(String username, String nombre, String correo, String password){
        if (!usuarioServicios.existeUsuario(username)){
            Usuario clienteSOAP = new Usuario(username, nombre,correo, password, TipoUsuario.CLIENTE);
            return usuarioServicios.addUsuario(clienteSOAP);
        }
            return null;
    }
}
