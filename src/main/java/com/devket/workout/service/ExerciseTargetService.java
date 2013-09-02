package com.devket.workout.service;

import com.devket.workout.domain.Exercise;
import com.devket.workout.domain.ExerciseTarget;
import com.devket.workout.exception.ExerciseNotFoundException;
import com.devket.workout.exception.ExerciseTargetNotFoundException;

import java.util.List;

/**
 * //tcTODO
 *
 * @author Tom Cross
 * @version $Revision: #1 $ submitted $DateTime: 2013/08/28 10:06:00 $ by $Author: CROSTA $
 */
public interface ExerciseTargetService {

    public ExerciseTarget create(ExerciseTarget exerciseTarget);
    public ExerciseTarget delete(long id) throws ExerciseTargetNotFoundException;
    public List<ExerciseTarget> findAll();
    public ExerciseTarget findById(long id);
    public ExerciseTarget findByTargetCode(String targetCode);
    public ExerciseTarget update(ExerciseTarget exerciseTarget) throws ExerciseTargetNotFoundException;
}
