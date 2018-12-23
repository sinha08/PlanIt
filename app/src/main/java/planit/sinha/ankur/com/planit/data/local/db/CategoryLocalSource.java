package planit.sinha.ankur.com.planit.data.local.db;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import java.util.List;

import io.reactivex.Observable;
import planit.sinha.ankur.com.planit.AppExecutors;
import planit.sinha.ankur.com.planit.data.CategoryDataSource;
import planit.sinha.ankur.com.planit.data.model.db.Category;

/**
 * Created by ankur sinha on 15-12-2018.
 */

public class CategoryLocalSource implements CategoryDataSource{
    private static volatile CategoryLocalSource INSTANCE;

    private CategoryDao mCategoryDao;

    private AppExecutors mAppExecutors;

    // Prevent direct instantiation.
    private CategoryLocalSource(@NonNull AppExecutors appExecutors,
                                 @NonNull CategoryDao tasksDao) {
        mAppExecutors = appExecutors;
        mCategoryDao = tasksDao;
    }

    public static CategoryLocalSource getInstance(@NonNull AppExecutors appExecutors,
                                                   @NonNull CategoryDao tasksDao) {
        if (INSTANCE == null) {
            synchronized (CategoryLocalSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CategoryLocalSource(appExecutors, tasksDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void saveCategories(final Category category) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mCategoryDao.insert(category);
                //TODO how to check if it's inserted
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getCategories(@NonNull LoadCategoriesCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Category> categories = mCategoryDao.loadAll();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onCategoriessLoaded(categories);
                    }
                });
            }
        };

        mAppExecutors.diskIO().execute(runnable);
    }

    public void getCategory(@NonNull final String categoryId) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }
        };

        mAppExecutors.diskIO().execute(runnable);
    }

    public void refreshCategories() {

    }

    public void deleteAllCategories() {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {

            }
        };

        mAppExecutors.diskIO().execute(deleteRunnable);
    }

    public void deleteCategory(@NonNull final String categoryId) {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {

            }
        };

        mAppExecutors.diskIO().execute(deleteRunnable);
    }

    @VisibleForTesting
    static void clearInstance() {
        INSTANCE = null;
    }
}
