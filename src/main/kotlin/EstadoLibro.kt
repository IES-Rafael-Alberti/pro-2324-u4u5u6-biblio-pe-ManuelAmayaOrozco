/**
 * Clase enumerada que define los dos tipos de estados que puede tener un libro:
 * Disponible o Prestado.
 */
enum class EstadoLibro(desc: String) {
    DISPONIBLE("Disponible"),
    PRESTADO("Prestado")
}