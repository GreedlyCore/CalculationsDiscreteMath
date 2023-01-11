import java.io.*;
import java.util.ArrayList;

public class BooleanTableGenerator {

    int countVariables;
    ArrayList<String> rows;


    public BooleanTableGenerator(int count) {
        this.countVariables = count;
        this.rows = rowsGenerator(countVariables);
    }

    public BooleanTableGenerator() {
        this.countVariables = countOfVariablesGenerator();
        this.rows = rowsGenerator(countVariables);
    }

    private int countOfVariablesGenerator() {
        return (int) (Math.random() * 3) + 2;
    }

    private int countOfVariablesGenerator(int min, int max) {
        return (int) ((Math.random() * (max - min + 1)) + min);
    }

    private int booleanInstanceGenerator() {
        return (int) Math.round(Math.random());
    }

    private ArrayList<String> rowsGenerator(int countVars) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, countVars); i++) {
            result.add(rowGenerator(i, countVars, this.booleanInstanceGenerator()));
        }
        return result;
    }

    private String rowGenerator(int num, int countVars, int funcValue) {
        StringBuilder temp = new StringBuilder(Integer.toBinaryString(num));
        if (temp.length() <= countVars) {
            int countOfZero = countVars - temp.length();
            for (int i = 0; i < countOfZero; i++) {
                temp.insert(0, "0");
            }

        }
        temp.append(funcValue);
        return temp.toString().replace("", " ").trim();

    }

    public ArrayList<String> getRows() {
        return rows;
    }

    public int getCountVariables() {
        return countVariables;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < getRows().size(); i++) {
            result.append(getRows().get(i)).append("\n");
        }
        return result.toString();
    }


//    public void writeTableToFile() {
//        try {
//            File file = new File(getClass().getResource("boolean_tables.txt").getPath());
//            FileWriter fileWriter = new FileWriter(file, true);
//            BufferedWriter writer = new BufferedWriter(fileWriter);
//            writer.write(this.toString());
//
//            fileWriter.close();
//
//        } catch (IllegalArgumentException | IOException e) {
//            System.out.println("file not found!");
//            e.printStackTrace();
//        }finally {
//            fileWriter.flush();
//            fileWriter.close();
//        }
//    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            BooleanTableGenerator table = new BooleanTableGenerator();
            //table.writeTableToFile();
            System.out.println(table.toString());
        }
    }
}
