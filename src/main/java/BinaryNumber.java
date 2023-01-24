public class BinaryNumber {

    // int num will be converted to binary then if length of binary less than "length" var
    // additional zeros will be added in right side
    private static String getStringBinaryBalancedNumber(int num, int length) {
        String stringBinaryNumber = Integer.toBinaryString(num);
        int c = stringBinaryNumber.length();
        if (length - c > 0) {
            for (int i = 0; i < length - c; i++) {
                stringBinaryNumber = "0" + stringBinaryNumber;
            }
        }
        return stringBinaryNumber;
    }

    public static int[][] generateSequence(int len) {

        int[][] result = new int[(int) Math.pow(2, len)][len];
        for (int i = 0; i < Math.pow(2, len); i++) {
            result[i] = Helper.mapStringToInteger(BinaryNumber.getStringBinaryBalancedNumber(i, len).split(""));
        }
        return result;
    }

}
