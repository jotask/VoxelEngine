package com.github.jotask.voxelengine.game;

import com.github.jotask.voxelengine.engine.renderer.Renderer;

/**
 * Entity
 *
 * @author Jose Vives Iznardo
 * @since 31/05/2017
 */
public interface Entity {

    void update();

    void render(Renderer sr);

}
