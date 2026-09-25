/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProcessQueue;

/**
 *
 * @author Ryan
 */
public class ProcessControlBlock {
    private final int processIdentification;
    private String status;
    private final String processName;
    
    private final int arrivalTime; // Non-preemptives
    private final int burstTime;
    private int startTime;
    private int waitingTime;
    
    private int completionTime; // Preeemptives
    private int turnAroundTime;
    
    public ProcessControlBlock(String name, int arrival, int burst){
        this.processIdentification = hashCode();
        this.processName = name;
        
        this.arrivalTime = arrival;
        this.burstTime = burst;
        this.startTime = 0;
        this.waitingTime = 0;
        
        this.completionTime = 0;
        this.turnAroundTime = 0;
    }
    
    public int getProcessIdentificatiion(){
        return this.processIdentification;
    }
    
    public String getStatus(){
        return this.status;
    }
    
    public void setStatus(String status){
        String[] states = {"Hold", "Ready", "Running", "Waiting", "Finished"};
        
        for(int i=0; i<states.length; i++){
            if(status.equals(states[i])){
                this.status = status;
                return;
            }
        }
        javax.swing.JOptionPane.showMessageDialog(null, "Error: Status is unrecognized!");
        System.exit(1);
    }
    
    public String getProcessName(){
        return this.processName;
    }
    
    public int getArrivalTime(){
        return this.arrivalTime;
    }
    
    public int getBurstTime(){
        return this.burstTime;
    }
    
    public int getStartTime(){
        return this.startTime;
    }
    
    public int getWaitingTime(){
        return this.waitingTime;
    }
    
    public void setStartTime(int startTime){
        if(startTime < 0){
            javax.swing.JOptionPane.showMessageDialog(null, "Error: Invalid start tome!");
            System.exit(1);
        }
        
        this.startTime = startTime;
        setWaitingTime();
    }
    
    private void setWaitingTime(){
        this.waitingTime = this.startTime - this.arrivalTime;
    }
    
    public int getCompletionTime(){
        return this.completionTime;
    }
    
    public void setCompletionTime(int lastStartTime, int residualBurstTime){
        this.completionTime = lastStartTime + residualBurstTime;
    }
    
    public int getTurnAroundTime(){
        return this.turnAroundTime;
    }
    
    public void setTurnAroundTime(){
        this.turnAroundTime = this.completionTime - this.arrivalTime;
    }
}

