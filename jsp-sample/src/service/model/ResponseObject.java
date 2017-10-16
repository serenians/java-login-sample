package service.model;

public class ResponseObject<T> {
    private Boolean isSuccess;
    private T data;
    private String message;

    private void initialize(Boolean isSuccess, String message, T data){
        this.isSuccess = isSuccess;
        this.data = data;
        this.message = message;
    }

    public ResponseObject(Boolean isSuccess, String message, T data){
        initialize(isSuccess, message, data);
    }
    public ResponseObject(Boolean isSuccess, String message){
        initialize(isSuccess,message, null);
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

