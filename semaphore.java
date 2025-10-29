public class Semaphore {

    protected int value=0; //number of available slots

    public Semaphore(){  // Default constructor initializes semaphore with 0
        value=0;
    }
    public Semaphore(int value){ //constructor to set the value of the semaphore
        this.value=value;
    }
    public synchronized void p(){  // Used by a thread to request access to a resource

        while(value<=0){ //if no resources are available thread must wait until another thread releases one
            try{
                wait();
            }
            catch(InterruptedException e){}
        }
        //once resource is available value is decremented
        value--;
    }
    public synchronized void v(){ // Used by a thread to release a resource when done

        // Increase the available resource count by 1
        value++;

        if(value<=0){ // If there are threads waiting wake one of them
            notify();
        }
    }
    public synchronized int getValue(){ //getter for value
        return value;
    }
}



// car class

class Car extends Thread {
    int id;
    Queue<Integer> buffer;  // waiting queque
    
    Semaphore empty;   // number of empty pump
    Semaphore full;    // Number of cars in pump
    Semaphore mutex;
             
    Car(int id, Queue<Integer> buffer, Semaphore empty, Semaphore full, Semaphore mutex) {
        this.id = id;
        this.buffer = buffer;
        this.empty = empty;
        this.full = full;
        this.mutex = mutex;
    }

    
    @Override
    public void run () {
        if(full.getValue() > 0) {     // if there is car waiting => new car will waite so print waiting
            System.out.println("Car " + id + " arrived and waiting");
        } else {System.out.println("Car " + id + " arrived");}  // if no car waiting => new car just print arrived
        
        empty.p();   // Wait until there is a free spot in the queue
        
        mutex.p();  // wait if the one entering to quque
        
        buffer.add(id);
        System.out.println("Car " + id + " entered the waiting queue");
        
        mutex.v();
  
        full.v();   // notify pump that there is car ready
    }
}


