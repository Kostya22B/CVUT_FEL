package cz.cvut.fel.pjv;

public class NodeImpl implements Node {
    public int merit;
    public Node leftNode = null;
    public Node rightNode = null;
    public void setLeft(Node node) {
        leftNode = node;
    }
    public void setRight(Node node) {
        rightNode = node;
    }
    public int getValue() {
        return merit;
    }
    public Node getLeft() {
        return leftNode;
    }
    public Node getRight() {
        return rightNode;
    }
    public void setValue(int merit) {
        this.merit = merit;
    }
}