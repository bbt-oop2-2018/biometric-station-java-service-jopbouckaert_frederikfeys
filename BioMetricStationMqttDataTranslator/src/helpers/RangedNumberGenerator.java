/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.Random;

/**
 *
 * @author jopbo_000
 */
public class RangedNumberGenerator {
    
    private Random dataGenerator = new Random();
    private int minimum;
    private int maximum;
    private double value;
    
    public RangedNumberGenerator(int minimum,int maximum){
        this.minimum = minimum;
        this.maximum = maximum;
        
    }
    
    public double generate(){
        value = (double) Math.round((dataGenerator.nextDouble()
                * ( maximum- minimum + 1) + minimum)*100d)/100d;
        
        
        return value;
    }
    
    @Override
    public String toString(){
        return "" + value;
    }
        
    
}
