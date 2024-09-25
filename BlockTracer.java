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
import java.io.File;

public class BlockTracer{
    public static void main(String[] args){
        Stack<Block> blockStack = new Stack<Block>();
        File file = new File(args[0]);
        Scanner stdin;
        try{
            stdin = new Scanner(file);
            String line = "";
            while(stdin.hasNextLine()){
                line = stdin.nextLine();
                if(line.contains("{")){
                    blockStack.push(new Block());
                }
                else if(line.contains("int")){
                    addNewVariable(line, blockStack.peek());
                }
                else if(line.contains("/*$print")){
                    printVariables(line, blockStack.peek());
                }
                else if(line.contains("}")){
                    blockStack.pop();
                    if(blockStack.isEmpty()){
                        break;
                    }
                }
            }
            stdin.close();
        }
        catch(Exception e){
            System.out.println("File not found");
        }
    }

    private static void addNewVariable(String line, Block curBlock){
        int start = line.indexOf("int") + 3; //start of the assignment
        int end = line.indexOf(";"); //end of the assignment
        String assignment = line.substring(start, end).strip();//meant to remove the boiler plate to make it easier to process
        int equals = assignment.indexOf("=");
        String left = assignment.substring(0, equals).strip();
        String right = assignment.substring(equals).strip();
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
        int end = line.indexOf("/", start);
        String argument = line.substring(start, end).strip(); //Represents the argument of the print
        if(argument.equals("LOCAL")){
            curBlock.printAllVariables();
        }
        else{
            curBlock.printOneVar(argument);
        }
    }
}