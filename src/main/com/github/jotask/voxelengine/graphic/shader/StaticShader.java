package com.github.jotask.voxelengine.graphic.shader;

import com.github.jotask.voxelengine.math.Matrix4;

/**
 * StaticShader
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class StaticShader extends ShaderProgram {

    public enum Uniforms{
        TRANSFORMATION,
        PROJECTION
    }

    public StaticShader() {
        super("shaders/vertex.glsl", "shaders/fragment.glsl");
    }

    @Override
    protected void getAllUniformLocations() {
        super.getUniformLocation(Uniforms.TRANSFORMATION.name().toLowerCase());
        super.getUniformLocation(Uniforms.PROJECTION.name().toLowerCase());
    }

    public void loadTransformationMatrix(Matrix4 matrix){
        super.loadMatrix4(getUniformLocation(Uniforms.TRANSFORMATION.name().toLowerCase()), matrix);
    }

    public void loadProjectionMatrix(Matrix4 matrix){
        super.loadMatrix4(getUniformLocation(Uniforms.PROJECTION.name().toLowerCase()), matrix);
    }

    @Override
    public void bindAttributes() {
        for(Attributes at: Attributes.values()){
            super.bindAttribute(at.ordinal(), at.name.toLowerCase());
        }
    }

}
