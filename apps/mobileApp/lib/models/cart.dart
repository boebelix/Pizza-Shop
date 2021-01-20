import 'dart:collection';

import 'package:app/models/Pizza.dart';
import 'package:app/models/size.dart';
import 'package:app/models/topping.dart';
import 'package:flutter/foundation.dart';

class Cart extends ChangeNotifier {
  /*
  * Bilder von
  * https://www.essen-und-trinken.de/pizza/73081-cstr-die-besten-pizza-rezepte
  * */
  List<Pizza> _pizzas = [
    Pizza(Size(
            id: 1,
            basePrice: 30,
            diameter: 10,
            doughAmount: 200,
            toppingFactor: 1.5,
            toppingPrice: 2.5))
        .addTopping(Topping(baseAmount: 10, id: 1, name: "Bugs", unit: "g"))
  ];

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
