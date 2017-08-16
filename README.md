### Escuela Colombiana de Ingeniería
## Arquitecturas de Software - ARSW

#### Laboratorio - Programación concurrente, condiciones de carrera, esquemas de sincronización, colecciones sincronizadas y concurrentes.


### Part I


Parte I – Antes de terminar la clase.

Control de hilos con wait/notify.

1.  Descargue el proyecto
    [*PrimeFinder*](https://github.com/ARSW-ECI/wait-notify-excercise).
    Este es un programa que calcula números primos entre 0 y M
    (Control.MAXVALUE), concurrentemente, distribuyendo la búsqueda de
    los mismos entre n (Control.NTHREADS) hilos independientes.

2.  Se necesita modificar la aplicación de manera que cada t
    milisegundos de ejecución de los threads, se detengan todos los
    hilos y se muestre el número de primos encontrados hasta el momento.
    Luego, se debe esperar a que el usuario presione ENTER para reanudar
    la ejecución de los mismos. Utilice los mecanismos de sincronización
    provistos por el lenguaje (wait y notify, notifyAll).

Tenga en cuenta:

-   La construcción synchronized se utiliza para obtener acceso exclusivo a un objeto.

-   La instrucción A.wait() ejecutada en un hilo B pone en modo suspendido al hilo B (independientemente de qué objeto 'A' sea usado como 'lock'). Para reanudarlo, otro hilo activo puede reanudar a B haciendo 'notify()' al objeto usado como 'lock' (es decir, A).

-   La instrucción notify(), despierta el primer hilo que hizo wait()
    sobre el objeto.

-   La instrucción notifyAll(), despierta todos los hilos que estan
    esperando por el objeto (hicieron wait()sobre el objeto).


### Parte II

SnakeRace es una versión autónoma, multi-serpiente del famoso juego 'snake', basado en el proyecto de João Andrade -este ejercicio es un 'fork' del mismo-. En este juego:
	
- N serpientes funcionan de manera autónoma.
- No existe el concepto de colisión entre las mismas. La única forma de que mueran es estrellándose contra un muro.
- Hay ratones distribuídos a lo largo del juego. Como en el juego clásico, cada vez que una serpiente se come a un ratón, ésta crece.
- Existen unos puntos (flechas rojas) que teletransportan a las serpientes.

![](img/sshot.png)

Ejercicio

1. Analice el código para entender cómo hace uso de hilos para crear un comportamiento autónomo de las N serpientes.
2. De acuerdo con lo anterior, y con la lógica del juego, identifique y escriba claramente:
    - Posibles condiciones de carrera.
    - Uso inadecuado de colecciones, considerando su manejo concurrente (para esto, aumente la velocidad del juego y ejecútelo varias veces hasta que se genere un error).
2. Identifique las regiones críticas asociadas a las condiciones de carrera, y haga algo para eliminarlas. Tenga en cuenta que se debe sincronizar estríctamente LO NECESARIO. En su documento de respuestas indique, la solución realizada para cada ítem del punto 2.

3. Como se puede observar, el juego está incompleto. Haga los ajustes necesarios para que

    * Botón inicio: iniciar el juego.
    * Pausa: Pausar el juego. Esto debe detener la ejecución de las serpientes y mostrar en algun lugar de la interfaz (agregue los componentes que desee)
    

- Race conditions
- Active wait




A variation of the popular game Snake using multi-snake threads moving against things to practice concurrency

Project made during 2nd Year of graduation for the class - Concurrent and Parallel Programming

## Instructions

You don't need to do anything, just watch snakes randomly battling against each other.

If you want to control a snake just Mouse-Right-Click it to select and Mouse-Left-Click in a Cell to make her move to its destiny.

There are 4 Special items on the board that Snakes can interact with:

##### Food

- Grabing a rat makes your Snake grow 3 cells.

##### Jump Pad

- Getting a jump pad makes your Snake able to walk through other objects/snakes.

##### Barrier

- Crashing against a barrier will kill your Snake

##### Turbo Boost

- Getting a Turbo Boost will make your Snake move 3 times as fast.

Snakes finish the "race" when they reach the opposite of the Board from where they Spawned.

Detailed instruction can be found in 'The Snake Race - Instrucoes.docx' & 'The Snake Race - Relatorio.docx' (both in Portuguese)
## How to run

/SnakeRace/src/snakepackage/SnakeApp.java is where the main method is. 



### Left to do

Need to refactor the code, many parts are messy. It works but it isn't very effective.

More documentation & comments to ease cleaning code.

Some OOP rules (Visibility/Encapsulation) are not being followed because it wasn't the focus on this subject, I should fix that.



----


- Active waiting
