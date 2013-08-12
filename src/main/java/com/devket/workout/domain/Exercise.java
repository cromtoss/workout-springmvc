package com.devket.workout.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
	private String imagePath;

	@ManyToMany
	@JoinTable(
		name = "Exercise_ExerciseTarget",
		joinColumns = {
			@JoinColumn(name = "ExerciseID", referencedColumnName = "id")
		},
		inverseJoinColumns = {
			@JoinColumn(name = "ExerciseTargetID", referencedColumnName = "id")
		}
	)
	private List<ExerciseTarget> exerciseTarget;


	public String getDescription() {
		return description;
	}


	public List<ExerciseTarget> getExerciseTarget() {
		return exerciseTarget;
	}


	public long getId() {
		return id;
	}


	public String getImagePath() {
		return imagePath;
	}


	public String getName() {
		return name;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setExerciseTarget(List<ExerciseTarget> exerciseTarget) {
		this.exerciseTarget = exerciseTarget;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public void setName(String name) {
		this.name = name;
	}
}