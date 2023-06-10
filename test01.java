package gdp;

import java.awt.*;
import javax.swing.*;

public class test01 extends JPanel {
    private int[] weights;
    private int[] values;
    private int capacity;
    private int[][] memo;

    public test01(int[] weights, int[] values, int capacity) {
        this.weights = weights;
        this.values = values;
        this.capacity = capacity;
        this.memo = new int[weights.length][capacity + 1];
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int x = 10;
        int y = 10;
        int width = 50;
        int height = 50;

        for (int i = 0; i < weights.length; i++) {
            g2d.drawRect(x, y, width, height);
            g2d.drawString("W:" + weights[i] + " V:" + values[i], x, y + height + 15);
            x += width + 10;
        }

        x = 10;
        y = 100;
        int[] result = knapsack(weights, values, capacity, weights.length - 1, memo);
        for (int i = 0; i < result.length; i++) {
            if (result[i] == 1) {
                g2d.setColor(Color.GREEN);
                g2d.fillRect(x, y, width, height);
                g2d.setColor(Color.BLACK);
            } else {
                g2d.drawRect(x, y, width, height);
            }
            g2d.drawString("W:" + weights[i] + " V:" + values[i], x, y + height + 15);
            x += width + 10;
        }
    }

    private int[] knapsack(int[] weights, int[] values, int capacity, int i, int[][] memo) {
        if (i < 0 || capacity == 0) {
            return new int[weights.length];
        }

        if (memo[i][capacity] != 0) {
            return memo[i][capacity] == 1 ? new int[weights.length] : knapsack(weights, values, capacity, i - 1, memo);
        }

        if (weights[i] > capacity) {
            memo[i][capacity] = -1;
            return knapsack(weights, values, capacity, i - 1, memo);
        }

        int[] without = knapsack(weights, values, capacity, i - 1, memo);
        int[] with = knapsack(weights, values, capacity - weights[i], i - 1, memo);
        with[i] = 1;
        int withoutValue = 0;
        int withValue = values[i];

        for (int j = 0; j < without.length; j++) {
            withoutValue += without[j] * values[j];
            withValue += with[j] * values[j];
        }

        if (withValue > withoutValue) {
            memo[i][capacity] = 1;
            return with;
        } else {
            memo[i][capacity] = -1;
            return without;
        }
    }

}