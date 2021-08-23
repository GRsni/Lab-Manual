package uca.esi.manual.models

/**
 * Question
 *
 * @property title
 * @property answers
 * @property correctIndex
 * @constructor Create empty Question
 */
data class Question(
    val title: String,
    val answers: List<String>,
    val correctIndex: Int
)
