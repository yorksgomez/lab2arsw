# snake-race-thread-concurrency

![My image](./snakeraceoutput.png)


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

    Conceptos:

-   La construcción synchronized se utiliza para obtener acceso
    exclusivo a un objeto.

-   Solo un método synchronized puede ejecutarse a la vez en un mismo
    objeto, los demás deben esperar a que este termine. En este caso el
    acceso exclusivo se realiza sobre el mismo objeto, i.e., this.

-   []{#__DdeLink__532_1117315021 .anchor}Los comandos sincronizados
    deben especificar el objeto que provee la exclusividad:
    synchronized(A) {…}

    Los comandos sincronizados pueden anidarse si se necesita
    exclusividad sobre múltiples objetos.

-   La instrucción A.wait() ejecutada en un hilo B espera a que A
    notifique que puede continuar; (interrumpe la ejecución del hilo B)
    usualmente se utiliza cuando el hilo B espera un resultado de A.
    Puede presentarse el caso que un evento diferente al
    []{#__DdeLink__528_1117315021 .anchor}esperado despierte al objeto
    B, por este motivo hay que verificar la condición esperada:

    synchronized (A) {

while (&lt;condition does not hold&gt;)

A.wait();

// Perform action appropriate to condition

}

Nota: el método wait solo puede ser llamado por un hilo que sea dueño
del monitor del objeto (this).

-   La instrucción notify(), despierta el primer hilo que hizo wait()
    sobre el objeto.

-   La instrucción notifyAll(), despierta todos los hilos que estan
    esperando por el objeto (hicieron wait()sobre el objeto).

-   La instrucción A.join() espera a que el hilo A finalice.

### Part II

SnakeRace es un simulador 

- Race conditions
- Active wait


![](img/sshot.png)

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
