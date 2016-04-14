# TP Grupal - Primera Entrega

## Enunciado ##

Se desea implementar un motor de generación de juegos tipo aventura gráfica (o similares).

En ésta primera instancia se considerarán solo aventuras gráficas basadas en texto, el jugador ingresa comandos a través de una consola y el juego devuelve la salida correspondiente también en forma de texto.
En ésta primera instancia el set de juegos a soportar será fijo y estará compuesto por los ejemplos mencionados al final del enunciado, más los juegos que el grupo quiera agregar (dentro de los comportamientos mencionados en los ejemplos).
El tp tendrá los siguientes componentes:
 - Un servidor en donde se pueden cargar los juegos. Esto es inicializa un motor con un juego y se queda esperando en un port por comandos del jugador.
 - Un cliente: se conecta a un servidor que esté ejecutando un juego y permite enviar comandos y recibir respuestas.
 - Un motor: Es el componente encargado de la carga y la ejecución del juego.

El servidor tiene que soportar los siguientes comandos:
 - `load game “juego”`: carga “juego” y se queda esperando a que el jugador se conecte.
El cliente tiene que soportar los siguientes comandos (independientes del juego):
 - `exit game`: sale del juego actual.
 - `help “juego”`: Muestra una descripción de “juego”.

La idea no es implementar estos juegos, sino diseñar un modelo que permita fácilmente la creación de los mismos.

>**Los requerimientos pueden (y van a) cambiar en cualquier momento.**

## Condiciones ##
 - Para las consultas generales se va a utilizar el channel `#tp-grupal-channel`.
 - Cada grupo va a tener su channel para consultas particulares y comunicación con su ayudante.
 - Se debe usar el mismos toolset que para el TP0 (java 8, IntelliJ, gradle, pmd, cpd, checkstyle, findbugs).
 - Se supone que todos están al tanto de las notificaciones en Slack. Esto incluye cambios de alcance, cambios en los requerimientos, restricciones adicionales, etc.
 - Se podrán modificar la configuración del toolset sólo bajo aprobación de los ayudantes (`#quejas-tooling`).
 - Cada grupo debe crear un repositorio en GitHub y agregar a los ayudantes de la materia al mismo.
 - Se debe integrar el repositorio con Travis-CI.
 - La documentación se realizará en la misma Wiki del repositorio en GitHub.
 - En la fecha de entrega se realizará una demo del TP a uno o dos ayudantes. Esto implica que el TP se debe poder utilizar dentro de las restricciones de la entrega.
Durante la demo y posterior corrección se cargarán issues en GitHub que deben estar solucionados para la siguiente entrega.
 - **No se aceptarán TPs:**
   - **con warnings.**
   - **que no compilen en Travis-CI.**
   - **con issues abiertos.**
   - **Que no se puedan utilizar**
 - **No hay re-entrega.** Es responsabilidad del grupo realizar las consultas durante las 2-3 semanas disponibles para realizar el TP.
 - Si un alumno no puede concurrir a la demo, se aceptará la entrega pero dicho alumno figurará como ausente. Esto puede repercutir en la nota final.

 ## Ejemplos de Juegos ##

 ### Fetch quest ###
 El jugador comienza en una  habitación, con un palo ubicado en dicha habitación. Es necesario tomar el palo.
```
 > look around [Enter]
 > There’s a stick in the room.
 > pick stick [Enter]
 > You won the game!
 ```
### Abrir Puerta ###
El jugador está en una habitación que contiene una  llave y una puerta. Gana al atravesar la puerta y pasar a la siguiente habitación.
```
> look around [Enter]
> There’s a key and a door in the room.
> open door. [Enter]
> Ey! Where do you go?! Room 2 is locked.
> pick key. [Enter]
> There you go!
> open door. [Enter]
> You enter room 2. You won the game!
```

### Abrir Puerta 2 ###
El jugador está en una habitación que contiene una caja con una llave dentro y una puerta. Gana al atravesar la puerta y pasar a la siguiente habitación.
```
> look around [Enter]
> There’s a box and a door in the room.
> open door. [Enter]
> Ey! Where do you go?! Room 2 is locked.
> What can I do with box? [Enter]
> You can open/close the box?
> open box.[Enter]
> The box is opened!.
> look around [Enter]
> There’re a box, a key and a door in the room.
> pick key. [Enter]
> There you go!
> open door. [Enter]
> You enter room 2. You won the game!
```

### Objeto Maldito ###
Hay tres habitaciones en serie: En la primera, hay un objeto “maldito” que el jugador debe poseer para abrir la primer puerta, pero del que no puede deshacerse por acción propia. La segunda puerta se abre si el jugador no posee el objeto maldito. Para ayudar al jugador, en la segunda habitación hay un ladrón que toma todos los objetos del jugador cuando interactúa con él. Gana al llegar a la tercer habitacion.

```
>...
> What can I do with thief? [Enter]
> You can talk with thief: “Hello”, “Bye”.
> Talk to thief “Hello” [Enter]
> Hi!
> The thief has just stolen your object!
> ...
```

### Acertijo del lobo, la cabra y la col ###
Hace mucho tiempo un granjero fue al mercado y compró un lobo , una cabra y una col. Para volver a su casa tenía que cruzar un río. El granjero dispone de una barca para cruzar a la otra orilla, pero en la barca solo caben él y una de sus compras.  Si el lobo se queda solo con la cabra se la come, si la cabra se queda sola con la col se la come. El reto del granjero era cruzar él mismo y dejar sus compras a la otra orilla del río, dejando cada compra intacta. ¿Cómo lo hizo?

```
> take wolf [Enter]
> ok, but be carefull!
> take sheep [Enter]
> You can’t do that! The boat is full.
> cross north-shore [Enter]
> you have crossed!
> leave wolf [Enter]
> Ok
> cross south-shore [Enter]
> you have crossed!
> take sheep [Enter]
> Ok
> cross north-shore [Enter]
> crossed!
> leave sheep [Enter]
> You can’t do that! The wolf will eat the sheep!
...
```

### Torres de Hanoi ###
El juego empieza con 3 pilares donde donde el primero tiene un pila de discos, donde el disco de mas abajo de la pila es de mayor tamaño y siendo los otros consecutivamente de menor tamaño.

El objetivo del juego es mover la pila de discos a otro pilar, cumpliendo las siguientes reglas:
 - Solo se puede mover un disco por vez.
 - Cada movimiento consiste en tomar el disco superior de una pila y en la sima de otra pila. Es decir solo se puede mover un disco que está en la sima de la pila.
 - Ningun disco se puede apilar sobre otro más pequeño.

```
> …
> What can I do with stack 1? [Enter]
> You can check top/move top.
> check top stack 1 [Enter]
> Size of top from stack 1 is 5.
> check top stack 2 [Enter]
> Size of top from stack 2 is 6
> move top stack 1 stack 2 [Enter]
> moved!
>...
```

### Busqueda del Tesoro ###
Se tienen 5 habitaciones. Algunas están cerradas y otras no. En cada habitación hay cajas, baúles, armarios, inicialmente todos cerrados. Puede ser que dentro de un armario o un baúl haya cajas. En ellos hay escondidos distintos elementos que el jugador tiene que ir recolectando, pero nunca puede tener más de dos en su posesión, por lo cual debería ir dejandolos cuando no le sirvan más. En las cajas se puede guardar sólo un elemento, pero en los baúles y los armarios se pueden guardar más de uno. Los elementos que se pueden encontrar son:
 - **Llaves**: sirven para abrir puertas. Una llave funciona con una puerta en particular.
 - **Venenos**: Cuando el player encuentra un veneno, esto no lo mata pero por razones que no alcanza a comprender si intenta salir de la habitación se muere. El jugador se envenena simplemente al abrir la caja/baúl/armario que tiene el veneno.
 - **Antídotos**: Se usan para curar al player cuando encuentra un veneno. Los antídotos funcionan con cualquier veneno, así que mejor tener uno a mano!.
 - **Tesoro**

El jugador gana al encontrar el tesoro y volver a la habitación inicial.


## Calendario tentativo ##

| Fecha       |                  |
|-----------  | -----------------|
| 7 de Abril  | Armado de grupos |
| 14 de Abril | Publicación 1ra Entrega |
| 21 de Abril | |
| 28 de Abril | **Primera Entrega** - Publicación 2da Entrega |
| 5 de Mayo   | Entrega de notas 1ra Entrega |
| 12 de Mayo  | |
| 19 de Mayo  | **Segunda Entrega** - Publicación 3ra Entrega |
| 26 de Mayo  | Entrega de notas 2da Entrega |
| 2 de junio  | **Tercera Entrega** |
| 9 de Junio  | Revisión |
| 16 de Junio | Cierre de notas |

