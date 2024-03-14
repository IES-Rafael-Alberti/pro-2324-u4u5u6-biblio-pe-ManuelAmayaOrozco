/**
 * Clase utilizada para proveer al usuario de un menú con el que poder utilizar las diferentes
 * funciones del sistema de la biblioteca.
 */
class MenuBiblioteca {

    fun accederBiblioteca(gestorBiblioteca: GestorBiblioteca) {
        println("¡BIENVENIDO A LA BIBLIOTECA!\n")

        var activeMenu = true

        do{
            println("¿Qué deseas hacer?")
            println("1. AÑADIR UN LIBRO")
            println("2. AÑADIR USUARIO")
            println("3. COGER PRESTADO UN LIBRO")
            println("4. DEVOLVER UN LIBRO")
            println("5. CONSULTAR DISPONIBILIDAD")
            println("6. MOSTRAR LIBROS")
            println("7. MOSTRAR USUARIOS")
            println("8. MOSTRAR HISTORIAL DE UN LIBRO")
            println("9. MOSTRAR HISTORIAL DE UN USUARIO")
            println("10. SALIR")

            var opt: Int?
            do {
                opt = readln().toIntOrNull()
                if (opt == null || opt <= 0 || opt >= 11) {
                    println("Opción no válida, prueba otra vez.")
                }
            } while (opt == null || opt <= 0 || opt >= 11)

            when (opt) {

                1 -> gestorBiblioteca.agregarLibro()

                2 -> gestorBiblioteca.agregarUsuario()

                3 -> {
                    println("Introduce tu ID de usuario.")

                    var usuEnc: Usuario?

                    do {
                        var encontrar = false
                        val idLib = readln().toIntOrNull()
                        usuEnc = gestorBiblioteca.usuarios.find { it.obtenerID() == idLib }
                        if (usuEnc == null) {
                            println("Usuario no encontrado, por favor vuelve a intentarlo.")
                        }
                        else {
                            encontrar = true
                        }
                    } while(!encontrar)

                    println("Introduce el ID del libro que deseas tomar prestado.")

                    var libEnc: ElementoBiblioteca?

                    do {
                        var encontrar = false
                        val idLib = readln().toIntOrNull()
                        libEnc = gestorBiblioteca.catalogo.find { it.obtenerID() == idLib }
                        if (libEnc == null) {
                            println("Libro no encontrado, por favor vuelve a intentarlo.")
                        }
                        else {
                            encontrar = true
                        }
                    } while(!encontrar)

                    usuEnc?.agregarLibroPrestado(libEnc!!)
                    gestorBiblioteca.registroPrestamos.prestarLibro(usuEnc!!, libEnc!!)
                }

                4 -> {
                    println("Introduce tu ID de usuario.")

                    var usuEnc: Usuario?

                    do {
                        var encontrar = false
                        val idLib = readln().toIntOrNull()
                        usuEnc = gestorBiblioteca.usuarios.find { it.obtenerID() == idLib }
                        if (usuEnc == null) {
                            println("Usuario no encontrado, por favor vuelve a intentarlo.")
                        }
                        else {
                            encontrar = true
                        }
                    } while(!encontrar)

                    println("Introduce el ID del libro que deseas devolver.")

                    var libEnc: ElementoBiblioteca?

                    do {
                        var encontrar = false
                        val idLib = readln().toIntOrNull()
                        libEnc = gestorBiblioteca.catalogo.find { it.obtenerID() == idLib }
                        if (libEnc == null) {
                            println("Libro no encontrado, por favor vuelve a intentarlo.")
                        }
                        else {
                            encontrar = true
                        }
                    } while(!encontrar)

                    usuEnc?.devolverLibroPrestado(libEnc!!)
                    gestorBiblioteca.registroPrestamos.devolverLibro(libEnc!!)
                }

                5 -> {
                    println("Introduce el ID del libro que deseas verificar.")

                    var libEnc: ElementoBiblioteca?

                    do {
                        var encontrar = false
                        val idLib = readln().toIntOrNull()
                        libEnc = gestorBiblioteca.catalogo.find { it.obtenerID() == idLib }
                        if (libEnc == null) {
                            println("Libro no encontrado, por favor vuelve a intentarlo.")
                        }
                        else {
                            encontrar = true
                        }
                    } while(!encontrar)

                    val res = gestorBiblioteca.elementoDisponible(libEnc!!)
                    if (res) {
                        println("El libro está disponible.")
                    }
                    else {
                        println("El libro no está disponible.")
                    }
                }

                6 -> gestorBiblioteca.mostrarLibros()

                7 -> gestorBiblioteca.mostrarUsuarios()

                8 -> {
                    println("Introduce el ID del libro que deseas mirar el historial.")

                    var libEnc: ElementoBiblioteca?

                    do {
                        var encontrar = false
                        val idLib = readln().toIntOrNull()
                        libEnc = gestorBiblioteca.catalogo.find { it.obtenerID() == idLib }
                        if (libEnc == null) {
                            println("Libro no encontrado, por favor vuelve a intentarlo.")
                        }
                        else {
                            encontrar = true
                        }
                    } while(!encontrar)

                    gestorBiblioteca.registroPrestamos.consultarHistorial(libEnc!!)
                }

                9 -> {
                    println("Introduce el ID del usuario.")

                    var usuEnc: Usuario?

                    do {
                        var encontrar = false
                        val idLib = readln().toIntOrNull()
                        usuEnc = gestorBiblioteca.usuarios.find { it.obtenerID() == idLib }
                        if (usuEnc == null) {
                            println("Usuario no encontrado, por favor vuelve a intentarlo.")
                        }
                        else {
                            encontrar = true
                        }
                    } while(!encontrar)

                    gestorBiblioteca.registroPrestamos.consultarHistorial(usuEnc!!)
                }

                10 -> {
                    println("¡Que tengas un buen día!")
                    activeMenu = false
                }
            }
        } while(activeMenu)
    }
}