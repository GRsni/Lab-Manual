package uca.esi.manual.models

data class Question(
    val title: String,
    val answers: List<String>,
    val correctIndex: Int
)
