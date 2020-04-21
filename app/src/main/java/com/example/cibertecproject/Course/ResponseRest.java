package com.example.cibertecproject.Course;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseRest {
    @SerializedName("getLugarResult")
    private List<Lugar> olstLugar;
    private Object oResult;
    private ArrayList<Object> arrResult;

    public List<Lugar> getOlstLugar() {
        return olstLugar;
    }

    public void setOlstLugar(List<Lugar> olstLugar) {
        this.olstLugar = olstLugar;
    }




    public Object getoResult() {
        return oResult;
    }

    public void setoResult(Object oResult) {
        this.oResult = oResult;
    }

    public ArrayList<Object> getArrResult() {
        return arrResult;
    }

    public void setArrResult(ArrayList<Object> arrResult) {
        this.arrResult = arrResult;
    }



}
