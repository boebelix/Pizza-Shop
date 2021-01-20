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

  void refresh(List<Size> sizes) {
    _sizes.clear();
    _sizes.addAll(sizes);
    notifyListeners();
  }
}
