import 'dart:collection';

import 'package:app/models/Pizza.dart';
import 'package:app/models/size.dart';
import 'package:app/models/topping.dart';
import 'package:flutter/foundation.dart';

class Cart extends ChangeNotifier {

  List<Pizza> _pizzas = [];

  UnmodifiableListView<Pizza> get pizzas => UnmodifiableListView(_pizzas);

  void add(Pizza pizza) {
    _pizzas.add(pizza);
    notifyListeners();
  }

  remove(int index) {
    _pizzas.removeAt(index);
    notifyListeners();
  }

  void removeAll() {
    _pizzas = new List();
    notifyListeners();
  }
}
