package com.devket.workout.service;

import com.devket.workout.domain.Exercise;
import com.devket.workout.exception.ExerciseNotFoundException;
import com.devket.workout.repository.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * tcTODO
 *
 * @author Tom Cross
 * @version $Revision: #1 $ submitted $DateTime: 2013/09/16 09:32:00 $ by $Author: CROSTA $
 */

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Resource
    private ExerciseRepository exerciseRepository;

    @Override
    @Transactional
    public Exercise create(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    @Transactional(rollbackFor = ExerciseNotFoundException.class)
    public Exercise delete(long id) throws ExerciseNotFoundException {
        final Exercise toDelete = exerciseRepository.findOne(id);

        if (toDelete == null) {
            throw new ExerciseNotFoundException();
        }

        exerciseRepository.delete(toDelete);
        return toDelete;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    @Transactional
    public Exercise findById(long id) {
        return exerciseRepository.findOne(id);
    }

    @Override
    @Transactional(rollbackFor = ExerciseNotFoundException.class)
    public Exercise update(Exercise exercise) throws ExerciseNotFoundException {
        final Exercise found = exerciseRepository.findOne(exercise.getId());

        if (found == null) {
            throw new ExerciseNotFoundException();
        }

        found.setDescription(exercise.getDescription());
        found.setExerciseTarget(exercise.getExerciseTarget());
        found.setName(exercise.getName());

        return found;
    }

    @Override
    @Transactional(readOnly = true)
    public Exercise findByName(String name) {
        return exerciseRepository.findByName(name);
    }
}
