package Evaluation;
import Data.*;
import java.util.ArrayList;

public class TripCreator {
	private ArrayList<Trip> base_trips;
	private ArrayList<Trip> simulation_trips;
	private boolean regulation;
	private float popularity_growth;
	
	public TripCreator(ArrayList<Trip> base_trips, boolean regulation, float popularity_growth)
	{
		this.base_trips = base_trips;
		this.regulation = regulation;
		this.popularity_growth = popularity_growth;
		this.simulation_trips = base_trips;
	}
	
	public void create_trips()
	{
		for(int i = 0; i < base_trips.size(); i++)
		{
			switch(base_trips.get(i).getReason()) {
				
				case REGULATION:
					if(regulation)
						simulation_trips.add(base_trips.get(i));
					break;
				case RENT:
					simulation_trips.add(base_trips.get(i));
					if(popularity_growth != 0)
					{
						if(Math.random() < popularity_growth)
						{
							simulation_trips.add(new Trip(base_trips.get(i))); //adds a copy of the trip 
						}
						
					}
					break;
				case MAINTENANCE:
					break;
				default:
					System.out.println("Reason not initialized, ERROR!!!!!!! \n");
			}
		}
	}
	
	public ArrayList<Trip> getTrips()
	{
		return simulation_trips;
	}
	
	public static void main(String[] args) 
	{

	}

}
