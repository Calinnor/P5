package com.cleanup.todoc.ui.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.ProjectDataRepository;
import com.cleanup.todoc.repository.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    private final ProjectDataRepository projectDataRepository;
    private final TaskDataRepository taskDataRepository;
    private final Executor executor;

    public TaskViewModel(ProjectDataRepository projectDataRepository, TaskDataRepository taskDataRepository, Executor executor) {
        this.projectDataRepository = projectDataRepository;
        this.taskDataRepository = taskDataRepository;
        this.executor = executor;
    }

    //-----Project-----

    /**
     * create a project method from ProjectDao
     *
     * @param project
     */
    public void createProject(Project project) {
        executor.execute(() -> {
            this.projectDataRepository.createProject(project);
        });
    }

    /**
     * read projects method from ProjectDao
     * dont forget to put Livedata public
     *
     * @return
     */
    public LiveData<List<Project>> getProjects() {
        return this.projectDataRepository.getProjects();
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


