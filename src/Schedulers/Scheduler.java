/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Schedulers;

/**
 *
 * @author Ryan
 */
import ProcessQueue.*;
public abstract class Scheduler {
    protected FinishedQueue finishedProcesses;
//    protected FinishedQueue ganttChart; // xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    protected GanttChart[] ganttChart = new GanttChart[100];
    protected int ganttChartIndex;
    
    protected double averageWaitingTime;
    protected double averageTurnAroundTime;
    
    protected int currentTime; // has no getters and setters yet
    
    protected int totalWaitingTime;
    protected int totalTurnAroundTime;
    
    protected int timeQuantum;
    
    public Scheduler(int timeQuantum){
        this.finishedProcesses = new FinishedQueue();
//        this.ganttChart = new FinishedQueue();
        this.ganttChartIndex = 0;
        
        this.averageTurnAroundTime = 0.0;
        this.averageWaitingTime = 0.0;
        
        this.currentTime = 0;
        
        this.totalWaitingTime = 0;
        this.totalTurnAroundTime = 0;
        
        this.timeQuantum = timeQuantum;
    }
    
    public abstract void schedule(HoldQueue hold);
    
    public FinishedQueue getFinishedProcesses(){
        return this.finishedProcesses;
    }
    
    public GanttChart[] getGanttChart(){
        return this.ganttChart;
    }
    
    public double getAverageWaitingTime(){
        return this.averageWaitingTime;
    }
    
    protected void setAverageWaitingTime(){
        int numProcess = this.finishedProcesses.getSize();
        
        if(numProcess == 0){
            this.averageWaitingTime = 0;
        }
        this.averageWaitingTime = (double) totalWaitingTime / numProcess;
    }
    
    public double getAverageTurnAroundTime(){
        return this.averageTurnAroundTime;
    }
    
    public void setAverageTurnAroundTime(){
        int numProcess = this.finishedProcesses.getSize();
        
        if(numProcess == 0){
            this.averageTurnAroundTime = 0;
        }
        this.averageTurnAroundTime = (double) totalTurnAroundTime / numProcess;
    }
}

