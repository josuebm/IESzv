package com.example.josu.ieszv;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Josué on 16/02/2015.
 */
public class ActividadDetalle implements Parcelable, Comparable<ActividadDetalle>{

    private String id, idProfesor, profesor, tipo, fechaI, fechaF, lugarI, lugarF, descripcion, idGrupo, grupo;

    public ActividadDetalle() {
    }

    public ActividadDetalle(String id, String idProfesor, String profesor, String tipo, String fechaI, String fechaF, String lugarI, String lugarF, String descripcion, String idGrupo, String grupo) {
        this.id = id;
        this.idProfesor = idProfesor;
        this.profesor = profesor;
        this.tipo = tipo;
        this.fechaI = fechaI;
        this.fechaF = fechaF;
        this.lugarI = lugarI;
        this.lugarF = lugarF;
        this.descripcion = descripcion;
        this.idGrupo = idGrupo;
        this.grupo = grupo;
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

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
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

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "ActividadDetalle{" +
                "id='" + id + '\'' +
                ", idProfesor='" + idProfesor + '\'' +
                ", profesor='" + profesor + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fechaI='" + fechaI + '\'' +
                ", fechaF='" + fechaF + '\'' +
                ", lugarI='" + lugarI + '\'' +
                ", lugarF='" + lugarF + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", idGrupo='" + idGrupo + '\'' +
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
        parcel.writeString(this.idProfesor);
        parcel.writeString(this.profesor);
        parcel.writeString(this.tipo);
        parcel.writeString(this.fechaI);
        parcel.writeString(this.fechaF);
        parcel.writeString(this.lugarI);
        parcel.writeString(this.lugarF);
        parcel.writeString(this.descripcion);
        parcel.writeString(this.idGrupo);
        parcel.writeString(this.grupo);
    }

    public ActividadDetalle(Parcel parcel){
        this.id = parcel.readString();
        this.idProfesor = parcel.readString();
        this.profesor = parcel.readString();
        this.tipo = parcel.readString();
        this.fechaI = parcel.readString();
        this.fechaF = parcel.readString();
        this.lugarI = parcel.readString();
        this.lugarF = parcel.readString();
        this.descripcion = parcel.readString();
        this.idGrupo = parcel.readString();
        this.grupo = parcel.readString();
    }

    public static final Creator <ActividadDetalle> CREATOR =
            new Creator <ActividadDetalle>() {
                @Override
                public ActividadDetalle createFromParcel(Parcel parcel) {
                    return new ActividadDetalle(parcel);
                }
                @Override
                public ActividadDetalle[] newArray(int i) {
                    return new ActividadDetalle[i];
                }
            };

    @Override
    public int compareTo(ActividadDetalle another) {
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
        if (o == null || ActividadDetalle.class != o.getClass()) return false;

        ActividadDetalle actividadDetalle = (ActividadDetalle) o;

        if (id != actividadDetalle.id) return false;

        return true;
    }
}
