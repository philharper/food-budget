package uk.co.philharper.foodbudget.service

import uk.co.philharper.foodbudget.dao.PropertiesDao
import uk.co.philharper.foodbudget.entity.Properties
import uk.co.philharper.foodbudget.entity.Shop
import uk.co.philharper.foodbudget.entity.ShopCalculations

class PropertiesService {

    private val propertiesDao = PropertiesDao()

    fun saveProperties(properties: Properties) {
        propertiesDao.saveProperties(properties)
    }

    fun getProperties(callback: (List<Properties>) -> Unit) {
        propertiesDao.getProperties { document -> callback(document.toObjects(Properties::class.java)) }
    }

}