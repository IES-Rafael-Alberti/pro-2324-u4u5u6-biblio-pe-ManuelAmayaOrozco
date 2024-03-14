/**
 * Clase que representa un libro para el sistema biblioteca.
 *
 * @param id El ID que tendrá el libro en el sistema.
 * @param titulo El título del libro.
 * @param autor El nombre del autor del libro.
 * @param anioPublicacion El año de publicación del libro.
 * @param tematica La temática del libro.
 * @param estado El estado del libro, que puede ser disponible o prestado.
 */
data class Libro(val id: String,
                 val titulo: String,
                 val autor: String,
                 val anioPublicacion: Int,
                 val tematica: String,
                 var estado: EstadoLibro = EstadoLibro.DISPONIBLE) {

    /**
     * Función que retorna una definición completa del libro, con todos sus parámetros.
     *
     * @return Retorna la definición al completo como un [String]
     */
    override fun toString(): String {
        return "LIBRO: $titulo (ID: $id - AUTOR: $autor - AÑO DE PUBLICACIÓN: $anioPublicacion - TEMÁTICA: $tematica - ESTADO: $estado)"
    }
}

/**
 * Clase enumerada que define los dos tipos de estados que puede tener un libro:
 * Disponible o Prestado.
 */
enum class EstadoLibro(desc: String) {
    DISPONIBLE("Disponible"),
    PRESTADO("Prestado")
}