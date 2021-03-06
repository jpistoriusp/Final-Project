angular.module('recipe')
	.filter('recipeFilter', function(){
		return function(recipes, tags) {
			var listCheck = false;
			var filteredRecipes = [];
			recipes.forEach(function(recipe, idx, arr){
				recipe.tags.forEach(function(tag, idx, arr){
					tags.forEach(function(t, idx, arr){
						if (t.selected) {
							listCheck = true;
						}
						if (t.selected && tag.id === t.id) {
							filteredRecipes.push(recipe);
						}
					})
				})
			});
			if (!listCheck) {
				return recipes;
			}
			return filteredRecipes;
		}
	});