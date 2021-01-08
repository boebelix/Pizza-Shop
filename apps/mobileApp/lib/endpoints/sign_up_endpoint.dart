import 'dart:convert';
import 'dart:io';

import 'package:app/models/http_exception.dart' as tm;
import 'package:app/endpoints/properties.dart';
import 'package:app/models/user.dart';
import 'package:http/http.dart' as http;

// Singelton
class SignUpEndpoint {
  static SignUpEndpoint _instance;
  static User _user;

  factory SignUpEndpoint.instance() {
    _instance ??= SignUpEndpoint._private();
    return _instance;
  }

  SignUpEndpoint._private();


  Future<User> signUpUser(User user) async {
    // TODO delete debug Ausgbabe
    print('SignUp: Create user from ' + user.toString());
    Map<String, dynamic> responseData;

    try {
      final response = await http.post(
        Uri.http(Properties.url, "/user"),
        body: jsonEncode(user.toJson()),
        headers: {
          HttpHeaders.contentTypeHeader: ContentType.json.value,
        },
      );

      responseData = jsonDecode(response.body);
      print('SignUp Endpoint responseData ' + responseData.toString());
      print('Status Code 200: ' + response.statusCode.toString());

      if (response.statusCode == 200) {
        print('Status Code 200: ' + response.statusCode.toString());
        return User.fromJson(responseData);
      }else{
        print('Status Code: ' + response.statusCode.toString());
        print('Response Code Error: ' + responseData.toString());
        throw Exception(responseData['message']);
      }

    }catch (error){
      throw error;
    }
  }

/*
  * TODO update user fehlt noch
  * */
}
