package edu.pucmm.eict.controladores;

import edu.pucmm.eict.modelos.CodigoQR;
import edu.pucmm.eict.modelos.TipoUsuario;
import edu.pucmm.eict.modelos.Url;
import edu.pucmm.eict.modelos.Usuario;
import edu.pucmm.eict.servicios.CodigoQRServicios;
import edu.pucmm.eict.servicios.UrlServicios;
import edu.pucmm.eict.servicios.UsuarioServicios;
import io.javalin.Javalin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static io.javalin.apibuilder.ApiBuilder.*;

public class CodigoQrController {
    Javalin app;

    public CodigoQrController(Javalin app) {
        this.app = app;
    }

    public void rutas() {
        app.routes(() -> {
            path("/codigoQR", () -> {

                get("/", ctx -> {

                    int pageNumber = 1;
                    if (ctx.queryParam("page") != null) {
                        pageNumber = Integer.parseInt(ctx.queryParam("page"));
                    }

                    int pageSize = 3;

                    Usuario user = ctx.sessionAttribute("usuario_CL");
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("usuario", user);

                    if(user == null){
                        ArrayList<CodigoQR> codigosQRSession = ctx.sessionAttribute("codigosQRSession");
                        if(codigosQRSession == null){
                            codigosQRSession = new ArrayList<>();
                        }

                        int totalPages = (int) Math.ceil((double) codigosQRSession.size() / pageSize);
                        ArrayList<CodigoQR> codigosPaginados = CodigoQRServicios.getInstance().getPaginatedCodigos(pageNumber, pageSize, codigosQRSession);

                        System.out.println("Total Pages: " + totalPages);
                        modelo.put("totalPages", totalPages);
                        modelo.put("pageNumber", pageNumber);

                        modelo.put("codigosQR", codigosPaginados);
                        ctx.render("vistas/codigoQR.ftl", modelo);
                    } else {
                        ArrayList<CodigoQR> codigosQR = CodigoQRServicios.getInstance().getCodigosQRByUser(user);

                        int totalPages = (int) Math.ceil((double) codigosQR.size() / pageSize);
                        ArrayList<CodigoQR> codigosPaginados = CodigoQRServicios.getInstance().getPaginatedCodigos(pageNumber, pageSize, codigosQR);

                        System.out.println("Total Pages: " + totalPages);
                        modelo.put("totalPages", totalPages);
                        modelo.put("pageNumber", pageNumber);

                        modelo.put("codigosQR", codigosPaginados);
                        modelo.put("usuario", user);
                        ctx.render("vistas/codigoQR.ftl", modelo);
                    }
                });

                post("/add", ctx -> {
                    Usuario user = ctx.sessionAttribute("usuario_CL");
                    String tipoQR = ctx.formParam("tipo");


                    if(user == null){
                        user = UsuarioServicios.getInstance().getInvitado();
                        ArrayList<CodigoQR> codigosQRSession = ctx.sessionAttribute("codigosQRSession");
                        if(codigosQRSession == null){
                            codigosQRSession = new ArrayList<>();
                        }
                        CodigoQR codigoQR;
                        if (tipoQR.equalsIgnoreCase("url")) {
                            String url = ctx.formParam("url");
                            codigoQR = new CodigoQR(url, user, ctx.formParam("tipo"));
                            codigosQRSession.add(codigoQR);
                            CodigoQRServicios.getInstance().addCodigoQR(codigoQR);
                            ctx.sessionAttribute("codigosQRSession", codigosQRSession);
                        }
                        else {
                            String url = "WIFI:T:" + ctx.formParam("wifi-type") + ";S:" + ctx.formParam("wifi-name") + ";P:" + ctx.formParam("wifi-password") + ";;";
                            codigoQR = new CodigoQR(url, user, ctx.formParam("tipo"));
                            codigosQRSession.add(codigoQR);
                            CodigoQRServicios.getInstance().addCodigoQR(codigoQR);
                            ctx.sessionAttribute("codigosQRSession", codigosQRSession);
                        }
                        ctx.redirect("/codigoQR/");
                    }
                    CodigoQR codigoQR;
                    if (tipoQR.equalsIgnoreCase("url")) {
                        String url = ctx.formParam("url");
                        codigoQR = new CodigoQR(url, user, ctx.formParam("tipo"));
                        CodigoQRServicios.getInstance().addCodigoQR(codigoQR);
                    }
                    else {
                        String url = "WIFI:T:" + ctx.formParam("wifi-type") + ";S:" + ctx.formParam("wifi-name") + ";P:" + ctx.formParam("wifi-password") + ";;";
                        codigoQR = new CodigoQR(url, user, ctx.formParam("tipo"));
                        CodigoQRServicios.getInstance().addCodigoQR(codigoQR);
                    }
                    ctx.redirect("/codigoQR/");
                });
            });
        });
    }
}
