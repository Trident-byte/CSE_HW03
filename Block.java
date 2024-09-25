/** 
 * The <code>Block</code> provides a way to store all the
 * variables in a certain block of code
 * 
 * @author Brian Chau
 *    email brian.chau@stonybrook.edu
 *    Stony Brook ID: 116125954  
 *    Recitation: 02
**/
public class Block{
    private Variable[] variables;
    private final static int CAPACITY = 10;
    private int size;

    /**
     * Creates an empty <code>Block</code> object
     * and initalizes the field
     */
    public Block(){
        variables = new Variable[CAPACITY];
        size = 0;
    }

    /**
     * Creates a new <code>Variable</code> and adds it to the array
     * of variables
     * 
     * <dt>Precondition
     *   <dd>The variables array is not full
     * 
     * <dt>Postcondition
     *   <dd>The size has been increased by one
     * 
     * @param varName
     *    The String that represents the name of the variable
     * @param value
     *    The integer that represents the intial value of the variable
     * @throws FullBlockError
     *    Indicates that the block's variable array is full
     */
    public void addVariable(String varName, int value) throws FullBlockError{
        if(size >= CAPACITY){
            throw new FullBlockError();
        }
        Variable newVar = new Variable(varName, value);
        variables[size] = newVar;
        size++;
    }

    /**
     * Prints out all the <code>Variable</code> in the block
     * 
     * <dt>Postcondition
     *   <dd>All the variables in the block are printed out
     */
    public void printAllVariables(){
        heading();
        for(int i = 0; i < size; i++){
            entry(variables[i]);
        }
    }

    /**
     * Prints out a var given a name. If name is not found an 
     * <code>IllegalArgumentException</code> will be thrown
     * 
     * @param name
     *    The name of the variable to be searched
     * @throws IllegalArgumentException
     *    Indicates variable is not found
     */
    public void printOneVar(String name) throws IllegalArgumentException{
        int index = 0;
        while(index < size){
            Variable var = variables[index];
            if(var.getName().equals(name)){
                heading();
                entry(var);
            }
            index++;
        }
        if(index == size){
            throw new IllegalArgumentException("Variable is not found");
        }

    }

    /**
     * Returns the value of the <code>variables</code> field
     * 
     * 
     * @return
     *     The Variable array in the <code>variables</code> field
     **/
    public Variable[] getVariables(){
        return variables;
    }

    /**
     * Returns the value of the <code>size</code> field
     * 
     * 
     * @return
     *     The int in the <code>size</code> field
     **/
    public int getSize(){
        return size;
    }

    private void heading(){
        System.out.println("Variable Name   Initial Value");
    }

    private void entry(Variable var){
        System.out.printf(" %-16s%-13d\n", var.getName(), var.getInitialValue());
    }
}