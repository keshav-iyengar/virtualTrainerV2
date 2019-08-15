package engines;

import static constants.Constants.buildMassKey;
import static constants.Constants.burnFatKey;
import static constants.Constants.gainEnduranceKey;
import static constants.Constants.gainStrengthKey;

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

		double aestheticScore = 0;
		double performanceScore = 0;
		boolean selection; // user's responses

		for(String key : selectedValues.keySet()) {

			for(String k : selectedValues.get(key).keySet()) {

				selection = selectedValues.get(key).get(k);

				switch (k) {

					case buildMassKey:
						if(selection == true) aestheticScore = 10;
					case burnFatKey:
						if(selection == true) aestheticScore = 1;
					case gainStrengthKey:
						if(selection == true) performanceScore = 10;
					case gainEnduranceKey:
						if(selection == true) performanceScore = 1;

				}

			}

		}

		return (aestheticScore + performanceScore) / 2;

	}

}
