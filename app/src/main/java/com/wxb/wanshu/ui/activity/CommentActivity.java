package com.wxb.wanshu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.wxb.wanshu.R;
import com.wxb.wanshu.base.BaseActivity;
import com.wxb.wanshu.bean.Base;
import com.wxb.wanshu.bean.UploadPictureBean;
import com.wxb.wanshu.component.AppComponent;
import com.wxb.wanshu.component.DaggerAccountComponent;
import com.wxb.wanshu.ui.adapter.easyadpater.CommentPictureAdapter;
import com.wxb.wanshu.ui.contract.CommentContract;
import com.wxb.wanshu.ui.presenter.CommentPresenter;
import com.wxb.wanshu.utils.FileUtils;
import com.wxb.wanshu.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.data;
import static android.R.attr.path;

public class CommentActivity extends BaseActivity implements CommentContract.View {

    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.et_qq)
    EditText etQq;
    @BindView(R.id.gridview)
    GridView gridview;
    @BindView(R.id.tv_send)
    TextView tvSend;

    private final int REQUEST_PHOTO_CODE = 10;
    private final int REQUEST_CAPTURE_CODE = 20;
    private Uri caputerPicUri;

    @Inject
    CommentPresenter mPresenter;
    private CommentPictureAdapter adapter;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, CommentActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAccountComponent.builder().appComponent(appComponent).build().inject(this);
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("意见反馈");
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        mPresenter.attachView(this);
    }

    @Override
    public void configViews() {
        List<String> picList = new ArrayList<>();
        picList.add("");
        adapter = new CommentPictureAdapter(mContext, picList);
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int count = adapter.getCount();
                if (i == count - 1) {
                    if (count > 6) {
                        ToastUtils.showLongToast("最多添加六张图片");
                    } else {
                        showActDialog();
                    }
                }
            }
        });
    }

    @OnClick(R.id.tv_send)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etContent.getText())) {
            ToastUtils.showLongToast("请填写反馈内容~");
            return;
        }
        if (TextUtils.isEmpty(etQq.getText())) {
            ToastUtils.showLongToast("请填写您的QQ~");
            return;
        }
        if (adapter.getCount() > 0) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("content", etContent.getText().toString().trim());
                jsonObject.put("qq", etQq.getText().toString().trim());
                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < adapter.getCount(); i++) {
                    String pic = adapter.getData(i);
                    if (!"".equals(pic)) {
                        jsonArray.put(pic);
                    }
                }
                jsonObject.put("imag", jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mPresenter.sendComment(jsonObject.toString());
        }
    }

    private void showActDialog() {
        View view1 = View.inflate(this, R.layout.dialog_pic_choose, null);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setView(view1);
        TextView tvEnter = (TextView) view1.findViewById(R.id.tv_fans_dialog_1);
        final TextView tvRename = (TextView) view1.findViewById(R.id.tv_fans_dialog_rename);
        final android.app.AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();

        tvEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caputerPicUri = FileUtils.toCaptureAction(mContext, REQUEST_CAPTURE_CODE);
                dialog.dismiss();
            }
        });
        tvRename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUtils.toPhotoAction(mContext, REQUEST_PHOTO_CODE);
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAPTURE_CODE) {//访问照相机
                String sdStatus = Environment.getExternalStorageState();
                if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                    return;
                }
                if (caputerPicUri != null) {
                    String picturePath = FileUtils.getRealFilePath(getApplicationContext(), caputerPicUri);
                    mPresenter.getUploadSinglePicture(new File(picturePath));
                }
            }
            if (requestCode == REQUEST_PHOTO_CODE) {//访问相册
                Uri originalUri = data.getData();
                String picturePath = FileUtils.getMediaPicturePath(originalUri, mContext);
                if (picturePath != null && !picturePath.equals("")) {
                    mPresenter.getUploadSinglePicture(new File(picturePath));
                }
            }
        }
    }

    @Override
    public void showError() {
    }

    @Override
    public void complete() {
        if (adapter.getCount() < 2) {
            String url = "http://7xkq88.com1.z0.glb.clouddn.com/23-content-201712-18-1513580286506.jpg";
            adapter.add(adapter.getCount() - 1, url);
        } else {
            String url = "http://lily.sunshe.com/c/742/1067742_issqed0gdz_o.jpg";
            adapter.add(adapter.getCount() - 1, url);
        }
    }

    @Override
    public void showUploadSinglePicture(UploadPictureBean data) {
        if (adapter.getCount() < 7) {
            UploadPictureBean.DataBean bean = data.getData();
            String url = bean.getUrl();
            adapter.add(adapter.getCount() - 1, url);
        }
    }

    @Override
    public void getCommentResult(Base result) {
        if (result.getErrcode() == 0) {
            ToastUtils.showLongToast("反馈成功");
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
