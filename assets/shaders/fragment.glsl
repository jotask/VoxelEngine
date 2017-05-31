#version 400 core

in vec4 colour;

out vec4 out_colour;

uniform sampler2D textureSampler;

void main()
{
    out_colour = colour;
}
