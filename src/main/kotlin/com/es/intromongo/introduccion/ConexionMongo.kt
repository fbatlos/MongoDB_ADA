import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import io.github.cdimascio.dotenv.dotenv

fun main() {

    val dotenv = dotenv()
    val connectString = dotenv["URL_MONGODB_2"]

    // Configuramos la uri del cluster
    val mongoClient: MongoClient = MongoClients.create(connectString)
    val databaseName = "adaprueba"

    try {
        // Obtener la base de datos
        val database: MongoDatabase = mongoClient.getDatabase(databaseName)

        // Listar las colecciones
        val collections = database.getCollection("documentoholamundo")

        // Mostrar las colecciones
        println("Colecciones en la base de datos '$databaseName':")
        collections.find().forEach { println(it)}

    } catch (e: Exception) {
        println("Error al conectar a MongoDB: ${e.message}")
    } finally {
        // Cerrar la conexión
        mongoClient.close()
    }


}