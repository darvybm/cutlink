<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
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

        .card-header {
            display: flex;
            flex-wrap: wrap;
            width: 100%;
            padding: 2rem;
        }

        .codigoQR {
            width: 100px;
            height: 100px;
            margin-right: 10px;
            background-color: #eee;
        }

        .url-details {
            flex-grow: 1;
        }

        .url-details h2 {
            margin-top: 0.5rem;
            text-decoration: none;
            color: #476F7A;
            font-size: 2rem;
            line-height: 1.5rem; /* altura de una línea de texto */
            max-height: calc(1.5rem * 1); /* altura máxima de 2 líneas */
            overflow: hidden;
        }

        .url-details .url-acortada {
            margin-bottom: 5px;
            text-decoration: none;
            color: #476F7A;
            font-size: 1.2rem;
            line-height: 1.5rem; /* altura de una línea de texto */
            max-height: calc(1.5rem * 1); /* altura máxima de 2 líneas */
            overflow: hidden;
        }

        .url-details .url-original {
            margin-bottom: 5px;
            text-decoration: none;
            color: #00BEC6;
            font-size: 1.2rem;
            line-height: 1.5rem; /* altura de una línea de texto */
            max-height: calc(1.5rem * 1); /* altura máxima de 2 líneas */
            overflow: hidden;
        }

        .url-details .url-acortada a,
        .url-details .url-original a {
            text-decoration: none;
            color: inherit;
        }

        .cards-container {
            margin-top: 1rem;
            width: 100%;
            padding: 2rem;
        }

        .preview-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 5rem;
        }

        .preview {
            background-color: #476F7A;
            font-size: 1.5rem;
            border-radius: 1rem;
            border: none;
            padding: 0.5rem 1rem;
            text-align: center;
            text-decoration: none;
            color: #EDF7FB;
            transition: 0.5s ease-out;
            width: 15rem;
            height: 100%;
        }


        .preview:hover {
            background-color: #00BEC6;
        }
        .preview:focus {
            background-color: #00BEC6;
        }

        .card {
            width: 100%;
            height: 16rem;
            background-color: #EDF7FB;
            border-radius: 1rem;
            border: none;
            box-shadow: -0.5rem 0.5rem 0.5rem rgba(0, 0, 0, 0.2);
            align-items: center;
            padding: 1rem;
        }

        .card-title {
            color: #476F7A;
            margin-bottom: 0.5rem;
            text-align: center;
            height: 2rem;
        }

        .card .card-title {
            margin-bottom: 1rem
        }

        .card .card-body {
            color: #00BEC6;
            font-weight: bold;
            font-size: 4rem;
            margin-top: 0.5rem;
        }

        .card .card-body-fecha {
            color: #00BEC6;
            font-size: 3rem;
            margin-top: 1.5rem;
            text-align: center;
        }

        .card .card-body-pais {
            color: #00BEC6;
            font-size: 3rem;
            margin-top: 1.5rem;
            text-align: center;
        }

        .charts {
            width: 100%;
            padding: 2rem;
        }

        .chart-container {
            width: 100%;
            height: 22rem;
            position: relative;
            background-color: #EDF7FB;
            margin-bottom: 1rem;
            padding: 2rem;
            border-radius: 1rem;
            border: none;
            box-shadow: -0.5rem 0.5rem 0.5rem rgba(0, 0, 0, 0.2);
        }

        #myChart {
            height: 80% !important;
            width: auto !important;
        }

        #myChart-2 {
            width: 100%;
            height: 100%;
        }

        #myChart-3 {
            width: 100%;
        }

        .accesos-container {
            height: 100%;
            width: 100%;
            margin-top: 1rem;
            padding: 0 2rem;
        }

        .accesos-title {
            font-size: 4rem;
            margin-bottom: 1.2rem;
            color: #476F7A;
        }

        .card-acceso {
            background-color: #EDF7FB;
            height: 14rem;
            width: 100%;
            border: none;
            border-radius: 1rem;
            margin-bottom: 1.2rem;
            padding: 1rem 1rem 0.5rem;
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

        .card-acceso span {
            font-size: 1.5rem;
            color: #476F7A;
        }

        .plataforma, .fecha-hora{
            color: #00BEC6 !important;
        }

        .box {
            width: 100%;
            height: 100%;
        }

        #myimage {
            width: 100%;
            height: 100%;
            object-fit: cover;
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
    <div class="card-header">
        <div id="qrcode-${url.id}" class="codigoQR"></div>
        <div class="url-details">
            <h2>${url.titulo}</h2>
            <div class="url-acortada">
                <a href="${url.direccion}">
                    <p>${url.direccion}</p>
                </a>
            </div>
            <div class="url-original">
                <a href="${url.direccion}">
                    <p>${url.urlOriginal}</p>
                </a>
            </div>
        </div>
        <div class="preview-container">
            <button class="preview" type="button" data-toggle="modal" data-target="#urlModal">Ver Preview</button>
        </div>
    </div>

    <div class="cards-container">
        <div class="row">
            <div class="col">

                <div class="card">
                    <h2 class="card-title">Cant. Accesos</h2>
                    <p class="card-body">${cantAccesos}</p>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <h2 class="card-title">Pais Con Mas Visitas</h2>
                    <p class="card-body-pais">
                    <#if paisMasVisitado??>
                    ${paisMasVisitado}
                    <#else>
                    Ninguno
                    </#if>
                    </p>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <#if ultimoAcceso??>
                    <h2 class="card-title">Ultimo Acceso</h2>
                    <p class="card-body-fecha">${ultimoAcceso}</p>
                    <#else>
                    <h2 class="card-title">Fecha Creacion</h2>
                    <p class="card-body-fecha">${url.fechaString}</p>
                    </#if>
                </div>
            </div>
        </div>
    </div>


    <div class="charts">
        <div class="row">
            <div class="col">
                <div class="chart-container" id="chart-container-1">
                    <h2 class="card-title">Plataformas populares</h2>
                    <canvas id="myChart"></canvas>
                </div>
            </div>
            <div class="col">
                <div class="chart-container" id="chart-container-2">
                    <h2 class="card-title">Navegadores populares</h2>
                    <canvas id="myChart-2"></canvas>
                </div>
            </div>
        </div>
        <div class="chart-container" id="chart-container-3" style="height: auto !important;">
            <h2 class="card-title">Cant. Accesos x hora</h2>
            <canvas id="myChart-3"></canvas>
        </div>
    </div>

    <div class="accesos-container">
        <h2 class="accesos-title">Accesos</h2>
        <div class="container">
            <div class="row">
                <#if accesos??>
                <#list accesos as acceso>
                <div class="col-md-6">
                    <div class="card-acceso">
                        <div class="ip">
                            <i class="fas fa-laptop"></i>
                            <span>${acceso.ipClient}</span>
                        </div>
                        <div class="navegador">
                            <i class="fab fa-windows"></i>
                            <span>${acceso.navegador}<span class="plataforma"> ${acceso.plataforma}</span></span>
                        </div>
                        <div class="pais">
                            <i class="fas fa-globe-americas"></i>
                            <span>${acceso.pais}</span>
                        </div>
                        <div class="fecha-hora">
                            <i class="far fa-calendar-alt"></i>
                            <span class="fecha-hora">${acceso.fechaString}</span>
                        </div>
                    </div>
                </div>
                <#if (acceso?index + 1) % 2 == 0>
            </div><div class="row">
                </#if>
                </#list>
                <#else>
                <div class="col-md-12">
                    <div class="card-acceso">
                        <div class="ip">
                            <i class="fas fa-laptop"></i>
                            <span>
                                No hay accesos
                            </span>
                        </div>
                    </div>
                </div>
                </#if>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="urlModal" tabindex="-1" role="dialog" aria-labelledby="urlModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="box">
                        <img id="myimage" src="">
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>

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



<script>
    const previewButton = document.querySelector('.preview');
    const previewBox = document.querySelector('.box');
    const previewImage = document.querySelector('#myimage');

    previewButton.addEventListener('click', () => {
        const url = '${url.urlOriginal}';
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
        $('#urlModal').modal('show');
    });

</script>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>
    var ctx = document.getElementById("myChart").getContext("2d");
    var myChart = new Chart(ctx, {
        type: "pie",
        data: {
            labels: [
                <#list plataformasLabels as p>
                "${p}",
                </#list>
            ],
            datasets: [{
                data: [
                    <#list plataformasData as d>
                    ${d},
                    </#list>
                ],
                backgroundColor: [
                    "#00BEC6",
                    "#FDB45C",
                    "#46BFBD",
                    "#949FB1",
                    "#4D5360"
                ],
                borderColor: [
                    "#FFFFFF",
                    "#FFFFFF",
                    "#FFFFFF",
                    "#FFFFFF",
                    "#FFFFFF"
                ],
                borderRadius: 10
            }]
        },
    });


    var ctx2 = document.getElementById("myChart-2").getContext("2d");
    var myChart2 = new Chart(ctx2, {
        type: "bar",
        data: {
            labels: [
                <#list navegadoresLabels as l>
                "${l}",
                </#list>
            ],
            datasets: [{
                label: "Navegadores mas populares",
                data: [
                    <#list navegadoresData as d>
                    ${d},
                    </#list>
                ],
                backgroundColor: [
                    "#00BEC6",
                ],
                borderColor: [
                    "#00BEC6",
                ],
                borderRadius: 10,
                barPercentage: 0.5
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    gridLines: {
                        display: false
                    },
                    ticks: {
                        beginAtZero: true
                    }
                }],
                xAxes: [{
                    gridLines: {
                        display: false
                    }
                }]
            }
        }
    });

    // Obtener el elemento canvas y el contexto 2D
    const canvas = document.getElementById('myChart-3');
    const ctx3 = canvas.getContext('2d');

    const config = {
        type: 'line',
        data: {
            labels: [
                <#list horasLabels as l>
                "${l}",
                </#list>
            ],
            datasets: [{
                label: 'Cantidad de accesos',
                data: [
                    <#list accesosPorHora as d>
                    ${d},
                    </#list>
                ],
                fill: false,
                borderColor: 'rgb(75, 192, 192)',
                borderWidth: 3,
                tension: 0.1,
                pointStyle: 'circle',
                pointRadius: 10,
                pointHoverRadius: 15
            }]
        },
        options: {
            responsive: true
        }
    };

    // Crear el gráfico
    const chart3 = new Chart(ctx3, config);

</script>

<script src="/js/sidebar.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>