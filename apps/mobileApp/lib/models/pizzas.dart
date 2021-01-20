import 'dart:collection';

import 'package:app/models/Pizza.dart';
import 'package:flutter/cupertino.dart';

class Pizzas extends ChangeNotifier{

  List<Pizza> pizzas = [];

  Pizzas({this.pizzas});

  void add(Pizza pizza) {
    pizzas.add(pizza);
    notifyListeners();
  }

  void addAll(List<Pizza> pizzas) {
    pizzas.addAll(pizzas);
    notifyListeners();
  }

  List toJson(){
    List jsonList = List();
    pizzas.map((item)=>
        jsonList.add(item.toJson())
    ).toList();
    return jsonList;
  }

  factory Pizzas.fromJson(Map<String, dynamic> json) => Pizzas(
    pizzas: json["pizzas"]
  );
}
