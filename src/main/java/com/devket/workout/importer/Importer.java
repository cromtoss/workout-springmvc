/*	$Header: $
 *
 *	Copyright © 2013 Pearson VUE. All rights reserved.
 */

package com.devket.workout.importer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 *  //tcTODO
 *
 *  @author Tom Cross
 *  @version $Revision: #1 $ submitted $DateTime: 2013/08/28 10:06:00 $ by $Author: CROSTA $	
 */
public final class Importer {

	//tcTODO rename from main -- temp for debugging
	public static final void main(String[] args) throws IOException {

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

		// tcTODO first, wire up with web app... need to use Spring Data to access Repository.
		// use the DTO list to import the data in appropriate table order:
		// 1. Targets 2. Exercises

	}

}
