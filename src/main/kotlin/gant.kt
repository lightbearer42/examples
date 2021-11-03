import java.util.regex.Pattern

/*
У тебя есть текстовое представление диаграммы Ганта.
Диаграмма - временное представление некого бизнес процесса.
Составить json объект , элементом которого будет задача из диаграммы .
Символ - день .
Длинна задачи в днях соответствует длинне символов , включая открывающие и закрывающие элементы .
Задачи могут иметь вложенные задачи.
Задачи могут повторяться в разные промежутки времени.
Родительские задачи начинаются с upper case , а дочерние (вложенные) - с lower case той же буквы.
Рядом с буквой задачи стоит номер ее выполнения.
Таким образов в представленном примере ниже в тех процессе етсь две родительские задачи (процесса) : A , B.
В каждой задаче по три подпроцесса : a1,a2,a3 и b1,b2,b3.
При этом a1 повторяется дважды. Также как и b2.
Фигурная левая скобка всегда открывает родительскую задачу .
Фигурная правая скобка всегда закрывает родительскую или дочернюю задачу, завершающую процесс родительской.

Составить json объект , элементом которого будет задача из диаграммы с соответствующей вложенностью.
Вывести json в out.



A0 {---------------------|
a1 |---| |--------}
a2 |------|
B0 {----------------------|
b1 |---|
b2 |-----| |---}
b3 |------|
 */

class SubTask(val periods: MutableList<Period>, val order: Int, val name: String)
class MainTask(val order: Int, val name: String, val periods: MutableList<Period>, val subTasks: MutableList<SubTask>)
class Period(val start: Int, val end: Int)

fun main() {
    val str="A0 {---------------------|\n" +
            "a1 |---|        |--------}\n" +
            "a2      |------|\n" +
            "B0        {----------------------|\n" +
            "b1        |---|\n" +
            "b2              |-----|      |---}\n" +
            "b3                    |------|"

    val l = toJson(str);
}

fun toJson(input: String): List<MainTask> {
    val strings = input.split("\n");
    val mainTasks = mutableListOf<MainTask>()
    var currentMainTask: MainTask? = null

    for(i in 0 until strings.size) {
        val pattern = Pattern.compile("(?<name>[a-zA-Z])(?<order>\\d+) (?<periods>.*)")
        val matcher = pattern.matcher(strings[i])
        if (matcher.find()) {
            val name = matcher.group("name")
            val order = matcher.group("order").toInt()
            val periods = matcher.group("periods")

            var ps: MutableList<Period> = mutableListOf()
            var i = 0
            while (i < periods.length) {
                // Пропускаем пробелы(если есть)
                while (i < periods.length && periods[i].equals(' ')) i++;
                val periodStart = i;
                // Считаем сам периоды процесса
                while (i < periods.length && setOf('|', '{', '}', '-').contains(periods[i])) i++;
                val periodEnd = i;
                // Пропускаем пробелы(если есть)
                while (i < periods.length && periods[i].equals(' ')) i++;
                val period = Period(periodStart, periodEnd)
                ps.add(period)
            }

            // Главная задача
            if (name[0].isUpperCase()) {
                currentMainTask = MainTask(order, name, ps, mutableListOf())
                mainTasks.add(currentMainTask)
            } else {
                if (currentMainTask == null) {
                    throw IllegalStateException();
                }
                val task = SubTask(ps, order, name)
                currentMainTask.subTasks.add(task)
            }
        }
    }

    return mainTasks
}
