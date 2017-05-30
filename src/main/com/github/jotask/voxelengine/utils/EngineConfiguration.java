package com.github.jotask.voxelengine.utils;

/**
 * EngineConfiguration
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class EngineConfiguration {

    public enum STATUS{ ALPHA, BETA, FINAL }

    public String title = "";
    public double version = 0.1;
    public STATUS status = STATUS.ALPHA;

    public int widht = 800;
    public int height = 600;

    public boolean vsync = true;

}
