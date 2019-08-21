package engines;

import static constants.Constants.benchPress;
import static constants.Constants.burpees;
import static constants.Constants.deadlift;
import static constants.Constants.dumbellFly;
import static constants.Constants.militaryPress;
import static constants.Constants.pushups;
import static constants.Constants.run;
import static constants.Constants.situps;
import static constants.Constants.sprints;
import static constants.Constants.squats;
import static constants.Constants.warmUpJog;

import java.util.ArrayList;

public class CompileWorkoutEngine {

	int fitGoalsIndex;
	int weightGoalsIndex;

	ArrayList<Exercise> workout = new ArrayList<Exercise>();

	public CompileWorkoutEngine(int[] i) {

		this.fitGoalsIndex = i[0];
		this.weightGoalsIndex = i[1];

	}

	public interface Exercise {

		String getDataHtml();

	}

	class WeightedExercise implements Exercise {
		String exerciseId;
		int sets;
		int reps;
		int weight; //pounds
		double rest; //mins between sets

		WeightedExercise(String eID, int s, int r, int w, double rst) {
			exerciseId = eID;
			sets = s;
			reps = r;
			weight = w;
			rest = rst;
		}

		@Override
		public String getDataHtml() {
			return "<tr><td>" + exerciseId + "</td><td>" + String.valueOf(sets) + "</td><td>" + String.valueOf(reps)
					+ " reps</td><td>" + String.valueOf(weight) + " lbs.</td><td>" + String.valueOf(rest)
					+ " mins.</td></tr>";
		}
	}

	class CardioExercise implements Exercise {
		String exerciseId;
		int sets;
		double distance; //miles
		int time; //total mins
		double rest; //mins between sets

		CardioExercise(String eID, int set, double dist, int t, double rst) {
			exerciseId = eID;
			sets = set;
			distance = dist;
			time = t;
			rest = rst;
		}

		@Override
		public String getDataHtml() {
			return "<tr><td>" + exerciseId + "</td><td>" + String.valueOf(sets) + "</td><td>" + String.valueOf(distance)
					+ " mile(s)</td><td>" + String.valueOf(time) + " mins.</td><td>" + String.valueOf(rest)
					+ " mins.</td></tr>";
		}
	}

	/*
	 * Select/calculate a workout based on the user's goals.
	 */
	public ArrayList<Exercise> compileWorkout() {

		int i = weightGoalsIndex;

		switch (fitGoalsIndex) {
			case 0:
				if(i != 5) { //lose weight
					workout.add(new WeightedExercise(pushups, 1 + i, 24 / i, 0, i / 2));
					workout.add(new WeightedExercise(situps, 2 + i, 30 / i, 0, i / 2)); //3, 4, or 5 sets, 30, 15, or 10 reps, .5, 1, or 1.5 mins rest
					workout.add(new WeightedExercise(burpees, 1 + i, 24 / i, 0, i / 2));
					workout.add(new WeightedExercise(benchPress, 1 + i, 12 / i, 45, i / 2)); //2, 3, or 4 sets, 12, 6 or 4 reps
					workout.add(new WeightedExercise(squats, 1 + i, 12 / i, 45, i / 2));
					workout.add(new CardioExercise(run, 1, 6 / i, (6 / i) * (10 - i), 0));
				} else { //maintain weight
					workout.add(new WeightedExercise(pushups, 3, 10, 0, 1.5));
					workout.add(new WeightedExercise(situps, 3, 20, 0, 1.5));
					workout.add(new WeightedExercise(burpees, 3, 10, 0, 1.5));
					workout.add(new WeightedExercise(benchPress, 3, 8, 45, 1.5));
					workout.add(new WeightedExercise(squats, 3, 8, 45, 1.5));
					workout.add(new CardioExercise(run, 1, 3, 30, 0));
				}
				break;
			case 5:
				workout.add(new CardioExercise(warmUpJog, 1, .5, 5, 0));
				workout.add(new WeightedExercise(situps, 3, 20, 0, 1));
				workout.add(new WeightedExercise(benchPress, 3, 10, 95, 1));
				workout.add(new WeightedExercise(dumbellFly, 3, 10, 70, 1));
				workout.add(new WeightedExercise(militaryPress, 3, 10, 70, 1));
				workout.add(new WeightedExercise(squats, 3, 10, 135, 1));
				workout.add(new CardioExercise(sprints, 3, .25, 2, 2));
				break;
			case 10:
				workout.add(new WeightedExercise(benchPress, i, 15 - i, 20 * i, i / 4));
				workout.add(new WeightedExercise(deadlift, i, 15 - i, 20 * i, i / 4));
				workout.add(new WeightedExercise(squats, i, 15 - i, 20 * i, i / 4));
				break;
		}
		return workout;
	}

	/*
	 * Returns an html markup of the workout so that it can be displayed as a table.
	 */
	public String getWorkoutHTML(ArrayList<Exercise> wkout) {
		StringBuilder stringBuilder = new StringBuilder(
				"<html><style> table {font-family: arial, sans-serif; border-collapse: collapse; width: 100%;} "
						+ "td, th { border: 1px solid #dddddd; text-align: left; padding: 2px;} </style>"
						+ "<body><table><tr><th>Exercise</th><th>Sets</th><th>Reps/Dist.</th><th>Weight/Time</th><th>Rest</th></tr>");

		for(Exercise exercise : workout) {
			stringBuilder.append(exercise.getDataHtml());
		}

		stringBuilder.append("</table></body></html>");
		return stringBuilder.toString();
	}
}
