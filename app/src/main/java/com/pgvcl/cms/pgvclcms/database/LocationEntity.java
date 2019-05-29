package com.pgvcl.cms.pgvclcms.database;

public class LocationEntity {
    public static final String COL_CIRCODE = "CIRCODE",COL_CIR_LOCCODE = "CIRELOCCD",COL_CIR_NAME = "CIRNAME",COL_DVNCODE = "DVNCODE",COL_DVNLOCCODE = "DVNELOCCD",COL_DVNAME = "DVNNAME", COL_SDNCODE = "SDNCODE",COL_SDNLOC ="LOC",COL_SDNLOCCODE ="ELOCCD",COL_SDNNAME = "NAME";
    String cirCode,cirLocCode,cirName,dvnCode,dvnLocCode,dvnName,sdnCode,sdnLoc,sdnLocCode,sdnName;

    public LocationEntity(){}
    public LocationEntity(String cirCode, String cirLocCode, String cirName, String dvnCode, String dvnLocCode, String dvnName, String sdnCode, String sdnLoc, String sdnLocCode, String sdnName) {
        this.cirCode = cirCode;
        this.cirLocCode = cirLocCode;
        this.cirName = cirName;
        this.dvnCode = dvnCode;
        this.dvnLocCode = dvnLocCode;
        this.dvnName = dvnName;
        this.sdnCode = sdnCode;
        this.sdnLoc = sdnLoc;
        this.sdnLocCode = sdnLocCode;
        this.sdnName = sdnName;
    }

    public static String getColCircode() {
        return COL_CIRCODE;
    }

    public static String getColCirLoccode() {
        return COL_CIR_LOCCODE;
    }

    public static String getColCirName() {
        return COL_CIR_NAME;
    }

    public static String getColDvncode() {
        return COL_DVNCODE;
    }

    public static String getColDvnloccode() {
        return COL_DVNLOCCODE;
    }

    public static String getColDvname() {
        return COL_DVNAME;
    }

    public static String getColSdncode() {
        return COL_SDNCODE;
    }

    public static String getColSdnloc() {
        return COL_SDNLOC;
    }

    public static String getColSdnloccode() {
        return COL_SDNLOCCODE;
    }

    public static String getColSdnname() {
        return COL_SDNNAME;
    }

    public String getCirCode() {
        return cirCode;
    }

    public void setCirCode(String cirCode) {
        this.cirCode = cirCode;
    }

    public String getCirLocCode() {
        return cirLocCode;
    }

    public void setCirLocCode(String cirLocCode) {
        this.cirLocCode = cirLocCode;
    }

    public String getCirName() {
        return cirName;
    }

    public void setCirName(String cirName) {
        this.cirName = cirName;
    }

    public String getDvnCode() {
        return dvnCode;
    }

    public void setDvnCode(String dvnCode) {
        this.dvnCode = dvnCode;
    }

    public String getDvnLocCode() {
        return dvnLocCode;
    }

    public void setDvnLocCode(String dvnLocCode) {
        this.dvnLocCode = dvnLocCode;
    }

    public String getDvnName() {
        return dvnName;
    }

    public void setDvnName(String dvnName) {
        this.dvnName = dvnName;
    }

    public String getSdnCode() {
        return sdnCode;
    }

    public void setSdnCode(String sdnCode) {
        this.sdnCode = sdnCode;
    }

    public String getSdnLoc() {
        return sdnLoc;
    }

    public void setSdnLoc(String sdnLoc) {
        this.sdnLoc = sdnLoc;
    }

    public String getSdnLocCode() {
        return sdnLocCode;
    }

    public void setSdnLocCode(String sdnLocCode) {
        this.sdnLocCode = sdnLocCode;
    }

    public String getSdnName() {
        return sdnName;
    }

    public void setSdnName(String sdnName) {
        this.sdnName = sdnName;
    }

    @Override
    public String toString() {
        return "LocationEntity{" +
                "cirCode='" + cirCode + '\'' +
                ", cirLocCode='" + cirLocCode + '\'' +
                ", cirName='" + cirName + '\'' +
                ", dvnCode='" + dvnCode + '\'' +
                ", dvnLocCode='" + dvnLocCode + '\'' +
                ", dvnName='" + dvnName + '\'' +
                ", sdnCode='" + sdnCode + '\'' +
                ", sdnLoc='" + sdnLoc + '\'' +
                ", sdnLocCode='" + sdnLocCode + '\'' +
                ", sdnName='" + sdnName + '\'' +
                '}';
    }
}