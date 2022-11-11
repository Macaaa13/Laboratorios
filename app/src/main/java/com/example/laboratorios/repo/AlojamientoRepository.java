package com.example.laboratorios.repo;

import com.example.laboratorios.model.Alojamiento;
import com.example.laboratorios.model.Departamento;
import com.example.laboratorios.model.Habitacion;
import com.example.laboratorios.model.Hotel;
import com.example.laboratorios.model.Ubicacion;

import java.util.List;

public class AlojamientoRepository {

    private static final Ubicacion ubicacion1 = new Ubicacion(-42.6,-38.3,"San Martin","1989",CiudadRepository._CIUDADES.get(0));
    private static final Ubicacion ubicacion2 = new Ubicacion(-42.25,-38.2,"Lopez y Planes","2007",CiudadRepository._CIUDADES.get(1));

    public static final List<Alojamiento> _ALOJAMIENTOS = List.of(
            new Departamento(1, "Depto 1", "Luminoso y amplio", 6, 300.0,true, 1500.0, 3,ubicacion1),
            new Habitacion(2, "Habitacion 2", "Espectacular suite",4, 1200.0, 2,1,false,new Hotel(1,"Hotel 1",3,ubicacion2)),
            new Departamento(3,"Depto2","Ideal y comodo", 4, 1000.0, true, 1300.0 , 2, ubicacion1)
    );

    public List<Alojamiento> listaCiudades(){
        return  _ALOJAMIENTOS;
    }

}
