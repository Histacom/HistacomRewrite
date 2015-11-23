/*
 * This file is part of Histacom 2, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015, Histacom Development Team <http://histacom.jamierocks.uk/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
