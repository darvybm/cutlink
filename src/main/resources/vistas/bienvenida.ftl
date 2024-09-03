<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Bienvenido</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>

        @import url('https://fonts.googleapis.com/css2?family=Jockey+One&family=Ubuntu:ital@1&display=swap');

        .navbar {
            background-color: #003443;
        }
        .box-presentation {
            background-color: #003443;
        }
        .box-presentation form {
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
        }

        .box-presentation .input-group {
            width: 80%;
            height: 3rem;
            margin: 0 auto;
        }

        .box-presentation .input-group .btn-crear {
            background-color: #00BEC6;
            font-size: 1.5rem;
            border-radius: 1.5rem;
            border: none;
            font-family: 'Jockey One', sans-serif;
            padding: 0.5rem 1rem;
            margin-left: 0.4rem;
            text-align: center;
            text-decoration: none;
            color: #003443;
            width: 8rem;
        }

        .box-presentation .input-group .btn-crear:hover {
            background-color: #00eff9;
            animation: glow 1s ease-out infinite;
        }

        .btn-crear:focus {
            outline: none;
        }

        .boton {
            font-size: 1.2rem;
            border-radius: 25px;
            padding: 0.25rem 1rem;
            text-align: center;
            text-decoration: none;
            margin: 0.2rem;
            width: 150px;
            border: 1.5px solid #00BEC6;
            font-family: 'Jockey One', sans-serif;
            transition: all 0.1s ease-out;
            height: 2.5rem;
        }

        .boton-registro {
            background-color: #00BEC6;
            color: #003443!important;
        }
        .boton-registro:hover {
            background-color: #00eff9;
        }

        .boton-administrar {
            background-color: #00BEC6;
            color: #003443!important;
            transition: box-shadow 0.3s ease-in-out;
        }

        .boton-administrar:hover {
            background-color: #00eff9;
            animation: glow 1s ease-out infinite;
        }

        @keyframes glow {
            0% {
                box-shadow: 0 0 0 0 rgba(0, 190, 198, 0.7);
            }
            100% {
                box-shadow: 0 0 0 20px rgba(0, 190, 198, 0);
            }
        }


        .boton-login {
            background-color: transparent;
            color: #00BEC6!important;
        }
        .boton-login:hover {
            color: #00eff9!important;
            border: #00eff9 1.5px solid;
        }

        body {
            background-color: #DDE6E9;
        }

        .card {
            height: 16.5rem;
            border: 1px solid #003443;
            border-radius: 10px;
            background-color: #EDF7FB;
            transition: all 0.3s ease-in-out;
        }

        .card:hover {
            border: 2px dashed #003443;
            transform: translateY(-5px) scale(1.05);
            box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
            background-color: #d1f4f6;
            transition: all 0.3s ease-in-out;
        }

        .card .card-icon {
            width: 6rem;
            height: 6rem;
            object-fit: scale-down;

        }

        .card .card-title {
            font-size: 2.2rem;
            text-align: center;
            margin-top: 1rem;
            padding: 20px;
            color: rgba(0, 52, 67, 0.7);
            font-family: 'Jockey One', sans-serif;
            transition: 0.1s ease-out;
        }

    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <a class="navbar-brand" href="#"><img src="/img/logo.svg" style="width: 10rem"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <#if usuario ??>
                    <li class="nav-item">
                        <a href="/urls" class="boton boton-administrar nav-link">Ver enlaces</a>
                    </li>
                    <#--<li class="nav-item">
                        <a href="/urls/add" class="boton boton-administrar nav-link">Mis enlace</a>
                    </li>-->
                    <li class="nav-item">
                        <a href="/logout" class="boton boton-login nav-link">Cerrar sesion</a>
                    </li>
                <#else>
                <li class="nav-item">
                    <a href="/register" class="boton boton-registro nav-link">Registrarse</a>
                </li>
                <li class="nav-item">
                    <a href="/login" class="boton boton-login nav-link">Iniciar sesion</a>
                </li>
                </#if>
            </ul>
        </div>
    </div>
</nav>

<div class="box-presentation d-flex justify-content-center align-items-center" style="height:25rem">
    <div class="container text-center">
        <img src="img/logo.svg" style="width: 20rem">
        <form class="form-inline mt-4" action="/urls/add" method="post">
            <div class="input-group">
                <input type="text" class="form-control" name="myURL" placeholder="https://www.amazon.com/s?__mk_es_US=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=3STVVHL9YU4JY&k=melodica&ref=glow_cls&refresh=1&sprefix=melodic%2Caps%2C267" style="background-color: #DDE6E9; border-radius: 1.5rem; border: none";>
                <button type="submit" class="btn-crear">Crear</button>
            </div>
        </form>
    </div>
</div>

<!-- Grid of cards -->
<div class="container text-center">
    <div class="row mt-4 text-center">
        <div class="col-12 col-md-6 col-lg-4 mb-4">
            <div class="card">
                <div class="card-body text-center">
                    <img src="img/tijerasIcon.svg" class="card-icon" alt="">
                    <h5 class="card-title">Acorta todos tus enlaces gratis</h5>
                </div>
            </div>
        </div>
        <div class="col-12 col-md-6 col-lg-4 mb-4">
            <div class="card">
                <div class="card-body text-center">
                    <img src="img/QRIcon.svg" class="card-icon" alt="">
                    <h5 class="card-title">Adquiere un codigo QR para tus enlaces</h5>
                </div>
            </div>
        </div>
        <div class="col-12 col-md-6 col-lg-4 mb-4">
            <div class="card">
                <div class="card-body text-center">
                    <img src="img/chartIcon.svg" class="card-icon" alt="">
                    <h5 class="card-title">Mira todas las estadisticas</h5>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>

