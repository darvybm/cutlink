package edu.pucmm.eict.controladores;

import edu.pucmm.eict.modelos.Url;
import edu.pucmm.eict.modelos.Usuario;
import edu.pucmm.eict.servicios.AccesoServicios;
import edu.pucmm.eict.servicios.UrlServicios;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;

public class HomeController{
    Javalin app;

    public HomeController(Javalin app) {
        this.app = app;
    }

    public void rutas() {
        app.routes(() -> {
            path("/home", () -> {
                get("/", ctx -> {
                    Map<String, Object> modelo = new HashMap<>();
                    Usuario user = getUsuarioCTX(ctx);
                    if(user == null){
                        ctx.redirect("/urls");
                        return;
                    }
                    else {
                        ArrayList<Url> topURLS = UrlServicios.getInstance().topUrlsList(user);
                        ArrayList<Integer> topURLAccesos = new ArrayList<>();
                        //TODO: hacerlo con un solo query, no con un for. Ya esta presente la funcion
                        //TODO: en AccesoServicios, solo falta llamarla. topUrls() Luego implementarla en
                        //TODO: Freemarker
                        for (Url topURL : topURLS) {
                            topURLAccesos.add(topURL.getAccesos().size());
                        }
                        HashMap<String,Integer> Paises = AccesoServicios.getInstance().getCountriesTotalAccess(user);
                        modelo.put("Paises", Paises);

                        int totalAccesos = AccesoServicios.getInstance().countTotalAccesses(user);
                        modelo.put("cantUrls", UrlServicios.getInstance().getCountUrlsActive(user));
                        modelo.put("cantAccesos", totalAccesos);
                        modelo.put("topURL", topURLS);
                        modelo.put("cantTopURL", topURLAccesos);
                        modelo.put("usuario", user);
                    }
                    ctx.render("vistas/home.ftl", modelo);
                });
            });
        });
    }
    public Usuario getUsuarioCTX(Context ctx){return ctx.sessionAttribute("usuario_CL");}
}