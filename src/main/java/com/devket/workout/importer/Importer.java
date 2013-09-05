/*	$Header: $
 *
 *	Copyright © 2013 Pearson VUE. All rights reserved.
 */

package com.devket.workout.importer;

import com.devket.workout.domain.Exercise;
import com.devket.workout.domain.ExerciseTarget;
import com.devket.workout.service.ExerciseService;
import com.devket.workout.service.ExerciseTargetService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 *  Object that knows how to populate an empty database with data from a JSON file
 *  supplied on the classpath.
 *
 *  @author Tom Cross
 *  @version $Revision: #1 $ submitted $DateTime: 2013/08/28 10:06:00 $ by $Author: CROSTA $	
 */
public final class Importer {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseTargetService exerciseTargetService;

	//tcTODO link up with controller/web framework
	public void doImport() throws IOException {

		// read exercises.json from classpath
		final Resource jsonResource = new ClassPathResource("exercises.json");

		// use Gson to create a list of DTOs from the json in the file
		final JsonReader jsonReader = new JsonReader(new InputStreamReader(jsonResource.getInputStream()));

		final Gson gson = new Gson();
		final JsonParser jsonParser = new JsonParser();
		final JsonArray jsonElements = jsonParser.parse(jsonReader).getAsJsonArray();

		final List<ExerciseImportDTO> exercisesToImport = new ArrayList<>();
		for (JsonElement element : jsonElements) {
			exercisesToImport.add(gson.fromJson(element, ExerciseImportDTO.class));
		}

		// use the DTO list to import the data in appropriate table order:
		// 1. Targets 2. Exercises
        // tcTODO add ability to update existing DB records??

        for (ExerciseImportDTO anExercise : exercisesToImport) {

            // Build the list of ExerciseTarget objects for this exercise.
            final List<ExerciseTarget> exerciseTargets = new ArrayList<>();

            for (String aCode : anExercise.getTargets()) {
                // Does this target exist in the DB? if not, create it.
                final ExerciseTarget foundTarget = exerciseTargetService.findByTargetCode(aCode);
                if (null == foundTarget) {
                    final ExerciseTarget newTarget = new ExerciseTarget();
                    newTarget.setTargetCode(aCode);
                    newTarget.setTargetDescription(aCode);
                    exerciseTargetService.create(newTarget);
                    exerciseTargets.add(newTarget);
                } else {
                    exerciseTargets.add(foundTarget);
                }
            }

            // Does this exercise exist in the DB? if not, create it.
            final Exercise foundExercise = exerciseService.findByName(anExercise.getName());
            if (null == foundExercise) {
                // make new exercise.
                final Exercise newExercise = new Exercise();
                newExercise.setName(anExercise.getName());
                newExercise.setExerciseTarget(exerciseTargets);
                newExercise.setDescription(anExercise.getDescription());
                newExercise.setImagePath(anExercise.getImagePath());

                exerciseService.create(newExercise);
            }

        }

	}

}
