package com.dream.will.hydrogenballoon.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.utils.DisplayUtil;


public class CollapseTextView extends LinearLayout implements View.OnClickListener {
    private static final int NORMAL_STATE = 0; //行数不足
    private static final int EXPAND_STATE = 1;//展开状态
    private static final int COLLAPSE_STATE = 2;//收缩状态
    private static final String DEFAULT_COLLAPSE_TEXT = "全文"; //收缩时的提示文本
    private static final String DEFAULT_EXPAND_TEXT = "收起"; //展开时的提示文本
    private static final int DEFAULT_MINI_LINE = 6; //默认最小行数
    private static final int DEFAULT_MAX_LINE = 100;//最大行数，这里设为100
    private static final int DEFAULT_TITLE_SIZE = R.dimen.default_title_size;
    //    private static final int DEFAULT_TITLE_SIZE = 20;
    private static final int DEFAULT_TITLE_COLOR = R.color.black;
    private static final int DEFAULT_CONTENT_SIZE = R.dimen.default_content_size;
    //    private static final int DEFAULT_CONTENT_SIZE = 16;
    private static final int DEFAULT_CONTENT_COLOR = R.color.gray;
    private static final int DEFAULT_TIPS_COLOR = R.color.colorPrimary;
    private static final int DEFAULT_TIPS_SIZE = R.dimen.default_tips_size;
    //    private static final int DEFAULT_TIPS_SIZE = 14;
    private Context context;
    private TextView mTitleTV;//内容
    private TextView mContentTV;//内容
    private TextView mTipsTV;//提示
    private int miniLine = DEFAULT_MINI_LINE;//最小行数
    private int collapseState = COLLAPSE_STATE;//折叠状态
    private boolean isTitleBold = true;
    private boolean isNeedLayout = false;   //是否需要请求重新布局
    private boolean isSetText = false;  //是否设置文本过后
    private String title;
    private String content;
    private String expandTipsText;
    private String collapseTipsText;
    private float titleSize;
    private float contentSize;
    private float tipsSize;
    private int titleColor;
    private int contentColor;
    private int tipsColor;

    public CollapseTextView(Context context) {
        super(context, null);
        Log.i("google.karlo", "CollapseTextView: 1");
    }

    public CollapseTextView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        Log.i("google.karlo", "CollapseTextView: 2");
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CollapseTextView);
        title = typedArray.getString(R.styleable.CollapseTextView_mTitleText);
        content = typedArray.getString(R.styleable.CollapseTextView_mContentText);
        expandTipsText = typedArray.getString(R.styleable.CollapseTextView_mExpandTipsText);
        collapseTipsText = typedArray.getString(R.styleable.CollapseTextView_mCollapseTipsText);
        titleColor = typedArray.getColor(R.styleable.CollapseTextView_mTitleTextColor, getResources().getColor(DEFAULT_TITLE_COLOR));
        contentColor = typedArray.getColor(R.styleable.CollapseTextView_mContentTextColor, getResources().getColor(DEFAULT_CONTENT_COLOR));
        tipsColor = typedArray.getColor(R.styleable.CollapseTextView_mTipsTextColor, getResources().getColor(DEFAULT_TIPS_COLOR));
        titleSize = typedArray.getDimension(R.styleable.CollapseTextView_mTitleTextSize, getResources().getDimension(DEFAULT_TITLE_SIZE));
//        titleSize = typedArray.getDimension(R.styleable.CollapseTextView_mTitleTextSize, DisplayUtil.sp2px(context, DEFAULT_TITLE_SIZE));
        contentSize = typedArray.getDimension(R.styleable.CollapseTextView_mContentTextSize, getResources().getDimension(DEFAULT_CONTENT_SIZE));
//        contentSize = typedArray.getDimension(R.styleable.CollapseTextView_mContentTextSize, DisplayUtil.sp2px(context, DEFAULT_CONTENT_SIZE));
        tipsSize = typedArray.getDimension(R.styleable.CollapseTextView_mTipsTextSize, getResources().getDimension(DEFAULT_TIPS_SIZE));
//        tipsSize = typedArray.getDimension(R.styleable.CollapseTextView_mTipsTextSize, DisplayUtil.sp2px(context, DEFAULT_TIPS_SIZE));
        typedArray.recycle();
        initView();
    }

    public CollapseTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i("google.karlo", "CollapseTextView: 3");
    }

    private void initView() {
        Log.i("google.karlo", "initView: ");
        if (null == collapseTipsText) {
            collapseTipsText = DEFAULT_COLLAPSE_TEXT;
        }
        if (null == expandTipsText) {
            expandTipsText = DEFAULT_EXPAND_TEXT;
        }
        View view = View.inflate(context, R.layout.collapse_textview_layout, this);
        mTitleTV = (TextView) view.findViewById(R.id.collapse_title);
        mTitleTV.setText(title);
        mTitleTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
        mTitleTV.setTextColor(titleColor);
        setTitleBold(true);
        mContentTV = (TextView) view.findViewById(R.id.collapse_context);
        mContentTV.setText(content);
        mContentTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, contentSize);
        mContentTV.setTextColor(contentColor);
        mTipsTV = (TextView) view.findViewById(R.id.collapse_tips);
        mTitleTV.setText(collapseTipsText);
        mTipsTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, tipsSize);
        mTipsTV.setTextColor(tipsColor);
        mTipsTV.setOnClickListener(this);
    }

    public int getMiniLine() {
        return miniLine;
    }

    public void setMiniLine(int miniLine) {
        this.miniLine = miniLine;
    }

    public String getExpanedTipsText() {
        return expandTipsText;
    }

    public void setExpanedTipsText(String expanedTipsText) {
        this.expandTipsText = expanedTipsText;
    }

    public String getCollapseTipsText() {
        return collapseTipsText;
    }

    public void setCollapseTipsText(String collapseTipsText) {
        this.collapseTipsText = collapseTipsText;
    }

    public float getContentSize() {
        return contentSize;
    }

    public void setContentSize(float contentSize) {
        this.contentSize = contentSize;
        mContentTV.setTextSize(DisplayUtil.sp2px(context, contentSize));
    }

    public float getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(float titleSize) {
        this.titleSize = titleSize;
        mTitleTV.setTextSize(DisplayUtil.sp2px(context, titleSize));
    }

    public float getTipsSize() {
        return tipsSize;
    }

    public void setTipsSize(float tipsSize) {
        this.tipsSize = tipsSize;
        mTipsTV.setTextSize(DisplayUtil.sp2px(context, tipsSize));
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(@ColorInt int titleColor) {
        this.titleColor = titleColor;
        mTitleTV.setTextColor(titleColor);
    }

    public int getContentColor() {
        return contentColor;
    }

    public void setContentColor(@ColorInt int contentColor) {
        this.contentColor = contentColor;
        mContentTV.setTextColor(contentColor);
    }

    public int getTipsColor() {
        return tipsColor;
    }

    public void setTipsColor(@ColorInt int tipsColor) {
        this.tipsColor = tipsColor;
        mTipsTV.setTextColor(tipsColor);
    }

    public TextView getmTipsTV() {
        return mTipsTV;
    }

    public void setmTipsTV(TextView mTipsTV) {
        this.mTipsTV = mTipsTV;
    }

    public String getExpandTipsText() {
        return expandTipsText;
    }

    @Override
    public void onClick(View v) {
        isNeedLayout = true;
        if (EXPAND_STATE == collapseState) {
            collapseState = COLLAPSE_STATE;
        } else if (COLLAPSE_STATE == collapseState) {
            collapseState = EXPAND_STATE;
        }
        requestLayout();
    }

    public void setExpandTipsText(String expandTipsText) {
        this.expandTipsText = expandTipsText;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        isNeedLayout = true;
        isSetText = true;
        mContentTV.setText(content);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        mTitleTV.setText(title);
    }

    public boolean isTitleBold() {
        return isTitleBold;
    }

    public void setTitleBold(boolean titleBold) {
        this.isTitleBold = titleBold;
        if (isTitleBold) {
            mTitleTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        } else {
            mTitleTV.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (isNeedLayout) {
            isNeedLayout = false;
            Log.i("google.karlo", "onLayout: 行数" + mContentTV.getLineCount());
            if (isSetText && mContentTV.getLineCount() <= miniLine) {
                isSetText = false;
                Toast.makeText(context, "行数不足", Toast.LENGTH_SHORT).show();
                collapseState = NORMAL_STATE;
                mTipsTV.setVisibility(GONE);
                return;
            }
            isSetText = false;
            mTipsTV.setVisibility(VISIBLE);
            if (EXPAND_STATE == collapseState) {
                Toast.makeText(context, "展开", Toast.LENGTH_SHORT).show();
                mContentTV.setMaxLines(DEFAULT_MAX_LINE);
                mTipsTV.setText(expandTipsText);
            } else if (COLLAPSE_STATE == collapseState) {
                Toast.makeText(context, "收缩", Toast.LENGTH_SHORT).show();
                mContentTV.setMaxLines(miniLine);
                mTipsTV.setText(collapseTipsText);
            }
        }
    }
}
