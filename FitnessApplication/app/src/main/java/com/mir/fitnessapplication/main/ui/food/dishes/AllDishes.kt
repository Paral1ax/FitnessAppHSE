package com.mir.fitnessapplication.main.ui.food.dishes

import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.main.ui.fitness.exercises.ExerciseItemStorage

class AllDishes(private val position: Int) {

    val listOfAll = mutableListOf(
        mutableListOf(
            DishItemStorage(R.drawable.breakfast1, "Завтрак","Омлет с авокадо"),
            DishItemStorage(R.drawable.breakfast2, "Завтрак","Диетический омлет"),
            DishItemStorage(R.drawable.breakfast3, "Завтрак","Шакшука"),
            DishItemStorage(R.drawable.breakfast4, "Завтрак","Кабачковые маффины"),
            DishItemStorage(R.drawable.breakfast5, "Завтрак","Тыквенные сырники"),
            DishItemStorage(R.drawable.breakfast6, "Завтрак","Омлет с курицей и брокколи"),
            DishItemStorage(R.drawable.breakfast7, "Завтрак","Овсяные оладьи"),
            DishItemStorage(R.drawable.breakfast8, "Завтрак","Пшенная каша на кокосовом молоке"),
            DishItemStorage(R.drawable.breakfast9, "Завтрак","Быстрая творожная запеканка"),
            DishItemStorage(R.drawable.breakfast10, "Завтрак","Кокосово-банановые сырники"),
            DishItemStorage(R.drawable.breakfast11, "Завтрак","Самая полезная каша"),
            DishItemStorage(R.drawable.breakfast12, "Завтрак","Яблочная каша"),
            DishItemStorage(R.drawable.breakfast13, "Напиток","Бананово-тыквенный смузи"),
            DishItemStorage(R.drawable.breakfast14, "Завтрак","Кабачковые оладьи"),
            DishItemStorage(R.drawable.breakfast15, "Завтрак","Ленивая овсянка с семенами чиа"),
            DishItemStorage(R.drawable.breakfast16, "Перекус","Творожно-банановые маффины"),
            DishItemStorage(R.drawable.breakfast17, "Завтрак","Омлет с творогом и шпинатом"),
            DishItemStorage(R.drawable.breakfast19, "Завтрак","Лаваш с творогом и зеленью"),
        ),
        mutableListOf(
            DishItemStorage(R.drawable.lunch1, "Закуска","Паста с тунцом и помидорами"),
            DishItemStorage(R.drawable.lunch2, "Горячее","Рыбные котлеты на пару"),
            DishItemStorage(R.drawable.lunch3, "Суп","Окрошка с курицей"),
            DishItemStorage(R.drawable.lunch4, "Суп","Чечевичный суп-пюре"),
            DishItemStorage(R.drawable.lunch5, "Салат","Салат со свеклой и фетой"),
            DishItemStorage(R.drawable.lunch6, "Горячее","Котлеты из индейки с овощами"),
            DishItemStorage(R.drawable.lunch7, "Суп","Суп с зеленым горошком"),
            DishItemStorage(R.drawable.lunch8, "Гарнир","Гречка с кабачками"),
            DishItemStorage(R.drawable.lunch9, "Суп","Суп с брокколи, цветной капустой и грибами"),
            DishItemStorage(R.drawable.lunch10, "Салат","Оливье с курицей и авокадо"),
            DishItemStorage(R.drawable.lunch11, "Суп","Гороховый суп с курицей"),
            DishItemStorage(R.drawable.lunch12, "Салат","Салат с тунцом и базиликом"),
            DishItemStorage(R.drawable.lunch13, "Горячее","Куриные тефтели с булгуром"),
            DishItemStorage(R.drawable.lunch14, "Салат","Салат с куриной печенью"),
            DishItemStorage(R.drawable.lunch15, "Суп","Суп с кускусом и рыбными фрикадельками"),
            DishItemStorage(R.drawable.lunch16, "Суп","Овощной суп без зажарки"),
            DishItemStorage(R.drawable.lunch17, "Суп","Морковный суп-пюре"),
            DishItemStorage(R.drawable.lunch18, "Суп","Крем-суп из тыквы"),
        ),
        mutableListOf(
            DishItemStorage(R.drawable.dinner1, "Горячее","Куриное филе с помидорами и моцареллой"),
            DishItemStorage(R.drawable.dinner2, "Гарнир","Киноа с болгарским перцем и тимьяном"),
            DishItemStorage(R.drawable.dinner3, "Горячее","Треска с овощами в фольге"),
            DishItemStorage(R.drawable.dinner4, "Гарнир","Капуста в рукаве в духовке"),
            DishItemStorage(R.drawable.dinner5, "Горячее","Индейка с кабачками и стручковой фасолью"),
            DishItemStorage(R.drawable.dinner6, "Салат","Салат с курицей и кукурузой"),
            DishItemStorage(R.drawable.dinner7, "Горячее","Минтай с брокколи и цветной капустой"),
            DishItemStorage(R.drawable.dinner8, "Горячее","Рис с тунцом и овощами"),
            DishItemStorage(R.drawable.dinner9, "Горячее","Курица с грибами в сметанном соусе"),
            DishItemStorage(R.drawable.dinner10, "Салат","Салат из молодого картофеля"),
            DishItemStorage(R.drawable.dinner11, "Закуска","Заливной капустный пирог"),
            DishItemStorage(R.drawable.dinner12, "Горячее","Запеченные баклажаны с фаршем"),
            DishItemStorage(R.drawable.dinner13, "Горячее","Куриное филе с апельсинами"),
            DishItemStorage(R.drawable.dinner14, "Горячее","Горбуша с рисом в духовке"),
            DishItemStorage(R.drawable.dinner15, "Закуска","Кабачки в сметане"),
            DishItemStorage(R.drawable.dinner16, "Горячее","Куриные котлеты с брокколи"),
            DishItemStorage(R.drawable.dinner17, "Салат","Салат с капустой и зеленым горошком"),
            DishItemStorage(R.drawable.dinner18, "Горячее","Курица с брюссельской капустой в рукаве"),
            DishItemStorage(R.drawable.dinner19, "Салат","Капустный салат с маслинами и помидорами"),
            DishItemStorage(R.drawable.dinner20, "Горячее","Куриные фрикадельки в сливочном соусе"),
        )
    )


    fun getListByPosition(): MutableList<DishItemStorage> {
        return listOfAll[position]
    }
}