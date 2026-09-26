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
public class GanttChart {
    private String processName;
    private int starTime;
    private int completionTime;
    
    public GanttChart(String processName, int startTime, int completionTime){
        this.processName = processName;
        this.starTime = startTime;
        this.completionTime = completionTime;
    }
    
    public String getProcessName(){
        return this.processName;
    }
    
    public int getStartTime(){
        return this.starTime;
    }
    
    public int getCompletionTime(){
        return this.completionTime;
    }
}
