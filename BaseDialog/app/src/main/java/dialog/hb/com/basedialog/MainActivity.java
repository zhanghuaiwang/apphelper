package dialog.hb.com.basedialog;

import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.hb.dialog.dialog.ConfirmDialog;
import com.hb.dialog.dialog.ConnectingDialog;
import com.hb.dialog.dialog.InputAndSelectDialog;
import com.hb.dialog.dialog.LoadingDialog;
import com.hb.dialog.dialog.LoadingFragmentDialog;
import com.hb.dialog.dialog.NoticeDialog;
import com.hb.dialog.myDialog.ActionSheetDialog;
import com.hb.dialog.myDialog.MultiListViewDialog;
import com.hb.dialog.myDialog.MyAlertDialog;
import com.hb.dialog.myDialog.MyAlertInputDialog;
import com.hb.dialog.myDialog.MyImageMsgDialog;
import com.hb.dialog.myDialog.MyPayInputDialog;
import com.hb.dialog.myDialog.MyPwdInputDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dialog.hb.com.basedialog.adapter.CommonAdapter;
import dialog.hb.com.basedialog.adapter.ViewHolder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AnimationDrawable connectAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_input_select,R.id.action_dialog, R.id.alert_dialog, R.id.alert_input_dialog, R.id.image_msg_dialog,
            R.id.confirm_dialog, R.id.connecting_dialog,
            R.id.loading_dialog, R.id.loading_fragment_dialog,
            R.id.pwd_dialog, R.id.pay_dialog, R.id.update_dialog, R.id.btn_muilt_list})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_input_select:
                InputAndSelectDialog inputAndSelectDialog = new InputAndSelectDialog(this)
                        .builder().setTitle("存为仪器套餐")
                        .setRadioButtonText(new String[]{"个人","科室"})
                        .setPositiveButton("存储",R.color.dialog_black, new InputAndSelectDialog.InputAndSelectListener() {
                            @Override
                            public void onSelected(String content, int index) {
                                Toast.makeText(MainActivity.this,content+index,Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                inputAndSelectDialog.show();
                break;
            case R.id.btn_muilt_list:
                List<String> dataList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    dataList.add("item" + (i + 1));
                }
                MultiListViewDialog mulDialog = new MultiListViewDialog(this).builder().setAdapter(new CommonAdapter<String>(this, dataList, R.layout.item_list) {
                    @Override
                    public void convert(ViewHolder helper, String item) {
                        helper.setText(R.id.tv_name, item);
                    }
                }).setNegativeButton("", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).setPostiveButton("", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                });
                mulDialog.show();
                break;
            case R.id.update_dialog:
                NoticeDialog noticeDialog = new NoticeDialog(this).builder()
                        .setMsg("请输入正确内容请输入正确内容请输入正确内容");
                noticeDialog.show();
//                UpdateDialog updateDialog = new UpdateDialog(this).builder().setCancelable(true).setMsg("1.连续登录失败两次，会出现验证码输入框\\n2.我的页面添加手动检测版本的功能\\n3.首页的升级，十分钟检测一次").setPositiveButton("升级", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                });
//                updateDialog.show();
                break;
            case R.id.action_dialog:
                ActionSheetDialog dialog = new ActionSheetDialog(this).builder().setTitle("请选择")
                        .addSheetItem("相册", null, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                showMsg("相册");
                            }
                        }).addSheetItem("拍照", null, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                showMsg("拍照");
                            }
                        });
                dialog.show();
                break;
            case R.id.alert_dialog:
                final MyAlertDialog myAlertDialog = new MyAlertDialog(this).builder()
                        .setTitle("确认吗？")
                        .setMsg("删除内容")
                        .setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMsg("确认");
                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMsg("取消");
                            }
                        });
                myAlertDialog.show();
                break;
            case R.id.alert_input_dialog:
                final MyAlertInputDialog myAlertInputDialog = new MyAlertInputDialog(this).builder()
                        .setTitle("请输入")
                        .setEditText("");
                myAlertInputDialog.setPositiveButton("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMsg(myAlertInputDialog.getResult());
                        myAlertInputDialog.dismiss();
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMsg("取消");
                        myAlertInputDialog.dismiss();
                    }
                });
                myAlertInputDialog.show();
                break;
            case R.id.image_msg_dialog:
                MyImageMsgDialog myImageMsgDialog = new MyImageMsgDialog(this).builder()
                        .setImageLogo(ContextCompat.getDrawable(this, R.mipmap.dialog_notice))
                        .setMsg("连接中...");
                ImageView logoImg = myImageMsgDialog.getLogoImg();
                logoImg.setImageResource(R.drawable.connect_animation);
                connectAnimation = (AnimationDrawable) logoImg.getDrawable();
                connectAnimation.start();
                myImageMsgDialog.show();
                break;
            case R.id.confirm_dialog:
                final ConfirmDialog confirmDialog = new ConfirmDialog(this);
                confirmDialog.setLogoImg(R.mipmap.dialog_notice).setMsg("提示");
                confirmDialog.setPositiveBtn(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                    }
                });
                confirmDialog.show();
                break;
            case R.id.connecting_dialog:
                ConnectingDialog connectingDialog = new ConnectingDialog(this);
                connectingDialog.setMessage("MSG");
                connectingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                });
                connectingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        return false;
                    }
                });
                connectingDialog.show();
                break;
            case R.id.loading_dialog:
                LoadingDialog loadingDialog = new LoadingDialog(this);
                loadingDialog.setMessage("loading");
                loadingDialog.show();
                break;
            case R.id.loading_fragment_dialog:
                LoadingFragmentDialog loadingFragmentDialog = new LoadingFragmentDialog();
                loadingFragmentDialog.setMessage("loading");
                loadingFragmentDialog.show(getSupportFragmentManager(), "msg");
                break;
            case R.id.pwd_dialog:
                final MyPwdInputDialog pwdDialog = new MyPwdInputDialog(this)
                        .builder()
                        .setTitle("请输入密码");
                pwdDialog.setPasswordListener(new MyPwdInputDialog.OnPasswordResultListener() {
                    @Override
                    public void onPasswordResult(String password) {
                        showMsg("您的输入结果：" + password);
                        pwdDialog.dismiss();
                    }
                });
                pwdDialog.show();
                break;
            case R.id.pay_dialog:
                final MyPayInputDialog myPayInputDialog = new MyPayInputDialog(this).Builder();
                myPayInputDialog.setResultListener(new MyPayInputDialog.ResultListener() {
                    @Override
                    public void onResult(String result) {
                        showMsg("您的输入结果：" + result);
                        myPayInputDialog.dismiss();
                    }
                }).setTitle("支付");
                myPayInputDialog.show();
                break;
        }
    }

    private void showMsg(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
