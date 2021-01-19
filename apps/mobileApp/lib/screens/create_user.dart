import 'package:app/models/http_exception.dart';
import 'package:app/models/user.dart';
import 'package:app/services/signup_service.dart';
import 'package:flutter/material.dart';

class CreateUser extends StatefulWidget {
  static const String routeName = "/createUserScreen";

  @override
  _CreateUserState createState() => _CreateUserState();
}

class _CreateUserState extends State<CreateUser> {
  final GlobalKey<FormState> _formKey = GlobalKey();
  final TextEditingController firstName = TextEditingController();
  final TextEditingController lastName = TextEditingController();
  final TextEditingController username = TextEditingController();
  final TextEditingController street = TextEditingController();
  final TextEditingController number = TextEditingController();
  final TextEditingController city = TextEditingController();
  final TextEditingController postalCode = TextEditingController();
  final TextEditingController email = TextEditingController();
  final TextEditingController password = TextEditingController();
  final TextEditingController passwordRepeat = TextEditingController();
  final TextEditingController country = TextEditingController();

  String _errorMsg;
  String _msg;

  @override
  Widget build(BuildContext context) {
    const radius = 8.0;
    const padding = 4.0;

    /*
    * TODO delete default values after debug
    * */
    firstName.text = 'Vorname';
    lastName.text = 'Nachname';
    username.text = 'test11';
    street.text = 'Teststraße';
    number.text = '11';
    city.text = 'Teststadt';
    postalCode.text = '11111';
    email.text = 'test11@vip.com';
    password.text = '!Test1234';
    passwordRepeat.text = '!Test1234';
    country.text = 'Testland';

    return Scaffold(
        appBar: AppBar(
        title: Text('Anmelden'),
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
                padding: const EdgeInsets.all(12),
                children: [
                  Column(
                    children: [
                      Text(
                        'Persönliche Daten',
                        style: TextStyle(fontSize: 18),
                      ),
                      TextFormField(
                        decoration: InputDecoration(
                          icon: const Icon(Icons.email_outlined),
                          labelText: "E-Mail",
                        ),
                        controller: email,
                      ),
                      TextFormField(
                        decoration: InputDecoration(
                          icon: const Icon(Icons.person),
                          labelText: "Nutzername",
                        ),
                        controller: username,
                      ),
                      Row(
                        children: [
                          Flexible(
                            flex: 10,
                            child: TextFormField(
                              decoration: const InputDecoration(
                                icon: const Icon(Icons.person),
                                labelText: "Vorname",
                              ),
                              controller: firstName,
                              validator: (value) => value?.isEmpty ?? true
                                  ? "Bitte Vornamen eingeben"
                                  : null,
                            ),
                          ),
                          Expanded(
                            flex: 1,
                            child: Container(),
                          ),
                          Flexible(
                            flex: 10,
                            child: TextFormField(
                              decoration: const InputDecoration(
                                labelText: "Nachname",
                              ),
                              controller: lastName,
                              validator: (value) => value?.isEmpty ?? true
                                  ? "Bitte Nachnamen eingeben"
                                  : null,
                            ),
                          )
                        ],
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
                      TextFormField(
                        decoration: const InputDecoration(
                          icon: const Icon(Icons.vpn_key_rounded),
                          labelText: "Passwort wiederholen",
                        ),
                        controller: passwordRepeat,
                        validator: (value) => value?.isEmpty ?? true
                            ? "Bitte wiederholen Sie die Passwort eingabe"
                            : null,
                      ),
                    ],
                  ),
                  Padding(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 0.0,
                      vertical: 20,
                    ),
                    child: Column(
                      children: [
                        Text(
                          'Lieferadresse',
                          style: TextStyle(fontSize: 18),
                        ),
                        Row(
                          children: [
                            Flexible(
                              flex: 24,
                              child: TextFormField(
                                decoration: const InputDecoration(
                                  icon: const Icon(Icons.streetview),
                                  labelText: "Straße",
                                ),
                                controller: street,
                              ),
                            ),
                            Expanded(
                              flex: 1,
                              child: Container(),
                            ),
                            Flexible(
                              flex: 4,
                              child: TextFormField(
                                decoration: InputDecoration(
                                  labelText: "Nr.",
                                ),
                                controller: number,
                                keyboardType: TextInputType.number,
                              ),
                            ),
                          ],
                        ),
                        Row(
                          children: [
                            Flexible(
                              flex: 6,
                              child: TextFormField(
                                decoration: const InputDecoration(
                                    icon: const Icon(Icons.location_city),
                                    labelText: "Postleitzahl"),
                                controller: postalCode,
                                keyboardType: TextInputType.number,
                              ),
                            ),
                            Expanded(
                                child: SizedBox(
                              width: 6,
                            )),
                            Flexible(
                              flex: 10,
                              child: TextFormField(
                                decoration: const InputDecoration(
                                  labelText: "Stadt",
                                ),
                                controller: city,
                              ),
                            )
                          ],
                        ),
                        TextFormField(
                          decoration: const InputDecoration(
                            icon: const Icon(Icons.location_city),
                            labelText: "Land",
                          ),
                          controller: country,
                        ),
                        SizedBox(height: 16),
                      ],
                    ),
                  ),
                  _msg == null
                      ? Container()
                      : Text(
                          _msg,
                          style: TextStyle(
                            color: Colors.green,
                          ),
                        ),
                  _errorMsg == null
                      ? Container()
                      : Text(
                          _errorMsg,
                          style: TextStyle(
                            color: Colors.redAccent,
                          ),
                        ),
                  RaisedButton(
                    child: Text("absenden"),
                    onPressed: () async {
                      setState(() {
                        _msg = null;
                        _errorMsg = null;
                      });

                      print('Benutzer anlegen');

                      if (_formKey.currentState.validate()) {
                        try {
                          await SignUpService.instance()
                              .signUpUser(
                            new User(
                                email: email.text,
                                username: username.text,
                                firstName: firstName.text,
                                lastName: lastName.text,
                                password: password.text,
                                street: street.text,
                                number: number.text,
                                postalCode: postalCode.text,
                                city: city.text,
                                country: country.text),
                          ).then((loginRepsonse) {
                            setState(() {
                              _msg = 'Willkommen ${loginRepsonse.user.firstName}';
                            });
                          });
                        } on HttpException catch (error) {
                          setState(() {
                            _errorMsg = error.message;
                            print('Error message on signup user: ${error.message}');
                          });
                        } catch (error) {
                          setState(() {
                            _errorMsg = error.toString();
                          });
                          print('Unbekannter Fehler $error');
                        }
                        //Navigator.pop(context);
                      }
                    },
                  ),
                ],
              ),
            ),
          ),
        ),

    ),);
  }
}
