<p align="center"> 
  <img src="https://github.com/user-attachments/assets/d99d9772-598f-4aeb-98b9-0e6c1b1dda40" alt="Header Image" width="400"/> 
</p> 

<p align="center"> 
  <img src="https://img.shields.io/badge/Language-Java-blue" alt="Java Badge"/> 
  <img src="https://img.shields.io/badge/Framework-Javalin-brightgreen" alt="Javalin Badge"/> 
  <img src="https://img.shields.io/badge/Frontend-Freemarker-lightgrey" alt="Freemarker Badge"/> 
  <img src="https://img.shields.io/badge/Build%20Tool-Gradle-orange" alt="Gradle Badge"/> 
  <img src="https://img.shields.io/badge/Database-H2-blue" alt="H2 Badge"/> 
  <img src="https://img.shields.io/badge/API-REST%20%7C%20gRPC%20%7C%20SOAP-yellow" alt="API Badge"/>
</p>

# Cutlink
Cutlink es una aplicación web diseñada para acortar URLs de manera rápida y sencilla, además de proporcionar estadísticas detalladas sobre el uso de cada enlace acortado. Con Cutlink, puedes gestionar tus enlaces, obtener valiosos insights sobre quién y cómo accede a ellos, y generar códigos QR para facilitar su distribución.

La aplicación está desarrollada en Java utilizando el framework Javalin, lo que asegura un rendimiento óptimo y una experiencia de usuario fluida. Cutlink no solo ofrece una interfaz intuitiva para acortar URLs, sino que también incluye una serie de APIs (REST, gRPC y SOAP) para una integración flexible y el manejo avanzado de enlaces.

Además de la funcionalidad de acortamiento de URLs, Cutlink proporciona herramientas avanzadas para aquellos que desean más control y análisis de sus enlaces, tales como estadísticas de clics, países de origen, navegadores utilizados, y más. También permite la generación y personalización de códigos QR para diversos propósitos, incluyendo enlaces web y configuraciones de redes Wi-Fi.

## Table of Contents
- [Features](#features)
- [Application](#application)
- [Tools Used](#tools-used)
- [How to Install](#how-to-install)
- [Contributors](#contributors)
- [License](#license)
- [Contact Me](#contact-me)

## Features
- **Shorten URLs without registration:** Acorta URLs directamente desde la página de inicio sin necesidad de iniciar sesión.
- **URL Management:** Crea, almacena y gestiona URLs acortadas en tu cuenta.
- **Detailed Statistics:** Accede a estadísticas detalladas de tus URLs acortadas, incluyendo clics, países de origen, navegadores utilizados y más.
- **QR Codes:** Genera códigos QR para URLs, páginas web, contraseñas de Wi-Fi, conexiones Wi-Fi, y más.
- **Page Preview:** Visualiza una vista previa de la página a la que apunta el enlace acortado directamente desde la aplicación.
- **REST API:** Proporciona endpoints para gestionar URLs acortadas, acceder a estadísticas, y más.
- **gRPC API:** Ofrece una interfaz eficiente para la comunicación entre servicios internos.
- **SOAP API:** Implementa un servicio SOAP para interoperabilidad con sistemas antiguos o que requieren este estándar.
  
## Application
Aquí tienes algunas capturas de pantalla y GIFs que muestran la aplicación en acción:

**Sign In and Register**
<p align="left">
  <img src="https://github.com/darvybm/cutlink/blob/main/src/main/resources/publico/img/IniciarSesion.gif" alt="Sign In" width="800"/>
</p>
<p align="left">
  <img src="https://github.com/darvybm/cutlink/blob/main/src/main/resources/publico/img/Registrarse.gif" alt="Register" width="800"/>
</p>

**Home Section**
<p align="left">
  <img src="https://github.com/darvybm/cutlink/blob/main/src/main/resources/publico/img/HomeGif.gif" alt="Home" width="800"/>
</p>

**URLs Section**
<p align="left">
  <img src="https://github.com/darvybm/cutlink/blob/main/src/main/resources/publico/img/UrlListaGif.gif" alt="URLs List" width="800"/>
</p>
<p align="left">
  <img src="https://github.com/darvybm/cutlink/blob/main/src/main/resources/publico/img/UrlCrearGif.gif" alt="Create URL" width="800"/>
</p>

**Statistics Section**
<p align="left">
  <img src="https://github.com/darvybm/cutlink/blob/main/src/main/resources/publico/img/UrlDetallesGif.gif" alt="Statistics" width="800"/>
</p>

**QR Codes Section**
<p align="left">
  <img src="https://github.com/user-attachments/assets/699eeca9-896e-4ba3-81f2-534524ae025f" alt="QR Codes" width="800"/>
</p>

## Tools Used
- **Language:** Java ![Java](https://img.shields.io/badge/Language-Java-blue)
- **Frameworks:** Javalin ![Javalin](https://img.shields.io/badge/Framework-Javalin-brightgreen)
- **Frontend:** Freemarker, HTML, CSS, JavaScript, Bootstrap ![Freemarker](https://img.shields.io/badge/Frontend-Freemarker-lightgrey) ![HTML](https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=html5&logoColor=white) ![CSS](https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=css3&logoColor=white) ![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=javascript&logoColor=black) ![Bootstrap](https://img.shields.io/badge/Bootstrap-563D7C?style=flat&logo=bootstrap&logoColor=white)
- **Build Tool:** Gradle ![Gradle](https://img.shields.io/badge/Build%20Tool-Gradle-02303A?style=flat&logo=gradle&logoColor=white)
- **Database:** H2 ![H2](https://img.shields.io/badge/Database-H2-blue)
  
## How to Install

Para ejecutar la aplicación, sigue estos pasos:

> [!IMPORTANT]  
> **Requisitos:**
> - **Java 17:** Asegúrate de tener Java 17 instalado en tu sistema.
> - **Javalin 5.3.2:** Esto está incluido en el archivo `build.gradle` del proyecto, por lo que no necesitas instalarlo manualmente.

1. **Clona este repositorio:**
    ```bash
    git clone https://github.com/darvybm/cutlink.git
    ```

2. **Navega al directorio del proyecto:**
    ```bash
    cd cutlink
    ```

3. **Configura las variables en `application.properties` si es necesario:** 
   Aquí puedes personalizar ajustes como el puerto de la aplicación o la URL de la base de datos.

4. **Ejecuta el proyecto utilizando tu IDE preferido o Gradle:**
    ```bash
    ./gradlew run
    ```

> [!NOTE]  
> En este proyecto, se utiliza la redirección donde la IP de la máquina se usa en lugar de `localhost`. Asegúrate de configurar la IP correctamente en tu entorno de desarrollo.

## Contributors
Aquí están los contribuyentes a este proyecto:

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/darvybm">
        <img src="https://github.com/darvybm.png" width="100px;" alt="darvybm"/>
        <br />
        <sub><b>darvybm</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/christpaul01">
        <img src="https://github.com/christpaul01.png" width="100px;" alt="Christpaul01"/>
        <br />
        <sub><b>Christpaul01</b></sub>
      </a>
    </td>
  </tr>
</table>

## License
Este proyecto está licenciado bajo la Licencia MIT - consulta el archivo [LICENSE](LICENSE) para más detalles.

## Contact Me

<p align="center">
  <a href="https://www.linkedin.com/in/darvybm" target="_blank">
    <img src="https://img.shields.io/badge/LinkedIn-@darvybm-blue?style=flat&logo=linkedin&logoColor=white" alt="LinkedIn Badge"/>
  </a>
  <a href="mailto:darvybm@gmail.com" target="_blank">
    <img src="https://img.shields.io/badge/Email-Contact%20Me-orange" alt="Email Badge"/>
  </a>
</p>
