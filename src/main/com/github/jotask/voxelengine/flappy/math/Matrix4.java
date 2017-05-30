package com.github.jotask.voxelengine.flappy.math;

import com.github.jotask.voxelengine.flappy.BufferUtils;

import java.nio.FloatBuffer;

/**
 * Matrix4
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class Matrix4 {

    public static final int SIZE = 4 * 4;
    public float[] elements = new float[SIZE];

    public Matrix4(){

    }

    public static Matrix4 identity(){
        Matrix4 result = new Matrix4();
        for(int i = 0; i < SIZE; i++){
            result.elements[i] = 0f;
        }
        result.elements[0 + 0 * 4] = 1f;
        result.elements[1 + 1 * 4] = 1f;
        result.elements[2 + 2 * 4] = 1f;
        result.elements[3 + 3 * 4] = 1f;

        return result;
    }

    public static Matrix4 orthograhic(float left, float right, float bottom, float top, float near, float far){
        Matrix4 result = identity();
        result.elements[ 0 + 0 * 4] = 2f / (right - left);
        result.elements[ 1 + 1 * 4] = 2f / (top - bottom);
        result.elements[ 2 + 2 * 4] = 2f / (near - far);

        result.elements[ 0 + 3 * 4] = (left + right) / (left - right);
        result.elements[ 1 + 3 * 4] = (bottom + top) / (bottom - top);
        result.elements[ 2 + 3 * 4] = (far + near) / (far - near);

        return result;
    }

    public static Matrix4 translation(Vector3 vector){
        Matrix4 result = identity();
        result.elements[0 + 3 * 4] = vector.x;
        result.elements[1 + 3 * 4] = vector.y;
        result.elements[2 + 3 * 4] = vector.z;
        return result;
    }

    public static Matrix4 rotate(float angle){
        Matrix4 result = identity();
        float r = (float) Math.toRadians(angle);
        float cos = (float) Math.cos(r);
        float sin = (float) Math.sin(r);

        result.elements[0 + 0 * 4] = cos;
        result.elements[1 + 0 * 4] = sin;

        result.elements[0 + 1 * 4] = -sin;
        result.elements[1 + 1 * 4] = cos;

        return result;

    }

    public Matrix4 multiply(Matrix4 matrix){
        Matrix4 result = new Matrix4();
        for(int y = 0; y < 4; y++){
            for(int x = 0; x < 4; x++){
                float sum = 0f;
                for(int z = 0; z < 4; z++) {
                    sum += elements[x + z * 4] * matrix.elements[z + y * 4];
                }
                result.elements[x + y * 4] = sum;
            }
        }
        return result;
    }

    public FloatBuffer toFloatBuffer(){
        return BufferUtils.createFloatBuffer(elements);
    }

}
