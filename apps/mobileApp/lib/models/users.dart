import 'package:app/models/user.dart';
import 'file:///C:/Develop/TP/apps/mobileApp/lib/endpoints/user_endpoint.dart';
import 'package:flutter/foundation.dart';



class Users extends ChangeNotifier {
  List<User> users;

  Users() {
    fetch();
  }

  void fetch() {
    UserService.instance().getAllUsers().then((value) {
      users = value;
      notifyListeners();
    });
  }

  void createUser(User user) {
    UserService.instance().createUser(user).then((value){
      print("Created User:");
      print(value);
    });
  }
}
