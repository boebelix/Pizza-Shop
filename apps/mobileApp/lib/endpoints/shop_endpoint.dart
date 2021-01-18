import 'dart:convert';
import 'dart:io';

import 'package:app/endpoints/properties.dart';
import 'package:app/models/login_data.dart';
import 'package:app/models/login_response.dart';
import 'package:app/models/user.dart';
import 'package:http/http.dart' as http;

// Singleton
class ShopEndpoint {
  static ShopEndpoint _instance;

  factory ShopEndpoint.instance() {
    _instance ??= ShopEndpoint._private();
    return _instance;
  }

  ShopEndpoint._private();


  Future<LoginResponse> getToppings() async {
    // TODO delete debug Ausgbabe
    print('sign in user');


    return await http.get(
      Uri.http(Properties.url_shop, "/topping"),
      headers: {
        HttpHeaders.contentTypeHeader: ContentType.json.value,
      },
    ).then((response) {
      Map<String, dynamic> responseData = jsonDecode(response.body);
      if (response.statusCode == HttpStatus.ok) {
        final loginResponse = LoginResponse.fromJson(responseData);

        print("LoginResponse get topping " + loginResponse.toString());

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
