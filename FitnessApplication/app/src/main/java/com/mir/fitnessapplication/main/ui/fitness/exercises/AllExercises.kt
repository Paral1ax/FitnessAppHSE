package com.mir.fitnessapplication.main.ui.fitness.exercises

import com.mir.fitnessapplication.R

class AllExercises(val position: Int) {
    private val listOfAll = mutableListOf(
        mutableListOf(
            ExerciseItemStorage(R.drawable.bicepc1, "Бицепс","Подъемы штанги стоя"),
            ExerciseItemStorage(R.drawable.bicepc5, "Бицепс","Подъемы гантелей стоя"),
            ExerciseItemStorage(R.drawable.bicepc2, "Бицепс","Подъемы гантелей сидя"),
            ExerciseItemStorage(R.drawable.bicepc3, "Бицепс","Подъемы на скамье Скотта 1"),
            ExerciseItemStorage(R.drawable.bicepc4, "Бицепс","Подъемы на скамье Скотта 2"),
            ExerciseItemStorage(R.drawable.bicepc6, "Бицепс","Подтягивания узким обратным хватом"),
            ExerciseItemStorage(R.drawable.bicepc7, "Бицепс","Подъемы штанги лежа грудью на наклонной скамье"),
            ExerciseItemStorage(R.drawable.bicepc8, "Бицепс","Концентрированные сгибания с гантелей"),
            ExerciseItemStorage(R.drawable.bicepc9, "Бицепс","Сгибания рук на верхних ручках кроссовера"),
            ExerciseItemStorage(R.drawable.bicepc10, "Бицепс","“Молотки”"),
            ExerciseItemStorage(R.drawable.bicepc11, "Бицепс","Подъемы на нижнем блоке"),
            ExerciseItemStorage(R.drawable.tricepc1, "Трицепс","Жим лежа узким хватом"),
            ExerciseItemStorage(R.drawable.tricepc2, "Трицепс","Французский жим"),
            ExerciseItemStorage(R.drawable.tricepc3, "Трицепс","Отжимания на брусьях в трицепсовом стиле"),
            ExerciseItemStorage(R.drawable.tricepc4, "Трицепс","Отжимания спиной к скамье"),
            ExerciseItemStorage(R.drawable.tricepc5, "Трицепс","Отжимания от пола с узкой постановкой рук"),
            ExerciseItemStorage(R.drawable.tricepc6, "Трицепс","Разгибания рук с гантелей из-за головы"),
            ExerciseItemStorage(R.drawable.tricepc7, "Трицепс","Разгибания рук на блоке"),
            ExerciseItemStorage(R.drawable.plechi3, "Предплечья","Запястные сжимания"),
            ExerciseItemStorage(R.drawable.plechi2, "Предплечья","Перекаты грифа в ладонях"),
            ExerciseItemStorage(R.drawable.plechi1, "Предплечья","Сгибание кистей обратным хватом")
        ),
        mutableListOf(
            ExerciseItemStorage(R.drawable.base1, "Базовое","Становая тяга"),
            ExerciseItemStorage(R.drawable.base2, "Базовое","Подтягивания"),
            ExerciseItemStorage(R.drawable.base3, "Базовое","Тяга штанги в наклоне"),
            ExerciseItemStorage(R.drawable.base4, "Базовое","Тяга штанги к подбородку"),
            ExerciseItemStorage(R.drawable.isol1, "Изолирующее","Тяга вертикального блока широким хватом"),
            ExerciseItemStorage(R.drawable.isol2, "Изолирующее","Тяга горизонтального блока к поясу"),
            ExerciseItemStorage(R.drawable.home1, "Дома","Лодочка"),
            ExerciseItemStorage(R.drawable.home2, "Дома","Мостик"),
            ExerciseItemStorage(R.drawable.ingym1, "В зале","Тяга верхнего блока за голову"),
            ExerciseItemStorage(R.drawable.ingym2, "В зале","Тяга верхнего блока обратным хватом"),
            ExerciseItemStorage(R.drawable.greblya, "Гребля","Гребной тренажер"),
            ExerciseItemStorage(R.drawable.trx, "TRX","Тяга кроссовера"),
            ExerciseItemStorage(R.drawable.ingym3, "В зале","Тяга нижнего кроссовера"),
            ExerciseItemStorage(R.drawable.ingym4, "В зале","Гиперэкстензия"),
            ExerciseItemStorage(R.drawable.espander1, "С грудным эспандером","Сведение лопаток с грудным эспандером"),
            ExerciseItemStorage(R.drawable.espander2, "Со жгутом","Тяга собственного веса при помощи резинового жгута"),
            ExerciseItemStorage(R.drawable.espander3, "Фитбол","Гиперэкстензия на фитболе"),
        ),
        mutableListOf(
            ExerciseItemStorage(R.drawable.press1, "Пресс","Скручивания лежа"),
            ExerciseItemStorage(R.drawable.press2, "Пресс","Ситап"),
            ExerciseItemStorage(R.drawable.press3, "Пресс","Косые скручивания"),
            ExerciseItemStorage(R.drawable.press4, "Пресс","Подъемы прямых ног лежа"),
            ExerciseItemStorage(R.drawable.press5, "Пресс","Планка"),
            ExerciseItemStorage(R.drawable.press6, "Пресс","Комбинированные скручивания"),
        ),
        mutableListOf(
            ExerciseItemStorage(R.drawable.legs1, "Базовое","Мертвая тяга"),
            ExerciseItemStorage(R.drawable.legs2, "Базовое","Приседания со штангой в любой вариации"),
            ExerciseItemStorage(R.drawable.legs3, "Изолирующее","Жим ногами"),
            ExerciseItemStorage(R.drawable.ingym4, "Изолирующее","Гиперэкстензия"),
            ExerciseItemStorage(R.drawable.legs5, "Изолирующее","Подъем носков в тренажере сидя"),
            ExerciseItemStorage(R.drawable.legs6, "Изолирующее","Разведение ног в стороны на тренажере"),
            ExerciseItemStorage(R.drawable.legs7, "Изолирующее","Сгибание/разгибание ног на тренажере"),
            ExerciseItemStorage(R.drawable.legs8, "В зале","Райдер"),
            ExerciseItemStorage(R.drawable.legs9, "В зале","Климбер"),
            ExerciseItemStorage(R.drawable.legs10, "В зале","Велотренажеры"),
            ExerciseItemStorage(R.drawable.legs11, "В зале","Эллипсоиды"),
            ExerciseItemStorage(R.drawable.legs12, "Дома","Воздушные приседания"),
            ExerciseItemStorage(R.drawable.legs13, "Дома","Выпады"),
            ExerciseItemStorage(R.drawable.legs14, "Дома","Наклоны к прямым ногам"),
            ExerciseItemStorage(R.drawable.legs15, "Дома","Выпрыгивания"),
            ExerciseItemStorage(R.drawable.legs16, "Растяжка","Глубокие выпады без веса"),
            ExerciseItemStorage(R.drawable.legs17, "Растяжка","Полушпагаты – поперечные и продольные"),
            ExerciseItemStorage(R.drawable.legs18, "Растяжка","Всевозможные виды шпагатов"),
            ExerciseItemStorage(R.drawable.legs19, "Растяжка","Махи ногами")
        )

    )

    fun getListByPosition(): MutableList<ExerciseItemStorage> {
        return listOfAll[position]
    }
}
