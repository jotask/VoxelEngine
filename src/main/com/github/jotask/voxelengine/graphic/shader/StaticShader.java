package com.github.jotask.voxelengine.graphic.shader;

import com.github.jotask.voxelengine.engine.renderer.Camera;
import com.github.jotask.voxelengine.math.Maths;
import com.github.jotask.voxelengine.math.Matrix4;

/**
 * StaticShader
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class StaticShader extends ShaderProgram {

    public enum Uniforms{
        TRANSFORMATIONMATRIX,
        PROJECTIONMATRIX,
        VIEWMATRIX
    }

    public StaticShader() {
        super("shaders/vertex.glsl", "shaders/fragment.glsl");
    }

    @Override
    protected void getAllUniformLocations() {
        super.getUniformLocation(Uniforms.TRANSFORMATIONMATRIX.name().toLowerCase());
        super.getUniformLocation(Uniforms.PROJECTIONMATRIX.name().toLowerCase());
    }

    public void loadTransformationMatrix(Matrix4 matrix){
        super.loadMatrix4(getUniformLocation(Uniforms.TRANSFORMATIONMATRIX.name().toLowerCase()), matrix);
    }

    public void loadProjectionMatrix(Matrix4 matrix){
        super.loadMatrix4(getUniformLocation(Uniforms.PROJECTIONMATRIX.name().toLowerCase()), matrix);
    }

    public void loadViewMatrix(Camera camera){
        Matrix4 view = Maths.createViewMatrix(camera);
        super.loadMatrix4(getUniformLocation(Uniforms.VIEWMATRIX.name().toLowerCase()), view);
    }

    @Override
    public void bindAttributes() {
        for(Attributes at: Attributes.values()){
            super.bindAttribute(at.ordinal(), at.name.toLowerCase());
        }
    }

}
