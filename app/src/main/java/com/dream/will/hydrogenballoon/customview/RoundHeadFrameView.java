package com.dream.will.hydrogenballoon.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.dream.will.hydrogenballoon.R;

/**
 * 自定义 ImageView 控件，实现了圆角和边框
 */

public class RoundHeadFrameView extends ImageView{
   private Paint pressPain;
    private int width;
    private  int height;
    //定义Bitmap的默认配置
    private static final Bitmap.Config BITMAP_CONFIG=Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION=1;
    private int borderColor;
    private int borderWidth;
    // 按下的透明度
    private int pressAlpha;
    // 按下的颜色
    private int pressColor;
    // 圆角半径
    private int radius;
    // 图片类型（矩形，圆形）
    private int shapeType;


    public RoundHeadFrameView(Context context) {
        super(context);
        init(context,null);
    }




    public RoundHeadFrameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }



    private void init(Context context, AttributeSet attrs) {

        //初始化默认值
        borderWidth=6;
        borderColor=getResources().getColor(R.color.white);
        pressAlpha = 64;
        pressColor = getResources().getColor(R.color.colorPrimary);;
        radius = 16;
        shapeType = 0;
        //获取属性；
        if (attrs!=null){
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundHeadFrameView);
            borderColor = array.getColor(R.styleable.RoundHeadFrameView_border_color, borderColor);
            borderWidth=array.getDimensionPixelOffset(R.styleable.RoundHeadFrameView_border_width,borderWidth);
            radius=array.getDimensionPixelOffset(R.styleable.RoundHeadFrameView_radius,radius);
            shapeType=array.getInteger(R.styleable.RoundHeadFrameView_shape_type,shapeType);
            array.recycle();
        }
        pressPain= new Paint();
        pressPain.setAntiAlias(true);
        pressPain.setStyle(Paint.Style.FILL);
        pressPain.setColor(pressColor);
        pressPain.setAlpha(0);
        pressPain.setFlags(Paint.ANTI_ALIAS_FLAG);

      setClickable(true);
        //setDrawingCacheEnabled(true)：提高绘图速度
        setDrawingCacheEnabled(true);
        //setWillNotDraw(false)：保证View的onDraw函数被调用
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
       //获取当前控件
        Drawable drawable = getDrawable();
        if (drawable==null){
            return;
        }
     // 这里 get 回来的宽度和高度是当前控件相对应的宽度和高度（在 xml 设置）
        if (getWidth()==0||getHeight()==0){
            return;
        }
        //获取bitmap,即传入imageview的bitmap
        Bitmap bitmap = getBitmapFromDrawable(drawable);
        drwaDrawable(canvas,bitmap);
        drawaPress(canvas);
        drawaBorder(canvas);



    }

    private void drwaDrawable(Canvas canvas, Bitmap bitmap) {
        Paint paint = new Paint();
        //设置颜色
        paint.setColor(0xffffffff);
        //抗锯齿
        paint.setAntiAlias(true);
        //Paint 的 Xfermode，PorterDuff.Mode.SRC_IN 取两层图像的交集部门, 只显示上层图像。
        PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        //标志
        int saveFlags = Canvas.MATRIX_SAVE_FLAG|Canvas.CLIP_SAVE_FLAG
                |Canvas.HAS_ALPHA_LAYER_SAVE_FLAG
                |Canvas.FULL_COLOR_LAYER_SAVE_FLAG
                |Canvas.CLIP_TO_LAYER_SAVE_FLAG;
      /* int saveLayer (float left,
        float top,
        float right,
        float bottom,
        Paint paint,
        int saveFlags)*/
        canvas.saveLayer(0,0,width,height,null,saveFlags);
        if (shapeType==0){
            // 画遮罩，画出来就是一个和空间大小相匹配的圆
           /* void drawCircle (float cx,
            float cy,
            float radius,
            Paint paint)*/
            canvas.drawCircle(width/2,height/2,width/2-1,paint);
        }else {
            // 当ShapeType = 1 时 图片为圆角矩形
            RectF rectF = new RectF(0, 0, getWidth(), getHeight());
            canvas.drawRoundRect(rectF,radius,radius,paint);

        }
        paint.setXfermode(xfermode);
        // 空间的大小 / bitmap 的大小 = bitmap 缩放的倍数
        float scaleWidth= ((float) getWidth())/bitmap.getWidth();
        float scaleHeight= ((float) getHeight())/bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth,scaleHeight);
        //bitmap缩放
        bitmap= Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.restore();


    }
    private void drawaBorder(Canvas canvas) {
        if (borderWidth>0){
            Paint paint= new Paint();
            paint.setStrokeWidth(borderWidth);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(borderColor);
            paint.setAntiAlias(true);
            if (shapeType==0){
                canvas.drawCircle(width/2,height/2,(width - borderWidth) / 2,paint);
            }else {
                RectF rectf = new RectF(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth / 2,
                        getHeight() - borderWidth / 2);
                canvas.drawRoundRect(rectf, radius, radius, paint);
            }
        }


    }

    private void drawaPress(Canvas canvas) {
    }

//    重写父类的 onSizeChanged 方法，检测控件宽高的变化
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;
    }

    /**
     * 这里是参考其他开发者获取Bitmap内容的方法， 之前是因为没有考虑到 Glide 加载的图片
     * 导致drawable 类型是属于 SquaringDrawable 类型，导致强转失败
     * 这里是通过drawable不同的类型来进行获取Bitmap
     */

    private Bitmap getBitmapFromDrawable(Drawable drawable){
      try {


        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable){
            return ((BitmapDrawable) drawable).getBitmap();
        }else if(drawable instanceof ColorDrawable){
            bitmap= Bitmap.createBitmap(COLORDRAWABLE_DIMENSION,COLORDRAWABLE_DIMENSION,BITMAP_CONFIG);

        }else {
            bitmap=Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(),BITMAP_CONFIG);

        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0,0,canvas.getWidth(),getHeight());
        drawable.draw(canvas);
        return bitmap;
      }catch (OutOfMemoryError e){
          e.printStackTrace();
          return null;
      }



    }



}
