import 'package:app/endpoints/sign_up_endpoint.dart';
import 'package:app/models/login_data.dart';
import 'package:app/models/user.dart';
import 'package:app/services/signin_service.dart';

import 'security/auth.dart';

// Singleton
class SignUpService {
  static SignUpService _instance;

  factory SignUpService.instance() {
    _instance ??= SignUpService._private();
    return _instance;
  }

  SignUpService._private();

  Future<AuthService> signUpUser(User user) async {
     return SignUpEndpoint.instance().signUpUser(user).then((newUser) {
      LoginData loginData = LoginData(username: user.username, password: user.password);


      print('SignUpService: signUpUser');
      return SignInService.instance().signInUser(loginData);
    });
  }
}
