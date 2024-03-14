class Usuario(private val nombre: String) {

    private var id = 0

    private val librosPrestados = mutableListOf<Libro>()

    private var numPrestados = 0

    fun obtenerNumPrestados(): Int {
        return numPrestados
    }

    fun actualizarNumPrestados() {
        numPrestados++
    }

    fun obtenerID(): Int  {
        return id
    }

    fun actualizarID(newID: Int) {
        id = newID
    }

    fun obtenerNombre(): String {
        return nombre
    }

    fun agregarLibroPrestado(libro: ElementoBiblioteca) {
        librosPrestados.add(libro)
    }

    fun devolverLibroPrestado(libro: ElementoBiblioteca) {
        librosPrestados.remove(libro)
    }

    fun mostrarLibrosPrestados() {
        for (libro in librosPrestados) {
            println(libro)
        }
    }

    override fun toString(): String {
        return "USUARIO $nombre (ID: $id)"
    }
}