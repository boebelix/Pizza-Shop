import 'dart:convert';
import 'dart:io';

import 'package:app/endpoints/properties.dart';
import 'package:app/models/http_exception.dart';
import 'package:app/models/user.dart';
import 'package:http/http.dart' as http;

// Singelton
class SignUpEndpoint {
  static SignUpEndpoint _instance;

  factory SignUpEndpoint.instance() {
    _instance ??= SignUpEndpoint._private();
    return _instance;
  }

  SignUpEndpoint._private();

  Future<User> signUpUser(User user) async {
    Map<String, dynamic> responseData;

    final response = await http.post(
      Uri.http(Properties.USER_SERVICE_URL, "/user"),
      body: jsonEncode(user.toJson()),
      headers: {
        HttpHeaders.contentTypeHeader: ContentType.json.value,
      },
    );

    responseData = jsonDecode(response.body);
    print('SignUp Endpoint responseData ' + responseData.toString());
    print('Status Code 200: ' + response.statusCode.toString());

    if (response.statusCode == HttpStatus.ok) {
      return User.fromJson(responseData);
    } else {
      throw HttpException(responseData['message']);
    }
  }

/*
  * TODO update user fehlt noch
  * */
}
