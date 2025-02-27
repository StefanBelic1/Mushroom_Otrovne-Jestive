package hr.ferit.stefanbelic.mushroom.models

import kotlin.random.Random

data class MashroomInfo(
    var uid: String = generateRandomStringId(),
    var image: String = "",
    var title: String = ""
)

fun generateRandomStringId(): String {
    val characters = "abcdefghijklmnopqrstuvwxyz123456789"
    val random = Random
    val sb = StringBuilder(16)

    repeat(16) {
        val randomIndex = random.nextInt(characters.length)
        sb.append(characters[randomIndex])
    }

    return sb.toString()
}
