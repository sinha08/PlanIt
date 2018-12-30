package planit.sinha.ankur.com.planit.data;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import planit.sinha.ankur.com.planit.data.local.db.CategoryLocalSource;
import planit.sinha.ankur.com.planit.data.model.db.Category;

/**
 * Created by ankur sinha on 15-12-2018.
 */

public class CategoryRepository implements CategoryDataSource {
    private static CategoryRepository INSTANCE = null;

    private final CategoryLocalSource mLocalSource;

    // Prevent direct instantiation.
    private CategoryRepository(@NonNull CategoryLocalSource categoryLocalSource) {
        mLocalSource = categoryLocalSource;
    }

    /**
     *
     * @param categoryLocalSource
     * @return
     */
    public static CategoryRepository getInstance(CategoryLocalSource categoryLocalSource) {
        if (INSTANCE == null) {
            INSTANCE = new CategoryRepository(categoryLocalSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(CategoryLocalSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getCategories(@NonNull LoadCategoriesCallback callback) {
        mLocalSource.getCategories(new LoadCategoriesCallback() {

            @Override
            public void onCategoriessLoaded(List<Category> categories) {
                callback.onCategoriessLoaded(categories);
            }

            @Override
            public void onDataNotAvailable() {
                //TODO remote call
            }
        });
    }

    @Override
    public void saveCategories(Category category) {
        mLocalSource.saveCategories(category);
    }

    @Override
    public LiveData<Category> getCategoryById(int id) {
        return mLocalSource.getCategoryById(id);
    }

    @Override
    public void deleteCategory(Category category) {
        mLocalSource.deleteCategory(category);
    }
}
