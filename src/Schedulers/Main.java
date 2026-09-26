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
    static  int[] inputArrivalTime = {0, 1, 2, 4};
    static int[] inputBurstTime = {5, 4, 2, 1};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        // TODO code application logic here
        
        HoldQueue hold = new HoldQueue();
        
//        for(int i=0; i<inputArrivalTime.length; i++){
//            ProcessControlBlock process = new ProcessControlBlock(
//                    "p"+(i+1), 
//                    inputArrivalTime[i], 
//                    inputBurstTime[i]
//            );
//            hold.enqueue(process);
//        }
//        
//        FirstComeFirstServed fcfs = new FirstComeFirstServed();
//        fcfs.schedule(hold);
//        displayFCFS(fcfs);
//        
//        for(int i=0; i<inputArrivalTime.length; i++){
//            ProcessControlBlock process = new ProcessControlBlock(
//                    "p"+(i+1), 
//                    inputArrivalTime[i], 
//                    inputBurstTime[i]
//            );
//            hold.enqueue(process);
//        }
//
//        ShortestJobNext sjn = new ShortestJobNext();
//        sjn.schedule(hold);
//        displaySJN(sjn);
        
        for(int i=0; i<inputArrivalTime.length; i++){
            ProcessControlBlock process = new ProcessControlBlock(
                    "p"+(i+1), 
                    inputArrivalTime[i], 
                    inputBurstTime[i]
            );
            hold.enqueue(process);
        }

        RoundRobin rr = new RoundRobin(2);
        rr.schedule(hold);
        displayRR(rr);
    }
    
    public static void displayFCFS(FirstComeFirstServed fcfs){ 
        FinishedQueue finishedProcesses = fcfs.getFinishedProcesses();
        
        String[] header = {"ProcessName", "ArrivalTime", "BurstTime", "StartTime", "WaitTime"};
        System.out.printf("%-12s %-18s %-15s %-15s %3s%n", header[0], header[1], header[2], header[3], header[4]);
        
        for(int i=0; i<5; i++){
            ProcessControlBlock p = finishedProcesses.dequeue();
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
        
        GanttChart[] ganttChart = fcfs.getGanttChart();
        
        int j = 0;
        while(ganttChart[j] != null){
            System.out.print(ganttChart[j].getProcessName() + " -> ");
            j++;
        }
        
        System.out.println();
        
        j = 0;
        while(ganttChart[j] != null){
           System.out.print(ganttChart[j].getStartTime() + "    ");
           if(ganttChart[j+1] == null){
               System.out.println(ganttChart[j].getCompletionTime());
           }
            j++; 
        }
    }
    
    public static void displaySJN(ShortestJobNext sjn){ 
        FinishedQueue finishedProcesses = sjn.getFinishedProcesses();
        
        String[] header = {"ProcessName", "ArrivalTime", "BurstTime", "StartTime", "WaitTime"};
        System.out.printf("%-12s %-18s %-15s %-15s %3s%n", header[0], header[1], header[2], header[3], header[4]);
        
        for(int i=0; i<5; i++){
            ProcessControlBlock p = finishedProcesses.dequeue();
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
        
        GanttChart[] ganttChart = sjn.getGanttChart();
        
        int j = 0;
        while(ganttChart[j] != null){
            System.out.print(ganttChart[j].getProcessName() + " -> ");
            j++;
        }
        
        System.out.println();
        
        j = 0;
        while(ganttChart[j] != null){
           System.out.print(ganttChart[j].getStartTime() + "    ");
           if(ganttChart[j+1] == null){
               System.out.println(ganttChart[j].getCompletionTime());
           }
            j++; 
        }
    }
    
    public static void displayRR(RoundRobin rr){ 
        FinishedQueue finishedProcess = rr.getFinishedProcesses();
        
        String[] header = {"ProcessName", "ArrivalTime", "BurstTime", "CompletionTime", "TurnAroundTime", "WaitTime"};
        System.out.printf("%-12s %-18s %-15s %-15s %-15s %-15s%n", header[0], header[1], header[2], header[3], header[4], header[5]);
        
        for(int i=0; i<4; i++){
            ProcessControlBlock p = finishedProcess.dequeue();
            System.out.printf(
                    "%-12s %-18s %-15s %-15s %-15s %1s%n",
                    p.getProcessName(),
                    p.getArrivalTime(), 
                    p.getBurstTime(), 
                    p.getCompletionTime(),
                    p.getTurnAroundTime(),
                    p.getWaitingTime()
            );
        }
        System.out.println("Average waiting time: " + rr.getAverageWaitingTime());
        System.out.println("Average turn around time: " + rr.getAverageTurnAroundTime());
        System.out.println("Time quantum: " + rr.timeQuantum);
        
        GanttChart[] ganttChart = rr.getGanttChart();
        
        int j = 0;
        while(ganttChart[j] != null){
            System.out.print(ganttChart[j].getProcessName() + " -> ");
            j++;
        }
        
        System.out.println();
        
        j = 0;
        while(ganttChart[j] != null){
           System.out.print(ganttChart[j].getStartTime() + "    ");
           if(ganttChart[j+1] == null){
               System.out.println(ganttChart[j].getCompletionTime());
           }
            j++; 
        }
    }
}
