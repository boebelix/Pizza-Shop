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


  Future<LoginResponse> signInUser(LoginData loginData) async {
    // TODO delete debug Ausgbabe
    print('sign in user');
    print(loginData.toJson());

    return await http.post(
      Uri.http(Properties.url_user, "/auth"),
      body: jsonEncode(loginData.toJson()),
      headers: {
        HttpHeaders.contentTypeHeader: ContentType.json.value,
      },
    ).then((response) {
      Map<String, dynamic> responseData = jsonDecode(response.body);
      if (response.statusCode == HttpStatus.ok) {
        final loginResponse = LoginResponse.fromJson(responseData);
        print("LoginResponse " + loginResponse.toString());

        return LoginResponse.fromJson(responseData);
      }else{
        throw HttpException(responseData['message']);
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
