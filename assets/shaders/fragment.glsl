#version 400 core

in vec2 pass_texture;

out vec4 out_colour;

uniform sampler2D textureSampler;

void main()
{
    out_colour = texture(textureSampler, pass_texture);
}
