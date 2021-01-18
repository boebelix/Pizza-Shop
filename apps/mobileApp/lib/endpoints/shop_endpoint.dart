import 'dart:convert';
import 'dart:io';

import 'package:app/endpoints/properties.dart';
import 'package:app/models/login_data.dart';
import 'package:app/models/login_response.dart';
import 'package:app/models/topping.dart';
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


  Future getToppings() async {
    // TODO delete debug Ausgbabe
    print('SHOP ENDPOINT');


    return await http.get(
      Uri.http(Properties.url_shop, "/topping"),
      headers: {
        HttpHeaders.contentTypeHeader: ContentType.json.value,
      },
    ).then((response) {
      var responseData = jsonDecode(response.body);
      if (response.statusCode == HttpStatus.ok) {


        print("SHOP get topping " + responseData.toString());
        return responseData;
      }else{
        throw HttpException(responseData['message']);
      }
    });
  }

  Future getSizes() async {
    // TODO delete debug Ausgbabe
    print('SHOP ENDPOINT');


    return await http.get(
      Uri.http(Properties.url_shop, "/size"),
      headers: {
        HttpHeaders.contentTypeHeader: ContentType.json.value,
      },
    ).then((response) {
      var responseData = jsonDecode(response.body);
      if (response.statusCode == HttpStatus.ok) {


        print("SHOP get size " + responseData.toString());
        return responseData;
      }else{
        throw HttpException(responseData['message']);
      }
    });
  }
}
