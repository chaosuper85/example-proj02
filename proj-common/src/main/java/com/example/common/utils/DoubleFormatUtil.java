package com.example.common.utils;

import java.text.NumberFormat;

/**
 * @author zhuchao
 * @date 2022/2/6 8:31 上午
 */
public class DoubleFormatUtil {

    public static String numberFormat(double n,int size) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(size);
        return numberFormat.format(n);
    }


    public static void main(String[] args) {
        System.out.println(DoubleFormatUtil.numberFormat(1000.98080980809809,2));
    }
}
