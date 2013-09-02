package com.devket.workout.service;

import com.devket.workout.domain.ExerciseTarget;
import com.devket.workout.exception.ExerciseTargetNotFoundException;
import com.devket.workout.repository.ExerciseTargetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * //tcTODO
 *
 * @author Tom Cross
 * @version $Revision: #1 $ submitted $DateTime: 2013/08/28 10:06:00 $ by $Author: CROSTA $
 */

@Service
public class ExerciseTargetServiceImpl implements ExerciseTargetService {

    @Resource
    private ExerciseTargetRepository repository;

    @Override
    @Transactional
    public ExerciseTarget create(ExerciseTarget exerciseTarget) {
        return repository.save(exerciseTarget);
    }

    @Override
    @Transactional(rollbackFor = ExerciseTargetNotFoundException.class)
    public ExerciseTarget delete(long id) throws ExerciseTargetNotFoundException {
        final ExerciseTarget toDelete = repository.findOne(id);

        if (toDelete == null) {
            throw new ExerciseTargetNotFoundException();
        }

        repository.delete(toDelete);
        return toDelete;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseTarget> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ExerciseTarget findById(long id) {
        return repository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ExerciseTarget findByTargetCode(String targetCode) {
        return repository.findByTargetCode(targetCode);
    }

    @Override
    @Transactional(rollbackFor = ExerciseTargetNotFoundException.class)
    public ExerciseTarget update(ExerciseTarget exerciseTarget) throws ExerciseTargetNotFoundException {
        //tcTODO add JSR 330 validations to guarantee that ID is never null? but what if the object was created by the app?
        final ExerciseTarget toUpdate = repository.findOne(exerciseTarget.getId());

        if (toUpdate == null) {
            throw new ExerciseTargetNotFoundException();
        }

        toUpdate.setTargetCode(exerciseTarget.getTargetCode());
        toUpdate.setTargetDescription(exerciseTarget.getTargetDescription());

        return toUpdate;
    }
}
