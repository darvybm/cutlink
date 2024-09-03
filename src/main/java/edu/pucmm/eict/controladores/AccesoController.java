package edu.pucmm.eict.controladores;

import edu.pucmm.eict.modelos.Acceso;
import edu.pucmm.eict.modelos.TipoUsuario;
import edu.pucmm.eict.modelos.Url;
import edu.pucmm.eict.modelos.Usuario;
import edu.pucmm.eict.servicios.AccesoServicios;
import edu.pucmm.eict.servicios.UrlServicios;
import edu.pucmm.eict.servicios.UsuarioServicios;
import io.javalin.Javalin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.javalin.http.Context;
import org.jasypt.util.text.StrongTextEncryptor;

import static io.javalin.apibuilder.ApiBuilder.*;


public class AccesoController {
    Javalin app;
    private StrongTextEncryptor encriptador;

    public Boolean Autenticar(String usuarioID, String password){
        Usuario user = UsuarioServicios.getInstance().getUsuarioByUsuario(usuarioID);
        if(user != null){
            encriptador = new StrongTextEncryptor();
            encriptador.setPassword("CutLink");
            return user.isHabilitado() && encriptador.decrypt(user.getPassword()).equals(password);
        }
        else{
            return null;
        }
    }

    public void invalidarSesion(Context ctx){
        ctx.sessionAttribute("usuarioID_CL", null);
        ctx.sessionAttribute("usuario_CL", null);
        ctx.sessionAttribute("tipoUsuario_CL", TipoUsuario.INVITADO);
        ctx.sessionAttribute("SCutLink", "null");
        ctx.cookie("CutLink", "null");
    }
    public void invalidarCookie(Context ctx){
        ctx.cookie("CutLink", "null");
    }

    public AccesoController(Javalin app) {
        this.app = app;
    }

    public Boolean verificarURLSession(Context ctx){
        ArrayList<Url> urlSession = ctx.sessionAttribute("urlSession");
        if (urlSession != null){
            return true;
        }
        else {
            urlSession = new ArrayList<Url>();
            ctx.sessionAttribute("urlSession", urlSession);
            return false;
        }
    }

    public void rutas() {

        app.before(ctx -> {
            //Verificar si la sesión está activa
            verificarURLSession(ctx);

            //Manejo de la cookie
            String cookie = ctx.cookie("CutLink");
            if (cookie != null){
                String[] cookieParts = cookie.split("-");
                if(cookieParts != null && cookieParts.length == 2)
                {
                    if (cookieParts[0].equals("null") || cookieParts[1].equals("null"))
                    {
                        invalidarSesion(ctx);
                        return;
                    }
                    else {
                        String usuario = cookieParts[0];
                        String password = cookieParts[1];
                        encriptador = new StrongTextEncryptor();
                        encriptador.setPassword("CutLink");
                        try {
                            usuario = encriptador.decrypt(usuario);
                            password = encriptador.decrypt(password);
                            if (Autenticar(usuario, password) != null && Autenticar(usuario, password)){
                                ctx.sessionAttribute("SCutLink", cookie);
                            }
                            else {
                                invalidarCookie(ctx);
                            }

                        } catch (Exception e) {
                            invalidarCookie(ctx);
                        }
                    }
                }
            }
            else {
                //TODO: Discutir si conviene siempre crear la cookie o solo cuando se loguea
            }

            //Manejo de la sesión

            String sessionManga = ctx.sessionAttribute("SCutLink");
            if (sessionManga == null){
                ctx.sessionAttribute("SCutLink", "null");
            }
            else {
                String[] sessionParts = sessionManga.split("-");
                if(sessionParts != null && sessionParts.length == 2)
                {
                    if (sessionParts[0].equals("null") || sessionParts[1].equals("null"))
                    {
                       invalidarSesion(ctx);
                    }
                    else {
                        String usuario = sessionParts[0];
                        String password = sessionParts[1];
                        encriptador = new StrongTextEncryptor();
                        encriptador.setPassword("CutLink");
                        try {
                            usuario = encriptador.decrypt(usuario);
                            password = encriptador.decrypt(password);
                            if (Autenticar(usuario, password) != null && Autenticar(usuario, password)){
                                Usuario user = UsuarioServicios.getInstance().getUsuarioByUsuario(usuario);
                                ctx.sessionAttribute("usuarioID_CL", user.getUsuario());
                                ctx.sessionAttribute("usuario_CL", user);
                                ctx.sessionAttribute("tipoUsuario_CL", user.getTipoUsuario());
                            }
                            else {
                                invalidarSesion(ctx);
                                return;
                            }

                        } catch (Exception e) {
                            invalidarSesion(ctx);
                            return;
                        }
                    }
                }
            }

            String path = ctx.path();
            if(!path.equals("/")) {
                Matcher matcher = Pattern.compile("^/([a-zA-Z0-9]{7})$").matcher(path);
                if (matcher.matches()) {
                    String hash = matcher.group(1);
                    Url url = UrlServicios.getInstance().buscarUrlPorHash(hash);
                    if (url != null) {
                        if(!url.isActivo())
                        {
                            ctx.redirect("/");
                            return;
                        }
                        String ip = ctx.ip();
                        String userAgent = ctx.header("User-Agent");
                        String SO = ctx.header("sec-ch-ua-platform");
                        System.out.println("Navegador: " + userAgent);
                        System.out.println("IP: " + ip);
                        Acceso acceso = new Acceso(ip, userAgent, new Date(), url);
                        AccesoServicios.getInstance().crearAcceso(acceso);
                        url.getAccesos().add(acceso);
                        ctx.redirect(url.getUrlOriginal());
                    }
                }
            }
        });

        app.routes(() -> {

            path("/", () -> {
                get("/", ctx -> {
                    Usuario usuario = ctx.sessionAttribute("usuario_CL");
                    TipoUsuario tipoUsuario = ctx.sessionAttribute("tipoUsuario_CL");

                    if(usuario != null){
                        System.out.println("Usuario: " +usuario.getUsuario());
                        Map<String, Object> model = new HashMap<>();
                        model.put("usuario", usuario);
                        model.put("tipoUsuario", tipoUsuario);
                        ctx.render("vistas/bienvenida.ftl", model);
                    }
                    else {
                        System.out.println("Usuario no logueado");
                        ctx.render("vistas/bienvenida.ftl");
                    }
                });
            });

        });
    }


}
