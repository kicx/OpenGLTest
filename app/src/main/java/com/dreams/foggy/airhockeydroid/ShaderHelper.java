package com.dreams.foggy.airhockeydroid;

import android.util.Log;

import static android.opengl.GLES20.GL_COMPILE_STATUS;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_LINK_STATUS;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glDeleteProgram;
import static android.opengl.GLES20.glDeleteShader;
import static android.opengl.GLES20.glGetProgramInfoLog;
import static android.opengl.GLES20.glGetProgramiv;
import static android.opengl.GLES20.glGetShaderInfoLog;
import static android.opengl.GLES20.glGetShaderiv;
import static android.opengl.GLES20.glLinkProgram;

/**
 * Created by lee on 8/28/2017.
 */

public class ShaderHelper {
    private static final String TAG = "ShaderHelper";

    public static int compileVertexShader(String shaderCode){
        return compileShader(GL_VERTEX_SHADER,shaderCode);
    }

    public static int compileFragmentShader(String shaderCode){
        return compileShader(GL_FRAGMENT_SHADER, shaderCode);
    }

    public static int compileShader(int type, String shaderCode){
        final int shaderObjectId = glCreateShader(type);
        if(shaderObjectId == 0) {
            Log.w(TAG, "could not create new shader");

            return 0;
        }

        final int[] compileStatus = new int[1];
        glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus,0);

        Log.v(TAG, "results of compiling source: " + "\n" + shaderCode + "\n:" + glGetShaderInfoLog(shaderObjectId));

        if(compileStatus[0]== 0){
            glDeleteShader(shaderObjectId);
            Log.w(TAG , "compilation of shader failed");
            return 0;
        }

        return shaderObjectId;
    }

    public static int linkProgram(int vertexShaderId , int fragmentShaderId){
        final int programObjectId = glCreateProgram();
        if(programObjectId == 0){
            Log.w(TAG ,  "COULD NOT CREATE NEW PROGRAM");
        }else{
        return 0;
        }

        glAttachShader(programObjectId, vertexShaderId);
        glAttachShader(programObjectId, fragmentShaderId);
        glLinkProgram(programObjectId);

        final int[] linkStatus = new int[1];
        glGetProgramiv(programObjectId,GL_LINK_STATUS,linkStatus,0 );
            Log.v(TAG, "results of linking program " + glGetProgramInfoLog(programObjectId));

        if(linkStatus[0] == 0){
            glDeleteProgram(programObjectId);
            Log.w(TAG, "Linking of program failed");
        }else{
            return 0;
        }
        return programObjectId;
    }


}
