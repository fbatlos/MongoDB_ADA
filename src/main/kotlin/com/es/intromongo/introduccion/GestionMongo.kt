package com.es.intromongo.introduccion

import com.mongodb.client.*
import com.mongodb.client.model.Filters
import io.github.cdimascio.dotenv.dotenv

fun main() {
    //Hasta el create se puede hacer objecy con lazy y añadir get database y get collection

    val dotenv = dotenv()
    val connectString = dotenv["URL_MONGODB_2"]

    // Configuramos la uri del cluster
    val mongoClient: MongoClient = MongoClients.create(connectString)
    val databaseName = "adaprueba"

    try {
        // Obtener la base de datos
        val database: MongoDatabase = mongoClient.getDatabase(databaseName)

        // Listar las colecciones
        val coll:MongoCollection<Usuario> = database.getCollection("collviajes", Usuario::class.java)

        //coll.insertOne(Usuario("jose luis",89,"joselete@gmail.com"))

         val filtro = Filters.eq("nombre", "jose luis")
        println(filtro.toString())
        val busqueda: FindIterable<Usuario> = coll.find()

        busqueda.forEach { println(it.toString()) }

    } catch (e: Exception) {
        println("Error al conectar a MongoDB: ${e.message}")
    } finally {
        // Cerrar la conexión
        mongoClient.close()
    }

}