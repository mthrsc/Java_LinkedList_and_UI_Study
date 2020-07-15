/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppPackage;

/**
 *
 * @author mth
 */
public class SongObject {
    
    private String title;   //The 2 piece of information requested
    private int year;
    
    public SongObject(String title, int year){      //Overriden Constructor 
        this.title = title;
        this.year = year;
    }

    public SongObject() {   //Regular constructor in case I need to create an empty object (For temporary use)
        this.title = null;
        this.year = 0;
    }
    
    
    //Getters and Setters
    public void setTitle(String s){
        title = s;        
    }
    
    public void setYear(int y){
        year = y;
    }
    
    public String getTitle(){
        return title;
    }
    
    public int getYear(){
        return year;
    }
    
    //Overrinden toString that let me print the object info the way I want
    @Override
    public String toString(){
        String s;
        s = "Title: " + title + "- Year: " + year;
        return s;
    }
}
