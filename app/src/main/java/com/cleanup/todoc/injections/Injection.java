package com.cleanup.todoc.injections;

import android.content.Context;

import com.cleanup.todoc.database.database.TodocDatabase;
import com.cleanup.todoc.repository.ProjectDataRepository;
import com.cleanup.todoc.repository.TaskDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    public static ProjectDataRepository provideProjectDataSource(Context contextProject)
    {
        TodocDatabase database = TodocDatabase.getInstance(contextProject);
        return new ProjectDataRepository(database.projectDao());
    }

    public static TaskDataRepository provideTaskDataSource(Context contextTask) {
       TodocDatabase database = TodocDatabase.getInstance(contextTask);
       return new TaskDataRepository(database.taskDao());
    }

    public static Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    public static ViewModelFactory provideViewModelFactory (Context contextViewModelFactory) {
        ProjectDataRepository projectDataRepository = provideProjectDataSource(contextViewModelFactory);
        TaskDataRepository taskDataRepository = provideTaskDataSource(contextViewModelFactory);
        Executor executor = provideExecutor();
        return new ViewModelFactory(projectDataRepository, taskDataRepository, executor);

    }
}
