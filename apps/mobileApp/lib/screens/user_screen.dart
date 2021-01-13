import 'package:app/screens/update_user.dart';
import 'package:app/services/security/auth.dart';
import 'package:app/widgets/user_button.dart';
import 'package:flutter/material.dart';

import 'create_user.dart';
import 'login_user.dart';

class UserScreen extends StatefulWidget {
  static const String routeName = "/userScreen";

  @override
  _UserScreenState createState() => _UserScreenState();
}

class _UserScreenState extends State<UserScreen> {
  bool isLoggedIn;

  @override
  Widget build(BuildContext context) {
    setState(() {
      isLoggedIn = AuthService.instance().isSignIn;
    });
    return isLoggedIn
        ? UpdateUser()
        : Container(
            decoration: new BoxDecoration(
              image: new DecorationImage(
                image: new AssetImage("images/Background_Login.jpg"),
                fit: BoxFit.cover,
              ),
            ),
            child: Padding(
              padding: const EdgeInsets.all(8.0),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.start,
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: [
                  UserButton(
                      headline: "Login", destinationRoute: LoginUser.routeName),
                  UserButton(
                      headline: "Noch kein Kunde?",
                      destinationRoute: CreateUser.routeName)
                ],
              ),
            ),
          );
  }
}
