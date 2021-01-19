// Singleton
import 'package:app/endpoints/sign_in_endpoint.dart';
import 'package:app/models/login_data.dart';

import 'security/auth.dart';

class SignInService {
  static SignInService _instance;

  factory SignInService.instance() {
    _instance ??= SignInService._private();
    return _instance;
  }

  SignInService._private();

  Future<AuthService> signInUser(LoginData loginData) async {
    SignInEndpoint.instance().signInUser(loginData).then((value) {
      AuthService.instance().user = value.user;
      AuthService.instance().uuid = value.uuid;
    });

    print('SignInService: signInUser');
    return AuthService.instance();
  }
}
