package com.github.jotask.voxelengine.math;

import java.nio.FloatBuffer;

/**
 * Vector4
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class Vector4 {

    public float x;
    public float y;
    public float z;
    public float w;

    public Vector4() {
        this(0f, 0f, 0f, 0f);
    }

    public Vector4(float x, float y, float z, float w){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /* (non-Javadoc)
     * @see org.lwjgl.util.vector.WritableVector4f#set(float, float, float, float)
     */
    public void set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * @return the length squared of the vector
     */
    public float lengthSquared() {
        return x * x + y * y + z * z + w * w;
    }

    /**
     * Translate a vector
     * @param x The translation in x
     * @param y the translation in y
     * @return this
     */
    public Vector4 translate(float x, float y, float z, float w) {
        this.x += x;
        this.y += y;
        this.z += z;
        this.w += w;
        return this;
    }

    /**
     * Add a vector to another vector and place the result in a destination
     * vector.
     * @param left The LHS vector
     * @param right The RHS vector
     * @param dest The destination vector, or null if a new vector is to be created
     * @return the sum of left and right in dest
     */
    public static Vector4 add(Vector4 left, Vector4 right, Vector4 dest) {
        if (dest == null)
            return new Vector4(left.x + right.x, left.y + right.y, left.z + right.z, left.w + right.w);
        else {
            dest.set(left.x + right.x, left.y + right.y, left.z + right.z, left.w + right.w);
            return dest;
        }
    }

    /**
     * Subtract a vector from another vector and place the result in a destination
     * vector.
     * @param left The LHS vector
     * @param right The RHS vector
     * @param dest The destination vector, or null if a new vector is to be created
     * @return left minus right in dest
     */
    public static Vector4 sub(Vector4 left, Vector4 right, Vector4 dest) {
        if (dest == null)
            return new Vector4(left.x - right.x, left.y - right.y, left.z - right.z, left.w - right.w);
        else {
            dest.set(left.x - right.x, left.y - right.y, left.z - right.z, left.w - right.w);
            return dest;
        }
    }


    /**
     * Negate a vector
     * @return this
     */
    public Vector4 negate() {
        x = -x;
        y = -y;
        z = -z;
        w = -w;
        return this;
    }

    /**
     * Negate a vector and place the result in a destination vector.
     * @param dest The destination vector or null if a new vector is to be created
     * @return the negated vector
     */
    public Vector4 negate(Vector4 dest) {
        if (dest == null)
            dest = new Vector4();
        dest.x = -x;
        dest.y = -y;
        dest.z = -z;
        dest.w = -w;
        return dest;
    }

    /**
     * @return the length of the vector
     */
    public final float length() {
        return (float) Math.sqrt(lengthSquared());
    }


    /**
     * Normalise this vector and place the result in another vector.
     * @param dest The destination vector, or null if a new vector is to be created
     * @return the normalised vector
     */
    public Vector4 normalise(Vector4 dest) {
        float l = length();

        if (dest == null)
            dest = new Vector4(x / l, y / l, z / l, w / l);
        else
            dest.set(x / l, y / l, z / l, w / l);

        return dest;
    }

    /**
     * The dot product of two vectors is calculated as
     * v1.x * v2.x + v1.y * v2.y + v1.z * v2.z + v1.w * v2.w
     * @param left The LHS vector
     * @param right The RHS vector
     * @return left dot right
     */
    public static float dot(Vector4 left, Vector4 right) {
        return left.x * right.x + left.y * right.y + left.z * right.z + left.w * right.w;
    }

    /**
     * Calculate the angle between two vectors, in radians
     * @param a A vector
     * @param b The other vector
     * @return the angle between the two vectors, in radians
     */
    public static float angle(Vector4 a, Vector4 b) {
        float dls = dot(a, b) / (a.length() * b.length());
        if (dls < -1f)
            dls = -1f;
        else if (dls > 1.0f)
            dls = 1.0f;
        return (float)Math.acos(dls);
    }

    /* (non-Javadoc)
     * @see org.lwjgl.vector.Vector#load(FloatBuffer)
     */
    public Vector4 load(FloatBuffer buf) {
        x = buf.get();
        y = buf.get();
        z = buf.get();
        w = buf.get();
        return this;
    }

    /* (non-Javadoc)
     * @see org.lwjgl.vector.Vector#scale(float)
     */
    public Vector4 scale(float scale) {
        x *= scale;
        y *= scale;
        z *= scale;
        w *= scale;
        return this;
    }

    /* (non-Javadoc)
     * @see org.lwjgl.vector.Vector#store(FloatBuffer)
     */
    public Vector4 store(FloatBuffer buf) {

        buf.put(x);
        buf.put(y);
        buf.put(z);
        buf.put(w);

        return this;
    }

}
