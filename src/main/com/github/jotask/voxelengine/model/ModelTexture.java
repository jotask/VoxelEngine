package com.github.jotask.voxelengine.model;

import com.github.jotask.voxelengine.graphic.texture.Texture;

/**
 * ModelTexture
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class ModelTexture {

    private final RawModel model;
    private final Texture texture;

    public ModelTexture(RawModel model, Texture texture) {
        this.model = model;
        this.texture = texture;
    }

    public RawModel getRawModel() { return model; }
    public Texture getTexture() { return texture; }

}
