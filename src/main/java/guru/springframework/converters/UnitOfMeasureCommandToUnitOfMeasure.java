package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import lombok.Synchronized;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

	@Synchronized /* Spring does not gurentee thread safety*/
	@Nullable
	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand source) {
		if (source == null) {
			return null;
		}

		final UnitOfMeasure target = new UnitOfMeasure();
		target.setId(source.getId());
		target.setDescription(source.getDescription());
		return target;
	}

}
