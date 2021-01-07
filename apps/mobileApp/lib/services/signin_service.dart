// Singleton
import 'package:app/endpoints/sign_in_endpoint.dart';
import 'package:app/models/login_data.dart';

import 'auth/auth.dart';

class SignInService {
  static SignInService _instance;

  factory SignInService.instance() {
    _instance ??= SignInService._private();
    return _instance;
  }

  SignInService._private();

  Future<Auth> signInUser(LoginData loginData) async {
    SignInEndpoint.instance().signInUser(loginData).then((value) {
      Auth.instance().user = value.user;
      Auth.instance().uuid = value.uuid;
    });

    print('SignInService: signInUser');
    return Auth.instance();
  }
}
