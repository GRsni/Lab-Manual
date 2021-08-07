package uca.esi.manual.screens.examination.repository.database

import uca.esi.manual.models.labs.BaseLab

interface DatabaseHandlerI {
    fun uploadData(lab: BaseLab)
}