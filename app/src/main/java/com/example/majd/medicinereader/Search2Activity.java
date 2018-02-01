package com.example.majd.medicinereader;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.majd.medicinereader.api.classes.Medicine;
import com.example.majd.medicinereader.api.classes.MedicineData;
import com.example.majd.medicinereader.api.interfaces.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Search2Activity extends AppCompatActivity {


    public List<Medicine> medicines;
    private ListView search_result_listview;
    private ArrayAdapter<String> listAdapter ;
    private Intent medicine_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        medicine_intent = new Intent(this, ResultActivity.class);



        search_result_listview = (ListView)findViewById(R.id.search_result_listview);

        search_result_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                medicine_intent.putExtra("scanContent", medicines.get(i).getBarcode());
                startActivity(medicine_intent);
            }
        });

        ArrayList<String> medicines_arraylist = new ArrayList<String>();
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medicines_arraylist);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://medicine-reader.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface api = retrofit.create(RetrofitInterface.class);

        String medicine_name = getIntent().getStringExtra("medicine_name");

        Call<List<Medicine>> call =api.getSearchResult(medicine_name);

        call.enqueue(new Callback<List<Medicine>>() {
            @Override
            public void onResponse(Call<List<Medicine>> call, Response<List<Medicine>> response) {
                try {

                    medicines = response.body();
                    if (medicines.size() > 0) {
                        for (int i = 0; i < medicines.size(); i++) {
//                        Toast.makeText(getApplicationContext(),medicines.get(i).getName(),Toast.LENGTH_LONG).show();
                            listAdapter.add(medicines.get(i).getName());
                        }
                        search_result_listview.setAdapter(listAdapter);
                    } else {
                        Toast.makeText(getApplicationContext(), "لا يوجد لدي دواء بهذا الاسم :(", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"لقد حدث خطأ.. حاول مرة اخرى",Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<List<Medicine>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"لا يوجد اتصال مع الانترنت :(",Toast.LENGTH_LONG).show();
                finish();
            }
        });


    }

}
