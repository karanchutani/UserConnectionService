package com.assignment.AstrotalkAssignment.DTO;

public class ResponseDTO {

    private String message;

    private Object data;

    private String status;

    private Object addInfo;

    public ResponseDTO(String message, Object data, String status, Object addInfo) {
        this.message = message;
        this.data = data;
        this.status = status;
        this.addInfo = addInfo;
    }

    public ResponseDTO(String message, Object data, String status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public ResponseDTO(){}

    public Object getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(Object addInfo) {
        this.addInfo = addInfo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
