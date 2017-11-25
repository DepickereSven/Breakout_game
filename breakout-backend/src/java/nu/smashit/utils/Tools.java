package nu.smashit.utils;

// @author Jonas

import java.util.concurrent.ThreadLocalRandom;

public class Tools {

    public static int validateBetween(int validate, int min, int max, int defaultvalue) {
        if (validate >= min && validate <= max){
            return validate;
        }else{
            return defaultvalue;
        }
    } 
    
    public static int getRandomBetween(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static int[] shuffleArray(int[] array) {
        for (int i = array.length - 1; i >= 0; i--){
            int index = ThreadLocalRandom.current().nextInt(i+1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        
        return array;
    }
    
}
