package com.github.jotask.voxelengine;

import com.github.jotask.voxelengine.engine.GameEngine;
import com.github.jotask.voxelengine.game.Game;
import com.github.jotask.voxelengine.utils.EngineConfiguration;

/**
 * Main
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class Main {

    public static void main(String[] args) {

        EngineConfiguration cfg = new EngineConfiguration();
        cfg.title = "Procedural Terrain Generation";

        new GameEngine(new Game(), cfg);
    }

}
