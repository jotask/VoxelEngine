package com.github.jotask.voxelengine.graphic.shader;

/**
 * Attributes
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public enum Attributes {

    VERTEX_POSITIONS("a_position"),
    NORMAL("a_normal"),
    TEXTURE_COORD("a_textureCoords"),
    COLOR("a_colour");

    public final String name;

    Attributes(String name){
        this.name = name;
    }

}
