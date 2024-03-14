abstract class ElementoBiblioteca(val titulo: String, var estado: EstadoLibro = EstadoLibro.DISPONIBLE) {
    protected abstract var id: Int

    fun obtenerEstado(): EstadoLibro {
        return estado
    }

    fun obtenerTitulo(): String {
        return titulo
    }

    fun obtenerID(): Int{
        return id
    }
}