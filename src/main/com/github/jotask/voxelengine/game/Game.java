package com.github.jotask.voxelengine.game;

import com.github.jotask.voxelengine.engine.GameObject;
import com.github.jotask.voxelengine.engine.LWJGLApplication;
import com.github.jotask.voxelengine.engine.renderer.Camera;
import com.github.jotask.voxelengine.engine.renderer.Loader;
import com.github.jotask.voxelengine.engine.renderer.Renderer;
import com.github.jotask.voxelengine.graphic.shader.StaticShader;
import com.github.jotask.voxelengine.model.ModelTexture;
import com.github.jotask.voxelengine.model.RawModel;

/**
 * Game
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class Game implements LWJGLApplication {

    private Renderer sb;
    private Loader loader;
    private StaticShader shader;
    private Camera camera;

    GameObject obj;

    @Override
    public void init() {

        loader = new Loader();
        shader = new StaticShader();

        sb = new Renderer(shader);

        camera = new Camera();

        RawModel raw = RawModel.createPrimitive(RawModel.PrimitiveType.CUBE, loader);
        ModelTexture model = new ModelTexture(raw, loader.loadTexture("bricks.png"));
        obj = new GameObject(model);
        obj.move(0, 0, -1);

    }

    @Override
    public void update() {
        camera.move();
        obj.rotate(1, 1, 1);
        obj.move(0, 0, -0.01f);
    }

    @Override
    public void render() {
        sb.prepare();
        shader.start();
        shader.loadViewMatrix(camera);
        sb.render(obj, shader);
        shader.stop();
    }

    @Override
    public void dispose() {
        shader.dispose();
        loader.dispose();
    }

}
