package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

	private final IngredientToIngredientCommand ingredientConverter;
	private final CategoryToCategoryCommand categoryConverter;
	private final NotesToNotesCommand notesConverter;

	public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientConverter,
			CategoryToCategoryCommand categoryConverter, NotesToNotesCommand notesConverter) {
		super();
		this.ingredientConverter = ingredientConverter;
		this.categoryConverter = categoryConverter;
		this.notesConverter = notesConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		if (source == null) {
			return null;
		}
		final RecipeCommand target = new RecipeCommand();
		target.setId(source.getId());
		target.setDescription(source.getDescription());
		target.setCookTime(source.getCookTime());
		target.setDifficulty(source.getDifficulty());
		target.setDirections(source.getDirections());
		target.setPrepTime(source.getPrepTime());
		target.setServings(source.getServings());
		target.setSource(source.getSource());
		target.setUrl(source.getUrl());

		target.setNotes(notesConverter.convert(source.getNotes()));

		if (source.getIngredients() != null && source.getIngredients().size() > 0) {
			source.getIngredients().forEach(ingredient -> target.getIngredients().add(ingredientConverter.convert(ingredient)));
		}

		if (source.getCategories() != null && source.getCategories().size() > 0) {
			source.getCategories().forEach(category -> target.getCategories().add(categoryConverter.convert(category)));
		}
		return target;
	}

}
