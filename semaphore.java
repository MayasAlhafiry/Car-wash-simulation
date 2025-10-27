public class semaphore {

    protected int value=0; //number of available slots

    public semaphore(){  // Default constructor initializes semaphore with 0
        value=0;
    }
    public semaphore(int value){ //constructor to set the value of the semaphore
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
