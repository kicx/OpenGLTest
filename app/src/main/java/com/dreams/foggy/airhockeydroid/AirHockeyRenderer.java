package com.dreams.foggy.airhockeydroid;

import android.content.Context;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glViewport;

/**
 * Created by lee on 8/27/2017.
 */

public class AirHockeyRenderer implements GLSurfaceView.Renderer {
    private final static int POSITION_COMPONENT_COUNT = 2;
    private final Context context;
    private int program;

    private final static int BYTES_PER_FLOAT = 4;
    //stores data in native memory
    private final FloatBuffer vertexData;


    public AirHockeyRenderer(Context context) {
        this.context = context;
        float[] tablesVerticesWithTriangles = {
                //triangle 1
                                 0f, 0f,
                                 9f, 14f,
                                 0f, 14f,
                // triangle 2
                                 0f, 0f,
                                 9f, 0f,
                                 9f, 0f,
                                 9f, 14f,

               // center line
                                 0f,7f,
                                  9f,7f,

                // mallets
                                4.5f, 2f,
                                4.5f, 12f
        };

        vertexData = ByteBuffer.allocateDirect(tablesVerticesWithTriangles.length * BYTES_PER_FLOAT).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexData.put(tablesVerticesWithTriangles);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        glClearColor(1.0f,0.0f, 0.0f,0.0f);

        String vertexShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_vertex_shader);
        String fragmentShaderSource = TextResourceReader.readTextFileFromResource(context,R.raw.simple_fragment_shader);
        int vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource);
        int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSource);

        program = ShaderHelper.linkProgram(vertexShader, fragmentShader);


    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        glViewport(0,0,width,height);

    }

    @Override
    public void onDrawFrame(GL10 gl10) {

        glClear(GL_COLOR_BUFFER_BIT);

    }
}
