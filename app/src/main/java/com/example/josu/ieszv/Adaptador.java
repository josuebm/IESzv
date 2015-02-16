package com.example.josu.ieszv;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by 2dam on 28/01/2015.
 */
public class Adaptador extends ArrayAdapter {

    static class ViewHolder{
        public TextView tvFecha, tvProfesor, tvGrupo, tvDescripcion;
        public int position;
    }

    private Context contexto;
    private ArrayList<ActividadDetalle> lista;
    private int recurso;
    private static LayoutInflater i;
    /*
    private ArrayList<Profesor> profesores;
    private ArrayList<Grupo> grupos;
    private ArrayList<ActividadGrupo> actividadGrupos;*/
    private final static String URL_BASE = "http://ieszv.x10.bz/restful/api/";

    public Adaptador(Context context, int resource, ArrayList<ActividadDetalle> objects) {
        super(context, resource, objects);
        this.contexto = context;
        this.lista = objects;
        this.recurso = resource;
        this.i = i = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        /*
        profesores = new ArrayList();
        grupos = new ArrayList();
        actividadGrupos = new ArrayList();*/
        /*String[] peticiones = {"profesor", "grupo", "actividadgrupo"};
        GetRestful get = new GetRestful();
        get.execute(peticiones);*/
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView == null){
            convertView = i.inflate(recurso, null);
            vh = new ViewHolder();
            vh.tvFecha = (TextView)convertView.findViewById(R.id.tvFecha);
            vh.tvProfesor = (TextView)convertView.findViewById(R.id.tvProfesor);
            vh.tvGrupo = (TextView)convertView.findViewById(R.id.tvGrupo);
            vh.tvDescripcion = (TextView)convertView.findViewById(R.id.tvDescripcion);
            convertView.setTag(vh);
        }
        else
            vh = (ViewHolder)convertView.getTag();
        vh.tvFecha.setText(lista.get(position).getFechaI());
        vh.tvProfesor.setText(lista.get(position).getProfesor());
        vh.tvGrupo.setText(lista.get(position).getGrupo());
        vh.tvDescripcion.setText(lista.get(position).getDescripcion());
        vh.position = position;
        return convertView;
    }
/*
    public String getProfesor(Actividad actividad){
        for(int i=0; i<profesores.size(); i++){
            if(profesores.get(i).getId().equals(actividad.getIdProfesor()))
                return profesores.get(i).getNombre() + " " + profesores.get(i).getApellidos();
        }
        return null;
    }

    public String getGrupo(Actividad actividad){
        String idGrupo = null;
        for(int i=0; i<actividadGrupos.size(); i++){
            if(actividadGrupos.get(i).getIdActividad().equals(actividad.getId()))
                idGrupo = actividadGrupos.get(i).getIdGrupo();
        }
        for(int i=0; i<grupos.size(); i++){
            if(grupos.get(i).getId().equals(idGrupo))
                return grupos.get(i).getGrupo();
        }
        return null;
    }*/

}