/**
 * Represents a Task object
 */
package edu.utsa.cs3443.taskeliminator.model;

public class Task {
    String name;
    String date;
    boolean complete;

    /**
     * Creates Task object
     * @param name Name of task
     * @param date Date of task
     * @param complete Task completion
     */
    public Task(String name, String date, boolean complete) {
        this.name = name;
        this.date = date;
        this.complete = complete;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Name of task
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date Date of task
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return boolean
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * @param complete Completion of task
     */
    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}

