package org.mtcoding.poly.core.util;


public class Msg {

    public static String fail(String msg) {
        return "{\"reason\": " + msg + "}";
    }
}
