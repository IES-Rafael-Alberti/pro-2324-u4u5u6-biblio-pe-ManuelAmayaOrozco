class GestorBiblioteca {

    companion object {
        var numPrestamos = 1
    }

    val catalogo = mutableListOf<Libro>()

    private val registroPrestamos = mutableListOf<String>()

    fun agregarLibro(libro: Libro) {
        catalogo.add(libro)
        println("Libro ${libro.titulo} agregado al catálogo.")
    }

    fun eliminarLibro(libro: Libro) {
        val result = catalogo.remove(libro)
        if (result) {
            println("Libro ${libro.titulo} eliminado del catálogo.")
        }
        else {
            println("Libro ${libro.titulo} no encontrado en el catálogo.")
        }
    }

    fun prestarLibro(libro: Libro) {
        if (libroDisponible(libro)) {
            val pos = catalogo.indexOf(libro)
            catalogo[pos].estado = EstadoLibro.PRESTADO
            println("El Libro ${libro.titulo} ha sido prestado con éxito.")
            registroPrestamos.add("$numPrestamos ${libro.titulo} prestado.")
            numPrestamos++
        }
        else {
            println("El libro ${libro.titulo} no se encuentra disponible.")
        }
    }

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

    fun libroDisponible(libro: Libro): Boolean {
        return libro in catalogo && libro.estado == EstadoLibro.DISPONIBLE
    }

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