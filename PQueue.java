/**
 * Priority queue
 *
 * Toby Steiner
 * 16/6/2025
 */
import java.util.Arrays;
public class PQueue
{
    private Queue lowP;
    private Queue highP;

    public PQueue(){
        this.lowP = new Queue();
        this.highP = new Queue();
    }

    public void enqueue(int num, boolean high){
        if(high){
            this.highP.queue(num);
        }else{
            this.lowP.queue(num);
        }
    }
    
    int dequeue (){
        if(this.highP.emptyQueue()){
            return (this.lowP.dequeue());
        }else{
            return (this.highP.dequeue());
        }
    }
    
    public boolean queueEmpty(){
        if(this.highP.emptyQueue() && this.lowP.emptyQueue()){
            return true;
        }else{
            return false;
        }
    }
    
    public int getLowPLength(){
        return this.lowP.getLength();
    }
    
    public int getHighPLength(){
        return this.highP.getLength();
    }
}
