import 'package:app/models/http_exception.dart';
import 'package:app/models/login_data.dart';
import 'package:app/services/security/auth.dart';
import 'package:app/services/signin_service.dart';
import 'package:app/widgets/navigation.dart';
import 'package:flutter/material.dart';

class LoginUser extends StatefulWidget {
  static const String routeName = "/loginUserScreen";

  @override
  _LoginUserState createState() => _LoginUserState();
}

class _LoginUserState extends State<LoginUser> {
  final GlobalKey<FormState> _formKey = GlobalKey();
  final TextEditingController username = TextEditingController();
  final TextEditingController password = TextEditingController();

  String _msgSuccess = '';
  String _msgError = '';

  @override
  Widget build(BuildContext context) {
    const radius = 8.0;
    const padding = 4.0;

    return Scaffold(
      appBar: AppBar(
        title: Text('Non Stop Pizza'),
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
                  _msgError == null
                      ? Container()
                      : Text(
                    _msgError,
                    style: TextStyle(color: Colors.redAccent),
                  ),
                  _msgSuccess == null
                      ? Container()
                      : Text(
                          _msgSuccess,
                          style: TextStyle(color: Colors.green),
                        ),
                  _submitLoginData(),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }

  RaisedButton _submitLoginData() {
    SignInService signInService = SignInService.instance();

    return RaisedButton(
      child: Text("Einloggen"),
      onPressed: () {
        if (_formKey.currentState.validate()) {
          try {
            signInService.signInUser(
              new LoginData(
                username: username.text,
                password: password.text,
              ),
            );
            _msgSuccess = 'Erfolgreich eingeloggt';

            Navigator.pop(context);
            Navigator.pushReplacementNamed(context, Navigation.routeName);

          } on HttpException catch (error) {
            setState(() {
              _msgError = error.message;
            });

            print('Error login');
          } catch (error) {
            throw (error);
          }
        }
      },
    );
  }
}
