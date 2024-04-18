package algorithm.segmenttree;

/**
 * Segment Tree의 Lazy propagation을 사용하여, 지연 갱신.
 * 시간복잡도, update시, O(logN) 이 걸리는 알고리즘.
 *
 */

public class LazySegmentTree {

    static int[] st;
    static int[] lazy;

    public static void main(String[] args) {
        // 터미널 노드는 1,2,3,4,5 가 될 예정
        int[] nums = {1, 2, 3, 4, 5};

        init(nums);

        int res = query(1, 0, nums.length-1, 1, 3);

        System.out.println(res);

        update(1, 0, nums.length-1, 1, 3, 1);
        res = query(1, 0, nums.length-1, 1, 3);

        System.out.println(res);
    }

    public static void init(int[] nums) {
        int i = 1;
        for (int e = 1; e < nums.length; i++)
            e = e << 1;

        int l = (int) (Math.pow(2, i) - 1);

        st = new int[l + 1];
        lazy = new int[l + 1];

        build(1, 0, nums.length-1, nums);
    }

    public static void build(int idx, int l, int r, int[] nums) {
        if (l == r) {
            st[idx] = nums[l];
            return;
        }

        int mid = (l + r) / 2;

        build(2 * idx, l, mid, nums);
        build(2 * idx + 1, mid+1, r, nums);

        st[idx] = st[2 * idx] + st[2 * idx + 1];
    }

    public static void propagate(int idx, int l, int r) {
        if (lazy[idx] > 0) {
            st[idx] += (r - l + 1) * lazy[idx];
            if (l != r) {
                lazy[idx * 2] += lazy[idx];
                lazy[idx * 2 + 1] += lazy[idx];
            }
            lazy[idx] = 0;
        }
    }

    public static int query(int idx, int l, int r, int tl, int tr) {
        propagate(idx, l, r);

        // 범위 벗어날 시,
        if (tr < l || r < tl)
            return 0;

        // 범위에 속한다면,
        if (tl <= l && r <= tr)
            return st[idx];

        int m = (l + r) / 2;

        return query(2 * idx, l, m, tl, tr) + query(2 * idx + 1, m + 1, r , tl, tr);
    }

    public static void update(int idx, int l, int r, int tl, int tr, int val) {

        propagate(idx, l, r);

        if (tr < l || r < tl)
            return;

        if (tl <= l && r <= tr) {
            st[idx] += (r - l + 1) * val;
            if (l != r) {
                lazy[idx * 2] += val;
                lazy[idx * 2 + 1] += val;
            }
            return;
        }

        int m = (l + r) / 2;
        update(2 * idx, l, m, tl, tr, val);
        update(2 * idx + 1, m+1, r, tl, tr, val);

        st[idx] = st[2 * idx] + st[2 * idx + 1];
    }

}