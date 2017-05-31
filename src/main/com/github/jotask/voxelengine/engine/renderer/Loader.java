package com.github.jotask.voxelengine.engine.renderer;

import com.github.jotask.voxelengine.graphic.shader.Attributes;
import com.github.jotask.voxelengine.graphic.texture.Texture;
import com.github.jotask.voxelengine.model.RawModel;
import com.github.jotask.voxelengine.utils.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Loader
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class Loader {

    private List<Integer> vaos = new ArrayList<Integer>();
    private List<Integer> vbos = new ArrayList<Integer>();
    private List<Integer> textures = new ArrayList<Integer>();

    public RawModel loadMesh(float[] positions, int[] indices, float[] uvs){
        int vao = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(Attributes.VERTEX_POSITIONS.ordinal(), 3 ,positions);
        storeDataInAttributeList(Attributes.TEXTURE_COORD.ordinal(), 2 ,uvs);
        unbindVAO();
        return new RawModel(vao, indices.length);
    }

    public Texture loadTexture(String filename){
        Texture texture = new Texture(filename);
        textures.add(texture.getTexture());
        return texture;
    }

    private int createVAO(){
        int vaoID = GL30.glGenVertexArrays();
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    private void storeDataInAttributeList(int attributeNumber, int coordinateSize, float data[]){
        int vbo = GL15.glGenBuffers();
        vbos.add(vbo);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private void unbindVAO(){
        GL30.glBindVertexArray(0);
    }

    private void bindIndicesBuffer(int[] indices){
        int vbo = GL15.glGenBuffers();
        vbos.add(vbo);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vbo);
        IntBuffer buffer = BufferUtils.createIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    public void dispose(){
        for(int i: vaos)
            GL30.glDeleteVertexArrays(i);
        for(int i: vbos)
            GL15.glDeleteBuffers(i);
        for(int i: textures)
            GL11.glDeleteTextures(i);

    }

}
