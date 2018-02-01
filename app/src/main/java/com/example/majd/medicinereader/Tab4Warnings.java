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

public class Tab4Warnings extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab4_warnings, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label_warnings);
        try {
            textView.setText("تحذيرات الدواء");
            ResultActivity activity = (ResultActivity)getActivity();
            MedicineData medicineData = activity.medicineData;
            if (medicineData != null) {
                textView.setText("");
                for (int i = 0; i< medicineData.getWarnings().size(); i++){
                    textView.append(" * ");
                    textView.append(medicineData.getWarnings().get(i).getWarning());
                    textView.append("\n");
                }
            }
        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

        }
        return rootView;
    }
}
