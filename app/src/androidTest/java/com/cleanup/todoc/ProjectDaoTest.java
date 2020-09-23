package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.database.TodocDatabase;
import com.cleanup.todoc.model.Project;

import org.junit.After;
import org.junit.Before;
import org.junit.ComparisonFailure;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class ProjectDaoTest {

    //for data
    private TodocDatabase database;

    /**
     * each test is launched in sync task
     */
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    /**
     *for create a database instance in memory
     */
    @Before
    public void initDb() {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                TodocDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() {
        database.close();
    }

    //data to use in tests for Project table
    /**
     * create project test
     */

    private static long PROJECT_ID_1 = 1;
    private static Project DEMO_1_PROJECT = new Project(PROJECT_ID_1, "Projet Tartampion", 0xFFEADAD1);
    private static Project FALSE_DEMO_PROJECT_WITH_ID_1 = new Project(PROJECT_ID_1, "Projet Lucidia", 0xFFB4CDBA);

    /**
     * At first time search with :
     * @Query("SELECT * FROM Project")
     *     LiveData<Project> getProjects();
     */
//    @Test
//    public void insertProjectWithSuccess() {
//        this.database.projectDao().createProject(DEMO_1_PROJECT);
//        assertNotNull(this.database.projectDao());
//    }
//
//    @Test
//    public void getProjectWithSuccess() throws InterruptedException {
//        this.database.projectDao().createProject(DEMO_1_PROJECT);
//        Project project = LiveDataTestUtils.getValue(this.database.projectDao().getProjects());
//        assertEquals(project.getId(), PROJECT_ID_1);
//        assertEquals(project.getName(), DEMO_1_PROJECT.getName());
//        assertEquals(project.getColor(), DEMO_1_PROJECT.getColor());
//    }

    /**
     * In a second time search with
     * @Query("SELECT * FROM Project")
     *     LiveData<List<Project>> getProjects();
     */
    @Test
    public void insertOneProjectAndGetProjects () throws InterruptedException
    {
        List<Project> projects = LiveDataTestUtils.getValue(this.database.projectDao().getProjects());
        this.database.projectDao().createProject(DEMO_1_PROJECT);
        assertEquals(projects.size(), 0);
        projects = LiveDataTestUtils.getValue(this.database.projectDao().getProjects());
        assertEquals(projects.size(), 1);

    }


}
