/** 
 * The <code>FullBlockError</code> indicates that the Block is fulll
 * 
 * @author Brian Chau
 *    email brian.chau@stonybrook.edu
 *    Stony Brook ID: 116125954  
 *    Recitation: 02
**/

public class FullBlockError extends Exception{
    public FullBlockError(){
        new Exception("Block is full");
    }    
}
