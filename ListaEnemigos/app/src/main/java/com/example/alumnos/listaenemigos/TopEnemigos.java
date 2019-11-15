package com.example.alumnos.listaenemigos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class TopEnemigos extends Fragment {

    /* Variables publicas de elementos */
    MiAdapter adapter;
    ListView lista;
    Enemigos datosEnemigos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_enemigos, container, false);

        /* Acceso al MainActivity */
        MainActivity mainActivity = (MainActivity) getActivity();
        /* Asignacion de la clase Enemigos */
        datosEnemigos = new Enemigos();
        /* Obtencion de los enemigos almacenados en el ArrayList datosEnemigos del MainActivity */
        datosEnemigos = mainActivity.getEnemigos();
        /* Asignacion de las referencias XML a los elementos */
        lista= view.findViewById(R.id.listaEmemigos);
        /* Declaracion del adapter personalizado y su asignacion al elemento */
        adapter = new MiAdapter(this.getActivity(), R.layout.adapter_layout, datosEnemigos);
        lista.setAdapter(adapter);

        return view;
    }
}
