package ml.melonz.histacomrm.util;

import ml.melonz.histacomrm.HistacomRewrite;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class Language {
    private static final Map<String, Properties> langs = new HashMap<>();
    private static volatile String current;
    public static final String fallback = "enGB";

    static {
        try {
            load("enUS");
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    public static void load(String lang) throws IOException {
        if (!langs.containsKey(lang)) {
            Properties props = new Properties();
            props.load(HistacomRewrite.class.getResourceAsStream("/assets/locale/" + lang + ".lang"));
            if(props.containsKey("locale.name")) {
                System.out.println("Loading Language: " + props.getProperty("locale.name"));
                langs.put(lang, props);
            } else {
                System.out.println("Invalid language: " + lang);
            }
        }

        current = lang;
    }

    public static void reload(String lang) throws IOException {
        if (langs.containsKey(lang)) {
            langs.remove(lang);
        }

        load(lang);
    }

    public static String localize(String lang, String tag) {
        if (langs.containsKey(lang)) {
            Properties props = langs.get(lang);
            if (props.containsKey(tag)) {
                return props.getProperty(tag, tag);
            } else {
                if (lang.equalsIgnoreCase(fallback)) {
                    return "Unknown";
                } else {
                    return localize(fallback, tag);
                }
            }
        } else {
            return localize(fallback, tag);
        }
    }

    public static String localize(String tag) {
        return localize(current, tag);
    }

    public static String localizeWithReplace(String tag, String replaceWith) {
        return localize(current, tag).replace("%s", replaceWith);
    }

    public static String getCurrent() {
        return current;
    }
}
