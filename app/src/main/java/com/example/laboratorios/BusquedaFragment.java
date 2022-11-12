package com.example.laboratorios;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.laboratorios.databinding.FragmentBusquedaBinding;
import com.example.laboratorios.model.Ciudad;
import com.example.laboratorios.repo.CiudadRepository;
import com.google.android.material.slider.Slider;

import java.util.Arrays;
import java.util.List;

public class BusquedaFragment extends Fragment {

    //Constantes
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //Atributos
    private String mParam1, mParam2;
    private FragmentBusquedaBinding binding;
    private NumberPicker npCapacidad, npCiudad;
    private CiudadRepository cr;
    private List<Ciudad> ciudades;
    private String[] stringCiudades;
    private SeekBar sbPrecio;
    private Button botonResetear, botonBuscar;
    private ToggleButton tbDepto, tbHotel;
    private Slider sliderPrecio;
    private Switch switchWifi;

    //Constructores
    public BusquedaFragment() {
        // Required empty public constructor
    }

    public static BusquedaFragment newInstance(String param1, String param2) {
        BusquedaFragment fragment = new BusquedaFragment();
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
        binding = FragmentBusquedaBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        //NumberPicker Capacidad
        npCapacidad = binding.numberPickerCapacidad;
        npCapacidad.setMinValue(1);
        npCapacidad.setMaxValue(6);
        //NumberPicker Ciudad
        cr = new CiudadRepository();
        ciudades = cr.listaCiudades();
        stringCiudades = new String[ciudades.size()];
        for(int i=0; i<ciudades.size(); i++){
            stringCiudades[i] = ciudades.get(i).toString();
        }
        npCiudad = binding.numberPickerCiudad;
        npCiudad.setMinValue(0);
        npCiudad.setMaxValue(ciudades.size()-1);
        npCiudad.setDisplayedValues(stringCiudades);
        //Button Resetear
        tbDepto = binding.toggleButtonDepto;
        tbHotel = binding.toggleButtonHotel;
        botonResetear = binding.buttonResetear;
        sliderPrecio = binding.sliderPrecio;
        switchWifi = (Switch) binding.switchWifi;
        botonResetear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                tbDepto.setChecked(false);
                tbHotel.setChecked(false);
                sliderPrecio.setValue(20000);
                switchWifi.setChecked(false);
                npCapacidad.setValue(0);
                npCiudad.setValue(0);
            }
        });
        Button pref = binding.buttonPreferencias;
        pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BusquedaFragment.this).navigate(R.id.action_busquedaFragment_to_settingsFragment);
            }
        });
        //Button Buscar
        botonBuscar = binding.buttonBuscar;
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BusquedaFragment.this).navigate(R.id.action_busquedaFragment_to_resultadoBusquedaFragment);
            }
        });
        return view;
    }



}
