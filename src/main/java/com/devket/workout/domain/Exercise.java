package com.devket.workout.domain;

import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.persistence.*;

/**
 * tcTODO
 *
 * @author Tom Cross
 * @version $Revision: #1 $ submitted $DateTime: 2013/09/16 09:32:00 $ by $Author: CROSTA $
 */

@Entity
public class Exercise {

	@Id
	@GeneratedValue
	private long id;

    @Column(unique = true)
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