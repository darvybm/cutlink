package edu.pucmm.eict.controladores;

import edu.pucmm.eict.modelos.TipoUsuario;
import edu.pucmm.eict.modelos.Usuario;
import edu.pucmm.eict.servicios.AccesoServicios;
import edu.pucmm.eict.servicios.UrlServicios;
import edu.pucmm.eict.servicios.UsuarioServicios;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.get;

public class AyudaController {

    Javalin app;
    public AyudaController(Javalin app) {
        this.app = app;
    }

    public void rutas() {
        app.routes(() -> {

            path("/ayuda", () -> {
                get("/", ctx -> {
                    Usuario usuario = ctx.sessionAttribute("usuario_CL");
                    if(usuario == null){
                        ctx.render("vistas/ayuda.ftl");
                        return;
                    }
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("usuario", usuario);
                    ctx.render("vistas/ayuda.ftl", modelo);
                });
            });
        });
    }

}
