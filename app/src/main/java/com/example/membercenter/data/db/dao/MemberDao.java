package com.example.membercenter.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.membercenter.data.db.entity.Member;

@Dao
public interface MemberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Member member);

    @Delete
    void delete(Member member);


    /*
    * 当数据库更新时，Room会生成LiveData所必需的所有代码
    * */
    @Query("Select * From Member where _id = :id limit 1")
    LiveData<Member> select(int id);
}
