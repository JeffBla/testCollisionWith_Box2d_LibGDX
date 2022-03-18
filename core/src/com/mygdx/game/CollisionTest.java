package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.World;


public class CollisionTest extends ApplicationAdapter {
    World world;
    Box2DDebugRenderer debugRenderer;
    Body body;
    OrthographicCamera camera;

    @Override
    public void create() {
//		batch = new SpriteBatch();
        world = new World(new Vector2(0, -200), true);
        debugRenderer = new Box2DDebugRenderer();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(500,800);
        body= world.createBody(bodyDef);

        CircleShape circle= new CircleShape();
        circle.setRadius(50f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 1f;
        fixtureDef.friction=0.4f;
        fixtureDef.restitution=0.2f;

        Fixture fixture = body.createFixture(fixtureDef);
        // -----------

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,0);
        body= world.createBody(bodyDef);

        ChainShape chain = new ChainShape();

        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(50,1000);
        vertices[1] = new Vector2(1500,20);
        vertices[2] = new Vector2(50,20);
        chain.createLoop(vertices);

        FixtureDef fd = new FixtureDef();
        fd.shape = chain;
        fd.density = 1;
        fd.friction = 0.3f;
        fd.restitution = 0.5f;
        body.createFixture(fd);

        chain.dispose();
        circle.dispose();

        camera = new OrthographicCamera();

        camera.setToOrtho(false);
    }

    @Override
    public void render() {
//        Gdx.gl.glClearColor(0.5f, 0.8f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        debugRenderer.render(world, camera.combined);


        world.step(1 / 60f, 6, 2);


    }

    @Override
    public void dispose() {
    }
}
