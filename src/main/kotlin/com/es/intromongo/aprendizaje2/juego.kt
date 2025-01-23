package com.es.intromongo.aprendizaje2

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class juego(
    @BsonId
    val id: ObjectId,
    val nombre: String,
    val puntuaciones: List<Int>
)
