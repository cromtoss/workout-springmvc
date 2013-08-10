package com.devket.workout.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
