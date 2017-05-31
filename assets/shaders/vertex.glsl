#version 400 core

in vec3 position;
in vec2 textureCoords;

out vec4 colour;

uniform mat4 transformation;
uniform mat4 projection;

void main()
{
    gl_Position = projection * transformation * vec4(position, 1f);
    colour = vec4(position, 1f);
}
