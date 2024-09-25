/** 
 * The <code>BlockTracer</code> reads a file of C code
 * and puts them into blocks and prints out the inital value
 * of variables depending on the print command.
 * 
 * @author Brian Chau
 *    email brian.chau@stonybrook.edu
 *    Stony Brook ID: 116125954  
 *    Recitation: 02
**/

import java.util.Stack;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BlockTracer{
    public static void main(String[] args){
        Stack<Block> blockStack = new Stack<Block>();
        try{
            FileInputStream fis = new FileInputStream(args[0]); 
            InputStreamReader inStream = new InputStreamReader(fis);
            BufferedReader stdin = new BufferedReader(inStream);
            String line = "";
            while((line = stdin.readLine()) != null){
//                System.out.println(line);
                if(line.contains("{")){
                    blockStack.push(new Block());
                }
                if(line.contains("int ") && !line.contains("/*$print")){
                    addNewVariable(line, blockStack.peek());
                }
                if(line.contains("/*$print")){
                    printVariables(line, blockStack.peek());
                }
                if(line.contains("}")){
                    blockStack.pop();
                    if(blockStack.isEmpty()){
                        break;
                    }
                }
            }
            stdin.close();
        }
        catch(Exception e){
            // System.out.println("File not found");
            e.printStackTrace();
        }
    }

    private static void addNewVariable(String line, Block curBlock){
        int start = line.indexOf("int") + 3; //start of the assignment
        int end = line.indexOf(";"); //end of the assignment
        String assignment = line.substring(start, end).strip();//meant to remove the boiler plate to make it easier to process
        int equals = assignment.indexOf("=");
        String left = assignment.substring(0, equals).strip();
        String right = assignment.substring(equals + 1).strip();
        int initialValue = Integer.parseInt(right);
        try{
            curBlock.addVariable(left, initialValue);
        }
        catch(Exception e){
            System.out.println("Block is full");
        }
    }

    private static void printVariables(String line, Block curBlock){
        //Add 5 to make the start after the print
        int start = line.indexOf("print") + 5;
        int end = line.indexOf("*", start);
        String argument = line.substring(start, end).strip(); //Represents the argument of the print
        if(argument.equals("LOCAL")){
            curBlock.printAllVariables();
        }
        else{
            curBlock.printOneVar(argument);
        }
    }
}