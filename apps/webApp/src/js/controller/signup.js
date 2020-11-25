const initSignup = () => {
    document.getElementById("register_submit_button").addEventListener("click", () => submitData());
    document.getElementById("register_back_button").addEventListener("click", () => setState("login_state"));
}

const submitData = async () => {
    let accepted = false;
    let email = lengthValidator("email_signup");
    let name = lengthValidator("name_signup");
    let prename = lengthValidator("prename_signup");
    let username = lengthValidator("username_signup");
    let password = passwordValidator("password_signup","password_signup_repeat");
    let street = lengthValidator("street_signup");
    let number = lengthValidator("number_signup");
    let postalcode = lengthValidator("postalcode_signup");
    let city = lengthValidator("city_signup");
    let country = lengthValidator("country_signup");

    if (email && name &&prename && username && password && street
        && number && postalcode && city && country){
            accepted = true;
        }

    if(accepted){
    let data={"email" : document.getElementById("email_signup").value,
    "lastName" : document.getElementById("name_signup").value,
    "firstName" : document.getElementById("prename_signup").value,
    "username" : document.getElementById("username_signup").value,
    "password" : document.getElementById("password_signup").value,
    "street" : document.getElementById("street_signup").value,
    "number" : document.getElementById("number_signup").value,
    "postalCode" : document.getElementById("postalcode_signup").value,
    "city" : document.getElementById("city_signup").value,
    "country" : document.getElementById("country_signup").value};
    let error= await postData('http://localhost:9080/user', data);
    
    if(error.status==200){
      console.log(error.message);
      document.getElementById("error_server").style.display="none";
      document.getElementById("error_server").innerHTML="";
      //Login here
    }else{
      console.log(error.message);
      document.getElementById("error_server").innerHTML=decode_utf(error.message);
      document.getElementById("error_server").style.display="block";
    }
    }
}

const postData = async (url, data) => {
  let error;
    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: JSON.stringify(data) // body data type must match "Content-Type" header
    });
    if(response.status === 200){
      error = {"message" : "Successfully registered", "status": 200};
    }else{
    return await response.json();
    }
    return error;
  }

 const decode_utf = (message) => {
      let first_replace = message.replace(/ÃŸ/g, 'ß');
      let decoded_message;
    try{
      decoded_message=decodeURIComponent(escape(first_replace));
    }catch(e){
      decoded_message=first_replace;
    }
    return decoded_message;
  }

