package uca.esi.manual.models

data class UserListResponse(
    var users: MutableMap<String, String>? = null,
    var exception: Exception? = null
)
