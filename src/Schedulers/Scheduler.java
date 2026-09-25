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
    protected FinishedQueue ganttChart;
    
    protected double averageWaitingTime;
    protected double averageTurnAroundTime;
    
    protected int startTime;
    
    protected int totalWaitingTime;
    protected int totalTurnAroundTime;
    
    public Scheduler(){
        this.ganttChart = new FinishedQueue();
        
        this.averageTurnAroundTime = 0.0;
        this.averageWaitingTime = 0.0;
        
        this.startTime = 0;
        
        this.totalWaitingTime = 0;
        this.totalTurnAroundTime = 0;
    }
    
    public abstract void schedule(HoldQueue hold);
    
    public FinishedQueue getGanttChart(){
        return this.ganttChart;
    }
    
    public double getAverageWaitingTime(){
        return this.averageWaitingTime;
    }
    
    protected void setAverageWaitingTime(){
        int numProcess = this.ganttChart.getSize();
        
        if(numProcess == 0){
            this.averageWaitingTime = 0;
        }
        this.averageWaitingTime = (double) totalWaitingTime / numProcess;
    }
    
    public double getAverageTurnAroundTime(){
        return this.averageTurnAroundTime;
    }
    
    public void setAverageTurnAroundTime(){
        int numProcess = this.ganttChart.getSize();
        
        if(numProcess == 0){
            this.averageTurnAroundTime = 0;
        }
        this.averageTurnAroundTime = (double) totalTurnAroundTime / numProcess;
    }
}

