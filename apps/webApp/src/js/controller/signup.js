const initSignup = () => {
    document.getElementById("register_submit_button").addEventListener("click", () => submitData());
    document.getElementById("register_back_button").addEventListener("click", () => setState("login_state"));
}

const submitData = async () => {
    let accepted = false;
    let email = emailValidator(document.getElementById("email_signup").value);
    let name = nameValidator(document.getElementById("name_signup").value);
    let prename = prenameValidator(document.getElementById("prename_signup").value);
    let username = usernameValidator(document.getElementById("username_signup").value);
    let password = passwordValidator(document.getElementById("password_signup").value,document.getElementById("password_signup_repeat").value);
    let street = streetValidator(document.getElementById("street_signup").value);
    let number = numberValidator(document.getElementById("number_signup").value);
    let postalcode = postalcodeValidator(document.getElementById("postalcode_signup").value);
    let city = cityValidator(document.getElementById("city_signup").value);
    let country = countryValidator(document.getElementById("country_signup").value);

    if (email && name &&prename && username && password && street
        && number && postalcode && city && country){
            accepted = true;
        }

    if(accepted != false){
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
    postData('http://localhost:9080/user', data);
    }
}

const postData = async (url, data) => {
    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: JSON.stringify(data) // body data type must match "Content-Type" header
    }).finally(err => console.log(err));
  }


