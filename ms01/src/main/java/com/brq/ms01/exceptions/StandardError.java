package com.brq.ms01.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {

    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}

