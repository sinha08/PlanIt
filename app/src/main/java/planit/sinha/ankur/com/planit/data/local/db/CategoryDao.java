package planit.sinha.ankur.com.planit.data.local.db;

/**
 * Created by ankur sinha on 15-12-2018.
 */

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.List;

import planit.sinha.ankur.com.planit.data.model.db.Category;

@Dao
public interface CategoryDao {

    @Delete
    void delete(Category category);

    @Query("DELETE FROM category WHERE text = :name")
    void delete(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Category category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Category> categories);

    @Query("SELECT * FROM category")
    List<Category> loadAll();

    @Query("SELECT * FROM category WHERE id IN (:categoryIds)")
    List<Category> loadAllByIds(List<Integer> categoryIds);

    @Query("SELECT * FROM category WHERE id = :id")
    LiveData<Category> getCategoryById(int id);
}
