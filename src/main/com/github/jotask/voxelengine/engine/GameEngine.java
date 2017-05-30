package com.github.jotask.voxelengine.engine;

import com.github.jotask.voxelengine.input.Input;
import com.github.jotask.voxelengine.utils.EngineConfiguration;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * GameEngine
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class GameEngine implements Runnable{

    private final Thread thread;

    private long window;

    private boolean isRunning;

    private final LWJGLApplication app;
    private final EngineConfiguration cfg;

    public GameEngine(LWJGLApplication app, EngineConfiguration cfg) {

        this.app = app;
        this.cfg = cfg;

        isRunning = true;
        thread = new Thread(this, "GameEngine");
        thread.start();

    }

    private void init(){
        if(!glfwInit()){
            throw new IllegalStateException("GLFW not initialized");
        }

        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        final String title = cfg.title + " - " + cfg.version + "[" +cfg.status + "]";
        window = glfwCreateWindow(this.cfg.widht, this.cfg.height, title, MemoryUtil.NULL, MemoryUtil.NULL);

        if(window == MemoryUtil.NULL){
            throw new IllegalStateException("Windows is null");
        }

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidmode.width() - cfg.widht) / 2, (vidmode.height() - cfg.height) / 2);

        glfwSetKeyCallback(window, new Input());

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);

        if(cfg.vsync){
            glfwSwapInterval(1);
        }

        GL.createCapabilities();

        System.out.println("OpenGL: " + glGetString(GL_VERSION));

    }

    private void update(){
        glfwPollEvents();
        app.update();
    }

    private void render(){

        app.render();

        int error = glGetError();
        if(error != GL_NO_ERROR){
            System.out.println(error);
        }

        glfwSwapBuffers(window);

    }

    private void dispose(){
        // TODO
        app.dispose();
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    @Override
    public void run() {
        init();
        app.init();

        long lastTime = System.nanoTime();
        double delta = 0.0;
        double ns = 1000000000.0 / 60.0;

        int updates = 0;
        int frames = 0;

        long timer = System.currentTimeMillis();

        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1.0) {
                update();

                updates++;
                delta--;

            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("Ups: " + updates + " Fps: " + frames);
                updates = 0;
                frames = 0;
            }

            if(glfwWindowShouldClose(window)){
                isRunning = false;
            }
        }
        dispose();
    }

}
