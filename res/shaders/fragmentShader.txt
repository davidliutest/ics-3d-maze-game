#version 400

in vec2 passTexCoords;
in vec3 surfaceNormal;
in vec3 toLightVector;

out vec4 color_out;

uniform sampler2D texSampler;
uniform vec3 lightColour;

void main(void){

    vec3 unitNormal  = normalize(surfaceNormal);
    vec3 unitLightVector = normalize(toLightVector);

    float nDot1 = dot(unitNormal,unitLightVector);
    float brightness = max(nDot1,0.0);
    vec3 diffuse = brightness * lightColour;

	color_out = vec4(diffuse, 1.0) * texture(texSampler, passTexCoords);
}