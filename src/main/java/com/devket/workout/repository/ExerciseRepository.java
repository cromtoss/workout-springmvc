package com.devket.workout.repository;

import com.devket.workout.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * tcTODO
 *
 * @author Tom Cross
 * @version $Revision: #1 $ submitted $DateTime: 2013/09/16 09:32:00 $ by $Author: CROSTA $
 */

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Exercise findByName(String name);
}
