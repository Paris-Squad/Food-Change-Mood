package org.example.domain.usecase

import org.example.domain.FoodException
import org.example.domain.repository.FoodRepository
import org.example.model.Food

class KetoDietMealHelperUseCase(private val foodRepository: FoodRepository) {

    val repeatedMeals = mutableSetOf<Food>()
    companion object{
        const val MAX_CARB = 50f
        const val MIN_FAT = 0.60f
        const val MIN_PROTEIN = 0.15f
        const val MAX_PROTEIN = 0.4f
    }

    fun ketoDietMeal(): Result<Food>{
        return try{
            val ketoMeals =  ketoDietMeals().filter { food ->
                !repeatedMeals.contains(food)
            }
            if (ketoMeals.isEmpty()){
                Result.failure<Food>(FoodException.NoKetoDietMealFound("no keto meal found"))
            }
            val suggestedMeal = ketoMeals.random()
            repeatedMeals.add(suggestedMeal)
            Result.success(suggestedMeal)
        }catch (exception : Exception){
            Result.failure(FoodException.NoKetoDietMealFound("Error Fetching Meals: ${exception.message}"))
        }


        //choose random meal then offer it for the user
        // 70% fat 20% protein 10% carbs

    }

    private fun ketoDietMeals(): List<Food> {
        return foodRepository.getFood()
           .filter { checkForKetoDietNutrition(it) }
    }
   private fun checkForKetoDietNutrition(food : Food): Boolean{
        return food.nutrition.protein != null
                && food.nutrition.totalFat != null
                && food.nutrition.carbohydrates != null
                && food.nutrition.calories != null
                && food.nutrition.totalFat*9/food.nutrition.calories >= MIN_FAT
                && food.nutrition.carbohydrates <= MAX_CARB
                && food.nutrition.protein*4/food.nutrition.calories in MIN_PROTEIN..MAX_PROTEIN

    }


}

