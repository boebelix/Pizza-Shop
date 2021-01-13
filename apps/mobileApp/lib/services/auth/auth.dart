import 'package:app/models/user.dart';

class Auth{
  static Auth _instance;

  factory Auth.instance() {
    _instance ??= Auth._private();
    return _instance;
  }

  Auth._private();


  User _user;
  String _uuid;


  User get user => _user;

  set user(User value) {
    _user = value;
  }

  String get uuid => _uuid;

  set uuid(String value) {
    _uuid = value;
  }

  bool get isSignIn{
    return _user != null && _uuid != null;
  } 

  void logoutUser(){
    _user = null;
    _uuid = null;
  }
}
