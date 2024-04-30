package GameComponents;

import javax.swing.*;

public class NavigationStack<T> {
   private static class Node<T>{
       T data;
       Node<T> next;

       Node(T data){
           this.data = data;
           this.next = null;
       }
   }

    private Node<T> top;
   public NavigationStack(){
        top = null;

    }
    public boolean isEmpty() {
        return top == null;
    }

    public void push(T value){
       if(isEmpty()){
           top= new Node<>(value);
       }else {
           Node<T> temp = new Node<>(value);
           temp.next = top;
           top = temp;
       }
        if (value instanceof JPanel)
            ((JPanel) value).setVisible(true);
    }

    public T pop(){
        if (!isEmpty()){
            T show = top.data;
            top = top.next;
            if (show instanceof JPanel)
                ((JPanel) show).setVisible(false);
            return show;
        }else {
            System.out.println("Stack is Empty");
            return null;
        }
    }

    public T peek(){
        if (!isEmpty()){
            return top.data;
        }
        else {
            System.out.println("Stack is EMPTY");
            return null;
        }
    }



}
