package com.github.jotask.voxelengine.game;

import com.github.jotask.voxelengine.engine.LWJGLApplication;
import com.github.jotask.voxelengine.engine.renderer.Loader;
import com.github.jotask.voxelengine.engine.renderer.RawModel;
import com.github.jotask.voxelengine.engine.renderer.Renderer;

/**
 * Game
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class Game implements LWJGLApplication {

    private Renderer sb;
    private Loader loader;

    RawModel model;

    @Override
    public void init() {

        loader = new Loader();
        sb = new Renderer();

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,

                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
                -0.5f, 0.5f, 0f
        };

        model = loader.loadMesh(vertices);

    }

    @Override
    public void update() {
    }

    @Override
    public void render() {
        sb.prepare();
        sb.render(model);
    }

    @Override
    public void dispose() {
        loader.dispose();
    }

}
