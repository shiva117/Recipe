package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	private final IngredientCommandToIngredient ingredientConverter;
	private final NotesCommandToNotes noteConverter;
	private final CategoryCommandToCategory categoryConverter;

	public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientConverter, NotesCommandToNotes noteConverter,
			CategoryCommandToCategory categoryConverter) {
		super();
		this.ingredientConverter = ingredientConverter;
		this.noteConverter = noteConverter;
		this.categoryConverter = categoryConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand source) {
		if (source == null) {
			return null;
		}
		final Recipe target = new Recipe();
		target.setId(source.getId());
		target.setDescription(source.getDescription());
		target.setCookTime(source.getCookTime());
		target.setDifficulty(source.getDifficulty());
		target.setDirections(source.getDirections());
		target.setPrepTime(source.getPrepTime());
		target.setServings(source.getServings());
		target.setSource(source.getSource());
		target.setUrl(source.getUrl());
		target.setNotes(noteConverter.convert(source.getNotes()));

		if (source.getIngredients() != null && source.getIngredients().size() > 0) {
			source.getIngredients()
					.forEach(ingradient -> target.getIngredients().add(ingredientConverter.convert(ingradient)));
		}

		if (source.getCategories() != null && source.getCategories().size() > 0) {
			source.getCategories().forEach(category -> target.getCategories().add(categoryConverter.convert(category)));
		}
		return target;
	}

}
