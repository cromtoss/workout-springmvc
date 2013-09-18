/*	$Header: $
 *
 *	Copyright © 2013 Pearson VUE. All rights reserved.
 */

package com.devket.workout.controller;

import com.devket.workout.importer.Importer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *  Controller for basic navigation. Essentially a placeholder at this point.
 *
 *  @author Tom Cross
 *  @version $Revision: #1 $ submitted $DateTime: 2013/09/16 09:32:00 $ by $Author: CROSTA $	
 */

@Controller
public final class NavigationController{

	@Autowired
	private Importer importer;


	@RequestMapping(value={"/emberStart"}, method=RequestMethod.GET)
	public String getEmberStart() {
		return "emberStart";
	}

	@RequestMapping(value={"/", "/index"}, method=RequestMethod.GET)
	public String getIndex(ModelMap modelMap) {
		return "index";
	}
}
