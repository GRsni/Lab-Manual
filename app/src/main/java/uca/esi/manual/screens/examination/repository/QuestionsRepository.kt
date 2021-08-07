package uca.esi.manual.screens.examination.repository

import uca.esi.manual.models.Question
import uca.esi.manual.models.labs.BaseLab

class QuestionsRepository {
    private val torsionQuestions =
        listOf(
            Question(
                "¿Para el cálculo teórico de la deformada por torsión," +
                        " qué valor de los siguiente hay que aplicar?",
                listOf(
                    "El módulo de la elasticidad transversal(G)",
                    "El módulo de la elasticidad longitudinal (E)",
                    "Ninguno de los dos valores es necesario",
                    "Ambos valores son necesarios"
                ), 0
            ),
            Question(
                "¿Sabrías decir, para la barra sometida sólo a torsión," +
                        " qué tipo de deformación se produce?",
                listOf(
                    "Deformación longitudinal en mm",
                    "Deformación longitudinal en radianes",
                    "Deformación angular en radianes",
                    "Deformación angular en mm"
                ), 2
            ),
            Question(
                "¿Cómo se produce la deformación?",
                listOf(
                    "Alrededor del eje perpendicular de la barra",
                    "Alrededor del eje longitudinal de la barra",
                    "Alrededor del plano en el que está contenida la barra"
                ), 1
            ),
            Question(
                "¿Qué distancia hay que tomar para calcular el" +
                        " momento torsor aplicado a la barra?",
                listOf(
                    "Desde la carga hasta la galga extensiométrica",
                    "Desde la carga hasta el extremo inicial del brazo de palanca",
                    "Desde la carga hasta el extremo izquierdo de la barra"
                ), 1
            )
        )

    private val pandeoQuestions = listOf(
        Question(
            "La carga crítica de Euler depende del límite elástico del material.",
            listOf("Verdadero", "Falso"), 1
        ),
        Question(
            "La barra del experimento pandea siempre hacia el mismo lado y" +
                    " no se puede cambiar la dirección de la deformación.",
            listOf("Verdadero", "Falso"), 1
        ),
        Question(
            "Un elemento sometido a compresión pandeará en el plano que" +
                    " presente menor rigidez a la flexión.",
            listOf("Verdadero", "Falso"), 0
        ),
        Question(
            "La longitud de pandeo de una viga depende de los apoyos que tenga.",
            listOf("Verdadero", "Falso"), 0
        )
    )

    fun getQuestionList(labType: BaseLab.LabType): List<Question> {
        return when (labType) {
            BaseLab.LabType.TORSION -> torsionQuestions
            BaseLab.LabType.PANDEO -> pandeoQuestions
            else -> throw IllegalArgumentException("Lab type not implemented yet")
        }
    }
}