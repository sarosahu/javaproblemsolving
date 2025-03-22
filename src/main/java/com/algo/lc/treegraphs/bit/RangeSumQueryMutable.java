package com.algo.lc.treegraphs.bit;

public class RangeSumQueryMutable {
    static class NumArray {
        int [] nums;
        int [] BIT;
        int n;
        public NumArray(int[] nums) {
            this.nums = nums;
            this.n = nums.length;
            BIT = new int[n + 1];
            for (int i = 0; i < n; ++i) {
                init(i, nums[i]);
            }
        }

        private void init(int i, int val) {
            ++i;
            while (i <= n) {
                BIT[i] += val;
                System.out.printf("%d --> ", i);
                i += (i & -i);
                System.out.printf("%d <-- DONE.\n", i);
            }
        }

        public void update(int index, int val) {
            int diff = val - nums[index];
            nums[index] = val;
            init(index, diff);
        }

        public int sumRange(int left, int right) {
            return getSum(right) - getSum(left - 1);
        }

        private int getSum(int i) {
            int sum = 0;
            ++i;
            while (i > 0) {
                sum += BIT[i];
                i -= (i & -i);
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        int [] arr = {2, 1, 4, 3, 5, 6, 7, 3};
        NumArray numArray = new NumArray(arr);
        int sumRange = numArray.sumRange(1, 7);
        System.out.println("Sum range : " + sumRange);
    }
}
