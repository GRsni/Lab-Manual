package uca.esi.manual.models.labs

import uca.esi.manual.models.Constants

/**
 * Lab factory i
 *
 * @constructor Create empty Lab factory i
 */
interface LabFactoryI {
    /**
     * Create from lab type
     *
     * @param userId
     * @param labType
     * @return
     */
    fun createFromLabType(userId: String, labType: Int): BaseLab
}

/**
 * Lab factory
 *
 * @constructor Create empty Lab factory
 */
class LabFactory : LabFactoryI {
    /**
     * Create from lab type
     *
     * @param userId
     * @param labType
     */
    override fun createFromLabType(userId: String, labType: Int) =
        when (labType) {
            Constants.TORSION_LAB_ID -> TorsionLab(userId)
            Constants.PANDEO_LAB_ID -> PandeoLab(userId)
            //3 and 4 are not implemented
            else -> throw IllegalArgumentException("Could not create a Lab instance")
        }
}