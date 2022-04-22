import javax.crypto.spec.PSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> {

    protected Node<Character> root;

    public MorseCodeTree() {
        super();
        root = new Node<>('0');
        readFromFile();
    }

    public MorseCodeTree(Character data) {
        super(data);
        root = new Node<>(data);
        readFromFile();
    }

    protected MorseCodeTree(Node<Character> root) {
        super(root);
    }

    public MorseCodeTree(Character data, BinaryTree<Character> leftTree, BinaryTree<Character> rightTree) {
        super(data, leftTree, rightTree);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, 1, sb);
        return sb.toString();
    }

    private void toString(Node<Character> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
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

    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.

    public void readFromFile () {
        try(BufferedReader br = new BufferedReader(new FileReader("MorseCode.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            String[] parts;

            while (line != null) {
                parts = line.split(" ");
                readMorseCodeTree(root, parts[0].charAt(0), parts[1]);

                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Node readMorseCodeTree(Node<Character> node, Character data, String morseCode) {

        int index = 0;

        if (morseCode.charAt(index) == '*') {

            if (node.left == null) {
                node.left = new Node<>(data);
                return node.left;
            }
            else
                return readMorseCodeTree(node.left, data, morseCode.substring(index +1 ));
        }
        else if (morseCode.charAt(index) == '-') {

            if (node.right == null) {
                node.right = new Node<>(data);
                return node.right;
            }
            else {
                return readMorseCodeTree(node.right, data, morseCode.substring(index + 1));
            }
        }
        return node;
    }
    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) {

        Scanner scan = new Scanner(morseCode);
        StringBuilder msg = new StringBuilder();

        while(scan.hasNext()) {
            String morseCodeToken = scan.next();

            if (morseCodeToken.length() > 5 || morseCodeToken.equals("**--") || morseCodeToken.equals("*-*-") || morseCodeToken.equals("----")) {
                throw new IllegalArgumentException();
            }

            Node<Character> temp = root;
            for (int i = 0; i < morseCodeToken.length(); i++) {
                if (morseCodeToken.charAt(i) == '*') {
                    root = root.left;

                } else if (morseCodeToken.charAt(i) == '-') {

                    root = root.right;

                } else if (morseCodeToken.charAt(i) == ' ') {
                    msg.append(root.data);
                    root = temp;

                } else {
                    System.out.println("Input must consist of * - and spaces only!");
                    throw new IllegalArgumentException();
                }
            }
            msg.append(root.data);
            root = temp;
        }
        return msg.toString();
    }

} // End of class MorseCodeTree