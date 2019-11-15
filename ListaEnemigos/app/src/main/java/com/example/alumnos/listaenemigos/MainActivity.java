package com.example.alumnos.listaenemigos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    /* Variables publicas de los elementos */
    ListView menu;
    DrawerLayout drawerLayout;
    NuevoEnemigo nuevo_Enemigo;

    /* Variable del menu lateral */
    ActionBarDrawerToggle drawerToggle;

    /* Variable del Fragment Manager*/
    FragmentManager manager;

    /* Variable que representa el valor inicial de fragment dentro de la aplicacion */
    Fragment fragment = null;

    /* ArrayList Enemigos */
    Enemigos datosEnemigos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Carga los datos guardados de una sesion anterior */
        datosEnemigos = cargar();

        /* Inicialización de un nuevo Array si no hay datos previos */
        if(datosEnemigos == null){
            datosEnemigos = new Enemigos();
        }

        /* Referencias de los elementos */
        menu = findViewById(R.id.menu);
        drawerLayout = findViewById(R.id.drawerLayout);

        /* Acceder al manager */
        manager = getSupportFragmentManager();

        /* Crea la navegación en barra */
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.abrirMenu, R.string.cerrarMenu);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* Permite seleccionar los elementos del menu */
        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cambiarPantalla(position);
            }
        });

        /* Muestra el fragment Top5 al abrir la aplicacion */
        cambiarPantalla(0);

    }


    public Enemigos getEnemigos() {

        return datosEnemigos;
    }


    void cambiarPantalla(int pantalla) {

        FragmentTransaction transaction;

        switch (pantalla) {

            case 0:
                fragment = new TopEnemigos();
                break;
            case 1:
                fragment = new NuevoEnemigo();
                break;
            case 2:
                fragment = new GaleriaEnemigos();
                break;

        }

        transaction = manager.beginTransaction();
        transaction.replace(R.id.contenedor, fragment);
        transaction.commit();
        drawerLayout.closeDrawer(Gravity.START);

    }


    public void sendEnemigo(Enemigo enemigo){

        datosEnemigos.add(enemigo);

    }


     // guarda los datos de enemigos en Json.

    public void guardar(Enemigos enemigo){
        String json = enemigo.toJson();
        SharedPreferences preferences = getSharedPreferences("Enemigos", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("enemigo", json);
        editor.commit();

    }


    Enemigos cargar() {
        SharedPreferences preferences = getSharedPreferences("Enemigos", MODE_PRIVATE);
        String json = preferences.getString("enemigo", "");
        return Enemigos.fromJson(json);

    }

    /* sirve para acceder a la camara del dispositivo */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == NuevoEnemigo.CODIGO_REQUEST && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            Bitmap imagen = (Bitmap) bundle.get("data");
            Uri uriImagen = guardarImagen(imagen);
            nuevo_Enemigo = (NuevoEnemigo) fragment;
            nuevo_Enemigo.sendImage(uriImagen);
        }
    }


    protected Uri guardarImagen(Bitmap imagen) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        imagen.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), imagen, "Titulo", null);
        return Uri.parse(path);
    }

    /* Permite desplegar el menu lateral */
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    /* Permite cerrar el menu lateral */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* Guarda los datos contenidos en datosEnemigos cuando se pausa o cierra la aplicacion */
    @Override
    protected void onPause() {
        super.onPause();
        guardar(datosEnemigos);
    }
}
