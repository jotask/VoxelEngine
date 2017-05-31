package com.github.jotask.voxelengine.graphic;

import com.github.jotask.voxelengine.engine.GameEngine;
import com.github.jotask.voxelengine.math.Matrix4;
import com.github.jotask.voxelengine.math.Vector3;

/**
 * Camera
 *
 * @author Jose Vives Iznardo
 * @since 31/05/2017
 */
public abstract class Camera {

    // The position of the camera
    public final Vector3 position = new Vector3();

    // THe unit length direction of the camera
    public final Vector3 direction = new Vector3(0,0,-1);

    // the unit length up direction of the camera
    public final Vector3 up = new Vector3(0,1,0);

    // The projection matrix
    public final Matrix4 projection = new Matrix4();

    // The view matrix
    public final Matrix4 view = new Matrix4();

    // The combined projection and view
    public final Matrix4 combined = new Matrix4();

    // Temporatl Vecotor3 for calculations
    private final Vector3 tmp = new Vector3();

    public float near = 1;
    public float far = 100;

    public float viewportWidth = 0;
    public float viewportHeight = 0;

    // Recalculate the projection and the view matrix of this camera
    public abstract void actualizar();

    /** Recalculates the direction of the camera to look at the point (x, y, z). This function assumes the up vector is normalized.
     * @param x the x-coordinate of the point to look at
     * @param y the y-coordinate of the point to look at
     * @param z the z-coordinate of the point to look at */
    public void lookAt (float x, float y, float z) {
        tmp.set(x, y, z).sub(position).nor();
        if (!tmp.isZero()) {
            float dot = tmp.dot(up); // up and direction must ALWAYS be orthonormal vectors
            if (Math.abs(dot - 1) < 0.000000001f) {
                // Collinear
                up.set(direction).scl(-1);
            } else if (Math.abs(dot + 1) < 0.000000001f) {
                // Collinear opposite
                up.set(direction);
            }
            direction.set(tmp);
            normalizeUp();
        }
    }

    /** Recalculates the direction of the camera to look at the point (x, y, z).
     * @param target the point to look at */
    public void lookAt (Vector3 target) {
        lookAt(target.x, target.y, target.z);
    }

    /** Normalizes the up vector by first calculating the right vector via a cross product between direction and up, and then
     * recalculating the up vector via a cross product between right and direction. */
    public void normalizeUp () {
        tmp.set(direction).crs(up).nor();
        up.set(tmp).crs(direction).nor();
    }

//    /** Rotates the direction and up vector of this camera by the given angle around the given axis. The direction and up vector
//     * will not be orthogonalized.
//     *
//     * @param angle the angle
//     * @param axisX the x-component of the axis
//     * @param axisY the y-component of the axis
//     * @param axisZ the z-component of the axis */
//    public void rotate (float angle, float axisX, float axisY, float axisZ) {
//        direction.rotate(angle, axisX, axisY, axisZ);
//        up.rotate(angle, axisX, axisY, axisZ);
//    }
//
//    /** Rotates the direction and up vector of this camera by the given angle around the given axis. The direction and up vector
//     * will not be orthogonalized.
//     *
//     * @param axis the axis to rotate around
//     * @param angle the angle, in degrees */
//    public void rotate (Vector3 axis, float angle) {
//        direction.rotate(axis, angle);
//        up.rotate(axis, angle);
//    }
//
//    /** Rotates the direction and up vector of this camera by the given rotation matrix. The direction and up vector will not be
//     * orthogonalized.
//     *
//     * @param transform The rotation matrix */
//    public void rotate (final Matrix4 transform) {
//        direction.rot(transform);
//        up.rot(transform);
//    }

//    /** Rotates the direction and up vector of this camera by the given angle around the given axis, with the axis attached to given
//     * point. The direction and up vector will not be orthogonalized.
//     *
//     * @param point the point to attach the axis to
//     * @param axis the axis to rotate around
//     * @param angle the angle, in degrees */
//    public void rotateAround (Vector3 point, Vector3 axis, float angle) {
//        tmp.set(point);
//        tmp.sub(position);
//        translate(tmp);
//        rotate(axis, angle);
//        tmp.rotate(axis, angle);
//        translate(-tmp.x, -tmp.y, -tmp.z);
//    }

//    /** Transform the position, direction and up vector by the given matrix
//     *
//     * @param transform The transform matrix */
//    public void transform (final Matrix4 transform) {
//        position.mul(transform);
//        rotate(transform);
//    }
//
//    /** Moves the camera by the given amount on each axis.
//     * @param x the displacement on the x-axis
//     * @param y the displacement on the y-axis
//     * @param z the displacement on the z-axis */
//    public void translate (float x, float y, float z) {
//        position.add(x, y, z);
//    }
//
//    /** Moves the camera by the given vector.
//     * @param vec the displacement vector */
//    public void translate (Vector3 vec) {
//        position.add(vec);
//    }

    /** Projects the {@link Vector3} given in world space to screen coordinates. It's the same as GLU gluProject with one small
     * deviation: The viewport is assumed to span the whole screen. The screen coordinate system has its origin in the
     * <b>bottom</b> left, with the y-axis pointing <b>upwards</b> and the x-axis pointing to the right. This makes it easily
     * useable in conjunction with {@link Batch} and similar classes.
     * @return the mutated and projected worldCoords {@link Vector3} */
    public Vector3 project (Vector3 worldCoords) {
        project(worldCoords, 0, 0, GameEngine.WIDTH, GameEngine.HEIGHT);
        return worldCoords;
    }

    /** Projects the {@link Vector3} given in world space to screen coordinates. It's the same as GLU gluProject with one small
     * deviation: The viewport is assumed to span the whole screen. The screen coordinate system has its origin in the
     * <b>bottom</b> left, with the y-axis pointing <b>upwards</b> and the x-axis pointing to the right. This makes it easily
     * useable in conjunction with {@link Batch} and similar classes. This method allows you to specify the viewport position and
     * dimensions in the coordinate system expected by {@link GL20#glViewport(int, int, int, int)}, with the origin in the bottom
     * left corner of the screen.
     * @param viewportX the coordinate of the bottom left corner of the viewport in glViewport coordinates.
     * @param viewportY the coordinate of the bottom left corner of the viewport in glViewport coordinates.
     * @param viewportWidth the width of the viewport in pixels
     * @param viewportHeight the height of the viewport in pixels
     * @return the mutated and projected worldCoords {@link Vector3} */
    public Vector3 project (Vector3 worldCoords, float viewportX, float viewportY, float viewportWidth, float viewportHeight) {
        worldCoords.prj(combined);
        worldCoords.x = viewportWidth * (worldCoords.x + 1) / 2 + viewportX;
        worldCoords.y = viewportHeight * (worldCoords.y + 1) / 2 + viewportY;
        worldCoords.z = (worldCoords.z + 1) / 2;
        return worldCoords;
    }

}
