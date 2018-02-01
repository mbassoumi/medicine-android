package com.example.majd.medicinereader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.majd.medicinereader.api.classes.MedicineData;

/**
 * Created by Majd on 2018-01-26.
 */

public class Tab1HowUse extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1_how_use, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label_how_use);

        try {

            textView.setText("كيفية الاستخدام");
            ResultActivity activity = (ResultActivity)getActivity();
            MedicineData  medicineData = activity.medicineData;
            if (medicineData != null) {
                textView.setText(medicineData.getMedicine().getUse());
            }
        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

        }
        return rootView;
    }
}
