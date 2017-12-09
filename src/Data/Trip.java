package Data;

import java.util.Date;

//import com.modeliosoft.modelio.javadesigner.annotations.objid;

public class Trip {
    private enum Reason{
                  RENT, MAINTENANCE,REGULATION };
    private Reason reason;
    private Date startDate;
    private Date endDate;
    private boolean isValid;
    
    public Station startStation;
    public Station endStation;

    /* Constructeur reason: 1-RENT, 2-MAINTENANCE, 3-REGULATION*/
    public Trip (int rsn, Date start, Date end) {
      switch (rsn) {
        case 1: this.reason = Reason.RENT;
                break;
        case 2: this.reason = Reason.MAINTENANCE;
                break;
        case 3: this.reason = Reason.REGULATION;
                break;
      }
      this.startDate = start;
      this.endDate = end;
      this.isValid = isValid;
    }
    
    /* Trip manipulation */
    public void cancelTrip() {
      isValid = false;
    }
    
    public void validateTrip() {
      isValid = true;
    }

    
    /* Getters */
    public Reason getReason() {
      return reason;
    }
    public Date getStartDate() {
      return startDate;
    }
    public Date getEndDate() {
      return endDate;
    }
    public boolean isValid() {
      return isValid;
    }
    public Station getStartStation() {
      return startStation;
    }
    public Station getEndStation() {
      return endStation;
    }
    
    
    /* Setters */
    public void setStartStation(Station newStartStation) {
      startStation = newStartStation;
    }
    
    public void setEndStation(Station newEndStation) {
      endStation = newEndStation;
    }
   

}
