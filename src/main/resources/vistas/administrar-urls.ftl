<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrar Urls</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/sidebar.css">
    <script src="https://kit.fontawesome.com/41bcea2ae3.js" crossorigin="anonymous"></script>
    <script src="/js/qrcode.js"></script>

    <style>
        * {
            font-family: 'Jockey One', sans-serif;
        }
        /* Barra de búsqueda */
        .search-bar {
            display: flex;
            align-items: center;
            padding: 10px;
            background-color: #f0f0f0;
            border-radius: 25px;
            margin: 20px;
            width: 90%;
        }

        .search-bar input[type="text"] {
            flex: 1;
            border: none;
            background-color: transparent;
            font-size: 16px;
            margin-left: 10px;
            outline: none;
        }

        .search-bar button {
            border: none;
            border-radius: 1rem;
            color: white;
            padding: 0.5rem 1rem;
            background-color: #00BEC6;
            cursor: pointer;
            outline: none;
        }

        /* Tabla de usuarios */
        .user-table {
            border-collapse: collapse;
            width: 90%;
            margin: 20px;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        .user-table th,
        .user-table td {
            padding: 10px;
            text-align: center;
            font-size: 14px;
        }

        .user-table th {
            background-color: #f0f0f0;
        }

        .user-table td {
            border-bottom: 1px solid #ddd;
        }

        .user-table td:last-child {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .user-table button {
            margin: 2.5px;
        }

        .user-table button.ver {
            border: none;
            border-radius: 1rem;
            color: white;
            padding: 0.5rem 1rem;
            background-color: #00BEC6;
            cursor: pointer;
            outline: none;
        }

        .user-table button.ban {
            border: none;
            border-radius: 1rem;
            color: white;
            padding: 0.5rem 1rem;
            background-color: #c74b4b;
            cursor: pointer;
            outline: none;
        }

        .user-table button.habilitar {
            border: none;
            border-radius: 1rem;
            color: white;
            padding: 0.5rem 1rem;
            background-color: #476F7A;
            cursor: pointer;
            outline: none;
        }

        .user-table button.admin {
            border: none;
            border-radius: 1rem;
            color: white;
            padding: 0.5rem 1rem;
            background-color: #476F7A;
            cursor: pointer;
            outline: none;
        }

        .user-table tr:nth-child(odd) {
            background-color: #d1d8dc;
        }

        .card-acceso {
            background-color: #EDF7FB;
            height: 12rem;
            width: 100%;
            border: none;
            padding: 1rem;
            border-radius: 1rem;
            margin-bottom: 1.2rem;
            box-shadow: -0.25rem 0.25rem 1rem rgba(0, 0, 0, 0.07);
            transition: all 0.35s ease-out;
        }

        .card-acceso:hover {
            transform: translateY(-10px) scale(1.05);
            background-color: #d1f4f6;
            box-shadow: -0.25rem 0.25rem 1.5rem rgba(0, 0, 0, 0.1);
        }

        .card-acceso i {
            color: #476F7A;
            font-size: 1.5rem;
            margin-right: 10px;
        }
        .box {
            width: 100%;
            height: 100%;
        }

        /* Define the modal */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        /* Style the modal content */
        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 60%;
        }

        /* Style the close button */
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        /* Style the header */
        .modal-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 1px solid #ccc;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }

        .modal-header h2 {
            margin: 0;
            font-size: 24px;
            font-weight: bold;
        }

        /* Style the body */
        .modal-body {
            margin-bottom: 20px;
        }

        .modal-body img {
            max-width: 100%;
            height: auto;
            margin-bottom: 10px;
        }

        .modal-body p {
            margin: 0;
            line-height: 1.5;
        }

        /* Style the footer */
        .modal-footer {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-top: 1px solid #ccc;
            padding-top: 10px;
            margin-top: 20px;
        }

        .modal-footer button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .modal-footer button:hover {
            background-color: #0069d9;
        }

        .modal-footer button:focus {
            outline: none;
            box-shadow: 0 0 0 3px rgba(0, 123, 255, 0.5);
        }

        .modal-footer p {
            margin: 0;
            line-height: 1.5;
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
    <h2>Administrar<span style="color: #00BEC6"> | Urls</span></h2>
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
        <img src="/img/logo.svg" style="width: 10rem">
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
        <a href="/ayuda">
            <div class="option">
                <i class="fas fa-question-circle" title="Ayuda"></i>
                <h4>Ayuda</h4>
            </div>
        </a>
    </div>
</div>

<main>
    <div id="myModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2 id="mytitle"></h2>
                <span class="close">&times;</span>
            </div>
            <div class="modal-body">
                <img id="myimage" src="" alt="Preview image"/>
                <p id="mydescription"></p>
            </div>
            <div class="modal-footer">
                <p id="myurl"></p>
            </div>
        </div>
    </div>

    <#if urls?has_content>
        <form method="post" action="/administrar/urls">
        <div class="search-bar">
            <i class="fas fa-search"></i>
            <input type="text" name="busqueda" placeholder="Buscar urls..." />
            <button>Buscar</button>
        </div>
        </form>
        <table class="user-table">
            <thead>
            <tr>
                <th>Titulo</th>
                <th>Url Original</th>
                <th>Hash</th>
                <th>Fecha Creacion</th>
                <th>Usuario</th>
                <th>Activo</th>
                <th>Codigo QR</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <#list urls as url>
                <tr>
                    <td>${url.titulo}</td>
                    <td>${url.urlOriginal}</td>
                    <td>${url.hash}</td>
                    <td>${url.fechaString}</td>
                    <td>${url.usuario.nombre}</td>

                    <td class="baneado">${url.activo?string}</td>
                    <td><div id="qrcode-${url.id}"></div></td>
                    <script>
                        var qrcode = new QRCode(document.getElementById("qrcode-${url.id}"), {

                            text: "${url.direccion}",
                            width: 100,
                            height: 100,
                            colorDark : "#476F7A",
                            colorLight : "#DDE6E9",
                            correctLevel : QRCode.CorrectLevel.H
                        });
                    </script>
                    <td>
                        <button class="ver" onclick="showPreview('${url.urlOriginal}')">Ver</button>
                        <#if url.activo>
                            <button class="ban"  onclick="location.href = '/administrar/habilitar/url/false/${url.id?string}'">Deshabilitar</button>
                        <#else>
                            <button class="habilitar"  onclick="location.href = '/administrar/habilitar/url/true/${url.id?string}'">Habilitar</button>
                        </#if>
                    </td>

                </tr>
            </#list>
            </tbody>
        </table>
    <#else>
        <div class="col-md-12" style="padding: 2rem">
            <div class="card-acceso text-center">
                <h1 style="font-size: 2.5rem; color: #476F7A; margin: auto">
                    No hay Urls
                </h1>
            </div>
        </div>
    </#if>

</main>
<script>
    function showPreview(url) {
        var modal = document.getElementById("myModal");
        var span = document.getElementsByClassName("close")[0];

        var data = {key: '0c2e834b39c0491fb29487f23decafa7', q: url};

        fetch('https://api.linkpreview.net', {
            method: 'POST',
            mode: 'cors',
            body: JSON.stringify(data),
        })
            .then(res => res.json())
            .then(response => {
                document.getElementById("mytitle").innerHTML = response.title;
                document.getElementById("mydescription").innerHTML = response.description;
                document.getElementById("myimage").src = response.image;
                document.getElementById("myurl").innerHTML = response.url;

                modal.style.display = "block";
            });

        span.onclick = function() {
            modal.style.display = "none";
        }

        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    }
</script>
<script src="/js/sidebar.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>