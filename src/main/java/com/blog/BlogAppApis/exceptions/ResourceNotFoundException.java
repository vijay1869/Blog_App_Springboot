package com.blog.BlogAppApis.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    //so that it is a unchecked exception
    String resourceName;
    String fieldName;
    long filedValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long filedValue) {
        super(String.format("%s not found with %s : %s",resourceName,fieldName,filedValue));//format specifier
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.filedValue = filedValue;
    }


}
