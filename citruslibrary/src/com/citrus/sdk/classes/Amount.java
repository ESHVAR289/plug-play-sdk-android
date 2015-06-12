package com.citrus.sdk.classes;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by salil on 24/4/15.
 */
public class Amount implements Parcelable {
    private String value;
    private String currency;

    /**
     *
     * @param value
     */
    public Amount(String value) {
        this.value = value;
        this.currency = "INR";
    }

    public Amount(String value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public String getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public static Amount fromJSONObject(JSONObject amountObject) {
        Amount amount = null;

        if (amountObject != null) {
            String value = amountObject.optString("value");
            String currency = amountObject.optString("currency");

            if (!TextUtils.isEmpty(value) && !TextUtils.isEmpty(currency)) {
                amount = new Amount(value, currency);
            }
        }

        return amount;
    }

    public static JSONObject toJSONObject(Amount amount) {
        JSONObject billObject = null;

        if (amount != null) {
            try {
                billObject = new JSONObject();
                billObject.put("value", amount.value);
                billObject.put("currency", amount.currency);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return billObject;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.value);
        dest.writeString(this.currency);
    }

    private Amount(Parcel in) {
        this.value = in.readString();
        this.currency = in.readString();
    }

    public static final Parcelable.Creator<Amount> CREATOR = new Parcelable.Creator<Amount>() {
        public Amount createFromParcel(Parcel source) {
            return new Amount(source);
        }

        public Amount[] newArray(int size) {
            return new Amount[size];
        }
    };

    @Override
    public String toString() {
        return "Amount{" +
                "value='" + value + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
