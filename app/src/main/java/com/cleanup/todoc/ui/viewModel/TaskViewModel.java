package com.cleanup.todoc.ui.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    private final TaskDataRepository taskDataRepository;
    private final Executor executor;

    public TaskViewModel(TaskDataRepository taskDataRepository, Executor executor) {
        this.taskDataRepository = taskDataRepository;
        this.executor = executor;
    }
    
    //-----Task-----

    /**
     * create a task from TaskDao
     */
    public void insertTask(Task task) {
        executor.execute(() -> this.taskDataRepository.insertTask(task));
    }

    /**
     * read a task from TaskDao
     */
    public LiveData<List<Task>> getTasks() {
        return this.taskDataRepository.getTasks();
    }

        /**
         * delete task
         * @param task from TaskDao
         */
    public void deleteTask(Task task) {
        executor.execute(() ->
                this.taskDataRepository.deleteTask(task));
    }
}


