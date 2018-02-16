package guru.springframework.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.net.ssl.SSLEngineResult.Status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;

public class RecipeControllerTest {

	@Mock
	RecipeService recipeService;

	RecipeController recipeController;

	MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		recipeController = new RecipeController(recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
	}

	@Test
	public void testGetRecipe() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		when(recipeService.getById(anyLong())).thenReturn(recipe);

		mockMvc.perform(get("/recipe/1/show"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("recipe/show"))
			   .andExpect(model().attributeExists("recipe"));
	}
	
	
	@Test
	public void testGetNewRecipeForm() throws Exception{
		RecipeCommand command = new RecipeCommand();
		
		mockMvc.perform(get("/recipe/new"))
		       .andExpect(status().isOk())
		       .andExpect(view().name("recipe/recipeform"))
		       .andExpect(model().attributeExists("recipe"));
	}
	
	@Test
	public void testPostNewRecipeForm()throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId(2L);
		
		
		when(recipeService.saveRecipeCommand(any())).thenReturn(command);
		
		mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id", "").param("description", "some description"))
			   .andExpect(status().is3xxRedirection())
			   .andExpect(view().name("redirect:/recipe/2/show"));
	}
	
	@Test
	public void testGetUpdateView() throws Exception{
		RecipeCommand command = new RecipeCommand();
		command.setId(2L);
		
		when(recipeService.findCommandById(anyLong())).thenReturn(command);
		
		mockMvc.perform(get("/recipe/1/update"))
		       .andExpect(status().isOk())
		       .andExpect(view().name("recipe/recipeform"))
		       .andExpect(model().attributeExists("recipe"));
	}

	@Test
	public void testGetById() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		when(recipeService.getById(anyLong())).thenReturn(recipe);

		mockMvc.perform(get("/recipe/1/show")).andExpect(status().isOk()).andExpect(view().name("recipe/show"));

	}

}
