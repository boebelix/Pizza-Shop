import 'package:app/models/user.dart';
import 'package:app/models/users.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';


class CreateUserScreen extends StatelessWidget {
  final GlobalKey<FormState> _formKey = GlobalKey();
  final TextEditingController name = TextEditingController();
  final TextEditingController username = TextEditingController();
  final TextEditingController street = TextEditingController();
  final TextEditingController number = TextEditingController();
  final TextEditingController city = TextEditingController();
  final TextEditingController zip = TextEditingController();
  final TextEditingController email = TextEditingController();
  final TextEditingController phone = TextEditingController();


  @override
  Widget build(BuildContext context) {
    return Container(
      child:  Form(
          key: _formKey,
          child: ListView(
            padding: const EdgeInsets.all(8),
            children: [
              TextFormField(
                decoration: InputDecoration(labelText: "Name"),
                controller: name,
                validator: (value) =>
                    value?.isEmpty ?? true ? "Bitte Name eingeben" : null,
              ),
              TextFormField(
                decoration: InputDecoration(labelText: "Nutzername"),
                controller: username,
              ),
              TextFormField(
                decoration: InputDecoration(labelText: "Straße"),
                controller: street,
              ),
              TextFormField(
                decoration: InputDecoration(labelText: "Hausnummer"),
                controller: number,
                keyboardType: TextInputType.number,
              ),
              TextFormField(
                decoration: InputDecoration(labelText: "Stadt"),
                controller: city,
              ),
              TextFormField(
                decoration: InputDecoration(labelText: "Postleitzahl"),
                controller: zip,
                keyboardType: TextInputType.number,
              ),
              TextFormField(
                decoration: InputDecoration(labelText: "E-Mail"),
                controller: email,
              ),
              TextFormField(
                decoration: InputDecoration(labelText: "Telefonnummer"),
                controller: phone,
                keyboardType: TextInputType.number,
              ),

              SizedBox(height: 16),
              RaisedButton(
                child: Text("Ändern"),
                onPressed: () {
                  if (_formKey.currentState.validate()) {
                    context.read<Users>().createUser(
                          User(
                            name: name.text,
                            username: username.text,
                            address: Address(
                              city: city.text,
                              street: street.text,
                              suite: number.text,
                              zipcode: zip.text,
                            ),
                            email: email.text,
                            phone: phone.text,
                          ),
                        );
                    Navigator.pop(context);
                  }
                },
              ),
            ],
          ),
        ),
    );
  }
}
