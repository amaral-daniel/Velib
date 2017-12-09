package Evaluation;
import java.util.ArrayList;

import Data.*;

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
			if (currentState.getNBikes() <= 2 || currentState.getNBikes() == station.getCapacity()) 
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

	public static void main(String[] args)
	{
		System.out.println("test");
		
	}	
	
	
	
	
}

		
