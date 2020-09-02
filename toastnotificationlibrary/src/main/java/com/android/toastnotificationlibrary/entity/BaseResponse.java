/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.toastnotificationlibrary.entity;

import android.app.Activity;

import com.android.toastnotificationlibrary.C;
import com.android.toastnotificationlibrary.R;

import java.io.Serializable;

/**
 * ================================================
 * 如果你服务器返回的数据格式固定为这种方式(这里只提供思想,服务器返回的数据格式可能不一致,可根据自家服务器返回的格式作更改)
 * 指定范型即可改变 {@code data} 字段的类型, 达到重用 {@link BaseResponse}, 如果你实在看不懂, 请忽略
 * <p>
 * Created by JessYan on 26/09/2016 15:19
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class BaseResponse<T> implements Serializable {
    private T data;
    private String code;
    private String msg;

    public T getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 请求是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        if (code.equals(C.RequestSuccess)) {
            return true;
        } else {
            return false;
        }
    }

    public String getMsgByCode(Activity activity, String code){
        String msg = "";
        switch (code){
            case C.RequestSuccess:
                msg = activity.getString(R.string.code_success);
                break;
            case C.RequestCoinsNo:
                msg = activity.getString(R.string.code_coin_no);
                break;
            case C.RequestCannotUpdate:
                msg = activity.getString(R.string.cannot_update);
                break;
            case C.RequestIncorrectInvitationCode:
                msg = activity.getString(R.string.incorrect_invitation_code);
                break;
            case C.RequestInvitationCodeFilled:
                msg = activity.getString(R.string.invitation_code_filled);
                break;
            case C.RequestInvitationCompleted:
                msg = activity.getString(R.string.invitation_completed);
                break;
            case C.RequestFilesFill:
                msg = activity.getString(R.string.files_exceeded);
                break;
        }
        return msg;
    }
}
