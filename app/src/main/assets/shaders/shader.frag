#version 300 es
//precision highp float;
precision mediump float;

uniform float time;
uniform vec2 resolution;
uniform mat4 orientation;

in vec2 coords;
out vec4 output_colour;



vec4 example();

void main()
	{
	float r = coords.x;
	float g = coords.y;
	float b = .5f + (sin(time * 3.f) * .5f);
	output_colour = vec4(r, g, b, 1.f);

	output_colour = example();
	}





//https://www.shadertoy.com/view/mtyGWy
/* This animation is the material of my first youtube tutorial about creative
   coding, which is a video in which I try to introduce programmers to GLSL
   and to the wonderful world of shaders, while also trying to share my recent
   passion for this community.
                                       Video URL: https://youtu.be/f4s1h2YETNY
*/

//https://iquilezles.org/articles/palettes/
vec3 palette( float t )
	{
	vec3 a = vec3(0.5, 0.5, 0.5);
	vec3 b = vec3(0.5, 0.5, 0.5);
	vec3 c = vec3(1.0, 1.0, 1.0);
	vec3 d = vec3(0.263,0.416,0.557);

	return a + b*cos( 6.28318*(c*t+d) );
	}

//https://www.shadertoy.com/view/mtyGWy
vec4 example()
	{
	//vec2 uv = (coords * 2.0 - resolution.xy) / resolution.y;
	vec2 uv = (-.5f + coords.xy) * 2.f;
	vec2 uv0 = uv;
	vec3 finalColor = vec3(0.0);

	for (float i = 0.0; i < 4.0; i++)
		{
		uv = fract(uv * 1.5) - 0.5;

		float d = length(uv) * exp(-length(uv0));

		vec3 col = palette(length(uv0) + i*.4 + time*.4);

		d = sin(d*8. + time)/8.;
		d = abs(d);

		d = pow(0.01 / d, 1.2);

		finalColor += col * d;
		}

	return vec4(finalColor, 1.0);
	}