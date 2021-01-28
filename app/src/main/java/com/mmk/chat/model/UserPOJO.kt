package com.mmk.chat.model

import kotlin.random.Random

/**
 * Created by mirzemehdi on 1/27/21
 */
data class UserPOJO(
    val id: Int= Random.nextInt(),
    val name: String = "",
    val surname: String = "",
    val profileImageUrl: String? = null,
    val isOnline: Boolean = false,
    val lastSeen: String? = null

) {
    val fullName:String="$name $surname"
}