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
 * Calculates a score based on user workout preferences to be used to determine a workout routine.
 */
public class WorkoutIndexEngine {

	HashMap<String, HashMap<String, Boolean>> selectedValues;
	int currentWeight;
	int goalWeight;
	int[] workoutIndex;

	//      0    				 5     				  10
	//0 1 2 5 8 9 10       0 1 2 5 8 9 10		0 1 2 5 8 9 10

	public WorkoutIndexEngine(HashMap<String, HashMap<String, Boolean>> values, int cWeight, int gWeight) {

		this.selectedValues = values;
		this.currentWeight = cWeight;
		this.goalWeight = gWeight;

		int[] category = determineCategory(getFitnessGoalsIndex(), getWeightGoalsIndex());
		this.workoutIndex = category;
		CompileWorkoutEngine compileWorkoutEngine = new CompileWorkoutEngine(category);

	}

	public int[] getWorkoutIndex() {
		return this.workoutIndex;
	}

	public int[] determineCategory(int fitIndex, int weightIndex) {

		//return (fitIndex * 10) + weightIndex;
		return new int[] { fitIndex, weightIndex };

	}

	/*
	 * Calculate the user's fitness index by averaging aesthetic and performance
	 * goals.
	 */
	public int getFitnessGoalsIndex() {

		int aestheticIndex = 0, performanceIndex = 0;
		boolean aestheticNa = false, performanceNa = false;
		boolean selected; // user's responses

		for(String key : this.selectedValues.keySet()) {

			for(String k : selectedValues.get(key).keySet()) {

				selected = selectedValues.get(key).get(k);

				switch (k) {

					case buildMassKey:
						if(selected) aestheticIndex = 10;
						break;
					case burnFatKey:
						if(selected) aestheticIndex = 0;
						break;
					case gainStrengthKey:
						if(selected) performanceIndex = 10;
						break;
					case gainEnduranceKey:
						if(selected) performanceIndex = 0;
						break;
					case naKey:
						if(selected && key.equals(aestheticGoalsKey)) {
							aestheticNa = true;
						}
						if(selected && key.equals(performanceGoalsKey)) {
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
