import 'package:app/screens/update_user.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'models/meals.dart';
import 'screens/create_user.dart';
import 'screens/login_user.dart';
import 'screens/meal_detail_screen.dart';
import 'widgets/navigation.dart';

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

      initialRoute: Navigation.routeName,

      routes: {
        Navigation.routeName: (_) => Navigation(),
        LoginUser.routeName: (_) => LoginUser(),
        CreateUser.routeName: (_) => CreateUser(),
        MealDetailScreen.routeName: (_) => MealDetailScreen(),
        UpdateUser.routeName: (_) => UpdateUser()
      },
    );
  }
}
