package uk.co.philharper.foodbudget.entity

import com.google.firebase.Timestamp
import java.util.*

data class Shop(val location: String = "", var price: Float = 0f, var date: Timestamp = Timestamp(Date())) {
    var id = ""
}