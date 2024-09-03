#IGNORAR:
#IP WSL 192.168.192.1
import requests
from socket import socket, AF_INET, SOCK_DGRAM

# Clase para representar un usuario

class UrlDTO:
    def __init__(self, id, url_original, titulo, hash, fecha_creacion, direccion, activo, usuario_id, accesos, imagen_base64):
        self.imagen_base64 = imagen_base64
        self.id = id
        self.url_original = url_original
        self.titulo = titulo
        self.hash = hash
        self.fecha_creacion = fecha_creacion
        self.direccion = direccion
        self.activo = activo
        self.usuario_id = usuario_id
        self.accesos = accesos

    @classmethod
    def from_dict(cls, d):
        return cls(
            d.get('imagenBase64'),
            d.get('id'),
            d.get('urlOriginal'),
            d.get('titulo'),
            d.get('hash'),
            d.get('fechaCreacion'),
            d.get('direccion'),
            d.get('activo'),
            d.get('usuarioId'),
            d.get('accesos'),
        )




class AccesoDTO:
    def __init__(self, ip_client, pais, user_agent, navegador, plataforma, fecha_acceso, url):
        self.id = str(uuid.uuid4())
        self.ip_client = ip_client
        self.pais = pais
        self.user_agent = user_agent
        self.navegador = navegador
        self.plataforma = plataforma
        self.fecha_acceso = fecha_acceso
        self.url = url

    def to_dict(self):
        return {
            'id': self.id,
            'ipClient': self.ip_client,
            'pais': self.pais,
            'userAgent': self.user_agent,
            'navegador': self.navegador,
            'plataforma': self.plataforma,
            'fechaAcceso': self.fecha_acceso,
            'Url': self.url,
        }

    @classmethod
    def from_dict(cls, d):
        return cls(
            d.get('ipClient'),
            d.get('pais'),
            d.get('userAgent'),
            d.get('navegador'),
            d.get('plataforma'),
            d.get('fechaAcceso'),
            d.get('Url'),
        )

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

print("Bienvenido a la aplicacion de acortador de URL")
print("La direccion IP local es: " + local_ip + " desea usar esta direccion? (Y/N)")
optionDireccion = input("Select an option: ")

if optionDireccion == "N":
    local_ip = input("Ingrese la direccion IP local: ")


miToken = ""

print("Usando direccion IP: " + local_ip + " para conectarse al servidor")

#ask if the user wants to login or register
print("1. Login")
print("2. Invitado")
print("3. Exit")
option = input("Select an option: ")

if option == "1":
    # Pedir el usuario y password al usuario
    usuario = input("Ingrese su usuario: ")
    password = input("Ingrese su password: ")

    # Hacer la petición al servidor con los parámetros ingresados

    url = f"http://{local_ip}:5000/rest/login"
    params = {
        'usuario': usuario,
        'password': password
    }

    response = requests.post(url, params=params)
    if response.status_code == requests.codes.ok:
        miToken = response.text
        print(f'JWT token: {miToken}')


    else:
        print(f'Error: {response.status_code}')
        exit()
    print("1. Crear URL")
    print("2. Listar URL")
    print("3. Exit")
    option = input("Select an option: ")

    if option == "1":
        urlInput = input("Ingrese la URL: ")
        # Hacer una petición a la API /url/shorten con el JWT token
        headers = {'Authorization': f'Bearer {miToken}'}
        url = f'http://{local_ip}:5000/rest/url/shorten'
        params = {'url': urlInput}
        response = requests.get(url, headers=headers, params=params)

        if response.status_code == requests.codes.ok:
            response_dict = response.json()
            print(response_dict)
        else:
            print(f'Error: {response.status_code}')

    if option == "2":
        # Hacer una petición a la API /url/list con el JWT token
        headers = {'Authorization': f'Bearer {miToken}'}
        urlOPT2 = f'http://{local_ip}:5000/rest/url/list'

        response = requests.get(urlOPT2, headers=headers)
        print(response.status_code)
        urls_dto = []

        #check if response.json is a list
        if isinstance(response.json(), list):
            print(response.json())
        else:
            print('Error: La lista esta vacia')
    if option == "3":
        exit()
    else:
        exit()

if option == "2":
    print("1. Crear URL")
    print("2. Exit")
    option = input("Select an option: ")
    if option == "1":
        urlInput = input("Ingrese la URL: ")
        # Hacer una petición a la API /url/shorten sin el JWT token
        url = f'http://{local_ip}:5000/rest/url/shorten'
        params = {'url': urlInput}
        response = requests.get(url, params=params)
        if response.status_code == requests.codes.ok:
            response_dict = response.json()
            print(response.json())
        else:
            print(f'Error: {response.status_code}')
    if option == "2":
        exit()
if option == "2":
    exit()