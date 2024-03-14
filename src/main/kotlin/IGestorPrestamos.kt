interface IGestorPrestamos {
    fun prestarLibro(usuario: Usuario, elemento: ElementoBiblioteca)
    fun devolverLibro(elemento: ElementoBiblioteca)
    fun consultarHistorial(elemento: ElementoBiblioteca)
    fun consultarHistorial(usuario: Usuario)
}