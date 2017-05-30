package com.github.jotask.voxelengine.graphic;

import com.github.jotask.voxelengine.utils.BufferUtils;
import org.lwjgl.opengl.GL15;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Mesh
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class Mesh {

    private int vao;
    private int vbo;
    private int tbo; // Texture Coordinates object
    private int ibo; // Index Buffer Object

    private int count;

    public Mesh(float[] vertices, byte[] indices, float[] textureCoordinates){
        this.count = indices.length;

        this.vao = glGenVertexArrays();
        glBindVertexArray(vao);

        // Store vertices
        this.vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        GL15.glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);
        glVertexAttribPointer(Shader.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(Shader.VERTEX_ATTRIB);

        // Store UV
        this.tbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, tbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(textureCoordinates), GL_STATIC_DRAW);
        glVertexAttribPointer(Shader.TEXTURE_CORDINATE_ATTRIB, 2, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(Shader.TEXTURE_CORDINATE_ATTRIB);

        this.ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createByteBuffer(indices), GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

    }

    public void bind(){
        glBindVertexArray(vao);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
    }

    public void unbind(){
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void draw(){
        glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
    }

    public void render(){
        bind();
        draw();
        unbind();
    }

    public static Mesh createPlane(){
        float[] vert = new float[]{
                -1,  -1, 0,
                -1, 1, 0,
                1, 1, 0,
                1, -1, 0
        };

        byte[] ind = new byte[]{ 0, 1, 2, 2, 3, 0 };

        float[] uv = new float[]{
                0, 1,
                0, 0,
                1, 0,
                1, 1
        };

        return new Mesh(vert, ind, uv);
    }

}
