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

import java.util.Random;

public class BillingFragment extends Fragment {
BillingView billingView;
TableLayout tableLayout;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BillingFragment() {
        // Required empty public constructor
    }

    public static BillingFragment newInstance(String param1, String param2) {
        BillingFragment fragment = new BillingFragment();
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
        View root = inflater.inflate(R.layout.fragment_billing, container, false);
        tableLayout = root.findViewById(R.id.tlBilling);
        createTableWithData();
        // Inflate the layout for this fragment
        return root;
    }

    public void createTableWithData(){
        createHeader();
        loadDataInTable();

    }


    public void createHeader(){
        TableRow header = (TableRow) getLayoutInflater().inflate(R.layout.tablerowmaster,null);

        TextView tvYear = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        tvYear.setTypeface(Typeface.DEFAULT_BOLD);
        tvYear.setBackgroundResource(R.drawable.tablerowheaderback);
        tvYear.setText("YEAR");
        header.addView(tvYear);

        TextView tvMonth = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        tvMonth.setTypeface(Typeface.DEFAULT_BOLD);
        tvMonth.setBackgroundResource(R.drawable.tablerowheaderback);
        tvMonth.setText("MONTH");
        header.addView(tvMonth);

        TextView tvBillDate = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        tvBillDate.setTypeface(Typeface.DEFAULT_BOLD);
        tvBillDate.setBackgroundResource(R.drawable.tablerowheaderback);
        tvBillDate.setText("BILL DATE");
        header.addView(tvBillDate);

        TextView tvReading = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        tvReading.setTypeface(Typeface.DEFAULT_BOLD);
        tvReading.setBackgroundResource(R.drawable.tablerowheaderback);
        tvReading.setText("READING");
        header.addView(tvReading);

        TextView tvConsumption = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        tvConsumption.setTypeface(Typeface.DEFAULT_BOLD);
        tvConsumption.setBackgroundResource(R.drawable.tablerowheaderback);
        tvConsumption.setText("CONSUMPTION");
        header.addView(tvConsumption);

        TextView tvBillAmount = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        tvBillAmount.setTypeface(Typeface.DEFAULT_BOLD);
        tvBillAmount.setBackgroundResource(R.drawable.tablerowheaderback);
        tvBillAmount.setText("BILL AMOUNT");
        header.addView(tvBillAmount);

        TextView tvArrear = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
        tvArrear.setTypeface(Typeface.DEFAULT_BOLD);
        tvArrear.setBackgroundResource(R.drawable.tablerowheaderback);
        tvArrear.setText("ARREAR");
        header.addView(tvArrear);

        tableLayout.addView(header);
    }

    public void loadDataInTable(){
        Random random = new Random();
        int reading=1000,consumption=0;
        for(int i=0;i<12;i++){
            reading = reading + random.nextInt(300);
            consumption = random.nextInt(300);
            BillingView billingView = new BillingView(2018,i+1,reading,random.nextInt(600),consumption*6,0,"05-" + (i+1) + "-2018");

            TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.tablerowmaster,null);

            TextView tvYear = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
            tvYear.setText(String.valueOf(billingView.getYear()));
            tableRow.addView(tvYear);

            TextView tvMonth = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
            tvMonth.setText(String.valueOf(billingView.getMonth()));
            tableRow.addView(tvMonth);

            TextView tvBillDate = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
            tvBillDate.setText(String.valueOf(billingView.getBillDate()));
            tableRow.addView(tvBillDate);

            TextView tvReading = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
            tvReading.setText(String.valueOf(billingView.getReading()));
            tableRow.addView(tvReading);

            TextView tvConsumption = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
            tvConsumption.setText(String.valueOf(billingView.getConsumption()));
            tableRow.addView(tvConsumption);

            TextView tvBillAmount = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
            tvBillAmount.setText(String.valueOf(billingView.getBill_amount()));
            tableRow.addView(tvBillAmount);

            TextView tvArrear = (TextView) getLayoutInflater().inflate(R.layout.tablecellmasterlayout,null);
            tvArrear.setText(String.valueOf(billingView.getArrear()));
            tableRow.addView(tvArrear);

            tableLayout.addView(tableRow);
        }
    }
    public BillingView getBillingView(String consumerNo){
        return null;

    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
