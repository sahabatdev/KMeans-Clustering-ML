package com.sahabatdeveloper.kmeansclustering;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class CreateActivity extends AppCompatActivity {


    LinearLayout linearLayout;
    int[] x1,x2,d,c,r;
    String[] classi,in;
    String x1Testing, x2Testing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

//        btnTambahData = (Button)findViewById(R.id.btnTambahData);
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);

        final EditText etJumlah = new EditText(this);
        Button btnJumlah = new Button(this);
        etJumlah.setHint("Jumlah Data Training");
        btnJumlah.setText("Jumlah");
        btnJumlah.setBackgroundResource(R.color.colorAccent);
        final Button btnSimpanAttribut = new Button(CreateActivity.this);
        btnSimpanAttribut.setText("Simpan Data Training");
        btnSimpanAttribut.setBackgroundResource(R.color.colorAccent);
        final EditText[] etAttribut = new EditText[100];
        final EditText etDataTesting = new EditText(this);
        final EditText etBatasan = new EditText(this);
        etDataTesting.setHint("Masukkan Data Testing (x1,x2)");
        etBatasan.setHint("Masukkan Batasan");
        final Button btnCari = new Button(CreateActivity.this);
        btnCari.setText("Cari Hasil");
        btnCari.setBackgroundResource(R.color.colorAccent);
        linearLayout.addView(etJumlah);
        linearLayout.addView(btnJumlah);

        btnJumlah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<Integer.parseInt(etJumlah.getText().toString());i++){
                    etAttribut[i] = new EditText(CreateActivity.this);
                    etAttribut[i].setHint("Masukkan data x1,x2");
                    linearLayout.addView(etAttribut[i]);
                }
                linearLayout.addView(btnSimpanAttribut);
            }
        });

        btnSimpanAttribut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] data=new String[3];
                int jumlah = Integer.parseInt(etJumlah.getText().toString());
                x1 = new int[jumlah];
                x2 = new int[jumlah];
                d = new int[jumlah];
                c = new int[jumlah];
                r = new int[jumlah];
                in = new String[jumlah];
                classi = new String[jumlah];

                for(int i=0;i<Integer.parseInt(etJumlah.getText().toString());i++){
                    etAttribut[i].setFocusable(false);
                    etAttribut[i].setEnabled(false);
                    etAttribut[i].setClickable(false);
                    data = etAttribut[i].getText().toString().split(",");
                    x1[i] = Integer.parseInt(data[0]);
                    x2[i] = Integer.parseInt(data[1]);
                    classi[i] = data[2];
                }

                linearLayout.addView(etDataTesting);
                linearLayout.addView(etBatasan);
                linearLayout.addView(btnCari);
            }
        });

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] isiDataTesting = etDataTesting.getText().toString().split(",");
                int batasan = Integer.parseInt(etBatasan.getText().toString());
                x1Testing = isiDataTesting[0];
                x2Testing = isiDataTesting[1];
                int jumlah = Integer.parseInt(etJumlah.getText().toString());
                for (int i=0;i<jumlah;i++){
                    d[i] = (((x1[i]-Integer.parseInt(x1Testing))*(x1[i]-Integer.parseInt(x1Testing))) + ((x2[i]-Integer.parseInt(x2Testing))*(x2[i]-Integer.parseInt(x2Testing))));
                }

                for (int i=0;i<jumlah;i++){
                    c[i] = d[i];
                }

                for (int i=0;i<jumlah;i++){
                    for(int j=1;j<(jumlah-i);j++){
                        if(d[j-1]>d[j]){
                            int t = d[j-1];
                            d[j-1] = d[j];
                            d[j] = t;
                        }
                    }
                }
                for (int i=0;i<jumlah;i++){
                    for(int j=0;j<jumlah;j++){
                        if(c[i]==d[j]){
                            r[i] = j+1;
                            continue;
                        }
                    }
                }
                int g=0,b=0;
                for (int i=0;i<jumlah;i++){
                    if(r[i] < (batasan+1)){
                        in[i] = "Ya";
                        if(classi[i].equals("pintar")) g++;
                        else b++;
                    }else{
                        in[i] = "Tidak";
                    }
                }
                ArrayList<Model> list =((Application)CreateActivity.this.getApplication()).getListTable();
                for (int i=0;i<jumlah;i++){
                    list.add(new Model(x1[i],x2[i],c[i],r[i],in[i],classi[i]));
                }
                ((Application)CreateActivity.this.getApplication()).setListTable(list);

                Intent intent = new Intent(CreateActivity.this,MainActivity.class);
                if(g>b){
                    intent.putExtra("prediksi",x1Testing +" Dan "+ x2Testing +"Prediksi Termasuk dalam Kategori Pintar");
                }else{
                    intent.putExtra("prediksi",x1Testing +" Dan "+ x2Testing +"Prediksi Termasuk dalam Kategori Bodoh");
                }
                startActivity(intent);
            }
        });

    }
}
