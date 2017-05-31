package com.github.jotask.voxelengine.math;

/**
 * Math
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class Maths {

    private Maths(){}

    public static Matrix4 createTransformationMatrix(Vector3 translation, Vector3 rotation, Vector3 scale){
        // Kimo loves you!
        Matrix4 matrix = new Matrix4();
        Matrix4.translate(translation, matrix, matrix);
        Matrix4.rotate((float)Math.toRadians(rotation.x), new Vector3(1, 0, 0), matrix, matrix);
        Matrix4.rotate((float)Math.toRadians(rotation.y), new Vector3(0, 1, 0), matrix, matrix);
        Matrix4.rotate((float)Math.toRadians(rotation.z), new Vector3(0, 0, 1), matrix, matrix);
        Matrix4.scale(scale, matrix, matrix);
        return matrix;
    }

//    public static Matrix4 createViewMatrix(Camera camera){
//        Matrix4 matrix = new Matrix4();
//        matrix.setIdentity();
//        Matrix4.rotate((float)Math.toRadians(camera.getPitch()), new Vector3(1, 0, 0), matrix, matrix);
//        Matrix4.rotate((float)Math.toRadians(camera.getYaw()), new Vector3(0, 1, 0), matrix, matrix);
//        Matrix4.rotate((float)Math.toRadians(camera.getRoll()), new Vector3(0, 0, 1), matrix, matrix);
//        Vector3 negativePos = camera.getPosition().negate(new Vector3());
//        Matrix4.translate(negativePos, matrix, matrix);
//        return matrix;
//    }

    public static Matrix4 lookAt(Vector3 eye, Vector3 center, Vector3 up){
        Vector3 f = Vector3.sub(center, eye, null).normalise(null);
        Vector3 u = up.normalise(null);
        Vector3 s = Vector3.cross(f, u, null).normalise(null);

        u = Vector3.cross(s, f, null);

        Matrix4 result = new Matrix4();
        result.setIdentity();
        result.m00 = s.x;
        result.m10 = s.y;
        result.m20 = s.z;
        result.m01 = u.x;
        result.m11 = u.y;
        result.m21 = u.z;
        result.m02 = -f.x;
        result.m12 = -f.y;
        result.m22 = -f.z;

        return result.translate(eye.negate(null));

//        return translate(result, new Vector3(-eye.x,-eye.y,-eye.z));

    }

}
