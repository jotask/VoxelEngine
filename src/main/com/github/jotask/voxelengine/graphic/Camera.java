package com.github.jotask.voxelengine.graphic;

import com.github.jotask.voxelengine.engine.GameEngine;
import com.github.jotask.voxelengine.input.Input;
import com.github.jotask.voxelengine.math.Matrix4;
import com.github.jotask.voxelengine.math.Vector3;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Camera
 *
 * @author Jose Vives Iznardo
 * @since 01/06/2017
 */
public class Camera {

    private static final float FOV = 70f;
    private static final float NEAR = .1f;
    private static final float FAR = 1000f;

    public final Matrix4 projection;
    private final Matrix4 view;

    public final Vector3 position = new Vector3();
    public float pitch;
    public float yaw;
    public float roll;

    public Camera() {
        this.projection = createProjectionMatrix();
        this.view = createViewMatrix();
    }

    public void update(){
        if(Input.keys[GLFW_KEY_W])
            position.z -= .02f;
        if(Input.keys[GLFW_KEY_S])
            position.z += .02f;

        if(Input.keys[GLFW_KEY_A])
            position.x -= .02f;
        if(Input.keys[GLFW_KEY_D])
            position.x += .02f;
    }

    private Matrix4 createProjectionMatrix(){
        float aspectRatio = (float) GameEngine.WIDTH / (float) GameEngine.HEIGHT;
        float yScale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float xScale = yScale / aspectRatio;
        float frustumLength = FAR - NEAR;

        Matrix4 projection = new Matrix4();
        projection.m00 = xScale;
        projection.m11 = yScale;
        projection.m22 = -((FAR + NEAR) / frustumLength);
        projection.m23 = -1f;
        projection.m32 = -((2 * NEAR * FAR) / frustumLength);
        projection.m33 = 0f;
        return projection;

    }

    private Matrix4 createViewMatrix(){
        Matrix4 matrix = new Matrix4();
        matrix.setIdentity();
        Matrix4.rotate((float) Math.toRadians(pitch), new Vector3(1,0,0), matrix, matrix);
        Matrix4.rotate((float) Math.toRadians(yaw), new Vector3(0,1,0), matrix, matrix);
        Matrix4.rotate((float) Math.toRadians(roll), new Vector3(0,0,1), matrix, matrix);
        Vector3 neg = new Vector3(-position.x, -position.y, -position.z);
        Matrix4.translate(neg, matrix, matrix);
        return matrix;

    }

    public Matrix4 getProjection() {
        return new Matrix4().setIdentity();
    }

    public Matrix4 getView(){
        this.view.set(createViewMatrix());
        return new Matrix4().setIdentity();
    }

    public Matrix4 getCombined(){
        Matrix4 result = new Matrix4();
        result.set(projection);
        result.mul(createViewMatrix());
        return result;
    }

}
