<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>URLs</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/sidebar.css">
    <script src="https://kit.fontawesome.com/41bcea2ae3.js" crossorigin="anonymous"></script>
    <script src="/js/qrcode.js"></script>
    <style>
        * {
            font-family: 'Jockey One', sans-serif;
        }

        .codigoQR {
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 1rem 0;
            width: 100%;
        }

        .card {
            display: flex;
            background-color: #EDF7FB;
            height: 22.5rem;
            padding: 1rem;
            border-radius: 2rem;
            border: none;
            width: 100%;
            align-items: center;
            transition: all 0.3s ease-in-out;
        }

        .card:hover {
            transform: translateY(-10px) scale(1.05);
            box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
            background-color: #d1f4f6;
        }

        .card h5 {
            text-decoration: none;
            text-align: center;
            color: #476F7A;
            font-size: 2rem;
            line-height: 1.5rem; /* altura de una línea de texto */
            max-height: calc(1.5rem * 2); /* altura máxima de 2 líneas */
            overflow: hidden;
            width: 20rem;
        }

        .btn-resumen {
            background-color: #00BEC6;
            font-size: 1.5rem;
            border-radius: 1.5rem;
            border: none;
            padding: 0.5rem 1rem;
            text-align: center;
            text-decoration: none;
            color: #EDF7FB;
            width: 10rem;
            transition: all 0.3s ease-in-out;
        }

        .btn-resumen:hover {
            width: 12rem;
        }

        .url-acortada {
            text-decoration: none;
            text-align: center;
            color: #7B8083;
            font-size: 1rem;
            line-height: 1.5rem; /* altura de una línea de texto */
            max-height: calc(1.5rem * 1);
            overflow: hidden;
            margin: 0;
        }

        .url-original {
            text-decoration: none;
            text-align: center;
            color: rgba(0, 52, 67, 0.7);
            font-size: 1rem;
            line-height: 1.5rem; /* altura de una línea de texto */
            max-height: calc(1.5rem * 2);
            overflow: hidden;
        }

        a {
            text-decoration: none;
        }

        .input-url {
            background-color: #EDF7FB;
            border: none;
            border-radius: 5px;
            margin-bottom: 10px;
            width: 100%;
            padding-left: 3rem;
            padding-top: 1rem;
            padding-bottom: 1rem;
            color: #476F7A;
            height: 3.5rem;
        }

        .btn-url {
            background-color: #00BEC6;
            border: none;
            border-radius: 1rem;
            color: white;
            padding: 1rem 0;
            cursor: pointer;
            margin-top: 10px;
            width: 100%;
            font-size: 1.2rem;
            height: 3.5rem;
        }

        .input-grupo {
            width: 100%;
            margin: auto;
            display: flex;
            align-items: center;
            position: relative;
            justify-content: center;
        }

        .icon {
            font-size: 1.5rem;
            position: absolute;
            left: 10px;
            color: #476F7A;
            width: 20px; /* Ajustar el ancho de los iconos */
        }

        .btn-preview {
            background-color: #00BEC6;
            color: #ffffff;
            font-size: 1.5rem;
            padding: 0.5rem;
            border: none;
            border-radius: 0.4rem;
            margin-left: 10px;
            height: 3.5rem;
        }

        .box {
            width: 100%;
            height: 15rem;
        }

        #myimage {
            height: 100%;
            width: 100%;
            object-fit: cover;
        }

        #urlModal {
            border-radius: 1rem;
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
    <h2>Links</h2>
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
        <a href="/urls" class="selected">
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
    <div class="container" style="padding-top: 2rem">
        <div class="row d-flex flex-wrap">

            <#if urls??>

                <div class="col-sm-12 col-md-6 col-lg-4 mb-4" onclick="openModal();" style="height: 22.5rem; cursor: pointer">
                    <div class="card" style="background-color: #00BEC6; height: 100%; display: flex; justify-content: center; align-items: center;">
                        <i class="fa-solid fa-plus" style="color: #DDE6E9; font-size: 5rem; display: flex; justify-content: center; align-items: center;"></i>
                    </div>
                </div>

            <#list urls as url>
                <div class="col-sm-12 col-md-6 col-lg-4 mb-4">
                    <div class="card">
                        <div onclick="/id/${url.hash}">
                            <div id="qrcode-${url.id}" class="codigoQR"></div>
                            <h5 href="/${url.hash}">${url.titulo}</h5>
                        </div>
                        <a href="/${url.hash}">
                            <p class="url-acortada">${url.direccion}</p>
                        </a>
                        <a href="/${url.hash}">
                            <p class="url-original">${url.urlOriginal}</p>
                        </a>
                        <a href="/urls/resumen/${url.hash}" class="btn-resumen">Resumen</a>
                    </div>
                </div>

                <script>
                    var qrcode = new QRCode(document.getElementById("qrcode-${url.id}"), {

                        text: "${url.direccion}",
                        width: 100,
                        height: 100,
                        colorDark : "#476F7A",
                        colorLight : "#EDF7FB",
                        correctLevel : QRCode.CorrectLevel.H
                    });
                </script>
            </#list>

            <#else>
                <div class="col-sm-12 col-md-6 col-lg-4 mb-4">
                    <div class="card text-center align-items-center" style="background-color: #00BEC6; align-content: center; height: 100%">
                        <i class="fa-solid fa-plus" style="color: #DDE6E9; font-size: 5rem"></i>
                        <h5>No hay URLs</h5>
                    </div>
                </div>
            </#if>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="urlModal" tabindex="-1" role="dialog" aria-labelledby="urlModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <h1 style="color: #476F7A">Crear Url</h1>
                    <form method="post" action="/urls/add">
                        <div class="input-grupo">
                            <i class="fa-solid fa-heading icon"></i>
                            <input class="input-url" type="text" id="titulo" name="titulo" placeholder="Nombre - Titulo">
                        </div>
                        <div class="input-grupo">
                            <i class="fa-solid fa-link icon"></i>
                            <input class="input-url" type="text" id="myURL" name="myURL" placeholder="youtube.com" required>
                            <button type="button" class="btn-preview">
                                <i class="fa-solid fa-eye"></i>
                            </button>
                        </div>
                        <div class="box" hidden="hidden">
                            <img id="myimage" src="">
                        </div>
                        <button class="btn-url" type="submit">Crear URL</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <#if totalPages gt 1>
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center text-success">
                <li class="page-item<#if pageNumber?int == 1> disabled</#if>">
                    <a class="page-link" href="/urls?page=${pageNumber-1}" tabindex="-1">Anterior</a>
                </li>
                <#list 1..totalPages as page>
                    <li class="page-item<#if page == pageNumber> active</#if>">
                        <a class="page-link" href="/urls?page=${page}">${page}</a>
                    </li>
                </#list>
                <li class="page-item<#if pageNumber == totalPages> disabled</#if>">
                    <a class="page-link" href="/urls?page=${pageNumber+1}" aria-disabled="">Siguiente</a>
                </li>
            </ul>
        </nav>
    </#if>
</main>

<script src="/js/sidebar.js"></script>

<script>
    function openModal() {
        $('#urlModal').modal('show');
    }
</script>

<script>
    const previewButton = document.querySelector('.btn-preview');
    const previewBox = document.querySelector('.box');
    const previewImage = document.querySelector('#myimage');

    previewButton.addEventListener('click', () => {
        const url = document.getElementById('myURL').value;
        const data = { key: '0c2e834b39c0491fb29487f23decafa7', q: url };

        fetch('https://api.linkpreview.net', {
            method: 'POST',
            mode: 'cors',
            body: JSON.stringify(data),
        })
            .then(res => res.json())
            .then(response => {
                previewBox.removeAttribute('hidden');
                previewImage.src = response.image;
            });
    });
</script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>