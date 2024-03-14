/**
 * Clase principal donde se gestiona toda la biblioteca.
 */
class GestorBiblioteca() {

    companion object {
        const val NUMERO_ANIO_MAXIMO = 2024 //El año actual permitido al crear libros.
    }

    val catalogo = mutableListOf<ElementoBiblioteca>() //Catálogo de todos los libros.

    val usuarios = mutableListOf<Usuario>() //Lista de todos los usuarios de la biblioteca.

    val registroPrestamos = RegistroPrestamos()

    /**
     * Función que construye un libro desde cero, creado por el usuario, y
     * después lo agrega al catálogo.
     */
    fun agregarLibro() {
        val id = UtilidadesBiblioteca.generarIdentificadorUnicoLibro() //Creamos un ID para el sistema.

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
        libro.actualizarID(id) //Actualizamos el ID del libro

        catalogo.add(libro)

        println("Libro ${libro.obtenerTitulo()} agregado al catálogo.")
    }

    /**
     * Versión de la función anterior que añade un libro ya creado al sistema biblioteca.
     *
     * @param libro El libro que será añadido.
     */
    fun agregarLibro(libro: Libro) {
        val id = UtilidadesBiblioteca.generarIdentificadorUnicoLibro() //Creamos un ID para el sistema.
        libro.actualizarID(id) //Actualizamos el ID del libro.
        catalogo.add(libro)
        println("Libro ${libro.obtenerTitulo()} agregado al catálogo.")
    }

    fun agregarUsuario() {
        val id = UtilidadesBiblioteca.generarIdentificadorUnicoUsuario() //Creamos un ID para el sistema.

        println("Introduce el nombre del usuario:")
        val nombre = readln()

        val usuario = Usuario(nombre)
        usuario.actualizarID(id) //Actualizamos el ID del usuario

        usuarios.add(usuario)

        println("Usuario ${usuario.obtenerNombre()} agregado al sistema.")
    }

    fun agregarUsuario(usuario: Usuario) {
        val id = UtilidadesBiblioteca.generarIdentificadorUnicoUsuario() //Creamos un ID para el sistema.
        usuario.actualizarID(id) //Actualizamos el ID del usuario
        usuarios.add(usuario)
        println("Usuario ${usuario.obtenerNombre()} agregado al sistema.")
    }

    /**
     * Función que elimina un libro del catálogo de la biblioteca.
     *
     * @param libro El libro que será eliminado del catálogo.
     */
    fun eliminarElemento(elemento: ElementoBiblioteca) {
        val result = catalogo.remove(elemento)
        if (result) {
            println("Libro ${elemento.obtenerTitulo()} eliminado del catálogo.")
        }
        else {
            println("Libro ${elemento.obtenerTitulo()} no encontrado en el catálogo.")
        }
    }

    /**
     * Función que indica si un libro se encuentra en el catálogo y si está disponible.
     *
     * @param libro El libro que será examinado.
     */
    fun elementoDisponible(elemento: ElementoBiblioteca): Boolean {
        return elemento in catalogo && elemento.obtenerEstado() == EstadoLibro.DISPONIBLE
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
                val catDisponible = catalogo.filter { it.obtenerEstado() == EstadoLibro.DISPONIBLE }
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
                val catPrestado = catalogo.filter { it.obtenerEstado() == EstadoLibro.PRESTADO }
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

    fun mostrarUsuarios() {
        if (usuarios.isEmpty()) {
            println("Aún no hay ningún usuario en el sistema. ¡Intenta crear uno!")
        }
        else {
            for (usuario in usuarios) {
                println(usuario)
            }
        }
    }

    inner class RegistroPrestamos: IGestorPrestamos {

        private var numPrestamos = 1 //Contador que sube cada vez que se añade un préstamo al registro.

        private val registroPrestamosLibros = mutableMapOf<Libro,MutableList<String>>()
        private val registroPrestamosUsuarios = mutableMapOf<Usuario,MutableList<String>>()


        /**
         * Función que cambia el estado de un libro a prestado si está disponible.
         *
         * @param libro El libro que será prestado.
         */
        override fun prestarLibro(usuario: Usuario, elemento: ElementoBiblioteca) {
            if (elementoDisponible(elemento)) {
                val pos = catalogo.indexOf(elemento)
                if (catalogo[pos] is Prestable) {
                    catalogo[pos].prestar()
                    println("El Libro ${elemento.obtenerTitulo()} ha sido prestado con éxito.")
                    registroPrestamosLibros[elemento]?.add("$numPrestamos ${elemento.obtenerTitulo()} prestado por ${usuario.obtenerNombre()}.")
                    usuario.actualizarNumPrestados()
                    registroPrestamosUsuarios[usuario]?.add("${usuario.obtenerNumPrestados()} ${elemento.obtenerTitulo()} prestado por ${usuario.obtenerNombre()}.")//Se añade al registro.
                    numPrestamos++ //Sube el número para el préstamo siguiente
                }

            }
            else {
                println("El libro ${elemento.obtenerTitulo()} no se encuentra disponible.")
            }
        }

        /**
         * Función que cambia el estado de un libro a disponible tras haber sido prestado.
         *
         * @param libro El libro que será devuelto.
         */
        override fun devolverLibro(elemento: ElementoBiblioteca) {
            if (!elementoDisponible(elemento)) {
                val pos = catalogo.indexOf(elemento)
                catalogo[pos].devolver()
                println("El Libro ${elemento.obtenerTitulo()} ha sido devuelto con éxito.")
            }
            else {
                println("El libro ${elemento.obtenerTitulo()} ya se encuentra disponible.")
            }
        }

        override fun consultarHistorial(elemento: ElementoBiblioteca) {
            val historial = registroPrestamosLibros[elemento]
            if (historial != null) {
                for (reg in historial) {
                    println(reg)
                }
            }
        }

        override fun consultarHistorial(usuario: Usuario) {
            val historial = registroPrestamosUsuarios[usuario]
            if (historial != null) {
                for (reg in historial) {
                    println(reg)
                }
            }
        }

    }
}