package com.example.app_checktime.extras

class GlobalVariables {

    companion object{
        val url_app = "http://192.168.1.74:8000/"
        val url_login = url_app + "api/login"
        val url_registro = url_app + "api/new_user"

        //UNIDAD
        val url_get_unidad = url_app + "api/get_unidad"
        val url_add_unidad= url_app + "api/new_unidad"
        val url_delete_unidad= url_app + "api/delete_unidad"

        //PERSONAL
        val url_get_trabajador = url_app + "api/get_trabajador"
        val url_add_trabajador= url_app + "api/new_trabajador"
        val url_delete_trabajador= url_app + "api/delete_trabajador"

        //OPERADORES
        val url_get_operador = url_app + "api/get_operador"
        val url_add_operador= url_app + "api/new_operador"
        val url_delete_operador= url_app + "api/delete_operador"

        //CHECK
        val url_get_check = url_app + "api/get_check"
        val url_add_check= url_app + "api/new_check"
        val url_delete_check= url_app + "api/delete_check "

    }

}