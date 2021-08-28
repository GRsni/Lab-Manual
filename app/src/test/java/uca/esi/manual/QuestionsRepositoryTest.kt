package uca.esi.manual

import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import uca.esi.manual.models.labs.BaseLab
import uca.esi.manual.screens.questionary.repository.QuestionsRepository

class QuestionsRepositoryTest {

    @Test
    fun questionsRepository_pandeoLab_returnsCorrectList() {
        val questionsRepository = QuestionsRepository()

        assertEquals(
            "La carga crítica de Euler depende del límite elástico del material.",
            questionsRepository.getQuestionList(BaseLab.LabType.PANDEO)[0].title
        )
    }

    @Test
    fun questionsRepository_torsionLab_returnsCorrectList() {
        val questionsRepository = QuestionsRepository()

        assertEquals(
            "¿Para el cálculo teórico de la deformada por torsión, " +
                    "qué valor de los siguiente hay que aplicar?",
            questionsRepository.getQuestionList(BaseLab.LabType.TORSION)[0].title
        )
    }

    @Test
    fun questionsRepository_traccionLab_throwsException() {
        val questionsRepository = QuestionsRepository()

        assertThrows(IllegalArgumentException::class.java) {
            questionsRepository.getQuestionList(BaseLab.LabType.TRACCION)
        }
    }

    @Test
    fun questionsRepository_flexionLab_throwsException() {
        val questionsRepository = QuestionsRepository()

        assertThrows(IllegalArgumentException::class.java) {
            questionsRepository.getQuestionList(BaseLab.LabType.FLEXION)
        }
    }
}