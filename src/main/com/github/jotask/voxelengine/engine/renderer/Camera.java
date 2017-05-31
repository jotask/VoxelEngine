package com.github.jotask.voxelengine.engine.renderer;

import com.github.jotask.voxelengine.input.Input;
import com.github.jotask.voxelengine.math.Vector3;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Camera
 *
 * @author Jose Vives Iznardo
 * @since 31/05/2017
 */
public class Camera {

    private Vector3 position;
    private float pitch;
    private float yaw;
    private float roll;

    public Camera() {
        this.position = new Vector3();
        this.pitch = 0f;
        this.yaw = 0f;
        this.roll = 0f;
    }

    public void move(){
        if(Input.keys[GLFW_KEY_W])
            this.position.z -= .1f;
        if(Input.keys[GLFW_KEY_S])
            this.position.z += .1f;
        if(Input.keys[GLFW_KEY_A])
            this.position.x -= .1f;
        if(Input.keys[GLFW_KEY_D])
            this.position.x += .1f;
    }

    public Vector3 getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }

}
