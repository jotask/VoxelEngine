package com.github.jotask.voxelengine.graphic;

import com.github.jotask.voxelengine.math.Vector3;

/**
 * PerspectiveCamera
 *
 * @author Jose Vives Iznardo
 * @since 31/05/2017
 */
public class PerspectiveCamera extends Camera {

    public final float FOV = 70;

    private final Vector3 tmp = new Vector3();

    public PerspectiveCamera(float width, float height) {
        this.viewportWidth = width;
        this.viewportHeight = height;
//
//        actualizar();

    }

    @Override
    public void actualizar() {
        float aspect = viewportWidth / viewportHeight;
        projection.setToProjection(Math.abs(near), Math.abs(far), FOV, aspect);
        view.setToLookAt(position, tmp.set(position).add(direction), up);
        combined.set(projection);
        combined.mul(view);

    }

}
