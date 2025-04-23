package domain.usecase.GetIngredientGuessUseCase
import domain.model.Meal
import org.example.domain.MealException
import org.example.domain.repository.MealRepository

class GetUniqueRandomMealUseCase (private val mealRepository : MealRepository) {

    fun getUniqueRandomMeal(usedMeals: Set<String>): Result<Meal>  {
        val available = mealRepository.getMeals().filterNot { it.mealName==null || usedMeals.contains(it.mealName) }
        return if (available.isNotEmpty())  Result.success(available.random())
        else  Result.failure(MealException.NoMealsFoundException("No Random Meals found"))

    }
}