/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugba.Netgsm.ClientInfo;

/**
 *
 * @author tugbacanoglu
 */
public class RequestBody {

    int id;
    String operation;
    int num1;
    int num2;

    
    RequestBody (int id, String operation, int num1, int num2){
    this.id = id;
    this.operation = operation;
    this.num1 = num1;
    this.num2 = num2;
    
    }
    
      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

      public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
 
      public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }
    
      public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }
}

