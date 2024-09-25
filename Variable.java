/** 
 * The <code>Variable</code> holds the name of a variable and its inital value
 * 
 * @author Brian Chau
 *    email brian.chau@stonybrook.edu
 *    Stony Brook ID: 116125954  
 *    Recitation: 02
**/
public class Variable{
    private String name;
    private int initialValue;

    /**
     * Creates an empty <code>Variable</code> object
     */
    public Variable(){

    }

    /**
     * Creates a <code>Variable</code> object given a <code>Song</code>
     * 
     * @param name
     *   The String to be put in the name field
     * 
     * @param initVal
     *   The integer to be put in the initialValue
     */
    public Variable(String name, int initVal){
        this.name = name;
        this.initialValue = initVal;
    }

    /**
     * Sets the value of the field <code>name</code> to the <code>newName</code>
     * 
     * @param newName
     *    String that represents the new value of the name field
     **/
    public void setName(String newName){
        name = newName;
    }

    /**
     * Sets the value of the field <code>initalValue</code> to the <code>newVal</code>
     * 
     * @param newVal
     *    int that represents the new value of the initalValue field
     **/
    public void setVal(int newVal){
        initialValue = newVal;
    }

    /**
     * Returns the value of the <code>name</code> field
     * 
     * 
     * @return
     *     The String in the <code>name</code> field
     **/
    public String getName(){
        return name;
    }

    /**
     * Returns the value of the <code>initalValue</code> field
     * 
     * 
     * @return
     *     The int in the <code>initialValue</code> field
     **/
    public int getInitialValue(){
        return initialValue;
    }
}