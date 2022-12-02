package com.sfkao.pokeviewerbackend.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EquipoDao {

    public static final int KEYSIZE = 8;
    public static int MINCHAR = (int) 'A';
    public static int MAXCHAR = (int) 'Z';

    public static String KEYPARAM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public synchronized String getNewID(){
        try {
            String lastID = jdbcTemplate.queryForObject("SELECT id FROM Equipo ORDER BY id DESC  LIMIT 1", String.class);
            return getNextID(lastID);
        }catch (EmptyResultDataAccessException e) {
            return "00000000";
        }

    }


    public synchronized static String getNextID(String id){
        if(id.length()!= KEYSIZE)
            return null;
        int[] idArray = idAArray(id);
        boolean fin = false;
        int pos = idArray.length-1;
        while(!fin){
            if(pos==-1)
                return null;
            idArray[pos] = (idArray[pos]+1)%KEYPARAM.length();
            if(idArray[pos] == 0)
                pos--;
            else
                fin = true;
        }
        char[] returned = new char[idArray.length];
        for (int i = 0; i < returned.length; i++) {
            returned[i] = KEYPARAM.charAt(idArray[i]);
        }
        return new String(returned);

    }

    private static int[] idAArray(String id){
        int[] array = new int[id.length()];
        for(int i = 0; i < array.length;i++){
            int ascii = id.charAt(i);
            if(ascii>=48 && ascii<=57){
                array[i] = ascii-48;
            }else if(ascii>=65 && ascii<=90){
                array[i] = ascii-55;
            }else
                return null;
        }
        return array;
    }



}
