package com.pps1.guiame.guiame.persistencia.dao;

import android.util.Log;

import com.pps1.guiame.guiame.dto.Curso;
import com.pps1.guiame.guiame.persistencia.conexion.Conexion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CursoDAO
{
    private final String PHP_NAME_CURSO_PERSONAL = "registrarCursoPersonalizado.php";
    private final String PHP_NAME_BORRADOR_CURSO = "borrarCurso.php";

    public void registrarCursoPersonalizado(Curso curso, Integer idUsuario) throws Exception {
        Map<String, String> datos = new HashMap<String, String>();
        datos.put("idUsuario",idUsuario.toString());
        datos.put("idCurso",curso.getId().toString());
        datos.put("nombre",curso.getNombre().toString());

        Conexion.enviarPost(datos, PHP_NAME_CURSO_PERSONAL);
    }

    public void eliminarCurso(Curso curso, Integer idUsuario) throws Exception {
        //La key del map deben ser los nombres de los campos en la tabla
        Map<String, String> datos = new HashMap<String, String>();
        datos.put("id_cursos",curso.getId().toString());
        datos.put("id_usuarios", idUsuario.toString());
        //datos.put("nomPersonalizado",nombreMateria);

        String result = Conexion.enviarPost(datos, PHP_NAME_BORRADOR_CURSO);
        Log.d("RESULT BORRAR", result);
    }
}
