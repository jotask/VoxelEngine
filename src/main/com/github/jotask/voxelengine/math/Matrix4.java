package com.github.jotask.voxelengine.math;/*
 * Copyright (c) 2002-2008 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.nio.FloatBuffer;

/**
 * Holds a 4x4 float matrix.
 *
 * @author foo
 */
public class Matrix4 {

    public float m00, m01, m02, m03, m10, m11, m12, m13, m20, m21, m22, m23, m30, m31, m32, m33;

    /**
     * Construct a new matrix, initialized to the identity.
     */
    public Matrix4() {
        setIdentity();
    }

    public Matrix4(final Matrix4 src) {
        super();
        load(src);
    }

    /**
     * Returns a string representation of this matrix
     */
    public String toString() {
        return String.valueOf(m00) + ' ' + m10 + ' ' + m20 + ' ' + m30 + '\n' +
                m01 + ' ' + m11 + ' ' + m21 + ' ' + m31 + '\n' +
                m02 + ' ' + m12 + ' ' + m22 + ' ' + m32 + '\n' +
                m03 + ' ' + m13 + ' ' + m23 + ' ' + m33 + '\n';
    }

    /**
     * Set this matrix to be the identity matrix.
     * @return this
     */
    public Matrix4 setIdentity() {
        return setIdentity(this);
    }

    /**
     * Set the given matrix to be the identity matrix.
     * @param m The matrix to set to the identity
     * @return m
     */
    public static Matrix4 setIdentity(Matrix4 m) {
        m.m00 = 1.0f;
        m.m01 = 0.0f;
        m.m02 = 0.0f;
        m.m03 = 0.0f;
        m.m10 = 0.0f;
        m.m11 = 1.0f;
        m.m12 = 0.0f;
        m.m13 = 0.0f;
        m.m20 = 0.0f;
        m.m21 = 0.0f;
        m.m22 = 1.0f;
        m.m23 = 0.0f;
        m.m30 = 0.0f;
        m.m31 = 0.0f;
        m.m32 = 0.0f;
        m.m33 = 1.0f;

        return m;
    }

    public void set(Matrix4 other){
        this.m00 = other.m00;
        this.m01 = other.m01;
        this.m02 = other.m02;
        this.m03 = other.m03;
        this.m10 = other.m10;
        this.m11 = other.m11;
        this.m12 = other.m12;
        this.m13 = other.m13;
        this.m20 = other.m20;
        this.m21 = other.m21;
        this.m22 = other.m22;
        this.m23 = other.m23;
        this.m30 = other.m30;
        this.m31 = other.m31;
        this.m32 = other.m32;
        this.m33 = other.m33;
    }

    /**
     * Set this matrix to 0.
     * @return this
     */
    public Matrix4 setZero() {
        return setZero(this);
    }

    /**
     * Set the given matrix to 0.
     * @param m The matrix to set to 0
     * @return m
     */
    public static Matrix4 setZero(Matrix4 m) {
        m.m00 = 0.0f;
        m.m01 = 0.0f;
        m.m02 = 0.0f;
        m.m03 = 0.0f;
        m.m10 = 0.0f;
        m.m11 = 0.0f;
        m.m12 = 0.0f;
        m.m13 = 0.0f;
        m.m20 = 0.0f;
        m.m21 = 0.0f;
        m.m22 = 0.0f;
        m.m23 = 0.0f;
        m.m30 = 0.0f;
        m.m31 = 0.0f;
        m.m32 = 0.0f;
        m.m33 = 0.0f;

        return m;
    }

    /**
     * Load from another matrix4f
     * @param src The source matrix
     * @return this
     */
    public Matrix4 load(Matrix4 src) {
        return load(src, this);
    }

    /**
     * Copy the source matrix to the destination matrix
     * @param src The source matrix
     * @param dest The destination matrix, or null of a new one is to be created
     * @return The copied matrix
     */
    public static Matrix4 load(Matrix4 src, Matrix4 dest) {
        if (dest == null)
            dest = new Matrix4();
        dest.m00 = src.m00;
        dest.m01 = src.m01;
        dest.m02 = src.m02;
        dest.m03 = src.m03;
        dest.m10 = src.m10;
        dest.m11 = src.m11;
        dest.m12 = src.m12;
        dest.m13 = src.m13;
        dest.m20 = src.m20;
        dest.m21 = src.m21;
        dest.m22 = src.m22;
        dest.m23 = src.m23;
        dest.m30 = src.m30;
        dest.m31 = src.m31;
        dest.m32 = src.m32;
        dest.m33 = src.m33;

        return dest;
    }

    /**
     * Load from a float buffer. The buffer stores the matrix in column major
     * (OpenGL) order.
     *
     * @param buf A float buffer to read from
     * @return this
     */
    public Matrix4 load(FloatBuffer buf) {

        m00 = buf.get();
        m01 = buf.get();
        m02 = buf.get();
        m03 = buf.get();
        m10 = buf.get();
        m11 = buf.get();
        m12 = buf.get();
        m13 = buf.get();
        m20 = buf.get();
        m21 = buf.get();
        m22 = buf.get();
        m23 = buf.get();
        m30 = buf.get();
        m31 = buf.get();
        m32 = buf.get();
        m33 = buf.get();

        return this;
    }

    /**
     * Load from a float buffer. The buffer stores the matrix in row major
     * (maths) order.
     *
     * @param buf A float buffer to read from
     * @return this
     */
    public Matrix4 loadTranspose(FloatBuffer buf) {

        m00 = buf.get();
        m10 = buf.get();
        m20 = buf.get();
        m30 = buf.get();
        m01 = buf.get();
        m11 = buf.get();
        m21 = buf.get();
        m31 = buf.get();
        m02 = buf.get();
        m12 = buf.get();
        m22 = buf.get();
        m32 = buf.get();
        m03 = buf.get();
        m13 = buf.get();
        m23 = buf.get();
        m33 = buf.get();

        return this;
    }

    /** Sets the matrix to a projection matrix with a near- and far plane, a field of view in degrees and an aspect ratio. Note that
     * the field of view specified is the angle in degrees for the height, the field of view for the width will be calculated
     * according to the aspect ratio.
     *
     * @param near The near plane
     * @param far The far plane
     * @param fovy The field of view of the height in degrees
     * @param aspectRatio The "width over height" aspect ratio
     * @return This matrix for the purpose of chaining methods together. */
    public Matrix4 setToProjection (float near, float far, float fovy, float aspectRatio) {
        this.setIdentity();
        float l_fd = (float)(1.0 / Math.tan((fovy * (Math.PI / 180)) / 2.0));
        float l_a1 = (far + near) / (near - far);
        float l_a2 = (2 * far * near) / (near - far);
        m00 = l_fd / aspectRatio;
        m10 = 0;
        m20 = 0;
        m30 = 0;
        m01 = 0;
        m11 = l_fd;
        m21 = 0;
        m31 = 0;
        m02 = 0;
        m12 = 0;
        m22 = l_a1;
        m23 = -1;
        m03 = 0;
        m13 = 0;
        m32 = l_a2;
        m33 = 0;

        return this;
    }

    /** Sets the matrix to a look at matrix with a direction and an up vector. Multiply with a translation matrix to get a camera
     * model view matrix.
     *
     * @param direction The direction vector
     * @param up The up vector
     * @return This matrix for the purpose of chaining methods together. */
    public Matrix4 setToLookAt (Vector3 direction, Vector3 up) {
        Vector3 l_vez = new Vector3().set(direction).nor();
        Vector3 l_vex = new Vector3().set(direction).nor();
        l_vex.crs(up).nor();
        Vector3 l_vey = new Vector3().set(l_vex).crs(l_vez).nor();
        this.setIdentity();

        m00 = l_vex.x;
        m10 = l_vex.y;
        m20 = l_vex.z;

        m01 = l_vey.x;
        m11 = l_vey.y;
        m21 = l_vey.z;

        m02 = -l_vez.x;
        m12 = -l_vez.y;
        m22 = -l_vez.z;

        return this;
    }
    /** Sets this matrix to a look at matrix with the given position, target and up vector.
     *
     * @param position the position
     * @param target the target
     * @param up the up vector
     * @return This matrix */
    public Matrix4 setToLookAt (Vector3 position, Vector3 target, Vector3 up) {
        Vector3 tmp = new Vector3(target).sub(position);
        setToLookAt(tmp, up);
        this.mul(new Matrix4().setToTranslation(-position.x, -position.y, -position.z));
        return this;
    }

    public Matrix4 mul (Matrix4 matrix){
        mul(this, matrix);
        return this;
    }

    /** Sets this matrix to a translation matrix, overwriting it first by an identity matrix and then setting the 4th column to the
     * translation vector.
     *
     * @param x The x-component of the translation vector.
     * @param y The y-component of the translation vector.
     * @param z The z-component of the translation vector.
     * @return This matrix for the purpose of chaining methods together. */
    public Matrix4 setToTranslation (float x, float y, float z) {
        this.setIdentity();
        m03 = x;
        m13 = y;
        m23 = z;
        return this;
    }


    /**
     * Store this matrix in a float buffer. The matrix is stored in column
     * major (openGL) order.
     * @param buf The buffer to store this matrix in
     */
    public Matrix4 store(FloatBuffer buf) {
        buf.put(m00);
        buf.put(m01);
        buf.put(m02);
        buf.put(m03);
        buf.put(m10);
        buf.put(m11);
        buf.put(m12);
        buf.put(m13);
        buf.put(m20);
        buf.put(m21);
        buf.put(m22);
        buf.put(m23);
        buf.put(m30);
        buf.put(m31);
        buf.put(m32);
        buf.put(m33);
        return this;
    }

    /**
     * Store this matrix in a float buffer. The matrix is stored in row
     * major (maths) order.
     * @param buf The buffer to store this matrix in
     */
    public Matrix4 storeTranspose(FloatBuffer buf) {
        buf.put(m00);
        buf.put(m10);
        buf.put(m20);
        buf.put(m30);
        buf.put(m01);
        buf.put(m11);
        buf.put(m21);
        buf.put(m31);
        buf.put(m02);
        buf.put(m12);
        buf.put(m22);
        buf.put(m32);
        buf.put(m03);
        buf.put(m13);
        buf.put(m23);
        buf.put(m33);
        return this;
    }

    /**
     * Store the rotation portion of this matrix in a float buffer. The matrix is stored in column
     * major (openGL) order.
     * @param buf The buffer to store this matrix in
     */
    public Matrix4 store3f(FloatBuffer buf) {
        buf.put(m00);
        buf.put(m01);
        buf.put(m02);
        buf.put(m10);
        buf.put(m11);
        buf.put(m12);
        buf.put(m20);
        buf.put(m21);
        buf.put(m22);
        return this;
    }

    /**
     * Add two matrices together and place the result in a third matrix.
     * @param left The left source matrix
     * @param right The right source matrix
     * @param dest The destination matrix, or null if a new one is to be created
     * @return the destination matrix
     */
    public static Matrix4 add(Matrix4 left, Matrix4 right, Matrix4 dest) {
        if (dest == null)
            dest = new Matrix4();

        dest.m00 = left.m00 + right.m00;
        dest.m01 = left.m01 + right.m01;
        dest.m02 = left.m02 + right.m02;
        dest.m03 = left.m03 + right.m03;
        dest.m10 = left.m10 + right.m10;
        dest.m11 = left.m11 + right.m11;
        dest.m12 = left.m12 + right.m12;
        dest.m13 = left.m13 + right.m13;
        dest.m20 = left.m20 + right.m20;
        dest.m21 = left.m21 + right.m21;
        dest.m22 = left.m22 + right.m22;
        dest.m23 = left.m23 + right.m23;
        dest.m30 = left.m30 + right.m30;
        dest.m31 = left.m31 + right.m31;
        dest.m32 = left.m32 + right.m32;
        dest.m33 = left.m33 + right.m33;

        return dest;
    }

    /**
     * Subtract the right matrix from the left and place the result in a third matrix.
     * @param left The left source matrix
     * @param right The right source matrix
     * @param dest The destination matrix, or null if a new one is to be created
     * @return the destination matrix
     */
    public static Matrix4 sub(Matrix4 left, Matrix4 right, Matrix4 dest) {
        if (dest == null)
            dest = new Matrix4();

        dest.m00 = left.m00 - right.m00;
        dest.m01 = left.m01 - right.m01;
        dest.m02 = left.m02 - right.m02;
        dest.m03 = left.m03 - right.m03;
        dest.m10 = left.m10 - right.m10;
        dest.m11 = left.m11 - right.m11;
        dest.m12 = left.m12 - right.m12;
        dest.m13 = left.m13 - right.m13;
        dest.m20 = left.m20 - right.m20;
        dest.m21 = left.m21 - right.m21;
        dest.m22 = left.m22 - right.m22;
        dest.m23 = left.m23 - right.m23;
        dest.m30 = left.m30 - right.m30;
        dest.m31 = left.m31 - right.m31;
        dest.m32 = left.m32 - right.m32;
        dest.m33 = left.m33 - right.m33;

        return dest;
    }

    /**
     * Multiply the right matrix by the left and place the result in a third matrix.
     * @param left The left source matrix
     * @param right The right source matrix
     * @return the destination matrix
     */
    public Matrix4 mul(Matrix4 left, Matrix4 right) {

        float m00 = left.m00 * right.m00 + left.m10 * right.m01 + left.m20 * right.m02 + left.m30 * right.m03;
        float m01 = left.m01 * right.m00 + left.m11 * right.m01 + left.m21 * right.m02 + left.m31 * right.m03;
        float m02 = left.m02 * right.m00 + left.m12 * right.m01 + left.m22 * right.m02 + left.m32 * right.m03;
        float m03 = left.m03 * right.m00 + left.m13 * right.m01 + left.m23 * right.m02 + left.m33 * right.m03;
        float m10 = left.m00 * right.m10 + left.m10 * right.m11 + left.m20 * right.m12 + left.m30 * right.m13;
        float m11 = left.m01 * right.m10 + left.m11 * right.m11 + left.m21 * right.m12 + left.m31 * right.m13;
        float m12 = left.m02 * right.m10 + left.m12 * right.m11 + left.m22 * right.m12 + left.m32 * right.m13;
        float m13 = left.m03 * right.m10 + left.m13 * right.m11 + left.m23 * right.m12 + left.m33 * right.m13;
        float m20 = left.m00 * right.m20 + left.m10 * right.m21 + left.m20 * right.m22 + left.m30 * right.m23;
        float m21 = left.m01 * right.m20 + left.m11 * right.m21 + left.m21 * right.m22 + left.m31 * right.m23;
        float m22 = left.m02 * right.m20 + left.m12 * right.m21 + left.m22 * right.m22 + left.m32 * right.m23;
        float m23 = left.m03 * right.m20 + left.m13 * right.m21 + left.m23 * right.m22 + left.m33 * right.m23;
        float m30 = left.m00 * right.m30 + left.m10 * right.m31 + left.m20 * right.m32 + left.m30 * right.m33;
        float m31 = left.m01 * right.m30 + left.m11 * right.m31 + left.m21 * right.m32 + left.m31 * right.m33;
        float m32 = left.m02 * right.m30 + left.m12 * right.m31 + left.m22 * right.m32 + left.m32 * right.m33;
        float m33 = left.m03 * right.m30 + left.m13 * right.m31 + left.m23 * right.m32 + left.m33 * right.m33;

        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m03 = m03;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m30 = m30;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;

        return this;
    }

    /**
     * Transform a Vector by a matrix and return the result in a destination
     * vector.
     * @param left The left matrix
     * @param right The right vector
     * @param dest The destination vector, or null if a new one is to be created
     * @return the destination vector
     */
    public static Vector4 transform(Matrix4 left, Vector4 right, Vector4 dest) {
        if (dest == null)
            dest = new Vector4();

        float x = left.m00 * right.x + left.m10 * right.y + left.m20 * right.z + left.m30 * right.w;
        float y = left.m01 * right.x + left.m11 * right.y + left.m21 * right.z + left.m31 * right.w;
        float z = left.m02 * right.x + left.m12 * right.y + left.m22 * right.z + left.m32 * right.w;
        float w = left.m03 * right.x + left.m13 * right.y + left.m23 * right.z + left.m33 * right.w;

        dest.x = x;
        dest.y = y;
        dest.z = z;
        dest.w = w;

        return dest;
    }

    /**
     * Transpose this matrix
     * @return this
     */
    public Matrix4 transpose() {
        return transpose(this);
    }

    /**
     * Translate this matrix
     * @param vec The vector to translate by
     * @return this
     */
    public Matrix4 translate(Vector3 vec) {
        return translate(vec, this);
    }

    /**
     * Scales this matrix
     * @param vec The vector to scale by
     * @return this
     */
    public Matrix4 scale(Vector3 vec) {
        return scale(vec, this, this);
    }

    /**
     * Scales the source matrix and put the result in the destination matrix
     * @param vec The vector to scale by
     * @param src The source matrix
     * @param dest The destination matrix, or null if a new matrix is to be created
     * @return The scaled matrix
     */
    public static Matrix4 scale(Vector3 vec, Matrix4 src, Matrix4 dest) {
        if (dest == null)
            dest = new Matrix4();
        dest.m00 = src.m00 * vec.x;
        dest.m01 = src.m01 * vec.x;
        dest.m02 = src.m02 * vec.x;
        dest.m03 = src.m03 * vec.x;
        dest.m10 = src.m10 * vec.y;
        dest.m11 = src.m11 * vec.y;
        dest.m12 = src.m12 * vec.y;
        dest.m13 = src.m13 * vec.y;
        dest.m20 = src.m20 * vec.z;
        dest.m21 = src.m21 * vec.z;
        dest.m22 = src.m22 * vec.z;
        dest.m23 = src.m23 * vec.z;
        return dest;
    }

    /**
     * Rotates the matrix around the given axis the specified angle
     * @param angle the angle, in radians.
     * @param axis The vector representing the rotation axis. Must be normalized.
     * @return this
     */
    public Matrix4 rotate(float angle, Vector3 axis) {
        return rotate(angle, axis, this);
    }

    /**
     * Rotates the matrix around the given axis the specified angle
     * @param angle the angle, in radians.
     * @param axis The vector representing the rotation axis. Must be normalized.
     * @param dest The matrix to put the result, or null if a new matrix is to be created
     * @return The rotated matrix
     */
    public Matrix4 rotate(float angle, Vector3 axis, Matrix4 dest) {
        return rotate(angle, axis, this, dest);
    }

    /**
     * Rotates the source matrix around the given axis the specified angle and
     * put the result in the destination matrix.
     * @param angle the angle, in radians.
     * @param axis The vector representing the rotation axis. Must be normalized.
     * @param src The matrix to rotate
     * @param dest The matrix to put the result, or null if a new matrix is to be created
     * @return The rotated matrix
     */
    public static Matrix4 rotate(float angle, Vector3 axis, Matrix4 src, Matrix4 dest) {
        if (dest == null)
            dest = new Matrix4();
        float c = (float) Math.cos(angle);
        float s = (float) Math.sin(angle);
        float oneminusc = 1.0f - c;
        float xy = axis.x*axis.y;
        float yz = axis.y*axis.z;
        float xz = axis.x*axis.z;
        float xs = axis.x*s;
        float ys = axis.y*s;
        float zs = axis.z*s;

        float f00 = axis.x*axis.x*oneminusc+c;
        float f01 = xy*oneminusc+zs;
        float f02 = xz*oneminusc-ys;
        // n[3] not used
        float f10 = xy*oneminusc-zs;
        float f11 = axis.y*axis.y*oneminusc+c;
        float f12 = yz*oneminusc+xs;
        // n[7] not used
        float f20 = xz*oneminusc+ys;
        float f21 = yz*oneminusc-xs;
        float f22 = axis.z*axis.z*oneminusc+c;

        float t00 = src.m00 * f00 + src.m10 * f01 + src.m20 * f02;
        float t01 = src.m01 * f00 + src.m11 * f01 + src.m21 * f02;
        float t02 = src.m02 * f00 + src.m12 * f01 + src.m22 * f02;
        float t03 = src.m03 * f00 + src.m13 * f01 + src.m23 * f02;
        float t10 = src.m00 * f10 + src.m10 * f11 + src.m20 * f12;
        float t11 = src.m01 * f10 + src.m11 * f11 + src.m21 * f12;
        float t12 = src.m02 * f10 + src.m12 * f11 + src.m22 * f12;
        float t13 = src.m03 * f10 + src.m13 * f11 + src.m23 * f12;
        dest.m20 = src.m00 * f20 + src.m10 * f21 + src.m20 * f22;
        dest.m21 = src.m01 * f20 + src.m11 * f21 + src.m21 * f22;
        dest.m22 = src.m02 * f20 + src.m12 * f21 + src.m22 * f22;
        dest.m23 = src.m03 * f20 + src.m13 * f21 + src.m23 * f22;
        dest.m00 = t00;
        dest.m01 = t01;
        dest.m02 = t02;
        dest.m03 = t03;
        dest.m10 = t10;
        dest.m11 = t11;
        dest.m12 = t12;
        dest.m13 = t13;
        return dest;
    }

    /**
     * Translate this matrix and stash the result in another matrix
     * @param vec The vector to translate by
     * @param dest The destination matrix or null if a new matrix is to be created
     * @return the translated matrix
     */
    public Matrix4 translate(Vector3 vec, Matrix4 dest) {
        return translate(vec, this, dest);
    }

    /**
     * Translate the source matrix and stash the result in the destination matrix
     * @param vec The vector to translate by
     * @param src The source matrix
     * @param dest The destination matrix or null if a new matrix is to be created
     * @return The translated matrix
     */
    public static Matrix4 translate(Vector3 vec, Matrix4 src, Matrix4 dest) {
        if (dest == null)
            dest = new Matrix4();

        dest.m30 += src.m00 * vec.x + src.m10 * vec.y + src.m20 * vec.z;
        dest.m31 += src.m01 * vec.x + src.m11 * vec.y + src.m21 * vec.z;
        dest.m32 += src.m02 * vec.x + src.m12 * vec.y + src.m22 * vec.z;
        dest.m33 += src.m03 * vec.x + src.m13 * vec.y + src.m23 * vec.z;

        return dest;
    }

    /**
     * Transpose this matrix and place the result in another matrix
     * @param dest The destination matrix or null if a new matrix is to be created
     * @return the transposed matrix
     */
    public Matrix4 transpose(Matrix4 dest) {
        return transpose(this, dest);
    }

    /**
     * Transpose the source matrix and place the result in the destination matrix
     * @param src The source matrix
     * @param dest The destination matrix or null if a new matrix is to be created
     * @return the transposed matrix
     */
    public static Matrix4 transpose(Matrix4 src, Matrix4 dest) {
        if (dest == null)
            dest = new Matrix4();
        float m00 = src.m00;
        float m01 = src.m10;
        float m02 = src.m20;
        float m03 = src.m30;
        float m10 = src.m01;
        float m11 = src.m11;
        float m12 = src.m21;
        float m13 = src.m31;
        float m20 = src.m02;
        float m21 = src.m12;
        float m22 = src.m22;
        float m23 = src.m32;
        float m30 = src.m03;
        float m31 = src.m13;
        float m32 = src.m23;
        float m33 = src.m33;

        dest.m00 = m00;
        dest.m01 = m01;
        dest.m02 = m02;
        dest.m03 = m03;
        dest.m10 = m10;
        dest.m11 = m11;
        dest.m12 = m12;
        dest.m13 = m13;
        dest.m20 = m20;
        dest.m21 = m21;
        dest.m22 = m22;
        dest.m23 = m23;
        dest.m30 = m30;
        dest.m31 = m31;
        dest.m32 = m32;
        dest.m33 = m33;

        return dest;
    }

    /**
     * @return the determinant of the matrix
     */
    public float determinant() {
        float f =
                m00
                        * ((m11 * m22 * m33 + m12 * m23 * m31 + m13 * m21 * m32)
                        - m13 * m22 * m31
                        - m11 * m23 * m32
                        - m12 * m21 * m33);
        f -= m01
                * ((m10 * m22 * m33 + m12 * m23 * m30 + m13 * m20 * m32)
                - m13 * m22 * m30
                - m10 * m23 * m32
                - m12 * m20 * m33);
        f += m02
                * ((m10 * m21 * m33 + m11 * m23 * m30 + m13 * m20 * m31)
                - m13 * m21 * m30
                - m10 * m23 * m31
                - m11 * m20 * m33);
        f -= m03
                * ((m10 * m21 * m32 + m11 * m22 * m30 + m12 * m20 * m31)
                - m12 * m21 * m30
                - m10 * m22 * m31
                - m11 * m20 * m32);
        return f;
    }

    /**
     * Calculate the determinant of a 3x3 matrix
     * @return result
     */

    private static float determinant3x3(float t00, float t01, float t02,
                                        float t10, float t11, float t12,
                                        float t20, float t21, float t22)
    {
        return   t00 * (t11 * t22 - t12 * t21)
                + t01 * (t12 * t20 - t10 * t22)
                + t02 * (t10 * t21 - t11 * t20);
    }

    /**
     * Invert this matrix
     * @return this if successful, null otherwise
     */
    public Matrix4 invert() {
        return invert(this, this);
    }

    /**
     * Invert the source matrix and put the result in the destination
     * @param src The source matrix
     * @param dest The destination matrix, or null if a new matrix is to be created
     * @return The inverted matrix if successful, null otherwise
     */
    public static Matrix4 invert(Matrix4 src, Matrix4 dest) {
        float determinant = src.determinant();

        if (determinant != 0) {
			/*
			 * m00 m01 m02 m03
			 * m10 m11 m12 m13
			 * m20 m21 m22 m23
			 * m30 m31 m32 m33
			 */
            if (dest == null)
                dest = new Matrix4();
            float determinant_inv = 1f/determinant;

            // first row
            float t00 =  determinant3x3(src.m11, src.m12, src.m13, src.m21, src.m22, src.m23, src.m31, src.m32, src.m33);
            float t01 = -determinant3x3(src.m10, src.m12, src.m13, src.m20, src.m22, src.m23, src.m30, src.m32, src.m33);
            float t02 =  determinant3x3(src.m10, src.m11, src.m13, src.m20, src.m21, src.m23, src.m30, src.m31, src.m33);
            float t03 = -determinant3x3(src.m10, src.m11, src.m12, src.m20, src.m21, src.m22, src.m30, src.m31, src.m32);
            // second row
            float t10 = -determinant3x3(src.m01, src.m02, src.m03, src.m21, src.m22, src.m23, src.m31, src.m32, src.m33);
            float t11 =  determinant3x3(src.m00, src.m02, src.m03, src.m20, src.m22, src.m23, src.m30, src.m32, src.m33);
            float t12 = -determinant3x3(src.m00, src.m01, src.m03, src.m20, src.m21, src.m23, src.m30, src.m31, src.m33);
            float t13 =  determinant3x3(src.m00, src.m01, src.m02, src.m20, src.m21, src.m22, src.m30, src.m31, src.m32);
            // third row
            float t20 =  determinant3x3(src.m01, src.m02, src.m03, src.m11, src.m12, src.m13, src.m31, src.m32, src.m33);
            float t21 = -determinant3x3(src.m00, src.m02, src.m03, src.m10, src.m12, src.m13, src.m30, src.m32, src.m33);
            float t22 =  determinant3x3(src.m00, src.m01, src.m03, src.m10, src.m11, src.m13, src.m30, src.m31, src.m33);
            float t23 = -determinant3x3(src.m00, src.m01, src.m02, src.m10, src.m11, src.m12, src.m30, src.m31, src.m32);
            // fourth row
            float t30 = -determinant3x3(src.m01, src.m02, src.m03, src.m11, src.m12, src.m13, src.m21, src.m22, src.m23);
            float t31 =  determinant3x3(src.m00, src.m02, src.m03, src.m10, src.m12, src.m13, src.m20, src.m22, src.m23);
            float t32 = -determinant3x3(src.m00, src.m01, src.m03, src.m10, src.m11, src.m13, src.m20, src.m21, src.m23);
            float t33 =  determinant3x3(src.m00, src.m01, src.m02, src.m10, src.m11, src.m12, src.m20, src.m21, src.m22);

            // transpose and divide by the determinant
            dest.m00 = t00*determinant_inv;
            dest.m11 = t11*determinant_inv;
            dest.m22 = t22*determinant_inv;
            dest.m33 = t33*determinant_inv;
            dest.m01 = t10*determinant_inv;
            dest.m10 = t01*determinant_inv;
            dest.m20 = t02*determinant_inv;
            dest.m02 = t20*determinant_inv;
            dest.m12 = t21*determinant_inv;
            dest.m21 = t12*determinant_inv;
            dest.m03 = t30*determinant_inv;
            dest.m30 = t03*determinant_inv;
            dest.m13 = t31*determinant_inv;
            dest.m31 = t13*determinant_inv;
            dest.m32 = t23*determinant_inv;
            dest.m23 = t32*determinant_inv;
            return dest;
        } else
            return null;
    }

    /**
     * Negate this matrix
     * @return this
     */
    public Matrix4 negate() {
        return negate(this);
    }

    /**
     * Negate this matrix and place the result in a destination matrix.
     * @param dest The destination matrix, or null if a new matrix is to be created
     * @return the negated matrix
     */
    public Matrix4 negate(Matrix4 dest) {
        return negate(this, dest);
    }

    /**
     * Negate this matrix and place the result in a destination matrix.
     * @param src The source matrix
     * @param dest The destination matrix, or null if a new matrix is to be created
     * @return The negated matrix
     */
    public static Matrix4 negate(Matrix4 src, Matrix4 dest) {
        if (dest == null)
            dest = new Matrix4();

        dest.m00 = -src.m00;
        dest.m01 = -src.m01;
        dest.m02 = -src.m02;
        dest.m03 = -src.m03;
        dest.m10 = -src.m10;
        dest.m11 = -src.m11;
        dest.m12 = -src.m12;
        dest.m13 = -src.m13;
        dest.m20 = -src.m20;
        dest.m21 = -src.m21;
        dest.m22 = -src.m22;
        dest.m23 = -src.m23;
        dest.m30 = -src.m30;
        dest.m31 = -src.m31;
        dest.m32 = -src.m32;
        dest.m33 = -src.m33;

        return dest;
    }

    public static Matrix4 mult(Matrix4 a, Matrix4 b){
        Matrix4 tmp = new Matrix4();
        tmp.m00 = (a.m00 * b.m00) + (a.m10 * b.m01) + (a.m20 * b.m02) + (a.m30 * b.m03);
        tmp.m00 = a.m00 * b.m00 + a.m01 * b.m10 + a.m02 * b.m20 + a.m03 * b.m30;
        tmp.m01 = a.m00 * b.m01 + a.m01 * b.m11 + a.m02 * b.m21 + a.m03 * b.m31;
        tmp.m02 = a.m00 * b.m02 + a.m01 * b.m12 + a.m02 * b.m22 + a.m03 * b.m32;
        tmp.m03 = a.m00 * b.m03 + a.m01 * b.m13 + a.m02 * b.m23 + a.m03 * b.m33;
        tmp.m10 = a.m10 * b.m00 + a.m11 * b.m10 + a.m12 * b.m20 + a.m13 * b.m30;
        tmp.m11 = a.m10 * b.m01 + a.m11 * b.m11 + a.m12 * b.m21 + a.m13 * b.m31;
        tmp.m12 = a.m10 * b.m02 + a.m11 * b.m12 + a.m12 * b.m22 + a.m13 * b.m32;
        tmp.m13 = a.m10 * b.m03 + a.m11 * b.m13 + a.m12 * b.m23 + a.m13 * b.m33;
        tmp.m20 = a.m20 * b.m00 + a.m21 * b.m10 + a.m22 * b.m20 + a.m23 * b.m30;
        tmp.m21 = a.m20 * b.m01 + a.m21 * b.m11 + a.m22 * b.m21 + a.m23 * b.m31;
        tmp.m22 = a.m20 * b.m02 + a.m21 * b.m12 + a.m22 * b.m22 + a.m23 * b.m32;
        tmp.m23 = a.m20 * b.m03 + a.m21 * b.m13 + a.m22 * b.m23 + a.m23 * b.m33;
        tmp.m30 = a.m30 * b.m00 + a.m31 * b.m10 + a.m32 * b.m20 + a.m33 * b.m30;
        tmp.m31 = a.m30 * b.m01 + a.m31 * b.m11 + a.m32 * b.m21 + a.m33 * b.m31;
        tmp.m32 = a.m30 * b.m02 + a.m31 * b.m12 + a.m32 * b.m22 + a.m33 * b.m32;
        tmp.m33 = a.m30 * b.m03 + a.m31 * b.m13 + a.m32 * b.m23 + a.m33 * b.m33;
        a.set(tmp);
//        System.out.println(a);
        return a;
    }

    public float[] getValues(){
        float[] tmp = new float[16];
        tmp[0] = m00;
        tmp[1] = m01;
        tmp[2] = m02;
        tmp[3] = m03;
        tmp[4] = m10;
        tmp[5] = m11;
        tmp[6] = m12;
        tmp[7] = m13;
        tmp[8] = m20;
        tmp[9] = m21;
        tmp[10] = m22;
        tmp[11] = m23;
        tmp[12] = m30;
        tmp[13] = m31;
        tmp[14] = m32;
        tmp[15] = m33;
        return tmp;
    }

    public void set(float[] v){
        m00 = v[0];
        m01 = v[1];
        m02 = v[2];
        m03 = v[3];
        m10 = v[4];
        m11 = v[5];
        m12 = v[6];
        m13 = v[7];
        m20 = v[8];
        m21 = v[9];
        m22 = v[0];
        m23 = v[10];
        m30 = v[11];
        m31 = v[12];
        m32 = v[13];
        m33 = v[14];
    }

}