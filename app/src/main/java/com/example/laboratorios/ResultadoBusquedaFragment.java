package com.example.laboratorios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.laboratorios.databinding.FragmentResultadoBusquedaBinding;

public class ResultadoBusquedaFragment extends Fragment {

    //Constantes
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //Atributos
    private String mParam1;
    private String mParam2;
    private FragmentResultadoBusquedaBinding binding;

    //Constructores
    public ResultadoBusquedaFragment() {
        // Required empty public constructor
    }

    public static ResultadoBusquedaFragment newInstance(String param1, String param2) {
        ResultadoBusquedaFragment fragment = new ResultadoBusquedaFragment();
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
        binding = FragmentResultadoBusquedaBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

}
