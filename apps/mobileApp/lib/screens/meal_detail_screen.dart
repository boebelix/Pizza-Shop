import 'package:app/models/cart.dart';
import 'package:provider/provider.dart';
import 'package:app/models/meal.dart';
import 'package:flutter/material.dart';


class MealDetailScreen extends StatelessWidget {
  static const String routeName = "/mealDetails";

  @override
  Widget build(BuildContext context) {
    final meal = ModalRoute.of(context).settings.arguments as Meal;

    return Scaffold(
      appBar: AppBar(
        title: Text(meal.name),
        backgroundColor: Colors.black.withOpacity(0.5),
        elevation: 0,
      ),
      extendBodyBehindAppBar: true,
      body: SafeArea(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Flexible(child: _MealInfo(meal: meal)),
          ],
        ),
      ),
    );
  }
}

class _MealInfo extends StatelessWidget {
  final Meal meal;

  const _MealInfo({Key key, this.meal}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          SizedBox(height: 16),
          Text(meal.description),
          Spacer(),
          Text(
            "${meal.price.toStringAsFixed(2)} \€",
            textAlign: TextAlign.right,
            style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold),
          ),
          SizedBox(height: 8),
          RaisedButton(
            child: Text("Zum Warenkorb hinzufügen"),
            onPressed: () {
              print("${meal.name} zum Warenkorb hinzugefügt");
              context.read<Cart>().add(meal);

            }
          ),
        ],
      ),
    );
  }
}
