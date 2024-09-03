package edu.pucmm.eict.modelos;

import edu.pucmm.eict.controladores.CountryController;
import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Browser;

@Entity
@Table(name = "accesos")
public class Acceso {

    @Id
    @GeneratedValue
    private UUID id;
    private String ipClient;
    private String pais;
    private String userAgent;
    private String navegador;
    private String plataforma;
    private Date localDateTime;
    private String fechaString;
    @ManyToOne(fetch = FetchType.LAZY)
    private Url url;

    public Acceso(String ipClient, String userAgent, Date localDateTime, Url url) throws Exception {
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.navegador = getBrowser(userAgent);
        this.plataforma = getPlatform(userAgent);
        this.localDateTime = localDateTime;
        this.pais = CountryController.getInstancia().getCountry(ipClient);
        this.url = url;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        fechaString = formatter.format(localDateTime);
    }

    public Acceso() {    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIpClient() {
        return ipClient;
    }

    public void setIpClient(String ipClient) {
        this.ipClient = ipClient;
    }

    public String getNavegador() {
        return navegador;
    }

    public void setNavegador(String navegador) {
        this.navegador = navegador;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public Date getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(Date localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Url getUrl() {
        return url;
    }
    public void setUrl(Url url) {
        this.url = url;
    }
    public String getFechaString() {
        return fechaString;
    }
    public void setFechaString(String fechaString) {
        this.fechaString = fechaString;
    }
    public String getPais() {return pais;}
    public void setPais(String pais) {this.pais = pais;}
    public String getUserAgent() {return userAgent;}
    public void setUserAgent(String userAgent) {this.userAgent = userAgent;}

    public String getBrowser(String myUserAgent) {
        UserAgent userAgent = UserAgent.parseUserAgentString(myUserAgent);
        Browser browser = userAgent.getBrowser();
        return StringUtils.capitalize(browser.getName());
    }

    public String getPlatform(String myUserAgent) {
        UserAgent userAgent = UserAgent.parseUserAgentString(myUserAgent);
        return userAgent.getOperatingSystem().getName();
    }

}
