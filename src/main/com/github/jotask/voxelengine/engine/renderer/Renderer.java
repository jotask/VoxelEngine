package com.github.jotask.voxelengine.engine.renderer;

import com.github.jotask.voxelengine.engine.GameEngine;
import com.github.jotask.voxelengine.engine.GameObject;
import com.github.jotask.voxelengine.graphic.shader.Attributes;
import com.github.jotask.voxelengine.graphic.shader.StaticShader;
import com.github.jotask.voxelengine.math.Maths;
import com.github.jotask.voxelengine.math.Matrix4;
import com.github.jotask.voxelengine.model.ModelTexture;
import com.github.jotask.voxelengine.model.RawModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 * Render
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class Renderer {

    private static final float FOV = 70;
    private static final float NEAR = 0.1f;
    private static final float FAR = 1000f;
    private Matrix4 projectionMatrix;

    public Renderer(StaticShader shader) {
        createProjectionMatrix();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void prepare(){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1, 0, 0, 1);
    }

    public void render(GameObject obj, StaticShader shader){

        ModelTexture modelTexture = obj.getModel();
        RawModel raw = modelTexture.getRawModel();
        GL30.glBindVertexArray(raw.getVaoID());
        GL20.glEnableVertexAttribArray(Attributes.VERTEX_POSITIONS.ordinal());
        GL20.glEnableVertexAttribArray(Attributes.TEXTURE_COORD.ordinal());

        Matrix4 transformationMatrix = Maths.createTransformationMatrix(obj.getPosition(), obj.getRotation(), obj.getScale());

        shader.loadTransformationMatrix(transformationMatrix);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, modelTexture.getTexture().getTexture());
        GL11.glDrawElements(GL11.GL_TRIANGLES, raw.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(Attributes.VERTEX_POSITIONS.ordinal());
        GL20.glDisableVertexAttribArray(Attributes.TEXTURE_COORD.ordinal());
        GL30.glBindVertexArray(0);

    }

    private void createProjectionMatrix(){
        float aspectRatio = ((float)GameEngine.WIDTH / (float)GameEngine.HEIGHT);
        float yScale = (float) (( 1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float xScale = (yScale / aspectRatio);
        float fustrumLength = FAR - NEAR;

        this.projectionMatrix = new Matrix4();
        this.projectionMatrix.m00 = xScale;
        this.projectionMatrix.m11 = yScale;
        this.projectionMatrix.m22 = -((FAR + NEAR) / fustrumLength);
        this.projectionMatrix.m23 = -1;
        this.projectionMatrix.m32 = -((2 * NEAR * FAR) / fustrumLength);
        this.projectionMatrix.m33 = 0;
    }

}
