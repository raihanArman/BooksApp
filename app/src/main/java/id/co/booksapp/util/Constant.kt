package id.co.booksapp.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object Constant {
    val BASE_URL = "https://choicestd.com/raihan/public/api/"
    val BASE_URL_IMAGE = "https://choicestd.com/raihan/public/images/"
//    val BASE_URL = "http://192.168.1.68:8000/api/"

    fun rupiahFormat(harga: Int): String{
        val kursIndonesia = DecimalFormat.getCurrencyInstance() as DecimalFormat
        val formatRp = DecimalFormatSymbols()

        formatRp.currencySymbol = "Rp."
        formatRp.monetaryDecimalSeparator = ','
        formatRp.groupingSeparator = '.'

        kursIndonesia.decimalFormatSymbols = formatRp

        return kursIndonesia.format(harga)
    }
}