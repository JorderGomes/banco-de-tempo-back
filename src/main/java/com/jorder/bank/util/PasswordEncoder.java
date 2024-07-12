package com.jorder.bank.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncoder {
    
    public String encode(String text) {
        return  DigestUtils.sha256Hex(text);
    }

}
