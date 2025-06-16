
/**
 * Queue
 *
 * Toby Steiner
 * 16/6/2025
 */
public class Queue{
    private Node head;
    private Node tail;
    private int length;


    public Queue(){
    
    }
    
    public boolean emptyQueue(){
        return(this.head==null);
    }
    
    public int getLength(){
        return this.length;
    }
    
    public void queue(int data){
        Node node = new Node(data);
        if(this.length == 0){
            this.head = node;
            this.tail = node;
            this.length = length + 1;
        }else{
            this.tail.setNext(node);
            this.tail = node;
            this.length = length + 1;
        }
    }
    
    public void addAtEnd (Node node){
        queue(node.getData());
    }
    
    public void insertInOrder (Node node){
        Node temp = this.head;
        for(int i = 0;i<this.length;i++){
            if(temp.getData() == node.getData()){
                node.setNext(temp.getNext());
                temp.setNext(node);
            }
            temp = temp.getNext();
        }
        
        
    }
    
    public int dequeue(){
        if(this.length == 0){
            System.out.println("ERROR EMPTY LIST");
            return 0;
        }else{
            int data = this.head.getData();
            this.head = head.getNext();
            this.length = length - 1;
            return data;
        }
    }
    
    public int count(int val){
        int count = 0;
        Node temp = this.head;
        for(int i = 0;i<this.length;i++){
            if(temp.getData() == val){
                count++;
            }
            temp = temp.getNext();
        }
        return count;
    }
    
    public Node getHead(){
        return this.head;
    }
    public Node getTail(){
        return this.tail;
    }

}
