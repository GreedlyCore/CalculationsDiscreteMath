import java.util.Arrays;

public class BinaryNumber {

    private int integerNumber;
    private int length;
    private String stringOrdinaryNumber;



    BinaryNumber(int n) {
        this.length = length;
        this.stringOrdinaryNumber = Integer.toBinaryString(n);
    }

    public String getStringLongNumber(){
        String result = stringOrdinaryNumber;
        if (length-stringOrdinaryNumber.length() > 0) {
            for (int i = 0; i < length - stringOrdinaryNumber.length(); i++) {
                result = "0" + result;
            }
        }
        return result;
    }

    public static int[][] generateSequence(int len){
        int[][] result = new int[(int) Math.pow(2,len)][len];
        for (int i = 0; i < Math.pow(2,len); i++) {
            BinaryNumber bin = new BinaryNumber(i);
            bin.setLength(len);
            result[i] = Helper.mapStringToInteger(bin.getStringLongNumber().split(""));
        }
        return result;
    }

    public void setLength(int length) {
        this.length = length;
    }


    public static void main(String[] args) {
        BinaryNumber bin = new BinaryNumber(4);
        bin.setLength(3);
        System.out.println(Arrays.toString(Helper.mapStringToInteger(bin.getStringLongNumber().split(""))));
    }
}
