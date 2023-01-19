import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ZhegalkinPolynomialMain {


    public static void main(String[] args) throws URISyntaxException, IOException {
        ZhegalkinPolynomialMain main = new ZhegalkinPolynomialMain();
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        System.out.print("Select from these\n" +
                "1.check test txt file\n" +
                "2.enter your values");
        int selection = Integer.parseInt(scanner.nextLine());
        if (selection == 2) {
            System.out.print("Enter count of variables: ");
            int n = Integer.parseInt(scanner.nextLine());  // Read user input
            System.out.println("Enter rows in format '1 0 1 0'...but" + (n + 1) + " symbols included(vars + func value)");
            ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
            for (int i = 0; i < Math.pow(2, n); i++) {
                System.out.print("your " + i + " row:");
                String[] stringNumbers = scanner.nextLine().split(" ");
                Integer[] numbers = new Integer[n + 1];
                for (int j = 0; j < stringNumbers.length; j++) {
                    numbers[j] = Integer.parseInt(stringNumbers[j]);
                }
                matrix.add(new ArrayList<Integer>(Arrays.asList(numbers)));  // Read user input
            }
            System.out.println("You entered: ");
            System.out.print(matrix);
            System.out.println(addPlus(getExpressions(matrix, n)));
        } else if (selection == 1) {
            //System.class.getResource("foo.txt");
            URL url = main.getClass().getResource("testPolynomial.txt");
            System.out.println(url.getFile());

            URL resource = main.getClass().getClassLoader().getResource("testPolynomial.txt");
            if (resource == null) {
                throw new IllegalArgumentException("file not found!");
            } else {
                File file = new File(resource.toURI());
                BufferedReader br = new BufferedReader(new FileReader(file));
                List<String> lines = new ArrayList<String>();
                String line;

                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
                br.close();
                System.out.println(lines);

            }
        }


    }

    public static ArrayList<Integer> getFuncValues(ArrayList<ArrayList<Integer>> matrix) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            result.add(matrix.get(i).get(matrix.get(i).size() - 1));
        }
        return result;
    }

    public static ArrayList<Integer> getBooleanTable(ArrayList<Integer> list) {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(list.get(0));
        int c = list.size() - 1;
        for (int i = 0; i < c; i++) {
            if (list.size() > 1) {
                result.add((list.get(0) + list.get(1)) % 2);
            } else {
                result.add((list.get(0)));
            }
            for (int j = 0; j < c - i; j++) {

                list.set(j, (list.get(j) + list.get(j + 1)) % 2);

            }
            list.remove(list.size() - 1);
        }

        return result;
    }

    public static char getLetter(int n) {
        char[] array = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        return array[n];
    }

    public static ArrayList<ArrayList<Integer>> getVars(ArrayList<ArrayList<Integer>> matrix) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) {
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int j = 0; j < matrix.get(i).size() - 1; j++) {
                arr.add(matrix.get(i).get(j));
            }
            result.add(arr);
        }
        return result;


    }

    public static ArrayList<String> getExpressions(ArrayList<ArrayList<Integer>> matrix, int n) {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Integer> booleanCheck = getBooleanTable(getFuncValues(matrix));


        if (arrSum(getVars(matrix).get(0)) == 0 && booleanCheck.get(0) == 1) {
            result.add("1");
        }

        for (int i = 1; i < getVars(matrix).size(); i++) {
            ArrayList<Integer> subArr = getVars(matrix).get(i);
            String str = "";

            for (int j = 0; j < subArr.size(); j++) {
                if (booleanCheck.get(i) == 0) break;
                if (subArr.get(j) == 1) {
                    str += getLetter(j);
                }
            }
            if (!str.equals("")) result.add(str);

        }
        return result;
    }

    public static int arrSum(ArrayList<Integer> m) {
        int sum = 0;
        for (int i = 0; i < m.size(); i++)
            sum += m.get(i);
        return sum;
    }


    public static String addPlus(ArrayList<String> m) {
        String result = "";
        for (int i = 0; i < m.size(); i++) {
            if (i != m.size() - 1) {
                result += m.get(i) + " + ";
            } else {
                result += m.get(i);
            }
        }
        return result;
    }

}
