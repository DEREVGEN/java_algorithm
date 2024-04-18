package algorithm.lowerbound;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LowerUpperAlgo {

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        System.out.print("input size : ");
        int N = Integer.parseInt(input.readLine());
        System.out.print("input numbers with blank : ");


        st = new StringTokenizer(input.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        System.out.print("input target num : ");
        int target = Integer.parseInt(input.readLine());

        // LowerUpperBound<Integer> lowerUpperBound = new LowerUpperBound<>();

        int li = lowerBound(nums, target);
        int ui = upperBound(nums, target);

        System.out.println("lower bound: " + li + " upper bound: " + ui);
    }

    static int lowerBound(int[] arr, int key) {
        int start = 0, end = arr.length-1;
        while (start <= end) {
            int mid = (start + end)/2;
            if (arr[mid] >= key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    static int upperBound(int[] arr, int key) {
        int start = 0, end = arr.length-1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] > key)
                end = mid - 1;
            else
                start = mid + 1;
        }
        return end;
    }
}
