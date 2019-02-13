package smarthome.io.ui;

import java.util.ArrayList;
import java.util.List;

public enum TextStyle {

    RESET("\u001b[0m"),
    BLACK("\u001b[30m"),
    RED("\u001b[31m"),
    GREEN("\u001b[32m"),
    YELLOW("\u001b[33m"),
    BLUE("\u001b[34m"),
    MAGENTA("\u001b[35m"),
    CYAN("\u001b[36m"),
    WHITE("\u001b[37m"),
    BR_BLACK("\u001b[30;1m"),
    BR_RED("\u001b[31;1m"),
    BR_GREEN("\u001b[32;1m"),
    BR_YELLOW("\u001b[33;1m"),
    BR_BLUE("\u001b[34;1m"),
    BR_MAGENTA("\u001b[35;1m"),
    BR_CYAN("\u001b[36;1m"),
    BR_WHITE("\u001b[37;1m"),
    BG_BLACK("\u001b[40m"),
    BG_RED("\u001b[41m"),
    BG_GREEN("\u001b[42m"),
    BG_YELLOW("\u001b[43m"),
    BG_BLUE("\u001b[44m"),
    BG_MAGENTA("\u001b[45m"),
    BG_CYAN("\u001b[46m"),
    BG_WHITE("\u001b[47m"),
    BG_BR_BLACK("\u001b[40;1m"),
    BG_BR_RED("\u001b[41;1m"),
    BG_BR_GREEN("\u001b[42;1m"),
    BG_BR_YELLOW("\u001b[43;1m"),
    BG_BR_BLUE("\u001b[44;1m"),
    BG_BR_MAGENTA("\u001b[45;1m"),
    BG_BR_CYAN("\u001b[46;1m"),
    BG_BR_WHITE("\u001b[47;1m"),

    BOLD("\u001b[1m"),
    UNDERLINE("\u001b[4m"),
    REVERSED("\u001b[7m"),

    UP("\u001b[1A"),
    DOWN("\u001b[1B"),
    RIGHT("\u001b[1C"),
    LEFT("\u001b[1D");

    private final String code;

    TextStyle(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
