package my.string.utils;

public class Difference {
    public static String difference(String str1, String str2) {
        if (str1 == str2) {
            return null;
        }
        if (str1.equals(str2)) {
            return "";
        }
        if (str1 == null) {
            return str2;
        }
        if (str2 == null) {
            return str1;
        }

        String longer = "";
        String shorter = "";
        if( str1.length() > str2.length() ) {
            longer = str1;
            shorter = str2;
        }
        else {
            longer = str2;
            shorter = str1;
        }

        int at = indexOfDifference(longer, shorter);
        if (at == -1) {
            at = indexOfDifferenceFromBack(longer, shorter);
            if (at == -1) {
                return "";
            }
            else {
                return longer.substring(0, at);
            }
        }
        else {
            return longer.substring(at);
        }
    }

    public static int indexOfDifference(String longer, String shorter) {
        if (longer == shorter || longer.equals(shorter)) {
            return -1;
        }
        if (longer == null) {
            return shorter.length();
        }
        if (shorter == null) {
            return longer.length();
        }

        return -1;
    }

    public static int indexOfDifferenceFromBack(String longer, String shorter) {
        if (longer == shorter || longer.equals(shorter)) {
            return -1;
        }
        if (longer == null) {
            return shorter.length();
        }
        if (shorter == null) {
            return longer.length();
        }
        int indexOfSimilarity = -1;
        for(int j = 0; j < longer.length(); j++) {
            if( longer.charAt(j) == shorter.charAt(0) ) {
                indexOfSimilarity = j;
                for(int i = 0; i+indexOfSimilarity < longer.length() && i < shorter.length() && longer.charAt(i+indexOfSimilarity) == shorter.charAt(i); i++) {
                    if( (i+indexOfSimilarity) == (longer.length()-1) || i == (shorter.length()-1) ) {
                        return (indexOfSimilarity-1);
                    }
                    j = i+indexOfSimilarity;
                }
            }
        }
        return -1;
    }
}
