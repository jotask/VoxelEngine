package com.github.jotask.voxelengine.engine.renderer;

import com.github.jotask.voxelengine.engine.GameObject;
import com.github.jotask.voxelengine.graphic.shader.StaticShader;
import com.github.jotask.voxelengine.math.Matrix4;
import org.lwjgl.opengl.GL11;

/**
 * Render
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class Renderer {

    private final Matrix4 projectionMatrix = new Matrix4();
    private final Matrix4 transforMatrix = new Matrix4();
    private final Matrix4 combinedMatrix = new Matrix4();

    private boolean dirty;

    private final StaticShader shader;

    public Renderer() {
        this.shader = new StaticShader();
    }

    public void begin(){
        this.prepare();
        if(dirty){
            combinedMatrix.set(projectionMatrix);
            combinedMatrix.mul(transforMatrix);
            dirty = false;
        }
        shader.start();
        shader.loadProjectionMatrix(combinedMatrix);
    }

    public void end(){
        shader.stop();
    }

    public void prepare(){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1, 1, 1, 1);
    }

    public void render(GameObject obj){

        obj.render(this);

//        ModelTexture modelTexture = obj.getModel();
//        RawModel raw = modelTexture.getRawModel();
//        GL30.glBindVertexArray(raw.getVaoID());
//        GL20.glEnableVertexAttribArray(Attributes.VERTEX_POSITIONS.ordinal());
//        GL20.glEnableVertexAttribArray(Attributes.TEXTURE_COORD.ordinal());
//
//        Transformation t = obj.getTransformation();
//
//        Matrix4 transformationMatrix = Maths.createTransformationMatrix(t.getPosition(), t.getRotation(), t.getScale());
//
//        shader.loadTransformationMatrix(transformationMatrix);
//
//        GL13.glActiveTexture(GL13.GL_TEXTURE0);
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, modelTexture.getTexture().getTexture());
//        GL11.glDrawElements(GL11.GL_TRIANGLES, raw.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
//        GL20.glDisableVertexAttribArray(Attributes.VERTEX_POSITIONS.ordinal());
//        GL20.glDisableVertexAttribArray(Attributes.TEXTURE_COORD.ordinal());
//        GL30.glBindVertexArray(0);

    }

    /** Sets the projection matrix to be used for rendering. Usually this will be set to {@link Camera#combined}.
     * @param matrix */
    public void setProjectionMatrix(Matrix4 matrix){
        projectionMatrix.set(matrix);
        dirty = true;
    }

    public StaticShader getShader() {
        return shader;
    }

    public void dispose() {
        this.shader.dispose();
    }

}
