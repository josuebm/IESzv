package com.example.josu.ieszv;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Josué on 15/02/2015.
 */
public class ActividadGrupo implements Parcelable{

    String id, idActividad, idGrupo;

    public ActividadGrupo() {
    }

    public ActividadGrupo(String id, String idActividad, String idGrupo) {
        this.id = id;
        this.idActividad = idActividad;
        this.idGrupo = idGrupo;
    }

    public ActividadGrupo(JSONObject object) throws JSONException {
        this.id = object.getString("id");
        this.idActividad = object.getString("idactividad");
        this.idGrupo = object.getString("idgrupo");
    }

    public JSONObject getJSON(){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("id", this.id);
            jsonObject.put("idactividad", this.idActividad);
            jsonObject.put("idgrupo", this.idGrupo);
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

    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String idActividad) {
        this.idActividad = idActividad;
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Override
    public String toString() {
        return "ActividadGrupo{" +
                "id='" + id + '\'' +
                ", idActividad='" + idActividad + '\'' +
                ", idGrupo='" + idGrupo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.id);
        parcel.writeString(this.idActividad);
        parcel.writeString(this.idGrupo);
    }

    public ActividadGrupo(Parcel parcel) {
        this.id = parcel.readString();
        this.idActividad = parcel.readString();
        this.idGrupo = parcel.readString();
    }

    public static final Creator <ActividadGrupo> CREATOR =
            new Creator <ActividadGrupo>() {
                @Override
                public ActividadGrupo createFromParcel(Parcel parcel) {
                    return new ActividadGrupo(parcel);
                }
                @Override
                public ActividadGrupo[] newArray(int i) {
                    return new ActividadGrupo[i];
                }
            };
}
