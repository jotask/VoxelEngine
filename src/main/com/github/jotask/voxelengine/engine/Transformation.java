package com.github.jotask.voxelengine.engine;

import com.github.jotask.voxelengine.math.Vector3;

/**
 * Transformation
 *
 * @author Jose Vives Iznardo
 * @since 31/05/2017
 */
public class Transformation {

    private Vector3 position;
    private Vector3 rotation;
    private Vector3 scale;

    public Transformation() {
        this.position = new Vector3();
        this.rotation = new Vector3();
        this.scale = new Vector3(1, 1, 1);
    }

    public void move(float x, float y, float z){
        this.position.x += x;
        this.position.y += y;
        this.position.z += z;
    }

    public void rotate(float x, float y, float z){
        this.rotation.x += x;
        this.rotation.y += y;
        this.rotation.z += z;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Vector3 getRotation() {
        return rotation;
    }

    public void setRotation(Vector3 rotation) {
        this.rotation = rotation;
    }

    public Vector3 getScale() {
        return scale;
    }

    public void setScale(Vector3 scale) {
        this.scale = scale;
    }

}
