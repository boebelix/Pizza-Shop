import 'dart:convert';
import 'dart:io';

import 'package:app/models/user.dart';
import 'package:http/http.dart' as http;


class UserService {
  static UserService _instance;
  final String url = "localhost:9080";

  factory UserService.instance() {
    _instance ??= UserService._private();
    return _instance;
  }

  UserService._private();

  Future<List<User>> getAllUsers() {
    return http
        .get(Uri.https(url, "/users"))
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
    return http.post(
      Uri.https(url, "/user"),
      body: jsonEncode(user.toJson()),
      headers: {
        HttpHeaders.contentTypeHeader: ContentType.json.value,
      },
    ).then((response) {
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
