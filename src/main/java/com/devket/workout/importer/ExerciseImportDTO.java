/*	$Header: $
 *
 *	Copyright © 2013 Pearson VUE. All rights reserved.
 */

package com.devket.workout.importer;

import java.util.List;

/**
 *  A simple DTO type object to store records imported from JSON.
 *
 *  @author Tom Cross
 *  @version $Revision: #1 $ submitted $DateTime: 2013/08/12 10:54:00 $ by $Author: CROSTA $	
 */
public final class ExerciseImportDTO {

	private String name;
	private List<String> targets;
	private String description;
	private String imagePath;


	public String getName() {
		return name;
	}


	public List<String> getTargets() {
		return targets;
	}


	public String getDescription() {
		return description;
	}


	public String getImagePath() {
		return imagePath;
	}
}
