package br.com.cezarcruz.fleet.web.errors

data class FieldErrorVM(
    val objectName: String,
    val field: String,
    val message: String
)
