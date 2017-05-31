package com.github.jotask.voxelengine.engine;

import com.github.jotask.voxelengine.engine.components.Component;
import com.github.jotask.voxelengine.engine.renderer.Renderer;

import java.util.HashMap;

/**
 * GameObject
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class GameObject {

    private final Transformation transformation;

    private HashMap<String, Component> components;

    public GameObject() {
        this.components = new HashMap<String, Component>();
        this.transformation = new Transformation();
    }

    public void addComponent(Component component){
        if(this.components.containsKey(component)){
            System.out.println("Component: " + component.name + " already on this object");
            return;
        }
        this.components.put(component.name, component);
    }


    public void update(){
        this.transformation.rotate(1, 1, 1);
        for(final Component c: this.components.values()){
            c.update();
        }
    }

    public void render(Renderer sb){
        for(final Component c: this.components.values()){
            c.render(sb, this);
        }
    }

    public void dispose(){
        for(final Component c: this.components.values()){
            c.dispose();
        }
    }

    public Transformation getTransformation() { return transformation; }

}
