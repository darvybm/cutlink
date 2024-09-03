<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrar</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/sidebar.css">
    <script src="https://kit.fontawesome.com/41bcea2ae3.js" crossorigin="anonymous"></script>

    <style>
        * {
            font-family: 'Jockey One', sans-serif;
        }
        .cards-container {
            margin-top: 1rem;
            width: 100%;
            padding: 2rem;
        }

        .card {
            width: 100%;
            height: 14rem;
            background-color: #EDF7FB;
            border-radius: 1rem;
            border: none;
            box-shadow: -0.5rem 0.5rem 0.5rem rgba(0, 0, 0, 0.2);
            align-items: center;
            padding: 0.2rem;
            margin: 1rem;
        }

        .card .card-title {
            color: #476F7A;
            margin-top: 1rem;
            text-align: center;
            height: 2rem;
        }

        .card .card-body {
            color: #00BEC6;
            font-weight: bold;
            font-size: 2rem;
            margin-top: 0.5rem;
        }

        .buttons-container {
            padding: 0 2rem;
        }

        .card-button {
            background-color: #00BEC6;
            color: #DDE6E9;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            transition: 0.3s ease-in-out;
            justify-content: center;
            align-items: center;
            height: 10rem;
            cursor: pointer;
        }

        .card-button:hover {
            transform: translateY(-5%) scale(1);
            background-color: #009ca2;
            box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
        }

        .card-button i {
            margin-right: 1rem;
        }

        .card-button .card-text {
            font-size: 1.5rem;
            font-weight: bold;
            vertical-align: middle;
        }

        .buttom-body {
            align-items: center;
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
    <h2>Administrar</h2>
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
        <a href="/ayuda">
            <div class="option">
                <i class="fas fa-question-circle" title="Ayuda"></i>
                <h4>Ayuda</h4>
            </div>
        </a>
    </div>
</div>

<main>
    <div class="cards-container">
        <div class="row">
            <div class="col">
                <div class="card">
                    <h2 class="card-title">Cant. Usuarios</h2>
                    <p class="card-body">${cantUsuarios}</p>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <h2 class="card-title">Urls registradas</h2>
                    <p class="card-body">
                        ${cantUrls}
                    </p>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <h2 class="card-title">Accesos registrados</h2>
                    <p class="card-body">${cantAccesos}</p>
                </div>
            </div>
        </div>
    </div>

    <div class="buttons-container">
        <div class="row">
            <a href="/administrar/Administradores" class="col-sm-4">
                <div class="card card-button">
                    <div class="buttom-body">
                        <i class="fas fa-users fa-3x"></i>
                        <span class="card-text">Administradores</span>
                    </div>
                </div>
            </a>

            <a href="/administrar/Clientes" class="col-sm-4">
                <div class="card card-button">
                    <div class="buttom-body">
                        <i class="fas fa-users fa-3x"></i>
                        <span class="card-text">Clientes</span>
                    </div>
                </div>
            </a>

            <a href="/administrar/urls" class="col-sm-4">
                <div class="card card-button">
                    <div class="buttom-body">
                        <i class="fas fa-link fa-3x"></i>
                        <span class="card-text">Urls</span>
                    </div>
                </div>
            </a>
        </div>
    </div>
</main>

<script>

</script>

<script src="/js/sidebar.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>