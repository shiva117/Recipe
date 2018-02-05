package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RecipeController {

	RecipeService recipeService;

	public RecipeController(RecipeService service) {
		this.recipeService = service;
	}

	@RequestMapping("/recipe/show/{id}")
	public String getById(@PathVariable("id") String id, Model model) {
		log.debug("Request ID is : " + id);
		model.addAttribute("recipe", recipeService.getById(new Long(id)));
		return "recipe/show";
	}

}
