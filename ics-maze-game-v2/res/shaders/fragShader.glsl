#version 400
in vec2 passTexCoords;
out vec4 colorOut;
uniform sampler2D texSampler;
void main(void){
	colorOut = texture(texSampler, passTexCoords);
}