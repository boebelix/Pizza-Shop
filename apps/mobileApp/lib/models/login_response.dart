import 'package:app/models/user.dart';

class LoginResponse{
  final String uuid;
  final User user;

  LoginResponse({this.uuid, this.user});

  factory LoginResponse.fromJson(Map<String, dynamic> json) => LoginResponse(
    uuid: json['token'],
    user: json['user'] != null ? User.fromJson(json['user']) : null,
  );

  Map<String, dynamic> toJson() => {
    "UUID":   uuid,
    "user":   user.toJson(),
  };

  @override
  String toString() {
    return 'LoginResponse{uuid: $uuid, user: $user}';
  }
}



