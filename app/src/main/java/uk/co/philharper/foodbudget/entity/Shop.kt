package uk.co.philharper.foodbudget.entity

import com.google.firebase.Timestamp
import java.util.*

class Shop(var location: String = "", var price: Float = 0f, var date: Timestamp = Timestamp(Date())) {
}