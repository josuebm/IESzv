package com.example.josu.ieszv;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Josué on 14/02/2015.
 */
public class Grupo implements Parcelable{

    private String id, grupo;

    public Grupo() {
    }

    public Grupo(String id, String grupo) {
        this.id = id;
        this.grupo = grupo;
    }

    public Grupo(JSONObject object) throws JSONException {
        this.id = object.getString("id");
        this.grupo = object.getString("grupo");
    }

    public JSONObject getJSON(){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("id", this.id);
            jsonObject.put("grupo", this.grupo);
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

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "id='" + id + '\'' +
                ", grupo='" + grupo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.id);
        parcel.writeString(this.grupo);
    }

    public Grupo(Parcel parcel){
        this.id = parcel.readString();
        this.grupo = parcel.readString();
    }

    public static final Creator <Grupo> CREATOR =
            new Creator <Grupo>() {
                @Override
                public Grupo createFromParcel(Parcel parcel) {
                    return new Grupo(parcel);
                }
                @Override
                public Grupo[] newArray(int i) {
                    return new Grupo[i];
                }
            };
}
