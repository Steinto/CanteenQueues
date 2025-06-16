/**
 * Node for inside a data structure
 *
 * Toby Steiner
 * 16/6/2025
 */
public class Node
{
    private int data;
    private Node next;

    public Node(){
        this.data = 0;
    }
    
    public Node(int data){
        this.data = data;
    }

    
    public void setData(int data){
        this.data = data;
    }
    public void setNext(Node n){
        this.next = n;
    }
    
    public int getData(){
        return this.data;
    }
    public Node getNext(){
        return this.next;
    }
}
