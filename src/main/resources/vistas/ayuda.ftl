<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ayuda</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/sidebar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/41bcea2ae3.js" crossorigin="anonymous"></script>
    <style>
        *{
            font-family: 'Jockey One', sans-serif;
        }
        h3 {
            color: #00BEC6;
            font-weight: bold;
            margin-top: 1rem;
            font-size: 2.2rem;
        }
        p {
            color: rgba(0, 0, 0, 0.54);
            font-size: 1.2rem;
        }

        img {
            width: 80vh;
            height: auto;
        }

        @media screen and (max-width: 1080px) {
            .header-right {
                display: none;
            }
        }

    </style>
</head>

<body id="body">

<header>
    <div class="icon__menu">
        <i class="fas fa-bars" id="btn_open"></i>
    </div>
    <h2>Home</h2>
    <#if usuario??>
        <div class="header-right">
            <a href="/logout" class="boton boton-login ml-3">Cerrar sesion</a>
        </div>
    <#else>
        <div class="header-right">
            <a href="/login" class="boton boton-login ml-3">Iniciar sesion</a>
            <a href="/register" class="boton boton-registro">Registrarse</a>
        </div>
    </#if>
</header>

<div class="menu__side" id="menu_side">
    <div class="name__page">
        <img src="img/logo.svg" style="width: 10rem">
    </div>
    <div class="options__menu">
        <a href="/home">
            <div class="option">
                <i class="fas fa-home" title="Inicio"></i>
                <h4>Home</h4>
            </div>
        </a>
        <a href="/urls">
            <div class="option">
                <i class="fas fa-link" title="Acortar URL"></i>
                <h4>URLs</h4>
            </div>
        </a>
        <a href="/codigoQR">
            <div class="option">
                <i class="fa-solid fa-qrcode"></i>
                <h4>Codigos QR</h4>
            </div>
        </a>
        <#if usuario??>
            <#if usuario.tipoUsuario == "ADMINISTRADOR">
                <a href="/administrar">
                    <div class="option">
                        <i class="fa-solid fa-lock"></i>
                        <h4>Administrar</h4>
                    </div>
                </a>
            </#if>
        </#if>
        <a href="/ayuda" class="selected">
            <div class="option">
                <i class="fas fa-question-circle" title="Ayuda"></i>
                <h4>Ayuda</h4>
            </div>
        </a>
    </div>
</div>

<main style="padding: 0 2rem;">

    <h3>Inicia session o registrate</h3>
    <p class="pregunta">Por que debería hacerlo?</p>
    <p>Inicia session o regístrate en cutlink para tener el beneficio de poder acceder a las estadísticas y resumenes de tus enlaces en cualquier momento, si creas un enlace sin estar registrado no podras acceder a su informacion nuevamente aunque esta siga funcionando</p>
    <img src="/img/IniciarSesion.gif">
    <img src="/img/Registrarse.gif">

    <h3>Seccion del home</h3>
    <p class="pregunta">Que información puedo ver en la seccion del home?</p>
    <p>En la seccion del home, puedes ver un resumen de tus URLs acortadas, incluyendo la cantidad de accesos y el número de veces que se ha compartido cada una. Tambien puedes ver algunas estadisticas rapidas sobre el uso de la aplicacion.</p>
    <img src="/img/HomeGif.gif">
    <h3>Seccion de URLs</h3>
    <p class="pregunta">Como puedo ver todas mis URLs acortadas?</p>
    <p>En la seccion de URLs, puedes ver todas tus URLs acortadas en una lista.</p>
    <img src="/img/UrlListaGif.gif">
    <img src="/img/UrlCrearGif.gif">
    <p class="pregunta">Como puedo ver las estadisticas de una URL?</p>
    <p>Para ver las estadisticas de una url puedes hacer clic en el boton de "ver resumen" para acceder a una cantidad de estadisticas, graficos e informacion acerca de todos los accesos de la aplicacion.</p>
    <img src="/img/UrlDetallesGif.gif">
    <p class="pregunta">Que tipo de estadisticas puedo ver en la seccion de estadísticas?</p>
    <p>En la seccion de estadísticas, puedes ver información detallada sobre el uso de tus URLs acortadas. Puedes ver la cantidad de clics, el pais de origen de los usuarios que hicieron clic en tus URLs, el navegador que utilizaron, y mucho mas.</p>

    <h3>Seccion de Codigos QR</h3>
    <p class="pregunta">Para que sirve esta seccion?</p>
    <p>Aqui puedes crear un codigo qr para todo lo que necesites, incluyendo páginas web, contrasenas de wifi o directamente la conexion a una red wifi, puedes almacenar archivos dentro del codigo qr y descargalos para exportar en donde quieras</p>
    <img src="/img/codigoQR.gif">

</main>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script src="/js/sidebar.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>