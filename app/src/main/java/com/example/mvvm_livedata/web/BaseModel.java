package com.example.mvvm_livedata.web;

/**
 * Created by Gagan on 8/2/18.
 */

import java.io.Serializable;

/**
 * base of all the API responses.
 *
 * @param <G> type of {@link BaseModel#data} field.
 */
public class BaseModel<G> implements Serializable
{
    private String status;
    private String message;
    private G      data;

    public boolean getStatus() {
        return status.equalsIgnoreCase("OK");
    }

    public String getMessage() {
        return message;
    }

    public G getData() {
        return data;
    }


    //    {
//        "status": "OK",
//            "message": "Sucessfully sent",
//            "data": {
//        "phone_number": "345345",
//                "otp": 4806
//    }
//    }
}
