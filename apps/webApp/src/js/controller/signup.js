const initSignup = () => {
    document.getElementById("register_submit_button").addEventListener("click", () => submitData());
    document.getElementById("register_back_button").addEventListener("click", () => setState("login_state"));
    document.getElementById("password_signup").addEventListener("keyup", () => isPasswordValid());
    let canvas = document.querySelector("#pwdCanvas");
    let ctx = canvas.getContext("2d");
    ctx.fillStyle = "#FFF";
    ctx.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
}

const isPasswordValid = () => {
    const passwd = document.getElementById("password_signup").value;
    let hasMinLen = passwd.length >= MIN_SIGNS;
    let hasUpperAndLowercase = passwd.toUpperCase() != passwd && passwd.toLowerCase() != passwd;
    let usesNumAndSpecial = /\d/.test(passwd) && REGEX_SIGNS.test(passwd);
	let hasMoreThanSeven = passwd.length > SAFE_SIGNS;
	let barColorLeft = "#FFF";
	let barColorRight = "#FFF";
	let factor = 0;

	if(passwd.length === 0) {
		factor = 5 * CANVAS_FACTOR;
	} else if(!hasMinLen) {
		document.querySelector("#passwdMsg").innerHTML = "nicht sicher";
		barColorLeft = "#F00";
		factor = 1 * CANVAS_FACTOR;
    } else if(hasUpperAndLowercase && usesNumAndSpecial && hasMoreThanSeven){
		document.querySelector("#passwdMsg").innerHTML = "sehr sicher";
		barColorLeft = "#4D4";
		factor = 5 * CANVAS_FACTOR;
    } else if(hasUpperAndLowercase && usesNumAndSpecial) {
		document.querySelector("#passwdMsg").innerHTML = "sicher";
		barColorLeft = "#3B3";
		factor = 4 * CANVAS_FACTOR;
    } else if(hasUpperAndLowercase) {
		document.querySelector("#passwdMsg").innerHTML = "mittel sicher";
		barColorLeft = "#DB0";
		factor = 3 * CANVAS_FACTOR;
    } else {
		document.querySelector("#passwdMsg").innerHTML = "akzeptabel";
		barColorLeft = "#D50";
		factor = 2 * CANVAS_FACTOR;
    }

    let c = document.querySelector("#pwdCanvas");
    let ctx = c.getContext("2d");

    let grd = ctx.createLinearGradient(0, 0, CANVAS_WIDTH * factor, 0);
    grd.addColorStop(0, barColorLeft);
    grd.addColorStop(1, barColorRight);

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
