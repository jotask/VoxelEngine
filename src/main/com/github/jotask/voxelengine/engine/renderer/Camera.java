//package com.github.jotask.voxelengine.engine.renderer;
//
//import com.github.jotask.voxelengine.engine.GameEngine;
//import com.github.jotask.voxelengine.input.Input;
//import com.github.jotask.voxelengine.math.Matrix4;
//import com.github.jotask.voxelengine.math.Vector3;
//
//import static org.lwjgl.glfw.GLFW.*;
//
///**
// * Camera
// *
// * @author Jose Vives Iznardo
// * @since 31/05/2017
// */
//public class Camera {
//
//    /** the projection matrix **/
//    public final Matrix4 projection = new Matrix4();
//
//    /** the view matrix **/
//    public final Matrix4 view = new Matrix4();
//
//    /** the combined projection and view matrix **/
//    public final Matrix4 combined = new Matrix4();
//
//    /** the inverse combined projection and view matrix **/
//    public final Matrix4 invProjectionView = new Matrix4();
//
//    /** the viewport width **/
//    public float viewportWidth = 0;
//
//    /** the viewport height **/
//    public float viewportHeight = 0;
//
//    private static final float FOV = 70;
//    private static final float NEAR = 0.1f;
//    private static final float FAR = 1000f;
//
//
//    /** the unit length direction vector of the camera **/
//    public final Vector3 direction = new Vector3(0, 0, -1);
//
//    /** the unit length up vector of the camera **/
//    public final Vector3 up = new Vector3(0, 1, 0);
//
//    private Vector3 position;
//    private float pitch;
//    private float yaw;
//    private float roll;
//
//    public Camera() {
//        // FIXME
//        this.viewportWidth = 400;
//        this.viewportHeight = 400;
//
//        this.position = new Vector3();
////        this.position.x = 1;
//        this.position.z = 10f;
//        this.pitch = 0f;
//        this.yaw = 0f;
//        this.roll = 0f;
//        createProjectionMatrix();
//
//        update();
//    }
//
//    public void move(){
//        if(Input.keys[GLFW_KEY_W])
//            this.position.z -= .1f;
//        if(Input.keys[GLFW_KEY_S])
//            this.position.z += .1f;
//        if(Input.keys[GLFW_KEY_A])
//            this.position.x -= .1f;
//        if(Input.keys[GLFW_KEY_D])
//            this.position.x += .1f;
////
////
//
////        long time = System.currentTimeMillis() / 500;
////        float camX = (float) (Math.sin(time) * radius);
////        float camZ = (float) (Math.cos(time) * radius);
////        this.position.x = camX;
////        this.position.z = camZ;
//
////        lookAt(new Vector3());
//
//    }
//
//    public Vector3 getPosition() {
//        return position;
//    }
//
//    public float getPitch() {
//        return pitch;
//    }
//
//    public float getYaw() {
//        return yaw;
//    }
//
//    public float getRoll() {
//        return roll;
//    }
//
//    public void lookAt(Vector3 lookAt){
//
//        Vector3 tmpVec = new Vector3();
//        tmpVec.set(lookAt.x, lookAt.y, lookAt.z).sub(position).nor();
//        if (!tmpVec.isZero()) {
//            float dot = tmpVec.dot(up); // up and direction must ALWAYS be orthonormal vectors
//            if (Math.abs(dot - 1) < 0.000000001f) {
//                // Collinear
//                up.set(direction).scl(-1);
//            } else if (Math.abs(dot + 1) < 0.000000001f) {
//                // Collinear opposite
//                up.set(direction);
//            }
//            direction.set(tmpVec);
//            normalizeUp();
//        }
//    }
//
//    private void normalizeUp(){
//        Vector3 tmp = new Vector3(direction);
//        tmp.crs(up).nor();
//        up.set(tmp).crs(direction).nor();
//    }
//
//    private void createProjectionMatrix(){
//        float aspectRatio = ((float) GameEngine.WIDTH / (float)GameEngine.HEIGHT);
//        float yScale = (float) (( 1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
//        float xScale = (yScale / aspectRatio);
//        float fustrumLength = FAR - NEAR;
//
//        this.projection.m00 = xScale;
//        this.projection.m11 = yScale;
//        this.projection.m22 = -((FAR + NEAR) / fustrumLength);
//        this.projection.m23 = -1;
//        this.projection.m32 = -((2 * NEAR * FAR) / fustrumLength);
//        this.projection.m33 = 0;
//    }
//
//    public void update(){
//        float aspect = viewportWidth / viewportHeight;
//        projection.setToProjection(Math.abs(NEAR), Math.abs(FAR), FOV, aspect);
//        view.setToLookAt(position, new Vector3(position).add(direction), up);
//        combined.set(projection);
//        Matrix4.mul(combined, view, combined);
//
////        if (updateFrustum) {
////            invProjectionView.set(combined);
////            Matrix4.inv(invProjectionView.val);
////            frustum.update(invProjectionView);
////        }
//    }
//
//    public Vector3 project (Vector3 worldCoords, float viewportX, float viewportY, float viewportWidth, float viewportHeight) {
//        worldCoords.prj(combined);
//        worldCoords.x = viewportWidth * (worldCoords.x + 1) / 2 + viewportX;
//        worldCoords.y = viewportHeight * (worldCoords.y + 1) / 2 + viewportY;
//        worldCoords.z = (worldCoords.z + 1) / 2;
//        return worldCoords;
//    }
//
//}
