/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProcessQueue;

/**
 *
 * @author Ryan
 */
public abstract class Queue {
    protected static class Node{
        ProcessControlBlock data;
        Node next;
        
        public Node(ProcessControlBlock data){
            this.data = data;
            this.next = null;
        }
    }
    
    protected String status;
    protected Node front;
    protected Node rear;
    protected int size;
    
    public Queue(String status){
        this.status = status;
        this.front = null;
        this.rear = null;
        this.size = 0;
    }
    
    public void enqueue(ProcessControlBlock process){
        process.setStatus(this.status);
        Node newNode = new Node(process);
        if(isEmpty()){
            front = newNode;
            rear = front;
        }else{
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }
    
    public ProcessControlBlock dequeue(){
        if(isEmpty()){
            return null;
        }
        ProcessControlBlock process = front.data;
        front = front.next;
        if(isEmpty()){
            rear = null;
        }
        size--;
        return process;
    }
    
    public boolean isEmpty(){
        return front == null;
    }
    
    public int getSize(){
        return size;
    }
    
    public void display(){
        Node current = front;
        while(current != null){
            System.out.print(current.data.getProcessName() + "->");
            current = current.next;
        }
        System.out.println("null");
    }
    
    public ProcessControlBlock peek(){
        return front.data;
    }
}
