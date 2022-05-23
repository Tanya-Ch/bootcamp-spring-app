//package service;
//
//public class QueensService {
//
//    int queens = 8;
//    int[] array = new int[queens];
//    static int count = 0;
//
//    private void check(int n) {
//        if (n == queens) {
//            print();
//            return;
//        }
//        for (int i = 0; i < queens; i++) {
//            array[n] = i;
//            if (judge(n)) {
//                check(n + 1);
//            }
//        }
//    }
//
//    private boolean judge(int n) {
//        for (int i = 0; i < n; i++) {
//            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public void print() {
//        count++;
//        for (int i = 0; i < array.length; i++) {
//            System.out.print(array[i] + " ");
//        }
//        System.out.println();
//    }
//}
//
