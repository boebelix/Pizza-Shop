import 'dart:convert';
import 'dart:io';

import 'package:app/endpoints/properties.dart';
import 'package:app/models/Pizza.dart';
import 'package:app/models/exception_response.dart';
import 'package:app/models/login_data.dart';
import 'package:app/models/login_response.dart';
import 'package:app/models/order.dart';
import 'package:app/models/size.dart';
import 'package:app/models/topping.dart';
import 'package:app/models/user.dart';
import 'package:app/services/security/auth.dart';
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
      Uri.http(Properties.SHOP_URL, "/topping"),
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
      Uri.http(Properties.SHOP_URL, "/size"),
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




  Future<Order> sendOrder(Order order) async {
    // TODO delete debug Ausgbabe
    print(order.toJson());
  print(ContentType.json.value);
  print(AuthService.instance().uuid);

    Map<String, dynamic> responseData;

    final response = await http.post(
      Uri.http(Properties.SHOP_URL, "/shop"),
      body: jsonEncode(order.toJson()),
      headers: {
        HttpHeaders.contentTypeHeader: ContentType.json.value,
        "Authorization": AuthService.instance().uuid
      },

    );

    print(response);
    print(response.body);
    print(response.headers);
    print(response.statusCode);

    responseData = jsonDecode(response.body);
    print('SignUp Endpoint responseData ' + responseData.toString());
    print('Status Code 200: ' + response.statusCode.toString());

    if (response.statusCode == 201) {
      print('Status Code 200: ' + response.statusCode.toString());
      return Order.fromJson(responseData);
    } else {
      print('Status Code: ' + response.statusCode.toString());
      print('Response Code Error: ' + responseData.toString());
      throw HttpException(responseData['message']);
    }
  }
}
