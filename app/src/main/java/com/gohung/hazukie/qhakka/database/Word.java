package com.gohung.hazukie.qhakka.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word")
public class Word {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id",typeAffinity = ColumnInfo.INTEGER)
    public int id;

    @NonNull
    @ColumnInfo(name = "hz",typeAffinity = ColumnInfo.TEXT)
    public String hz;

    @NonNull
    @ColumnInfo(name = "bh",typeAffinity = ColumnInfo.TEXT)
    public String bh;

    @NonNull
    @ColumnInfo(name = "hk_p",typeAffinity = ColumnInfo.TEXT)
    public String hk_p;

    @NonNull
    @ColumnInfo(name = "hk",typeAffinity = ColumnInfo.TEXT)
    public String hk;

    @NonNull
    @ColumnInfo(name = "va",typeAffinity = ColumnInfo.TEXT)
    public String va;

    @NonNull
    @ColumnInfo(name = "cmn_p",typeAffinity = ColumnInfo.TEXT)
    public String cmn_p;

    @NonNull
    @ColumnInfo(name = "ltc_mc",typeAffinity = ColumnInfo.TEXT)
    public String ltc_mc;

    @NonNull
    @ColumnInfo(name = "sw",typeAffinity = ColumnInfo.TEXT)
    public String sw;

    @NonNull
    @ColumnInfo(name = "kx",typeAffinity = ColumnInfo.TEXT)
    public String kx;

    @NonNull
    @ColumnInfo(name = "hd",typeAffinity = ColumnInfo.TEXT)
    public String hd;

    public Word(@NonNull int id,@NonNull String hz,@NonNull String bh,@NonNull String hk_p,@NonNull String hk,@NonNull String cmn_p,@NonNull String va,@NonNull String ltc_mc,@NonNull String sw,@NonNull String kx,@NonNull String hd){
        this.id=id;
        this.hz=hz;
        this.bh=bh;
        this.hk_p=hk_p;
        this.hk=hk;
        this.cmn_p=cmn_p;
        this.ltc_mc=ltc_mc;
        this.sw=sw;
        this.kx=kx;
        this.hd=hd;
        this.va=va;
    }

    public String getHz() {
        return hz;
    }

    public String getCmn_p() {
        return cmn_p;
    }

    public String getBh() {
        return bh;
    }

    public String getHk_p() {
        return hk_p;
    }

    public String getVa() {
        return va;
    }

    public String getSw() {
        return sw;
    }

    public String getKx() {
        return kx;
    }

    public String getHd() {
        return hd;
    }

    public String getHk() {
        return hk;
    }

    public int getId() {
        return id;
    }

    public String replaceHz(){
        return "<font color=\"red\">"+hz+"</font>";
    }
}
