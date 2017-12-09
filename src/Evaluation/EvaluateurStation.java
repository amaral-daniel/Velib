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
		
		//loop running through all States j of the selected Station i
		//Must change this!!!!!!!!!!!!!!! Methods have changed
		for	(int j; j < station.getNumberOfStates() - 1; j++)
		{
			
			State currentState = station.getState(j);
			State nextState = station.getStateList(j + 1);
			//check if the station i is almost empty or full in State j
			if (currentState.getNBikes() <= 2 || currentState.getNBikes() == station.getCapacity()) 
			{			
				int durationState = nextState.getTime() - currentState.getTime() ;	
				criticalTime += durationState;
			}
					
		}			
		
		if(criticalTime >= minimalCriticalTime)
		{
			return true;
		}
		return false;
	}

	
}
