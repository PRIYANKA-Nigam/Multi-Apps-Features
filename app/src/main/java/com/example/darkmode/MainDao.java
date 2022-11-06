package com.example.darkmode;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {
    @Insert(onConflict = REPLACE)
    void  insert(MainData mainData);

    @Delete
    void delete(MainData mainData);

    @Delete
    void reset(List<MainData> mainData);
    //update
    @Query("UPDATE room SET text= :stext where id= :sid")
    void update(int sid,String stext);
    @Query("Select * from room")
    List<MainData> getAll();


}
