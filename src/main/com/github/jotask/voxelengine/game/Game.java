package com.github.jotask.voxelengine.game;

import com.github.jotask.voxelengine.engine.GameObject;
import com.github.jotask.voxelengine.engine.LWJGLApplication;
import com.github.jotask.voxelengine.engine.renderer.Camera;
import com.github.jotask.voxelengine.engine.renderer.Loader;
import com.github.jotask.voxelengine.engine.renderer.Renderer;
import com.github.jotask.voxelengine.file.OBJLoader;
import com.github.jotask.voxelengine.graphic.shader.StaticShader;
import com.github.jotask.voxelengine.model.ModelTexture;
import com.github.jotask.voxelengine.model.RawModel;

import java.util.ArrayList;
import java.util.List;

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

    private List<GameObject> objs = new ArrayList<GameObject>();

    @Override
    public void init() {

        loader = new Loader();
        shader = new StaticShader();

        sb = new Renderer(shader);

        camera = new Camera();

        {
            RawModel raw = RawModel.createPrimitive(RawModel.PrimitiveType.CUBE, loader);
            ModelTexture model = new ModelTexture(raw, loader.loadTexture("bricks.png"));
            GameObject obj = new GameObject(model);
            obj.move(0, 0, -1);
            objs.add(obj);
        }

        {
            RawModel raw = OBJLoader.loadFile(loader, "testone.obj");
            ModelTexture model = new ModelTexture(raw, loader.loadTexture("bricks.png"));
            GameObject obj = new GameObject(model);
            obj.move(5, 0, 0);
            objs.add(obj);
        }

        {
            RawModel raw = OBJLoader.loadFile(loader, "werido.obj");
            ModelTexture model = new ModelTexture(raw, loader.loadTexture("bricks.png"));
            GameObject obj = new GameObject(model);
            obj.move(-5, 0, 0);
            objs.add(obj);
        }


    }

    @Override
    public void update() {
        camera.move();
        for(GameObject o: objs) {
            o.update();
        }

    }

    @Override
    public void render() {
        sb.prepare();
        shader.start();
        shader.loadViewMatrix(camera);
        for(GameObject o: objs)
            sb.render(o, shader);
        shader.stop();
    }

    @Override
    public void dispose() {
        shader.dispose();
        loader.dispose();
    }

}
