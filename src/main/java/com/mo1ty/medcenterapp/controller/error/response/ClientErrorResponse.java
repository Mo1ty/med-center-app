package com.mo1ty.medcenterapp.controller.error.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientErrorResponse {

    private int status;
    private String message;
    private long timestamp;


}
