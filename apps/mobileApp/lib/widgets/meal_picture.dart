import 'package:flutter/material.dart';

class MealPicture extends StatelessWidget {
  final String imagePath;

  const MealPicture({Key key, this.imagePath}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Image.network(
        imagePath ?? "",
        fit: BoxFit.cover,
        errorBuilder: (context, error, stackTrace) => Container(
          color: Colors.grey,
          child: Center(
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                Icon(Icons.fastfood),
                Text("no picture"),
              ],
            ),
          ),
        ),
      ),
    );
  }
}