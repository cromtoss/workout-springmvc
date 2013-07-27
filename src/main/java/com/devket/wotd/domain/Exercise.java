package com.devket.wotd.domain;

import javax.persistence.Entity;
import java.util.List;

/**
 * tcTODO
 *
 * Created: 7/21/13 5:28 PM
 */

@Entity
public class Exercise {

    private String id;
    private String name;
    private List<ExerciseTarget> exerciseTarget;
    private String description;


}
