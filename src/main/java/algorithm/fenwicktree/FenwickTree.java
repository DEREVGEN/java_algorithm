package algorithm.fenwicktree;

/*
    Fenwick Tree
 */

public class FenwickTree {

    public static void main(String[] args) {
        int[] nums = {0, 3, 0, 5, 5, 0, 0, 0};

        int N = nums.length;

        // 인덱스는 1부터 시작.
        int[] tree = new int[N+1];

        buildTree(tree, nums);

        for (int k = 1; k <= N; k++) {
            for (int i = k; i <= N; i++) {
                System.out.print("l: " + k + " r: " + i + " res: " + find(tree, k, i) + "\t");
            }
            System.out.println();
        }
    }

    public static void buildTree(int[] tree, int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            update(tree, i+1, nums[i]);
        }
    }

    public static void update(int[] tree, int idx, int val) {
        while (idx < tree.length) {
            tree[idx] += val;
            /*
                index 2의 보수로 반전 -> 해당 bit와 기존 idx의 bit를 and 연산하게 되면,
                1을 가진 가장 오른쪽의 비트만 남아, 이를 더해준다.
             */
            idx += (idx & -idx);
        }
    }

    public static int find(int[] tree, int l, int r) {
        return findLeftSum(tree, r) - findLeftSum(tree,l-1);
    }

    public static int findLeftSum(int[] tree, int idx) {
        int sum = 0;
        while (0 < idx) {
            sum += tree[idx];
            /*
                1을 가진 가장 오른쪽의 비트를 구하여,
                idx에 빼주어, 다음 idx로 이동한다.
             */
            idx -= (idx & -idx);
        }
        return sum;
    }
}