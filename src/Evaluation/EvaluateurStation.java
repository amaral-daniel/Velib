package Evaluation;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Data.State;
import Data.Station;

public final class EvaluateurStation {
	private EvaluateurStation()
	{
		minimalCriticalTime = 30*60; //30 minutes 
	}
	
	private static int minimalCriticalTime;
	
	public static void setMinimalCriticalTime(int n)
	{
		minimalCriticalTime = n;
	}
		
	public static boolean isCritical(Station station)
	{

		int criticalTime = 0;
		//System.out.println("--------------------");
		//System.out.println("looking station:" + station + "\n");
		
		//loop running through all States j of the selected Station i
		//Must change this!!!!!!!!!!!!!!! Methods have changed
		//System.out.println("number of state:::" + station.getNumberOfStates());
		for	(int j = 0; j < station.getNumberOfStates() - 1; j++)
		{

			State currentState = station.getState(j);
			State nextState = station.getState(j + 1);
			//check if the station i is almost empty or full in State j
		//	System.out.println("state::::" + currentState);
			if (currentState.getNBikes() <= 0 || currentState.getNBikes() == station.getCapacity()) 
			{			
		//		System.out.println("next:::" + nextState.getDate().getTime() + "\n");
		//		System.out.println("current:::" + currentState.getDate().getTime() + "\n");
				long durationState = nextState.getDate().getTime() - currentState.getDate().getTime() ;	
				criticalTime += (int)durationState;
		//		System.out.println("adding critical time:" + (int)durationState + "\n");
			}
					
		}			
		
		if(criticalTime >= minimalCriticalTime)
		{
				return true;
		}
		return false;
	}
	
	public static void exportCSVStationStates(Station station) throws FileNotFoundException
	{
	
		ArrayList<State> stateList = station.getStateList();

		PrintWriter out = new PrintWriter( "src/Evaluation/states_" + station.getName() )  ;
		for(int i = 0; i < stateList.size(); i++)
		{    		     	 
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");      
		    String reportDate = df.format(stateList.get(i).getDate());
		   	out.println(reportDate + "," + stateList.get(i).getNBikes());
		}
		
		out.close();
	}
	
	 public static void main(String args[]) throws IOException {  
	      FileInputStream in = null;
	      FileOutputStream out = null;

	      try {
	         in = new FileInputStream("src/Evaluation/input.txt");
	         out = new FileOutputStream("src/Evaluation/output.txt");
	         
	         int c;
	         while ((c = in.read()) != -1) {
	            out.write(c);
	         }
	      }finally {
	         if (in != null) {
	            in.close();
	         }
	         if (out != null) {
	            out.close();
	         }
	      }
	   }
	
	
	
	
}

		
