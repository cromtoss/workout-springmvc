package com.devket.wotd.domain;

import javax.persistence.Entity;
import java.util.List;

/**
 * tcTODO
 *
 * Created: 7/21/13 5:28 PM
 */

@Entity
public class Workout {

    private long id;
	private List<Exercise> exercises;
}
