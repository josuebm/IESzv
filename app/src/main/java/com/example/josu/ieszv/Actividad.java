package com.example.josu.ieszv;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by 2dam on 28/01/2015.
 */
public class Actividad implements Parcelable, Comparable<Actividad> {

    private String id, idProfesor, tipo, fechaI, fechaF, lugarI, lugarF, descripcion, alumno;

    public Actividad() {
    }

    public Actividad(String idProfesor, String tipo, String fechaI, String fechaF, String lugarI, String lugarF, String descripcion, String alumno) {
        this.idProfesor = idProfesor;
        this.tipo = tipo;
        this.fechaI = fechaI;
        this.fechaF = fechaF;
        this.lugarI = lugarI;
        this.lugarF = lugarF;
        this.descripcion = descripcion;
        this.alumno = alumno;
    }

    public Actividad(JSONObject object) throws JSONException {
        this.id = object.getString("id");
        this.idProfesor = object.getString("idprofesor");
        this.tipo = object.getString("tipo");
        this.fechaI = object.getString("fechai");
        this.fechaF = object.getString("fechaf");
        this.lugarI = object.getString("lugari");
        this.lugarF = object.getString("lugarf");
        this.descripcion = object.getString("descripcion");
        this.alumno = object.getString("alumno");
    }

    public JSONObject getJSON(){
        JSONObject jsonObject = new JSONObject();
        try{
            //jsonObject.put("id", this.id);
            jsonObject.put("idprofesor", this.idProfesor);
            jsonObject.put("tipo", this.tipo);
            jsonObject.put("fechai", this.fechaI);
            jsonObject.put("fechaf", this.fechaF);
            jsonObject.put("lugari", this.lugarI);
            jsonObject.put("lugarf", this.lugarF);
            jsonObject.put("descripcion", this.descripcion);
            jsonObject.put("alumno", this.alumno);
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaI() {
        return fechaI;
    }

    public void setFechaI(String fechaI) {
        this.fechaI = fechaI;
    }

    public String getFechaF() {
        return fechaF;
    }

    public void setFechaF(String fechaF) {
        this.fechaF = fechaF;
    }

    public String getLugarI() {
        return lugarI;
    }

    public void setLugarI(String lugarI) {
        this.lugarI = lugarI;
    }

    public String getLugarF() {
        return lugarF;
    }

    public void setLugarF(String lugarF) {
        this.lugarF = lugarF;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id='" + id + '\'' +
                ", idProfesor='" + idProfesor + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fechaI='" + fechaI + '\'' +
                ", fechaF='" + fechaF + '\'' +
                ", lugarI='" + lugarI + '\'' +
                ", lugarF='" + lugarF + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", alumno='" + alumno + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.id);
        parcel.writeString(this.idProfesor);
        parcel.writeString(this.tipo);
        parcel.writeString(this.fechaI);
        parcel.writeString(this.fechaF);
        parcel.writeString(this.lugarI);
        parcel.writeString(this.lugarF);
        parcel.writeString(this.descripcion);
        parcel.writeString(this.alumno);
    }

    public Actividad(Parcel parcel){
        this.id = parcel.readString();
        this.idProfesor = parcel.readString();
        this.tipo = parcel.readString();
        this.fechaI = parcel.readString();
        this.fechaF = parcel.readString();
        this.lugarI = parcel.readString();
        this.lugarF = parcel.readString();
        this.descripcion = parcel.readString();
        this.alumno = parcel.readString();
    }

    public static final Creator <Actividad> CREATOR =
            new Creator <Actividad>() {
                @Override
                public Actividad createFromParcel(Parcel parcel) {
                    return new Actividad(parcel);
                }
                @Override
                public Actividad[] newArray(int i) {
                    return new Actividad[i];
                }
            };

    @Override
    public int compareTo(Actividad another) {
        String a = this.fechaI;
        String b = another.fechaI;
        if(a.compareTo(b) < 0)
            return -1;
        else
        if(a.compareTo(b) > 0)
            return 1;
        else
            return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Actividad.class != o.getClass()) return false;

        Actividad actividad = (Actividad) o;

        if (id != actividad.id) return false;

        return true;
    }
}
