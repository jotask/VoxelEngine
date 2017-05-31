package com.github.jotask.voxelengine.game.terrain;

import com.github.jotask.voxelengine.engine.renderer.Renderer;
import com.github.jotask.voxelengine.game.Entity;

/**
 * Chunk
 *
 * @author Jose Vives Iznardo
 * @since 31/05/2017
 */
public class Chunk implements Entity {

    private final int i;
    private final int j;

    public Chunk(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Renderer sr) {

    }
}
