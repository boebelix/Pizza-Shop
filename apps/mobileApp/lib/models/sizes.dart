import 'dart:collection';
import 'package:app/models/size.dart';
import 'package:flutter/foundation.dart';


class Sizes extends ChangeNotifier {

  List<Size> _sizes = [];

  UnmodifiableListView<Size> get sizes => UnmodifiableListView(_sizes);

  void add(Size size) {
    _sizes.add(size);
    notifyListeners();
  }

  void addAll(List<Size> sizes) {
    _sizes.addAll(sizes);
    notifyListeners();
  }

  void deleteAll(){
    _sizes.clear();
    notifyListeners();
  }
}
