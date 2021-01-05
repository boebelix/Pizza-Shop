import 'package:app/screens/create_user_screen.dart';
import 'package:app/screens/login_user_screen.dart';
import 'package:app/screens/user_screen.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'models/meals.dart';
import 'screens/meal_creation_screen.dart';
import 'screens/meal_detail_screen.dart';
import 'widgets/nav_bottom.dart';

void main() => runApp(
      ChangeNotifierProvider(
        create: (_) => Meals(),
        child: App(),
      ),
    );

class App extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Non Stop Pizza',
      theme: ThemeData(
        primarySwatch: Colors.amber,
        accentColor: Colors.amber,
        // errorColor: Colors.red,
        fontFamily: 'Quicksand',
        textTheme: ThemeData.light().textTheme.copyWith(
              title: TextStyle(
                fontFamily: 'OpenSans',
                fontWeight: FontWeight.bold,
                fontSize: 18,
              ),
              button: TextStyle(color: Colors.white),
            ),

        appBarTheme: AppBarTheme(
          textTheme: ThemeData.light().textTheme.copyWith(
                title: TextStyle(
                  fontFamily: 'OpenSans',
                  fontSize: 20,
                  fontWeight: FontWeight.bold,
                ),
              ),
        ),
      ),

      initialRoute: NavBottom.routeName,
      routes: {
        NavBottom.routeName: (_) => NavBottom(),
        MealCreationScreen.routeName: (_) => MealCreationScreen(),
        UserScreen.routeName: (_) => UserScreen(),
        LoginUser.routeName: (_) => LoginUser(),
        CreateUserScreen.routeName: (_) => CreateUserScreen(),
      },
    );
  }
}
