package com.pgvcl.cms.pgvclcms;

public class BillingView {
    int year,month,reading,consumption,bill_amount,arrear;
    String billDate;

    public BillingView(int year, int month, int reading, int consumption, int bill_amount, int arrear, String billDate) {
        this.year = year;
        this.month = month;
        this.reading = reading;
        this.consumption = consumption;
        this.bill_amount = bill_amount;
        this.arrear = arrear;
        this.billDate = billDate;
    }

    public BillingView() {

    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getReading() {
        return reading;
    }

    public void setReading(int reading) {
        this.reading = reading;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public int getBill_amount() {
        return bill_amount;
    }

    public void setBill_amount(int bill_amount) {
        this.bill_amount = bill_amount;
    }

    public int getArrear() {
        return arrear;
    }

    public void setArrear(int arrear) {
        this.arrear = arrear;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }
}
