package com.zmy.laosiji.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.zmy.laosiji.base.MyApplication;

import static com.zmy.laosiji.base.MyApplication.getContext;

/**
 * Created by Michael on 2017/12/25.
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　 ┣┓
 * 　　　　┃　　　　 ┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 */
@Database(entities = TestEntity.class,version = 1)
public abstract class TestDataBase  extends RoomDatabase {
    public abstract TestDao testDao();

    private static volatile TestDataBase INSTANCE;

    public static TestDataBase getInstance() {
        if (INSTANCE == null) {
            synchronized (TestDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(getContext(), TestDataBase.class, "database-name").build();
                }
            }
        }
        return INSTANCE;
    }
}
