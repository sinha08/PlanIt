package planit.sinha.ankur.com.planit.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.List;

import planit.sinha.ankur.com.planit.data.CategoryDataSource;
import planit.sinha.ankur.com.planit.data.DataSourceProvider;
import planit.sinha.ankur.com.planit.data.model.db.Category;

/**
 * Created by ankur sinha on 25-12-2018.
 */

public class HomeViewModel extends AndroidViewModel {

    private final LiveData<Category> mObservableCategory;
    private final MutableLiveData<List<Category>> mObservableList;
    private CategoryDataSource mRepository;

    public HomeViewModel(@NonNull Application application, CategoryDataSource repository) {
        super(application);
        mRepository = repository;
        mObservableList = new MutableLiveData<>();
        mObservableCategory = repository.getCategoryById(1);
    }

    public void saveCategory(String name) {
        mRepository.saveCategories(new Category(name));
    }

    public void deleteCategory(Category category) {
        mRepository.deleteCategory(category);
    }

    public void getListOfCategories() {
        mRepository.getCategories(new CategoryDataSource.LoadCategoriesCallback() {
            @Override
            public void onCategoriessLoaded(List<Category> categories) {
                mObservableList.setValue(categories);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
    /**
     * Expose the LiveData Comments query so the UI can observe it.
     */
    public MutableLiveData<List<Category>> getObservableList () {
        return mObservableList;
    }

    public LiveData<Category> getObservableCategory() {
        return mObservableCategory;
    }
    

    /**
     * A creator is used to inject the product ID into the ViewModel
     * <p>
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the product ID can be passed in a public method.
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final CategoryDataSource mRepository;

        public Factory(@NonNull Application application) {
            mApplication = application;
            mRepository = DataSourceProvider.getCategoryDataSource(application.getApplicationContext());
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new HomeViewModel(mApplication, mRepository);
        }
    }
}