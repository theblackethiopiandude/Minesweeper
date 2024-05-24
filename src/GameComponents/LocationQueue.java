package GameComponents;

import java.awt.*;

public class LocationQueue {
    class Node{
        private Node next;
        private Point value;

        public Node(Point value) {
            this.value = value;
        }
    }
    private Node head, tail;

    public void enQueue(Point location){
        Node node = new Node(location);
        if (head == null){
            head = tail = node;
            return;
        }
        tail.next = node;
        tail = node;
    }
    public Point deQueue(){
        if (head == null)
            return null;
      var location = head.value;
      head = head.next;
      System.gc();
      return location;
    }
    public boolean isEmpty(){
        return head == null;
    }
}
