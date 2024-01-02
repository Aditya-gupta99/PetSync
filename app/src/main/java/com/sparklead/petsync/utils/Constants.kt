package com.sparklead.petsync.utils

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import java.util.Date

object Constants {

    const val SHARED_PREFERENCE = "PetSync"

    const val TOKEN = "token"

    const val SERVER_KEY =
        "AAAAOqBZjiA:APA91bE5BugAfPwEZQKIamtS0hkC_zEr0Mg-ygmDofsDlBJXx6JDcj_-IlJZorIb1E7AcLqqenxwo386RudtlY5NFhR1a9XWFmu16kZ1xocK4-ULEhLYNRBECVoMop40kunDxBN7JVHz"

    fun convertTimestampToDate(timestamp: String): Pair<String, String> {
        val sdfDate = SimpleDateFormat("dd/MM/yy")
        val sdfTime = SimpleDateFormat("hh:mm a")
        val netDate = Date(timestamp.toLong())
        val date = sdfDate.format(netDate)
        val time = sdfTime.format(netDate)
        return Pair(date.toString(), time.toString())
    }

    fun timeDifference(timestamp: String): String {
        val currentTime = System.currentTimeMillis()

        val oldDateTime =
            LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.toLong()), ZoneOffset.UTC)
        val currentDateTime =
            LocalDateTime.ofInstant(Instant.ofEpochMilli(currentTime), ZoneOffset.UTC)
        val minutesDiff = ChronoUnit.MINUTES.between(oldDateTime, currentDateTime)
        return when {
            minutesDiff < 1 -> "just now"
            minutesDiff == 1L -> "1 minute ago"
            minutesDiff < 60 -> "$minutesDiff minutes ago"
            minutesDiff < 120 -> "1 hour ago"
            else -> "${minutesDiff / 60} hours ago"
        }
    }
}