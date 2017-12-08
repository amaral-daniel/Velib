package Data;

import java.text.*;
import java.util.*;

public class State {
    
    private Date date;
    private int numberOfFreeBikes;

    /* Constructeur */
    /* Date format "yyyyMMddhhmmss" */ 
    public State(int numOfFreeBikes, Date currentDate) {
      numberOfFreeBikes = numOfFreeBikes;
      date = currentDate;
    }
    
    /* Input Date String "yyyyMMddhhmmss" */ 
    /* Date format "yyyyMMddhhmmss" */ 
    public State(int numOfFreeBikes, String currentDateString) {
      numberOfFreeBikes = numOfFreeBikes;
      if (currentDateString.length() == 14) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmss");
        try {
          date = ft.parse(currentDateString);
        } catch (ParseException e) {
          System.out.println("Unparseable using" + ft);
        }
      }
    }
    
    /*getters*/
    public Date getDate(Date date) {
      return date;
    }
    public void setDate(final Date p1) {
      date = p1;
    }
    
    public int getNumberOfFreeBikes() {
      return numberOfFreeBikes;
    }
    
    public boolean isOpen() {
      return isOpen;
    }
    
    public String toString() {
      return date.toString()+"\t Number of free bikes: "+getNumberOfFreeBikes();
    }

    
    /*Pour tester le code*/
    public static void main(String [] args) {
      //constructeur de classe Date
      Date date1 = new Date();
      State st1 = new State(3,date1);
      System.out.println(st1);
      
    //constructeur de String
      SimpleDateFormat myFormater = new SimpleDateFormat ("yyyyMMddhhmmss");
      try {
        Date date2 = myFormater.parse("19950930091030");
        State st2 = new State(1,date2);
        System.out.print(st2);
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }
    
}
