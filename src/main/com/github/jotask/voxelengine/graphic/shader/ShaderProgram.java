package com.github.jotask.voxelengine.graphic.shader;

import com.github.jotask.voxelengine.math.Matrix4;
import com.github.jotask.voxelengine.math.Vector3;
import com.github.jotask.voxelengine.file.FileUtils;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

/**
 * ShaderProgram
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public abstract class ShaderProgram {

    private final int id;
    private final int vertex;
    private final int fragment;

    private static final FloatBuffer matrixBuffer = org.lwjgl.BufferUtils.createFloatBuffer(4*4);

    private Map<String, Integer> uniforms = new HashMap<String, Integer>();

    public ShaderProgram(String vertexFile, String fragmentFile){

        String vert = FileUtils.loadAsString(vertexFile);
        String frag = FileUtils.loadAsString(fragmentFile);

        this.id = GL20.glCreateProgram();

        if(this.id == -1)
            throw new IllegalStateException("ShaderProgram impossible to load");

        this.vertex = glCreateShader(GL_VERTEX_SHADER);
        this.fragment = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(vertex, vert);
        glShaderSource(fragment, frag);

        glCompileShader(vertex);
        if(glGetShaderi(vertex, GL_COMPILE_STATUS) == GL_FALSE){
            System.err.println("Failed to compile vertex ShaderProgram!");
            System.err.println(glGetShaderInfoLog(vertex));
            System.exit(-1);
        }

        glCompileShader(fragment);
        if(glGetShaderi(fragment, GL_COMPILE_STATUS) == GL_FALSE){
            System.err.println("Failed to compile fragment ShaderProgram!");
            System.err.println(glGetShaderInfoLog(fragment));
            System.exit(-1);
        }

        glAttachShader(id, vertex);
        glAttachShader(id, fragment);

        bindAttributes();

        glLinkProgram(id);

        glValidateProgram(id);

        glDeleteShader(vertex);
        glDeleteShader(fragment);

        getAllUniformLocations();

    }

    protected abstract void getAllUniformLocations();

    protected int getUniformLocation(String name){
        if(this.uniforms.containsKey(name))
            return this.uniforms.get(name);
        int location = GL20.glGetUniformLocation(this.id, name);
        this.uniforms.put(name, location);
        return location;
    }

    public abstract void bindAttributes();

    protected void bindAttribute(int attribute, String variable){
        GL20.glBindAttribLocation(id, attribute, variable);
    }

    protected void loadFloat(int location, float value){
        GL20.glUniform1f(location, value);
    }

    protected void loadVector3(int location, Vector3 vector){
        GL20.glUniform3f(location, vector.x, vector.y, vector.z);
    }

    protected void loadBoolean(int location,boolean value){
        float toLoad = 0;
        if(value)
            toLoad = 1f;
        GL20.glUniform1f(location, toLoad);
    }

    protected void loadMatrix4(int location, Matrix4 matrix){
        matrix.store(matrixBuffer);
        matrixBuffer.flip();
        GL20.glUniformMatrix4fv(location, false, matrixBuffer);
    }


//    public int getUniform(String name){
//
//        if(uniforms.containsKey(name)){
//            return uniforms.get(name);
//        }
//
//        int result =  GL20.glGetUniformLocation(id, name);
//
//        if(result == -1)
//            throw new IllegalStateException("Could not find uniform variable: " + name);
//
//        this.uniforms.put(name, result);
//
//        return result;
//    }
//
//    public void setUniform1i(String name, int value){
//        GL20.glUniform1i(getUniform(name), value);
//    }
//
//    public void setUniform1f(String name, float value){
//        GL20.glUniform1f(getUniform(name), value);
//    }
//
//    public void setUniform2f(String name, float x, float y){
//        GL20.glUniform2f(getUniform(name), x, y);
//    }
//
//    public void setUniform2f(String name, Vector3 vector){
//        GL20.glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
//    }
//
//    public void setUniformMat4(String name, Matrix4 matrix){
//        GL20.glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
//    }

    public void start(){
        GL20.glUseProgram(id);
    }

    public void stop(){
        GL20.glUseProgram(id);
    }

    public void dispose(){
        stop();
        // TODO check if deletes all the vbo with this
        GL20.glDeleteProgram(id);
    }

}
