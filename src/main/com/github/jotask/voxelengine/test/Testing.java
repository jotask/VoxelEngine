package com.github.jotask.voxelengine.test;

import com.github.jotask.voxelengine.math.Matrix4;
import com.github.jotask.voxelengine.math.Vector3;

/**
 * Testing
 *
 * @author Jose Vives Iznardo
 * @since 31/05/2017
 */
public class Testing {

    public Testing() {

        float near = 1f;
        float far = 2f;
        float FOV = 3f;
        float aspect = 4f;
        Vector3 position = new Vector3();
        Vector3 direction = new Vector3();
        Vector3 up = new Vector3();

        Matrix4 combinedOne = new Matrix4();
        Matrix4f combinedTwo = new Matrix4f();
        {
            Matrix4 projection = new Matrix4();
            Matrix4 view = new Matrix4();
            projection.setToProjection(Math.abs(near), Math.abs(far), FOV, aspect);
            view.setToLookAt(position, new Vector3().set(position).add(direction), up);
            combinedOne.set(projection);
            System.out.println(combinedOne);
//            combinedOne.mul(view);
        }
        {
            Matrix4f projection = new Matrix4f();
            Matrix4f view = new Matrix4f();
            projection.setToProjection(Math.abs(near), Math.abs(far), FOV, aspect);
            view.setToLookAt(position, new Vector3().set(position).add(direction), up);
            combinedTwo.set(projection);
            System.out.println(combinedTwo);
//            combinedTwo.mul(view);
        }

        System.out.println(" ");
        printArray(combinedOne.getValues());
        printArray(combinedTwo.getValues());

    }

    private void printArray(float[] v){
        StringBuilder sb = new StringBuilder();
        sb.append("Size: " + v.length + " - {");
        for(int i = 0; i < v.length; i++) {
            sb.append(v[i] + ", ");
        }
        sb.append("]");
        System.out.println(sb);
    }

}
