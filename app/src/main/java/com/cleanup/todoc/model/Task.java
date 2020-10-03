package com.cleanup.todoc.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import java.util.Comparator;

/**
 * <p>Model for the tasks of the application.</p>
 *
 * @author Gaëtan HERFRAY
 */
//@Entity(foreignKeys = @ForeignKey (
//        entity = Project.class,
//        parentColumns = "id",
//        childColumns = "projectId"))
    @Entity
public class Task {
    /**
     * The unique identifier of the task
     */
    @PrimaryKey (autoGenerate = true)
    private long id;

    /**
     * The unique identifier of the project associated to the task
     */
    @ColumnInfo (name = "projectId",
                 index = true)
    private long projectId;

    /**
     * The name of the task
     */
    // Suppress warning because setName is called in constructor beware suppress annotations make crash
    //@SuppressWarnings("NullableProblems")
    @NonNull
    private String name;

    /**
     * The timestamp when the task has been created
     */
    private long creationTimestamp;

    /**
     * Instantiates a new Task.
     *
     * @param id                the unique identifier of the task to set
     * @param projectId         the unique identifier of the project associated to the task to set
     * @param name              the name of the task to set
     * @param creationTimestamp the timestamp when the task has been created to set
     */

    //Constructor used for tests. Need a constant id
//    @Ignore
//    @VisibleForTesting
//    public Task(long id, long projectId, @NonNull String name, long creationTimestamp) {
//        this.setId(id);
//        this.setProjectId(projectId);
//        this.setName(name);
//        this.setCreationTimestamp(creationTimestamp);
//    }

    @Ignore
    @VisibleForTesting
    public Task(long id, long projectId, @NonNull String name, long creationTimestamp) {
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.creationTimestamp = creationTimestamp;
    }


    //Constructor used in app because id is autogenerate (MainActivity). Needed to modify in it
    //TODO determine what constructor is the best

    public Task(long projectId, @NonNull String name, long creationTimestamp) {
        this.projectId = projectId;
        this.name = name;
        this.creationTimestamp = creationTimestamp;
    }

//    public Task(long projectId, @NonNull String name, long creationTimestamp) {
//        this.setProjectId(projectId);
//        this.setName(name);
//        this.setCreationTimestamp(creationTimestamp);
//    }


    //----GETTERS---
    /**
     * @return the unique identifier of the task
     */
    public long getId() {
        return id;
    }

    /**
     * @return the project associated to the task
     */
    @Nullable
    public Project getProject() {
        return Project.getProjectById(projectId);
    }

    /**
     * @return the name of the task
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * @return the identifier of the project's task
     */
    public long getProjectId() {
        return projectId;
    }

    /**
     * @return the timestamp when the task has been created.
     */
    public long getCreationTimestamp() {
        return creationTimestamp;
    }



    //----SETTERS----
    /**
     * Sets the unique identifier of the task.
     *
     * @param id the unique idenifier of the task to set
     */
    public void setId(long id) {
        this.id = id;
    }

//    /**
//     * Sets the unique identifier of the project associated to the task.
//     *
//     * @param projectId the unique identifier of the project associated to the task to set
//     */
//    private void setProjectId(long projectId) {
//        this.projectId = projectId;
//    }
//
//    /**
//     * Sets the name of the task.
//     *
//     * @param name the name of the task to set
//     */
//    private void setName(@NonNull String name) {
//        this.name = name;
//    }
//
//    /**
//     * Sets the timestamp when the task has been created.
//     *
//     * @param creationTimestamp the timestamp when the task has been created to set
//     */
//    private void setCreationTimestamp(long creationTimestamp) {
//        this.creationTimestamp = creationTimestamp;
//    }

    //-----COMPARATORS-----
    /**
     * Comparator to sort task from A to Z
     */
    //task comparator compare two values
    public static class TaskAZComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            //write comparison logic below. In this case the comparison is the mock datas of the initial app. Here the method compare name
            //left for A, right for Z ?
            //may implement data from database to display here i think
            //return left.dataName.compareTo(right.dataName) or something like this instead of actuals values
            return left.name.compareTo(right.name);
            //return left.getName().compareTo(right.getName());
        }
    }

    /**
     * Comparator to sort task from Z to A
     */
    public static class TaskZAComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            //write comparison logic below. In this case the comparison is the mock datas of the initial app. Here the method compare name
            //right for Z, left for A ?
            //return right.name.compareTo(left.name);
            return right.getName().compareTo(left.getName());
        }
    }

    /**
     * Comparator to sort task from last created to first created
     */
    public static class TaskRecentComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
           // return (int) (right.creationTimestamp - left.creationTimestamp);
            return (int) (right.getCreationTimestamp() - left.getCreationTimestamp());
        }
    }

    /**
     * Comparator to sort task from first created to last created
     */
    public static class TaskOldComparator implements Comparator<Task> {
        @Override
        public int compare(Task left, Task right) {
            //return (int) (left.creationTimestamp - right.creationTimestamp);
            return (int) (left.getCreationTimestamp() - right.getCreationTimestamp());
        }
    }
}
