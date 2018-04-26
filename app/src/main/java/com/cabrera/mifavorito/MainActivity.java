package com.cabrera.mifavorito;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    SeriesAdapter adapter;
    ArrayList<Serie> series;
    ArrayList<Serie> favoritos;
    LinearLayoutManager lManager;
    Button series_button;
    Button favorite_button;
    View left,right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        left = findViewById(R.id.leftV);
        right = findViewById(R.id.rightV);
        favoritos = new ArrayList<>();
        //Se busca el layout que se va utilizar y se le asigna un adminstrador de recycler views
        rv = findViewById(R.id.recycler);
        rv.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        rv.setLayoutManager(lManager);
        //Se llena el arreglo de series
        prepareSeries();
        //Se asigna un adaptador con la lista series que tiene por contexto el activity
        adapter = new SeriesAdapter(series, this);
        rv.setAdapter(adapter);
        //Agregan Listener a los botones
        favorite_button = findViewById(R.id.favoritos_id);
        series_button = findViewById(R.id.series_id);
        series_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Se llama funcion para cambiar el estado de View
                resizeRight();
                //Se le indica al RecyclerView que cambia a modo general
                adapter.SetFalse();
                //Se le reasigna los objetos
                adapter = new SeriesAdapter(series, view.getContext());
                rv.setAdapter(adapter);
            }
        });
        favorite_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Se llama funcion para cambiar el estado de View
                resizeLeft();
                //Se le indica al RecyclerView que cambia a modo de favoritos
                adapter.SetTrue();
                //Se le reasigna los objetos
                adapter = new SeriesAdapter(favoritos, view.getContext());
                rv.setAdapter(adapter);
            }
        });
    }
    //Funcion que se encarga de crear y llenar la lista principal
    private void prepareSeries() {
        series = new ArrayList<>();
        series.add(new Serie("Game of Thrones","13", R.drawable.got,"TV show created By robert"));
        series.add(new Serie("Dexter","13", R.drawable.dt,"TV show created By robert"));
        series.add(new Serie("House of Cards","13", R.drawable.hc,"TV show created By robert"));
        series.add(new Serie("Vikings","13", R.drawable.vk,"TV show created By robert"));
        series.add(new Serie("Lucifer","13", R.drawable.lc,"TV show created By robert"));
    }
    //Se agrega un elemento a la lista de Favoritos
    public void Change(Serie gusta){
        favoritos.add(gusta);
    }
    //Funcion que se encarga de quitar el objeto de la lista favoritos
    public void Remove(String x){
        int counter=0;
        //Si encuentra el nombre se remueve de la lista
        for(Serie e : favoritos){
            if(e.getName() == x){
                break;
            }
            counter++;
        }
        favoritos.remove(counter);
        //Se refresca la lista de favoritos si se encuentra en el estado de solo favoritos
        if(adapter.isfav()){
            adapter = new SeriesAdapter(favoritos, this);
            rv.setAdapter(adapter);
        }
    }
    //Muesta el view de la derecha
    public void resizeLeft(){
        left.setVisibility(View.INVISIBLE);
        right.setVisibility(View.VISIBLE);
    }
    //Muestra el view de la izquierda
    public void resizeRight(){
        right.setVisibility(View.INVISIBLE);
        left.setVisibility(View.VISIBLE);
    }
}