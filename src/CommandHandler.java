/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author diptam
 */
public class CommandHandler {
    
    String[] arr;
    int getCommand(String cmd)
    {
        arr = cmd.split(" ");
        if(arr[0].equalsIgnoreCase("insert"))
            return 1;
        if(arr[0].equalsIgnoreCase("display"))
            return 2;
        if(arr[0].equalsIgnoreCase("find"))
            return 3;
        if(arr[0].equalsIgnoreCase("range"))
            return 4;
        if(arr[0].equalsIgnoreCase("count"))
            return 5;
        return 6;
    }
    
    int getFirstOperand()
    {
        if(arr!=null)
            return Integer.parseInt(arr[1]);
        return 0;
    }
    
    int getSecondOperand()
    {
        if(arr!=null)
            return Integer.parseInt(arr[2]);
        return 0;
    }
    
}
