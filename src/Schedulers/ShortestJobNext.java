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
public class ShortestJobNext extends Scheduler{
    public ShortestJobNext(){
        super(0);
    }
    
    @Override
    public void schedule(HoldQueue hold){
        int numProcess = hold.getSize();
        
        ReadyQueue ready = new ReadyQueue();
        RunningQueue running = new RunningQueue();
        
        for(int i=0; i<numProcess; i++){ // Process proceses one by one
            setReadyQueue(hold, ready); // Updates ready queue with shortest burst time order and within the time elapsed (currentTime)
            run(running, ready); // Transfer the process from ready queue to running queue and run
            finished(running); // Trasfer the process from running queue to finished queue (Gantt Chart)
        }
        this.setAverageWaitingTime();
    }
    
    private void setReadyQueue(HoldQueue hold, ReadyQueue ready){
        admitArrivedProcesses(hold, ready);
        
        if(ready.isEmpty()){ // If processes hasnt arrived et
            hold.sortBy("arrivalTime");
            
            this.ganttChart[this.ganttChartIndex++] = new GanttChart( // Updates gantt chart for cpu idle time
                    "Idle",
                    this.currentTime,
                    hold.peek().getArrivalTime()
            );
            
            this.currentTime = hold.peek().getArrivalTime(); // CPU is idle: jump to the next process arrival time
            setReadyQueue(hold, ready); // Recursion with guaranteed shortest process
        }
        
        ready.sortBy("burstTime");
    }
    
    private void admitArrivedProcesses(HoldQueue hold, ReadyQueue ready){
        int numProcess = hold.getSize();
        
        hold.sortBy("arrivalTime");
        for(int i=0; i<numProcess; i++){
            ProcessControlBlock process = hold.dequeue();
            if(process.getArrivalTime() <= this.currentTime){
                ready.enqueue(process);
            }else{
                hold.enqueue(process);
                break;
            }
        }
    }
    
    private void run(RunningQueue running, ReadyQueue ready){
        ProcessControlBlock process = ready.dequeue();
                
        running.enqueue(process);
        
        this.ganttChart[this.ganttChartIndex++] = new GanttChart( // Updates gantt chart 
                process.getProcessName(),
                this.currentTime,
                this.currentTime + process.getBurstTime()
        );
        
        process.setStartTime(this.currentTime); // Update the accounts 
        this.currentTime += process.getBurstTime();
        this.totalWaitingTime += process.getWaitingTime();
    }
    
    private void finished(RunningQueue running){
        this.finishedProcesses.enqueue(running.dequeue());
    }
}
