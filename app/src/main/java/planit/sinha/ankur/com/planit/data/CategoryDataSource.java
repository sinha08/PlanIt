package planit.sinha.ankur.com.planit.data;

import android.support.annotation.NonNull;

import java.util.List;

import planit.sinha.ankur.com.planit.data.model.db.Category;

/**
 * Created by ankur sinha on 23-12-2018.
 */

public interface CategoryDataSource {
    interface LoadCategoriesCallback {

        void onCategoriessLoaded(List<Category> categories);

        void onDataNotAvailable();
    }
    //TODO define common methods for remote and local data source

    void getCategories(LoadCategoriesCallback callback);

    void saveCategories(Category category);
}
