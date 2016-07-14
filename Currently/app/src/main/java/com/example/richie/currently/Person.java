package com.example.richie.currently;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richie on 7/14/16.
 */
public class Person {
    private String name;
    private List<Task> tasks;


    public Person(String name){
        this.name = name;
        tasks = new ArrayList<>();
    }

    public void createTask(String task, int urgency, String manager){
        Task newTask = new Task(task, urgency, manager);
        tasks.add(newTask);
    }






    private class Task{
        String task;
        boolean finished;
        int urgency;
        String manager;
        List<String> history;

        public Task(String task, int urgency, String manager){
            this.task = task;
            this.urgency = urgency;
            this.manager= manager;
            this.history = new ArrayList<>();
        }
    }


}

