
import org.example.domain.usecase.GetFoodUseCase

class GetFoodUi(private val useCase:GetFoodUseCase) {
     fun invoke() {
        println(useCase.invoke())
    }
}