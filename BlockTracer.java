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
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BlockTracer{
    public static final int BLOCK_CAPACITY = 10;
    public static void main(String[] args){
        Stack<Block> blockStack = new Stack<Block>();
        try{
            FileInputStream fis = new FileInputStream(args[0]); 
            InputStreamReader inStream = new InputStreamReader(fis);
            BufferedReader stdin = new BufferedReader(inStream);
            String line = "";
            while((line = stdin.readLine()) != null){
                System.out.println(line);
                if(line.contains("{")){
                    blockStack.push(new Block());
                }
                while(line.contains("int ") && !line.contains("/*$print")){
                    line = addNewVariable(line, blockStack.peek());
                }
                while (line.contains("/*$print")){
                    line = printVariables(line, blockStack);
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

    private static String addNewVariable(String line, Block curBlock){
        int start = line.indexOf("int") + 3; //start of the assignment
        int end = line.indexOf(";"); //end of the assignment
        String assignment = line.substring(start, end).strip();//meant to remove the boiler plate to make it easier to process
        //keeps the rest in case it is needed
        line = keepString(end, line);
        String assignments[] = new String[BLOCK_CAPACITY];
        int commaIndex = 0;
        int index = 0;
        while((commaIndex = assignment.indexOf(",")) != -1){
            assignments[index] = assignment.substring(0, commaIndex);
            assignment = keepString(commaIndex, assignment);
            index++;
        }
        assignments[index++] = assignment;
        for(int i = 0; i < index; i++){
            if(assignments[i].indexOf("=") != -1){
                evalAssignment(assignments[i], curBlock);
            }
            else{
                try{
                    curBlock.addVariable(assignments[i], 0);
                } 
                catch(Exception e){
                    System.out.println("Block is full");
                }
            }
        }
        return line;
    }

    private static void evalAssignment(String assignment, Block curBlock){
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

    private static String printVariables(String line, Stack<Block> blockStack){
        //Add 5 to make the start after the print
        int start = line.indexOf("print") + 5;
        int end = line.indexOf("*", start);
        String argument = line.substring(start, end).strip(); //Represents the argument of the print
        line = keepString(end, line);
        printBlock(argument, blockStack);
        return line;
    }

    private static void printBlock(String variable, Stack<Block> blockStack){
        Block curBlock = blockStack.peek();
        try {
            if(variable.equals("LOCAL")){
                curBlock.printAllVariables();
            }
            else {
                curBlock.printOneVar(variable);
            }
        }
        catch(Exception e){
            blockStack.pop();
            printBlock(variable, blockStack);
            blockStack.push(curBlock);
        }
    }

    private static String keepString(int end, String string){
        return string.substring(end + 1).strip();
    }
}