import 'package:app/models/user.dart';


class AuthService{
  static AuthService _instance;

  factory AuthService.instance() {
    _instance ??= AuthService._private();
    return _instance;
  }

  AuthService._private();


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

  bool logoutUser(){
    _user = null;
    _uuid = null;
    return true;
  }
}
