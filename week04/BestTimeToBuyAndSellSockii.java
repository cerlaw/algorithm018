/**
 * @author zhanghongjie
 * @date 2020/11/11
 */
public class BestTimeToBuyAndSellSockii {
    /**
     * 贪心算法,时间复杂度O(n)，空间复杂度O(1)，
     * 只要后一天的价格比前一天的价格要贵就可以获得利润，所以拆分成最小子问题，从最小子问题最优解得到最终的最优解
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }

    /**
     * 动态规划，时间复杂度O(n），空间复杂度为O(1)
     * 有两种状态，第一种是持有的，第二种是空仓的，第二题的状态可以有前一天的状态推导出来，最后的话肯定是空仓的状态利润最好
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int dp0 = 0, dp1 = -prices[0];
        int n = prices.length;

        for (int i = 1; i < n; i++) {
            dp0 = Math.max(dp0, dp1 + prices[i]);
            dp1 = Math.max(dp1, dp0 - prices[i]);
        }
        return dp0;
    }
}
