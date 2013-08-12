package com.devket.workout.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * tcTODO
 *
 * Created: 7/21/13 5:28 PM
 */

@Entity
public class Workout {

	@Id
	@GeneratedValue
	private long id;

	@ManyToMany
	@JoinTable(
		name = "Workout_Exercise",
		joinColumns = {
			@JoinColumn(name = "WorkoutID", referencedColumnName = "id")
		},
		inverseJoinColumns = {
			@JoinColumn(name = "ExerciseID", referencedColumnName = "id")
		}
	)
	private List<Exercise> exercises;


	public List<Exercise> getExercises() {
		return exercises;
	}


	public long getId() {
		return id;
	}


	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}


	public void setId(long id) {
		this.id = id;
	}
}
