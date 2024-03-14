/**
 * Clase principal donde se gestiona toda la biblioteca.
 */
class GestorBiblioteca {

    companion object {
        var numPrestamos = 1 //Contador que sube cada vez que se añade un préstamo al registro.
        const val NUMERO_ANIO_MAXIMO = 2024 //El año actual permitido al crear libros.
    }

    val catalogo = mutableListOf<Libro>() //Catálogo de todos los libros.

    private val registroPrestamos = mutableListOf<String>() //Registro de todos los préstamos.

    /**
     * Función que construye un libro desde cero, creado por el usuario, y
     * después lo agrega al catálogo.
     */
    fun agregarLibro() {
        val id = UtilidadesBiblioteca.generarIdentificadorUnico() //Creamos un ID para el sistema.

        println("Introduce el título del libro:")
        val titulo = readln()

        println("Introduce el autor del libro: ")
        val autor = readln()

        println("Introduce el año de publicación: ")
        var anioPublicacion: Int?
        do {
            anioPublicacion = readln().toIntOrNull()
            if (anioPublicacion == null || anioPublicacion <= 0 || anioPublicacion >= (NUMERO_ANIO_MAXIMO + 1)) {
                println("Introduce un año del 1 hasta 2024.")
            }
        } while (anioPublicacion == null || anioPublicacion <= 0 || anioPublicacion >= (NUMERO_ANIO_MAXIMO + 1))

        println("Introduce la temática del libro:  ")
        val tema = readln()

        val libro = Libro(titulo, autor, anioPublicacion, tema)
        libro.darID(id) //Actualizamos el ID del libro

        catalogo.add(libro)

        println("Libro ${libro.titulo} agregado al catálogo.")
    }

    /**
     * Versión de la función anterior que añade un libro ya creado al sistema biblioteca.
     *
     * @param libro El libro que será añadido.
     */
    fun agregarLibro(libro: Libro) {
        val id = UtilidadesBiblioteca.generarIdentificadorUnico() //Creamos un ID para el sistema.
        libro.darID(id) //Actualizamos el ID del libro.
        catalogo.add(libro)
        println("Libro ${libro.titulo} agregado al catálogo.")
    }

    /**
     * Función que elimina un libro del catálogo de la biblioteca.
     *
     * @param libro El libro que será eliminado del catálogo.
     */
    fun eliminarLibro(libro: Libro) {
        val result = catalogo.remove(libro)
        if (result) {
            println("Libro ${libro.titulo} eliminado del catálogo.")
        }
        else {
            println("Libro ${libro.titulo} no encontrado en el catálogo.")
        }
    }

    /**
     * Función que cambia el estado de un libro a prestado si está disponible.
     *
     * @param libro El libro que será prestado.
     */
    fun prestarLibro(libro: Libro) {
        if (libroDisponible(libro)) {
            val pos = catalogo.indexOf(libro)
            catalogo[pos].estado = EstadoLibro.PRESTADO
            println("El Libro ${libro.titulo} ha sido prestado con éxito.")
            registroPrestamos.add("$numPrestamos ${libro.titulo} prestado.") //Se añade al registro.
            numPrestamos++ //Sube el número para el préstamo siguiente
        }
        else {
            println("El libro ${libro.titulo} no se encuentra disponible.")
        }
    }

    /**
     * Función que cambia el estado de un libro a disponible tras haber sido prestado.
     *
     * @param libro El libro que será devuelto.
     */
    fun devolverLibro(libro: Libro) {
        if (!libroDisponible(libro)) {
            val pos = catalogo.indexOf(libro)
            catalogo[pos].estado = EstadoLibro.DISPONIBLE
            println("El Libro ${libro.titulo} ha sido devuelto con éxito.")
        }
        else {
            println("El libro ${libro.titulo} ya se encuentra disponible.")
        }
    }

    /**
     * Función que indica si un libro se encuentra en el catálogo y si está disponible.
     *
     * @param libro El libro que será examinado.
     */
    fun libroDisponible(libro: Libro): Boolean {
        return libro in catalogo && libro.estado == EstadoLibro.DISPONIBLE
    }

    /**
     * Función que permite el usuario elegir entre si quiere ver todos los libros,
     * solo los libros disponibles o solo los libros prestados en un simple listado
     * por pantalla.
     */
    fun mostrarLibros() {
        println("¿Qué libros quieres que sean mostrados?")
        println("1. TODOS     2. DISPONIBLES      3. PRESTADOS")

        var opt: Int?
        do {
            opt = readln().toIntOrNull()
            if (opt == null || opt <= 0 || opt >= 4) {
                println("Opción no válida, prueba otra vez.")
            }
        } while (opt == null || opt <= 0 || opt >= 4)

        when (opt) {
            1 -> {
                if (catalogo.isEmpty()) {
                    println("No hay ningún libro actualmente. ¡Intenta añadir tú alguno!")
                }
                else {
                    for (libro in catalogo) {
                        println(libro)
                    }
                }
            }

            2 -> {
                val catDisponible = catalogo.filter { it.estado == EstadoLibro.DISPONIBLE }
                if (catDisponible.isEmpty()) {
                    println("No hay ningún libro disponible actualmente.")
                }
                else {
                    for (libro in catDisponible) {
                        println(libro)
                    }
                }
            }

            3 -> {
                val catPrestado = catalogo.filter { it.estado == EstadoLibro.PRESTADO }
                if (catPrestado.isEmpty()) {
                    println("No hay ningún libro prestado actualmente.")
                }
                else {
                    for (libro in catPrestado) {
                        println(libro)
                    }
                }
            }
        }
    }
}