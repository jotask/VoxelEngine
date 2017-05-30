package com.github.jotask.voxelengine.engine.renderer;

/**
 * RawModel
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class RawModel {

    private int vaoID;
    private int vertexCount;

    public RawModel(int vao, int vertexCount) {
        this.vaoID = vao;
        this.vertexCount = vertexCount;
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }

}
