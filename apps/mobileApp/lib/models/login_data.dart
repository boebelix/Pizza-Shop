import 'package:flutter/cupertino.dart';

class LoginData{
  final String username;
  final String password;

  LoginData({@required this.username, @required this.password});


  factory LoginData.fromJson(Map<String, dynamic> json) => LoginData(
      username: json['username'],
      password: json['password'],
  );

  Map<String, dynamic> toJson() => {
    "username":   username,
    "password":   password,
  };


  @override
  String toString() {
    return 'loginData{username: $username, password: $password}';
  }
}




