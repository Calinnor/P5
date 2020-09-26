package com.cleanup.todoc.repository;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.database.TodocDatabase;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectDataRepository {
    //declare Dao
    //constructor
    //methods

    private final ProjectDao projectDao;

    public ProjectDataRepository(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    /**
     * create a project method from ProjectDao
     * @param project
     */
    public void createProject (Project project)
    {
        this.projectDao.createProject(project);
    }

    /**
     * read projects method from ProjectDao
     * @return
     */
    public LiveData<List<Project>> getProjects(){
        return this.projectDao.getProjects();
    }
}
