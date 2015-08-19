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
package ml.melonz.histacomrm;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

    public static final Sound win95Start = new Sound("/ml/melonz/histacomrm/Windows95Startup.wav");
    private AudioClip clip;

    public Sound(String filename) {
        try {
            clip = Applet.newAudioClip(Sound.class.getResource(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void play() {
        try {
            new Thread() {
                public void run() {
                    clip.play();
                }
            }.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}