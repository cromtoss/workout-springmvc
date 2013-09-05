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

    Exercise create(Exercise exercise);

    Exercise delete(long id) throws ExerciseNotFoundException;

    List<Exercise> findAll();

    Exercise findById(long id);

    Exercise findByName(String name);

    Exercise update(Exercise exercise) throws ExerciseNotFoundException;
}
