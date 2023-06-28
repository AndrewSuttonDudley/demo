package com.crscreditapi.demo.model.mongo.sub;

public class RequestResponsePayload {

    private String className;

    private Object payload;

    public RequestResponsePayload() {
    }

    public RequestResponsePayload(String className,
                                  Object payload) {
        this.className = className;
        this.payload = payload;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
