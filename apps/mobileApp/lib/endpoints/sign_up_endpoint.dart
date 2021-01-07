import 'dart:convert';
import 'dart:io';

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
    print('create user from ' + user.toString());

    try{
      return http.post(
        Uri.http(Properties.url, "/user"),
        body: jsonEncode(user.toJson()),
        headers: {
          HttpHeaders.contentTypeHeader: ContentType.json.value,
        },
      ).then((response) {
        if (response.statusCode == HttpStatus.ok) {
          Map<String, dynamic> json = jsonDecode(response.body);

          return User.fromJson(json);
        } else{
          print('Endpoint Http Exception');
          throw HttpException(jsonDecode(response.body)['message'].toString());
        }
      });
    } catch (error){
      throw Exception(error);
    }

  }

/*
  * TODO update user fehlt noch
  * */
}
