package com.sfkao.pokeviewerbackend.backend.endpoint;

import com.sfkao.pokeviewerbackend.backend.modelo.Equipo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetEquipoTest {

    @Autowired
    GetEquipo equipo;

    @Test
    public void registrarEquipo(){
        for(int i = 0; i < 100000; i++){
            System.out.println(equipo.saveEquipo("Prueba","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",1,2,3,4,5,6));
        }
    }

}