import 'dart:collection';

import 'package:app/models/Pizza.dart';
import 'package:flutter/foundation.dart';

class Cart extends ChangeNotifier {
  /*
  * Bilder von
  * https://www.essen-und-trinken.de/pizza/73081-cstr-die-besten-pizza-rezepte
  * */
  List<Pizza> _pizzas = [];

  UnmodifiableListView<Pizza> get pizzas => UnmodifiableListView(_pizzas);

  void add(Pizza pizza) {
    _pizzas.add(pizza);
    notifyListeners();
  }
}
