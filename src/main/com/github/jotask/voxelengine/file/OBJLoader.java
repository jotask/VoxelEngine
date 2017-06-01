package com.github.jotask.voxelengine.file;

import com.github.jotask.voxelengine.engine.renderer.Loader;
import com.github.jotask.voxelengine.math.Vector2;
import com.github.jotask.voxelengine.math.Vector3;
import com.github.jotask.voxelengine.model.RawModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * OBJLoader
 *
 * @author Jose Vives Iznardo
 * @since 31/05/2017
 */
public class OBJLoader {

    public static RawModel loadFile(Loader loader, String file){
        FileReader fr = null;
        try {
            fr = new FileReader(new File(file));
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't load file. File not found!");
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(fr);
        String line;
        List<Vector3> vertices = new ArrayList<>();
        List<Vector2> uvs = new ArrayList<>();
        List<Vector3> normals = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();

        float[] verticesArray = null;
        float[] normalArray = null;
        float[] uvsArray = null;
        int[] indicesArray = null;

        try{
            while(true){
                line = reader.readLine();
                String[] currentLine = line.split(" ");
                if(line.startsWith("v ")){
                    float x = Float.parseFloat(currentLine[1]);
                    float y = Float.parseFloat(currentLine[2]);
                    float z = Float.parseFloat(currentLine[3]);
                    Vector3 vertex = new Vector3(x, y, z);
                    vertices.add(vertex);
                }else if(line.startsWith("vt ")){
                    float x = Float.parseFloat(currentLine[1]);
                    float y = Float.parseFloat(currentLine[2]);
                    Vector2 uv = new Vector2(x, y);
                    uvs.add(uv);
                }else if(line.startsWith("vn ")){
                    float x = Float.parseFloat(currentLine[1]);
                    float y = Float.parseFloat(currentLine[2]);
                    float z = Float.parseFloat(currentLine[3]);
                    Vector3 normal = new Vector3(x, y, z);
                    normals.add(normal);
                }else if(line.startsWith("f ")){
                    uvsArray = new float[vertices.size() * 2];
                    normalArray = new float[vertices.size() * 3];
                    break;
                }
            }

            while(line != null){
                if(!line.startsWith("f ")){
                    line = reader.readLine();
                    continue;
                }

                String[] currentLine = line.split(" ");
                String[] v1 = currentLine[1].split("/");
                String[] v2 = currentLine[2].split("/");
                String[] v3 = currentLine[3].split("/");

                processVertex(v1, indices, uvs, normals, uvsArray, normalArray);
                processVertex(v2, indices, uvs, normals, uvsArray, normalArray);
                processVertex(v3, indices, uvs, normals, uvsArray, normalArray);

                line = reader.readLine();

            }

            reader.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        verticesArray = new float[vertices.size() * 3];
        indicesArray = new int[indices.size()];
        int vertexPointer = 0;
        for(Vector3 v: vertices){
            verticesArray[vertexPointer++] = v.x;
            verticesArray[vertexPointer++] = v.y;
            verticesArray[vertexPointer++] = v.z;
        }

        for(int i = 0; i < indices.size(); i++){
            indicesArray[i] = indices.get(i);
        }

        return loader.loadMesh(verticesArray, indicesArray, uvsArray);

    }

    private static void processVertex(String[] data, List<Integer> indices, List<Vector2> uvs, List<Vector3> normals, float[] uvsArray, float[] normalArray){

        // FIXME add textures

        int currentVertexPointer = Integer.parseInt(data[0]) - 1;
        indices.add(currentVertexPointer);
//        Vector2 currentUV = uvs.get(Integer.parseInt(data[1]) - 1);
//        uvsArray[currentVertexPointer * 2] = currentUV.x;
//        uvsArray[currentVertexPointer * 2 + 1] = 1 - currentUV.y;

        Vector3 currentNormal = normals.get(Integer.parseInt(data[2]) - 1);
        normalArray[currentVertexPointer * 3] = currentNormal.x;
        normalArray[currentVertexPointer * 3 + 1] = currentNormal.x;
        normalArray[currentVertexPointer * 3 + 2] = currentNormal.x;

    }

}
