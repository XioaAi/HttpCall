package com.example.zhao.frameworkdemo.net.progress;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.zhao.frameworkdemo.R;

public class ProgressDialog {

    private Dialog mDialog;
    private Boolean outSizeCancleAble = false;
    private IProgressDialogCancleListener iProgressDialogCancleListener;

    /**
     * 动态控制是否可以取消进度条    并监听取消事件
     * @param context
     * @param iProgressDialogCancleListener
     * @param outSizeCancleAble
     */
    public ProgressDialog(Context context, IProgressDialogCancleListener iProgressDialogCancleListener,
                          boolean outSizeCancleAble) {
        // TODO Auto-generated constructor stub
        this.mDialog = createLoadingDialog(context);
        this.outSizeCancleAble = outSizeCancleAble;
        this.iProgressDialogCancleListener = iProgressDialogCancleListener;
    }

    /**
     * 默认不能取消进度条
     * @param context
     */
    public ProgressDialog(Context context) {
        // TODO Auto-generated constructor stub
        this.mDialog = createLoadingDialog(context);
    }

    /**
     * 创建进度条
     * @param context
     * @return
     */
    private Dialog createLoadingDialog(Context context) {
        if(mDialog==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.loading_dialog, null,false);// 得到加载view
            LinearLayout layout =  v.findViewById(R.id.dialog_view);// 加载布局
            mDialog = new Dialog(context, R.style.net_loading_dialog);// 创建自定义样式dialog
            mDialog.setCancelable(outSizeCancleAble);
            mDialog.setContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.FILL_PARENT));// 设置布局
            if(outSizeCancleAble && iProgressDialogCancleListener !=null){
                mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        iProgressDialogCancleListener.cancleDialog();
                    }
                });
            }

        }
        return mDialog;

    }

    /**
     * 展示进度条
     */
    public void ShowProgress() {
        try {
            if(mDialog!=null){
                mDialog.show();
            }
        } catch (Exception e) {}
    }

    /**
     * 隐藏进度条
     */
    public void DismissProgress() {
        if(mDialog != null && mDialog.isShowing()){
            try{
                mDialog.dismiss();
            }catch (Exception e){}
        }
    }
}
