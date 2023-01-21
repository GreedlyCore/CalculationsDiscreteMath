import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class Helper {


    public static int[] mapStringToInteger(String[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = Integer.parseInt(arr[i]);
        }
        return result;
    }
    public static int[] reversePair(int[] pair) {
        int[] result = new int[2];
        result[0] = pair[1];
        result[1] = pair[0];
        return result;
    }

    // ["3","2"] --> [3, 2]
    public static int[] stringArrayPairToInteger(String[] pair) {
        int[] result = new int[2];
        for (int j = 0; j < 2; j++) {
            result[j] = Integer.parseInt(pair[j]);
        }
        return result;
    }

    public static String[][] readPairs(String filename) throws URISyntaxException, IOException {
        URL resource = Helper.class.getClassLoader().getResource(filename);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }
        File file = new File(resource.toURI());

        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        String[][] result = new String[lines.size()][2];
        for (int i = 0; i < lines.size(); i++) {
            result[i] = lines.get(i).split(" ");
        }
        return result;
    }

    public static int fact(int number) {
        int f = 1;
        int j = 1;
        while(j <= number) {
            f = f * j;
            j++;
        }
        return f;
    }
    static boolean contains(int[][] pairs, int[] pair){
        for (int[] ints : pairs) {
            if (Arrays.equals(ints, pair)) {
                return true;
            }
        }
        return false;
    }


}
