package com.phunlh2001.prm392_beverages.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.phunlh2001.prm392_beverages.data.dao.CartDao;
import com.phunlh2001.prm392_beverages.data.dao.CategoryDao;
import com.phunlh2001.prm392_beverages.data.dao.OrderDao;
import com.phunlh2001.prm392_beverages.data.dao.OrderDetailDao;
import com.phunlh2001.prm392_beverages.data.dao.ProductDao;
import com.phunlh2001.prm392_beverages.data.dao.UserDao;
import com.phunlh2001.prm392_beverages.data.entities.Category;
import com.phunlh2001.prm392_beverages.data.entities.Order;
import com.phunlh2001.prm392_beverages.data.entities.OrderDetail;
import com.phunlh2001.prm392_beverages.data.entities.Product;
import com.phunlh2001.prm392_beverages.data.entities.User;
import com.phunlh2001.prm392_beverages.utils.DateConverter;

@Database(entities = {Category.class, Product.class, User.class, Order.class, OrderDetail.class},
        version = 3, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "_beverages.db";
    private static AppDatabase INSTANCE;

    static final Migration MIGRATION_2_TO_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // ALTER TABLE <table_name> ADD COLUMN <column_name> <datatype>
            database.execSQL("DROP TABLE Orders"); // drop table of migration v2
            database.execSQL("CREATE TABLE Orders (\n" +
                    "id INTEGER NOT NULL,\n" +
                    "total_price REAL NOT NULL,\n" +
                    "type TEXT,\n" +
                    "status TEXT,\n" +
                    "user_id INTEGER NOT NULL,\n" +
                    "createAt TEXT DEFAULT (datetime('now', 'localtime')),\n" +
                    "PRIMARY KEY(id),\n" +
                    "FOREIGN KEY(user_id) REFERENCES Users(id) ON UPDATE NO ACTION ON DELETE CASCADE)");
            database.execSQL("CREATE INDEX index_Orders_user_id ON Orders (user_id)");
        }
    };

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_2_TO_3)
                    .createFromAsset("database/_data.db")
                    .build();
        }
        return INSTANCE;
    }

    public void destroyInstance() {
        INSTANCE = null;
    }

    // DAOs
    public abstract ProductDao productDao();
    public abstract CategoryDao categoryDao();
    public abstract UserDao userDao();
    public abstract OrderDao orderDao();
    public abstract OrderDetailDao orderDetailDao();
    public abstract CartDao cartDao();
}
