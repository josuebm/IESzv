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
        tvGrupo.setText(Html.fromHtml("<b>" + getResources().getString(R.string.tvGrupo) + "</b> " + actividadDetalle.getGrupo()));
        tvFechaI.setText(Html.fromHtml(("<b>" + getResources().getString(R.string.tvFechaI) + "</b> ") + actividadDetalle.getFechaI()));
        tvFechaF.setText(Html.fromHtml(("<b>" + getResources().getString(R.string.tvFechaF) + "</b> ") + actividadDetalle.getFechaF()));
        tvTipo.setText(Html.fromHtml(("<b>" + getResources().getString(R.string.tvTipo) + "</b> ") + actividadDetalle.getTipo()));
        tvProfesor.setText(Html.fromHtml(("<b>" + getResources().getString(R.string.tvProfesor) + "</b> ") + actividadDetalle.getProfesor()));
        tvDescripcion.setText(Html.fromHtml(("<b>" + getResources().getString(R.string.tvDescripcion) + "</b>") + "<br />" + actividadDetalle.getDescripcion()));
        tvLugarI.setText(Html.fromHtml(("<b>" + getResources().getString(R.string.tvLugarI) + "</b> ") + actividadDetalle.getLugarI()));
        tvLugarF.setText(Html.fromHtml(("<b>" + getResources().getString(R.string.tvLugarF) + "</b> ") + actividadDetalle.getLugarF()));
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
