package com.example.josu.ieszv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

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

    public Adaptador(Context context, int resource, ArrayList<ActividadDetalle> objects) {
        super(context, resource, objects);
        this.contexto = context;
        this.lista = objects;
        this.recurso = resource;
        this.i = i = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

}