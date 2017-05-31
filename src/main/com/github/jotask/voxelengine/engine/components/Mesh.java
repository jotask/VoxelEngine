package com.github.jotask.voxelengine.engine.components;

import com.github.jotask.voxelengine.engine.GameObject;
import com.github.jotask.voxelengine.engine.Transformation;
import com.github.jotask.voxelengine.engine.renderer.Renderer;
import com.github.jotask.voxelengine.graphic.shader.Attributes;
import com.github.jotask.voxelengine.graphic.shader.StaticShader;
import com.github.jotask.voxelengine.math.Maths;
import com.github.jotask.voxelengine.math.Matrix4;
import com.github.jotask.voxelengine.model.RawModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 * Mesh
 *
 * @author Jose Vives Iznardo
 * @since 31/05/2017
 */
public class Mesh extends Component{

    private RawModel rawModel;

    public Mesh(RawModel model) {
        super("mesh");
        this.rawModel = model;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Renderer sb, GameObject parent) {
        StaticShader shader = sb.getShader();

        //        ModelTexture modelTexture = obj.getModel();
//        RawModel raw = modelTexture.getRawModel();
        GL30.glBindVertexArray(this.rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(Attributes.VERTEX_POSITIONS.ordinal());
        GL20.glEnableVertexAttribArray(Attributes.TEXTURE_COORD.ordinal());

        final Transformation t = parent.getTransformation();
        Matrix4 transformationMatrix = Maths.createTransformationMatrix(t.getPosition(), t.getRotation(), t.getScale());

        shader.loadTransformationMatrix(transformationMatrix);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, modelTexture.getTexture().getTexture());
        GL11.glDrawElements(GL11.GL_TRIANGLES, this.rawModel.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(Attributes.VERTEX_POSITIONS.ordinal());
        GL20.glDisableVertexAttribArray(Attributes.TEXTURE_COORD.ordinal());
        GL30.glBindVertexArray(0);
    }

    @Override
    public void dispose() {

    }
}
