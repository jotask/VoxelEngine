#version 400 core

in vec3 position;
in vec2 textureCoords;

out vec2 pass_texture;

uniform mat4 transformation;
uniform mat4 projection;

void main()
{
    gl_Position = projection * transformation * vec4(position, 1f);
    pass_texture = textureCoords;
}
