package com.devket.workout.domain;

import javax.persistence.*;
import java.util.List;

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
        name="Workout_Exercise",
        joinColumns={@JoinColumn(name="WorkoutID", referencedColumnName="id")},
        inverseJoinColumns={@JoinColumn(name="ExerciseID", referencedColumnName="id")}
    )
	private List<Exercise> exercises;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
