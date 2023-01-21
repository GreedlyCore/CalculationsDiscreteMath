public class Pair<T, R> {

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