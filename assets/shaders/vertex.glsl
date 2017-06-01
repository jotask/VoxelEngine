#version 400 core

in vec3 a_position;
in vec3 a_normal;
in vec3 a_colour;
in vec2 a_textureCoords;

out vec3 color;

uniform mat4 transformation;
uniform mat4 projection;

void main()
{

    gl_Position = projection * transformation * vec4(a_position, 1f);
    color = a_position;

}
