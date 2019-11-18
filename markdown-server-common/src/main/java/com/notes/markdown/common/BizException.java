package com.notes.markdown.common;

public class BizException extends Throwable{
    public BizException() {super();}

    public BizException(String message) {
        super(message);
    }

//    public BizException(String code, String message) {
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append(code);
//        stringBuffer.append(":");
//        stringBuffer.append(message);
//        String msg = stringBuffer.toString();
//    }
}
