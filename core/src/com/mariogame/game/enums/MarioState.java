package com.mariogame.game.enums;

/**
 * Created by mike on 9/28/15.
 */
public enum MarioState {
    FALLING("falling"),
    JUMPING("jumping"),
    STANDING("standing"),
    RUNNING("running")
    ;

    private final String text;

    /**
     * @param text
     */
    private MarioState(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
