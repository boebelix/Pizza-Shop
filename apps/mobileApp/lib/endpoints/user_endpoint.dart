import 'dart:convert';
import 'dart:io';

import 'package:app/models/user.dart';
import 'package:http/http.dart' as http;


class UserService {
  static UserService _instance;

  /*
  * localhost:9080 doesn't work her if you are use android emulator adv
  * you have to change the ip to 10.0.2.2
  * https://stackoverflow.com/a/55786011/14755959
  * */

  static const String _url = "10.0.2.2:9080";

  factory UserService.instance() {
    _instance ??= UserService._private();
    return _instance;
  }

  UserService._private();

  Future<List<User>> getAllUsers() {
    return http
        .get(Uri.http(_url, "/user"))
        .then((response) {
      if (response.statusCode == HttpStatus.ok) {
        List<dynamic> usersJson = jsonDecode(response.body);
        return usersJson.map((json) => User.fromJson(json)).toList();
      } else {
        throw Exception("Failed to load users");
      }
    });
  }

  Future<User> createUser(User user) {
    print('create user');
    print(user.toJson());
    return http.post(
      Uri.http(_url, "/user"),
      body: jsonEncode(user.toJson()),
      headers: {
        HttpHeaders.contentTypeHeader: ContentType.json.value,
      },
    ).then((response) {
      print(response);
      if (response.statusCode == HttpStatus.created) {
        Map<String, dynamic> json = jsonDecode(response.body);
        return User.fromJson(json);
      } else {
        throw Exception("Failed to create user");
      }
    });
  }

  Future<void> deleteUser(int id) {
    return http
        .delete(Uri.https("jsonplaceholder.typicode.com", "/users/$id"))
        .then((response) {
      if (response.statusCode == HttpStatus.created) {
        return;
      } else {
        throw Exception("Failed to delete user");
      }
    });
  }
}
