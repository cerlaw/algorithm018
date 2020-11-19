import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 874模拟行走机器人
 * 欧式距离：坐标点(x,y)到原点的距离 根号下x的平方加上y的平方
 * https://leetcode-cn.com/problems/walking-robot-simulation/
 * @author zhanghongjie
 * @date 2020/11/19
 */
public class WalkingRobotSimulation {
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<Pair<Integer, Integer>> obstaclesSet = new HashSet<>();
        for (int i = 0; i < obstacles.length; i++) {
            obstaclesSet.add(new Pair<>(obstacles[i][0], obstacles[i][1]));
        }

        //坐标
        int[] directX = new int[]{0, 1, 0, -1};
        int[] directY = new int[]{1, 0, -1, 0};
        //方向
        int direct = 0;
        //起点
        int x = 0, y = 0;
        int res = 0;
        for (int i = 0; i < commands.length; i++) {
            if (commands[i] == -1) {
                direct = (direct + 3) % 4;
            } else if (commands[i] == -2) {
                direct = (direct + 1) % 4;
            } else {
                for (int j = 0; j < commands[i]; j++) {
                    int nextX = x + directX[direct];
                    int nextY = y + directY[direct];
                    if (obstaclesSet.contains(new Pair<>(nextX, nextY))) {
                        break;
                    } else {
                        x = nextX;
                        y = nextY;
                    }
                }
                //跑完后计算欧式平方距离的平方，比较之前的
                res = Math.max(x * x + y * y, res);
            }
        }
        return res;
    }
}
