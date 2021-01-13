import 'package:flutter/material.dart';

class UserButton extends StatelessWidget {
  final String destinationRoute;
  final String headline;

  static const double itemHeight = 120;
  static const double radius = 8;

  UserButton({this.headline, @required this.destinationRoute});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        Navigator.of(context).pushNamed(destinationRoute);
      },
      child: Card(
        elevation: 8,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(radius),
        ),
        child: ClipRRect(
          child: Container(
            height: itemHeight,
            child: Center(
              child: Text(
                headline,
                style: TextStyle(fontSize: 24),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
