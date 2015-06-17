package com.HW;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class PlayOpenGL extends Activity{
	int type = 1;
	OpenGLView mOpenGLView;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(type == 0){
			// ʵ����MyRenderer,GLSurfaceView��
			GLSurfaceView glSurfaceView = new GLSurfaceView(this);
			// ������Ⱦ��
			glSurfaceView.setRenderer(new ThreeDG());
			// Ϊ��ǰActivity��ָ����ͼ
			setContentView(glSurfaceView);
		}else{
			requestWindowFeature(Window.FEATURE_NO_TITLE);
		    //����ȫ��
		    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		    
		    mOpenGLView = new OpenGLView(this);
		    setContentView(mOpenGLView);
		}
	}
}
