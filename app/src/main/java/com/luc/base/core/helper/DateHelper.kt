package com.luc.base.core.helper

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    private fun parseDate(time: String?, inputPattern: String, outputPattern: String): String {
        if (time.isNullOrEmpty()) return ""
        val locale = Locale.US
        val inputFormat = SimpleDateFormat(inputPattern, locale)
        val outputFormat = SimpleDateFormat(outputPattern, locale)
        val date: Date?
        var str: String? = null
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date!!)
        } catch (e: ParseException) {
            e.localizedMessage
        }
        return str ?: ""
    }

    fun reformat(dateText: String?, inputPattern: String, outputPattern: String): String? {
        return parseDate(dateText, inputPattern, outputPattern)
    }
}
