package com.devket.wotd.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

/**
 * tcTODO
 *
 * Created: 7/21/13 5:28 PM
 */

@Entity
public class Exercise {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private List<ExerciseTarget> exerciseTarget;
    private String description;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExerciseTarget> getExerciseTarget() {
        return exerciseTarget;
    }

    public void setExerciseTarget(List<ExerciseTarget> exerciseTarget) {
        this.exerciseTarget = exerciseTarget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
