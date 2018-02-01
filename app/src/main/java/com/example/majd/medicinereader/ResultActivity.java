package com.example.majd.medicinereader;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import com.example.majd.medicinereader.api.classes.MedicineData;
import com.example.majd.medicinereader.api.interfaces.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ResultActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private TextView side_effect_textView;
    private TextView uses_textView;
    private TextView warnings_textView;
    private TextView store_textView;
    private TextView how_use_textView;
    private Toolbar toolbar;
    private ListView listView;

    Tab1HowUse tab1 = new Tab1HowUse();
    Tab2SideEffects tab2 = new Tab2SideEffects();
    Tab3Uses tab3 = new Tab3Uses();
    Tab4Warnings tab4 = new Tab4Warnings();
    Tab5Storing tab5 = new Tab5Storing();
    private FragmentTransaction ft;



    public MedicineData medicineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);




        // -------------------------------------------------------------------------



        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("معلومات الدواء");


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        // -------------------------------------------------------------------------


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://medicine-reader.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface api = retrofit.create(RetrofitInterface.class);

        Call<MedicineData> call =api.getMedicineData("72900008000281") ;

        call.enqueue(new Callback<MedicineData>() {

            @Override
            public void onResponse(Call<MedicineData> call, Response<MedicineData> response) {

                try {
                    medicineData = response.body();
                    try {
                        toolbar.setTitle(medicineData.getMedicine().getName());
                        how_use_textView = (TextView)findViewById(R.id.section_label_how_use);
                        side_effect_textView = (TextView)findViewById(R.id.section_label_side_effect);
                        how_use_textView.setText(medicineData.getMedicine().getUse());
                        side_effect_textView.setText("");
                        for (int i = 0; i< medicineData.getSideEffects().size(); i++){
                            side_effect_textView.append(" * ");
                            side_effect_textView.append(medicineData.getSideEffects().get(i).getSide_effects());
                            side_effect_textView.append("\n");
                        }

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),"عذرا. هذا الدواء غير مسجل لدي :(",Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {

//                    Toast.makeText(getApplicationContext(),"حدث خطأ بالتطبيق",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }

                try {
                    ft = getSupportFragmentManager().beginTransaction();
//                    ft.detach(tab1);
//                    ft.attach(tab1);
//                    ft.detach(tab2);
//                    ft.attach(tab2);
//                    ft.detach(tab3);
//                    ft.attach(tab3);
//                    ft.detach(tab4);
//                    ft.attach(tab4);
//                    ft.detach(tab5);
//                    ft.attach(tab5);
                    ft.commit();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<MedicineData> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });



    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_result, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.homeAsUp) {
//            Toast toast = Toast.makeText(getApplicationContext(),
//                    "back!", Toast.LENGTH_SHORT);
//            toast.show();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // return the current tabs
            switch (position){
                case 0:
//                    Tab1HowUse tab1 = new Tab1HowUse();
                    return tab1;
                case 1:
//                    Tab2SideEffects tab2 = new Tab2SideEffects();
                    return tab2;
                case 2:
//                    Tab3Uses tab3 = new Tab3Uses();
                    return tab3;
                case 3:
//                    Tab4Warnings tab4 = new Tab4Warnings();
                    return tab4;
                case 4:
//                    Tab5Storing tab5 = new Tab5Storing();
                    return tab5;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "طريقة الاستخدام";
                case 1:
                    return "اثار جانبية";
                case 2:
                    return "استعمال";
                case 3:
                    return "تحذيرات";
                case 4:
                    return "طريقة الحفظ";
                default:
                    return null;
            }
        }
    }
}
