package com.es.intromongo.introduccion

data class Usuario(
    val nombre: String,
    val edad: Int,
    val pais: String
){
    override fun toString(): String {
        return ("Hola $nombre, Edad $edad, pais $pais")
    }
}
