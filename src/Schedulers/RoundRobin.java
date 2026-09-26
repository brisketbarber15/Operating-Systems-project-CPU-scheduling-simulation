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
public class RoundRobin extends Scheduler{
    public RoundRobin(int timeQuantum){
        super(timeQuantum);
    }
    
    @Override
    public void schedule(HoldQueue hold){
        ReadyQueue ready = new ReadyQueue();
        RunningQueue running = new RunningQueue();
        
        while(!(ready.isEmpty() && hold.isEmpty() && running.isEmpty())){
            setReadyQueue(hold, ready);
            runReadyProcess(ready, running);
            admitArrivedProcesses(hold, ready);
            isFinished(running, ready);
        }
        constructOutput();
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
            setReadyQueue(hold, ready); // Recursion with guaranteed ready process
        }
    }
    
    private void runReadyProcess(ReadyQueue ready, RunningQueue running){
        ProcessControlBlock process = ready.dequeue();
        running.enqueue(process);        
           
        int completionTime = this.currentTime + process.setRemainingBurstTimeReturnDeduction(this.timeQuantum); // Run process with time quantum constraint
        
        this.ganttChart[this.ganttChartIndex++] = new GanttChart( // Updates gantt chart 
                process.getProcessName(),
                this.currentTime,
                completionTime
        );
        
        this.currentTime = completionTime;
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
    
    private void isFinished(RunningQueue running, ReadyQueue ready){
        ProcessControlBlock process = running.dequeue();
        
        if(process.getRemainingBurstTime() == 0){
            process.setCompletionTime(this.currentTime);
            this.finishedProcesses.enqueue(process);
        }else{
            ready.enqueue(process); // Send the unfinished process back to the end of the queue
        }
    }
    
    private void constructOutput(){ // Because calculations need completion time, calculation of attributes happens after all process are finnished running
        this.finishedProcesses.sortBy("arrivalTime");
        for(int i=0; i<finishedProcesses.getSize(); i++){
            ProcessControlBlock process = finishedProcesses.dequeue();
            this.totalTurnAroundTime += process.getTurnAroundTime();
            this.totalWaitingTime += process.getWaitingTime();
            this.finishedProcesses.enqueue(process);
        }
        
        this.setAverageWaitingTime();
        this.setAverageTurnAroundTime();
    }
}
