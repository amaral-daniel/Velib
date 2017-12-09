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
	
	
	public static boolean isCritical(Station stationAEvaluer)
	{

		ArrayList<State> stationStates = stationAEvaluer.getStates();
		int criticalTime = 0;
		
		//loop running through all States j of the selected Station i
		for	(int j; j < stationStates.size() - 1; j++) {
			
			State currentState = stationStates.get(j);
					
			//check if the station i is almost empty or full in State j
			if (currentState.getBikes() == < 2 || currentState.getBikes() == currentStation.getCapacity()) 
			{			
				State nextState = stationStates.get(j + 1);
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
