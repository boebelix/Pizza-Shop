import 'dart:convert';
import 'dart:io';

import 'package:app/models/user.dart';
import 'package:http/http.dart' as http;


class UserService {
  static UserService _instance;

  /*
  * localhost:9080 doesn't work her if you are use android emulator avd
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

      switch(response.statusCode) {
        case HttpStatus.ok:{
          Map<String, dynamic> json = jsonDecode(response.body);
          print(json);
          return User.fromJson(json);
        } break;

        case HttpStatus.badRequest:{
          throw Exception("Validation exception");
        } break;

        case HttpStatus.conflict:{
          throw Exception("Conflict exception");
        } break;

        case HttpStatus.internalServerError:{
          throw Exception("Internal server error userservice exception");
        } break;

        default: {
          throw Exception("Exception: " + response.statusCode.toString());
        }
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
