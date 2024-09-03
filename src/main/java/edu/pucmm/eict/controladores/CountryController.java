package edu.pucmm.eict.controladores;

import com.maxmind.geoip2.DatabaseReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;


public class CountryController {

    private static CountryController instancia;
    private final String defaultCountry = "Dominican Republic";

    private CountryController() {
    }

    public static CountryController getInstancia() {
        if (instancia == null) {
            instancia = new CountryController();
        }
        return instancia;
    }

    public String getPublicIP() throws Exception {
        try {
            URL whatIsMyIp = new URL("http://checkip.amazonaws.com" );
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatIsMyIp.openStream()));
            return in.readLine();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public String getCountry(String ip) throws Exception {

        String publicIP = null;
        try {
            InetAddress inetAddress = InetAddress.getByName(ip);
            byte[] addressBytes = inetAddress.getAddress();
            if (inetAddress.isSiteLocalAddress() || inetAddress.isAnyLocalAddress() ||
                    (addressBytes[0] & 0x80) == 0) {
                //En caso de que sea una ip privada o clase A, se procede una verificacion de la direccion publica de la maquina
                //para después buscar el pais.
                publicIP = getPublicIP();
            } else {
                //En caso de que sea una ip pública, se procede a buscar el pais.
                publicIP = ip;
            }
            File database = new File("src/main/resources/publico/DB/GeoLite2-Country.mmdb" );
            DatabaseReader reader = new DatabaseReader.Builder(database).build();
            InetAddress ipAddress = InetAddress.getByName(publicIP);
            String country = reader.country(ipAddress).getCountry().getName();
            System.out.println(country);
            return country;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return defaultCountry;
        }
    }
}
