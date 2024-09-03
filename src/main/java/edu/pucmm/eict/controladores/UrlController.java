package edu.pucmm.eict.controladores;

import edu.pucmm.eict.modelos.Acceso;
import edu.pucmm.eict.modelos.TipoUsuario;
import edu.pucmm.eict.modelos.Url;
import edu.pucmm.eict.modelos.Usuario;
import edu.pucmm.eict.servicios.AccesoServicios;
import edu.pucmm.eict.servicios.UrlServicios;
import edu.pucmm.eict.servicios.UsuarioServicios;
import edu.pucmm.eict.utils.Parseador;
import io.javalin.Javalin;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.javalin.http.Context;


import static io.javalin.apibuilder.ApiBuilder.*;

public class UrlController {
    Javalin app;

    public UrlController(Javalin app) {
        this.app = app;
    }

    public void rutas() {
        app.routes(() -> {

            path("/urls", () -> {

                get("/", ctx -> {
                    Usuario user = getUsuarioCTX(ctx);

                    int pageNumber = 1;
                    if (ctx.queryParam("page") != null) {
                        pageNumber = Integer.parseInt(ctx.queryParam("page"));
                    }

                    int pageSize = 3;

                    if(user == null){
                        Map<String, Object> modelo = new HashMap<>();

                        ArrayList<Url> urlSession = ctx.sessionAttribute("urlSession");
                        if(urlSession == null){
                            urlSession = new ArrayList<>();
                        }

                        int totalPages = (int) Math.ceil((double) urlSession.size() / pageSize);
                        System.out.println("Total Pages: " + totalPages);
                        modelo.put("totalPages", totalPages);
                        modelo.put("pageNumber", pageNumber);

                        ArrayList<Url> urls = UrlServicios.getInstance().getPaginatedProductos(pageNumber, pageSize, urlSession);

                        modelo.put("urls", urls);
                        ctx.render("vistas/url.ftl", modelo);

                    } else if (user.getTipoUsuario().equals(TipoUsuario.CLIENTE)) {
                        Map<String, Object> modelo = new HashMap<>();

                        ArrayList<Url> misUrls = UrlServicios.getInstance().getUrlsUsuario(user.getUsuario());
                        ArrayList<Url> urls = UrlServicios.getInstance().getPaginatedProductos(pageNumber, pageSize, misUrls);

                        int totalPages = (int) Math.ceil((double) misUrls.size() / pageSize);
                        modelo.put("totalPages", totalPages);
                        modelo.put("pageNumber", pageNumber);

                        modelo.put("urls", urls);
                        modelo.put("usuario", user);
                        ctx.render("vistas/url.ftl", modelo);

                    }
                    else {
                        Map<String, Object> modelo = new HashMap<>();

                        ArrayList<Url> misUrls = UrlServicios.getInstance().getUrlsUsuario(user.getUsuario());
                        ArrayList<Url> urls = UrlServicios.getInstance().getPaginatedProductos(pageNumber, pageSize, misUrls);

                        int totalPages = (int) Math.ceil((double) misUrls.size() / pageSize);
                        modelo.put("totalPages", totalPages);
                        modelo.put("pageNumber", pageNumber);

                        modelo.put("urls", urls);
                        modelo.put("usuario", user);
                        ctx.render("vistas/url.ftl", modelo);
                    }
                });

                post("/add", ctx -> {
                    boolean invitado = false;
                    String urlParam = ctx.formParam("myURL");
                    String titulo = ctx.formParam("titulo");
                    if(urlParam == null || urlParam.isEmpty()){
                        //TODO: Configurar templado url.ftl para que muestre mensaje de error
                        ctx.redirect("/");
                        return;
                    }
                    //TODO: Configurar login para poder crear URLs
                    //Usuario usuario = ctx.sessionAttribute("usuario");
                    Usuario user = getUsuarioCTX(ctx);
                    if (user == null){
                        user = UsuarioServicios.getInstance().getInvitado();
                        invitado= true;
                    }
                    else if (user.getTipoUsuario() == TipoUsuario.INVITADO){
                        invitado = true;
                    }

                    String hash;
                    if (titulo == null || titulo.isEmpty()) {
                        hash = UrlServicios.getInstance().crearUrl(urlParam, Parseador.getTitulo(urlParam), user);
                    }
                    else {
                        hash = UrlServicios.getInstance().crearUrl(urlParam, ctx.formParam("titulo"), user);
                    }

                    Url url = UrlServicios.getInstance().buscarUrlPorHash(hash);
                    if(url == null){
                        ctx.redirect("/cutlink/urls");
                        return;
                    }
                    if(invitado){
                        ArrayList<Url> urlSession = ctx.sessionAttribute("urlSession");
                        if(urlSession == null){
                            urlSession = new ArrayList<>();
                        }
                        urlSession.add(url);
                        ctx.sessionAttribute("urlSession", urlSession);
                    }
                    ctx.redirect("/urls/resumen/"+hash);
                });

                get("/resumen/{hash}", ctx -> {

                    Usuario user = getUsuarioCTX(ctx);
                    Map<String, Object> modelo = new HashMap<>();
                    String id = ctx.pathParam("hash");
                    Url url = UrlServicios.getInstance().buscarUrlPorHash(id);
                    if(url == null){
                        ctx.redirect("/urls");
                        return;
                    }

                    if(user == null){
                        ArrayList<Url> urlSession = ctx.sessionAttribute("urlSession");
                        if(urlSession == null){
                            ctx.redirect("/urls");
                            return;
                        }
                        if(urlSession.stream().noneMatch(u -> u.getHash().equals(url.getHash()))){
                            ctx.redirect("/urls");
                            return;
                        }
                    }
                    else{
                        if(user.getTipoUsuario().equals(TipoUsuario.CLIENTE))
                        {
                            if(!url.getUsuario().getUsuario().equals(user.getUsuario())){
                                ctx.redirect("/urls");
                                return;
                            }
                        }

                    }
                    modelo.put("usuario", user);
                    modelo.put("url", url);
                    int tamanoAcceso = url.getAccesos().size();
                    modelo.put("cantAccesos", tamanoAcceso);
                    if(tamanoAcceso > 0) {

                        modelo.put("ultimoAcceso", url.getAccesos().get(tamanoAcceso-1).getFechaString());
                        modelo.put("accesos", AccesoServicios.getInstance().getAccesosFromHash(id));
                        modelo.put("paisMasVisitado", AccesoServicios.getInstance().getTopCountryWithMostAccesses(id));
                    }

                    List<Acceso> accesos = url.getAccesos();
                    Map<String, Long> plataformasData = accesos.stream()
                            .collect(Collectors.groupingBy(Acceso::getPlataforma, Collectors.counting()));

                    modelo.put("plataformasLabels", plataformasData.keySet().toArray());
                    modelo.put("plataformasData", plataformasData.values().toArray());

                    Map<String, Long> navegadoresData = accesos.stream()
                            .collect(Collectors.groupingBy(Acceso::getNavegador, Collectors.counting()));

                    modelo.put("navegadoresLabels", navegadoresData.keySet().toArray());
                    modelo.put("navegadoresData", navegadoresData.values().toArray());

                    // Obtener la cantidad de accesos por hora del día
                    Map<Integer, Long> accesosPorHora = new HashMap<>();

                    for (Acceso acceso : url.getAccesos()) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(acceso.getLocalDateTime());
                        int hora = calendar.get(Calendar.HOUR_OF_DAY);
                        accesosPorHora.put(hora, accesosPorHora.getOrDefault(hora, 0L) + 1L);
                    }

                    // Crear un mapa con todas las horas del día
                    Map<Integer, Long> horasDelDia = IntStream.rangeClosed(0, 23)
                            .boxed()
                            .collect(Collectors.toMap(hora -> hora, hora -> 0L));

                    // Fusionar los dos mapas
                    Map<Integer, Long> horasConAccesos = new HashMap<>(horasDelDia);
                    horasConAccesos.putAll(accesosPorHora);

                    // Poner los datos en el modelo
                    modelo.put("horasLabels", horasConAccesos.keySet().toArray());
                    modelo.put("accesosPorHora", horasConAccesos.values().toArray());


                    ctx.render("vistas/urlResumen.ftl", modelo);
                });
            });
        });
    }
    public Usuario getUsuarioCTX(Context ctx){return ctx.sessionAttribute("usuario_CL");}
}
