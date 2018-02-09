package com.example.majd.medicinereader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.majd.medicinereader.api.classes.MedicineData;

import java.util.ArrayList;

/**
 * Created by Majd on 2018-01-26.
 */

public class Tab4Warnings extends Fragment {

    private ArrayAdapter<String> listAdapter ;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab4_warnings, container, false);
//        TextView textView = (TextView) rootView.findViewById(R.id.section_label_warnings);

        ArrayList<String> side_effects_ArrayList = new ArrayList<String>();
//        listAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, side_effects_ArrayList);
        listAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.list_black_text, R.id.list_content, side_effects_ArrayList);

        listView = (ListView)rootView.findViewById(R.id.warnings_listview);
        try {
//            textView.setText("تحذيرات الدواء");
            ResultActivity activity = (ResultActivity)getActivity();
            MedicineData medicineData = activity.medicineData;
            if (medicineData != null) {
//                textView.setText("");
                for (int i = 0; i< medicineData.getWarnings().size(); i++){
//                    textView.append(" * ");
//                    textView.append(medicineData.getWarnings().get(i).getWarning());
//                    textView.append("\n");
                    listAdapter.add(medicineData.getWarnings().get(i).getWarning());
                }
                listView.setAdapter(listAdapter);
            }
        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

        }
        return rootView;
    }
}
