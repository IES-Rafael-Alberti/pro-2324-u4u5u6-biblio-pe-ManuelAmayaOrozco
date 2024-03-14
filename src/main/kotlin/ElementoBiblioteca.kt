abstract class ElementoBiblioteca(val titulo: String, var estado: EstadoLibro = EstadoLibro.DISPONIBLE) {
    fun obtenerEstado(): EstadoLibro {
        return estado
    }
}