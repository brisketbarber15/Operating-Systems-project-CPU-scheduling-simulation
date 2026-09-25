/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProcessQueue;

/**
 *
 * @author Ryan
 */
public class HoldQueue extends Queue{
    public HoldQueue(){
        super("Hold");
    }
    
    public void sortBy(String by){
        front = mergeSort(front, by);
        
        rear = front;
        if(rear != null){
            while(rear.next != null){
                rear = rear.next;
            }
        }
    }
    
    private Node mergeSort(Node front, String by){
        if(front == null || front.next == null){
            return front;
        }
        Node middle = getMiddle(front);
        Node nextToMiddle =  middle.next;
        middle.next = null;
        
        Node left = mergeSort(front, by);
        Node right = mergeSort(nextToMiddle, by);
        
        if(by.equals("arrivalTime")){
            return sortedMergeA(left, right);
        }else if(by.equals("burstTime")){
            return sortedMergeB(left, right);
        }else{
            return front;
        }
    }
    
    private Node sortedMergeA(Node left, Node right){
        if (left == null) return right;
        if (right == null) return left;
        
        Node result;
        if(left.data.getArrivalTime() <= right.data.getArrivalTime()){
            result = left;
            result.next = sortedMergeA(left.next, right);
        }else{
            result = right;
            result.next = sortedMergeA(left, right.next);
        }
        return result;
    }
    
    private Node sortedMergeB(Node left, Node right){
        if (left == null) return right;
        if (right == null) return left;
        
        Node result;
        if(left.data.getBurstTime() <= right.data.getBurstTime()){
            result = left;
            result.next = sortedMergeB(left.next, right);
        }else{
            result = right;
            result.next = sortedMergeB(left, right.next);
        }
        return result;
    }
    
    private static Node getMiddle(Node front) {
        if(front == null) return front;

        Node slow = front;
        Node fast = front;

        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
