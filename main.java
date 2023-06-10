package gdp;

import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;

public class main {
    static int[] weights;
    static int[] values;
    public static void main(String[] args) {
        System.out.println("请输入物品的个数：");
        Scanner sc=new Scanner(System.in);
        int  n=sc.nextInt();
        System.out.println("请输入物品的容量：");
        int  capacity=sc.nextInt();
        weights = new int[n];
        values= new int[n];
        System.out.println("请输出每个物品的重量");
        for (int i = 0; i < n; i++) {
            System.out.print("请输入第" + (i+1) + "个物品的重量：");
            weights[i]= sc.nextInt();
        }
        System.out.println(Arrays.toString(weights));//输出数组，将数组转换成String类型输出的
        for (int i = 0; i < n; i++) {
            System.out.print("请输入第" + (i+1) + "个物品的价值：");
            values[i]= sc.nextInt();
        }
        System.out.println(Arrays.toString(values));

        test01 panel = new test01(weights, values, capacity);
        JFrame frame = new JFrame("Knapsack Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }
}
