package com.github.jotask.voxelengine.engine;

/**
 * LWJGLApplication
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public interface LWJGLApplication {

    void init();

    void update();

    void render();

    void dispose();

}
