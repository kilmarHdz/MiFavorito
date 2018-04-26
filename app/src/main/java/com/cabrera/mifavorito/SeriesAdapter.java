package com.cabrera.mifavorito;

/**
 * Created by Kilmar Cabrera on 25/4/2018.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder> {
    private ArrayList<Serie>  series;
    //Variable de estado
    private static boolean fav=false;
    //Se encarga de guardar el contexto en el cual esta creado
    private Context mContext;
    private int lastPosition = -1;

    @Override
    public SeriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardview,parent,false);
        return (new SeriesViewHolder(v));
    }

    @Override
    public void onBindViewHolder(final SeriesViewHolder holder, final int position) {
        //Cuando se añade visualmente se agrega el contenido
        holder.name.setText(series.get(position).getName());
        holder.img.setImageResource(series.get(position).getImg());
        //Se verifica que si esta en el estado de favoritos y asigna la imagen respectiva
        if(series.get(position).isFav())
            holder.but.setImageResource(R.drawable.on);
        else
            holder.but.setImageResource(R.drawable.off);
        //Listener de boton que se encarga de cambiar estados del adaptor y llamar metodos que cambian las listas
        holder.but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Verifica el estado de fav del objeto en ese cardview *posicion* y cambia la imagen
                if (fav(position)){
                    holder.but.setImageResource(R.drawable.on);
                    //Pregunta si se encuentra en activity en caso de estarlo llama una de las funciones de Main
                    if(mContext instanceof MainActivity){
                        //Funcion del main que agrega a la lista de favoritos
                        ((MainActivity)mContext).Change(series.get(position));
                    }
                }
                //Lo mismo que la seccion del if
                else {
                    holder.but.setImageResource(R.drawable.off);
                    if(mContext instanceof MainActivity){
                        //Funcion del main que elimina de favoritos
                        ((MainActivity)mContext).Remove(series.get(position).getName());
                    }
                }

            }
        });
        //Se anima
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return series.size();
    }

    public static class SeriesViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        TextView name;
        ImageView img;
        ImageButton but;
        //Se agregan vistas a los holders
        public SeriesViewHolder(View itemView){
            super(itemView);
            card=itemView.findViewById(R.id.card_view);
            name=itemView.findViewById(R.id.name);
            img=itemView.findViewById(R.id.img);
            but=itemView.findViewById(R.id.status);
        }
    }
    //Pregunta el estado del obejto serie y cambia a su estdo inverso
    public boolean fav(final int position) {
        series.get(position).setFav(!series.get(position).isFav());
        return series.get(position).isFav();
    }
    //Recibe el arreglo y contexto
    public SeriesAdapter(ArrayList<Serie> series,Context context){
        this.series=series;
        this.mContext=context;
    }
    //Cambia el estado a verdadero
    public void SetTrue(){
        fav=true;
    }
    //Cambia el estado a falso
    public void SetFalse(){
        fav=false;
    }
    //Da animacion a los cardview
    private void setAnimation(View viewToAnimate, int position)
    {
        //Verifica que sea el ulimo de la posicion
        if (position > lastPosition)
        {
            //Cuando lo es le agrega un efecto de trasicion en el Bind
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.fade_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
    //Se saca el valor del estado
    public boolean isfav() {
        return fav;
    }
}