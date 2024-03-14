/**
 * Clase que representa un DVD para el sistema biblioteca.
 *
 * @param id El ID que tendrá el DVD en el sistema, empieza siendo 0 hasta que sea añadido al sistema.
 * @param titulo El título de el DVD.
 * @param editorial El nombre de la editorial de el DVD.
 * @param tematica La temática de el DVD.
 * @param estado El estado del DVD, que puede ser disponible o prestado.
 */
open class DVD(titulo: String,
                   private val autor: String,
                   private val estilo: String,
                   estado: EstadoLibro = EstadoLibro.DISPONIBLE): ElementoBiblioteca(titulo, estado),Prestable {

    private var id = 0 //Ahora el ID siempre será 0 al principio, hasta que sea agregado a la biblioteca.

    /**
     * Función que actualiza el ID del DVD, usado cuando es agregado al sistema biblioteca.
     */
    fun actualizarID(newID: Int) {
        id = newID
    }

    /**
     * Función que devuelve el ID actual del DVD.
     */
    fun obtenerID(): Int {
        return id
    }

    /**
     * Función que devuelve el título del DVD.
     */
    fun obtenerTitulo(): String {
        return titulo
    }

    /**
     * Función que retorna una definición completa del DVD, con todos sus parámetros.
     *
     * @return Retorna la definición al completo como un [String]
     */
    override fun toString(): String {
        return "REVISTA: $titulo (ID: $id - AUTOR: $autor - ESTILO: $estilo - ESTADO: $estado)"
    }

    override fun prestar() {
        estado = EstadoLibro.PRESTADO
    }

    override fun devolver() {
        estado = EstadoLibro.DISPONIBLE
    }
}