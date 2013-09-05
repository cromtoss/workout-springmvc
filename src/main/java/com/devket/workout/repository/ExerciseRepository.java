package com.devket.workout.repository;

import com.devket.workout.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * tcTODO
 * <p/>
 * Created: 8/3/13 3:18 PM
 */

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Exercise findByName(String name);
}
