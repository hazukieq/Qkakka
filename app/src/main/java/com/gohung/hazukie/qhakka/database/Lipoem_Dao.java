package com.gohung.hazukie.qhakka.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Lipoem_Dao {

    @Query("SELECT * FROM Lipoems WHERE muluk=:muluk")
    Lipoem getLipoemByMuluk(String muluk);

    @Query("SELECT*FROM Lipoems limit 984")
    List<Lipoem> getAllLipoems();

    @Query("SELECT muluk FROM Lipoems")
    LiveData<List<String>> getAllByLivedata();

    @Query("SELECT*FROM Lipoems limit 20")
    List<Lipoem> getFiftypoems();

    @Query("SELECT*FROM Lipoems WHERE id=:id")
    Lipoem getPoemById(int id);

    @Query("SELECT * FROM Lipoems WHERE id>984 limit 20")
    List<Lipoem> getAllLunnzis();

    @Query("SELECT * FROM Lipoems WHERE id>1004 limit 20")
    List<Lipoem> getTwentyDufupoems();

    @Query("SELECT * FROM Lipoems WHERE id>1004 limit 1170")
    List<Lipoem> getAllDufupoems();

    @Query("SELECT * FROM Lipoems WHERE id>2174 limit 33")
    List<Lipoem> getAllZongzus();

    @Query("SELECT * FROM Lipoems WHERE id>2174 limit 22")
    List<Lipoem> getTwentyZongzus();

    @Query("SELECT * FROM Lipoems WHERE id>2207 limit 20")
    List<Lipoem> getTwentyLauzus();

    @Query("SELECT * FROM Lipoems WHERE id>2207 limit 81")
    List<Lipoem> getAllLauzus();

}
