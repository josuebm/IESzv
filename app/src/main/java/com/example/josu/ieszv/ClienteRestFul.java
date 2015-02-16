package com.example.josu.ieszv;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by 2dam on 21/01/2015.
 */
public class ClienteRestFul {

    public static String delete(String url) throws IOException {
        HttpClient clienteHttp = new DefaultHttpClient();
        HttpDelete delete = new HttpDelete(url);
        delete.setHeader("content-type", "application/json");
        HttpResponse respuestaHttp = clienteHttp.execute(delete);
        String respuesta = EntityUtils.toString(respuestaHttp.getEntity());
        return respuesta;
    }

    public static String get(String url) throws IOException {
        HttpClient clienteHttp = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        get.setHeader("content-type", "application/json");
        HttpResponse respuestaHttp = clienteHttp.execute(get);
        String respuesta = EntityUtils.toString(respuestaHttp.getEntity());
        return respuesta;
    }

    public static String post(String url, JSONObject objetoJSON) throws IOException, JSONException {
        HttpClient clienteHttp = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.setHeader("content-type", "application/json");
        StringEntity entidad = new StringEntity(objetoJSON.toString());
        post.setEntity(entidad);
        HttpResponse respuestaHttp = clienteHttp.execute(post);
        String respuesta = EntityUtils.toString(respuestaHttp.getEntity());
        return respuesta;
    }

    public static String put(String url, JSONObject object) throws IOException, JSONException {
        HttpClient clienteHttp = new DefaultHttpClient();
        HttpPut put = new HttpPut(url);
        put.setHeader("content-type", "application/json");
        StringEntity entidad = new StringEntity(object.toString());
        put.setEntity(entidad);
        HttpResponse respuestaHttp = clienteHttp.execute(put);
        String respuesta = EntityUtils.toString(respuestaHttp.getEntity());
        return respuesta;
    }
}
