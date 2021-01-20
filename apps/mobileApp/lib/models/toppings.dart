import 'dart:collection';

import 'package:app/models/topping.dart';
import 'package:flutter/foundation.dart';

class Toppings extends ChangeNotifier {
  List<Topping> _toppings = [];

  UnmodifiableListView<Topping> get toppings => UnmodifiableListView(_toppings);

  void add(Topping topping) {
    _toppings.add(topping);
    notifyListeners();
  }

  void refresh(List<Topping> toppings) {
    _toppings.clear();
    _toppings.addAll(toppings);
    notifyListeners();
  }


  List toJson(){
    List jsonList = List();
    _toppings.map((item)=>
        jsonList.add(item.toJson())
    ).toList();
    return jsonList;
  }

}
