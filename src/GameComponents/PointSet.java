package GameComponents;


import java.awt.*;

public class PointSet{
class Node {
    private Node left, right;
    private final Point value;

    public Node(Point value) {
        this.value = value;
    }
}
    private Node root;
    private int size;
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return root == null || size == 0;
    }

    public boolean contains(Point location) {
        var current = root;
        while (current != null){
            if ((current.value.x == location.x) && (current.value.y == location.y))
                return true;

            if (location.x < current.value.x) {
                current = current.left;
            } else if (location.x > current.value.x) {
                current = current.right;
            } else {
                if (location.y < current.value.y) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }
        return false;
    }

    public void add(Point location) {
        if (contains(location))
            return;

        var node = new Node(location);
        if (root == null){
            root = node;
            return;
        }
        var current = root;
        while (true){
            if (location.x < current.value.x){
                if (current.left == null){
                    current.left = node;
                    break;
                }
                current = current.left;
            } else if (location.x > current.value.x) {
                if (current.right == null){
                    current.right = node;
                    break;
                }
                current = current.right;
            }else{
                if (location.y < current.value.y){
                    if (current.left == null){
                        current.left = node;
                        break;
                    }
                    current = current.left;
                } else if (location.y > current.value.y) {
                    if (current.right == null){
                        current.right = node;
                        break;
                    }
                    current = current.right;
                }
            }
        }
        size++;
    }

    public void remove(Point location) {

    }
}
