package guru.springframework.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;

public class NotesCommandToNotesTest {

	public static final Long ID_VALUE = new Long(1L);
	public static final String RECIPE_NOTES = "Notes";

	NotesCommandToNotes converter;

	@Before
	public void setUp() {
		converter = new NotesCommandToNotes();
	}

	@Test
	public void testNullParameters() {
		assertNull(null);
	}

	@Test
	public void testEmptyParameters() {
		assertNotNull(new NotesCommand());
	}

	@Test
	public void testConverter() {
		// given
		NotesCommand notesCommand = new NotesCommand();
		notesCommand.setId(ID_VALUE);
		notesCommand.setRecipeNotes(RECIPE_NOTES);

		// when
		Notes notes = converter.convert(notesCommand);

		// then
		assertNotNull(notes);
		assertEquals(ID_VALUE, notes.getId());
		assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
	}

}
