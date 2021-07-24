package uca.esi.manual

import com.google.common.hash.Hashing
import org.jetbrains.annotations.NotNull
import java.nio.charset.StandardCharsets


@NotNull
fun getSHA256HashedString(plain: String): String {
    return Hashing.sha256().hashString(plain, StandardCharsets.UTF_8).toString()
}