package com.devket.workout.repository;

import com.devket.workout.domain.ExerciseTarget;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  //tcTODO
 *
 *  @author Tom Cross
 *  @version $Revision: #1 $ submitted $DateTime: 2013/08/28 10:06:00 $ by $Author: CROSTA $
 */

public interface ExerciseTargetRepository extends JpaRepository<ExerciseTarget, Long> {

    ExerciseTarget findByTargetCode(String targetCode);
}
