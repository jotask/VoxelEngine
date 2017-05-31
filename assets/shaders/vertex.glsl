#version 400 core

in vec3 position;
in vec2 textureCoords;

out vec2 pass_texture;

uniform mat4 transformationmatrix;
uniform mat4 projectionmatrix;
uniform mat4 viewmatrix;

void main()
{
    gl_Position = projectionmatrix * viewmatrix * transformationmatrix * vec4(position, 1f);
    pass_texture = textureCoords;
}
