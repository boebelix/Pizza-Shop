import 'package:app/screens/meal_list_screen.dart';
import 'package:app/screens/user_screen.dart';
import 'package:app/services/security/auth.dart';
import 'package:flutter/material.dart';

/*
* Good documentation is find on
* https://willowtreeapps.com/ideas/how-to-use-flutter-to-build-an-app-with-bottom-navigation
* */

class Navigation extends StatefulWidget {
  static const String routeName = "/";

  @override
  _NavigationState createState() => _NavigationState();
}

class _NavigationState extends State<Navigation> {
  AuthService authService = AuthService.instance();

  int _currentIndex = 0;
  bool isLoggedIn;

  final List<Widget> _children = [
    meal_list(),
    meal_list(),
    UserScreen(),
  ];

  void onTabTapped(int index) {
    setState(() {
      _currentIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {

    setState(() {
      isLoggedIn = authService.isSignIn;
    });

    return Scaffold(
      appBar: AppBar(
        title: isLoggedIn ?  Text('Hallo ' + authService.user.firstName + ' bei Non Stop Pizza') : Text('Non Stop Pizza' ),
        centerTitle: true ,
        actions: <Widget>[
          isLoggedIn
              ? _logoutButton(context)
              : Container(), //IconButton
        ],
      ),

      body: _children[_currentIndex],
      bottomNavigationBar: BottomNavigationBar(
        onTap: onTabTapped, // new
        currentIndex: _currentIndex,

        items: [
          BottomNavigationBarItem(
            icon: new Icon(Icons.home),
            title: new Text('Bestellen'),
          ),
          BottomNavigationBarItem(
            icon: new Icon(Icons.shopping_cart),
            title: new Text('Warenkorb'),
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.person),
            title: Text('Profile'),
          )
        ],
      ),
    );
  }

  IconButton _logoutButton(BuildContext context) {
    return IconButton(
                    icon: Icon(Icons.logout), // The "+" icon
                    onPressed: () {
                      print('User before logout ' + authService.user.toString());

                      if (authService.logoutUser()) {
                        Navigator.pop(context);
                        Navigator.pushNamed(context, Navigation.routeName);
                      }

                      print('User after logout ' + authService.user.toString());
                    }, // The `_incrementCounter` function
                  );
  }
}
