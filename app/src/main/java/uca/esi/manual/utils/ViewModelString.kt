package uca.esi.manual.utils

import android.content.Context
import androidx.annotation.StringRes

class ViewModelString private constructor(
    private val string: String?,
    @StringRes private val stringResId: Int = 0,
    private val args: ArrayList<Any>?
) {

    //simple string constructor
    constructor(string: String) : this(string, 0, null)


    constructor(@StringRes stringResId: Int) : this(
        null,
        stringResId,
        null
    )

    //convenience constructor for most common cases with one string or int var arg
    constructor(@StringRes stringResId: Int, stringVar: String) : this(
        null,
        stringResId,
        arrayListOf(stringVar)
    )

    constructor(@StringRes stringResId: Int, intVar: Int) : this(
        null,
        stringResId,
        arrayListOf(intVar)
    )

    //constructor for multiple var args
    constructor(@StringRes stringResId: Int, args: ArrayList<Any>) : this(null, stringResId, args)

    fun resolve(context: Context): String {
        return when {
            string != null -> string
            args != null -> return context.getString(stringResId, *args.toArray())
            else -> context.getString(stringResId)
        }
    }
}