package com.example.travelnepalapp;

import com.example.travelnepalapp.BusinessLogic.Loginquery;
import com.example.travelnepalapp.Models.Authtoken;

import org.junit.Assert;
import org.junit.Test;
//import org.junit.Assert;


public class UnitTesting {
    String success;
    @Test
    public  void testLogin(){
        Loginquery loginquery=new Loginquery("r@r","rr");
         Authtoken result= loginquery.checkUser();
         String username= "Raj";

        Assert.assertEquals(username,result.getUsername());
    }
}
