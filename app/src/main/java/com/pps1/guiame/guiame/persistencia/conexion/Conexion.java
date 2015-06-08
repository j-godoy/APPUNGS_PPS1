package com.pps1.guiame.guiame.persistencia.conexion;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Conexion
{

    // IP localhost
    // private static final String URL = "http://10.0.2.2/guiame/";
    // ip Agus
    // private static final String URL = "http://181.16.65.149/guiame/";

    //IP hostinger
    private static final String URL = "http://pps1ungs.esy.es/guiame/";

    public static String getPHPResult(String phpName)
    {
        HttpClient cliente = new DefaultHttpClient();
        HttpContext contexto = new BasicHttpContext();
        HttpGet httpget = new HttpGet(URL+phpName);
        String resultado=null;
        try
        {
            HttpResponse response = cliente.execute(httpget,contexto);
            HttpEntity entity = response.getEntity();
            resultado = EntityUtils.toString(entity, "UTF-8");
            Log.d("RESULTADO", resultado);
        }
        catch (Exception e)
        {
            Log.d("EXCEPTION AL LEER",e+"");
            // TODO: handle exception
        }
        return resultado;
    }

    public static String enviarPost(Map<String, String> datos, String phpName) throws IOException {

        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpPost httpPost = new HttpPost(URL+phpName);
        Log.d("url",URL+phpName);
        HttpResponse response = null;
        String resultado = "";
            List<NameValuePair> params = new ArrayList<NameValuePair>(3);
            for(String nombreVariable : datos.keySet())
            {
                Log.d(nombreVariable, datos.get(nombreVariable));
                params.add(new BasicNameValuePair(nombreVariable, datos.get(nombreVariable)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            response = httpClient.execute(httpPost, localContext);

            HttpEntity entity = response.getEntity();
            resultado = EntityUtils.toString(entity, "UTF-8");
            Log.d("RESULTADO",resultado);

        return resultado;
    }

}
