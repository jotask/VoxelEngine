package com.github.jotask.voxelengine.flappy.math;

/**
 * Vector3
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class Vector3 {

    public float x;
    public float y;
    public float z;

    public Vector3() {
        this(0f, 0f, 0f);
    }

    public Vector3(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
