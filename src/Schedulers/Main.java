/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Schedulers;

/**
 *
 * @author Ryan
 */
import ProcessQueue.*;
public class Main { 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        // TODO code application logic here
        int[] inputArrivalTime = {0, 5, 16, 2, 8};
        int[] inputBurstTime = {3, 4, 8, 3, 2};
        
        HoldQueue hold = new HoldQueue();
        
        for(int i=0; i<inputArrivalTime.length; i++){
            ProcessControlBlock process = new ProcessControlBlock(
                    "p"+(i+1), 
                    inputArrivalTime[i], 
                    inputBurstTime[i]
            );
            hold.enqueue(process);
        }
        
        FirstComeFirstServed fcfs = new FirstComeFirstServed();
        fcfs.schedule(hold);
        displayFCFS(fcfs);
        
        for(int i=0; i<inputArrivalTime.length; i++){
            ProcessControlBlock process = new ProcessControlBlock(
                    "p"+(i+1), 
                    inputArrivalTime[i], 
                    inputBurstTime[i]
            );
            hold.enqueue(process);
        }

        ShortestJobNext sjn = new ShortestJobNext();
        sjn.schedule(hold);
        displaySJN(sjn);
    }
    
    public static void displayFCFS(FirstComeFirstServed fcfs){ 
        FinishedQueue ganttChart = fcfs.getGanttChart();
        
        String[] header = {"ProcessName", "ArrivalTime", "BurstTime", "StartTime", "WaitTime"};
        System.out.printf("%-12s %-18s %-15s %-15s %3s%n", header[0], header[1], header[2], header[3], header[4]);
        
        for(int i=0; i<5; i++){
            ProcessControlBlock p = ganttChart.dequeue();
            System.out.printf(
                    "%-12s %-18s %-15s %-15s %1s%n",
                    p.getProcessName(),
                    p.getArrivalTime(), 
                    p.getBurstTime(), 
                    p.getStartTime(),
                    p.getWaitingTime()
            );
        }
        System.out.println("Average waiting time: " + fcfs.getAverageWaitingTime());
    }
    
    public static void displaySJN(ShortestJobNext sjn){ 
        FinishedQueue ganttChart = sjn.getGanttChart();
        
        String[] header = {"ProcessName", "ArrivalTime", "BurstTime", "StartTime", "WaitTime"};
        System.out.printf("%-12s %-18s %-15s %-15s %3s%n", header[0], header[1], header[2], header[3], header[4]);
        
        for(int i=0; i<5; i++){
            ProcessControlBlock p = ganttChart.dequeue();
            System.out.printf(
                    "%-12s %-18s %-15s %-15s %1s%n",
                    p.getProcessName(),
                    p.getArrivalTime(), 
                    p.getBurstTime(), 
                    p.getStartTime(),
                    p.getWaitingTime()
            );
        }
        System.out.println("Average waiting time: " + sjn.getAverageWaitingTime());
    }
}
