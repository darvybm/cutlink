<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrar Usuarios</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/sidebar.css">
    <script src="https://kit.fontawesome.com/41bcea2ae3.js" crossorigin="anonymous"></script>

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

        @media screen and (max-width: 1080px) {
            .header-right {
                display: none;
            }
            /* Tabla de usuarios */
            .user-table {
                margin: 2.5%;
            }

            .user-table th,
            .user-table td {
                padding: 5px;
                font-size: 12px;

            }

            .user-table td:last-child {
                display: block;
                text-align: center;
            }

            .user-table button {
                margin: 1px;
            }
        }

        .input-url {
            background-color: #EDF7FB;
            border: none;
            border-radius: 5px;
            margin-bottom: 10px;
            width: 100%;
            padding-left: 1rem;
            padding-top: 1rem;
            padding-bottom: 1rem;
            color: #476F7A;
            height: 3.5rem;
        }

        .input-grupo {
            width: 100%;
            display: flex;
            align-items: center;
            align-content: center;
        }

        .titulo-icono {
            font-size: 1.5rem;
            left: 10px;
            color: #476F7A;
            width: 20px; /* Ajustar el ancho de los iconos */
        }

        .titulo {
            margin-right: 1.2rem;
            margin-left: 1rem;
            color: #476F7A;
        }

        @media (max-width: 480px) {
            .user-table {
                width: 100%;
                margin: 2.5%;
            }

            .user-table th,
            .user-table td {
                font-size: 10px;
                padding: 3px;
            }

            .user-table td:last-child {
                display: block;
                text-align: center;
            }

            .user-table button {
                margin: 1px;
                font-size: 10px;
            }
        }
    </style>

</head>

<body id="body">

<header>
    <div class="icon__menu">
        <i class="fas fa-bars" id="btn_open"></i>
    </div>
    <h2>Administrar<span style="color: #00BEC6"> | ${tipoAdministracion}</span></h2>
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
    <form method="post" action="/administrar/${tipoAdministracion}">
    <div class="search-bar">
        <i class="fas fa-search"></i>
        <input type="text" name="busqueda" placeholder="Buscar ${tipoAdministracion} ..." required/>

        <button>Buscar</button>
    </div>
    </form>
    <table class="user-table">
        <thead>
        <tr>
            <th>Usuario</th>
            <th>Nombre</th>
            <th>Correo</th>
            <th>Tipo de usuario</th>
            <th>Habilitado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <#if usuarios??>
        <#list usuarios as usuario>
            <tr>
                <td>${usuario.usuario}</td>
                <td>${usuario.nombre}</td>
                <td>${usuario.correo}</td>
                <td>${usuario.tipoUsuario}</td>
                <td class="baneado">${usuario.habilitado?string}</td>
                <td>
                    <button class="ver" onclick="$('#usuariosModal-${usuario.usuario}').modal('show')">Ver</button>
                    <div class="modal fade" id="usuariosModal-${usuario.usuario}" tabindex="-1" role="dialog" aria-labelledby="usuariosModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <h1 style="color: #476F7A">Usuario</h1>
                                    <div class="input-grupo">
                                        <i class="fas fa-user titulo-icono"></i>
                                        <label class="titulo">Nombre</label>
                                        <input class="input-url" type="text" value="${usuario.nombre}" readonly>
                                    </div>
                                    <div class="input-grupo">
                                        <i class="fas fa-user-circle titulo-icono"></i>
                                        <label class="titulo">usuario</label>
                                        <input class="input-url" type="text" value="${usuario.usuario}" readonly>
                                    </div>
                                    <div class="input-grupo">
                                        <i class="fas fa-envelope titulo-icono"></i>
                                        <label class="titulo">Correo</label>
                                        <input class="input-url" type="text" value="${usuario.correo}" readonly>
                                    </div>
                                    <div class="input-grupo">
                                        <i class="fas fa-user-tag titulo-icono"></i>
                                        <label class="titulo">Tipo de usuario</label>
                                        <input class="input-url" type="text" value="${usuario.tipoUsuario}" readonly>
                                    </div>
                                    <div class="input-grupo">
                                        <i class="fas fa-globe-americas titulo-icono"></i>
                                        <label class="titulo">Pais</label>
                                        <input class="input-url" type="text" value="${usuario.getPais()}" readonly>
                                    </div>
                                    <div class="input-grupo">
                                        <i class="fas fa-link titulo-icono"></i>
                                        <label class="titulo">Enlaces activos</label>
                                        <input class="input-url" type="text" value="${usuario.getLinks()}" readonly>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <#if usuario.usuario != "admin">
                        <#if usuario.habilitado>
                            <button class="ban"  onclick="location.href = '/administrar/habilitar/usuario/false/${usuario.usuario}'">Deshabilitar</button>
                        <#else>
                            <button class="habilitar"  onclick="location.href = '/administrar/habilitar/usuario/true/${usuario.usuario}'">Habilitar</button>
                        </#if>
                    </#if>
                </td>
            </tr>
        </#list>
        <#else>
            <tr>
                <td colspan="6">No existen usuarios de este tipo!</td>
            </tr>
        </#if>
        </tbody>
    </table>
</main>

<script>

</script>

<script src="/js/sidebar.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>