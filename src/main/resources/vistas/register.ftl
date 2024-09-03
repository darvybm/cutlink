<!DOCTYPE html>
<html>
<head>
    <title>Inicio de Sesion</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-6SjFpD1+Al3qIKm0EUtuxiA72T04TtTujmEUklGmXkfQkLyIzgH79yv1LG4O4/J4pp/SR0xRv2fbG9YzfmS0Ig==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://kit.fontawesome.com/41bcea2ae3.js" crossorigin="anonymous"></script>
    <style>

        @import url('https://fonts.googleapis.com/css2?family=Jockey+One&family=Ubuntu:ital@1&display=swap');

        * {
            font-family: 'Jockey One', sans-serif;
        }

        .left {
            background-color: #003443;
            height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            color: white;
            width: 45%;
            float: left;
        }

        .right {
            background-color: #DDE6E9;
            height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            width: 55%;
            float: right;
        }

        .logo {
            width: 150px;
            margin-bottom: 20px;
        }

        .ilustracion {
            display: block;
            max-width: 100%;
            width: 35rem;
            padding: 2rem;
        }

        form {
            width: 80%;
        }

        input[type="text"], input[type="password"], input[type="email"] {
            background-color: #EDF7FB;
            border: none;
            border-radius: 5px;
            margin-bottom: 10px;
            width: 100%;
            padding-left: 2.8rem;
            padding-top: 1rem;
            padding-bottom: 1rem;
            color: #476F7A;
            max-width: 25rem;
            height: 3.5rem;
        }

        label {
            font-size: 1.2rem;
            color: #476F7A;
        }

        #recuerdame {
            width: 2rem;
            border: none;
        }

        button, .register-btn {
            background-color: #00BEC6;
            border: none;
            border-radius: 5px;
            color: white;
            padding: 10px 20px;
            cursor: pointer;
            margin-top: 10px;
            width: 100%;
            font-size: 1.2rem;
            max-width: 25rem;
            height: 3.5rem;
        }

        .register-btn {
            background-color: transparent;
            border: 4px solid #00BEC6;
            color: #00BEC6;
        }

        .register-btn:hover {
            background-color: #00BEC6;
            color: white;
        }

        p {
            margin-top: 4rem;
            font-size: 1.2rem;
            color: #476F7A;
        }

        .logo {
            width: 22rem;
            padding: 2rem;
        }

        .input-grupo {
            width: 100%;
            margin: auto;
            display: flex;
            align-items: center;
            position: relative;
            justify-content: center;
            max-width: 25rem;
        }

        .icon {
            font-size: 1.8rem;
            position: absolute;
            left: 10px;
            color: #476F7A;
            width: 20px; /* Ajustar el ancho de los iconos */
        }

        h4 {
            margin-top: 1rem;
            font-size: 1.2rem;
            color: #ff7274;
        }

        @media (max-width: 1080px) {
            .ilustracion {
                display: none;
            }
        }

        @media (max-width: 1080px) {
            .left {
                display: flex;
                flex-direction: row;
                align-items: center;
                justify-content: center;
                height: 20vh;
                width: 100%;
            }
            .right {
                width: 100%;
                height: 80vh;
            }
        }
    </style>
</head>
<body>
<div class="left">
    <img class="logo" src="img/logo.svg">
    <img class="ilustracion" src="img/ilustracionRegister.svg">
</div>
<div class="right text-center">
    <h1 style="color: #476F7A">Registrarse</h1>
    <form method="post" action="/register">
        <div class="input-grupo">
            <i class="fa-regular fa-user icon"></i>
            <input type="text" id="usuario" name="usuario" placeholder="Usuario" required>
        </div>
        <div class="input-grupo">
            <i class="fa-regular fa-user icon"></i>
            <input type="email" id="email" name="email" placeholder="Email" required>
        </div>
        <div class="input-grupo">
            <i class="fa-regular fa-user icon"></i>
            <input type="text" id="nombre" name="nombre" placeholder="Nombre" required>
        </div>
        <div class="input-grupo">
            <i class="fa-solid fa-lock icon"></i>
            <input type="password" id="password" name="password" placeholder="Password" required>
        </div>
        <div class="input-grupo">
            <input type="checkbox" id="recuerdame" name="recuerdame">
            <label for="recuerdame">Recuerdame</label>
        </div>
        <#if mensaje??>
            <h4>${mensaje}</h4>
        </#if>
        <button type="submit">Registrarse</button>
        <p>Ya tienes cuenta?</p>
        <button class="register-btn" type="button"  onclick="location.href = '/login'">Inicia sesion</button>
    </form>
</div>
</body>
</html>

