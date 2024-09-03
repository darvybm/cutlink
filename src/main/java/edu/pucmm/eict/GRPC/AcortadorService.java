package edu.pucmm.eict.GRPC;

import acortadorrn.AcortadorRn;
import acortadorrn.AcortadorServiceGrpc;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.pucmm.eict.modelos.Acceso;
import edu.pucmm.eict.modelos.Url;
import edu.pucmm.eict.modelos.Usuario;
import edu.pucmm.eict.servicios.UrlServicios;
import edu.pucmm.eict.servicios.UsuarioServicios;
import edu.pucmm.eict.utils.Parseador;
import io.grpc.stub.StreamObserver;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AcortadorService extends AcortadorServiceGrpc.AcortadorServiceImplBase {

    @Override
    public void acortarUrl(AcortadorRn.RequestUrl request, StreamObserver<AcortadorRn.ResponseUrl> responseObserver) {
        String url = request.getUrlOriginal();

        // Generar la URL acortada
        String urlAcortada = null;
        try {
            urlAcortada = UrlServicios.getInstance().crearUrl(url, Parseador.getTitulo(url), UsuarioServicios.getInstance().getInvitado());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Crear el objeto de respuesta
        Url urlCreada = UrlServicios.getInstance().buscarUrlPorHash(urlAcortada);
        AcortadorRn.ResponseUrl response = AcortadorRn.ResponseUrl.newBuilder()
                .setJsonUrl(convertirUrlAJson(urlCreada))
                .build();

        // Enviar el objeto de respuesta a través del StreamObserver
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private String convertirUrlAJson(Url url) {
        // Crear una lista de objetos JsonObject
            // Crear un objeto JsonObject para cada URL
            JsonObject jsonUrl = new JsonObject();
            jsonUrl.addProperty("id", url.getId().toString());
            jsonUrl.addProperty("urlOriginal", url.getUrlOriginal());
            jsonUrl.addProperty("titulo", url.getTitulo());
            jsonUrl.addProperty("hash", url.getHash());
            jsonUrl.addProperty("fechaCreacion", url.getFechaCreacion().toString());
            jsonUrl.addProperty("fechaString", url.getFechaString());
            jsonUrl.addProperty("direccion", url.getDireccion());
            jsonUrl.addProperty("activo", url.isActivo());
            jsonUrl.addProperty("usuario", url.getUsuario().getUsuario());
            try {
                jsonUrl.addProperty("previewPageBase64", getImage(url.getUrlOriginal()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        // Agregar la lista de accesos al objeto JsonObject
            JsonArray jsonAccesos = new JsonArray();
            for (Acceso acceso : url.getAccesos()) {
                JsonObject jsonAcceso = new JsonObject();
                jsonAcceso.addProperty("id", acceso.getId().toString());
                jsonAcceso.addProperty("ipClient", acceso.getIpClient());
                jsonAcceso.addProperty("pais", acceso.getPais());
                jsonAcceso.addProperty("userAgent", acceso.getUserAgent());
                jsonAcceso.addProperty("navegador", acceso.getNavegador());
                jsonAcceso.addProperty("plataforma", acceso.getPlataforma());
                jsonAcceso.addProperty("localDateTime", acceso.getLocalDateTime().toString());
                jsonAcceso.addProperty("fechaString", acceso.getFechaString());
                jsonAccesos.add(jsonAcceso);
            }
            jsonUrl.add("accesos", jsonAccesos);

        Gson gson = new Gson();
        return gson.toJson(jsonUrl);
    }


    public void getUrlsUsuario(AcortadorRn.RequestUsuario usuario, StreamObserver<AcortadorRn.ResponseUrls> streamObserver) {
        // Implementa el método getUrlsUsuario aquí
        ArrayList<Url> urlsUser = UrlServicios.getInstance().getUrlsUsuario(usuario.getUsername());

        // Construir el mensaje de respuesta
        System.out.println("Username: " + usuario.getUsername());
        String jsonUrls = convertirUrlsAJson(urlsUser);
        AcortadorRn.ResponseUrls response = AcortadorRn.ResponseUrls.newBuilder().setJsonUrls(jsonUrls).build();

        // Enviar el mensaje de respuesta a través del StreamObserver
        streamObserver.onNext(response);
        streamObserver.onCompleted();
    }

    public String convertirUrlsAJson(List<Url> urls) {
        // Crear una lista de objetos JsonObject
        List<JsonObject> jsonUrls = new ArrayList<>();
        for (Url url : urls) {
            // Crear un objeto JsonObject para cada URL
            JsonObject jsonUrl = new JsonObject();
            jsonUrl.addProperty("id", url.getId().toString());
            jsonUrl.addProperty("urlOriginal", url.getUrlOriginal());
            jsonUrl.addProperty("titulo", url.getTitulo());
            jsonUrl.addProperty("hash", url.getHash());
            jsonUrl.addProperty("fechaCreacion", url.getFechaCreacion().toString());
            jsonUrl.addProperty("fechaString", url.getFechaString());
            jsonUrl.addProperty("direccion", url.getDireccion());
            jsonUrl.addProperty("activo", url.isActivo());
            jsonUrl.addProperty("usuario", url.getUsuario().getUsuario());
            try {
                jsonUrl.addProperty("previewPageBase64", getImage(url.getUrlOriginal()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Agregar la lista de accesos al objeto JsonObject
            JsonArray jsonAccesos = new JsonArray();
            for (Acceso acceso : url.getAccesos()) {
                JsonObject jsonAcceso = new JsonObject();
                jsonAcceso.addProperty("id", acceso.getId().toString());
                jsonAcceso.addProperty("ipClient", acceso.getIpClient());
                jsonAcceso.addProperty("pais", acceso.getPais());
                jsonAcceso.addProperty("userAgent", acceso.getUserAgent());
                jsonAcceso.addProperty("navegador", acceso.getNavegador());
                jsonAcceso.addProperty("plataforma", acceso.getPlataforma());
                jsonAcceso.addProperty("localDateTime", acceso.getLocalDateTime().toString());
                jsonAcceso.addProperty("fechaString", acceso.getFechaString());
                jsonAccesos.add(jsonAcceso);
            }
            jsonUrl.add("accesos", jsonAccesos);

            // Agregar el objeto JsonObject a la lista
            jsonUrls.add(jsonUrl);
        }

        // Convertir la lista de JsonObject a una cadena JSON
        Gson gson = new Gson();
        return gson.toJson(jsonUrls);
    }

    public String getImage(String url) throws Exception {
        // Send the POST request and get the response
        String urlPREVIEW = "https://api.linkpreview.net";
        String key = "0c2e834b39c0491fb29487f23decafa7";
        String query = url;
        String postData = String.format("key=%s&q=%s", key, query);
        URL apiUrl = new URL(urlPREVIEW);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", Integer.toString(postData.getBytes().length));
        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        os.write(postData.getBytes());
        os.flush();
        os.close();
        InputStream is = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        // Parse the JSON response and extract the image URL
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response.toString());
        String imageUrl = rootNode.get("image").asText();
        System.out.println("Image URL: " + imageUrl);

        return urlToBase64(imageUrl);
    }

    public String urlToBase64(String imageUrl) throws Exception {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStream inputStream = connection.getInputStream();
        byte[] bytes = inputStream.readAllBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

}
