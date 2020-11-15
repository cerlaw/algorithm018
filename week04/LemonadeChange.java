/**
 * 860柃檬水找零
 * https://leetcode-cn.com/problems/lemonade-change/
 * 贪心法，时间复杂度O(n)，空间复杂度O(1)
 * 使用贪心法的重点是要解释为什么贪心法能得到正确的答案，在这题是因为，零钱只有5,10，而且成倍数关系。
 * 所以优先找10的话是局部最优的方案
 */
public class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int i = 0; i < bills.length; i++) {
            switch(bills[i]) {
                case 5:
                    five++;
                    break;
                case 10:
                    if (five <= 0) {
                        return false;
                    } else {
                        five--;
                        ten++;
                    }
                    break;
                case 20:
                    //优先找大的零钱
                    if (ten > 0 && five > 0) {
                        ten--;
                        five--;
                    } else if (five >= 3) {
                        five -= 3;
                    } else {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        return true;
    }
}
