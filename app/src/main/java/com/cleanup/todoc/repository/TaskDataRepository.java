package com.cleanup.todoc.repository;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {
    //declare TaskDao
    //create constructor
    //implement TaskDao methods

    private final TaskDao taskDao;

    public TaskDataRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

   /**
    * create a task from TaskDao
    */
   public void insertTask(Task task) {
       this.taskDao.insertTask(task);
   }

    /**
     * read a task from TaskDao
     */
    public LiveData <List<Task>> getTasks(){
        return this.taskDao.getTasks();
    }

    /**
     * delete task
     * @param task
     */
    public void deleteTask(Task task) {
        this.taskDao.deleteTask(task.getId());
    }



}
