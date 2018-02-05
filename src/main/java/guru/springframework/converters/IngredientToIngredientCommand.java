package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

	private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

	public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
		super();
		this.uomConverter = uomConverter;
	}

	@Nullable
	@Override
	public IngredientCommand convert(Ingredient source) {
		if (source == null) {
			return null;
		}
		final IngredientCommand target = new IngredientCommand();
		target.setId(source.getId());
		target.setAmount(source.getAmount());
		target.setDescription(source.getDescription());
		target.setUnitOfMeasure(uomConverter.convert(source.getUom()));
		return target;
	}

}
