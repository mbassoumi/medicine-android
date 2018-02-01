package com.example.majd.medicinereader;

import android.app.SearchManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
barcode reader
 */
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.content.Intent;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button scanBtn;
    private SearchView searchView;

    private  Intent searchIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        searchIntent = new Intent(getApplicationContext(),Search2Activity.class);


        scanBtn = (Button)findViewById(R.id.scan_button);

        searchView = (SearchView)findViewById(R.id.search_input);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(getApplicationContext(),query,Toast.LENGTH_LONG).show();
                searchIntent.putExtra("medicine_name", query);
                startActivity(searchIntent);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        scanBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        //respond to clicks
        if(v.getId()==R.id.scan_button){
            //scan
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

//            formatTxt.setText("FORMAT: " + scanFormat);
//            contentTxt.setText("CONTENT: " + scanContent);

            Intent i = new Intent(this, ResultActivity.class);
            i.putExtra("scanFormat", scanFormat);
            i.putExtra("scanContent", scanContent);
            startActivity(i);

        }else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "لم يتم التعرف على الكود", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

