package Java.utility;

import org.apache.commons.lang.WordUtils;

public class DisplayUtil {

    public static String toTitleCase(String string) {
        return WordUtils.capitalize(WordUtils.swapCase(string));
    }
}
