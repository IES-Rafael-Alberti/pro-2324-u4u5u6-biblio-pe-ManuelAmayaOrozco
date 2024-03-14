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
            println("2. COGER PRESTADO UN LIBRO")
            println("3. DEVOLVER UN LIBRO")
            println("4. CONSULTAR DISPONIBILIDAD")
            println("5. MOSTRAR LIBROS")
            println("6. SALIR")

            var opt: Int?
            do {
                opt = readln().toIntOrNull()
                if (opt == null || opt <= 0 || opt >= 7) {
                    println("Opción no válida, prueba otra vez.")
                }
            } while (opt == null || opt <= 0 || opt >= 7)

            when (opt) {

                1 -> gestorBiblioteca.agregarLibro()

                2 -> {
                    println("Introduce el ID del libro que deseas tomar prestado.")

                    var libEnc: Libro?

                    do {
                        var encontrar = false
                        val idLib = readln().toIntOrNull()
                        libEnc = gestorBiblioteca.catalogo.find { it.id == idLib }
                        if (libEnc == null) {
                            println("Libro no encontrado, por favor vuelve a intentarlo.")
                        }
                        else {
                            encontrar = true
                        }
                    } while(!encontrar)

                    gestorBiblioteca.prestarLibro(libEnc!!)
                }

                3 -> {
                    println("Introduce el ID del libro que deseas devolver.")

                    var libEnc: Libro?

                    do {
                        var encontrar = false
                        val idLib = readln().toIntOrNull()
                        libEnc = gestorBiblioteca.catalogo.find { it.id == idLib }
                        if (libEnc == null) {
                            println("Libro no encontrado, por favor vuelve a intentarlo.")
                        }
                        else {
                            encontrar = true
                        }
                    } while(!encontrar)

                    gestorBiblioteca.devolverLibro(libEnc!!)
                }

                4 -> {
                    println("Introduce el ID del libro que deseas verificar.")

                    var libEnc: Libro?

                    do {
                        var encontrar = false
                        val idLib = readln().toIntOrNull()
                        libEnc = gestorBiblioteca.catalogo.find { it.id == idLib }
                        if (libEnc == null) {
                            println("Libro no encontrado, por favor vuelve a intentarlo.")
                        }
                        else {
                            encontrar = true
                        }
                    } while(!encontrar)

                    val res = gestorBiblioteca.libroDisponible(libEnc!!)
                    if (res) {
                        println("El libro está disponible.")
                    }
                    else {
                        println("El libro no está disponible.")
                    }
                }

                5 -> gestorBiblioteca.mostrarLibros()

                6 -> {
                    println("¡Que tengas un buen día!")
                    activeMenu = false
                }
            }
        } while(activeMenu)
    }
}