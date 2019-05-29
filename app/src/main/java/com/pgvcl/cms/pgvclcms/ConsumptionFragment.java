package com.pgvcl.cms.pgvclcms;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class ConsumptionFragment extends Fragment {
    TableLayout tableLayout;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    int[] years = {2016,2017,2018};
    String[] months = {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
    int[][] consumptions = {
        {101,142,159},
        {201,521,159},
            {147,247,125},
            {147,258,369},
            {254,256,145},
            {185,247,211},
            {214,412,154},
            {220,214,258},
            {156,147,120},
            {254,256,145},
            {185,247,211},
            {214,412,154}
    };

    public ConsumptionFragment() {
        // Required empty public constructor
    }

    public static ConsumptionFragment newInstance(String param1, String param2) {
        ConsumptionFragment fragment = new ConsumptionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_consumption, container, false);
        tableLayout = (TableLayout) root.findViewById(R.id.tlConsumption);
        createTableWithData();
        return root;

    }
    public void createTableWithData(){
        for(int i = -1; i<12;i++){
            TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerowmaster,null);
            for(int j=-1;j<3;j++){
                TextView textView = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
                if(j == -1 && i == -1){
                    textView.setBackgroundResource(R.drawable.tablecolumnmonthback);
                    textView.setTextColor(Color.parseColor("#ffffff"));
                    textView.setText("MONTH");

                }else if(i ==-1){
                    textView.setText(String.valueOf(years[j]));
                }else if(j ==-1){

                    textView.setTextColor(Color.parseColor("#ffffff"));
                    textView.setBackgroundResource(R.drawable.tablecolumnmonthback);
                    textView.setText(String.valueOf(months[i]));

                }else{
                    textView.setText(String.valueOf(consumptions[i][j]));
                }
                if(i == -1 && j !=-1){
                    textView.setBackgroundResource(R.drawable.tablerowheaderback);
                    textView.setTypeface(Typeface.DEFAULT_BOLD);
                }
            tableRow.addView(textView);
            }
            tableLayout.addView(tableRow);
        }
    }

}
