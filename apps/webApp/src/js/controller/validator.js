const lengthValidator = (id) => {
    const element = document.getElementById(id);
    if(element.value.length == 0){
        element.classList.add("btn_error");
        return false;
    }else{
        element.classList.remove("btn_error");
        return true;
    }
}

const passwordValidator = (id_passw,id_repeat) => {
    let ret = true;
    const first = document.getElementById(id_passw);
    const second = document.getElementById(id_repeat);
    const error = document.getElementById("error_password");

    if(first.value.length == 0){
        first.classList.add("btn_error");
        ret = false;
    }
    if(second.value.length == 0){
        second.classList.add("btn_error");
        ret = false;
    }
    if(first.value != second.value){
        first.classList.add("btn_error");
        second.classList.add("btn_error");
        error.style.display="block";
        ret=false;
    }

    if(ret){
        first.classList.remove("btn_error");
        second.classList.remove("btn_error");
        error.style.display="none";
    }
    return ret;
}