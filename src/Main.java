import java.io.*;

public class Main {

    public static void main(String[] args) {
	    /*BinaryTree<String> binaryTree = new BinaryTree<>("0");
        try(BufferedReader br = new BufferedReader(new FileReader("MorseCode.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            String[] parts;

            while (line != null) {
                parts = line.split(" ");
                System.out.println(parts[0]);
                System.out.println(parts[1]);

                binaryTree.addToTree(binaryTree.root, parts[0], parts[1]);


                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(binaryTree); */

        MorseCodeTree morseCodeTree = new MorseCodeTree('0');
        try(BufferedReader br = new BufferedReader(new FileReader("MorseCode.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            String[] parts;

            while (line != null) {
                parts = line.split(" ");
                System.out.println(parts[0]);
                System.out.println(parts[1]);

                morseCodeTree.readMorseCodeTree(morseCodeTree.root, parts[0].charAt(0), parts[1]);

                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(morseCodeTree);
        System.out.println("Translation: " + morseCodeTree.translateFromMorseCode("*-"));

    }
}
