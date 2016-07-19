package com.example.richie.currently;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Richie on 7/14/16.
 */
public class Person {
    private String name;
    private String position;
    private String availability;
    private List<Task> tasks;
    private List<Task> completedTasks;


    public Person(String name){
        this.name = name;
        tasks = new ArrayList<>();
        completedTasks = new ArrayList<>();
    }

    public void createTask(String task, int urgency, String manager){
        Task newTask = new Task(task, urgency, manager);
        tasks.add(newTask);
    }

    public void finishTask(int pos){
        completedTasks.add(tasks.get(pos));
        tasks.remove(pos);
    }
    public void editTask(int pos,String task, int duration, String manager){
        Task selectedTask = tasks.get(pos);
        selectedTask.editTask(task,duration,manager);
    }
    public String getName(){
        return name;
    }

    public String getPosition(){
        return position;
    }

    public String getAvailability(){return availability;}




    private class Task{
        String task;
        int duration;
        String manager;
        List<String> history;

        public Task(String task, int duration, String manager){
            this.task = task;
            this.duration = duration;
            this.manager= manager;
            this.history = new ArrayList<>();
        }
        public void editTask(String task, int duration, String manager){
            Date changedDate = new Date();
            convertDuration(duration);
            String prevTask = changedDate + ": ";
            String newTask= " Changed to: ";
            if(!this.task.equals(task)){
                prevTask += this.task+" ";
                newTask += task+" ";
                this.task = task;
            }
            if(!(this.duration==duration)){
                prevTask += convertDuration(this.duration)+" ";
                newTask += convertDuration(duration)+ " ";
                this.duration = duration;
            }
            if(!this.manager.equals(manager)){
                prevTask += this.manager;
                newTask += manager;
                this.manager = manager;
            }
            history.add(prevTask+newTask);
        }

        private String convertDuration(int duration){
            String convertedDuration;
            switch(duration){
                case 0: convertedDuration = "10 Minutes";
                        break;
                case 1: convertedDuration = "30 Minutes";
                        break;
                case 2: convertedDuration = "1 Hour";
                        break;
                case 3: convertedDuration = "2 Hour";
                        break;
                case 4: convertedDuration = "Half Day";
                        break;
                case 5: convertedDuration = "All Day";
                        break;
                default: convertedDuration = "30 Minutes";
                        break;
            }
            return convertedDuration;
        }
    }


}

