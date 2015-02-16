package com.example.josu.ieszv;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Principal extends Activity {

    private final static String URL_BASE = "http://ieszv.x10.bz/restful/api/";
    private ListView lv;
    private Adaptador ad;
    private ArrayList<Actividad> actividades;
    private ArrayList<Profesor> profesores;
    private ArrayList<Grupo> grupos;
    private ArrayList<ActividadGrupo> actividadGrupos;
    private ArrayList<ActividadDetalle> actividadDetalles;
    private static int ANADIR = 2;
    private static int EDITAR = 3;
    private boolean todo;
    private ImageView ivDetalle;
    private TextView tvGrupoDetalle, tvFechaIDetalle, tvFechaFDetalle, tvTipoDetalle, tvProfesorDetalle, tvDescripcionDetalle, tvLugarIDetalle, tvLugarFDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        if (getResources().getBoolean(R.bool.tablet)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            ivDetalle = (ImageView)findViewById(R.id.ivDetalle);
            tvGrupoDetalle = (TextView)findViewById(R.id.tvGrupoDetalle);
            tvFechaIDetalle = (TextView)findViewById(R.id.tvFechaIDetalle);
            tvFechaFDetalle = (TextView)findViewById(R.id.tvFechaFDetalle);
            tvTipoDetalle = (TextView)findViewById(R.id.tvTipoDetalle);
            tvProfesorDetalle = (TextView)findViewById(R.id.tvProfesorDetalle);
            tvDescripcionDetalle = (TextView)findViewById(R.id.tvDescripcionDetalle);
            tvLugarIDetalle = (TextView)findViewById(R.id.tvLugarIDetalle);
            tvLugarFDetalle = (TextView)findViewById(R.id.tvLugarFDetalle);
        }
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actividades = new ArrayList();
        profesores = new ArrayList();
        grupos = new ArrayList();
        actividadGrupos = new ArrayList();
        actividadDetalles = new ArrayList();
        lv = (ListView) findViewById(R.id.lvActividades);
        ad = new Adaptador(this, R.layout.detalle, actividadDetalles);
        lv.setAdapter(ad);
        cargarActividades(todo = true);
        registerForContextMenu(lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (getResources().getBoolean(R.bool.tablet)){
                    ivDetalle.setImageResource(R.drawable.logo_grande);
                    tvGrupoDetalle.setText(Html.fromHtml("<b>" + getResources().getString(R.string.tvGrupo) + "</b> " + actividadDetalles.get(position).getGrupo()));
                    tvFechaIDetalle.setText(Html.fromHtml(("<b>" + getResources().getString(R.string.tvFechaI) + "</b> ") + actividadDetalles.get(position).getFechaI()));
                    tvFechaFDetalle.setText(Html.fromHtml(("<b>" + getResources().getString(R.string.tvFechaF) + "</b> ") + actividadDetalles.get(position).getFechaF()));
                    tvTipoDetalle.setText(Html.fromHtml(("<b>" + getResources().getString(R.string.tvTipo) + "</b> ") + actividadDetalles.get(position).getTipo()));
                    tvProfesorDetalle.setText(Html.fromHtml(("<b>" + getResources().getString(R.string.tvProfesor) + "</b> ") + actividadDetalles.get(position).getProfesor()));
                    tvDescripcionDetalle.setText(Html.fromHtml(("<b>" + getResources().getString(R.string.tvDescripcion) + "</b>") + "<br />" + actividadDetalles.get(position).getDescripcion()));
                    tvLugarIDetalle.setText(Html.fromHtml(("<b>" + getResources().getString(R.string.tvLugarI) + "</b> ") + actividadDetalles.get(position).getLugarI()));
                    tvLugarFDetalle.setText(Html.fromHtml(("<b>" + getResources().getString(R.string.tvLugarF) + "</b> ") + actividadDetalles.get(position).getLugarF()));
                }else{
                    Intent intent = new Intent(Principal.this, DetalleGrande.class);
                    intent.putExtra("actividad", actividadDetalles.get(position));
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_anadir) {
            Intent intent = new Intent(this, Anadir.class);
            intent.putExtra("accion", "anadir");
            intent.putExtra("profesores", profesores);
            intent.putExtra("grupos", grupos);
            startActivityForResult(intent, ANADIR);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.principal_long_click, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //return super.onContextItemSelected(item);
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int index = info.position;
        Object o = info.targetView.getTag();
        Adaptador.ViewHolder vh;
        vh = (Adaptador.ViewHolder) o;
        if (id == R.id.action_eliminar) {
            final TextView aviso = new TextView(this);
            aviso.setText(getResources().getString(R.string.pregunta_eliminar));
            aviso.setTextSize(20);
            aviso.setPadding(10, 10, 10, 10);
            new AlertDialog.Builder(this)
                    .setTitle(getResources().getString(R.string.title_eliminar))
                    .setView(aviso)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            DeleteRestful dr = new DeleteRestful();
                            dr.execute("actividad/" + actividadDetalles.get(index).getId());
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show();
            return true;
        } else if (id == R.id.action_editar) {
            Intent intent = new Intent(this, Anadir.class);
            intent.putExtra("accion", "editar");
            intent.putExtra("actividad", actividadDetalles.get(index));
            intent.putExtra("profesores", profesores);
            intent.putExtra("grupos", grupos);
            startActivityForResult(intent, EDITAR);
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ANADIR || requestCode == EDITAR && resultCode == RESULT_OK)
            cargarActividades(todo = false);
    }

    public void tostada(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void cargarActividades(Boolean todo) {
        /*petición get al servidor restful para obtener las actividades, recibo una lista en formato json, lo paso a arraylist y cargo el listview EN UNA HEBRA
         a la que le paso la URL*/
        actividades.clear();
        actividadGrupos.clear();
        actividadDetalles.clear();
        String[] peticiones;

        if (todo) {
            profesores.clear();
            grupos.clear();
            peticiones = new String[]{"actividad/josue", "actividadgrupo", "profesor", "grupo"};
        }

        else
            peticiones = new String[]{"actividad/josue", "actividadgrupo"};
        GetRestful get = new GetRestful();
        get.execute(peticiones);
    }

    private class GetRestful extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... params) {
            try {
                String[] r = new String[params.length];
                int contador = 0;
                for (String s : params) {
                    r[contador] = ClienteRestFul.get(URL_BASE + s);
                    contador++;
                }
                return r;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] r) {
            super.onPostExecute(r);
            JSONTokener tokenerActividad = new JSONTokener(r[0]);
            JSONTokener tokenerActividadGrupo = new JSONTokener(r[1]);

            try {
                JSONArray jsonArray = new JSONArray(tokenerActividad);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Actividad actividad = new Actividad(jsonObject);
                    actividades.add(actividad);
                }

                jsonArray = new JSONArray(tokenerActividadGrupo);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    ActividadGrupo actividadGrupo = new ActividadGrupo(jsonObject);
                    actividadGrupos.add(actividadGrupo);
                }

                if(todo){
                    JSONTokener tokenerProfesor = new JSONTokener(r[2]);
                    JSONTokener tokenerGrupo = new JSONTokener(r[3]);


                    jsonArray = new JSONArray(tokenerProfesor);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Profesor profesor = new Profesor(jsonObject);
                        profesores.add(profesor);
                    }
                    jsonArray = new JSONArray(tokenerGrupo);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Grupo grupo = new Grupo(jsonObject);
                        grupos.add(grupo);
                    }
                }

                for (int i = 0; i < actividades.size(); i++) {
                    actividadDetalles.add(new ActividadDetalle(actividades.get(i).getId(), actividades.get(i).getIdProfesor(), getProfesor(actividades.get(i)), actividades.get(i).getTipo(), actividades.get(i).getFechaI(), actividades.get(i).getFechaF(), actividades.get(i).getLugarI(), actividades.get(i).getLugarF(), actividades.get(i).getDescripcion(), getIdGrupo(actividades.get(i)), getGrupo(actividades.get(i))));
                }
                Collections.sort(actividadDetalles);
                ad.notifyDataSetChanged();
            } catch (Exception e) {
                Log.v("ERROR1", e.toString());
            }
        }
    }

    private class DeleteRestful extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                String r = params[0];
                r = ClienteRestFul.delete(URL_BASE + r);
                return r;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String r) {
            super.onPostExecute(r);
            String respuesta = null;
            try {
                JSONObject idAct = new JSONObject(r);
                respuesta = idAct.getString("r");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (!respuesta.equals("0")) {
                tostada(getResources().getString(R.string.actividad_eliminada));
                cargarActividades(todo = false);

            } else
                tostada(getResources().getString(R.string.actividad_no_eliminada));

        }
    }

    public String getProfesor(Actividad actividad) {
        for (int i = 0; i < profesores.size(); i++) {
            if (profesores.get(i).getId().equals(actividad.getIdProfesor()))
                return profesores.get(i).getNombre() + " " + profesores.get(i).getApellidos();
        }
        return null;
    }

    public String getIdGrupo(Actividad actividad) {
        for (int i = 0; i < actividadGrupos.size(); i++) {
            if (actividadGrupos.get(i).getIdActividad().equals(actividad.getId()))
                return actividadGrupos.get(i).getIdGrupo();
        }
        return null;
    }

    public String getGrupo(Actividad actividad) {
        for (int i = 0; i < grupos.size(); i++) {
            if (grupos.get(i).getId().equals(getIdGrupo(actividad)))
                return grupos.get(i).getGrupo();
        }
        return null;
    }


}
