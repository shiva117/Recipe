package guru.springframework.services;

import java.util.Set;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeService {

	Set<Recipe> getRecipes();

	Recipe getById(Long id);
	
	RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
