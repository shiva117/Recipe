package guru.springframework.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;

public class UnitOfMeasureCommandToUnitOfMeasureTest {
	
	public static final Long LONG_ID = new Long(1L);
	public static final String DESCRIPTION = "some Description";
	
	UnitOfMeasureCommandToUnitOfMeasure converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new UnitOfMeasureCommandToUnitOfMeasure();
	}

	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyParameter() {
		assertNotNull(converter.convert(new UnitOfMeasureCommand()));
	}
	
	@Test
	public void testConverter() {
		/* given */
		UnitOfMeasureCommand input = new UnitOfMeasureCommand();
		input.setId(LONG_ID);
		input.setDescription(DESCRIPTION);
		
		/*when*/
		UnitOfMeasure result = converter.convert(input);
		
		/*then*/
		assertNotNull(result);
		assertEquals(LONG_ID, result.getId());
		assertEquals(DESCRIPTION,result.getDescription());
	}

}
