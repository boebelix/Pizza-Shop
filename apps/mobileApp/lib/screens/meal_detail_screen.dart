import 'package:flutter/material.dart';
import 'package:woche3_app/models/meal.dart';
import 'package:woche3_app/widgets/meal_picture.dart';
import 'package:woche3_app/widgets/rating.dart';

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
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          Flexible(
            flex: 2,
            fit: FlexFit.tight,
            child: MealPicture(imagePath: meal.imagePath),
          ),
          Flexible(flex: 3, child: _MealInfo(meal: meal)),
        ],
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
          Rating(
            size: 20,
            numberOfRatings: meal.numberOfRatings,
            rating: meal.rating,
          ),
          SizedBox(height: 16),
          Text(meal.description),
          Spacer(),
          Text(
            "\$${meal.price.toStringAsFixed(2)}",
            textAlign: TextAlign.right,
            style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold),
          ),
          SizedBox(height: 8),
          RaisedButton(
            child: Text("Add to cart"),
            onPressed: () => print("added ${meal.name} to cart"),
          ),
        ],
      ),
    );
  }
}
