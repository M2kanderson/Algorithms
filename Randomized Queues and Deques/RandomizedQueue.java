//A randomized queue is similar to a stack or queue, except that the item
//removed is chosen uniformly at random from items in the data structure.
//Create a generic ADT RandomizedQueue that supports the following operations.

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> {
   private Item[] store;
   private int capacity;
   private int length;
   public RandomizedQueue()         // construct an empty randomized queue
   {
     this.capacity = 1;
     this.length = 0;
     this.store = (Item[]) new Object[this.capacity];
   }
   public boolean isEmpty()         // return true if the queue is empty, false otherwise
   {
     return this.length == 0;
   }
   public void add(Item item)       // insert the item into the queue
   {
     
     if(this.length + 1 > this.capacity)
     {
       Item[] newStore = (Item[]) new Object[this.capacity * 2];
       this.capacity = this.capacity * 2;
       for(int i =0; i < this.length; i++){
         newStore[i] = this.store[i];
       }
       this.store = newStore;
     }
     this.store[this.length] = item;
     this.length++;
   }
   public Item remove()             // delete and return an item from the queue, uniformly at random
   {
       if(this.isEmpty()){
           throw new java.lang.IndexOutOfBoundsException("The queue is empty!");
       }
     Integer randIdx = (int) StdRandom.uniform(this.length);
     Item item = this.store[randIdx];
     int i = randIdx;
     while(i < this.length - 1)
     {
         this.store[i] = this.store[i + 1];
         i++;
     }
     this.length--;
     return item;
   }
   
   public static void main(String[] args) {
       RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
       System.out.println(queue.isEmpty());
       queue.add(1);
       System.out.println(queue.isEmpty());
       queue.add(2);
       queue.add(3);
       System.out.println(queue.remove());
       System.out.println(queue.remove());
       System.out.println(queue.remove());
       System.out.println(queue.isEmpty());
        queue.add(4);
        System.out.println(queue.remove());
       
   }
}
