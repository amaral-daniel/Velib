package Data;

import java.text.*;
import java.util.*;



public class State {

    private Date date;
    private int bikes;

    /* Constructeur */
    /* Date format "yyyy-MM-dd hh:mm:ss" */ 
    public State(int numOfBikes, Date currentDate) {
      bikes = numOfBikes;
      date = currentDate;
    }
    
    /* Input Date String "yyyy-MM-dd hh:mm:ss" */ 
    /* Date format "yyyy-MM-dd hh:mm:ss" */ 
    public State(int numOfBikes, String currentDateString) {
      bikes = numOfBikes;
      SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      try {
        date = ft.parse(currentDateString);
      } catch (ParseException e) {
        System.out.println("Unparseable using" + ft);
      }
    }
    

    public Date getDate(Date date) {
      return date;
    }

    public void setDate(final Date p1) {
      date = p1;
    }
    
    public int getBikes() {
      return bikes;
    }
    
    public String toString() {
      return date.toString();
    }

    
    /*Pour tester le code*/
    public static void main(String [] args) {
      Date date1 = new Date();
      State st = new State(3,date1);
      System.out.println(st);
    }
    
}
