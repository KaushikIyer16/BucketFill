/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bfa.beans;

/**
 *
 * @author kaushiknsiyer
 */
public class ElectiveCombination {
    private int day;
    private int hour;

    public ElectiveCombination(int day, int hour) {
        this.day = day;
        this.hour = hour;
    }

    @Override
    public boolean equals(Object obj) {
        ElectiveCombination obj2 = (ElectiveCombination)obj;
        if(this.day == obj2.day && this.hour == obj2.hour){
            return true;
        }else{
            return false;
        }
    }
    
    
}
