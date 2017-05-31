package com.github.jotask.voxelengine.engine.components;

import com.github.jotask.voxelengine.engine.GameObject;
import com.github.jotask.voxelengine.engine.renderer.Renderer;

/**
 * Component
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public abstract class Component {

    public final String name;

    public Component(String name) {
        this.name = name;
    }

    public abstract void update();

    public abstract void render(Renderer sb, GameObject parent);

    public abstract void dispose();

}
