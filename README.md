<p align="center">
  <img src="https://github.com/user-attachments/assets/d99d9772-598f-4aeb-98b9-0e6c1b1dda40" alt="Header Image" width="400"/>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Language-Java-blue" alt="Java Badge"/>
  <img src="https://img.shields.io/badge/Framework-Javalin-brightgreen" alt="Javalin Badge"/>
  <img src="https://img.shields.io/badge/Frontend-Freemarker-lightgrey" alt="Freemarker Badge"/>
  <img src="https://img.shields.io/badge/Build%20Tool-Gradle-orange" alt="Gradle Badge"/>
</p>

# Cutlink
**Cutlink** is a web application designed to shorten URLs quickly and easily, while also providing detailed statistics on each shortened link's usage. With **Cutlink**, you can manage your links, gain valuable insights into who and how people access them, and generate QR codes to facilitate their distribution.

The application is designed to be intuitive and accessible, allowing any user to shorten URLs effortlessly. Additionally, **Cutlink** offers advanced tools for those who want more control and analysis of their links, such as click statistics, origin countries, browsers used, and more. It also provides the ability to generate and customize QR codes for various purposes, including web links and Wi-Fi network configurations.

## Table of Contents
- [Features](#features)
- [Application](#application)
- [Tools Used](#tools-used)
- [How to Install](#how-to-install)
- [Contributors](#contributors)
- [License](#license)
- [Contact Me](#contact-me)

## Features
- **Shorten URLs without registration:** Shorten URLs directly from the homepage without needing to log in.
- **URL Management:** Create, store, and manage shortened URLs in your account.
- **Detailed Statistics:** Access detailed statistics for your shortened URLs, including clicks, origin countries, browsers used, and more.
- **QR Codes:** Generate QR codes for URLs, web pages, Wi-Fi passwords, Wi-Fi connections, and more.
- **Page Preview:** View a preview of the page that the shortened URL points to directly from the application.

## Application
Here are some screenshots and GIFs showing the application in action:

**Sign In and Register**
<p align="left">
  <img src="https://github.com/darvybm/cutlink/blob/main/src/main/resources/publico/img/IniciarSesion.gif" alt="Sign In"/>
</p>
<p align="left">
  <img src="https://github.com/darvybm/cutlink/blob/main/src/main/resources/publico/img/Registrarse.gif" alt="Register"/>
</p>

These views allow users to access their account, manage their shortened URLs, and get detailed statistics on their usage. Registering on Cutlink offers the benefit of centralizing all activities related to shortened links, providing a more personalized and secure experience with access to a complete history of links and additional options such as customization and URL protection.

**Home Section**
<p align="left">
  <img src="https://github.com/darvybm/cutlink/blob/main/src/main/resources/publico/img/HomeGif.gif" alt="Home"/>
</p>

In the Home section, you can see a summary of your shortened URLs, including the number of accesses, the number of times each one has been shared, the number of active links, and a map showing where the majority of accesses come from.

**URLs Section**
<p align="left">
  <img src="https://github.com/darvybm/cutlink/blob/main/src/main/resources/publico/img/UrlListaGif.gif" alt="URLs List"/>
</p>

<p align="left">
  <img src="https://github.com/darvybm/cutlink/blob/main/src/main/resources/publico/img/UrlCrearGif.gif" alt="Create URL"/>
</p>

In the URLs section, you can see all your shortened URLs in a list of cards providing information about the link's name, QR code, shortened URL, and original URL. To view the statistics for a URL, click the "View Summary" button to access detailed charts and information about all accesses.

**Statistics Section**
<p align="left">
  <img src="https://github.com/darvybm/cutlink/blob/main/src/main/resources/publico/img/UrlDetallesGif.gif" alt="Statistics"/>
</p>

In the statistics section, you can view detailed information about the usage of your shortened URLs, including the number of clicks, the origin country of users, the browser they used, and much more.

**QR Codes Section**
<p align="left">
  <img src="https://github.com/user-attachments/assets/699eeca9-896e-4ba3-81f2-534524ae025f" alt="QR Codes" width="756"/>
</p>

In the QR Codes section, you can create a QR code for anything you need, including web pages, Wi-Fi passwords, or directly connecting to a Wi-Fi network. You can store files within the QR code and download them to export wherever you like.

## Tools Used
- **Language:** Java ![Java](https://img.shields.io/badge/Language-Java-blue)
- **Frameworks:** Javalin ![Javalin](https://img.shields.io/badge/Framework-Javalin-brightgreen)
- **Frontend:** Freemarker, HTML, CSS, JavaScript, Bootstrap ![Freemarker](https://img.shields.io/badge/Frontend-Freemarker-lightgrey) ![HTML](https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=html5&logoColor=white) ![CSS](https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=css3&logoColor=white) ![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=javascript&logoColor=black) ![Bootstrap](https://img.shields.io/badge/Bootstrap-563D7C?style=flat&logo=bootstrap&logoColor=white)
- **Build Tool:** Gradle ![Gradle](https://img.shields.io/badge/Build%20Tool-Gradle-02303A?style=flat&logo=gradle&logoColor=white)

## How to Install

To run the application, follow these steps:

> [!IMPORTANT]  
> **Requirements:**
> - **Java 17:** Ensure you have Java 17 installed on your system.
> - **Javalin 5.3.2:** This is included in the project's `build.gradle` file, so you do not need to install it manually.

1. **Clone this repository:**
    ```bash
    git clone https://github.com/darvybm/cutlink.git
    ```

2. **Navigate to the project directory:**
    ```bash
    cd cutlink
    ```

3. **Configure the variables in `application.properties` if needed:** 
   Here you can customize settings such as the application's port or database URL.

4. **Run the project using your preferred IDE or Gradle:**
    ```bash
    ./gradlew run
    ```

> [!NOTE]  
> In this project, redirection is used where the machine's IP address is used instead of `localhost`. Ensure you configure the IP address correctly in your development environment.

## Contributors
Here are the contributors to this project:

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
        <img src="https://github.com/christpaul01" width="100px;" alt="Christpaul01"/>
        <br />
        <sub><b>Christpaul01</b></sub>
      </a>
    </td>
  </tr>
</table>

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact Me

<p align="center">
  <a href="https://www.linkedin.com/in/darvybm" target="_blank">
    <img src="https://img.shields.io/badge/LinkedIn-@darvybm-blue?style=flat&logo=linkedin&logoColor=white" alt="LinkedIn Badge"/>
  </a>
  <a href="mailto:darvybm@gmail.com" target="_blank">
    <img src="https://img.shields.io/badge/Email-Contact%20Me-orange" alt="Email Badge"/>
  </a>
</p>
