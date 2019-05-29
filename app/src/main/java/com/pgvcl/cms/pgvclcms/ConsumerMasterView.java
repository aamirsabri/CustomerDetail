package com.pgvcl.cms.pgvclcms;

public class ConsumerMasterView {
    String COLUMN_SDN = "sub_division";
    String sdn,consumerNo,consumerName,address,villCode,routeCode,consumerType,meterNo,load,feederCode,rentCode,releaseDate,oldMeterNo;

    public ConsumerMasterView() {
    }

    public ConsumerMasterView(String COLUMN_SDN, String sdn, String consumerNo, String consumerName, String address, String villCode, String routeCode, String consumerType, String meterNo, String load, String feederCode, String rentCode, String releaseDate, String oldMeterNo) {
        this.COLUMN_SDN = COLUMN_SDN;
        this.sdn = sdn;
        this.consumerNo = consumerNo;
        this.consumerName = consumerName;
        this.address = address;
        this.villCode = villCode;
        this.routeCode = routeCode;
        this.consumerType = consumerType;
        this.meterNo = meterNo;
        this.load = load;
        this.feederCode = feederCode;
        this.rentCode = rentCode;
        this.releaseDate = releaseDate;
        this.oldMeterNo = oldMeterNo;
    }

    public String getCOLUMN_SDN() {
        return COLUMN_SDN;
    }

    public void setCOLUMN_SDN(String COLUMN_SDN) {
        this.COLUMN_SDN = COLUMN_SDN;
    }

    public String getSdn() {
        return sdn;
    }

    public void setSdn(String sdn) {
        this.sdn = sdn;
    }

    public String getConsumerNo() {
        return consumerNo;
    }

    public void setConsumerNo(String consumerNo) {
        this.consumerNo = consumerNo;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVillCode() {
        return villCode;
    }

    public void setVillCode(String villCode) {
        this.villCode = villCode;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public String getConsumerType() {
        return consumerType;
    }

    public void setConsumerType(String consumerType) {
        this.consumerType = consumerType;
    }

    public String getMeterNo() {
        return meterNo;
    }

    public void setMeterNo(String meterNo) {
        this.meterNo = meterNo;
    }

    public String getLoad() {
        return load;
    }

    public void setLoad(String load) {
        this.load = load;
    }

    public String getFeederCode() {
        return feederCode;
    }

    public void setFeederCode(String feederCode) {
        this.feederCode = feederCode;
    }

    public String getRentCode() {
        return rentCode;
    }

    public void setRentCode(String rentCode) {
        this.rentCode = rentCode;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOldMeterNo() {
        return oldMeterNo;
    }

    public void setOldMeterNo(String oldMeterNo) {
        this.oldMeterNo = oldMeterNo;
    }
}
