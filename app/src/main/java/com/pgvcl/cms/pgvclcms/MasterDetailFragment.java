package com.pgvcl.cms.pgvclcms;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MasterDetailFragment extends Fragment {
ConsumerMasterView consumerMasterView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    TableLayout tableLayout;
    String SUB_DIVISION = "Sub Division",CONSUMERNO = "CONSUMER NO",CONSUMER_NAME = "CONSUMER NAME",ADD = "ADDRESS",VILLAGECODE = "VILLAGE CODE",ROUTECODE = "ROUTE CODE",TARRIF = "TARRIF",METERNO = "METER NO",LOAD ="LOAD",FEEDER_CODE = "FEEDER CODE",RENTCODE = "RENT CODE",RELEASE_DATE = "RELEASE DATE",OLDMETERNO ="OLD METER NO";

    public MasterDetailFragment() {
        // Required empty public constructor
    }

    public static MasterDetailFragment newInstance(String param1, String param2) {
        MasterDetailFragment fragment = new MasterDetailFragment();
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
        View root = inflater.inflate(R.layout.fragment_master_detail, container, false);
        tableLayout = (TableLayout) root.findViewById(R.id.tlMaster);
        createTableWithData();
        return root;
    }

    public ConsumerMasterView getConsumerMasterView(String consumerNo){
        consumerMasterView = new ConsumerMasterView("12345678901","Satellite","12346577891","Consumer A","123 START STREET, JUNAGADH","214545","124","LTMD","12345678910","2","30548","125","01-01-2005","");
        return consumerMasterView;

    }
    static class ViewHolderItem {
        TextView column1,column2;
        TableRow tableRow;
    }

    public void createTableWithData(){
        consumerMasterView =getConsumerMasterView("105424587412");
        TableRow header = (TableRow) getLayoutInflater().inflate(R.layout.tablerowmaster,null);


            TextView column1 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
            column1.setTypeface(Typeface.DEFAULT_BOLD);
            column1.setBackgroundResource(R.drawable.tablerowheaderback);
            column1.setText("Sub Division");

            TextView column2 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
            column2.setTypeface(Typeface.DEFAULT_BOLD);
            column2.setBackgroundResource(R.drawable.tablerowheaderback);
            column2.setText("Satellite");


            header.addView(column1);
            header.addView(column2);

            tableLayout.addView(header);

            TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerowmaster,null);
            column1 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
            column2 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
            column1.setTypeface(Typeface.DEFAULT_BOLD);
            column1.setText(CONSUMERNO);
            column2.setText(consumerMasterView.getConsumerNo());
            tableRow.addView(column1);
            tableRow.addView(column2);
            tableLayout.addView(tableRow);

            tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerowmaster,null);
            column1 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
            column2 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column1.setTypeface(Typeface.DEFAULT_BOLD);
            column1.setText(CONSUMER_NAME);
            column2.setText(consumerMasterView.getConsumerName());
            tableRow.addView(column1);
            tableRow.addView(column2);
            tableLayout.addView(tableRow);

        tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerowmaster,null);
        column1 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column2 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column1.setTypeface(Typeface.DEFAULT_BOLD);
        column1.setText(ADD);
        column2.setText(consumerMasterView.getAddress());
        tableRow.addView(column1);
        tableRow.addView(column2);
        tableLayout.addView(tableRow);

        tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerowmaster,null);
        column1 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column2 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column1.setTypeface(Typeface.DEFAULT_BOLD);
        column1.setText(VILLAGECODE);
        column2.setText(consumerMasterView.getVillCode());
        tableRow.addView(column1);
        tableRow.addView(column2);
        tableLayout.addView(tableRow);

        tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerowmaster,null);
        column1 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column2 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column1.setTypeface(Typeface.DEFAULT_BOLD);
        column1.setText(ROUTECODE);
        column2.setText(consumerMasterView.getRouteCode());
        tableRow.addView(column1);
        tableRow.addView(column2);
        tableLayout.addView(tableRow);

        tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerowmaster,null);
        column1 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column2 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column1.setTypeface(Typeface.DEFAULT_BOLD);
        column1.setText(TARRIF);
        column2.setText(consumerMasterView.getConsumerType());
        tableRow.addView(column1);
        tableRow.addView(column2);
        tableLayout.addView(tableRow);

        tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerowmaster,null);
        column1 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column2 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column1.setTypeface(Typeface.DEFAULT_BOLD);
        column1.setText(METERNO);
        column2.setText(consumerMasterView.getMeterNo());
        tableRow.addView(column1);
        tableRow.addView(column2);
        tableLayout.addView(tableRow);

        tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerowmaster,null);
        column1 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column2 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column1.setTypeface(Typeface.DEFAULT_BOLD);
        column1.setText(LOAD);
        column2.setText(consumerMasterView.getLoad());
        tableRow.addView(column1);
        tableRow.addView(column2);
        tableLayout.addView(tableRow);

        tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerowmaster,null);
        column1 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column2 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column1.setTypeface(Typeface.DEFAULT_BOLD);
        column1.setText(FEEDER_CODE);
        column2.setText(consumerMasterView.getFeederCode());
        tableRow.addView(column1);
        tableRow.addView(column2);
        tableLayout.addView(tableRow);

        tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerowmaster,null);
        column1 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column2 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column1.setTypeface(Typeface.DEFAULT_BOLD);
        column1.setText(RENTCODE);
        column2.setText(consumerMasterView.getRouteCode());
        tableRow.addView(column1);
        tableRow.addView(column2);
        tableLayout.addView(tableRow);

        tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerowmaster,null);
        column1 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column2 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column1.setTypeface(Typeface.DEFAULT_BOLD);
        column1.setText(RELEASE_DATE);
        column2.setText(consumerMasterView.getReleaseDate());
        tableRow.addView(column1);
        tableRow.addView(column2);
        tableLayout.addView(tableRow);

        tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerowmaster,null);
        column1 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column2 = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        column1.setTypeface(Typeface.DEFAULT_BOLD);
        column1.setText(OLDMETERNO);
        column2.setText(consumerMasterView.getOldMeterNo());
        tableRow.addView(column1);
        tableRow.addView(column2);
        tableLayout.addView(tableRow);




    }

}
