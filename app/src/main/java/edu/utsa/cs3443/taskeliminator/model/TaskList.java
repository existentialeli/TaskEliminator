/**
 * Represents a TaskList object
 */
package edu.utsa.cs3443.taskeliminator.model;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Adds a new task to the ArrayList
     * @param name Name of task
     * @param date Date of task
     * @param complete Task completion
     */
    public static void addTask(String name, String date, boolean complete) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        Task newTask = new Task(name, date, complete);
        tasks.add(newTask);
    }

    /**
     * Marks a task as complete
     * @param name Name of task
     */
    public static void taskComplete(String name) {
        for (Task t : tasks) {
            if (t.getName().equals(name)) {
                t.setComplete(true);
                break;
            }
        }
    }

    /**
     * Creates TaskList object
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * @return ArrayList
     */
    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * @param tasks ArrayList of tasks
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}

