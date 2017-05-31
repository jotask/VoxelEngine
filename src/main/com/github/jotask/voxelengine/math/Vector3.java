package com.github.jotask.voxelengine.math;

import java.nio.FloatBuffer;

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

    public Vector3(Vector3 other) {
        this(other.x, other.y, other.z);
    }

    public Vector3(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /** Sets this vector to the cross product between it and the other vector.
     * @param vector The other vector
     * @return This vector for chaining */
    public Vector3 crs (final Vector3 vector) {
        return this.set(y * vector.z - z * vector.y, z * vector.x - x * vector.z, x * vector.y - y * vector.x);
    }

    public float dot (final Vector3 vector) {
        return x * vector.x + y * vector.y + z * vector.z;
    }

    /** Multiplies this vector by the given matrix dividing by w, assuming the fourth (w) component of the vector is 1. This is
     * mostly used to project/unproject vectors via a perspective projection matrix.
     *
     * @param matrix The matrix.
     * @return This vector for chaining */
    public Vector3 prj (final Matrix4 matrix) {
        final float l_w = 1f / (x * matrix.m30 + y * matrix.m31 + z * matrix.m32 + matrix.m33);
        return this.set((x * matrix.m00 + y * matrix.m01 + z * matrix.m02 + matrix.m03) * l_w, (x
                * matrix.m10 + y * matrix.m11 + z * matrix.m12 + matrix.m13)
                * l_w, (x * matrix.m20 + y * matrix.m21 + z * matrix.m22 + matrix.m23) * l_w);
    }

    /** Sets this vector to the cross product between it and the other vector.
     * @param x The x-component of the other vector
     * @param y The y-component of the other vector
     * @param z The z-component of the other vector
     * @return This vector for chaining */
    public Vector3 crs (float x, float y, float z) {
        return this.set(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
    }

    /* (non-Javadoc)
     * @see org.lwjgl.util.vector.WritableVector2f#set(float, float)
     */
    public Vector3 set(Vector3 other) {
        return this.set(other.x, other.y, other.z);
    }

    /* (non-Javadoc)
     * @see org.lwjgl.util.vector.WritableVector2f#set(float, float)
     */
    public Vector3 set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vector3 nor () {
        final float len2 = this.lengthSquared();
        if (len2 == 0f || len2 == 1f) return this;
        return this.scl(1f / (float)Math.sqrt(len2));
    }

    public Vector3 scl (float scalar) {
        return this.set(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    /**
     * @return the length of the vector
     */
    public final float length() {
        return (float) Math.sqrt(lengthSquared());
    }


    /**
     * @return the length squared of the vector
     */
    public float lengthSquared() {
        return x * x + y * y + z * z;
    }

    /**
     * Translate a vector
     * @param x The translation in x
     * @param y the translation in y
     * @return this
     */
    public Vector3 translate(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public boolean isZero () {
        return x == 0 && y == 0 && z == 0;
    }

    public Vector3 sub (final Vector3 a_vec) {
        return this.sub(a_vec.x, a_vec.y, a_vec.z);
    }

    /** Subtracts the other vector from this vector.
     *
     * @param x The x-component of the other vector
     * @param y The y-component of the other vector
     * @param z The z-component of the other vector
     * @return This vector for chaining */
    public Vector3 sub (float x, float y, float z) {
        return this.set(this.x - x, this.y - y, this.z - z);
    }

    /**
     * Add a vector to another vector and place the result in a destination
     * vector.
     * @param left The LHS vector
     * @param right The RHS vector
     * @param dest The destination vector, or null if a new vector is to be created
     * @return the sum of left and right in dest
     */
    public static Vector3 add(Vector3 left, Vector3 right, Vector3 dest) {
        if (dest == null)
            return new Vector3(left.x + right.x, left.y + right.y, left.z + right.z);
        else {
            dest.set(left.x + right.x, left.y + right.y, left.z + right.z);
            return dest;
        }
    }

    public Vector3 add (final Vector3 vector) {
        return this.add(vector.x, vector.y, vector.z);
    }

    /** Adds the given vector to this component
     * @param x The x-component of the other vector
     * @param y The y-component of the other vector
     * @param z The z-component of the other vector
     * @return This vector for chaining. */
    public Vector3 add (float x, float y, float z) {
        return this.set(this.x + x, this.y + y, this.z + z);
    }

    /**
     * Subtract a vector from another vector and place the result in a destination
     * vector.
     * @param left The LHS vector
     * @param right The RHS vector
     * @param dest The destination vector, or null if a new vector is to be created
     * @return left minus right in dest
     */
    public static Vector3 sub(Vector3 left, Vector3 right, Vector3 dest) {
        if (dest == null)
            return new Vector3(left.x - right.x, left.y - right.y, left.z - right.z);
        else {
            dest.set(left.x - right.x, left.y - right.y, left.z - right.z);
            return dest;
        }
    }

    /**
     * The cross product of two vectors.
     *
     * @param left The LHS vector
     * @param right The RHS vector
     * @param dest The destination result, or null if a new vector is to be created
     * @return left cross right
     */
    public static Vector3 cross(
            Vector3 left,
            Vector3 right,
            Vector3 dest)
    {

        if (dest == null)
            dest = new Vector3();

        dest.set(
                left.y * right.z - left.z * right.y,
                right.x * left.z - right.z * left.x,
                left.x * right.y - left.y * right.x
        );

        return dest;
    }

    /**
     * Negate a vector
     * @return this
     */
    public Vector3 negate() {
        x = -x;
        y = -y;
        z = -z;
        return this;
    }

    /**
     * Negate a vector and place the result in a destination vector.
     * @param dest The destination vector or null if a new vector is to be created
     * @return the negated vector
     */
    public Vector3 negate(Vector3 dest) {
        if (dest == null)
            dest = new Vector3();
        dest.x = -x;
        dest.y = -y;
        dest.z = -z;
        return dest;
    }


    /**
     * Normalise this vector and place the result in another vector.
     * @param dest The destination vector, or null if a new vector is to be created
     * @return the normalised vector
     */
    public Vector3 normalise(Vector3 dest) {
        float l = length();

        if (dest == null)
            dest = new Vector3(x / l, y / l, z / l);
        else
            dest.set(x / l, y / l, z / l);

        return dest;
    }

    /**
     * The dot product of two vectors is calculated as
     * v1.x * v2.x + v1.y * v2.y + v1.z * v2.z
     * @param left The LHS vector
     * @param right The RHS vector
     * @return left dot right
     */
    public static float dot(Vector3 left, Vector3 right) {
        return left.x * right.x + left.y * right.y + left.z * right.z;
    }

    /**
     * Calculate the angle between two vectors, in radians
     * @param a A vector
     * @param b The other vector
     * @return the angle between the two vectors, in radians
     */
    public static float angle(Vector3 a, Vector3 b) {
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
    public Vector3 load(FloatBuffer buf) {
        x = buf.get();
        y = buf.get();
        z = buf.get();
        return this;
    }

    /* (non-Javadoc)
     * @see org.lwjgl.vector.Vector#scale(float)
     */
    public Vector3 scale(float scale) {

        x *= scale;
        y *= scale;
        z *= scale;

        return this;

    }

    /* (non-Javadoc)
     * @see org.lwjgl.vector.Vector#store(FloatBuffer)
     */
    public Vector3 store(FloatBuffer buf) {

        buf.put(x);
        buf.put(y);
        buf.put(z);

        return this;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(64);

        sb.append("Vector3f[");
        sb.append(x);
        sb.append(", ");
        sb.append(y);
        sb.append(", ");
        sb.append(z);
        sb.append(']');
        return sb.toString();
    }

}
