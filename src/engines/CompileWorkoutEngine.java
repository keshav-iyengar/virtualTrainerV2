package engines;

import java.util.ArrayList;

public class CompileWorkoutEngine {

	int[] index;

	ArrayList<Exercise> workout = new ArrayList<Exercise>();

	public CompileWorkoutEngine(int[] i) {

		this.index = i;

	}

	interface Exercise {

	}

	class WeightedExercise implements Exercise {
		String exerciseId;
		int sets;
		int reps;
		int weight;
		int rest;

		WeightedExercise(String eID, int s, int r, int w, int rst) {
			exerciseId = eID;
			sets = s;
			reps = r;
			weight = w;
			rest = rst;
		}
	}

	class CardioExercise implements Exercise {
		String exerciseId;
		int distance;
		int time;

		CardioExercise(String eID, int dist, int t) {
			exerciseId = eID;
			distance = dist;
			time = t;
		}
	}

	public void compileWorkout() {

		workout.add(new CardioExercise("Run", 2, 16));
		workout.add(new WeightedExercise("Bench press", 3, 10, 135, 2));

	}
}
