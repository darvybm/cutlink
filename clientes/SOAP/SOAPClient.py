from zeep import Client
from socket import socket, AF_INET, SOCK_DGRAM

# Función para obtener la dirección IP local
def get_local_ip():
    try:
        s = socket(AF_INET, SOCK_DGRAM)
        s.connect(("8.8.8.8", 80))
        return s.getsockname()[0]
    except Exception as e:
        print(f"Error: Failed to get local IP address ({e})")
        return None

# Obtener la dirección IP local
local_ip = get_local_ip()

# Si se pudo obtener la dirección IP local, continuar con la ejecución del código
if local_ip is not None:
    # Preguntar al usuario qué acción desea realizar
    opcion = None
    while opcion != "0":
        print("Seleccione una opción:")
        print("1. Crear usuario")
        print("2. Crear URL con usuario existente")
        print("3. Listar URL de usuario")
        print("4. Crear URL como Invitado")

        print("0. Salir")
        opcion = input("Opción: ")

        # Si el usuario selecciona la opción 1, crear un usuario y una URL
        if opcion == "1":
            # Obtener la URL y el nombre de usuario SOAP a través de prompts
            usernameSOAP = input("Ingrese el nombre de usuario SOAP: ")
            password = input("Ingrese la Contrasena del Usuario SOAP: ")

            # Creación del cliente para el servicio web SOAP usando el URL WSDL
            try:
                # Crear el cliente para el servicio de usuarios
                clientUsuario = Client(f"http://{local_ip}:5000/wsUsuario?wsdl")
                # Llamar el método crearUsuario del servicio web
                result = clientUsuario.service.crearUsuario(usernameSOAP, 'ClienteSOAP', f"{usernameSOAP}@gmail.com", password)
                print('Usuario Creado: ')
                print(result)
                clientUsuario.clear()
            except Exception as e:
                print("")

        # Si el usuario selecciona la opción 2, crear una URL con un usuario existente
        elif opcion == "2":
            # Obtener la URL y el nombre de usuario SOAP a través de prompts
            usernameSOAP = input("Ingrese el nombre de usuario SOAP: ")
            password = input("Ingrese la Contrasena del Usuario SOAP: ")
            url = input("Ingrese la URL que desea crear: ")


            # Ensure that the username is not empty
            if not usernameSOAP.strip():
                print("Error: El nombre de usuario SOAP no puede estar vacío")
            else:
                try:
                    # Crear el cliente para el servicio de URLs
                    clientURL = Client(f"http://{local_ip}:5000/wsURL?wsdl")

                    # Crear la URL ingresada por el usuario
                    responseURL = clientURL.service.crearURL(url, usernameSOAP, password)
                    print(responseURL)
                    clientURL.clear()

                except Exception as e:
                    print("")

        # Si el usuario selecciona la opción 2, crear una URL con un usuario existente
        elif opcion == "3":
            # Obtener la URL y el nombre de usuario SOAP a través de prompts
            usernameSOAP = input("Ingrese el nombre de usuario SOAP: ")
            password = input("Ingrese la Contrasena del Usuario SOAP: ")

            # Creación del cliente para el servicio web SOAP usando el URL WSDL
            try:
                # Crear el cliente para el servicio de URLs
                clientURL = Client(f"http://{local_ip}:5000/wsURL?wsdl")
                # Obtener las URLs del usuario, si existe
                result = clientURL.service.getURLSFromUser(usernameSOAP, password)
                if not result:
                    print('Credenciales Incorectos!')
                print(result)
                # Impresión de la respuesta
                print(responseURL)
                clientURL.clear()
            except Exception as e:
                print("")

        # Si el usuario selecciona la opción 3, crear una URL por el Invitado
        elif opcion == "4":
            # Obtener la URL y el nombre de usuario SOAP a través de prompts
            url = input("Ingrese la URL que desea crear: ")

            # Creación del cliente para el servicio web SOAP usando el URL WSDL
            try:
                # Crear el cliente para el servicio de URLs
                clientURL = Client(f"http://{local_ip}:5000/wsURL?wsdl")

                # Crear la URL ingresada por el Invitado
                responseURL = clientURL.service.crearURL(url, 'Invitado', '')

                # Impresión de la respuesta
                print(responseURL)
                clientURL.clear()
            except Exception as e:
                print("")


        # Si el usuario ingresa una opción no válida, mostrar un mensaje de error
        else:
            print("Error: Opción no válida")

else:
    print("Error: No se pudo obtener direccion IP")