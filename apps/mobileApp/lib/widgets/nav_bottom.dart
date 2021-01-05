import 'package:app/screens/meal_list_screen.dart';
import 'package:flutter/material.dart';

import '../screens/create_user_screen.dart';
import '../screens/meal_detail_screen.dart';
import 'package:provider/provider.dart';



/*
* Good documentation is find on
* https://willowtreeapps.com/ideas/how-to-use-flutter-to-build-an-app-with-bottom-navigation
* */

class NavBottom extends StatefulWidget {
  static const String routeName = "/";

  @override
  _NavBottomState createState() => _NavBottomState();
}

class _NavBottomState extends State<NavBottom> {
  int _currentIndex = 0;

  final List<Widget> _children = [
    meal_list(),
    meal_list(),
    CreateUserScreen(),
  ];

  void onTabTapped(int index) {
    setState(() {
      _currentIndex = index;
    });
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Non Stop Pizza'),
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
              title: Text('Profile')
          )
        ],
      ),
    );
  }
}
