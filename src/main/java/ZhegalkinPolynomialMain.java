import java.io.IOException;
import java.net.URISyntaxException;

public class ZhegalkinPolynomialMain {
    public static void main(String[] args) throws URISyntaxException, IOException {
        // read from file in resources
        ZhegalkinPolynomial zhegalkin = new ZhegalkinPolynomial("polynom.txt");
        System.out.println(zhegalkin);
        // input via console with strict order
        ZhegalkinPolynomial zhegalkin2 = new ZhegalkinPolynomial();
        System.out.println(zhegalkin2);
        // fast input via console
        ZhegalkinPolynomial zhegalkin3 = new ZhegalkinPolynomial(true);
        System.out.println(zhegalkin3);

        //TODO надо сортировать массив для треугольника паскаля, чтобы добавить ввод в любом порядке
        // - any order of rows in boolean table - will lead to the same result here


    }

}
