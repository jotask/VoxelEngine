package com.github.jotask.voxelengine.graphic;

import com.github.jotask.voxelengine.math.Matrix4;
import com.github.jotask.voxelengine.math.Vector3;
import com.github.jotask.voxelengine.utils.ShaderUtils;
import org.lwjgl.opengl.GL20;

import java.util.HashMap;
import java.util.Map;

/**
 * Shader
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class Shader {

    public static final int VERTEX_ATTRIB = 0;
    public static final int TEXTURE_CORDINATE_ATTRIB = 1;

    public static Shader bg;

    private boolean enabled = false;

    private final int ID;

    private Map<String, Integer> uniforms = new HashMap<String, Integer>();

    public Shader(String vertex, String fragment){
        ID = ShaderUtils.load(vertex, fragment);

        if(ID == -1)
            throw new IllegalStateException("Shader impossible to load");

    }

    public static void loadAll(){
        bg = new Shader("shaders/bg_vert.glsl", "shaders/bg_frag.glsl");
    }

    public int getUniform(String name){

        if(uniforms.containsKey(name)){
            return uniforms.get(name);
        }

        int result =  GL20.glGetUniformLocation(ID, name);

        if(result == -1)
            throw new IllegalStateException("Could not find uniform variable: " + name);

        this.uniforms.put(name, result);

        return result;
    }

    public void setUniform1i(String name, int value){
        GL20.glUniform1i(getUniform(name), value);
    }

    public void setUniform1f(String name, float value){
        GL20.glUniform1f(getUniform(name), value);
    }

    public void setUniform2f(String name, float x, float y){
        GL20.glUniform2f(getUniform(name), x, y);
    }

    public void setUniform2f(String name, Vector3 vector){
        GL20.glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
    }

    public void setUniformMat4(String name, Matrix4 matrix){
        GL20.glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
    }

    public void enable(){
        GL20.glUseProgram(ID);
        enabled = true;
    }

    public void disable(){
        GL20.glUseProgram(ID);
        enabled = false;
    }

}
