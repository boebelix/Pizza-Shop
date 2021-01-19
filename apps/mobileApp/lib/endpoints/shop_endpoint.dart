import 'dart:convert';
import 'dart:io';

import 'package:app/endpoints/properties.dart';
import 'package:app/models/exception_response.dart';
import 'package:app/models/login_data.dart';
import 'package:app/models/login_response.dart';
import 'package:app/models/size.dart';
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


  Future<List<Topping>> getToppings() async {
    return await http.get(
      Uri.http(Properties.url_shop, "/topping"),
      headers: {
        HttpHeaders.contentTypeHeader: ContentType.json.value,
      },
    ).then((response) {
      List<dynamic> responseList = jsonDecode(response.body);
      List<Topping> toppings = responseList.map((e) => Topping.fromJson(e)).toList();
      if (response.statusCode == HttpStatus.ok) {
        return toppings;
      }else{
        ExceptionResponse exceptionResponse = jsonDecode(response.body);
        throw HttpException(exceptionResponse.message);
      }
    });
  }

  Future<List<Size>> getSizes() async {
    return await http.get(
      Uri.http(Properties.url_shop, "/size"),
      headers: {
        HttpHeaders.contentTypeHeader: ContentType.json.value,
      },
    ).then((response) {
      List<dynamic> responseList = jsonDecode(response.body);
      List<Size> sizes = responseList.map((e) => Size.fromJson(e)).toList();
      if (response.statusCode == HttpStatus.ok) {
        return sizes;
      }else{
        ExceptionResponse exceptionResponse = jsonDecode(response.body);
        throw HttpException(exceptionResponse.message);
      }
    });
  }
}
