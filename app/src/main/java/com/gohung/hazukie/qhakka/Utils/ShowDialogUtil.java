package com.gohung.hazukie.qhakka.Utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gohung.hazukie.qhakka.R;
import com.qmuiteam.qmui.layout.QMUIFrameLayout;
import com.qmuiteam.qmui.skin.QMUISkinHelper;
import com.qmuiteam.qmui.skin.QMUISkinManager;
import com.qmuiteam.qmui.skin.QMUISkinValueBuilder;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.popup.QMUIFullScreenPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopups;

public class ShowDialogUtil {
    private Context getContext;

    public ShowDialogUtil(Context getContext){
        this.getContext=getContext;
    }

    public void ShowDialog(View AttachView , int str){
        QMUISkinValueBuilder builder = QMUISkinValueBuilder.acquire();
        QMUIFrameLayout frameLayout = new QMUIFrameLayout(getContext);
        frameLayout.setBackground(
                QMUIResHelper.getAttrDrawable(getContext, R.attr.qmui_skin_support_popup_bg));
        builder.background(R.attr.qmui_skin_support_popup_bg);
        QMUISkinHelper.setSkinValue(frameLayout, builder);
        frameLayout.setRadius(QMUIDisplayHelper.dp2px(getContext, 12));
        int padding = QMUIDisplayHelper.dp2px(getContext, 20);
        frameLayout.setPadding(padding, padding, padding, padding);


        ScrollView scrollView=new ScrollView(getContext);

        TextView textView = new TextView(getContext);
        textView.setLineSpacing(QMUIDisplayHelper.dp2px(getContext, 4), 1.0f);
        textView.setPadding(padding, padding, padding, padding);
        textView.setText(getContext.getText(str));
        textView.setTextColor(
                QMUIResHelper.getAttrColor(getContext, R.attr.app_skin_common_title_text_color));

        builder.clear();
        builder.textColor(R.attr.app_skin_common_title_text_color);
        QMUISkinHelper.setSkinValue(textView, builder);
        textView.setGravity(Gravity.LEFT);

        builder.release();

        int size = QMUIDisplayHelper.dp2px(getContext, 250);
        int height=QMUIDisplayHelper.dp2px(getContext,400);
        ViewGroup.LayoutParams scrollp=new ViewGroup.LayoutParams(size,height);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(size, height);
        scrollView.addView(textView,scrollp);
        frameLayout.addView(scrollView, lp);

        QMUIPopups.fullScreenPopup(getContext)
                .addView(frameLayout)
                .closeBtn(false)
                .skinManager(QMUISkinManager.defaultInstance(getContext))
                .onBlankClick(new QMUIFullScreenPopup.OnBlankClickListener() {
                    @Override
                    public void onBlankClick(QMUIFullScreenPopup popup) {
                        popup.dismiss();
                        //Toast.makeText(getContext(), "点击到空白区域", Toast.LENGTH_SHORT).show();
                    }
                })
                .onDismiss(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                        //Toast.makeText(getContext(), "onDismiss", Toast.LENGTH_SHORT).show();
                    }
                })
                .show(AttachView);
    }
}

