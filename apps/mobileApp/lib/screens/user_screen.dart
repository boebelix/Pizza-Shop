import 'package:app/screens/create_user_screen.dart';
import 'package:app/screens/login_user_screen.dart';
import 'package:app/widgets/user_button.dart';
import 'package:flutter/material.dart';

class UserScreen extends StatelessWidget {
  static const String routeName = "/userScreen";



  @override
  Widget build(BuildContext context) {
    return Container(
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
            UserButton(headline: "Login",destinationRoute: LoginUser.routeName),
            UserButton(headline: "Noch kein Kunde?", destinationRoute: CreateUserScreen.routeName)
          ],
        ),
      ),
    );
  }
}
