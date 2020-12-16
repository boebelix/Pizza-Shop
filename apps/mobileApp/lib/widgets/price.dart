import 'package:flutter/material.dart';

class Price extends StatelessWidget {
  final double price;
  final double fontSize;

  const Price({Key key, this.price, this.fontSize}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Text(
      "${price.toStringAsFixed(2)} \€",
      style: TextStyle(fontSize: fontSize, fontWeight: FontWeight.bold),
    );
  }
}
