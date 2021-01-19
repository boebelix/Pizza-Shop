import 'dart:collection';

import 'package:app/models/size.dart';
import 'package:app/models/topping.dart';

class Pizza {
  Size _size;
  final Set<Topping> _toppings = new Set();

  Pizza(this._size);

  UnmodifiableListView<Topping> get toppings => new UnmodifiableListView(_toppings);

  Size get size => _size;

  void set size(Size size) {
    _size = size;
  }

  bool addTopping(Topping topping) {
    return _toppings.add(topping);
  }

}
