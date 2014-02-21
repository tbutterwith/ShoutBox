package com.ShoutBox.Tom.stores;


public class ShoutStore {
     String Shout;
     String User;
     String uuid;
     
     public String getShout(){
     return Shout;
     }
     public String getUser(){
     return User;
     }
     
     public String getUuid(){
    	 return uuid;
     }
     
     public void setShout(String Shout){
     this.Shout=Shout;
     }
     
     public void setUser(String User){
     this.User=User;
     }
     
     public void setUuid(String uuid){
    	 this.uuid=uuid;
     }
     
}