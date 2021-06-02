/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugba.Netgsm.ClientInfo;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author tugbacanoglu
 */
public class JSONBody{ //once I sort out the JSON request recieving I should parse it here
    
   
    JSONParser parser = new JSONParser();
    
       RequestBody JSONBody(RequestBody request){
    
        
        
      JSONObject obj = new JSONObject();

      obj.put("id",  request.id);
      obj.put("operation", request.operation);
      obj.put("num1", request.num1);
      obj.put("num2", request.num2);

      //System.out.print(obj);
    return request;
    }
//       
//    void Parse(){
//    try
//    {
//    Object obj = parser.parse(s);
//    
//    
//    }catch(ParseException pe) {
//		
//         System.out.println("position: " + pe.getPosition());
//         System.out.println(pe);
//      }
//    }
//    
    
    
    }
    
 
    
    

