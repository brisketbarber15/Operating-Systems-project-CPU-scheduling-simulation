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
    @Override
    public void schedule(HoldQueue hold){
        int numProcess = hold.getSize();
        
        RunningQueue running = new RunningQueue();
        
        for(int i=0; i<numProcess; i++){ // Process proceses one by one
            ProcessControlBlock process = setReadyLeastBurst(hold); // Returns process that has the shortest burst time and within the time elapsed (startTime)
            
            run(running, process); // Transfer the process from ready queue to running queue and run
            finished(running.dequeue()); // Trasfer the process from running queue to finished queue (Gantt Chart)
        }
        this.setAverageWaitingTime();
    }
    
    private ProcessControlBlock setReadyLeastBurst(HoldQueue hold){
        int numProcess = hold.getSize();
        ReadyQueue ready = new ReadyQueue();
        
        hold.sortBy("arrivalTime");
        for(int i=0; i<numProcess; i++){
            ProcessControlBlock process = hold.dequeue();
            if(process.getArrivalTime() <= this.startTime){ // Transfering hold-status processes to ready queue if they've already arrived
                ready.enqueue(process);
            }else{
                hold.enqueue(process); // Restore processes that hasnt arrived yet back to hold queue
                break;
            }
        }
        
        hold.sortBy("arrivalTime");

        ProcessControlBlock shortest;
        if(ready.isEmpty()){ // If processes hasnt arrived et
            this.startTime = hold.peek().getArrivalTime(); // CPU is idle: jump to the next process arrival time
            shortest = setReadyLeastBurst(hold); // Recursion with guaranteed shortest process
        }else{
            ready.sortBy("burstTime");
            shortest = ready.dequeue(); // Process among valid proceses that has lowest burst time
        }

        while(!ready.isEmpty()){
            hold.enqueue(ready.dequeue()); // Restore valid but not the sshortest processes back to the hold queue
        }
        
        return shortest;
    }
    
    private void run(RunningQueue running, ProcessControlBlock process){
        running.enqueue(process);
        
        process.setStartTime(this.startTime); // Update the accounts 
        this.startTime += process.getBurstTime();
        this.totalWaitingTime += process.getWaitingTime();
    }
    
    private void finished(ProcessControlBlock process){
        this.ganttChart.enqueue(process);
    }
}
