import 'package:app/models/user.dart';
import 'package:app/models/users.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';


class CreateUserScreen extends StatelessWidget {
  final GlobalKey<FormState> _formKey = GlobalKey();
  final TextEditingController name = TextEditingController();
  final TextEditingController username = TextEditingController();
  final TextEditingController street = TextEditingController();
  final TextEditingController suite = TextEditingController();
  final TextEditingController city = TextEditingController();
  final TextEditingController zip = TextEditingController();
  final TextEditingController email = TextEditingController();
  final TextEditingController phone = TextEditingController();
  final TextEditingController website = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Create a new User"),
      ),
      body: Form(
        key: _formKey,
        child: ListView(
          padding: const EdgeInsets.all(8),
          children: [
            TextFormField(
              decoration: InputDecoration(labelText: "Full name"),
              controller: name,
              validator: (value) =>
                  value?.isEmpty ?? true ? "Enter a valid name" : null,
            ),
            TextFormField(
              decoration: InputDecoration(labelText: "Username"),
              controller: username,
            ),
            TextFormField(
              decoration: InputDecoration(labelText: "Street"),
              controller: street,
            ),
            TextFormField(
              decoration: InputDecoration(labelText: "Suite"),
              controller: suite,
            ),
            TextFormField(
              decoration: InputDecoration(labelText: "City"),
              controller: city,
            ),
            TextFormField(
              decoration: InputDecoration(labelText: "Zip"),
              controller: zip,
            ),
            TextFormField(
              decoration: InputDecoration(labelText: "Email"),
              controller: email,
            ),
            TextFormField(
              decoration: InputDecoration(labelText: "Phone"),
              controller: phone,
            ),
            TextFormField(
              decoration: InputDecoration(labelText: "Website"),
              controller: website,
            ),
            SizedBox(height: 16),
            RaisedButton(
              child: Text("Submit"),
              onPressed: () {
                if (_formKey.currentState.validate()) {
                  context.read<Users>().createUser(
                        User(
                          name: name.text,
                          username: username.text,
                          address: Address(
                            city: city.text,
                            street: street.text,
                            suite: suite.text,
                            zipcode: zip.text,
                          ),
                          email: email.text,
                          phone: phone.text,
                          website: website.text,
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
