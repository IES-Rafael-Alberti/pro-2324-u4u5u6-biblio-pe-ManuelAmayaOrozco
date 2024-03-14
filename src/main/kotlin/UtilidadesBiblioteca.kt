/**
 * Clase de utilidades para la biblioteca, actualmente genera un IDs que van
 * aumentando conforme se van agregando libros al sistema.
 */
class UtilidadesBiblioteca {
    companion object {
        var id = 0

        fun generarIdentificadorUnico(): Int {
            id++
            return id
        }
    }
}