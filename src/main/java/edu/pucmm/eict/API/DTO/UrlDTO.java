package edu.pucmm.eict.API.DTO;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.pucmm.eict.modelos.Acceso;
import edu.pucmm.eict.modelos.Url;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class UrlDTO {

    private UUID id;
    private String urlOriginal;
    private String titulo;
    private String hash;
    private String fechaCreacion;
    private String direccion;
    private boolean activo;
    private String usuarioId;
    private List<AccesoDTO> accesos;
    private String imagenBase64;

    public UrlDTO(Url url) throws Exception {
        this.id = url.getId();
        this.urlOriginal = url.getUrlOriginal();
        this.titulo = url.getTitulo();
        this.hash = url.getHash();
        this.fechaCreacion = url.getFechaString();
        this.direccion = url.getDireccion();
        this.activo = url.isActivo();
        this.usuarioId = url.getUsuario().getUsuario();
        if(url.getAccesos() != null)
        {
            this.accesos = new ArrayList<>();
            url.getAccesos().forEach(acceso -> accesos.add(new AccesoDTO(acceso)));
        }
        this.imagenBase64 = getImage(this.urlOriginal);
}

    public String urlToBase64(String imageUrl) throws Exception {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStream inputStream = connection.getInputStream();
        byte[] bytes = inputStream.readAllBytes();
        return Base64.getEncoder().encodeToString(bytes);
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrlOriginal() {
        return urlOriginal;
    }

    public void setUrlOriginal(String urlOriginal) {
        this.urlOriginal = urlOriginal;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<AccesoDTO> getAccesos() {return accesos;}

    public void setAccesos(List<AccesoDTO> accesos) {this.accesos = accesos;}

    public String getImagenBase64() {return imagenBase64;}

    public void setImagenBase64(String imagenBase64) {this.imagenBase64 = imagenBase64;}
}

