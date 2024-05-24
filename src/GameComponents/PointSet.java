package GameComponents;


import java.awt.*;
import java.util.Iterator;

public class PointSet implements Iterable<Point>{

    private class Node {
        private Node left, right;
        private Point value;

        public Node(Point value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node: x=" + value.x + ", y=" + value.y;
        }
    }
    private class PointSetIterator implements Iterator<Point>{
        LocatonQueue values = new LocatonQueue();
        public PointSetIterator(){
            inorderTraverse(root);
        }
        private void inorderTraverse(Node node){
            if (node == null)
                return;
            inorderTraverse(node.left);
            values.enQueue(node.value);
            inorderTraverse(node.right);
        }
        @Override
        public boolean hasNext() {
            return !values.isEmpty();
        }

        @Override
        public Point next() {
            return values.deQueue();
        }

    }

    @Override
    public Iterator<Point> iterator() {
        return new PointSetIterator();
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

    public boolean add(Point location) {
        if (contains(location))
            return false;

        var node = new Node(location);

        if (root == null){
            root = node;
        }
        else {
            var current = root;
            while (true) {
                if (location.x < current.value.x) {
                    if (current.left == null) {
                        current.left = node;
                        break;
                    }
                    current = current.left;
                } else if (location.x > current.value.x) {
                    if (current.right == null) {
                        current.right = node;
                        break;
                    }
                    current = current.right;
                } else {
                    if (location.y < current.value.y) {
                        if (current.left == null) {
                            current.left = node;
                            break;
                        }
                        current = current.left;
                    } else if (location.y > current.value.y) {
                        if (current.right == null) {
                            current.right = node;
                            break;
                        }
                        current = current.right;
                    }
                }
            }
        }
        size++;
        return true;
    }
    public void clear(){
        clear(root);
        System.gc();
    }
    public void remove(Point location) {
        //root = delete(root, location);
        var node = find(root, location);

        if (node == null)
            return;

        if (isLeaf(node) || hasOneChild(node) != null)
            delete(node);
        else {
            Node exchangeNode;

            if (height(node.right) < height(node.left)) {
                exchangeNode = findMin(node.right);
                if (exchangeNode == null)
                    exchangeNode = node.right;
            }
            else {
                exchangeNode = findMax(node.left);
                if (exchangeNode == null)
                    exchangeNode = node.left;
            }
            var temp = exchangeNode.value;
            delete(exchangeNode);
            node.value = temp;
        }
        size--;
        System.gc();
    }
    private void clear(Node node){
        if (node == null) {
            return;
        }
        clear(node.left);
        clear(node.right);
        delete(node);
        size--;
    }
    private boolean isLeaf(Node node){
        return node.left == null && node.right == null;
    }
    private Node hasOneChild(Node node){
        if (isLeaf(node) ||  (node.left != null && node.right != null))
            return null;
        if (node.left != null)
            return node.left;
        return node.right;
    }
    private void delete(Node node){
        var parent = parent(node);
        if (parent == null) {
            root = null;
            return;
        }
        if (parent.left == node)
            parent.left = hasOneChild(node);
        else
            parent.right = hasOneChild(node);
    }
    private Node find(Node node, Point data){
        if(node == null)
            return null;
        if (node.value.x > data.x || ((node.value.x == data.x) && (node.value.y > data.y)))
            return find(node.left, data);
        else if (node.value.x < data.x || ((node.value.x == data.x) && (node.value.y < data.y)))
            return find(node.right, data);
        return node;
    }
    private Node parent(Node node){
        var current = root;
        Node parent = null;
        while (current != null) {
            if (current.value.x > node.value.x || ((current.value.x == node.value.x) && (current.value.y > node.value.y))) {
                parent = current;
                current = current.left;
            } else if (current.value.x < node.value.x || ((current.value.x == node.value.x) && (current.value.y < node.value.y))) {
                parent = current;
                current = current.right;
            }else {
                break;
            }
        }
        return parent;
    }
    private Node findMin(Node node){
        if (node == null)
            return null;
        var current = node;
        while (current.left != null)
            current = current.left;

        return current;
    }
    private Node findMax(Node node){
        if (node == null)
            return null;
        var current = node;
        while (current.right != null)
            current = current.right;

        return current;
    }
    private int height(Node node){
        if (node == null)
            return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }
}
