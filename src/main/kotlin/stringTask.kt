import kotlin.math.abs

/*  Условие
 *  Вход 1 слово от пользователя 1 слово эталон
 *  Выход true/false
 *  true если отсутствует 1 буква
 *  true если лишняя 1 буква
 *  true если изменена 1 буква
 *  false в остальных случаях
 */
fun main() {
    val test1 = Pair("привет","привет")
    val test2 = Pair("привеет","привет")
    val test3 = Pair("приет","привет")
    val test4 = Pair("привееет","привет")
    val test5 = Pair("пркает","привет")
    val test6 = Pair("прмвет","привет")
    val test7 = Pair("привет1","привет")
    val test8 = Pair("прweет1","привет")
    println(isNotCorrected(test1.first,test1.second))
    println(isNotCorrected(test2.first,test2.second))
    println(isNotCorrected(test3.first,test3.second))
    println(isNotCorrected(test4.first,test4.second))
    println(isNotCorrected(test5.first,test5.second))
    println(isNotCorrected(test6.first,test6.second))
    println(isNotCorrected(test7.first,test7.second))
    println(isNotCorrected(test8.first,test8.second))
}

fun isNotCorrected(userWord: String, dictionary: String) : Boolean{
    if (userWord == dictionary)
        return false
    if (abs(userWord.length - dictionary.length) > 1)
        return false
    val userWordArray = userWord.toCharArray()
    val dictionaryArray = dictionary.toCharArray()
    if (userWord.length == dictionary.length) {
        var mistakes = 0
        for (i in userWordArray.indices){
            if (userWordArray[i] != dictionaryArray[i])
                mistakes++
        }
        return mistakes <= 1
    }
    var mistakes = 0
    var j = 0
    var bool = true
    for (i in userWordArray.indices){
        if (i == userWordArray.size-1 && bool && mistakes == 0)
            return true
        if (userWordArray[i] != dictionaryArray[j]){
            if (bool){
                bool = if (userWordArray.size < dictionaryArray.size){
                    j+=2
                    false
                } else {
                    false
                }
            }
            mistakes++
            continue
        }
        j++
    }
    if (mistakes > 1)
        return false
    return true
}