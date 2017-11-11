package nu.smashit.utils;

// @author Jonas
public class BreakoutException extends RuntimeException{

    public BreakoutException(){}
    
    public BreakoutException(String message){
        super(message);
    }
    
    public BreakoutException(String message, Exception ex){
        super(message, ex);
    }
    
}
