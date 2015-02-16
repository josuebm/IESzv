package com.example.josu.ieszv;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Josué on 14/02/2015.
 */
public class Profesor implements Parcelable{

    private String id, nombre, apellidos, departamento;

    public Profesor() {
    }

    public Profesor(String id, String nombre, String apellidos, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.departamento = departamento;
    }

    public Profesor(JSONObject object) throws JSONException {
        this.id = object.getString("id");
        this.nombre = object.getString("nombre");
        this.apellidos = object.getString("apellidos");
        this.departamento = object.getString("departamento");
    }

    public JSONObject getJSON(){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("id", this.id);
            jsonObject.put("nombre", this.nombre);
            jsonObject.put("apellidos", this.apellidos);
            jsonObject.put("departamento", this.departamento);
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", departamento='" + departamento + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.id);
        parcel.writeString(this.nombre);
        parcel.writeString(this.apellidos);
        parcel.writeString(this.departamento);
    }

    public Profesor(Parcel parcel){
        this.id = parcel.readString();
        this.nombre = parcel.readString();
        this.apellidos = parcel.readString();
        this.departamento = parcel.readString();
    }

    public static final Creator <Profesor> CREATOR =
            new Creator <Profesor>() {
                @Override
                public Profesor createFromParcel(Parcel parcel) {
                    return new Profesor(parcel);
                }
                @Override
                public Profesor[] newArray(int i) {
                    return new Profesor[i];
                }
            };
}
