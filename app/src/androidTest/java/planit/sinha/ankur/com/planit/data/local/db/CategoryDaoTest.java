package planit.sinha.ankur.com.planit.data.local.db;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import planit.sinha.ankur.com.planit.data.AppDatabase;
import planit.sinha.ankur.com.planit.data.model.db.Category;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by ankur sinha on 23-12-2018.
 */
@RunWith(AndroidJUnit4.class)
public class CategoryDaoTest {


        private static final Category CATEGORY = new Category(1234, "ToDos");

        private AppDatabase mDatabase;

        @Before
        public void initDb() {
            // using an in-memory database because the information stored here disappears when the
            // process is killed
            mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                    AppDatabase.class).build();
        }

        @After
        public void closeDb() {
            mDatabase.close();
        }

        @Test
        public void insertTaskAndGetById() {
            // When inserting a category
            mDatabase.categoryDao().insert(CATEGORY);

            // When getting the category by id from the database
            Category loaded = mDatabase.categoryDao().getCategoryById(CATEGORY.getId());

            // The loaded data contains the expected values
            assertCategory(loaded, 1234, "ToDos");
        }

        @Test
        public void insertTaskReplacesOnConflict() {
            //Given that a task is inserted
            mDatabase.categoryDao().insert(CATEGORY);

            // When a task with the same id is inserted
            Category newTask = new Category(1234, "Ideas");
            mDatabase.categoryDao().insert(newTask);
            // When getting the task by id from the database
            Category loaded = mDatabase.categoryDao().getCategoryById(CATEGORY.getId());

            // The loaded data contains the expected values
            assertCategory(loaded, 1234, "Ideas");
        }



        private void assertCategory(Category category, int id, String title) {
            assertThat(category, notNullValue());
            assertThat(category.getId(), is(id));
        }
}
