import 'package:app/endpoints/user_endpoint.dart';
import 'package:app/models/login_data.dart';
import 'package:flutter/material.dart';

class LoginUser extends StatelessWidget {
  static const String routeName = "/loginUserScreen";

  final GlobalKey<FormState> _formKey = GlobalKey();

  final TextEditingController username = TextEditingController();
  final TextEditingController password = TextEditingController();

  // TODO Validator übernehmen aus Backend oder aus WebApp

  @override
  Widget build(BuildContext context) {
    const radius = 8.0;
    const padding = 4.0;

    return Scaffold(
      appBar: AppBar(
        title: Text('Login'),
      ),
      body: Container(
        decoration: new BoxDecoration(
          image: new DecorationImage(
            image: new AssetImage("images/Background_Login.jpg"),
            fit: BoxFit.cover,
          ),
        ),
        child: Padding(
          padding: const EdgeInsets.all(padding),
          child: Card(
            elevation: 8,
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(radius),
            ),
            child: Form(
              key: _formKey,
              child: ListView(
                padding: const EdgeInsets.all(padding),
                children: [
                  Column(
                    children: [
                      Text(
                        'Login Daten',
                        style: TextStyle(fontSize: 18),
                      ),
                      TextFormField(
                        decoration: InputDecoration(
                          icon: const Icon(Icons.person),
                          labelText: "Nutzername",
                        ),
                        controller: username,
                      ),
                      TextFormField(
                        decoration: const InputDecoration(
                          icon: const Icon(Icons.vpn_key_rounded),
                          labelText: "Passwort Eingabe",
                        ),
                        controller: password,
                        validator: (value) => value?.isEmpty ?? true
                            ? "Bitte geben Sie ein Passwort ein"
                            : null,
                      ),
                    ],
                  ),
                  RaisedButton(
                    child: Text("Einloggen"),
                    onPressed: () {
                      if (_formKey.currentState.validate()) {
                        UserService.instance().loginUser(
                          new LoginData(
                            username: username.text,
                            password: password.text,
                          ),
                        );
                        //Navigator.pop(context);
                      }
                    },
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
