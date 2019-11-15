package com.example.alumnos.listaenemigos;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MiAdapter extends ArrayAdapter {

    /* Variables publicas */
    Context context;
    int itemLayout;
    ArrayList<Enemigo> datosEnemigos;

    public MiAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Enemigo> objects) {
        super(context, resource, objects);
        this.context = context;
        itemLayout = resource;
        datosEnemigos = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(itemLayout, parent, false);

        }

        /* Variable que almacena la referencia de imagenLista del adapter_layout */
        ImageView imagenEnemigo = convertView.findViewById(R.id.imagenLista);

        /* Se convierten los datos de imagen del ArrayList de Enemigo a una Uri */
        Uri uriTexto = Uri.parse(datosEnemigos.get(position).image);

        /* La Uri se asigna al elemento refenciado de XML */
        imagenEnemigo.setImageURI(uriTexto);

        /* Variable que almacena la referencia de nombreEnemigo del adapter_layout */
        TextView nombreEnemigo = convertView.findViewById(R.id.textoLista);

                /* Se comprueba si existe dicho elemento en el layout de XML asignado */
        if(nombreEnemigo != null){
            /* Si existe el elemento se asigna al elemento referenciado de XML */
            nombreEnemigo.setText(datosEnemigos.get(position).nombre);
        }

        /* Variable que almacena la referencia de nivelOdio del adapter_layout */
        TextView nivelOdio = convertView.findViewById(R.id.textoOdio);
        /* Se comprueba si existe dicho elemento en el layout de XML asignado */
        if(nivelOdio != null){
            /* Se almacena el valor de rating proveniente del ArraList de Enemigos */
            float odio = datosEnemigos.get(position).rating;
            /* Se convierte de float a String */
            String odioString = Float.toString(odio);
            /* Dicho String se asigna al elemento referenciado de XML */
            nivelOdio.setText( "Nivel de odio: " + odioString);
        }

        return convertView;
    }

}
