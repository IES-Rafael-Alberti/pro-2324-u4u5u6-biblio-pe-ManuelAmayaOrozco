fun main() {

    //AHORA PODEMOS AÑADIR LIBROS TANTO POR AQUÍ COMO POR EL MENÚ DE USUARIO.
    //Los IDs se generan de manera automatica con cada libro que sea agregado.

    val gestorBiblioteca = GestorBiblioteca() //Creación del gestor de biblioteca

    val libro = Libro("Prueba", "Autor Prueba", 2005, "Tema Prueba")

    gestorBiblioteca.agregarLibro(libro)

    // AHORA YA SOLO NECESITAMOS EL MENÚ DE USUARIO
    MenuBiblioteca().accederBiblioteca(gestorBiblioteca)
}