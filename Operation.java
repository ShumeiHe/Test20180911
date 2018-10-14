/*
项目名称：FourArithmetic
创建者：何淑媚
创建日期：2018-10-12
版本：1.0
描述：输出结果不为负数的简易四则运算
*/
import java.util.Scanner;

public class Operation {
    private static String[][] topic = new String[100][7];  //题目数组。
    private static int[] operate = new int[2];  //运算符数组，以两个运算符操作。
    private static int[] number = new int[3];  //操作数数组。

    public static void main(String arg[]) {
        int topicNum;
        while (true) {
            System.out.print("请输入题目题数：");
            Scanner in = new Scanner(System.in);
            topicNum = in.nextInt();  //题目题数
            if (topicNum <= 0) {
                System.out.println("您输入的题目题数小于1道，请重新输入！");
            } else {
                break;
            }
        }
        Scanner(topicNum);
        showTopic(topicNum);
    }
    //随机产生操作数和运算符。
    public static void getData() {
        for (int jnum = 0; jnum < 3; jnum++) {
            int randomNum = (int) (1 + Math.random() * 100);
            number[jnum] = randomNum;
        }
        for (int jop = 0; jop < 2; jop++) {
            int randomop = (int) (Math.random() * 4);
            operate[jop] = randomop;
        }
    }
    //获取运算符符号。
    public static String getOp(int randomop) {
        if (randomop == 0) {
            return "+";
        }
        if (randomop == 1) {
            return "-";
        }
        if (randomop == 2) {
            return "*";
        }
        if (randomop == 3) {
            return "/";
        }
        return "";

    }
    //加减乘除运算。
    public static double operation(int randomop, double num1, double num2) {
        if (randomop == 0) {
            return num1 + num2;
        }
        if (randomop == 1) {
            return num1 - num2;
        }
        if (randomop == 2) {
            return num1 * num2;
        }
        if (randomop == 3) {
            return num1 / num2;
        }
        return 0;
    }
    //得出运算结果。
    public static double getResult() {
        //第二个运算符为乘法或者除法运算。
        if (operate[1] >= 2) {
            double result = operation(operate[1], number[1], number[2]);
            result = operation(operate[0], number[0], result);
            result=Double.valueOf(String.format("%10.2f", result));//保留两位小数。
            return result;
        }
        //顺序运算。
        else {
            double result = operation(operate[0], number[0], number[1]);
            result = operation(operate[1], result, number[2]);
            result=Double.valueOf(String.format("%10.2f", result));
            return result;
        }
    }
    //输入运算结果。
    public static void Scanner(int  topicNum){
        double score=0; //统计分数。
        for(int i=0;i< topicNum;i++){
            int num=i+1;
            getData();
            for (int jnum = 0; jnum < 3; jnum++) {
                topic[i][2 * jnum] = String.valueOf(number[jnum]); //题目数组与操作数数组索引值关系。
            }
            for (int jop = 0; jop < 2; jop++) {
                topic[i][2 * jop + 1] = getOp(operate[jop]);
            }
            topic[i][5] = "=";
            topic[i][6] = String.valueOf(getResult());  //获取正确答案
            //答案为整数。
            if(getResult()>0) {
                int j = 0;
                System.out.print("("+num+")");
                for (j = 0; j < 5; j++) {
                    System.out.print(topic[i][j]);
                }
                System.out.print(topic[i][5]);
                Scanner in = new Scanner(System.in);
                double inputNum = in.nextDouble();
                if (!topic[i][6].equals(String.valueOf(inputNum))) {
                    System.out.println("回答错误！扣" + 100.0 / topicNum + "分");
                } else {
                    score =score +100.0 / topicNum ;    //100.0 / topicNum每道题分值。
                }
            }else {i--;}
        }
        System.out.println("您的总分是"+score+"分。请继续加油噢！");
        System.out.println("-------------------------------------");
    }
    //输出正确答案。
    public static void showTopic(int topicNum){
        System.out.println("正确答案是：");
        for(int i=0;i<topicNum;i++){
            for(int j=0;j<6;j++){
                System.out.print(topic[i][j]);
            }
            System.out.print(topic[i][6]);
            System.out.println();
        }

    }
}
