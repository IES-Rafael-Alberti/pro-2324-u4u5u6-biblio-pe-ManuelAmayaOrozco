fun main() {

    val gestorBiblioteca = GestorBiblioteca() //Creación del gestor de biblioteca

    //Unos cuantos libros ya generados
    val libro1 = Libro("1","Libro A", "Autor 1", 2024, "Tema 1")
    val libro2 = Libro("2","Libro B", "Autor 2", 2024, "Tema 2")
    val libro3 = Libro("3","Libro C", "Autor 3", 2024, "Tema 3")

    //Se añaden los libros
    gestorBiblioteca.agregarLibro(libro1)
    gestorBiblioteca.agregarLibro(libro2)
    gestorBiblioteca.agregarLibro(libro3)

    //FORMA NORMAL

    /*gestorBiblioteca.prestarLibro(libro1)
    gestorBiblioteca.prestarLibro(libro1)
    gestorBiblioteca.devolverLibro(libro1)
    gestorBiblioteca.devolverLibro(libro1)
    gestorBiblioteca.mostrarLibros()*/

    // USANDO MENÚ USUARIO

    MenuBiblioteca().accederBiblioteca(gestorBiblioteca)
}