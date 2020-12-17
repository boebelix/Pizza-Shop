import 'package:app/endpoints/user_endpoint.dart';
import 'package:app/models/user.dart';
import 'package:app/models/users.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

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
          padding: const EdgeInsets.all(8),
          children: [
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
            TextFormField(
              decoration: const InputDecoration(
                icon: const Icon(Icons.person),
                labelText: "Vorname",
              ),
              controller: firstName,
              validator: (value) =>
                  value?.isEmpty ?? true ? "Bitte Vornamen eingeben" : null,
            ),
            TextFormField(
              decoration: const InputDecoration(
                icon: const Icon(Icons.person),
                labelText: "Nachname",
              ),
              controller: lastName,
              validator: (value) =>
                  value?.isEmpty ?? true ? "Bitte Nachnamen eingeben" : null,
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
            TextFormField(
              decoration: const InputDecoration(
                icon: const Icon(Icons.streetview),
                labelText: "Straße",
              ),
              controller: street,
            ),
            TextFormField(
              decoration: InputDecoration(
                icon: const Icon(Icons.streetview),
                labelText: "Hausnummer",
              ),
              controller: number,
              keyboardType: TextInputType.number,
            ),
            TextFormField(
              decoration: const InputDecoration(
                  icon: const Icon(Icons.location_city),
                  labelText: "Postleitzahl"),
              controller: postalCode,
              keyboardType: TextInputType.number,
            ),
            TextFormField(
              decoration: const InputDecoration(
                icon: const Icon(Icons.location_city),
                labelText: "Stadt",
              ),
              controller: city,
            ),
            TextFormField(
              decoration: const InputDecoration(
                icon: const Icon(Icons.location_city),
                labelText: "Land",
              ),
              controller: country,
            ),
            SizedBox(height: 16),
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
                        country: country.text
                      ),
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
