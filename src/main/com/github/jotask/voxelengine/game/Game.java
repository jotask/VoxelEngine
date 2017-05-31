package com.github.jotask.voxelengine.game;

import com.github.jotask.voxelengine.engine.GameEngine;
import com.github.jotask.voxelengine.engine.GameObject;
import com.github.jotask.voxelengine.engine.LWJGLApplication;
import com.github.jotask.voxelengine.engine.components.Mesh;
import com.github.jotask.voxelengine.engine.renderer.Loader;
import com.github.jotask.voxelengine.engine.renderer.Renderer;
import com.github.jotask.voxelengine.graphic.PerspectiveCamera;
import com.github.jotask.voxelengine.model.ModelTexture;
import com.github.jotask.voxelengine.model.RawModel;
import com.github.jotask.voxelengine.test.Testing;

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

    private PerspectiveCamera camera;

    private List<GameObject> objs = new ArrayList<>();

    @Override
    public void init() {

        new Testing();

        loader = new Loader();

        sb = new Renderer();

        this.camera = new PerspectiveCamera(GameEngine.WIDTH, GameEngine.HEIGHT);


        {
            RawModel raw = RawModel.createPrimitive(RawModel.PrimitiveType.CUBE, loader);
            ModelTexture model = new ModelTexture(raw, loader.loadTexture("bricks.png"));
            GameObject obj = new GameObject();
            obj.addComponent(new Mesh(raw));
            obj.getTransformation().move(0,0,.5f);
            objs.add(obj);
        }
//
//        {
//            RawModel raw = OBJLoader.loadFile(loader, "testone.obj");
//            ModelTexture model = new ModelTexture(raw, loader.loadTexture("bricks.png"));
//            GameObject obj = new GameObject(model);
//            obj.getTransformation().move(5, 0, 0);
//            objs.add(obj);
//        }
//
//        {
//            RawModel raw = OBJLoader.loadFile(loader, "werido.obj");
//            ModelTexture model = new ModelTexture(raw, loader.loadTexture("bricks.png"));
//            GameObject obj = new GameObject(model);
//            obj.getTransformation().move(-5, 0, 0);
//            objs.add(obj);
//        }


    }

    @Override
    public void update() {
//        camera.move();
        for(GameObject o: objs) {
            o.update();
        }

    }

    @Override
    public void render() {

        sb.setProjectionMatrix(this.camera.combined);
        sb.begin();
        for(GameObject o: objs)
            sb.render(o);
        sb.end();
    }

    @Override
    public void dispose() {
        sb.dispose();
        loader.dispose();
    }

}
