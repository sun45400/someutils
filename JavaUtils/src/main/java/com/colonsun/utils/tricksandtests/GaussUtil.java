package com.colonsun.utils.tricksandtests;

public class GaussUtil {

    public static double[] boom(double[][] data){
        double[][] matrix = data;
        int length = matrix.length;
        //判断输入数组是否是线性方程组矩阵
        if(length != (matrix[0].length - 1)){
            return null;//矩阵格式不对，不是合规的线性方程组
        }
        for(int i = 0;i < length;i++){
            if(matrix[i][i] == 0){
                return null;//对角线上有0，没法用高斯法
            }
        }
        //下面开始解方程
        for(int i = 0;i < length;i++){
            for(int j = 0;j < length;j++){
                if(j == i){
                    //对角线留下
                    continue;
                }else{
                    //非对角线清零
                    double kValue = matrix[j][i] / matrix[i][i];
                    for(int k = 0;k < length + 1;k++){
                        matrix[j][k] = matrix[j][k] - matrix[i][k] * kValue;
                    }
                }

            }
        }
        double[] result = new double[length];
        for(int i = 0;i < length;i++){
            result[i] = matrix[i][length] / matrix[i][i];
        }
        return result;
    }

}
