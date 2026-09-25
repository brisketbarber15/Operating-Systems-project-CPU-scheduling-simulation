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
public class FirstComeFirstServed extends Scheduler{
    @Override
    public void schedule(HoldQueue hold){
        int numProcess = hold.getSize();
        
        ReadyQueue ready = setReadyQueue(hold, numProcess); // Transfer propceses from hold queue to ready queue
        RunningQueue running = new RunningQueue();
        
        for(int i=0; i<numProcess; i++){ // Process proceses one by one
            ProcessControlBlock process = ready.dequeue(); 
            
            run(running, process); // Transfer the process from ready queue to running queue and run
            finished(running.dequeue()); // Trasfer the process from running queue to finished queue (Gantt Chart)
        }
        this.setAverageWaitingTime();
    }
    
    private ReadyQueue setReadyQueue(HoldQueue hold, int numProcess){
        ReadyQueue ready = new ReadyQueue();
        hold.sortBy("arrivalTime");
        
        for(int i=0; i<numProcess; i++){
            ready.enqueue(hold.dequeue());
        }
        
        return ready;
    }
    
    private void run(RunningQueue running, ProcessControlBlock process){
        running.enqueue(process);
        
        if(process.getArrivalTime() > this.startTime){ // If there is no immediate process after last process then the scheduler waits
            this.startTime += (process.getArrivalTime() - this.startTime);
        }
        
        process.setStartTime(this.startTime); // Update the accounts 
        this.startTime += process.getBurstTime();
        this.totalWaitingTime += process.getWaitingTime();
    }
    
    private void finished(ProcessControlBlock process){
        this.ganttChart.enqueue(process);
    }
}
