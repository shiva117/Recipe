package guru.springframework.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

	public static final Long LONG_ID = new Long(1L);
	public static final String DESCRIPTION = "some Description";

	UnitOfMeasureToUnitOfMeasureCommand converter;

	@Before
	public void setUp() {
		converter = new UnitOfMeasureToUnitOfMeasureCommand();
	}

	@Test
	public void testNullParameters() {
		assertNull(converter.convert(null));
	}

	@Test
	public void testEmptyParameters() {
		assertNotNull(converter.convert(new UnitOfMeasure()));
	}

	@Test
	public void testConverter() {
		/*given*/
		UnitOfMeasure input = new UnitOfMeasure();
		input.setId(LONG_ID);
		input.setDescription(DESCRIPTION);
		
		/*when*/
		UnitOfMeasureCommand result = converter.convert(input);
		
		/*then*/
		assertEquals(LONG_ID, result.getId());
		assertEquals(DESCRIPTION,result.getDescription());
	}

}
