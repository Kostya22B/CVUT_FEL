package cz.cvut.fel.pjv;

public class TreeImpl implements Tree{
    NodeImpl root;
    int[] merits_tree;
    public TreeImpl() {

    }
    public void setTree(int[] merits_tree) {
        if (merits_tree.length == 0) {
            return;
        }
        int start = 0;
        int end = merits_tree.length - 1;
        int middleIndex = getMiddleIndex(start, end);

        root = new NodeImpl();

        this.merits_tree = merits_tree;
        root.setValue(merits_tree[middleIndex]);
        root.setLeft(SetTreeFunction(start, middleIndex - 1));
        root.setRight(SetTreeFunction(middleIndex + 1, end));
    }

    public NodeImpl SetTreeFunction(int start, int end) {
        System.out.println("Start: " + start + " End: " + end);
        NodeImpl node = new NodeImpl();
        if (end < 0 || start > end) {
            return null;
        }
        if (start == end) {
            node.setValue(merits_tree[start]);
            return node;
        }
        if (end == (start + 1)) {
            NodeImpl nodeLeft = new NodeImpl();
            node.setValue(merits_tree[end]);
            nodeLeft.setValue(merits_tree[start]);
            node.setLeft(nodeLeft);
            return node;
        }
        int middleIndex = getMiddleIndex(start, end);
        node.setValue(merits_tree[middleIndex]);
        node.setLeft(SetTreeFunction(start, middleIndex - 1));
        node.setRight(SetTreeFunction(middleIndex + 1, end));
        return node;
    }

    public int getMiddleIndex(int start, int end) {
        if (start == end) {
            return start;
        }
        return ((end - start) + 1)/2 + start;
    }
    public Node getRoot() {
        return root;
    }
    public String toString() {
        if (root == null) return "";
        StringBuilder result = new StringBuilder();
        TreeToStringConvert(root, 0, result);
        return result.toString();
    }
    private void TreeToStringConvert(Node node, int level, StringBuilder saving) {
        if (node == null) {
            return;
        }
        saving.append(stringRepeat(" ", level)).append("- ").append(node.getValue()).append("\n");
        TreeToStringConvert(node.getLeft(), level + 1, saving);
        TreeToStringConvert(node.getRight(), level + 1, saving);
    }
    public String stringRepeat(String str, int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(str);
        } return result.toString();
    }
}