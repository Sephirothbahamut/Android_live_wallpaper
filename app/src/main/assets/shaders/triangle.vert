#version 300 es

out vec2 coords;

void main()
	{
	vec2 vertices[3] = vec2[3]
		(
		vec2(-1.f, -1.f),
		vec2( 3.f, -1.f),
		vec2(-1.f,  3.f)
		);

	gl_Position = vec4(vertices[gl_VertexID], 0.f, 1.f);
	coords = .5f * gl_Position.xy + vec2(.5f);
	coords.y = 1.f - coords.y;
	}