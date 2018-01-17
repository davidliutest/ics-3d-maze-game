#version 400
in vec3 position;
in vec2 texCoords;
out vec2 passTexCoords;
uniform mat4 transMatrix;
uniform mat4 projMatrix;
uniform mat4 viewMatrix;
void main(void){
    vec4 worldPosition = transMatrix * vec4(position,1.0);
	gl_Position = projMatrix * viewMatrix * transMatrix * vec4(position, 1.0);
	passTexCoords = texCoords;
}