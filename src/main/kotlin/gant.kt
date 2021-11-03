import java.util.regex.Pattern

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
    var strings = input.split("\n");
    var mainTasks = mutableListOf<MainTask>()
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
                while (i < periods.length && periods[i].equals(' ')) i++;
                val periodStart = i;
                while (i < periods.length && setOf('|', '{', '}', '-').contains(periods[i])) i++;
                val periodEnd = i;
                while (i < periods.length && periods[i].equals(' ')) i++;
                val period = Period(periodStart, periodEnd)
                ps.add(period)
            }

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
