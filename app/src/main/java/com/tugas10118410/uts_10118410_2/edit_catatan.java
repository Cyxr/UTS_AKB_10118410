//5 Juni 2021, 10118410, Ridwan Caesarahman Julian, IF-10
package com.tugas10118410.uts_10118410_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class edit_catatan extends AppCompatActivity {

    private EditText kategori;
    private EditText judul;
    private EditText isi;
    private Memo memo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_catatan);

        this.kategori =  findViewById(R.id.kategori);
        this.judul =  findViewById(R.id.judul);
        this.isi =  findViewById(R.id.isi);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel =  findViewById(R.id.btnCencel);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            memo = (Memo)bundle.get("MEMO");
            if (memo != null){
                this.kategori.setText(memo.getKategori());
                this.judul.setText(memo.getJudul());
                this.isi.setText(memo.getIsi());
            }
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClicked();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void onSaveClicked(){
        AksesDatabase databaseAcces = AksesDatabase.getInstance(this);
        databaseAcces.open();
        if (memo == null){
            Memo temp = new Memo();
            temp.setKategori(kategori.getText().toString());
            temp.setJudul(judul.getText().toString());
            temp.setIsi(isi.getText().toString());
            databaseAcces.save(temp);
        }else {
            memo.setKategori(kategori.getText().toString());
            memo.setJudul(judul.getText().toString());
            memo.setIsi(isi.getText().toString());
            databaseAcces.update(memo);
        }
        databaseAcces.close();
        this.finish();
    }
}
