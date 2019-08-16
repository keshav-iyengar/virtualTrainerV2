package engines;

import static constants.Constants.aestheticGoalsKey;
import static constants.Constants.buildMassKey;
import static constants.Constants.burnFatKey;
import static constants.Constants.currentWeightKey;
import static constants.Constants.gainEnduranceKey;
import static constants.Constants.gainStrengthKey;
import static constants.Constants.goalWeightKey;
import static constants.Constants.naKey;
import static constants.Constants.performanceGoalsKey;

import java.util.HashMap;

/*
 * Calculates a score based on user workout preferences to output a workout routine.
 */
public class WorkoutIndexEngine {

	HashMap<String, HashMap<String, Boolean>> selectedValues;
	HashMap<String, Double> weightData;
	int age;

	// lose weight/fat
	// gain weight/mass

	// gain strength
	// gain endurance

	// 1 2 3 4 5 6 7 8 9 10
	// LW/GE LW/GS;GW/GE GW/GS

	public WorkoutIndexEngine(HashMap<String, HashMap<String, Boolean>> values, HashMap<String, Double> wtData, int a) {

		this.selectedValues = values;
		this.weightData = wtData;
		this.age = a;

		int category = determineCategory(getFitnessGoalsIndex(), getWeightGoalsIndex(), getAgeIndex());
		CompileWorkoutEngine compileWorkoutEngine = new CompileWorkoutEngine(category);

	}

	public int determineCategory(int fitIndex, int weightIndex, int ageIndex) {

		if(fitIndex == -1 || weightIndex == -1 || ageIndex == -1) return -1;
		else return (fitIndex * 100) + (weightIndex * 10) + ageIndex;

	}

	/*
	 * Calculate the user's fitness index by averaging aesthetic and performance
	 * goals.
	 */
	public int getFitnessGoalsIndex() {

		int aestheticIndex = 0;
		int performanceIndex = 0;
		boolean selection; // user's responses
		HashMap<String, Boolean> naSelections = new HashMap<String, Boolean>() {
			{
				put(aestheticGoalsKey, false);
				put(performanceGoalsKey, false);
			}
		};
		int validResponseCounter = 0; //no selections == invalid form

		for(String key : this.selectedValues.keySet()) {

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
							aestheticIndex = 0;
							validResponseCounter++;
						}
					case gainStrengthKey:
						if(selection == true) {
							performanceIndex = 10;
							validResponseCounter++;
						}
					case gainEnduranceKey:
						if(selection == true) {
							performanceIndex = 0;
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
					default:
						return -1;

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

	public int getWeightGoalsIndex() {

		double currentWeight = this.weightData.get(currentWeightKey);
		double goalWeight = this.weightData.get(goalWeightKey);

		double difference = goalWeight - currentWeight;
		double netDiff = Math.abs(difference);

		//build mass and lose weight (and vice versa) is invalid
		boolean isValidInput = (selectedValues.get(aestheticGoalsKey).get(buildMassKey) && (difference < 0)) || (selectedValues.get(aestheticGoalsKey).get(burnFatKey) && (difference > 0)) ? false
				: true;

		if(isValidInput) {

			if(difference < 0) { //lose weight

				if(netDiff <= 10) return 2;
				else if(netDiff <= 20) return 1;
				else return 0;

			} else if(difference > 0) { //gain weight

				if(netDiff <= 10) return 8;
				else if(netDiff <= 20) return 9;
				else return 10;

			} else return 5; //maintain weight

		} else return -1;

	}

	public int getAgeIndex() {

		if(this.age <= 30) return 3;
		else if(this.age <= 50) return 2;
		else return 1;

	}

}
