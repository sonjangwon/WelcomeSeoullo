package com.example.jangwon.welcomeseoullo.HomeMenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

/**
 * Created by woga1 on 2017-09-08.
 */

public class PermissionRequester {

    /**
     * 요청 AndroidOS의 버젼이 마쉬멜로우 이상 버젼이 아닐 경우
     */
    public static final int NOT_SUPPORT_VERSION = 2;
    /**
     * 요청 권한을 이미 가지고 있을 경우
     */
    public static final int ALREADY_GRANTED = -1;
    /**
     * 권한을 System에게 요청한 경우 * Activity의 onRequestPermissionsResult() 로 결과 리턴됨.
     */
    public static final int REQUEST_PERMISSION = 0;
    private Activity context;
    private Builder builder;

    private void setBuilder(Builder builder) {
        this.builder = builder;
    }

    private PermissionRequester(Activity context) {
        this.context = context;
    }

    public int request(final String permission, final int requestCode, final OnClickDenyButtonListener denyAction) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { /* * 해당 App이 특정 권한을 가지고 있는지 검사함. * 리턴결과는 PackageManager.PERMISSION_DENIED 와 PackageManager.PERMISSION_GRANTED로 나눠짐.
    * PackageManager.PERMISSION_DENIED : 권한이 없음
    * * PackageManager.PERMISSION_GRANTED : 권한이 있음. */
            int permissionCheck = ContextCompat.checkSelfPermission(context, permission);
        /* * 해당 권한이 없을 경우 처리 방법 */
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            /* * 권한을 취득할 때 사용자로부터 확인을 받아야 하는지 확인
            * * 여기서 true가 나올 경우는 해당 앱에서 한번이라도 권한을 Deny한 경우일 때 말고는 없음.
            * * 권한에 대해서 허가하지 않은 경우 다시 한번 권한의 취득을 위해 사용자에게 이유를 고지해야 함.
             * * Marshmellow 버젼 이상부터 사용가능함. */
                if (context.shouldShowRequestPermissionRationale(permission)) {
                /* * 권한 취득해야 하는 이유를 Dialog 등을 통해서 알린다. */
                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setTitle(builder.getTitle()).setMessage(builder.getMessage()).setPositiveButton(builder.getPositiveButtonName(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                    /* * 권한의 취득을 요청한다.
                    * * 취득하고자 하는 권한을 배열에 넣고 요청한다.
                    * * 뒤에 들어가는 파라미터(requestCode)는 onRequestPermissionsResult() 에서 권한 취득 결과에서 사용된다.
                    * * startActiviryForResult의 Request Code와 유사함.
                    * */
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                context.requestPermissions(new String[]{permission}, requestCode);
                            }
                        }
                    }).setNegativeButton(builder.getNegativeButtonName(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            denyAction.onClick(context);
                        }
                    }).create().show();
                    return REQUEST_PERMISSION;
                } else {
                    /* * 권한의 취득 요청을 처음 할 때 * 권한의 취득을 요청한다.
                    * * 취득하고자 하는 권한을 배열에 넣고 요청한다.
                    * * 뒤에 들어가는 파라미터(1000)는 onRequestPermissionsResult() 에서 권한 취득 결과에서 사용된다.
                     * * startActiviryForResult의 Request Code와 유사함. */
                    context.requestPermissions(new String[]{permission}, requestCode);
                    return REQUEST_PERMISSION;
                }
            } else {
            /* * 이미 권한을 가지고 있을 경우 * 해야할 일을 수행한다. */
                return ALREADY_GRANTED;
            }
        }
        return NOT_SUPPORT_VERSION;
    }

    public static class Builder {
        private PermissionRequester requester;

        public Builder(Activity context) {
            requester = new PermissionRequester(context);
        }

        private String title = "권한 요청";
        private String message = "기능의 사용을 위해 권한이 필요합니다.";
        private String positiveButtonName = "네";
        private String negativeButtonName = "아니요";

        public String getTitle() {
            return title;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getMessage() {
            return message;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public String getPositiveButtonName() {
            return positiveButtonName;
        }

        public Builder setPositiveButtonName(String positiveButtonName) {
            this.positiveButtonName = positiveButtonName;
            return this;
        }

        public String getNegativeButtonName() {
            return negativeButtonName;
        }

        public Builder setNegativeButtonName(String negativeButtonName) {
            this.negativeButtonName = negativeButtonName;
            return this;
        }

        public PermissionRequester create() {
            this.requester.setBuilder(this);
            return this.requester;
        }
    }

    public interface OnClickDenyButtonListener {
        public void onClick(Activity activity);
    }

}
