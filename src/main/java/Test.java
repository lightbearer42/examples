public class Test {

    public static void main(String[] args) {
        Node root = new Node(7);
        Node n1 = new Node(root, 3);
        Node n2 = new Node(root, 9);
        Node n11 = new Node(n1, 2);
        Node n12 = new Node(n1, 5);
        Node n21 = new Node(n2, 8);
        Node n22 = new Node(n2, 10);
        Node n111 = new Node(n11, 2);
        Node n121 = new Node(n12, 4);
        Node n122 = new Node(n12, 6);

        root.left = n1;
        root.right = n2;
        n1.left = n11;
        n1.right = n12;
        n2.left = n21;
        n2.right = n22;
        n11.left = n111;
        n12.left = n121;
        n12.right = n122;

        System.out.println("root => " + String.valueOf(next(root)));
        System.out.println("n1 => " + String.valueOf(next(n1)));
        System.out.println("n2 => " + String.valueOf(next(n2)));
        System.out.println("n11 => " + String.valueOf(next(n11)));
        System.out.println("n12 => " + String.valueOf(next(n12)));
        System.out.println("n21 => " + String.valueOf(next(n21)));
        System.out.println("n22 => " + String.valueOf(next(n22)));
        System.out.println("n111 => " + String.valueOf(next(n111)));
        System.out.println("n121 => " + String.valueOf(next(n121)));
        System.out.println("n122 => " + String.valueOf(next(n122)));
    }

    private static Node next(Node node) {
        if (node.right != null) {
            return goLeft(node.right);
        }

        if (node.parent != null) {
            return goParent(node.parent, node.val);
        }

        return null;
    }

    private static Node goLeft(Node node) {
        if (node.left == null) return node;

        return goLeft(node.left);
    }

    private static Node goParent(Node node, int val) {
        if (node.val > val) {
            return node;
        }
        if (node.parent != null) {
            return goParent(node.parent, val);
        }

        return null;
    }
}

class Node {
    Node left;
    Node right;
    Node parent;
    int val;

    public Node(int val) {
        this.val = val;
    }

    public Node(Node parent, int val) {
        this.parent = parent;
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{val=" + val +
                '}';
    }
}
