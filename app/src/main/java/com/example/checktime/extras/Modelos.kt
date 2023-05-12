package com.example.app_checktime.extras

class Modelos {
    //LISTO
    data class RespuestaLogin(
        var acceso:String,
        var error:String,
        var token:String,
    )

    data class Registro(
        var id:Int,
        var name:String,
        var email:String,
        var password:String,
        var perfil:String,
        var created_at:String,
        var updated_at:String
    )

    data class Operador(
        var id: Int,
        var id_personal: Int,
        var id_unidad: Int,
        var status_operador: String,
        val nombre_personal: String,
        var apaterno_personal: String,
        var amaterno_personal: String,
        var cve_unidad:String,
        val placas_unidad: String,
        var created_at:String,
        var updated_at:String
    )

    //LISTO
    data class Unidad(
        var id: Int,
        var cve_unidad:String,
        var placas_unidad:String,
        var modelo_unidad:String,
        var color_unidad:String,
        var created_at:String,
        var updated_at:String
    ){
        override fun toString(): String {
            return cve_unidad + " " + placas_unidad
        }
    }

    //LISTO
    data class Personal(
        var id: Int,
        var cve_personal: String,
        var nombre_personal: String,
        var apaterno_personal: String,
        var amaterno_personal: String,
        var edad_personal: String,
        var created_at:String,
        var updated_at:String
    ){
        override fun toString(): String {
            return nombre_personal + " " + apaterno_personal + " " + amaterno_personal
        }
    }

    data class OperadorUnidad(
        var id: Int,
        var cve_unidad: String,
        var placas_unidad: String,
        var nombre_personal: String,
        var apaterno_personal: String,
        var amaterno_personal: String
    ){
        override fun toString(): String {
            return nombre_personal + " " + apaterno_personal + " " + placas_unidad
        }
    }
    data class Asignacion(
        var id: Int,
        var id_personal: Int,
        var id_unidad: Int,
        var status_operador: String,
        var cve_unidad: String,
        var id_asignacion:Int,
        var placas_unidad: String,
        var nombre_personal: String,
        var apaterno_personal: String,
        var created_at:String,
        var updated_at:String
    )
    {
        override fun toString(): String {
            return nombre_personal + " " + placas_unidad
        }
    }

}