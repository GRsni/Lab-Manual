package uca.esi.manual.models.labs

interface LabFactoryI {
    fun createFromLabType(userId: String, labType: Int): BaseLab
}

class LabFactory : LabFactoryI {
    override fun createFromLabType(userId: String, labType: Int) =
        when (labType) {
            1 -> TorsionLab(userId)
            2 -> PandeoLab(userId)
            //3 and 4 are not implemented
            else -> throw IllegalArgumentException("Could not create a Lab instance")
        }
}