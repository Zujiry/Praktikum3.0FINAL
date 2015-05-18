/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation.util;

import java.text.DecimalFormat;

/**
 *
 * @author Ralf
 */
public class Utility {
    private static final DecimalFormat percentFormat = new DecimalFormat(" 0.00;-0.00");
    private static final DecimalFormat valueFormat = new DecimalFormat(" 000.00;-000.00");
    private static final DecimalFormat secondsFormat = new DecimalFormat("00");
    
    public static String useValueFormat(double number) {
        return valueFormat.format(number);
    }
    
    public static String usePercentFormat(double number) {
        return percentFormat.format(number);
    }
    
    public static String useSecondsFormat(double number) {
        return secondsFormat.format(number);
    }
}
