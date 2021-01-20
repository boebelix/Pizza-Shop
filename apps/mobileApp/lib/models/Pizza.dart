import 'dart:collection';

import 'package:app/models/size.dart';
import 'package:app/models/topping.dart';
import 'package:app/models/toppings.dart';

class Pizza {
  Size _size;
  Toppings _toppings = new Toppings();

  Pizza(this._size);

  UnmodifiableListView<Topping> get toppings => _toppings.toppings;

  Size get size => _size;

  void set size(Size size) {
    _size = size;
  }

  Pizza addTopping(Topping topping) {
    _toppings.add(topping);
    return this;
  }

  double get price {
    return size.basePrice + toppings.length * size.toppingPrice;
  }

  Map<String, dynamic> toJson() => {
    "sizeId": _size.id,
    "toppings":   _toppings.toJson()
  };

}
