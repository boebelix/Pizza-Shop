import 'dart:convert';
import 'dart:io';

import 'package:app/models/login_data.dart';
import 'package:app/models/login_response.dart';
import 'package:app/models/user.dart';
import 'package:http/http.dart' as http;


class UserService {
  static UserService _instance;

  /*
  * localhost:9080 doesn't work here if you are use android emulator avd
  * you have to change the ip to 10.0.2.2:9080
  * https://stackoverflow.com/a/55786011/14755959
  * */

  static const String _url = "10.0.2.2:9080";

  factory UserService.instance() {
    _instance ??= UserService._private();
    return _instance;
  }

  UserService._private();


  Future<User> createUser(User user) {
    // TODO delete debug Ausgbabe
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

  Future<LoginResponse> loginUser(LoginData loginData) {
    // TODO delete debug Ausgbabe
    print('sign in user');
    print(loginData.toJson());

    return http.post(
      Uri.http(_url, "/auth"),
      body: jsonEncode(loginData.toJson()),
      headers: {
        HttpHeaders.contentTypeHeader: ContentType.json.value,
      },
    ).then((response) {

      switch(response.statusCode) {

        case HttpStatus.ok:{
          Map<String, dynamic> json = jsonDecode(response.body);
          return LoginResponse.fromJson(json);
        } break;

        case HttpStatus.badRequest:{
          throw Exception("Validation exception");
        } break;

        case HttpStatus.unauthorized:{
          throw Exception("Unauthorized exception (username/password invalid)");
        } break;

        case HttpStatus.internalServerError:{
          throw Exception("Userservice exception");
        } break;

        default: {
          throw Exception("Exception: " + response.statusCode.toString());
        }
      }
    });
  }

  // TODO Noch nicht implemntiert
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
