/**
 * Clase que representa un libro para el sistema biblioteca.
 *
 * @param id El ID que tendrá el libro en el sistema, empieza siendo 0 hasta que sea añadido al sistema.
 * @param titulo El título del libro.
 * @param autor El nombre del autor del libro.
 * @param anioPublicacion El año de publicación del libro.
 * @param tematica La temática del libro.
 * @param estado El estado del libro, que puede ser disponible o prestado.
 */
data class Libro(private val titulo: String,
                 private val autor: String,
                 private val anioPublicacion: Int,
                 private val tematica: String,
                 private var estado: EstadoLibro = EstadoLibro.DISPONIBLE) {

    private var id = 0 //Ahora el ID siempre será 0 al principio, hasta que sea agregado a la biblioteca.

    /**
     * Función que actualiza el ID del Libro, usado cuando es agregado al sistema biblioteca.
     */
    fun actualizarID(newID: Int) {
        id = newID
    }

    /**
     * Función que devuelve el ID actual del libro.
     */
    fun obtenerID(): Int {
        return id
    }

    /**
     * Función que devuelve el título del libro.
     */
    fun obtenerTitulo(): String {
        return titulo
    }

    /**
     * Función que devuelve el estado del libro.
     */
    fun obtenerEstado(): EstadoLibro {
        return estado
    }

    /**
     * Función que actualiza el estado del libro.
     */
    fun actualizarEstado(nuevoEstado: EstadoLibro) {
        estado = nuevoEstado
    }

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