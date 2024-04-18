package algorithm.lowerbound;

public class LowerUpperBound <T extends Number & Comparable<T>> {

    int lowerBound(T[] arr, T key) {
        int start = 0, end = arr.length-1;
        while (start <= end) {
            int mid = (start + end)/2;
            if (arr[mid].compareTo(key) >= 0) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    int upperBound(T[] arr, T key) {
        int start = 0, end = arr.length-1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid].compareTo(key) > 0)
                end = mid - 1;
            else
                start = mid + 1;
        }
        return end;
    }
}
