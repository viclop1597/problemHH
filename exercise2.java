import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class exercise2 {
    public static void main(String[] args) {
        String fileName = "first_names.txt"; // name of the file containing the first names
        String[] names = readNamesFromFile(fileName); // read the names from the file and store them in an array
        Arrays.sort(names); // sort the names in alphabetical order
        int totalScore = 0; // variable to store the total score
        for (int i = 0; i < names.length; i++) {
            int nameScore = calculateNameScore(names[i], i + 1); // calculate the score for each name and add it to the total score
            totalScore += nameScore;
        }
        System.out.println("Total score of all the names in the file: " + totalScore);
    }

    /**
     * Reads the first names from the given file and returns them in an array
     * @param fileName name of the file containing the first names
     * @return array of first names read from the file
     */
    private static String[] readNamesFromFile(String fileName) {
        String[] names = new String[0];
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // remove the quotes and split the line into names separated by commas
                String[] splitNames = line.replace("\"", "").split(",");
                // add each name to the array
                names = Arrays.copyOf(names, names.length + splitNames.length);
                System.arraycopy(splitNames, 0, names, names.length - splitNames.length, splitNames.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }

    /**
     * Calculates the score for the given name
     * @param name name for which to calculate the score
     * @param position position of the name in the sorted array of names
     * @return score for the given name
     */
    private static int calculateNameScore(String name, int position) {
        int score = 0;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            score += (int) c - 64; // calculate the alphabetical value of each character
        }
        return score * position; // multiply the score by the position of the name
    }
}
