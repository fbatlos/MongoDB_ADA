package com.es.intromongo.aprendizaje2

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.mongodb.client.model.FindOneAndUpdateOptions
import com.mongodb.client.model.ReturnDocument
import com.mongodb.client.model.Updates
import org.bson.Document

fun main() {

    //1º conexion
    val db = ConexionMongo.getDatabase("adaprueba")

    //2º collection
    val collection: MongoCollection<juego> = db.getCollection("colljuego",juego::class.java)
    val returnDoc = FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)


    //3º Aperramos sobre los array
    /*
    todo Insercion 1

    val juegoNuevo = juego(ObjectId(), "Alfonsol" , listOf(1, 98, 54, 234))

    collection.insertOne(juegoNuevo)
      */

    /*
     todo Insertar +1

    val juegoNuevo = juego(ObjectId(), "Pepe" , listOf(4, 88, 14, 45))
    val juegoNuevo2 = juego(ObjectId(), "Lorca" , listOf(5, 9, 24, 64))
    val juegoNuevo3 = juego(ObjectId(), "Victor" , listOf(6, 78, 34, 74))

    collection.insertMany(listOf(juegoNuevo, juegoNuevo2, juegoNuevo3))

     */

    /*
    todo Vaciar la coleccion
    collection.drop()
     */


    /*
    todo Busqueda

    1º podmeos buscar un valor concreto dentro de un array -> dovuelve todos los documentos
    val filtroSimple = Filters.eq("puntuaciones",100)
    val resultadosFiltros = collection.find(filtroSimple)

    val filtroSize = Filters.size("puntuaciones",3)
    val resultadoSize = collection.find(filtroSize)

    // todo Por condicion
    val filtroCondicion = Filters.elemMatch("puntuaciones",org.bson.Document("\$lte", 50))
    val resultadoCondicion = collection.find(filtroCondicion)

    println("Filtro Size.")
    resultadoSize.forEach { println("${it.nombre} \nid ${it.id}\n------------") }
    println("Filtro normal.")
    resultadosFiltros.forEach {println("${it.nombre} \nid ${it.id}\n------------")}
    println("Filtro Condicion.")
    resultadoCondicion.forEach {println("${it.nombre} \nid ${it.id}\n------------")}

     */


    /*
    todo Actualizacion / modificacion

    //Pensar el filtro
    val filtroUpdatesimple = Filters.eq("nombre", "Pablo")
    //Pensar update array por posicion
    val updateSet = Updates.set("puntuaciones.2",75) //nombre del registroal actualizar, la posición del array

    //Devuelve el documento actualizado
    val returnDoc = FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)
    val operacionUpdate = collection.findOneAndUpdate(filtroUpdatesimple, updateSet,returnDoc)


    //todo Modificar un elemento concreto
    val filtroUpdatePrimera = Filters.eq("puntuaciones", 55)

    val updateInc = Updates.inc("puntuaciones.$", 2)

    val operacionUpdateInc = collection.findOneAndUpdate(filtroUpdatePrimera, updateInc, returnDoc)

    println(operacionUpdateInc.nombre)

     */


    /*
    todo Modificar un elemento con varias condicionales
    val filterCombi = Filters.and(
        Filters.gt("puntuaciones",90),
        Filters.eq( "nombre", "Alicia")
    )
    val updateCombi = Updates.inc("puntuaciones.$", -2)

    val returnDoc = FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)

    val operacionUpdateCombi = collection.findOneAndUpdate(filterCombi, updateCombi, returnDoc)

    println(operacionUpdateCombi.puntuaciones)
     */


    //todo Modificar Todos los elementos dentro de u array

    //Modificaremos todas las puntuaciones de Pablo
    val filtroNom = Filters.eq("nombre","Pablo")

    val updatesMap2 = Updates.set("puntuaciones",
        Document("\$map", Document()
            .append("input", "\$puntuaciones")
            .append("as", "puntuacion")
            .append("in", Document("\$cond", Document()
                .append("if", Filters.gte("\$puntuacion", 70))
                .append("then", 0)
                .append("else", "\$puntuacion"))
            ))
    )

    val resultado =  collection.findOneAndUpdate(filtroNom,updatesMap2,returnDoc)

    println(resultado.puntuaciones)



    ConexionMongo.close()


}