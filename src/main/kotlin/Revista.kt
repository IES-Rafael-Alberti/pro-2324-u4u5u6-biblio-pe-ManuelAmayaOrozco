/**
 * Clase que representa una revista para el sistema biblioteca.
 *
 * @param id El ID que tendrá la revista en el sistema, empieza siendo 0 hasta que sea añadido al sistema.
 * @param titulo El título de la revista.
 * @param editorial El nombre de la editorial de la revista.
 * @param tematica La temática de la revista.
 * @param estado El estado de la revista, que puede ser disponible o prestado.
 */
open class Revista(titulo: String,
                 private val editorial: String,
                 private val tematica: String,
                 estado: EstadoLibro = EstadoLibro.DISPONIBLE): ElementoBiblioteca(titulo, estado),Prestable {

    private var id = 0 //Ahora el ID siempre será 0 al principio, hasta que sea agregado a la biblioteca.

    /**
     * Función que actualiza el ID de la revista, usado cuando es agregado al sistema biblioteca.
     */
    fun actualizarID(newID: Int) {
        id = newID
    }

    /**
     * Función que devuelve el ID actual de la revista.
     */
    fun obtenerID(): Int {
        return id
    }

    /**
     * Función que devuelve el título de la revista.
     */
    fun obtenerTitulo(): String {
        return titulo
    }

    /**
     * Función que retorna una definición completa de la revista, con todos sus parámetros.
     *
     * @return Retorna la definición al completo como un [String]
     */
    override fun toString(): String {
        return "REVISTA: $titulo (ID: $id - EDITORIAL: $editorial - TEMÁTICA: $tematica - ESTADO: $estado)"
    }

    override fun prestar() {
        estado = EstadoLibro.PRESTADO
    }

    override fun devolver() {
        estado = EstadoLibro.DISPONIBLE
    }
}