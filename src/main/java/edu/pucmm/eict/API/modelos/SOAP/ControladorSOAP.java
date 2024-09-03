package edu.pucmm.eict.API.modelos.SOAP;

import com.sun.net.httpserver.HttpContext;
import edu.pucmm.eict.API.BaseControlador;
import io.javalin.Javalin;
import jakarta.xml.ws.Endpoint;
import org.eclipse.jetty.http.spi.HttpSpiContextHandler;
import org.eclipse.jetty.http.spi.JettyHttpContext;
import org.eclipse.jetty.http.spi.JettyHttpServer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;


import java.lang.reflect.Method;

public class ControladorSOAP extends BaseControlador{

    public ControladorSOAP(Javalin app) {super(app);}


    @Override
    public void aplicarRutas() {

        Server server = app.jettyServer().server();
        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
        server.setHandler(contextHandlerCollection);

        try {
            HttpContext httpcontext = build(server, "/wsURL");
            WebServiceURL wsa = new WebServiceURL();
            Endpoint endpoint = Endpoint.create(wsa);
            endpoint.publish(httpcontext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            HttpContext httpcontext = build(server, "/wsUsuario");
            WebServiceUsuario wsURL = new WebServiceUsuario();
            Endpoint endpoint = Endpoint.create(wsURL);
            endpoint.publish(httpcontext);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private HttpContext build(Server server, String contextString) throws Exception{
        JettyHttpServer jettyHttpServer = new JettyHttpServer(server, true);
        JettyHttpContext ctx = (JettyHttpContext) jettyHttpServer.createContext(contextString);
        Method method = JettyHttpContext.class.getDeclaredMethod("getJettyContextHandler");
        method.setAccessible(true);
        HttpSpiContextHandler contextHandler = (HttpSpiContextHandler) method.invoke(ctx);
        contextHandler.start();
        return ctx;
    }
}
