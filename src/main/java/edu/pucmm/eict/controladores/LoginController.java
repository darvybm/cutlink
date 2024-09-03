package edu.pucmm.eict.controladores;

import edu.pucmm.eict.modelos.TipoUsuario;
import edu.pucmm.eict.modelos.Usuario;
import edu.pucmm.eict.servicios.UsuarioServicios;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jasypt.util.text.StrongTextEncryptor;

import java.util.HashMap;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;

public class LoginController {

    Javalin app;
    private StrongTextEncryptor encriptador;

    public LoginController(Javalin app) {
        this.app = app;
    }

    public void rutas() {
        app.routes(() -> {

            get("/logout", ctx -> {
                invalidarSesion(ctx);
                ctx.redirect("/");
            });

            path("/login", () -> {

                get("/", ctx -> {
                    if(ctx.sessionAttribute("usuarioID_CL") != null){
                        ctx.redirect("/");
                    }
                    ctx.render("vistas/login.ftl");
                });

                post("/", ctx -> {
                    String usuario = ctx.formParam("usuario");
                    String password = ctx.formParam("password");

                    if(usuario == "Invitado")
                    {
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("mensaje", "Usuario o password incorrectos");
                        ctx.render("/vistas/login.ftl", modelo);
                        ctx.redirect("/");
                        return;
                    }

                    if (Autenticar(usuario, password) != null && Autenticar(usuario, password)){
                        Usuario user = UsuarioServicios.getInstance().getUsuarioByUsuario(usuario);
                        //En caso de que el usuario no este habilitado
                        if(!user.isHabilitado()){
                            Map<String, Object> modelo = new HashMap<>();
                            modelo.put("mensaje", "Cuenta Bloqueada");
                            ctx.render("/vistas/login.ftl", modelo);
                            return;
                        }
                        ctx.sessionAttribute("usuarioID_CL", user.getUsuario());
                        ctx.sessionAttribute("usuario_CL", user);
                        ctx.sessionAttribute("tipoUsuario_CL", user.getTipoUsuario());
                        ctx.sessionAttribute("SCutLink", encriptador.encrypt(usuario) + "-" + encriptador.encrypt(password));
                        if(ctx.formParam("recordar") != null){
                            ctx.cookie("CutLink", encriptador.encrypt(usuario) + "-" + encriptador.encrypt(password));
                        }
                        else{
                            ctx.cookie("CutLink", "null");
                        }
                        ctx.redirect("/urls");
                    }
                    else {
                        System.out.println("Usuario o contraseña incorrectos");
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("mensaje", "Usuario o password incorrectos");
                        ctx.render("/vistas/login.ftl", modelo);
                    }
                });


            });

            path("/register", () -> {
                get("/", ctx -> {
                    ctx.render("vistas/register.ftl");
                });
                post("/", ctx -> {
                    String usuario = ctx.formParam("usuario");
                    String nombre = ctx.formParam("nombre");
                    String password = ctx.formParam("password");
                    String email = ctx.formParam("email");
                    if(UsuarioServicios.getInstance().existeUsuario(usuario)){
                        Map<String, Object> modelo = new HashMap<>();
                        modelo.put("mensaje", "Usuario ya existente");
                        ctx.render("/vistas/register.ftl", modelo);
                        return;
                    }
                    Usuario nuevoUsuario = new Usuario(usuario, nombre, email, password, TipoUsuario.CLIENTE);
                    UsuarioServicios.getInstance().addUsuario(nuevoUsuario);
                    ctx.redirect("/login");
                });
            });
        });
    }

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
}
