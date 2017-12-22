package com.sahabatdeveloper.kmeansclustering;

/**
 * Created by Sahabat Developer on 05/10/2017.
 */

class Model {
    int x1;
    int x2;
    int kuadratJarak;
    int peringkat;
    String termasukKnn;
    String kategori;

    public Model(int x1, int x2, int kuadratJarak, int peringkat, String termasukKnn, String kategori) {
        this.x1 = x1;
        this.x2 = x2;
        this.kuadratJarak = kuadratJarak;
        this.peringkat = peringkat;
        this.termasukKnn = termasukKnn;
        this.kategori = kategori;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getKuadratJarak() {
        return kuadratJarak;
    }

    public void setKuadratJarak(int kuadratJarak) {
        this.kuadratJarak = kuadratJarak;
    }

    public int getPeringkat() {
        return peringkat;
    }

    public void setPeringkat(int peringkat) {
        this.peringkat = peringkat;
    }

    public String getTermasukKnn() {
        return termasukKnn;
    }

    public void setTermasukKnn(String termasukKnn) {
        this.termasukKnn = termasukKnn;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
