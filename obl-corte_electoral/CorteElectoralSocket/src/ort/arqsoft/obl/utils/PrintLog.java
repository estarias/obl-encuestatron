/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ort.arqsoft.obl.utils;

/**
 *
 * @author Felipe
 */
public class PrintLog {

    private String prefix = "";
    private String posfix = "";

    private static PrintLog instance = null;

    public static PrintLog getInstance(){
        if (PrintLog.instance == null){
            PrintLog.instance = new PrintLog();
            PrintLog.instance.posfix = "";
            PrintLog.instance.prefix = "";
            return PrintLog.instance;
        }else{
            return PrintLog.instance;
        }
    }

    private PrintLog(){
    }

    public void setPrefix(String prefix){
        instance.prefix = prefix.trim();
    }

    public void setPosfix(String posfix){
        instance.posfix = posfix;
    }

    public void printMsg(String msg){
        System.out.println(instance.prefix + " " + msg.trim() + " " + instance.posfix);
    }

}
