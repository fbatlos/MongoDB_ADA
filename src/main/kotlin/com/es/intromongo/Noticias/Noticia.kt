package com.es.intromongo.Noticias

import org.bson.types.ObjectId
import java.util.Date

data class Noticia(
    val _id: ObjectId,
    val titulo: String,
    val cuerpo: String,
    val fecha_pub:Date,
    val tag: List<String>,
    val user: String
)
