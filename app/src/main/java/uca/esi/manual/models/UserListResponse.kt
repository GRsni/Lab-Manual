package uca.esi.manual.models

/**
 * User list response
 *
 * @property users
 * @property exception
 * @constructor Create empty User list response
 */
data class UserListResponse(
    var users: MutableMap<String, String>? = null,
    var exception: Exception? = null
)
