public class SDictUtils {
    public static void main(String[] args) {
        boolean test1 = isNotCorrect("привет","привет");
        boolean test2 = isNotCorrect("привеет","привет");
        boolean test3 = isNotCorrect("приет","привет");
        boolean test4 = isNotCorrect("привееет","привет");
        boolean test5 = isNotCorrect("пркает","привет");
        boolean test6 = isNotCorrect("прмвет","привет");
        boolean test7 = isNotCorrect("привет1","привет");
        boolean test8 = isNotCorrect("привет","првсват");
        boolean test9 = isNotCorrect("прифет1","привет");

        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);
        System.out.println(test4);
        System.out.println(test5);
        System.out.println(test6);
        System.out.println(test7);
        System.out.println(test8);
        System.out.println(test9);
    }

    public static boolean isNotCorrect(String dictWord, String userWord) {
        if (userWord.equals(dictWord)) return false;

        if (dictWord.length() == userWord.length()) {
            int mistakes = 0;
            for (int i = 0; i < userWord.length(); i++) {
                if (userWord.charAt(i) != dictWord.charAt(i)) {
                    mistakes++;
                    if (mistakes > 1) {
                        return false;
                    }
                }
            }
            return true;
        }

        String longer;
        String shorter;

        if (dictWord.length() > userWord.length()) {
            longer = dictWord;
            shorter = userWord;
        } else {
            longer = userWord;
            shorter = dictWord;
        }

        for (int i = 0, j = 0; j < longer.length(); i++, j++) {
            if (i >= shorter.length() || longer.charAt(j) != shorter.charAt(i)) {
                j++;
                if (j - i > 1)
                    return false;
            }
        }
        return true;
    }
}
