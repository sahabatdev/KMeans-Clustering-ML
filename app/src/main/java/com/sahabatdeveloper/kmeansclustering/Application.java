package com.sahabatdeveloper.kmeansclustering;

import java.util.ArrayList;

/**
 * Created by Sahabat Developer on 21/09/2017.
 */

public class Application extends android.app.Application {
    private ArrayList<Model> listTable = new ArrayList<>();

    public ArrayList<Model> getListTable() {
        return listTable;
    }

    public void setListTable(ArrayList<Model> listTable) {
        this.listTable = listTable;
    }
}
