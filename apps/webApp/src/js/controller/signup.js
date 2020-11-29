const initSignup = () => {
    document.getElementById("register_submit_button").addEventListener("click", () => submitData());
    document.getElementById("register_back_button").addEventListener("click", () => setState("login_state"));
    document.getElementById("password_signup").addEventListener("keyup", () => isPasswordValid());
    let canvas = document.querySelector("#pwdCanvas");
    let ctx = canvas.getContext("2d");
    ctx.fillStyle = "#fff";
    ctx.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
}

const isPasswordValid = () => {
    const passwd = document.getElementById("password_signup").value;
    let format = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;

    let hasMinLen = passwd.length >= MIN_SIGNS;
    let hasUpperAndLowercase = passwd.toUpperCase() != passwd && passwd.toLowerCase() != passwd;
    let usesNumAndSpecial = /\d/.test(passwd) && format.test(passwd);
    let hasMoreThanSeven = passwd.length > SAFE_SIGNS;

    if(!hasMinLen) {
        document.querySelector("#passwdMsg").innerHTML = "nicht sicher";
    } else if(hasUpperAndLowercase && usesNumAndSpecial && hasMoreThanSeven){
        document.querySelector("#passwdMsg").innerHTML = "sehr sicher";
    } else if(hasUpperAndLowercase && usesNumAndSpecial) {
        document.querySelector("#passwdMsg").innerHTML = "sicher";
    } else if(hasUpperAndLowercase) {
        document.querySelector("#passwdMsg").innerHTML = "mittel sicher";
    } else {
        document.querySelector("#passwdMsg").innerHTML = "akzeptabel";
    }

    let size = 0;
    if (hasMinLen) {
        size += 4;
        if (hasUpperAndLowercase) {
            size += 4;
            if (usesNumAndSpecial) {
                size += 4;
                if(hasMoreThanSeven)
                    size += 4;
            }
        }
    }

    let c = document.querySelector("#pwdCanvas");
    let ctx = c.getContext("2d");

    ctx.fillStyle = "#c92e6f";
    ctx.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

    let barSize = size * 30;

    let grd = ctx.createLinearGradient(0, 0, barSize, 0);
    grd.addColorStop(0, "#2ec95a");
    grd.addColorStop(1, "#c92e6f");

    ctx.fillStyle = grd;
    ctx.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
}

const submitData = async() => {
    let accepted = false;
    let email = isLengthValid("email_signup");
    let name = isLengthValid("name_signup");
    let prename = isLengthValid("prename_signup");
    let username = isLengthValid("username_signup");
    let password = passwordValidator("password_signup", "password_signup_repeat");
    let street = isLengthValid("street_signup");
    let number = isLengthValid("number_signup");
    let postalcode = isLengthValid("postalcode_signup");
    let city = isLengthValid("city_signup");
    let country = isLengthValid("country_signup");

    if (email && name && prename && username && password && street &&
        number && postalcode && city && country) {
        accepted = true;
    }

    if (accepted) {
        let data = {
            "email": document.getElementById("email_signup").value,
            "lastName": document.getElementById("name_signup").value,
            "firstName": document.getElementById("prename_signup").value,
            "username": document.getElementById("username_signup").value,
            "password": document.getElementById("password_signup").value,
            "street": document.getElementById("street_signup").value,
            "number": document.getElementById("number_signup").value,
            "postalCode": document.getElementById("postalcode_signup").value,
            "city": document.getElementById("city_signup").value,
            "country": document.getElementById("country_signup").value
        };
        let error = await postData('http://localhost:9080/user', data);

        if (error.status == RESPONSE_OK) {
            console.log(error.message);
            document.getElementById("error_server").style.display = "none";
            document.getElementById("error_server").innerHTML = "";
            //Login here
            document.getElementById("username_login").value = data.username;
            document.getElementById("password_login").value = data.password;
            login();
        } else {
            console.log(error.message);
            document.getElementById("error_server").innerHTML = decode_utf(error.message);
            document.getElementById("error_server").style.display = "block";
        }
    }
}

const postData = async(url, data) => {
    let error;
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    if (response.status === RESPONSE_OK) {
        error = { "message": "Successfully registered", "status": RESPONSE_OK };
    } else if (response.status === RESPONSE_INTERNAL_SERVER_ERROR) {
        error = { "message": "Serverfehler", "status": RESPONSE_INTERNAL_SERVER_ERROR }
    } else {
        return await response.json();
    }
    return error;
}

const decode_utf = (message) => {
    let first_replace = message.replace(/ÃŸ/g, 'ß');
    let decoded_message;
    try {
        decoded_message = decodeURIComponent(escape(first_replace));
    } catch (e) {
        decoded_message = first_replace;
    }
    return decoded_message;
}