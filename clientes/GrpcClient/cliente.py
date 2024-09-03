import grpc
import json
import AcortadorRn_pb2
import AcortadorRn_pb2_grpc


def imprimir_menu():
    print("Seleccione una opción:")
    print("1. Obtener listado de URLs acortadas de un usuario")
    print("2. Acortar una URL")
    print("3. Salir")
    opcion = int(input("Opción: "))
    return opcion


def obtener_urls_usuario(stub):
    username = input("Ingrese el nombre de usuario: ")
    respuesta = stub.getUrlsUsuario(AcortadorRn_pb2.RequestUsuario(username=username))
    print("\n\n\n")
    print(f"Listado de URLs acortadas del usuario {username}: ")
    print(json.dumps(json.loads(respuesta.jsonUrls), indent=4))
    print("\n\n\n")


def acortar_url(stub):
    url_original = input("Ingrese la URL que desea acortar: ")
    acortarUrl = stub.acortarUrl(AcortadorRn_pb2.RequestUrl(UrlOriginal=url_original))
    print("\n\n\n")
    print("Url acortada: ")
    print(json.dumps(json.loads(acortarUrl.jsonUrl), indent=4))
    print("\n\n\n")


def llamada_servicio():
    # Abriendo el canal para el servidor
    channel = grpc.insecure_channel('localhost:50051')
    # Obteniendo la referencia del servicio.
    stub = AcortadorRn_pb2_grpc.AcortadorServiceStub(channel)

    while True:
        opcion = imprimir_menu()
        if opcion == 1:
            obtener_urls_usuario(stub)
        elif opcion == 2:
            acortar_url(stub)
        elif opcion == 3:
            break
        else:
            print("Opción no válida. Intente de nuevo.\n\n\n")


llamada_servicio()
