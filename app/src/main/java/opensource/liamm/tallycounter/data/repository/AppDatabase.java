package opensource.liamm.tallycounter.data.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import opensource.liamm.tallycounter.data.repository.entity.IntegerCounter;
import opensource.liamm.tallycounter.data.repository.dao.IntegerCounterDao;

@Database(entities = {IntegerCounter.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract IntegerCounterDao integerCounterDao();

    private static final String DB_NAME = "counter_db";
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
