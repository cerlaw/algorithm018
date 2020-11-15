/**
 * 1518换酒问题
 * https://leetcode-cn.com/problems/water-bottles/
 * 贪心法，时间复杂度O(n)，空间复杂度O(1)
 *
 */
public class WaterBottles {
    public int numWaterBottles(int numBottles, int numExchange) {
        //贪心算法
        int total = numBottles;
        while (numBottles >= numExchange) {
            int exchange = numBottles / numExchange;
            total += exchange;
            numBottles = numBottles % numExchange + exchange;
        }
        return total;
    }
}
