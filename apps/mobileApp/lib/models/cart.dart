import 'dart:collection';

import 'package:flutter/foundation.dart';

import 'meal.dart';

class Cart extends ChangeNotifier {
  /*
  * Bilder von
  * https://www.essen-und-trinken.de/pizza/73081-cstr-die-besten-pizza-rezepte
  * */
  List<Meal> _meals = [
    Meal(
      imagePath:
          "https://image.essen-und-trinken.de/11915996/t/Gf/v8/w960/r1/-/267910--5613-.jpg",
      name: "Erstelle dir deine eigene Pizza",
      description: "Sei dein eigener Koch",
      price: 10.0,
      rating: 5.0,
      numberOfRatings: 0,
    ),
  ];

  UnmodifiableListView<Meal> get meals => UnmodifiableListView(_meals);

  void add(Meal meal) {
    _meals.add(meal);
    notifyListeners();
  }
}
