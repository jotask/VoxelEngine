package com.github.jotask.voxelengine.graphic.shader;

/**
 * Attributes
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public enum Attributes {

    VERTEX_POSITIONS("position"),
    TEXTURE_COORD("textureCoords");

    public final String name;

    Attributes(String name){
        this.name = name;
    }

}
