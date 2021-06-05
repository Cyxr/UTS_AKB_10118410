//5 Juni 2021, 10118410, Ridwan Caesarahman Julian, IF-10
package com.tugas10118410.uts_10118410_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class Catatan_Activity extends AppCompatActivity {

    private ListView listView;
    private AksesDatabase databaseAcces;
    private List<Memo> memos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan_);

        databaseAcces = AksesDatabase.getInstance(this);

        listView = findViewById(R.id.listView);
        Button btnBuat = findViewById(R.id.btnBuat);

        btnBuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Catatan_Activity.this, edit_catatan.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            if(bundle.getString ("some") !=null){
                Toast.makeText(getApplicationContext(),
                        "data:" + bundle.getString("some"), Toast.LENGTH_SHORT).show();
            }
        }
    }




    @Override
    protected void onResume() {
        super.onResume();
        databaseAcces.open();
        this.memos = databaseAcces.getAllMemos();
        databaseAcces.close();
        MemoAdapter adapter = new MemoAdapter(this,memos);
        this.listView.setAdapter(adapter);
    }



    private class MemoAdapter extends ArrayAdapter<Memo>{

        MemoAdapter(@NonNull Context context, List<Memo> objects) {
            super(context, 0,  objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.layout_list_item, parent, false);
            }

            Button btnEdit = convertView.findViewById(R.id.btnEdit);
            Button btnDelete = convertView.findViewById(R.id.btnDelete);

            TextView txtDate =  convertView.findViewById(R.id.txtDate);
            TextView txtkategori = convertView.findViewById(R.id.txtkategori);
            TextView txtjudul = convertView.findViewById(R.id.txtjudul);
            TextView txtisi = convertView.findViewById(R.id.txtisi);

            final Memo memo = memos.get(position);
            txtDate.setText(memo.getDate());
            txtkategori.setText(memo.getShortKategori());
            txtjudul.setText(memo.getShortJudul());
            txtisi.setText(memo.getShortIsi());

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Catatan_Activity.this, edit_catatan.class);
                    intent.putExtra("MEMO", memo);
                    startActivity(intent);
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    databaseAcces.open();
                    databaseAcces.delete(memo);
                    databaseAcces.close();

                    ArrayAdapter<Memo> adapter = (ArrayAdapter<Memo>) listView.getAdapter();
                    adapter.remove(memo);
                    adapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }

    }

}
