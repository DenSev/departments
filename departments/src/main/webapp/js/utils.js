function processServerResponse(data, noSuccessMessage){

    var success = 0;
    var error = -1;
    var validation_error = -2;
    var delete_error = -3;

    var messages = {
            success : "Success!",
            error : "Error!",
            validationError : "Validation Error!",
            deleteError : "Cannot delete object, since it's in use somewhere else!"
    }

    if(data.responseCode == success){
        if(!noSuccessMessage){
            toastr.success(messages.success);
        }
        return data.responseData
    } else if (data.responseCode == error) {
        toastr.error(messages.error);
    } else if (data.responseCode == validation_error) {
        toastr.error(messages.validationError);
    } else if (data.responseCode == delete_error) {
        toastr.error(messages.deleteError);
    }

}