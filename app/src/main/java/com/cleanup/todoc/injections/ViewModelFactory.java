package com.cleanup.todoc.injections;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.cleanup.todoc.repository.ProjectDataRepository;
import com.cleanup.todoc.repository.TaskDataRepository;
import com.cleanup.todoc.ui.viewModel.TaskViewModel;

import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory{

    //declare Repositories
    //Declare Executor
    private final ProjectDataRepository projectDataRepository;
    private final TaskDataRepository taskDataRepository;
    private  final Executor executor;

    //Create constructor
    public ViewModelFactory(ProjectDataRepository projectDataRepository, TaskDataRepository taskDataRepository, Executor executor) {
        this.projectDataRepository = projectDataRepository;
        this.taskDataRepository = taskDataRepository;
        this.executor = executor;
    }

    //implement Factory method
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(TaskViewModel.class))
        {
            return (T) new TaskViewModel
                    (this.projectDataRepository, this.taskDataRepository, this.executor);
        }
        throw new IllegalArgumentException("Unkown ViewModel class");
    }
}
