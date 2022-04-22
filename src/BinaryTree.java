import java.io.Serializable;
import java.util.Scanner;

public class BinaryTree<E> implements Serializable {

    protected Node<E> root;

    public BinaryTree() {
        root = null;
    }

    /**
     * Constructor sets root to null
     */
    public BinaryTree(E data) {
        root = new Node<>(data);
    }

    /**
     * protected constructor, only used by methods internal to class + subclasses
     * @param root node
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Construct binary tree with data in left and right subtree (root of leftTree is left subtree)
     * @param data, leftTree, rightTree
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }



    /**
     * Gets left subtree
     * @return left subtree or null
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<>(root.left);
        } else {
            return null;
        }
    }

    /**
     * Gets right subtree
     * @return right subtree or null
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<>(root.right);
        } else {
            return null;
        }
    }

    public E getData() {
        return root.data;
    }

    /**
     * Determines whether node is a leaf
     *
     * @return true if no children
     */
    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    /**
     * toString
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, 1, sb);
        return sb.toString();
    }

    private void toString(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append(" ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            toString(node.left, depth + 1, sb);
            toString(node.right, depth + 1, sb);
        }
    }

   /* public Node addToTree(Node<E> node, E data, String morseCode) {
        int index = 0;

            if (morseCode.charAt(index) == '*') {
                System.out.println("left!");
                if (node.left == null) {
                    node.left = new Node<>(data);
                    return node.left;
                }
                else
                    return addToTree(node.left, data, morseCode.substring(index +1 ));
                }
            else if (morseCode.charAt(index) == '-') {
                System.out.println("right!");
                if (node.right == null) {
                    node.right = new Node<>(data);
                    return node.right;
                }
                else {
                    return addToTree(node.right, data, morseCode.substring(index + 1));
                }
            }

        return node;
        //System.out.println(data);
        //node.data = data;
        //System.out.println(node.data);
   // } */

    public static BinaryTree<String> readBinaryTree(Scanner scan){
        String data = scan.nextLine().trim();
        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<>(data, leftTree, rightTree);
        }
    }


    protected static class Node<E> implements Serializable {
        protected E data;
        protected Node<E> left;
        protected Node<E> right;

        /**
         * Constructor for node with data and no children
         *
         * @param data
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }

        public E getData() {
            return data;
        }

        /**
         * toString for node
         *
         * @return String of node data
         */
        public String toString() {
            return data.toString();
        }
    }
}
