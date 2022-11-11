package com.example.laboratorios;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.laboratorios.databinding.FragmentDetalleAlojamientoBinding;
import com.example.laboratorios.model.Alojamiento;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DetalleAlojamientoFragment extends Fragment {

    //Constantes
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //Atributos
    private String mParam1;
    private String mParam2;
    private FragmentDetalleAlojamientoBinding binding;
    private TextView nombreAlojamiento, descripcion, capacidad, precioBase, fechaIng, fechaSal, tvCantPersonas, precioTotal;
    private ToggleButton favorito;
    private Switch switchReserva;
    private NumberPicker npCantPersonas;
    private Button reservar;
    private Calendar ingreso, salida;

    //Constructores
    public DetalleAlojamientoFragment() {
        // Required empty public constructor
    }

    public static DetalleAlojamientoFragment newInstance(String param1, String param2) {
        DetalleAlojamientoFragment fragment = new DetalleAlojamientoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetalleAlojamientoBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        //Widgets
        nombreAlojamiento = binding.textViewAlojamiento;
        descripcion = binding.textViewDescripcionAlojamiento;
        capacidad = binding.textViewCapacidadAlojamiento;
        precioBase = binding.textViewPrecioBaseAlojamiento;
        fechaIng = binding.textViewFechaIngreso;
        fechaSal = binding.textViewFechaSalida;
        tvCantPersonas = binding.textViewCantidadPersonas;
        precioTotal = binding.textViewPrecioTotalAlojamiento;
        favorito = binding.toggleButtonAlojamientoFavorito;
        switchReserva = binding.switchReservar;
        npCantPersonas = binding.numberPickerCantidadPersonas;
        reservar = binding.buttonRealizarReserva;

        //Bundle para recuperar informacion
        Bundle infoAlojamiento = getArguments();
        Alojamiento alojam = (Alojamiento) infoAlojamiento.getSerializable("alojamiento");
        if(alojam!=null){
            Alojamiento finalAlojam = alojam;
            //ToggleButton Favorito
            if(alojam.getFavorito()){
                favorito.setChecked(true);
            } else{
                favorito.setChecked(false);
            }

            favorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(favorito.isChecked()){
                        finalAlojam.setFavorito(true);
                        favorito.setChecked(true);
                    } else{
                        finalAlojam.setFavorito(false);
                        favorito.setChecked(false);
                    }
                }
            });
            //TextView Alojamiento
            nombreAlojamiento.setText(alojam.getTitulo());
            //TextView Descripcion
            descripcion.setText(alojam.getDescripcion());
            //TextView Capacidad
            capacidad.setText("Capacidad: "+alojam.getCapacidad());
            //TextView PrecioBase
            precioBase.setText("Precio base: "+alojam.getPrecioBase().toString());
            //Switch Reserva
            switchReserva.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(switchReserva.isChecked()){
                        fechaIng.setVisibility(View.VISIBLE);
                        fechaSal.setVisibility(View.VISIBLE);
                        tvCantPersonas.setVisibility(View.VISIBLE);
                        npCantPersonas.setVisibility(View.VISIBLE);
                        precioTotal.setVisibility(View.VISIBLE);
                        reservar.setVisibility(View.VISIBLE);
                    } else{
                        fechaIng.setVisibility(View.GONE);
                        fechaSal.setVisibility(View.GONE);
                        tvCantPersonas.setVisibility(View.GONE);
                        npCantPersonas.setVisibility(View.GONE);
                        precioTotal.setVisibility(View.GONE);
                        reservar.setVisibility(View.GONE);
                    }
                }
            });
            //NumberPicker Cantidad de Personas
            npCantPersonas.setMinValue(1);
            npCantPersonas.setMaxValue(alojam.getCapacidad());
            //TextView PrecioTotal
            precioTotal.setText("Precio total: ");
            //TextView Fecha de Ingreso
            ingreso = Calendar.getInstance();
            fechaIng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Calendar calendario = Calendar.getInstance();
                    int yy = calendario.get(Calendar.YEAR);
                    int mm = calendario.get(Calendar.MONTH);
                    int dd = calendario.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            String fecha = String.valueOf(year) +"-"+String.valueOf(monthOfYear)
                                    +"-"+String.valueOf(dayOfMonth);
                            fechaIng.setText(fecha);
                            ingreso.set(year, monthOfYear, dayOfMonth);
                            fechaSal.setEnabled(true);
                        }
                    }, yy, mm, dd);
                    datePicker.getDatePicker().setMinDate(calendario.getTimeInMillis());
                    datePicker.show();
                }
            });
            //TextView Fecha de Salida
            salida = Calendar.getInstance();
            fechaSal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int yy = ingreso.get(Calendar.YEAR);
                    int mm = ingreso.get(Calendar.MONTH);
                    int dd = ingreso.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            String fecha = String.valueOf(year) +"-"+String.valueOf(monthOfYear)
                                    +"-"+String.valueOf(dayOfMonth);
                            fechaSal.setText(fecha);
                            salida.set(year,monthOfYear,dayOfMonth);
                            precioTotal.setText("Precio total: "+(alojam.getPrecioBase()*calcularDiferencia(ingreso,salida)));
                        }
                    }, yy, mm, dd);
                    datePicker.getDatePicker().setMinDate(ingreso.getTimeInMillis());
                    datePicker.show();
                }
            });
            //Button Reservar
            reservar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(camposCompletos(fechaIng, fechaSal)){
                        Toast toast = Toast.makeText(getContext(),"Reserva realizada", Toast.LENGTH_SHORT);
                        toast.show();
                    } else{
                        Toast toast = Toast.makeText(getContext(),"Por favor, completar los campos", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
        }

        return view;
    }

    public Boolean camposCompletos(TextView ingreso, TextView salida) {
        if (ingreso.getText() == getResources().getString(R.string.textView_FechaIngreso) ||
                salida.getText() == getResources().getString(R.string.textView_FechaSalida)) {
            return false;
        } else {
            return true;
        }
    }

    public long calcularDiferencia(Calendar ingreso, Calendar salida){
        long ing = ingreso.getTimeInMillis();
        long sal = salida.getTimeInMillis();
        long dif = sal - ing;
        long dias = TimeUnit.DAYS.convert(dif, TimeUnit.MILLISECONDS);
        return dias;
    }

}
