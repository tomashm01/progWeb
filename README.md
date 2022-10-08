# Práctica 1 - Iniciación a Java
Práctica 1 de Programación Web de 3 Ing.Informática curso 2022/2023.
Trabajo realizado por:
- [Tomás Hidalgo Martín](https://github.com/tomashm01)
- [Álvaro Pino Mérida](https://github.com/02Alvaro)
- [Mateo Fortea Dugo](https://github.com/mfortea)
- [Juan Higuera Mohedano](https://github.com/juannhm02)

La versión utilizada para compilar las clases es la 55.0(JRE 11). Si se usa una superior no compila correctamente.
Para ejecutar el fichero se debe usar:

```bash
java -jar practica1.jar
```

## Ficheros de datos
En estos ficheros binarios se almacenan los datos de los usuarios y los mensajes. Como son binarios no pueden leerse con un editor de texto.

datos/usuarios.txt
datos/karts.txt
datos/circuitos.txt
datos/bonos.txt
datos/reservas.txt

### Modelos
Las siguientes entidades se han incluido en el proyecto pedido.

#### Karts
Todos los karts que están en todas las pistas.
- id (PK)
- isAdult
- estado
- **pista.id**

#### Pista
- id
- nombre
- isAvailable
- dificultad
- maxKarts

#### Usuario
- id
- nombreApellidos
- fechaNacimiento;
- fechaInscipcion
- correo

#### Reserva
- id
- idUser
- precio
- fecha
- minutos
- idPista
- descuento

#### Bono
- id
- vector<int> bonosUser

### Handlers

#### CircuitHandler
Manejador de karts y pistas. Se encarga de la gestión de los karts y pistas.

#### UserHandler
Manejador de usuarios. Se encarga de la gestión de los usuarios.

#### ReservaHandler
Manejador de reservas y bonos. Se encarga de la gestión de las reservas y bonos.

