package com.example.alumnos.listaenemigos;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Enemigos extends ArrayList<Enemigo> {

//    /**
//     * Metodo que crea una nueva clase Gson y convierte los datos en Json.
//     */
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


    static public Enemigos fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Enemigos.class);
    }

}
