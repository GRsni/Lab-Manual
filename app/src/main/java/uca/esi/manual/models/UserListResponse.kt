package uca.esi.manual.models

data class UserListResponse(
    var users: List<User>? = null,
    var exception: Exception? = null
)
