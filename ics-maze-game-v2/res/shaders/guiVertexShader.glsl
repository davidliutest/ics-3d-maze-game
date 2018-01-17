#version 140
in vec2 pos;
out vec2 texCoords;
uniform mat4 transMatrix;
void main(void) {
	gl_Pos = transMatrix * vec4(pos, 0.0, 1.0);
	texCoords = vec2((pos.x+1.0)/2.0, 1 - (pos.y+1.0)/2.0);
}