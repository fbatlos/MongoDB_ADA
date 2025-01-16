package com.es.intromongo.introduccion

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import io.github.cdimascio.dotenv.dotenv
import org.bson.*

fun main() {
    val dotenv = dotenv()
    val connectString = dotenv["URL_MONGODB_2"]

// Configuramos la uri del cluster
    val mongoClient: MongoClient = MongoClients.create(connectString)
    val databaseName = "adaprueba"


    try {

         // Obtener la base de datos
        val database = mongoClient.getDatabase(databaseName)

         // Obtener la colección
        val coll = database.getCollection("documentoholamundo")

        val filter = Filters.eq("idioma", "japones")

        coll.find().forEach { println(it) }

        val update = Updates.set("idioma","chino mandarin" )
        //update 1
        //coll.updateOne(filter,update).also { println(it) }

        //update 2
        //operador y campo a cambiar
        val updateConDocument = Document("\$inc",Document("nhablantes",78878))
        //coll.updateOne()

        //update combinado
        val updateconbinado = Document("\$mul",Document("nhablantes",78878)).append("\$set",Document("idioma","mandarin"))

        coll.updateOne(filter,updateconbinado).also { println(it) }

        //renombrar Campos

        //val updatenombreCampo =

    } catch (e: Exception) {
        println("Error al conectar a MongoDB: ${e.message}")
    } finally {
        // Cerrar la conexión
        mongoClient.close()
    }
}
