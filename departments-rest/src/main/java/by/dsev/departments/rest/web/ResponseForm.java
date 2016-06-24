package by.dsev.departments.rest.web;


/**
 * Form for server response contains responseCode and responseData fields to pass info to client
 * @author DENIS SEVOSTEENKO
 * @param <Data> - data type
 */
public class ResponseForm <Data>{

    private int responseCode = Constants.RESPONSE_CODE_SUCCESS;
    private Data responseData;
    
    public int getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
    public Data getResponseData() {
        return responseData;
    }
    public void setResponseData(Data responseData) {
        this.responseData = responseData;
    }

}
