import 'package:app/endpoints/user_endpoint.dart';
import 'package:app/models/user.dart';
import 'package:flutter/material.dart';

class CreateUserScreen extends StatelessWidget {
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

  // TODO Validator übernehmen aus Backend oder aus WebApp

  @override
  Widget build(BuildContext context) {
    return Container(
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

            RaisedButton(
              child: Text("Registrierung absenden"),
              onPressed: () {
                if (_formKey.currentState.validate()) {
                  UserService.instance().createUser(
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
                  );
                  //Navigator.pop(context);
                }
              },
            ),
          ],
        ),
      ),
    );
  }
}
