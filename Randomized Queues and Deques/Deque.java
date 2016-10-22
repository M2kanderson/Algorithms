//A double-ended queue or deque (pronounced "deck") is a generalization
//of a stack and a queue that supports inserting and removing items from
//either the front or the back of the data structure. Create a generic ADT
//Deque that supports the following operations.

public class Deque<Item> {
   private Node first;
   private Node last;
   public Deque()                   // construct an empty deque
   {
      first = null; 
      last = null;
   }
   
   private class Node
   {
       Item item;
       Node next;
       Node prev;
   }
   public boolean isEmpty()         // return true if the queue is empty, false otherwise
   {
       return this.first == null;
   }
   public void addFirst(Item item)  // insert the item at the front of the queue
   {
       Node next = first;
       first = new Node();
       first.item = item;
       first.next = next;
       first.prev = null;
       if(next != null){
           next.prev = first;
       }
       if (last == null)
       {
           last = first;
       }
   }
   public void addLast(Item item)   // insert the item at the end of the queue
   {
       if(last == null)
       {
           addFirst(item);
       }
       else
       {
           Node prev = last;
           last = new Node();
           last.item = item;
           last.next = null;
           last.prev = prev;
           prev.next = last;
       }
       
   }
   public Item removeFirst()        // delete and return the first item in the queue
   {
       if(first == null)
       {
           throw new java.lang.IndexOutOfBoundsException("The queue is empty!");
       }
       Item item = first.item;
       first = first.next;
       if(first != null)
       {
           first.prev = null;
       }
       else {
           last = null;
       }
       
       return item;
   }
   public Item removeLast()         // delete and return the last item in the queue
   {
       if(last == null)
       {
           throw new java.lang.IndexOutOfBoundsException("The queue is empty!");
       }
       Item item = last.item;
       last = last.prev;
       if(last != null){
           last.next = null;
       }
       else{
           first = null;
       }
       return item;
   }
   
   public static void main(String[] args) {
       Deque<Integer> queue = new Deque<Integer>();
       queue.addFirst(1);
       queue.addFirst(2);
       queue.addFirst(3);
       queue.addFirst(4);
       queue.addFirst(5);
       queue.addFirst(6);
       System.out.println(queue.removeFirst());
       System.out.println(queue.removeLast());
       System.out.println(queue.removeFirst());
       System.out.println(queue.removeLast());
       System.out.println(queue.removeFirst());
       System.out.println(queue.removeLast());
       queue.addFirst(10);
       System.out.println(queue.removeLast());
       
   }
}
