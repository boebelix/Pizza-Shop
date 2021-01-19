import 'package:app/screens/create_pizza.dart';
import 'package:flutter/material.dart';

class CreatePizzaButton extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    const double radius = 8;
    const double itemHeight = 180;

    return Container(
      padding: EdgeInsets.all(5),
      height: itemHeight,
      child: ClipRRect(
        borderRadius: BorderRadius.circular(radius),
        child: GestureDetector(
          onTap: () {
            Navigator.pushNamed(context, CreatePizzaScreen.routeName);
          },
          child: Card(
            child: Row(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                Flexible(
                  child: _Info(),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

class _Info extends StatelessWidget {

  const _Info({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(12.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            'Erstelle deine eigene Pizza',
            style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
          ),
          Text(
            '',
            maxLines: 2,
            overflow: TextOverflow.ellipsis,
            style: TextStyle(fontSize: 14, color: Colors.black54),
          ),
        ],
      ),
    );
  }
}
