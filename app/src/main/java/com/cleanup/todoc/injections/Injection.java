package com.cleanup.todoc.injections;

import android.content.Context;

import com.cleanup.todoc.database.database.TodocDatabase;
import com.cleanup.todoc.repository.TaskDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    public static TaskDataRepository provideTaskDataSource(Context context) {
       TodocDatabase database = TodocDatabase.getInstance(context);
       return new TaskDataRepository(database.taskDao());
    }

    public static Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    public static ViewModelFactory provideViewModelFactory (Context contextViewModelFactory) {
        TaskDataRepository taskDataRepository = provideTaskDataSource(contextViewModelFactory);
        Executor executor = provideExecutor();
        return new ViewModelFactory(/*projectDataRepository,*/ taskDataRepository, executor);

    }
}
