import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GraphCheckerMain {
    private static class Pair<T, R> {

        Pair(T first, R second) {
            this.first = first;
            this.second = second;
        }

        T first;
        R second;

        T getEdgesCount() {
            return first;
        }

        R getVerticesCount() {
            return second;
        }
    }

    private static class Graph {

        private int countReflexivityEdges;
        private final int countNonReflexivityEdges;
        private final int countEdgesCompleteGraph;

        Graph() {

            System.out.print("Enter count of edges then vertices with space: ");
            Scanner scanner = new Scanner(System.in);
            int[] Graphvalues = Helper.stringArrayPairToInteger(scanner.nextLine().split(" "));
            this.first = Graphvalues[0];
            this.second = Graphvalues[1];

            this.edges = new int[first][2];
            this.degree = 0;
            System.out.println("Enter pairs: ");
            for (int i = 0; i < getEdgesCount(); i++) {
                System.out.print("Enter your " + i + " pair: ");
                addEdge(scanner.nextLine().split(" "));
            }
            countNonReflexivityEdges = getEdgesCount() - countReflexivityEdges;
            countEdgesCompleteGraph = (getVerticesCount() * (getVerticesCount() - 1)) / 2;

            vertices = new int[getVerticesCount()];
            for (int i = 0; i < getVerticesCount(); i++) {
                vertices[i] = i + 1;
            }
        }

        Graph(String filename) throws URISyntaxException, IOException {
            String[][] stringPairs = Helper.readPairs(filename);
            this.first = Helper.stringArrayPairToInteger(stringPairs[0])[0];
            this.second = Helper.stringArrayPairToInteger(stringPairs[0])[1];
            this.degree = 0;
            this.edges = new int[first][2];
            this.addEdge(stringPairs);
            countNonReflexivityEdges = getEdgesCount() - countReflexivityEdges;
            countEdgesCompleteGraph = (getVerticesCount() * (getVerticesCount() - 1)) / 2;
            vertices = new int[getVerticesCount()];
            for (int i = 0; i < getVerticesCount(); i++) {
                vertices[i] = i + 1;
            }
        }

        private final int first;
        private final int second;
        private int degree; // степень заполненности графа
        int[][] edges;
        private int[] vertices;

        int[] getVertices() {
            return vertices;
        }

        // need at least triangle of connected vertices
        public boolean isGraphEnoughForReflexivity() {
            if (getVerticesCount() > 2) {
                for (int i = 0; i < getVertices().length; i++) {
                    int vertex = getVertices()[i];

                    ArrayList<Integer> possibleSecondVertex = new ArrayList<>();
                    for (int j = 0; j < getEdges().length; j++) {
                        int[] pair = getEdges()[j];
                        if (pair[0] == vertex) {
                            possibleSecondVertex.add(pair[1]);
                        }

                    }
                    if (!possibleSecondVertex.isEmpty()) {
                        for (int j = 0; j < possibleSecondVertex.size() - 1; j++) {
                            for (int k = j + 1; k < possibleSecondVertex.size(); k++) {
                                if (Helper.contains(getEdges(), new int[]{possibleSecondVertex.get(j), possibleSecondVertex.get(k)}))
                                    return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

        public static boolean isPairReflex(int[] pair) {
            return pair[0] == pair[1];
        }

        int getEdgesCount() {
            return first;
        }

        int[][] getEdges() {
            return edges;
        }

        int getVerticesCount() {
            return second;
        }


        void addEdge(int a, int b) {
            if (degree < first) {
                getEdges()[degree][0] = a;
                getEdges()[degree][1] = b;
                degree++;
            } else {
                throw new ArrayIndexOutOfBoundsException("Graph's already filled up!!!");
            }

        }

        void addEdge(String[] pair) {
            if (degree < first) {
                getEdges()[degree][0] = Integer.parseInt(pair[0]);
                getEdges()[degree][1] = Integer.parseInt(pair[1]);

                if (getEdges()[degree][0] == getEdges()[degree][1]) {
                    countReflexivityEdges++;
                }
                degree++;
            } else {
                throw new ArrayIndexOutOfBoundsException("Graph's already filled up!!!");
            }
        }

        void addEdge(String[][] pairs) {
            for (int i = 1; i < pairs.length; i++) {
                if (degree < first) {
                    getEdges()[degree][0] = Integer.parseInt(pairs[i][0]);
                    getEdges()[degree][1] = Integer.parseInt(pairs[i][1]);
                    if (getEdges()[degree][0] == getEdges()[degree][1]) {
                        countReflexivityEdges++;
                    }
                    degree++;
                } else {
                    throw new ArrayIndexOutOfBoundsException("Graph's already filled up!!!");
                }
            }

        }

        public String checkSymmetry() {
            int counter = 0;
            for (int i = 0; i < getEdges().length; i++) {
                int[] reversedPair = Helper.reversePair(getEdges()[i]);
                //ingore reflective pairs
                if (Arrays.equals(getEdges()[i], reversedPair)) {
                    continue;
                }

                for (int j = i + 1; j < getEdges().length; j++) {
                    if (Arrays.equals(reversedPair, getEdges()[j])) {
                        counter += 1;
                    }
                }
            }

            if (counter == 0) {
                return "Антисимметричный";
            } else if (2 * counter == countNonReflexivityEdges) {
                return "Симметричный";
            } else if (2 * counter < countNonReflexivityEdges) {
                return "Несимметричный";
            }
            return "idk symmetry";
        }

        public String checkReflexivity() {

            if (this.countReflexivityEdges == 0) {
                return "Антирефлексивный";
            } else if (this.countReflexivityEdges < getVerticesCount()) {
                return "Нерефлексивный";
            } else if (this.countReflexivityEdges == getVerticesCount()) {
                return "Рефлексивный";
            }
            return "idk reflex";
        }

        public String checkTransitive() {
            int countTransitivePairs = 0;
            int countProbablyPairs = 0;
            if (isGraphEnoughForReflexivity()) {
                for (int i = 0; i < getEdges().length - 1; i++) {
                    for (int j = i + 1; j < getEdges().length; j++) {
                        int[] pairI = getEdges()[i];
                        int[] pairJ = getEdges()[j];
                        if ((pairI[1] == pairJ[0] && pairI[0] != pairJ[1]) && (!isPairReflex(pairI) && !isPairReflex(pairJ))) {
                            int[] probablyPair = {pairI[0], pairJ[1]};
                            countProbablyPairs++;

                            if (Helper.contains(getEdges(), probablyPair)) {
                                countTransitivePairs++;
                            }
                        } else if ((pairI[1] != pairJ[0] && pairI[0] == pairJ[1]) && (!isPairReflex(pairI) && !isPairReflex(pairJ))) {
                            int[] probablyPair = {pairI[0], pairJ[1]};
                            countProbablyPairs++;
                            if (Helper.contains(getEdges(), probablyPair)) {
                                countTransitivePairs++;
                            }
                        }
                    }
                }
                if (countTransitivePairs == countProbablyPairs) return "Транзитивный";
                if (countProbablyPairs != 0) return "Нетранзитивный";
                return "Антитранзитивный";
            } else {
                return "Not enough to calculate transitive --> :(";
            }
        }

        @Override
        public String toString() {
            return "Graph{" +
                    "countReflexivityEdges=" + countReflexivityEdges +
                    ", countNonReflexivityEdges=" + countNonReflexivityEdges +
                    ", countEdgesCompleteGraph=" + countEdgesCompleteGraph + "\n" +
                    "edges=" + Arrays.deepToString(getEdges()) + "\n" +
                    checkReflexivity() + "\n" +
                    checkSymmetry() + "\n" +
                    checkTransitive() + "\n" +
                    '}' + "\n";
        }
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        //create with console
        Graph graph = new Graph();
        // read from your txt file in resource folder
        Graph graph_file = new Graph("table_test.txt");
        System.out.println(graph);
        System.out.println(graph_file);

    }

}
