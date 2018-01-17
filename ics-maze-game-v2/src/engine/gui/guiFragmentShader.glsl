#version 140
in vec2 texCoords;
out vec4 colorOut;
uniform sampler2D guiTexture;
void main(void){
	colorOut = texture(guiTexture,vec2(texCoords.x, texCoords.y));
}