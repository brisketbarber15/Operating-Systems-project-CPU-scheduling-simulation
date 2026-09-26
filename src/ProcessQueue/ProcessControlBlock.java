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
    
    private int tempArrivalTime; // For rr 
    private int tempBurstTime;
    
    private int completionTime; // Preeemptives
    private int turnAroundTime;
    
    public ProcessControlBlock(String name, int arrival, int burst){
        this.processIdentification = hashCode();
        this.processName = name;
        
        this.arrivalTime = arrival;
        this.burstTime = burst;
        this.startTime = 0;
        this.waitingTime = 0;
        
        this.tempArrivalTime = arrival; 
        this.tempBurstTime = burst;
        
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
    
    public void setUpdatedArrivalTime(int arrivalTime){ // For RR 
        this.tempArrivalTime = arrivalTime; 
    } 
    
    public int getUpdatedArrivalTime(){ 
        return this.tempArrivalTime; 
    }
    
    public int getBurstTime(){
        return this.burstTime;
    }
    
    public int setRemainingBurstTimeReturnDeduction(int timeQuantum){ // For RR 
        if(this.tempBurstTime > timeQuantum){ 
            this.tempBurstTime -= timeQuantum; 
            return timeQuantum; 
        }else{ 
            int temp = this.tempBurstTime; 
            this.tempBurstTime = 0; 
            return temp; 
        } 
    } 
    
    public int getRemainingBurstTime(){ 
        return this.tempBurstTime; 
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
        setWaitingTime("nonPreemptive");
    }
    
    private void setWaitingTime(String by){
        if(by.equals("nonPreemptive")){
            this.waitingTime = this.startTime - this.arrivalTime;
        }else if(by.equals("preemptive")){
            this.waitingTime = this.turnAroundTime - this.burstTime;
        }else{
            javax.swing.JOptionPane.showMessageDialog(null, "Error: Waiting time method uis unrecognized!");
            System.exit(1);
        }
    }
    
    public int getCompletionTime(){
        return this.completionTime;
    }
    
    public void setCompletionTime(int completionTime){
        this.completionTime = completionTime;
        this.setTurnAroundTime();
    }
    
    public int getTurnAroundTime(){
        return this.turnAroundTime;
    }
    
    public void setTurnAroundTime(){
        this.turnAroundTime = this.completionTime - this.arrivalTime;
        this.setWaitingTime("preemptive");
    }
}

