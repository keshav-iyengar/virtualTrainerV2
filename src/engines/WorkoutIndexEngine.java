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
public class WorkoutIndexEngine {

	HashMap<String, HashMap<String, Boolean>> selectedValues;
	int currentWeight;
	int goalWeight;

	// lose weight/fat
	// gain weight/mass

	// gain strength
	// gain endurance

	// 1 2 3 4 5 6 7 8 9 10
	// LW/GE LW/GS;GW/GE GW/GS

	public WorkoutIndexEngine(HashMap<String, HashMap<String, Boolean>> values, int cWeight, int gWeight) {

		this.selectedValues = values;
		this.currentWeight = cWeight;
		this.goalWeight = gWeight;

		int category = determineCategory(getFitnessGoalsIndex(), getWeightGoalsIndex());
		System.out.println("Category is: " + category);
		CompileWorkoutEngine compileWorkoutEngine = new CompileWorkoutEngine(category);

	}

	public int determineCategory(int fitIndex, int weightIndex) {

		return (fitIndex * 10) + weightIndex;

	}

	/*
	 * Calculate the user's fitness index by averaging aesthetic and performance
	 * goals.
	 */
	public int getFitnessGoalsIndex() {

		int aestheticIndex = 0, performanceIndex = 0;
		boolean aestheticNa = false, performanceNa = false;
		boolean selection; // user's responses

		for(String key : this.selectedValues.keySet()) {

			for(String k : selectedValues.get(key).keySet()) {

				selection = selectedValues.get(key).get(k);

				switch (k) {

					case buildMassKey:
						if(selection == true) aestheticIndex = 10;
						break;
					case burnFatKey:
						if(selection == true) aestheticIndex = 0;
						break;
					case gainStrengthKey:
						if(selection == true) performanceIndex = 10;
						break;
					case gainEnduranceKey:
						if(selection == true) performanceIndex = 0;
						break;
					case naKey:
						if(selection == true && key.equals(aestheticGoalsKey)) {
							aestheticNa = true;
						}
						if(selection == true && key.equals(performanceGoalsKey)) {
							performanceNa = true;
						}
						break;
				}

			}

		}

		if(aestheticNa == true) return performanceIndex;
		else if(performanceNa == true) return aestheticIndex;
		else return (aestheticIndex + performanceIndex) / 2;

	}

	public int getWeightGoalsIndex() {

		int difference = this.goalWeight - this.currentWeight;
		int netDiff = Math.abs(difference);

		if(difference < 0) { //lose weight

			if(netDiff <= 10) return 2;
			else if(netDiff <= 20) return 1;
			else return 0;

		} else if(difference > 0) { //gain weight

			if(netDiff <= 10) return 8;
			else if(netDiff <= 20) return 9;
			else return 10;

		} else return 5; //maintain weight

	}

}
