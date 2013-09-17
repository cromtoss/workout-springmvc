package com.devket.workout.service;

import com.devket.workout.domain.Exercise;
import com.devket.workout.exception.ExerciseNotFoundException;

import java.util.List;

/**
 * tcTODO
 *
 * @author Tom Cross
 * @version $Revision: #1 $ submitted $DateTime: 2013/09/16 09:32:00 $ by $Author: CROSTA $
 */
public interface ExerciseService {

    Exercise create(Exercise exercise);

    Exercise delete(long id) throws ExerciseNotFoundException;

    List<Exercise> findAll();

    Exercise findById(long id);

    Exercise findByName(String name);

    Exercise update(Exercise exercise) throws ExerciseNotFoundException;
}
