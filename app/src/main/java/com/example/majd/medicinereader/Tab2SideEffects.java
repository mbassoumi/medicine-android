package com.example.majd.medicinereader;

/**
 * Created by Majd on 2018-01-26.
 */


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.majd.medicinereader.api.classes.MedicineData;


public class Tab2SideEffects extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2_side_effects, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label_side_effect);
        try {
            textView.setText("الاثار الجانبية");
            ResultActivity activity = (ResultActivity)getActivity();
            MedicineData medicineData = activity.medicineData;
            if (medicineData != null) {
                textView.setText("");
                for (int i = 0; i< medicineData.getSideEffects().size(); i++){
                    textView.append(" * ");
                    textView.append(medicineData.getSideEffects().get(i).getSide_effects());
                    textView.append("\n");
                }
            }
        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

        }
        return rootView;
    }
}
