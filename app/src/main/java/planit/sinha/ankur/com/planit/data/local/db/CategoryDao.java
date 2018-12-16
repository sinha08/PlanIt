package planit.sinha.ankur.com.planit.data.local.db;

/**
 * Created by ankur sinha on 15-12-2018.
 */

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.List;
import io.reactivex.Observable;

import planit.sinha.ankur.com.planit.data.model.db.Category;

@Dao
public interface CategoryDao {

    @Delete
    void delete(Category user);

    @Query("SELECT * FROM users WHERE name LIKE :name LIMIT 1")
    Observable<Category> findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Category user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Category> users);

    @Query("SELECT * FROM users")
    Observable<List<Category>> loadAll();

    @Query("SELECT * FROM users WHERE id IN (:userIds)")
    Observable<List<Category>> loadAllByIds(List<Integer> userIds);
}
