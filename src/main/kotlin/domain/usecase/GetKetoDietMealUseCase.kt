package org.example.domain.usecase

import domain.model.Meal
import org.example.domain.MealException
import org.example.domain.repository.MealRepository


class GetKetoDietMealUseCase(private val mealRepository: MealRepository) {
    fun getSuggestedKetoMeal(repeatedMeals : MutableSet<Meal>): Result<Meal>{
            val ketoMeals =  getAllKetoDietMeals().filter { meal ->
                !repeatedMeals.contains(meal)
            }
            if (ketoMeals.isEmpty()){
                return Result.failure(MealException.NoKetoDietMealFound("no keto meal found"))
            }
            val suggestedMeal = ketoMeals.random()
            return Result.success(suggestedMeal)

        //choose random meal then offer it for the user
        // 70% fat 20% protein 10% carbs
    }

    private fun getAllKetoDietMeals(): List<Meal> {
        return mealRepository.getMeals()
           .filter { checkForRequiredNutritionInKetoDiet(it) }
    }
    private fun checkForRequiredNutritionInKetoDiet(meal : Meal):Boolean {
        if (!checkForNutritionAvailability(meal)) return false

        val totalFat = meal.nutrition.totalFat ?: return false
        val calories = meal.nutrition.calories ?: return false
        val carbs = meal.nutrition.carbohydrates ?: return false
        val protein = meal.nutrition.protein ?: return false

        return (totalFat * TOTAL_FAT_CALORIES_FACTOR / calories >= MIN_FAT) &&
                (carbs <= MAX_CARB) &&
                (protein * PROTEIN_CALORIES_FACTOR / calories in MIN_PROTEIN..MAX_PROTEIN)

    }


    private fun checkForNutritionAvailability(meal:Meal):Boolean{
        return meal.nutrition.protein != null
                && meal.nutrition.totalFat != null
                && meal.nutrition.carbohydrates != null
                && meal.nutrition.calories != null
    }

    companion object{
        const val MAX_CARB = 50f
        const val MIN_FAT = 0.60f
        const val MIN_PROTEIN = 0.15f
        const val MAX_PROTEIN = 0.4f
        const val TOTAL_FAT_CALORIES_FACTOR = 9
        const val PROTEIN_CALORIES_FACTOR = 4
    }
}

