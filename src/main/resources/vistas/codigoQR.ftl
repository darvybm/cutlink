<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Codigos QR</title>

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
            height: 20rem;
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
            font-size: 1.8rem;
            line-height: 2rem; /* altura de una línea de texto */
            max-height: calc(2rem * 3); /* altura máxima de 2 líneas */
            width: 20rem;
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
    <h2>Codigos QR</h2>
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
        <a href="/codigoQR" class="selected">
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
                <div class="col-sm-12 col-md-6 col-lg-4 mb-4" onclick="openModal();" style="height: 20rem; cursor: pointer">
                    <div class="card" style="background-color: #00BEC6; height: 100%; display: flex; justify-content: center; align-items: center;">
                        <i class="fa-solid fa-plus" style="color: #DDE6E9; font-size: 5rem; display: flex; justify-content: center; align-items: center;"></i>
                    </div>
                </div>

            <#list codigosQR as qr>
                <div class="col-sm-12 col-md-6 col-lg-4 mb-4">
                    <div class="card">
                        <div>
                            <div id="qrcode-${qr.id}" class="codigoQR"></div>
                            <h5>${qr.url}</h5>
                        </div>
                    </div>
                </div>

                <script>
                    var qrcode = new QRCode(document.getElementById("qrcode-${qr.id}"), {

                        text: "${qr.url}",
                        width: 150,
                        height: 150,
                        colorDark : "#476F7A",
                        colorLight : "#EDF7FB",
                        correctLevel : QRCode.CorrectLevel.H
                    });
                </script>
            </#list>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="urlModal" tabindex="-1" role="dialog" aria-labelledby="urlModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <h1 style="color: #476F7A">Crear Qr code</h1>
                    <form method="post" action="/codigoQR/add">
                        <div class="input-group" style="margin-bottom: 1rem">
                            <label style="margin-right: 1rem; color: #476F7A">
                                <input type="radio" name="tipo" value="url" checked> URL
                            </label>
                            <label style="color: #476F7A">
                                <input type="radio" name="tipo" value="wifi"> WiFi
                            </label>
                        </div>

                        <div class="url-form">
                            <div class="input-grupo">
                                <i class="fa-solid fa-link icon"></i>
                                <input class="input-url" type="text" id="myURL" name="url" placeholder="youtube.com" required>
                            </div>
                        </div>

                        <div class="wifi-form" hidden="hidden">
                            <div class="input-grupo">
                                <i class="fa-solid fa-wifi icon"></i>
                                <input class="input-url" type="text" name="wifi-name" placeholder="Wifi name" >
                            </div>
                            <div class="input-grupo">
                                <i class="fa-solid fa-key icon"></i>
                                <input class="input-url" type="password" name="wifi-password" placeholder="Wifi password" >
                            </div>
                            <div class="input-grupo">
                                <i class="fa-solid fa-shield-halved icon"></i>
                                <input class="input-url" type="text" name="wifi-type" placeholder="Wifi Type" >
                            </div>
                        </div>

                        <button class="btn-url" type="submit">Crear codigoQR</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <#if totalPages gt 1>
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center text-success">
                <li class="page-item<#if pageNumber?int == 1> disabled</#if>">
                    <a class="page-link" href="/codigoQR?page=${pageNumber-1}" tabindex="-1">Anterior</a>
                </li>
                <#list 1..totalPages as page>
                    <li class="page-item<#if page == pageNumber> active</#if>">
                        <a class="page-link" href="/codigoQR?page=${page}">${page}</a>
                    </li>
                </#list>
                <li class="page-item<#if pageNumber == totalPages> disabled</#if>">
                    <a class="page-link" href="/codigoQR?page=${pageNumber+1}" aria-disabled="">Siguiente</a>
                </li>
            </ul>
        </nav>
    </#if>
</main>

<script>
    // Obtener los elementos HTML que se necesitan
    const urlForm = document.querySelector('.url-form');
    const wifiForm = document.querySelector('.wifi-form');
    const radioButtons = document.querySelectorAll('input[type="radio"][name="tipo"]');
    const urlInputs = document.querySelectorAll('.url-form input[required]');
    const wifiInputs = document.querySelectorAll('.wifi-form input[required]');

    // Agregar un controlador de eventos para cada radio buttom
    radioButtons.forEach(radio => {
        radio.addEventListener('change', () => {
            if (radio.value === 'url') {
                urlForm.removeAttribute('hidden');
                wifiForm.setAttribute('hidden', '');
                urlInputs.forEach(input => {
                    input.setAttribute('required', '');
                });
                wifiInputs.forEach(input => {
                    input.removeAttribute('required');
                });
            } else {
                wifiForm.removeAttribute('hidden');
                urlForm.setAttribute('hidden', '');
                wifiInputs.forEach(input => {
                    input.setAttribute('required', '');
                });
                urlInputs.forEach(input => {
                    input.removeAttribute('required');
                });
            }
        });
    });
</script>


<script src="/js/sidebar.js"></script>

<script>
    function openModal() {
        $('#urlModal').modal('show');
    }
</script>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>