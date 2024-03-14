/**
 * Clase de utilidades para la biblioteca, actualmente genera un IDs que van
 * aumentando conforme se van agregando libros al sistema.
 */
class UtilidadesBiblioteca {
    companion object {
        var idLib = 0
        var idUsu = 0

        fun generarIdentificadorUnicoLibro(): Int {
            idLib++
            return idLib
        }

        fun generarIdentificadorUnicoUsuario(): Int {
            idUsu++
            return idUsu
        }
    }
}