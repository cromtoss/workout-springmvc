package com.devket.workout.service;

import com.devket.workout.domain.Exercise;
import com.devket.workout.exception.ExerciseNotFoundException;

import java.util.List;

/**
 * tcTODO
 * <p/>
 * Created: 8/3/13 3:36 PM
 */
public interface ExerciseService {

    public Exercise create(Exercise exercise);
    public Exercise delete(long id) throws ExerciseNotFoundException;
    public List<Exercise> findAll();
    public Exercise findById(long id);
    public Exercise update(Exercise exercise) throws ExerciseNotFoundException;
}
