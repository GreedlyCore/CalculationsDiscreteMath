import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GraphCheckerMain {


    public static void main(String[] args) throws URISyntaxException, IOException {
        //create with console
        Graph graph = new Graph();
        // read from your txt file in resource folder
        Graph graph_file = new Graph("table_test.txt");
        System.out.println(graph);
        System.out.println(graph_file);

    }

}
