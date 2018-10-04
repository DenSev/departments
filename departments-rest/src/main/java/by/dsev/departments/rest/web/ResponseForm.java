package by.dsev.departments.rest.web;


/**
 * Form for server response contains responseCode and responseData fields to pass info to client
 *
 * @param <Data> - data type
 * @author DENIS SEVOSTEENKO
 */
public class ResponseForm<Data> {

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + responseCode;
        result = prime * result + ((responseData == null) ? 0 : responseData.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ResponseForm other = (ResponseForm) obj;
        if (responseCode != other.responseCode)
            return false;
        if (responseData == null) {
            if (other.responseData != null)
                return false;
        } else if (!responseData.equals(other.responseData))
            return false;
        return true;
    }

}
