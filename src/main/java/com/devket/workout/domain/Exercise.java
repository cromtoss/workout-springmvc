package com.devket.workout.domain;

import javax.persistence.*;
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
    private String description;

    @ManyToMany
    @JoinTable(
        name="Exercise_ExerciseTarget",
        joinColumns={@JoinColumn(name="ExerciseID", referencedColumnName="id")},
        inverseJoinColumns={@JoinColumn(name="ExerciseTargetID", referencedColumnName="id")}
    )
    private List<ExerciseTarget> exerciseTarget;

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
