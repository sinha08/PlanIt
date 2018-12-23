package planit.sinha.ankur.com.planit.data;

import android.content.Context;

import planit.sinha.ankur.com.planit.AppExecutors;
import planit.sinha.ankur.com.planit.data.local.db.CategoryLocalSource;

/**
 * Created by ankur sinha on 15-12-2018.
 */

public class DataSourceProvider {

    public static CategoryDataSource getCategoryDataSource(Context context) {
        AppDatabase dataBase = AppDatabase.getInstance(context);
        return CategoryRepository.getInstance(CategoryLocalSource.getInstance(new AppExecutors(),dataBase.categoryDao()));
    }
}
