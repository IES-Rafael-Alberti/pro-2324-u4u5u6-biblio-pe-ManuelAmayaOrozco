data class Libro(val id: String,
                 val titulo: String,
                 val autor: String,
                 val anioPublicacion: Int,
                 val tematica: String,
                 var estado: EstadoLibro = EstadoLibro.DISPONIBLE) {
    override fun toString(): String {
        return "LIBRO: $titulo (ID: $id - AUTOR: $autor - AÑO DE PUBLICACIÓN: $anioPublicacion - TEMÁTICA: $tematica - ESTADO: $estado)"
    }
}

enum class EstadoLibro(desc: String) {
    DISPONIBLE("Disponible"),
    PRESTADO("Prestado")
}