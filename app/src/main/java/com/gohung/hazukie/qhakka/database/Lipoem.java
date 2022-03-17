package com.gohung.hazukie.qhakka.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.w3c.dom.Text;

@Entity(tableName = "Lipoems")
public class Lipoem {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id",typeAffinity = ColumnInfo.INTEGER)
    public int id;

    @NonNull
    @ColumnInfo(name = "muluk",typeAffinity = ColumnInfo.TEXT)
    public String muluk;

    @NonNull
    @ColumnInfo(name = "content",typeAffinity = ColumnInfo.TEXT)
    public String content;


    public Lipoem(@NonNull int id,@NonNull String muluk,@NonNull String content){
        this.id=id;
        this.muluk=muluk;
        this.content=content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getMuluk() {
        return muluk;
    }
}
