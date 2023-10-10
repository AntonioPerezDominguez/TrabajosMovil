package com.example.blocdenotas.modelo

import java.util.Date

sealed class notas(
    var imagen:Int?,
    var titulo:String,
    var detalles:String
){
    object nota1:notas(
        null,
        "Redes",
        "practica vlsm ipv4 y ipv6"
    )
    object nota2:notas(
        null,
        "Redes",
        "resumen unidad 5"
    )
    object nota3:notas(
        null,
        "Programacion web",
        "Terminar ejercicio de clase"
    )
    object nota4:notas(
        null,
        "Ingles",
        "practicar speaking 30 min"
    )
    object nota5:notas(
        null,
        "Gym",
        "pagar la mensualidad"
    )
    object nota6:notas(
        null,
        "Gym",
        "Lunes pecho, tricep, hombro"
    )
    object nota7:notas(
        null,
        "Gym",
        "Martes espalda, bicep, antebrazo"
    )
    object nota8:notas(
        null,
        "Gym",
        "Martes espalda, bicep, antebrazo"
    )
    object nota9:notas(
        null,
        "Movil",
        "terminar el proyecto de bloc de notas"
    )
    object nota10:notas(
        null,
        "Movil",
        "hacer la lista de requerimientos"
    )
    object nota11:notas(
        null,
        "Gym",
        "Martes espalda, bicep, antebrazo"
    )
    object nota12:notas(
        null,
        "Gym",
        "Martes espalda, bicep, antebrazo"
    )
    object nota13:notas(
        null,
        "Gym",
        "Martes espalda, bicep, antebrazo"
    )
    object nota14:notas(
        null,
        "Gym",
        "Martes espalda, bicep, antebrazo"
    )
    object nota15:notas(
        null,
        "Gym",
        "Martes espalda, bicep, antebrazo"
    )
    object nota16:notas(
        null,
        "Gym",
        "Martes espalda, bicep, antebrazo"
    )
}
