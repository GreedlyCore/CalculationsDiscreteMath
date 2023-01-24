import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ZhegalkinPolynomial {

    private int countVariables;
    private int[] functionValues;
    private int[][] argumentsTable;

    private int countRows;

    private ArrayList<String> polynomialComponentsStringArray;

    ZhegalkinPolynomial(String filename) throws URISyntaxException, IOException {
        String[][] stringPairs = Helper.readPairs(filename);
        this.countVariables = Integer.parseInt(stringPairs[0][0]);
        countRows = (int) Math.pow(2, countVariables);
        // 2^n еще работает норм, но паша сказал, что 3^m уже будет хреново
        // найти аналог? ? как работает разобраться
        this.argumentsTable = new int[countRows][];
        this.functionValues = new int[countRows];
        for (int i = 0; i < stringPairs.length - 1; i++) {
            argumentsTable[i] = Helper.mapStringToInteger(Arrays.copyOfRange(stringPairs[i + 1], 0, countVariables));
            functionValues[i] = Integer.parseInt(stringPairs[i + 1][countVariables]);
        }

        this.polynomialComponentsStringArray = new ArrayList<>();
        int[] boolArray = getBooleanArrayByPascalMethod();
        if (arraySum(argumentsTable[0]) == 0 && boolArray[0] == 1) {
            polynomialComponentsStringArray.add("1");
        }
        for (int i = 1; i < argumentsTable.length; i++) {
            int[] subArr = argumentsTable[i];
            String str = "";

            for (int j = 0; j < subArr.length; j++) {
                if (boolArray[i] == 0) break;
                if (subArr[j] == 1) {
                    str += getAlphabetLetter(j);
                }
            }
            if (!str.equals("")) polynomialComponentsStringArray.add(str);
        }

    }

    //simplify the way input
    ZhegalkinPolynomial(Boolean bool) {
        System.out.print("Enter count of variables: ");
        Scanner scanner = new Scanner(System.in);
        this.countVariables = Integer.parseInt(scanner.nextLine());
        countRows = (int) Math.pow(2, countVariables);
        this.argumentsTable = BinaryNumber.generateSequence(countVariables);
        this.functionValues = new int[countRows];


        System.out.println("Enter func value");
        for (int i = 0; i < countRows; i++) {


            int number = -1;
            do {
                Helper.printSpaces(argumentsTable[i]);
                if (scanner.hasNextInt()) {
                    number = scanner.nextInt();
                } else {
                    scanner.next();
                }

            } while (number != 0 && number != 1);
            functionValues[i] = number;
        }

        this.polynomialComponentsStringArray = new ArrayList<>();
        int[] boolArray = getBooleanArrayByPascalMethod();
        if (arraySum(argumentsTable[0]) == 0 && boolArray[0] == 1) {
            polynomialComponentsStringArray.add("1");
        }
        for (int i = 1; i < argumentsTable.length; i++) {
            int[] subArr = argumentsTable[i];
            String str = "";

            for (int j = 0; j < subArr.length; j++) {
                if (boolArray[i] == 0) break;
                if (subArr[j] == 1) {
                    str += getAlphabetLetter(j);
                }
            }
            if (!str.equals("")) polynomialComponentsStringArray.add(str);
        }

    }

    ZhegalkinPolynomial() {
        System.out.print("Enter count of variables: ");
        Scanner scanner = new Scanner(System.in);
        this.countVariables = Integer.parseInt(scanner.nextLine());
        countRows = (int) Math.pow(2, countVariables);
        this.argumentsTable = new int[countRows][countVariables];
        this.functionValues = new int[countRows];


        System.out.println("Enter rows in format '1 0 1 0'...like vars + func value");
        for (int i = 0; i < countRows; i++) {
            System.out.print("your " + i + " row:");
            String[] stringNumbers = scanner.nextLine().split(" ");

            argumentsTable[i] = Arrays.copyOfRange(Helper.mapStringToInteger(stringNumbers), 0, countVariables);
            functionValues[i] = Integer.parseInt(stringNumbers[countVariables]);
        }

        this.polynomialComponentsStringArray = new ArrayList<>();
        int[] boolArray = getBooleanArrayByPascalMethod();
        if (arraySum(argumentsTable[0]) == 0 && boolArray[0] == 1) {
            polynomialComponentsStringArray.add("1");
        }
        for (int i = 1; i < argumentsTable.length; i++) {
            int[] subArr = argumentsTable[i];
            String str = "";

            for (int j = 0; j < subArr.length; j++) {
                if (boolArray[i] == 0) break;
                if (subArr[j] == 1) {
                    str += getAlphabetLetter(j);
                }
            }
            if (!str.equals("")) polynomialComponentsStringArray.add(str);
        }

    }

    public int[] getBooleanArrayByPascalMethod() {
        var list = functionValues.clone();
        int[] result = new int[functionValues.length];
        result[0] = list[0];
        int idx = 1;
        for (int i = 0; i < functionValues.length - 1; i++) {
            result[idx] = (list[0] + list[1]) % 2;
            idx++;
            for (int j = 0; j < functionValues.length - i - 1; j++) {
                list[j] = (list[j] + list[j + 1]) % 2;
            }
        }
        return result;
    }

    public static char getAlphabetLetter(int n) {
        return "abcdefghijklmnopqrstuvwxyz".toCharArray()[n];
    }

    public int arraySum(int[] m) {
        int sum = 0;
        for (int i = 0; i < m.length; i++)
            sum += m[i];
        return sum;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < polynomialComponentsStringArray.size(); i++) {
            if (i != polynomialComponentsStringArray.size() - 1) {
                result += polynomialComponentsStringArray.get(i) + " + ";
            } else {
                result += polynomialComponentsStringArray.get(i);
            }
        }
        return result;
    }

}
