package com.example.membercenter.data.db.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.membercenter.data.db.dao.MemberDao;
import com.example.membercenter.data.db.entity.Member;

@Database(entities = {Member.class},version = 1,exportSchema = false)
public abstract class MemberDatabase  extends RoomDatabase {

    private static MemberDatabase instance;

    public abstract MemberDao memberDao();

    public static synchronized MemberDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MemberDatabase.class,"member-database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
