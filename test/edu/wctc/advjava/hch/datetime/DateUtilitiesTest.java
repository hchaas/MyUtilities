/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.advjava.hch.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class DateUtilitiesTest {
    
    public DateUtilitiesTest() {
    }
    
    @Before
    public void setUp() {
        DateUtilities test = new DateUtilities();
    }
    
    @After
    public void tearDown() {
    }

    @Test (expected = IllegalArgumentException.class)
    public void convertDateToStringThrowsExceptionForNullDate(LocalDate date){
        DateUtilities test = new DateUtilities();
        test.convertDateToStringDefault(null);   
    }
    
    @Test
    public void convertDateToStringReturnsString(){
        
    }
    
}
