package com.example.josu.ieszv;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class DetalleGrande extends Activity {

    private ImageView iv;
    private TextView tvGrupo, tvFechaI, tvFechaF, tvTipo, tvProfesor, tvDescripcion, tvLugarI, tvLugarF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_grande);
        if (getResources().getBoolean(R.bool.tablet))
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        iv = (ImageView)findViewById(R.id.ivDetalle);
        tvGrupo = (TextView)findViewById(R.id.tvGrupoDetalle);
        tvFechaI = (TextView)findViewById(R.id.tvFechaIDetalle);
        tvFechaF = (TextView)findViewById(R.id.tvFechaFDetalle);
        tvTipo = (TextView)findViewById(R.id.tvTipoDetalle);
        tvProfesor = (TextView)findViewById(R.id.tvProfesorDetalle);
        tvDescripcion = (TextView)findViewById(R.id.tvDescripcionDetalle);
        tvLugarI = (TextView)findViewById(R.id.tvLugarIDetalle);
        tvLugarF = (TextView)findViewById(R.id.tvLugarFDetalle);
        ActividadDetalle actividadDetalle = (ActividadDetalle)getIntent().getExtras().get("actividad");

        iv.setImageResource(R.drawable.logo_grande);
        tvGrupo.setText(Html.fromHtml(("<b>Grupo:</b> ") + actividadDetalle.getGrupo()));
        tvFechaI.setText(Html.fromHtml(("<b>Desde:</b> ") + actividadDetalle.getFechaI()));
        tvFechaF.setText(Html.fromHtml(("<b>Hasta:</b> ") + actividadDetalle.getFechaF()));
        tvTipo.setText(Html.fromHtml(("<b>Tipo de actividad:</b> ") + actividadDetalle.getTipo()));
        tvProfesor.setText(Html.fromHtml(("<b>Profesor:</b> ") + actividadDetalle.getProfesor()));
        tvDescripcion.setText(Html.fromHtml(("<b>Descripción:</b>") + "<br />" + actividadDetalle.getDescripcion()));
        tvLugarI.setText(Html.fromHtml(("<b>Lugar de salida:</b> ") + actividadDetalle.getLugarI()));
        tvLugarF.setText(Html.fromHtml(("<b>Lugar de llegada:</b> ") + actividadDetalle.getLugarF()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalle_grande, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
