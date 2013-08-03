package com.devket.wotd.service;

import com.devket.wotd.domain.Exercise;
import com.devket.wotd.exception.ExerciseNotFoundException;
import com.devket.wotd.repository.ExerciseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * tcTODO
 * <p/>
 * Created: 8/3/13 3:39 PM
 */

@Service
public final class ExerciseServiceImpl implements ExerciseService {

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
    @Transactional
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
}
