import 'dart:convert';
import 'dart:io';

import 'package:app/endpoints/properties.dart';
import 'package:app/models/login_data.dart';
import 'package:app/models/login_response.dart';
import 'package:app/models/user.dart';
import 'package:http/http.dart' as http;

// Singleton
class SignInEndpoint {
  static SignInEndpoint _instance;

  factory SignInEndpoint.instance() {
    _instance ??= SignInEndpoint._private();
    return _instance;
  }

  SignInEndpoint._private();


  Future<LoginResponse> signInUser(LoginData loginData) {
    // TODO delete debug Ausgbabe
    print('sign in user');
    print(loginData.toJson());

    return http.post(
      Uri.http(Properties.url, "/auth"),
      body: jsonEncode(loginData.toJson()),
      headers: {
        HttpHeaders.contentTypeHeader: ContentType.json.value,
      },
    ).then((response) {

      switch(response.statusCode) {

        case HttpStatus.ok:{
          Map<String, dynamic> json = jsonDecode(response.body);
          LoginResponse loginResponse = LoginResponse.fromJson(json);
          print("LoginResponse" + loginResponse.toString());

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

  /*
  * Sign out user
  * */

  Future<void> signOutUser(int id) {
    return http
        .delete(Uri.http("_url", "/$id"))
        .then((response) {
      if (response.statusCode == HttpStatus.created) {
        return;
      } else {
        throw Exception("Failed to sign out user");
      }
    });
  }
}
