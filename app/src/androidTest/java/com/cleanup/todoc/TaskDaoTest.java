package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.database.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest
{
    private TodocDatabase database;

    // DATA FOR TESTS
    private static Project PROJECT_DEMO = new Project(1, "Tartampion", 0xFFEADAD1);
    private static long PROJECT_DEMO_ID = PROJECT_DEMO.getId();
    private static Task TASK_DEMO_ONE = new Task(1, PROJECT_DEMO_ID, "test1", 10);
    private static Task TASK_DEMO_TWO = new Task(2, PROJECT_DEMO_ID, "test2", 100);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception
    {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), TodocDatabase.class)
                .allowMainThreadQueries().build();
    }

    @After
    public void closeDb() throws Exception
    {
        this.database.close();
    }

    @Test
    public void insertOneProjectAndGetProjects () throws InterruptedException
    {
        List<Project> projects = LiveDataTestUtils.getValue(this.database.projectDao().getProjects());
        this.database.projectDao().createProject(PROJECT_DEMO);
        assertEquals(projects.size(), 0);
        projects = LiveDataTestUtils.getValue(this.database.projectDao().getProjects());
        assertEquals(projects.size(), 1);

    }

    @Test
    public void getTasksWhenNoTaskInserted() throws InterruptedException
    {
        List<Task> tasks = LiveDataTestUtils.getValue(this.database.taskDao().getTasks());
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void insertOneTaskAndGetTasks() throws InterruptedException
    {
        List<Task> tasks = LiveDataTestUtils.getValue(this.database.taskDao().getTasks());
        assertEquals(tasks.size(), 0);

        this.database.projectDao().createProject(PROJECT_DEMO);
        this.database.taskDao().insertTask(TASK_DEMO_ONE);

        tasks = LiveDataTestUtils.getValue(this.database.taskDao().getTasks());
        assertEquals(tasks.size(), 1);
    }

    @Test
    public void insertTasksAndDeleteTheSecond() throws InterruptedException
    {
        this.database.projectDao().createProject(PROJECT_DEMO);
        this.database.taskDao().insertTask(TASK_DEMO_ONE);
        this.database.taskDao().insertTask(TASK_DEMO_TWO);
        List<Task> tasks = LiveDataTestUtils.getValue(this.database.taskDao().getTasks());
        assertEquals(tasks.size(), 2);
        this.database.taskDao().deleteTask(TASK_DEMO_TWO);
        tasks = LiveDataTestUtils.getValue(this.database.taskDao().getTasks());
        assertEquals(tasks.size(), 1);
    }
}
