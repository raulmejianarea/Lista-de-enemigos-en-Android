package com.example.alumnos.listaenemigos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

public class NuevoEnemigo extends Fragment {

    /* Variables publicas  */
    ImageView fotoEnemigo;
    Button boton_guardar_enemigo;
    Button boton_añadir_foto;
    EditText nombreEnemigo;
    RatingBar nivelOdio;

    /* Variable que almacena la foto de camara */
    String foto_guardada;

    /* Constante necesaria para realizar la peticion de la camara*/
    static final int CODIGO_REQUEST = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nuevo_enemigo,container,false);

        /* Asignacion de las referencias XML a los elementos */
        fotoEnemigo = view.findViewById(R.id.imagenEnemigo);
        nombreEnemigo = view.findViewById(R.id.nombreEnemigo);
        boton_guardar_enemigo = view.findViewById(R.id.anadirEnemigo);
        boton_añadir_foto = view.findViewById(R.id.anadirFotoEnemigo);
        nivelOdio = view.findViewById(R.id.nivel_de_odio);

        /* Evento onClick en este elemento */
        boton_añadir_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if(intent.resolveActivity(getActivity().getPackageManager()) != null) {

                    getActivity().startActivityForResult(intent, CODIGO_REQUEST);
                }

            }
        });

        /* Evento onClick en este elemento */
        boton_guardar_enemigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(foto_guardada !=null){

                   sendNombre();
               }

            }
        });

        return view;
    }

//    /**
//     * Metodo que manda la imagen guardada a la previsualizacion de la foto del enemigo.
//     * Recibe un uri
//     */
    void sendImage(Uri uri) {

        foto_guardada = uri.toString();
        fotoEnemigo.setImageURI(uri);
    }

    /* Metodo que obtiene el nombre del campo de texto del fragment y lo manda al MainActivity */
    void sendNombre(){

        String nombre = nombreEnemigo.getText().toString();
        float odio = nivelOdio.getRating();
        Enemigo enemigo = new Enemigo(foto_guardada, nombre, odio);

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.sendEnemigo(enemigo);

    }

}
