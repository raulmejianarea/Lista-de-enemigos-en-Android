package com.example.alumnos.listaenemigos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.GridView;

public class GaleriaEnemigos extends Fragment {

    /* Variable que almacena el GridView*/
    GridView galeriaEnemigos;

    /* Variable que almacena el adapted personalizado */
    MiAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.galeria_enemigos,container,false);

        /* Referencia del elemento GridView contenido en el XML */
        galeriaEnemigos = view.findViewById(R.id.galeriaEnemigos);

        /* Se accede a la MainActivity y los datos del ArrayList de enemigos */
        MainActivity mainActivity = (MainActivity) getActivity();

        /* nuevo adapter personalizado */
        adapter = new MiAdapter(this.getActivity(), R.layout.galeria_layout, mainActivity.datosEnemigos);

        /* Al gridview le aplico mi adapter personalizado*/
        galeriaEnemigos.setAdapter(adapter);

        return view;
    }
}
