package edu.pucmm.eict;

import edu.pucmm.eict.API.modelos.REST.ControladorREST;
import edu.pucmm.eict.API.modelos.SOAP.ControladorSOAP;
import edu.pucmm.eict.GRPC.GrpcServer;
import edu.pucmm.eict.controladores.*;
import edu.pucmm.eict.modelos.TipoUsuario;
import edu.pucmm.eict.modelos.Usuario;
import edu.pucmm.eict.servicios.UrlServicios;
import edu.pucmm.eict.servicios.UsuarioServicios;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main {
    public static void main(String[] args) throws Exception {

        //Para obtener la IP de la máquina
        String direccion = Direccionamiento.getInstance().getRedServidor();
        int puerto = Direccionamiento.getInstance().getPuertoServidor();

        //TODO: Obtener y cambiar a la IP publica si necesitamos desplegar en la nube
        //Puerto por defecto 5000, si se desea cambiar, se cambia en el constructor de Direccionamiento
        var app = Javalin.create(javalinConfig -> {
            javalinConfig.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
                staticFileConfig.location = Location.CLASSPATH;
            });
        });

        ControladorSOAP controladorSOAP = new ControladorSOAP(app);
        controladorSOAP.aplicarRutas();

        app.start(direccion, puerto);

        new UrlController(app).rutas();
        new AccesoController(app).rutas();
        new HomeController(app).rutas();
        new CodigoQrController(app).rutas();
        new AdministrarController(app).rutas();
        new LoginController(app).rutas();
        new AyudaController(app).rutas();
        new ControladorREST(app).aplicarRutas();

        //Uso para obtener direcciones desde la nube
        try {
            long startTime = System.currentTimeMillis();
            CountryController.getInstancia().getCountry(direccion);
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println("Tiempo de respuesta: " + totalTime + "ms");
        } catch (Exception e) {
            throw new Exception("Error al obtener el país");
        }

        //Iniciando el servicio de Grpc
        Usuario admin = new Usuario("admin","admin", "admin@admin.com", "admin", TipoUsuario.ADMINISTRADOR);
        Usuario invitado = new Usuario("Invitado", "Invitado","Invitado", "Invitado", TipoUsuario.INVITADO);
        Usuario admin2 = new Usuario("admin2","admin2", "admin2", "admin2", TipoUsuario.ADMINISTRADOR);
        Usuario clienteTest = new Usuario("clienteTest","clienteTest", "clienteTest@gmail.com", "clienteTest", TipoUsuario.CLIENTE);


        UsuarioServicios.getInstance().addUsuario(admin);
        UsuarioServicios.getInstance().addUsuario(invitado);
        UsuarioServicios.getInstance().addUsuario(admin2);
        UsuarioServicios.getInstance().addUsuario(clienteTest);

        UrlServicios.getInstance().crearUrl("google.com", "Google", admin);
        UrlServicios.getInstance().crearUrl("youtube.com", "Youtube", admin);
        UrlServicios.getInstance().crearUrl("facebook.com", "Facebook", invitado);
        UrlServicios.getInstance().crearUrl("instagram.com", "Instagram", invitado);
        UrlServicios.getInstance().crearUrl("twitter.com", "Twitter", invitado);
        UrlServicios.getInstance().crearUrl("github.com", "Github", invitado);

        int port = 50051;
        GrpcServer grpcServer = new GrpcServer(port);
        grpcServer.start();
    }
}