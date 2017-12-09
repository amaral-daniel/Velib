package Data;

import java.text.*;
import java.util.*;

public class State {
    private Date date;
    private int numberOfFreeBikes;
    private int numberOfFreeStands;
    private boolean isFull;
    private boolean isEmpty;

    /* Constructeur */
    /* Date format "yyyyMMddhhmmss" */ 
    public State(int numOfFreeBikes, Date currentDate, int capacity) {
      this.numberOfFreeBikes = numOfFreeBikes;
      this.numberOfFreeStands = capacity - numOfFreeBikes;
      this.isFull = (numOfFreeBikes == capacity);
      this.isEmpty = (numOfFreeBikes == 0);
      this.date = currentDate;
    }
    
    /* Input Date String "yyyyMMddhhmmss" */ 
    /* Date format "yyyyMMddhhmmss" */ 
    public State(int numOfFreeBikes, String currentDateString, int capacity) {
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
    
    
    /* Getters */
    public Date getDate() {
      return date;
    }
    public int getNBikes() {
      return numberOfFreeBikes;
    }
    public int getNStands() {
      return numberOfFreeStands;
    }
    public boolean isFull() {
      return isFull;
    }
    public boolean isEmpty() {
      return isEmpty;
    }
    
    /* Setters */
    public void setDate(final Date p1) {
      date = p1;
    }
    
 
    public String toString() {
      SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmss");
      String info = ft.format(date);
 //     String info = date.toString();
      info = info +"\t N of freebikes: \t"+getNBikes() + "\t N of freestands: \t"+getNStands();
      if (isFull()) {
        info = info + "\t full";
        }
      else if (isEmpty()) {
        info = info + "\t empty";
      }
      else info = info + "\t ni full ni empty";
      return info;
    }

    
    /* Pour tester le code */
    public static void main(String [] args) {
      // Constructeur de classe Date
      Date date1 = new Date();
      State st1 = new State(3,date1,10); 
      System.out.println(st1);
      
      // Constructeur de classe Date
      Date date2 = new Date();
      State st2 = new State(0,date2,10); 
      System.out.println(st2);
      
      // Constructeur de String
      SimpleDateFormat myFormater = new SimpleDateFormat ("yyyyMMddhhmmss");
      try {
        Date date3 = myFormater.parse("19950930091030");
        State st3 = new State(10,date3,10);
        System.out.print(st3);
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }
    
}
