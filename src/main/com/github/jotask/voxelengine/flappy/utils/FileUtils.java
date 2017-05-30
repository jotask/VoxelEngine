package com.github.jotask.voxelengine.flappy.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * FileUtils
 *
 * @author Jose Vives Iznardo
 * @since 30/05/2017
 */
public class FileUtils {

    private FileUtils(){}

    public static String loadAsString(String file){
        StringBuilder sb = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String buffer;
            while((buffer = reader.readLine()) != null){
                sb.append(buffer);
            }
            reader.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString();
    }

}
