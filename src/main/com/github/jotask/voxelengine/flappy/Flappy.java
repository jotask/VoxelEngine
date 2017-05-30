package com.github.jotask.voxelengine.flappy;

import com.github.jotask.voxelengine.flappy.input.Input;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Flappy
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class Flappy implements Runnable{

    private final int WIDHT = 600 / 2;
    private final int HEIGHT = 800 / 2;

    private final Thread thread;

    private long window;

    private boolean isRunning;

    public Flappy() {
        isRunning = true;
        thread = new Thread(this, "Flappy");
        thread.start();
    }

    private void init(){
        if(!glfwInit()){
            throw new IllegalStateException("GLFW not initialized");
        }

        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        window = glfwCreateWindow(WIDHT, HEIGHT, "Flappy", MemoryUtil.NULL, MemoryUtil.NULL);

        if(window == MemoryUtil.NULL){
            throw new IllegalStateException("Windows is null");
        }

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidmode.width() - WIDHT) / 2, (vidmode.height() - HEIGHT) / 2);

        glfwSetKeyCallback(window, new Input());

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);

        GL.createCapabilities();

        glClearColor(1f, 1f, 1f, 1f);
        glEnable(GL_DEPTH_TEST);
        System.out.println("OpenGL: " + glGetString(GL_VERSION));

    }

    private void update(){
        glfwPollEvents();
    }

    private void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_TEST);
        glfwSwapBuffers(window);
    }

    @Override
    public void run() {
        init();
        while(isRunning){
            update();
            render();
            if(glfwWindowShouldClose(window)){
                isRunning = false;
            }
        }

    }

    public static void main(String[] args) {
        new Flappy();
    }

}
