import 'dart:collection';
import 'package:flutter/foundation.dart';
import 'meal.dart';


class Sizes extends ChangeNotifier {

  List<Meal> _toppings;

  UnmodifiableListView<Meal> get meals => UnmodifiableListView(_toppings);

  void add(Meal meal) {
    _toppings.add(meal);
    notifyListeners();
  }
}
