package algorithm.segmenttree;

/**
 * 시그먼트 트리, 구간의 누적 데이터를 구하기 위한 자료구조 알고리즘
 * 예제는 구간 합을 구하기 위한 시그먼트 트리 예제.
 */

public class SegmentTree {

    static int[] nums;
    static int[] st;
    static int n;

    public static void main(String[] args) {
        init();

        int n = nums.length;

        build(1, 0, n - 1);

        int res = query(1, 0, n-1, 0, 4);
        System.out.println(res);

        update(1, 0, n - 1, 1, 100);

        System.out.println(query(1, 0, n-1, 0, 4));
    }

    public static void init() {
        nums = new int[]{ 1, 2, 3, 4, 5 };

        n = nums.length;

        int i = 1;
        for (int e = 1; e < n; i++)
            e = e << 1;

        int l = (int) (Math.pow(2, i) - 1);

        st = new int[l + 1];
    }

    public static void build(int idx, int l, int r) {
        if (l == r) {
            st[idx] = nums[l];
            return;
        }

        int mid = (l + r) / 2;

        build(2 * idx, l, mid);
        build(2 * idx + 1, mid+1, r);

        st[idx] = st[2 * idx] + st[2 * idx + 1];
    }

    /**
     *   param(tl, tr) : 0-4의 합,
     *   param(l,
     *   r) : tree 순회 범위,
     *   param(idx) : tree index
     */

    /**
     *
     * @param idx 시그먼트 트리의 탐색 할 idx
     * @param l,r 시그먼트 트리에서의 구간 [l, r] 값.
     * @param tl,tr 구간 쿼리 [tl, tr] 값.
     * @return
     */
    public static int query(int idx, int l, int r, int tl, int tr) {

        // 범위 벗어날 시,
        if (tr < l || r < tl)
            return 0;

        // 범위에 속한다면,
        if (tl <= l && r <= tr)
            return st[idx];

        int m = (l + r) / 2;

        return query(2 * idx, l, m, tl, tr) + query(2 * idx + 1, m + 1, r , tl, tr);
    }

    public static void update(int idx, int l, int r, int nIdx, int val) {
        if (l == r) {
            nums[nIdx] += val;
            st[idx] += val;

            return;
        }

        int mid = (l + r) / 2;

        if (l <= nIdx && nIdx <= mid) {
            update(2 * idx, l, mid, nIdx, val);
        } else {
            update(2 * idx + 1, mid + 1, r, nIdx, val);
        }

        st[idx] = st[2 * idx] + st[2 * idx + 1];
    }
}
