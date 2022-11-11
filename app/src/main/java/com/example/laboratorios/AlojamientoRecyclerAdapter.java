package com.example.laboratorios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laboratorios.databinding.FilaAlojamientosRecyclerBinding;
import com.example.laboratorios.model.Alojamiento;
import com.example.laboratorios.model.Departamento;
import com.example.laboratorios.model.Habitacion;

import java.util.List;


public class AlojamientoRecyclerAdapter extends RecyclerView.Adapter<AlojamientoRecyclerAdapter.AlojamientoViewHolder>{

    private List<Alojamiento> alojamientos;
    private Alojamiento alojamiento;

    public static class AlojamientoViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        TextView nombreAlojamiento, precioAlojamiento;
        ImageView imagenAlojamiento;
        ToggleButton botonFavorito;

        public AlojamientoViewHolder(View v){
            super(v);
            card = v.findViewById(R.id.cardViewAlojamientos);
            nombreAlojamiento = v.findViewById(R.id.textViewNombreAlojamiento);
            precioAlojamiento = v.findViewById(R.id.textViewPrecioAlojamiento);
            imagenAlojamiento = v.findViewById(R.id.imageViewAlojamiento);
            botonFavorito = v.findViewById(R.id.toggleButtonFavorito);
        }
    }

    public AlojamientoRecyclerAdapter(List<Alojamiento> myDataSet){
        alojamientos = myDataSet;
    }

    @NonNull
    @Override
    public AlojamientoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_alojamientos_recycler,parent,false);
        AlojamientoViewHolder avh = new AlojamientoViewHolder(v);
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull AlojamientoViewHolder holder, int position) {
        alojamiento = alojamientos.get(position);

        holder.card.setTag(position);
        holder.nombreAlojamiento.setTag(position);
        holder.imagenAlojamiento.setTag(position);
        holder.precioAlojamiento.setTag(position);
        holder.botonFavorito.setTag(position);

        //TextViews
        holder.nombreAlojamiento.setText(alojamiento.getTitulo());
        holder.precioAlojamiento.setText("Precio: "+alojamiento.getPrecioBase().toString());

        //ImageView
        if(alojamiento.getClass()==Departamento.class){
            holder.imagenAlojamiento.setImageResource(R.drawable.departamento);
        } else if(alojamiento.getClass()==Habitacion.class){
            holder.imagenAlojamiento.setImageResource(R.drawable.hotelroom);
        }

        //ToggleButton
        if(alojamiento.getFavorito()){
            holder.botonFavorito.setChecked(true);
        } else{
            holder.botonFavorito.setChecked(false);
        }

        holder.botonFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alojamiento = alojamientos.get(holder.getAdapterPosition());
                if(holder.botonFavorito.isChecked()){
                    alojamiento.setFavorito(true);
                    holder.botonFavorito.setChecked(true);
                } else{
                    alojamiento.setFavorito(false);
                    holder.botonFavorito.setChecked(false);
                }
            }
        });

        //CardView
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alojamiento = alojamientos.get(holder.getAdapterPosition());
                Bundle args = new Bundle();
                args.putSerializable("alojamiento",alojamiento);
                Navigation.findNavController(view).navigate(R.id.action_resultadoBusquedaFragment_to_detalleAlojamientoFragment, args);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alojamientos.size();
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

}
