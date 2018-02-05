package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

	@Override
	public Category convert(CategoryCommand source) {
		if (source == null) {
			return null;
		}
		final Category target = new Category();
		target.setId(source.getId());
		target.setDescription(source.getDescription());
		return target;
	}

}
