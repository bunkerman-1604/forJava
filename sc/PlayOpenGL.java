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
			// 实例化MyRenderer,GLSurfaceView类
			GLSurfaceView glSurfaceView = new GLSurfaceView(this);
			// 设置渲染器
			glSurfaceView.setRenderer(new ThreeDG());
			// 为当前Activity类指定视图
			setContentView(glSurfaceView);
		}else{
			requestWindowFeature(Window.FEATURE_NO_TITLE);
		    //设置全屏
		    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		    
		    mOpenGLView = new OpenGLView(this);
		    setContentView(mOpenGLView);
		}
	}
}
