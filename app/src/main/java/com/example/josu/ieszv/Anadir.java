package com.example.josu.ieszv;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;


public class Anadir extends Activity {

    static TextView tvHora, tvHora2, tvFecha, tvFecha2;
    private TextView tvProfesor, tvGrupo, tvDescripcion, tvLugarInicio, tvLugarFin;
    private Spinner spProfesor, spGrupo, spTipo;
    private ImageButton ibHoraInicio, ibHoraFin, ibFechaInicio, ibFechaFin;
    //private ArrayList<Actividad> actividades;
    private ArrayList<Profesor> profesores;
    private ArrayList<Grupo> grupos;
    //private ArrayList<ActividadGrupo> actividadGrupos;
    private final static String URL_BASE = "http://ieszv.x10.bz/restful/api/";
    private static int ANADIR = 2;
    private static int EDITAR = 3;
    private String accion;
    private ActividadDetalle actividadEditando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);
        if (getResources().getBoolean(R.bool.tablet))
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //actividades = (ArrayList<Actividad>) getIntent().getExtras().get("actividades");
        profesores = (ArrayList<Profesor>) getIntent().getExtras().get("profesores");
        grupos = (ArrayList<Grupo>) getIntent().getExtras().get("grupos");
        //actividadGrupos = (ArrayList<ActividadGrupo>) getIntent().getExtras().get("actividadgrupos");
        tvHora = (TextView) findViewById(R.id.tvHoraInicio);
        tvHora2 = (TextView) findViewById(R.id.tvHoraFin);
        tvFecha = (TextView) findViewById(R.id.tvFechaInicio);
        tvFecha2 = (TextView) findViewById(R.id.tvFechaFin);
        tvProfesor = (TextView) findViewById(R.id.tvProfesor);
        spProfesor = (Spinner) findViewById(R.id.spProfesor);
        tvGrupo = (TextView) findViewById(R.id.tvGrupo);
        spGrupo = (Spinner) findViewById(R.id.spGrupo);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);
        tvLugarInicio = (TextView) findViewById(R.id.tvLugarInicio);
        tvLugarFin = (TextView) findViewById(R.id.tvLugarFin);
        ibHoraInicio = (ImageButton) findViewById(R.id.ibHoraInicio);
        ibHoraFin = (ImageButton) findViewById(R.id.ibHoraFin);
        ibFechaInicio = (ImageButton) findViewById(R.id.ibFechaInicio);
        ibFechaFin = (ImageButton) findViewById(R.id.ibFechaFin);
        invisible();
        spTipo = (Spinner) findViewById(R.id.spTipo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipo.setAdapter(adapter);
        spTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    visibleComplementaria();
                else
                    visibleExtraordinaria();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayList<String> nombreProfesores = new ArrayList();
        for (int i = 0; i < profesores.size(); i++)
            nombreProfesores.add(profesores.get(i).getNombre() + " " + profesores.get(i).getApellidos());
        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, nombreProfesores);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProfesor.setAdapter(adapter2);
        ArrayList<String> nombreGrupo = new ArrayList();
        for (Grupo g : grupos)
            nombreGrupo.add(g.getGrupo());
        ArrayAdapter<CharSequence> adapter4 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, nombreGrupo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGrupo.setAdapter(adapter4);

        accion = (String) getIntent().getExtras().get("accion");

        if (accion.equals("editar")) {
            ActividadDetalle actividadDetalle = actividadEditando = (ActividadDetalle) getIntent().getExtras().get("actividad");
            cargarDatos(actividadDetalle);
        }
    }

    public void cargarDatos(ActividadDetalle actividadDetalle) {
        if (actividadDetalle.getTipo().equals("complementaria"))
            spTipo.setSelection(0);
        else
            spTipo.setSelection(1);
        for (int i = 0; i < profesores.size(); i++)
            if (profesores.get(i).getId().equals(actividadDetalle.getIdProfesor()))
                spProfesor.setSelection(i);
        for (int i = 0; i < grupos.size(); i++)
            if (grupos.get(i).getId().equals(actividadDetalle.getIdGrupo()))
                spGrupo.setSelection(i);
        tvDescripcion.setText(actividadDetalle.getDescripcion());
        tvHora.setText(actividadDetalle.getFechaI().substring(11, 16));
        tvHora2.setText(actividadDetalle.getFechaF().substring(11, 16));
        tvFecha.setText(actividadDetalle.getFechaI().substring(0, 10));
        tvFecha2.setText(actividadDetalle.getFechaF().substring(0, 10));
        tvLugarInicio.setText(actividadDetalle.getLugarI());
        tvLugarFin.setText(actividadDetalle.getLugarF());
    }


    public void anadir() {
        if (accion.equals("editar")) {
            DeleteRestful dr = new DeleteRestful();
            dr.execute("actividad/" + actividadEditando.getId());
        }
        String idProfe = null;
        for (int i = 0; i < profesores.size(); i++) {
            if ((profesores.get(i).getNombre() + " " + profesores.get(i).getApellidos()).equals(spProfesor.getSelectedItem().toString()))
                idProfe = profesores.get(i).getId();
        }
        if (spTipo.getSelectedItem().toString().equals("Complementaria") && !tvDescripcion.getText().toString().isEmpty() && !tvHora.getText().equals("hh:mm") && !tvHora2.getText().equals("hh:mm") && !tvFecha.getText().equals("aaaa-mm-dd") && !tvLugarInicio.getText().toString().isEmpty()) {
            Actividad actividad = new Actividad(idProfe, "complementaria", tvFecha.getText().toString() + " " + tvHora.getText().toString() + ":00", tvFecha.getText().toString() + " " + tvHora2.getText().toString() + ":00", tvLugarInicio.getText().toString(), tvLugarInicio.getText().toString(), tvDescripcion.getText().toString(), "Josue");
            JSONObject object = actividad.getJSON();

            ParametrosPost parametrosPost = new ParametrosPost();
            parametrosPost.url = URL_BASE + "actividad";
            parametrosPost.json = object;
            PostRestful post = new PostRestful();
            post.execute(parametrosPost);

        } else if (spTipo.getSelectedItem().toString().equals("Extraescolar") && !tvDescripcion.getText().toString().isEmpty() && !tvHora.getText().equals("hh:mm") && !tvHora2.getText().equals("hh:mm") && !tvFecha.getText().equals("aaaa-mm-dd") && !tvFecha2.getText().equals("aaaa-mm-dd") && !tvLugarInicio.getText().toString().isEmpty() && !tvLugarFin.getText().toString().isEmpty()) {
            Actividad actividad = new Actividad(idProfe, "extraescolar", tvFecha.getText().toString() + " " + tvHora.getText().toString() + ":00", tvFecha2.getText().toString() + " " + tvHora2.getText().toString() + ":00", tvLugarInicio.getText().toString(), tvLugarFin.getText().toString(), tvDescripcion.getText().toString(), "Josue");
            //tostada(actividad.toString());
            Log.v("NUEVA ACTIVIDAD", actividad.toString());
            JSONObject object = actividad.getJSON();
            //tostada(object.toString());
            Log.v("NUEVO JSON", object.toString());
            ParametrosPost parametrosPost = new ParametrosPost();
            parametrosPost.url = URL_BASE + "actividad";
            parametrosPost.json = object;
            Log.v("NUEVA JSON PP", parametrosPost.json.toString());
            PostRestful post = new PostRestful();
            post.execute(parametrosPost);
        } else
            Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
    }

    public void visibleExtraordinaria() {
        visibleComplementaria();
        ibFechaFin.setVisibility(View.VISIBLE);
        ibFechaFin.setVisibility(View.VISIBLE);
        tvLugarFin.setVisibility(View.VISIBLE);
        tvHora2.setVisibility(View.VISIBLE);
        tvFecha2.setVisibility(View.VISIBLE);
    }

    public void visibleComplementaria() {
        tvProfesor.setVisibility(View.VISIBLE);
        spProfesor.setVisibility(View.VISIBLE);
        tvGrupo.setVisibility(View.VISIBLE);
        spGrupo.setVisibility(View.VISIBLE);
        tvDescripcion.setVisibility(View.VISIBLE);
        tvLugarInicio.setVisibility(View.VISIBLE);
        ibHoraInicio.setVisibility(View.VISIBLE);
        ibHoraFin.setVisibility(View.VISIBLE);
        ibFechaInicio.setVisibility(View.VISIBLE);
        ibFechaFin.setVisibility(View.VISIBLE);
        tvHora.setVisibility(View.VISIBLE);
        tvFecha.setVisibility(View.VISIBLE);
        ibFechaFin.setVisibility(View.INVISIBLE);
        ibFechaFin.setVisibility(View.INVISIBLE);
        tvLugarFin.setVisibility(View.INVISIBLE);
        tvHora2.setVisibility(View.VISIBLE);
        tvFecha2.setVisibility(View.INVISIBLE);
    }

    public void invisible() {
        tvHora.setVisibility(View.INVISIBLE);
        tvHora2.setVisibility(View.INVISIBLE);
        tvFecha.setVisibility(View.INVISIBLE);
        tvFecha2.setVisibility(View.INVISIBLE);
        tvProfesor.setVisibility(View.INVISIBLE);
        spProfesor.setVisibility(View.INVISIBLE);
        tvProfesor.setVisibility(View.INVISIBLE);
        spProfesor.setVisibility(View.INVISIBLE);
        tvGrupo.setVisibility(View.INVISIBLE);
        spGrupo.setVisibility(View.INVISIBLE);
        tvDescripcion.setVisibility(View.INVISIBLE);
        tvLugarInicio.setVisibility(View.INVISIBLE);
        tvLugarFin.setVisibility(View.INVISIBLE);
        ibHoraInicio.setVisibility(View.INVISIBLE);
        ibHoraFin.setVisibility(View.INVISIBLE);
        ibFechaInicio.setVisibility(View.INVISIBLE);
        ibFechaFin.setVisibility(View.INVISIBLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.anadir, menu);
        if(accion.equals("editar")){
            setTitle(getResources().getString(R.string.title_activity_editar));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_guardar) {
            anadir();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        int tvRef;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String hora, minuto;

            if (String.valueOf(hourOfDay).length() == 1)
                hora = "0" + hourOfDay;
            else
                hora = hourOfDay + "";
            if (String.valueOf(minute).length() == 1)
                minuto = "0" + minute;
            else
                minuto = minute + "";

            if (getTag().toString().equals("horaInicio"))
                tvHora.setText(hora + ":" + minuto);
            else
                tvHora2.setText(hora + ":" + minuto);
        }
    }

    public void hora(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "horaInicio");
    }

    public void hora2(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "horaFin");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            String dia, mes, anio;

            if (String.valueOf(day).length() == 1)
                dia = "0" + day;
            else
                dia = day + "";
            if (String.valueOf(month).length() == 1)
                mes = "0" + (month + 1);
            else
                mes = (month + 1) + "";

            if (getTag().toString().equals("fechaInicio"))
                tvFecha.setText(year + "-" + mes + "-" + dia);
            else
                tvFecha2.setText(year + "-" + mes + "-" + dia);
        }
    }

    public void fecha(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "fechaInicio");
    }

    public void fecha2(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "fechaFin");
    }

    public void tostada(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    private class PostRestful extends AsyncTask<ParametrosPost, String, String> {

        private ParametrosPost pp;

        @Override
        protected String doInBackground(ParametrosPost[] params) {
            pp = params[0];
            Log.v("NUEV PP JSON", pp.json.toString());
            String r = null;
            try {
                r = ClienteRestFul.post(pp.url, pp.json);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return r;
        }

        @Override
        protected void onPostExecute(String r) {
            super.onPostExecute(r);
            PostRestful prGrupo;
            String respuesta = null;
            try {
                JSONObject idAct = new JSONObject(r);
                respuesta = idAct.getString("r");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (!respuesta.equals("0") && pp.url.endsWith("actividad")) {
                prGrupo = new PostRestful();
                pp.url = URL_BASE + "actividadgrupo";
                pp.json = new JSONObject();
                try {
                    pp.json.put("idactividad", respuesta);
                    pp.json.put("idgrupo", getIdGrupo(spGrupo.getSelectedItem().toString()));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.v("ERROR JSON", e.toString());
                }
                prGrupo.execute(pp);
                if(accion.equals("anadir"))
                    tostada("La actividad se ha añadido.");
                else
                    tostada("La actividad se ha editado.");
                Intent intent = new Intent(Anadir.this, Principal.class);
                setResult(RESULT_OK, intent);
                finish();
            } else if (respuesta.equals("0"))
                tostada("No se ha podido añadir la actividad.");
        }

        public String getIdGrupo(String grupo) {
            for (int i = 0; i < grupos.size(); i++)
                if (grupos.get(i).getGrupo().equals(grupo))
                    return grupos.get(i).getId();
            return null;
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
                //tostada("La actividad se ha eliminado.");
                /* NO CONSIGO QUE ME CARGUE LAS ACTIVIDADES BIEN DESPUÉS DE HABER ELIMINADO UNA.
                TAMPOCO FUNCIONA SI INICIALIZO DE NUEVO EL ARRAYLIST DE ACTIVIDADES
                cargarActividades();*/
            }
        }
    }

    static class ParametrosPost {
        String url;
        JSONObject json;
    }
}

