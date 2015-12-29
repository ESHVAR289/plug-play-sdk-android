/*
 *
 *    Copyright 2014 Citrus Payment Solutions Pvt. Ltd.
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 * /
 */

package com.citrus.sdk.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by salil on 12/5/15.
 */
public class CitrusError extends CitrusResponse implements Parcelable {
    public CitrusError(String message, Status status) {
        super(message, status);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.message);
        dest.writeInt(this.status == null ? -1 : this.status.ordinal());
    }

    private CitrusError(Parcel in) {
        this.message = in.readString();
        int tmpStatus = in.readInt();
        this.status = tmpStatus == -1 ? null : Status.values()[tmpStatus];
    }

    public static final Creator<CitrusError> CREATOR = new Creator<CitrusError>() {
        public CitrusError createFromParcel(Parcel source) {
            return new CitrusError(source);
        }

        public CitrusError[] newArray(int size) {
            return new CitrusError[size];
        }
    };
}
