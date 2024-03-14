/**
 * Clase principal donde se gestiona toda la biblioteca.
 */
class GestorBiblioteca {

    companion object {
        var numPrestamos = 1 //Contador que sube cada vez que se añade un préstamo al registro.
    }

    val catalogo = mutableListOf<Libro>() //Catálogo de todos los libros.

    private val registroPrestamos = mutableListOf<String>() //Registro de todos los préstamos.

    /**
     * Función que agrega un libro al catálogo de la biblioteca.
     *
     * @param libro El libro que será añadido al catálogo.
     */
    fun agregarLibro(libro: Libro) {
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
                for (libro in catalogo) {
                    println(libro)
                }
            }

            2 -> {
                val catDisponible = catalogo.filter { it.estado == EstadoLibro.DISPONIBLE }
                for (libro in catDisponible) {
                    println(libro)
                }
            }

            3 -> {
                val catPrestado = catalogo.filter { it.estado == EstadoLibro.PRESTADO }
                for (libro in catPrestado) {
                    println(libro)
                }
            }
        }
    }
}