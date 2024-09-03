package edu.pucmm.eict.utils;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class Parseador {

    private Document miDoc;
    private Document miConnexion;

    public Parseador() {
    }

    public static String getTitulo(String url) {
        try {
            String miUrl = null;
            if (!(url.contains("https://") || url.contains("http://"))) {
                miUrl = "https://" + url;
            }
            else
                miUrl = url;
            Document miDoc = Jsoup.connect(miUrl).get();
            if (miDoc.title() != null) {
                return miDoc.title();
            } else {
                return "Sin Titulo";
            }
        } catch (HttpStatusException e) {
            System.out.println("HTTP error fetching URL. Status: " + e.getStatusCode());
            return "Sin Titulo";
        } catch (IOException e) {
            e.printStackTrace();
            return "Sin Titulo";
        }
    }

    public static String verifyURL(String url) throws IOException {
        Connection connection = null;
        String contentType = null;
        try {
            connection = Jsoup.connect(url).ignoreContentType(true);
            contentType = connection.execute().contentType();
        } catch (IOException e) {
            System.out.println("Error al acceder a la URL: " + e.getMessage());
        }
        return contentType;
    }

}
