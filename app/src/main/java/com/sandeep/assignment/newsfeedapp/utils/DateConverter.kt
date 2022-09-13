package com.sandeep.assignment.newsfeedapp.utils

import java.text.SimpleDateFormat
import java.util.*

class DateConverter {
    /**
     * Static method for converting date format
     * @param strDate : instance of input date
     */
    companion object{
        fun changeDateFormat(strDate: String?): String {
            if(strDate.isNullOrEmpty()){
                return EMPTY_STR
            }
            return try{
                val sourceSdf = SimpleDateFormat(SOURCE_DATE_PATTERN_STR, Locale.getDefault())
                val requiredSdf = SimpleDateFormat(TARGET_DATE_PATTERN_STR, Locale.getDefault())
                requiredSdf.format(sourceSdf.parse(strDate))
            }catch (ex: Exception){
                ""
            }
        }
    }
}