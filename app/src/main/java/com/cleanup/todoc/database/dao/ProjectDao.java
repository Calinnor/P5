package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.cleanup.todoc.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {
    /**
     * create a project
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createProject(Project project);

    /**
     * read projects
     */
    @Query("SELECT * FROM Project")
    LiveData<List<Project>> getProjects();

    //this one use to modify a specific project, not use in this app
    //Query("SELECT * FROM Project WHERE id = :projectId")
    //LiveData<Project> getProject(long projectId);
}
