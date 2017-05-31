package com.github.jotask.voxelengine.utils;

import org.lwjgl.opengl.GL20;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

/**
 * ShaderUtils
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class ShaderUtils {

    private ShaderUtils(){}

    public static int load(String vertFile, String fragFile){
        String vert = FileUtils.loadAsString(vertFile);
        String frag = FileUtils.loadAsString(fragFile);
        return create(vert, frag);
    }

    private static int create(String vert, String frag){
        int program = GL20.glCreateProgram();
        int vertID = glCreateShader(GL_VERTEX_SHADER);
        int fragID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(vertID, vert);
        glShaderSource(fragID, frag);

        glCompileShader(vertID);
        if(glGetShaderi(vertID, GL_COMPILE_STATUS) == GL_FALSE){
            System.err.println("Failed to compile vertex ShaderProgram!");
            System.err.println(glGetShaderInfoLog(vertID));
            System.exit(-1);
        }

        glCompileShader(fragID);
        if(glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE){
            System.err.println("Failed to compile fragment ShaderProgram!");
            System.err.println(glGetShaderInfoLog(fragID));
            System.exit(-1);
        }

        glAttachShader(program, vertID);
        glAttachShader(program, fragID);

        glLinkProgram(program);

        glValidateProgram(program);

        glDeleteShader(vertID);
        glDeleteShader(fragID);

        return program;
    }

}
