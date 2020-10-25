public class ClimbStairs {
    public int solution(int n) {
        //动态规划

        int first = 0;
        int second = 1;
        int third = 0;
        for (int i = 1; i <= n; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }
}