package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    /**
     * create a task
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Task task);

    /**
     * read tasks
     */
    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getTasks();

    /**
     * delete task
     * @param taskId
     */
    @Query("DELETE FROM Task WHERE id = :taskId")
    void deleteTask(long taskId);
}
