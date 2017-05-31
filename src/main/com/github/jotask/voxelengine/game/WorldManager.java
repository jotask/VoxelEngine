package com.github.jotask.voxelengine.game;

import com.github.jotask.voxelengine.engine.renderer.Renderer;
import com.github.jotask.voxelengine.game.terrain.Chunk;

/**
 * WorldManager
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class WorldManager implements Entity{

    private Chunk chunk;

    public WorldManager() {
        this.chunk = new Chunk(0,0);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Renderer sr) {

    }

}
