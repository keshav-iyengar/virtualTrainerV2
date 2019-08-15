package engines;

import static constants.Constants.aestheticGoalsKey;
import static constants.Constants.buildMassKey;
import static constants.Constants.burnFatKey;
import static constants.Constants.gainEnduranceKey;
import static constants.Constants.gainStrengthKey;
import static constants.Constants.naKey;
import static constants.Constants.performanceGoalsKey;

import java.util.HashMap;

/*
 * Calculates a score based on user workout preferences to output a workout routine.
 */
public class CompileWorkoutEngine {

	HashMap<String, HashMap<String, Boolean>> selectedValues;

	// lose weight/fat
	// gain weight/mass

	// gain strength
	// gain endurance

	// 1 2 3 4 5 6 7 8 9 10
	// LW/GE LW/GS;GW/GE GW/GS

	public CompileWorkoutEngine(HashMap<String, HashMap<String, Boolean>> values) {

		selectedValues = values;

	}

	/*
	 * Calculate the user's fitness index by averaging aesthetic and performance
	 * goals.
	 */
	public double getFitnessIndex() {

		double aestheticIndex = 0;
		double performanceIndex = 0;
		boolean selection; // user's responses
		HashMap<String, Boolean> naSelections = new HashMap<String, Boolean>() {
			{
				put(aestheticGoalsKey, false);
				put(performanceGoalsKey, false);
			}
		};
		int validResponseCounter = 0; //no selections == invalid form

		for(String key : selectedValues.keySet()) {

			for(String k : selectedValues.get(key).keySet()) {

				selection = selectedValues.get(key).get(k);

				switch (k) {

					case buildMassKey:
						if(selection == true) {
							aestheticIndex = 10;
							validResponseCounter++;
						}
					case burnFatKey:
						if(selection == true) {
							aestheticIndex = 1;
							validResponseCounter++;
						}
					case gainStrengthKey:
						if(selection == true) {
							performanceIndex = 10;
							validResponseCounter++;
						}
					case gainEnduranceKey:
						if(selection == true) {
							performanceIndex = 1;
							validResponseCounter++;
						}
					case naKey:
						if(selection == true && key.equals(aestheticGoalsKey)) {
							naSelections.put(aestheticGoalsKey, true);
							validResponseCounter++;
						}
						if(selection == true && key.equals(performanceGoalsKey)) {
							naSelections.put(performanceGoalsKey, true);
							validResponseCounter++;
						}

				}

			}

		}

		if(validResponseCounter == 2) {
			if(naSelections.get(aestheticGoalsKey).equals(true)) {
				if(naSelections.get(performanceGoalsKey).equals(true)) return -1;//error - 2 na's chosen or insufficient responses
				else return performanceIndex;
			} else {
				if(naSelections.get(performanceGoalsKey).equals(true)) return aestheticIndex;
				else return (aestheticIndex + performanceIndex) / 2;
			}
		} else return -1;

	}

}
