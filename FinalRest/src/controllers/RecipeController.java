package controllers;

import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.RecipeDAO;
import entities.Favorite;
import entities.Ingredient;
import entities.Instruction;
import entities.Rating;
import entities.Recipe;
import entities.RecipeIngredient;
import entities.Tag;

@RestController
public class RecipeController {
	
	@Autowired
	private RecipeDAO recipedao;
	
	@RequestMapping(path = "search/recipe", method = RequestMethod.POST)
	public Collection<Recipe> index(HttpServletRequest req, HttpServletResponse res, @RequestBody String json) {
		
		try {
			return recipedao.index(json);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
  
	@RequestMapping(path = "search/recipe/{rid}", method = RequestMethod.GET)
	public Recipe show(HttpServletRequest req, HttpServletResponse res, @PathVariable int rid){
		return recipedao.show(rid);
	}
  
	@RequestMapping(path = "user/{uid}/recipe", method = RequestMethod.POST)
	public Recipe create(HttpServletRequest req, HttpServletResponse res, @PathVariable int uid, @RequestBody String recipeDtoJson){
		return recipedao.createRecipe(uid, recipeDtoJson);
	}
	
	@RequestMapping(path = "user/{uid}/recipe/rating", method = RequestMethod.POST)
	public Rating createRating(HttpServletRequest req, HttpServletResponse res, @PathVariable int uid, @RequestBody String ratingDtoJson){
		return recipedao.createRating(uid, ratingDtoJson);
	}
  
	@RequestMapping(path = "user/{uid}/recipe/{rid}", method = RequestMethod.PUT)
	public Recipe update(HttpServletRequest req, HttpServletResponse res, @PathVariable int uid, @PathVariable int rid, @RequestBody String recipeJson){
		return recipedao.update(uid, rid, recipeJson);
	}
	
	@RequestMapping(path = "search/recipe/{rid}/recipeIngredient", method = RequestMethod.GET)
	public Collection<RecipeIngredient> showIngredients(HttpServletRequest req, HttpServletResponse res, @PathVariable int rid){
		return recipedao.showIngredients(rid);
	}
	
	@RequestMapping(path = "search/recipe/{rid}/instruction", method = RequestMethod.GET)
	public Collection<Instruction> showInstructions(HttpServletRequest req, HttpServletResponse res, @PathVariable int rid){
		return recipedao.showInstructions(rid);
	}
  
	@RequestMapping(path = "user/{uid}/recipe/{rid}", method = RequestMethod.DELETE)
	public Boolean destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int uid, @PathVariable int rid) {
		return recipedao.destroy(uid, rid);
	}
	
//	@RequestMapping(path = "recipe/{rid}/ingredient", method = RequestMethod.POST)
//	public RecipeIngredient createRecipeIngredient(HttpServletRequest req, HttpServletResponse res, @PathVariable int rid, @RequestBody String recipeIngJson){
//		return recipedao.createRecipeIngredient(rid,recipeIngJson);
//	}
	
	@RequestMapping(path = "user/{uid}/recipe/{rid}/unfave", method = RequestMethod.DELETE)
	public Boolean destroyFave(HttpServletRequest req, HttpServletResponse res, @PathVariable int uid, @PathVariable int rid) {
		return recipedao.destroyFave(uid, rid);
	}
	
	@RequestMapping(path = "user/{uid}/recipe/{rid}", method = RequestMethod.POST)
	public Recipe addToFavorite(HttpServletRequest req, HttpServletResponse res, @PathVariable int uid, @PathVariable int rid){
		return recipedao.addToFavorite(uid, rid);
	}
	
	@RequestMapping(path = "user/{uid}/recipe", method = RequestMethod.GET)
	public Collection<Favorite> setOfFavorite(HttpServletRequest req, HttpServletResponse res, @PathVariable int uid){
		return recipedao.showFavorite(uid);
	}
	
	@RequestMapping(path = "user/{uid}/recipe/rate/{rid}", method = RequestMethod.POST)
	public Rating rateRecipe(HttpServletRequest req, HttpServletResponse res, @PathVariable int uid,@PathVariable int rid,@RequestBody String jsonRating){
		return recipedao.addRating(uid, rid, jsonRating);
	}

	@RequestMapping(path = "search/tag", method = RequestMethod.GET)
	public Collection<Tag> showTags(HttpServletRequest req, HttpServletResponse res){
		return recipedao.showTags();
	}
	
	@RequestMapping(path = "search/recipe/tag/{tid}", method = RequestMethod.GET)
	public Set<Recipe> showTags(HttpServletRequest req, HttpServletResponse res, @PathVariable int tid){
		return recipedao.showFilteredRecipes(tid);
	}
	
	@RequestMapping(path = "recipe/random", method = RequestMethod.GET)
	public Recipe showRandomRecipe(HttpServletRequest req, HttpServletResponse res){
		return recipedao.showRandomRecipe();
	}
	
	@RequestMapping(path = "recipe/{rid}/random", method = RequestMethod.GET)
	public Set<Ingredient> getIngred(HttpServletRequest req, HttpServletResponse res, @PathVariable int rid){
		return recipedao.showIngred(rid);
	}
	
	@RequestMapping(path = "recipe/ingredient", method = RequestMethod.GET)
	public Set<Ingredient> indexIngred(HttpServletRequest req, HttpServletResponse res){
		return recipedao.indexIngred();
	}
}
