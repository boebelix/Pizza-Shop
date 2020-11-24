const emailValidator = (value) => {
    if(value.length == 0){
        document.getElementById("email").classList.add("btn_error");
        return false;
    }else{
        document.getElementById("email_signup").classList.remove("btn_error");
        return true;
    }
}

const nameValidator = (value) => {
    if(value.length == 0){
        document.getElementById("name_signup").classList.add("btn_error");
        return false;
    }else{
        document.getElementById("name_signup").classList.remove("btn_error");
        return true;
    }
}

const prenameValidator = (value) => {
    if(value.length == 0){
        document.getElementById("prename_signup").classList.add("btn_error");
        return false;
    }else{
        document.getElementById("prename_signup").classList.remove("btn_error");
        return true;
    }
}

const usernameValidator = (value) => {
    if(value.length == 0){
        document.getElementById("username_signup").classList.add("btn_error");
        return false;
    }else{
        document.getElementById("username_signup").classList.remove("btn_error");
        return true;
    }
}

const passwordValidator = (value,repeat) => {
    let ret = true;

    if(value.length == 0){
        document.getElementById("password_signup").classList.add("btn_error");
        ret = false;
    }
    if(repeat.length == 0){
        document.getElementById("password_signup_repeat").classList.add("btn_error");
        ret = false;
    }
    if(value != repeat){
        document.getElementById("password_signup").classList.add("btn_error");
        document.getElementById("password_signup_repeat").classList.add("btn_error");
        document.getElementById("error_password").style.display="block";
        ret=false;
    }

    if(ret == true){
        document.getElementById("password_signup").classList.remove("btn_error");
        document.getElementById("password_signup_repeat").classList.remove("btn_error");
        document.getElementById("error_password").style.display="none";
    }
    return ret;
}

const streetValidator = (value) => {
    if(value.length == 0){
        document.getElementById("street_signup").classList.add("btn_error");
        return false;
    }else{
        document.getElementById("street_signup").classList.remove("btn_error");
        return true;
    }
}

const numberValidator = (value) => {
    if(value.length == 0){
        document.getElementById("number_signup").classList.add("btn_error");
        return false;
    }else{
        document.getElementById("number_signup").classList.remove("btn_error");
        return true;
    }
}

const postalcodeValidator = (value) => {
    if(value.length == 0){
        document.getElementById("postalcode_signup").classList.add("btn_error");
        return false;
    }else{
        document.getElementById("postalcode_signup").classList.remove("btn_error");
        return true;
    }
}

const cityValidator = (value) => {
    if(value.length == 0){
        document.getElementById("city_signup").classList.add("btn_error");
        return false;
    }else{
        document.getElementById("city_signup").classList.remove("btn_error");
        return true;
    }
}

const countryValidator = (value) => {
    if(value.length == 0){
        document.getElementById("country_signup").classList.add("btn_error");
        return false;
    }else{
        document.getElementById("country_signup").classList.remove("btn_error");
        return true;
    }
}