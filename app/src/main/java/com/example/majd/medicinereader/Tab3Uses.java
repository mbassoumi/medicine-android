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

public class Tab3Uses extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3_uses, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label_uses);
//        String data = getArguments().get
        try {
            textView.setText("وقت الاستعمال");
            ResultActivity activity = (ResultActivity)getActivity();
            MedicineData medicineData = activity.medicineData;
            if (medicineData != null) {
                textView.setText("");
                for (int i = 0; i< medicineData.getUses().size(); i++){
                    textView.append(" * ");
                    textView.append(medicineData.getUses().get(i).getUse_for());
                    textView.append("\n");
                }
            }
        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

        }
        return rootView;
    }
}
